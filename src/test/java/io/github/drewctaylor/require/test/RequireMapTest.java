package io.github.drewctaylor.require.test;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static io.github.drewctaylor.require.RequireBound.requireGreaterThan;
import static io.github.drewctaylor.require.RequireMap.requireEmpty;
import static io.github.drewctaylor.require.RequireMap.requireForAllKey;
import static io.github.drewctaylor.require.RequireMap.requireForAllValue;
import static io.github.drewctaylor.require.RequireMap.requireNonEmpty;
import static io.github.drewctaylor.require.RequireMap.requireSize;
import static io.github.drewctaylor.require.RequireMap.requireSizeExclusive;
import static io.github.drewctaylor.require.RequireMap.requireSizeGreaterThan;
import static io.github.drewctaylor.require.RequireMap.requireSizeGreaterThanOrEqual;
import static io.github.drewctaylor.require.RequireMap.requireSizeInclusive;
import static io.github.drewctaylor.require.RequireMap.requireSizeLessThan;
import static io.github.drewctaylor.require.RequireMap.requireSizeLessThanOrEqual;
import static io.github.drewctaylor.require.RequireMap.requireSizeMinimumExclusiveMaximumInclusive;
import static io.github.drewctaylor.require.RequireMap.requireSizeMinimumInclusiveMaximumExclusive;
import static io.github.drewctaylor.require.RequireMap.requireThereExistsKey;
import static io.github.drewctaylor.require.RequireMap.requireThereExistsValue;
import static java.lang.Integer.valueOf;
import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonMap;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class RequireMapTest
{
    @Test
    void testRequireEmpty()
    {
        final var valueInvalid = singletonMap(new Object(), new Object());
        final var valueValid = emptyMap();

        assertThrows(IllegalArgumentException.class, () -> requireEmpty(valueInvalid, "name"));
        assertEquals(valueValid, requireEmpty(valueValid, "name"));
    }

    @Test
    void testRequireNonEmpty()
    {
        final var valid = singletonMap(new Object(), new Object());

        assertThrows(IllegalArgumentException.class, () -> requireNonEmpty(emptyMap(), "name"));
        assertEquals(valid, requireNonEmpty(valid, "name"));
    }

    private static <KEY, VALUE, MAP extends Map<KEY, VALUE>> void testRequireSizeHelper(
            final List<MAP> list)
    {
        list.stream().map(Map::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) < 0).forEach(value -> assertEquals(value, requireSizeLessThan(value, bound, "name"))));
        list.stream().map(Map::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) <= 0).forEach(value -> assertEquals(value, requireSizeLessThanOrEqual(value, bound, "name"))));
        list.stream().map(Map::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) == 0).forEach(value -> assertEquals(value, requireSize(value, bound, "name"))));
        list.stream().map(Map::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) >= 0).forEach(value -> assertEquals(value, requireSizeGreaterThanOrEqual(value, bound, "name"))));
        list.stream().map(Map::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) > 0).forEach(value -> assertEquals(value, requireSizeGreaterThan(value, bound, "name"))));

        list.stream().map(Map::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) >= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireSizeLessThan(value, bound, "name"))));
        list.stream().map(Map::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) > 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireSizeLessThanOrEqual(value, bound, "name"))));
        list.stream().map(Map::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) != 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireSize(value, bound, "name"))));
        list.stream().map(Map::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) < 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireSizeGreaterThanOrEqual(value, bound, "name"))));
        list.stream().map(Map::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) <= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireSizeGreaterThan(value, bound, "name"))));

        list.stream().map(Map::size).forEach(boundMinimum -> list.stream().map(Map::size).forEach(boundMaximum -> list.stream().filter(value -> valueOf(value.size()).compareTo(boundMinimum) <= 0 || valueOf(value.size()).compareTo(boundMaximum) >= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireSizeExclusive(value, boundMinimum, boundMaximum, "name")))));
        list.stream().map(Map::size).forEach(boundMinimum -> list.stream().map(Map::size).forEach(boundMaximum -> list.stream().filter(value -> valueOf(value.size()).compareTo(boundMinimum) < 0 || valueOf(value.size()).compareTo(boundMaximum) > 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireSizeInclusive(value, boundMinimum, boundMaximum, "name")))));
        list.stream().map(Map::size).forEach(boundMinimum -> list.stream().map(Map::size).forEach(boundMaximum -> list.stream().filter(value -> valueOf(value.size()).compareTo(boundMinimum) <= 0 || valueOf(value.size()).compareTo(boundMaximum) > 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireSizeMinimumExclusiveMaximumInclusive(value, boundMinimum, boundMaximum, "name")))));
        list.stream().map(Map::size).forEach(boundMinimum -> list.stream().map(Map::size).forEach(boundMaximum -> list.stream().filter(value -> valueOf(value.size()).compareTo(boundMinimum) < 0 || valueOf(value.size()).compareTo(boundMaximum) >= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireSizeMinimumInclusiveMaximumExclusive(value, boundMinimum, boundMaximum, "name")))));

        list.stream().map(Map::size).forEach(boundMinimum -> list.stream().map(Map::size).forEach(boundMaximum -> list.stream().filter(value -> valueOf(value.size()).compareTo(boundMinimum) > 0 && valueOf(value.size()).compareTo(boundMaximum) < 0).forEach(value -> assertEquals(value, requireSizeExclusive(value, boundMinimum, boundMaximum, "name")))));
        list.stream().map(Map::size).forEach(boundMinimum -> list.stream().map(Map::size).forEach(boundMaximum -> list.stream().filter(value -> valueOf(value.size()).compareTo(boundMinimum) >= 0 && valueOf(value.size()).compareTo(boundMaximum) <= 0).forEach(value -> assertEquals(value, requireSizeInclusive(value, boundMinimum, boundMaximum, "name")))));
        list.stream().map(Map::size).forEach(boundMinimum -> list.stream().map(Map::size).forEach(boundMaximum -> list.stream().filter(value -> valueOf(value.size()).compareTo(boundMinimum) > 0 && valueOf(value.size()).compareTo(boundMaximum) <= 0).forEach(value -> assertEquals(value, requireSizeMinimumExclusiveMaximumInclusive(value, boundMinimum, boundMaximum, "name")))));
        list.stream().map(Map::size).forEach(boundMinimum -> list.stream().map(Map::size).forEach(boundMaximum -> list.stream().filter(value -> valueOf(value.size()).compareTo(boundMinimum) >= 0 && valueOf(value.size()).compareTo(boundMaximum) < 0).forEach(value -> assertEquals(value, requireSizeMinimumInclusiveMaximumExclusive(value, boundMinimum, boundMaximum, "name")))));
    }

    @Test
    void testRequireSize()
    {
        testRequireSizeHelper(range(0, 6).mapToObj(i -> range(0, i + 1).mapToObj(index -> new Object()).collect(toMap(Function.identity(), Function.identity()))).collect(toList()));
    }

    @Test
    void testRequireForAllAndThereExists()
    {
        final Function<Object, Object> failure = object ->
        {
            throw new IllegalArgumentException();
        };

        final var map = Map.of(-1, "b", 0, "c", 1, "d");

        assertEquals(emptyMap(), requireForAllKey(emptyMap(), failure, "name"));
        assertEquals(map, requireForAllKey(map, identity(), "name"));
        assertEquals(map, requireForAllKey(map, i -> requireGreaterThan(i, -2, "i"), "name"));
        assertThrows(IllegalArgumentException.class, () -> requireForAllKey(map, i -> requireGreaterThan(i, 0, "i"), "name"));
        assertThrows(IllegalArgumentException.class, () -> requireForAllKey(map, i -> requireGreaterThan(i, 1, "i"), "name"));

        assertThrows(IllegalArgumentException.class, () -> requireThereExistsKey(emptyMap(), identity(), "name"));
        assertEquals(map, requireThereExistsKey(map, identity(), "name"));
        assertEquals(map, requireThereExistsKey(map, i -> requireGreaterThan(i, -2, "i"), "name"));
        assertEquals(map, requireThereExistsKey(map, i -> requireGreaterThan(i, 0, "i"), "name"));
        assertThrows(IllegalArgumentException.class, () -> requireThereExistsKey(map, i -> requireGreaterThan(i, 1, "i"), "name"));

        assertEquals(emptyMap(), requireForAllValue(emptyMap(), failure, "name"));
        assertEquals(map, requireForAllValue(map, identity(), "name"));
        assertEquals(map, requireForAllValue(map, s -> requireGreaterThan(s, "a", "s"), "name"));
        assertThrows(IllegalArgumentException.class, () -> requireForAllValue(map, s -> requireGreaterThan(s, "c", "s"), "name"));
        assertThrows(IllegalArgumentException.class, () -> requireForAllValue(map, s -> requireGreaterThan(s, "d", "s"), "name"));

        assertThrows(IllegalArgumentException.class, () -> requireThereExistsValue(emptyMap(), identity(), "name"));
        assertEquals(map, requireThereExistsValue(map, identity(), "name"));
        assertEquals(map, requireThereExistsValue(map, s -> requireGreaterThan(s, "a", "s"), "name"));
        assertEquals(map, requireThereExistsValue(map, s -> requireGreaterThan(s, "c", "s"), "name"));
        assertThrows(IllegalArgumentException.class, () -> requireThereExistsValue(map, s -> requireGreaterThan(s, "d", "s"), "name"));
    }
}
