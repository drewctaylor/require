package io.github.drewctaylor.require.test;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import static io.github.drewctaylor.require.RequireBound.requireGreaterThan;
import static io.github.drewctaylor.require.RequireCollection.requireEmpty;
import static io.github.drewctaylor.require.RequireCollection.requireForAll;
import static io.github.drewctaylor.require.RequireCollection.requireNonEmpty;
import static io.github.drewctaylor.require.RequireCollection.requireSize;
import static io.github.drewctaylor.require.RequireCollection.requireSizeExclusive;
import static io.github.drewctaylor.require.RequireCollection.requireSizeGreaterThan;
import static io.github.drewctaylor.require.RequireCollection.requireSizeGreaterThanOrEqual;
import static io.github.drewctaylor.require.RequireCollection.requireSizeLessThan;
import static io.github.drewctaylor.require.RequireCollection.requireSizeLessThanOrEqual;
import static io.github.drewctaylor.require.RequireCollection.requireSizeMinimumExclusiveMaximumInclusive;
import static io.github.drewctaylor.require.RequireCollection.requireSizeMinimumInclusiveMaximumExclusive;
import static io.github.drewctaylor.require.RequireCollection.requireThereExists;
import static java.lang.Integer.valueOf;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class RequireCollectionTest
{
    @Test
    void testRequireEmpty()
    {
        final Collection<Object> valueInvalid = singletonList(new Object());
        final Collection<Object> valueValid = emptyList();

        assertThrows(NullPointerException.class, () -> requireEmpty(valueValid, null));
        assertThrows(IllegalArgumentException.class, () -> requireEmpty(valueValid, ""));
        assertThrows(IllegalArgumentException.class, () -> requireEmpty(valueValid, " "));

        assertThrows(IllegalArgumentException.class, () -> requireEmpty(valueInvalid, "name"));
        assertEquals(valueValid, requireEmpty(valueValid, "name"));
    }

    @Test
    void testRequireNonEmpty()
    {
        final var valid = singletonList(new Object());

        assertThrows(NullPointerException.class, () -> requireNonEmpty(emptyList(), null));
        assertThrows(IllegalArgumentException.class, () -> requireNonEmpty(emptyList(), ""));
        assertThrows(IllegalArgumentException.class, () -> requireNonEmpty(emptyList(), " "));

        assertThrows(IllegalArgumentException.class, () -> requireNonEmpty(emptyList(), "name"));
        assertEquals(valid, requireNonEmpty(valid, "name"));
    }

    private static <TYPE, COLLECTION extends Collection<TYPE>> void testRequireSizeHelper(
            final List<COLLECTION> list)
    {
        list.stream().map(Collection::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) < 0).forEach(value -> assertThrows(NullPointerException.class, () -> requireSizeLessThan(value, bound, null))));
        list.stream().map(Collection::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) <= 0).forEach(value -> assertThrows(NullPointerException.class, () -> requireSizeLessThanOrEqual(value, bound, null))));
        list.stream().map(Collection::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) == 0).forEach(value -> assertThrows(NullPointerException.class, () -> requireSize(value, bound, null))));
        list.stream().map(Collection::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) >= 0).forEach(value -> assertThrows(NullPointerException.class, () -> requireSizeGreaterThanOrEqual(value, bound, null))));
        list.stream().map(Collection::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) > 0).forEach(value -> assertThrows(NullPointerException.class, () -> requireSizeGreaterThan(value, bound, null))));

        list.stream().map(Collection::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) < 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireSizeLessThan(value, bound, ""))));
        list.stream().map(Collection::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) <= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireSizeLessThanOrEqual(value, bound, ""))));
        list.stream().map(Collection::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) == 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireSize(value, bound, ""))));
        list.stream().map(Collection::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) >= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireSizeGreaterThanOrEqual(value, bound, ""))));
        list.stream().map(Collection::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) > 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireSizeGreaterThan(value, bound, ""))));

        list.stream().map(Collection::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) < 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireSizeLessThan(value, bound, " "))));
        list.stream().map(Collection::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) <= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireSizeLessThanOrEqual(value, bound, " "))));
        list.stream().map(Collection::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) == 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireSize(value, bound, " "))));
        list.stream().map(Collection::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) >= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireSizeGreaterThanOrEqual(value, bound, " "))));
        list.stream().map(Collection::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) > 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireSizeGreaterThan(value, bound, " "))));

        list.stream().map(Collection::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) < 0).forEach(value -> assertEquals(value, requireSizeLessThan(value, bound, "name"))));
        list.stream().map(Collection::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) <= 0).forEach(value -> assertEquals(value, requireSizeLessThanOrEqual(value, bound, "name"))));
        list.stream().map(Collection::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) == 0).forEach(value -> assertEquals(value, requireSize(value, bound, "name"))));
        list.stream().map(Collection::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) >= 0).forEach(value -> assertEquals(value, requireSizeGreaterThanOrEqual(value, bound, "name"))));
        list.stream().map(Collection::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) > 0).forEach(value -> assertEquals(value, requireSizeGreaterThan(value, bound, "name"))));

        list.stream().map(Collection::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) >= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireSizeLessThan(value, bound, "name"))));
        list.stream().map(Collection::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) > 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireSizeLessThanOrEqual(value, bound, "name"))));
        list.stream().map(Collection::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) != 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireSize(value, bound, "name"))));
        list.stream().map(Collection::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) < 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireSizeGreaterThanOrEqual(value, bound, "name"))));
        list.stream().map(Collection::size).forEach(bound -> list.stream().filter(value -> valueOf(value.size()).compareTo(bound) <= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireSizeGreaterThan(value, bound, "name"))));

        list.stream().map(Collection::size).forEach(boundMinimum -> list.stream().map(Collection::size).forEach(boundMaximum -> list.stream().filter(value -> valueOf(value.size()).compareTo(boundMinimum) <= 0 || valueOf(value.size()).compareTo(boundMaximum) >= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireSizeExclusive(value, boundMinimum, boundMaximum, "name")))));
        list.stream().map(Collection::size).forEach(boundMinimum -> list.stream().map(Collection::size).forEach(boundMaximum -> list.stream().filter(value -> valueOf(value.size()).compareTo(boundMinimum) < 0 || valueOf(value.size()).compareTo(boundMaximum) > 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireSize(value, boundMinimum, boundMaximum, "name")))));
        list.stream().map(Collection::size).forEach(boundMinimum -> list.stream().map(Collection::size).forEach(boundMaximum -> list.stream().filter(value -> valueOf(value.size()).compareTo(boundMinimum) <= 0 || valueOf(value.size()).compareTo(boundMaximum) > 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireSizeMinimumExclusiveMaximumInclusive(value, boundMinimum, boundMaximum, "name")))));
        list.stream().map(Collection::size).forEach(boundMinimum -> list.stream().map(Collection::size).forEach(boundMaximum -> list.stream().filter(value -> valueOf(value.size()).compareTo(boundMinimum) < 0 || valueOf(value.size()).compareTo(boundMaximum) >= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireSizeMinimumInclusiveMaximumExclusive(value, boundMinimum, boundMaximum, "name")))));

        list.stream().map(Collection::size).forEach(boundMinimum -> list.stream().map(Collection::size).forEach(boundMaximum -> list.stream().filter(value -> valueOf(value.size()).compareTo(boundMinimum) > 0 && valueOf(value.size()).compareTo(boundMaximum) < 0).forEach(value -> assertEquals(value, requireSizeExclusive(value, boundMinimum, boundMaximum, "name")))));
        list.stream().map(Collection::size).forEach(boundMinimum -> list.stream().map(Collection::size).forEach(boundMaximum -> list.stream().filter(value -> valueOf(value.size()).compareTo(boundMinimum) >= 0 && valueOf(value.size()).compareTo(boundMaximum) <= 0).forEach(value -> assertEquals(value, requireSize(value, boundMinimum, boundMaximum, "name")))));
        list.stream().map(Collection::size).forEach(boundMinimum -> list.stream().map(Collection::size).forEach(boundMaximum -> list.stream().filter(value -> valueOf(value.size()).compareTo(boundMinimum) > 0 && valueOf(value.size()).compareTo(boundMaximum) <= 0).forEach(value -> assertEquals(value, requireSizeMinimumExclusiveMaximumInclusive(value, boundMinimum, boundMaximum, "name")))));
        list.stream().map(Collection::size).forEach(boundMinimum -> list.stream().map(Collection::size).forEach(boundMaximum -> list.stream().filter(value -> valueOf(value.size()).compareTo(boundMinimum) >= 0 && valueOf(value.size()).compareTo(boundMaximum) < 0).forEach(value -> assertEquals(value, requireSizeMinimumInclusiveMaximumExclusive(value, boundMinimum, boundMaximum, "name")))));
    }

    @Test
    void testRequireSize()
    {
        testRequireSizeHelper(range(0, 6).mapToObj(i -> range(0, i + 1).mapToObj(index -> new Object()).collect(toList())).collect(toList()));
        testRequireSizeHelper(range(0, 6).mapToObj(i -> range(0, i + 1).mapToObj(index -> new Object()).collect(toSet())).collect(toList()));
    }

    @Test
    void testRequireForAllAndThereExists()
    {
        final Function<Object, Object> failure = object ->
        {
            throw new IllegalArgumentException("");
        };

        final var list = List.of(-1, 0, 1);

        assertThrows(NullPointerException.class, () -> requireForAll(emptyList(), failure, null));
        assertThrows(IllegalArgumentException.class, () -> requireForAll(emptyList(), failure, ""));
        assertThrows(IllegalArgumentException.class, () -> requireForAll(emptyList(), failure, " "));

        assertEquals(emptyList(), requireForAll(emptyList(), failure, "name"));
        assertEquals(list, requireForAll(list, identity(), "name"));
        assertEquals(list, requireForAll(list, i -> requireGreaterThan(i, -2, "i"), "name"));
        assertThrows(IllegalArgumentException.class, () -> requireForAll(list, i -> requireGreaterThan(i, 0, "i"), "name"));
        assertThrows(IllegalArgumentException.class, () -> requireForAll(list, i -> requireGreaterThan(i, 1, "i"), "name"));

        assertThrows(NullPointerException.class, () -> requireThereExists(emptyList(), identity(), null));
        assertThrows(IllegalArgumentException.class, () -> requireThereExists(emptyList(), identity(), ""));
        assertThrows(IllegalArgumentException.class, () -> requireThereExists(emptyList(), identity(), " "));

        assertThrows(IllegalArgumentException.class, () -> requireThereExists(emptyList(), identity(), "name"));
        assertEquals(list, requireThereExists(list, identity(), "name"));
        assertEquals(list, requireThereExists(list, i -> requireGreaterThan(i, -2, "i"), "name"));
        assertEquals(list, requireThereExists(list, i -> requireGreaterThan(i, 0, "i"), "name"));
        assertThrows(IllegalArgumentException.class, () -> requireThereExists(list, i -> requireGreaterThan(i, 1, "i"), "name"));
    }
}
