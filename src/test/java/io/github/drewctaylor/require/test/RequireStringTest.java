package io.github.drewctaylor.require.test;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.regex.Pattern;

import static io.github.drewctaylor.require.RequireString.requireBlank;
import static io.github.drewctaylor.require.RequireString.requireEmpty;
import static io.github.drewctaylor.require.RequireString.requireLength;
import static io.github.drewctaylor.require.RequireString.requireLengthExclusive;
import static io.github.drewctaylor.require.RequireString.requireLengthGreaterThan;
import static io.github.drewctaylor.require.RequireString.requireLengthGreaterThanOrEqual;
import static io.github.drewctaylor.require.RequireString.requireLengthLessThan;
import static io.github.drewctaylor.require.RequireString.requireLengthLessThanOrEqual;
import static io.github.drewctaylor.require.RequireString.requireLengthMinimumExclusiveMaximumInclusive;
import static io.github.drewctaylor.require.RequireString.requireLengthMinimumInclusiveMaximumExclusive;
import static io.github.drewctaylor.require.RequireString.requireMatch;
import static io.github.drewctaylor.require.RequireString.requireNonBlank;
import static io.github.drewctaylor.require.RequireString.requireNonEmpty;
import static java.lang.Integer.valueOf;
import static java.util.Arrays.asList;
import static java.util.regex.Pattern.compile;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class RequireStringTest
{
    @Test
    void testRequireEmpty()
    {
        final String invalid = "a";
        final String valid = "";

        assertThrows(NullPointerException.class, () -> requireEmpty(valid, null));
        assertThrows(IllegalArgumentException.class, () -> requireEmpty(valid, ""));
        assertThrows(IllegalArgumentException.class, () -> requireEmpty(valid, " "));

        assertThrows(IllegalArgumentException.class, () -> requireEmpty(invalid, "name"));
        assertEquals(valid, requireEmpty(valid, "name"));
    }

    @Test
    void testRequireNonEmpty()
    {
        final String invalid = "";
        final String valid = "a";

        assertThrows(NullPointerException.class, () -> requireNonEmpty(valid, null));
        assertThrows(IllegalArgumentException.class, () -> requireNonEmpty(valid, ""));
        assertThrows(IllegalArgumentException.class, () -> requireNonEmpty(valid, " "));

        assertThrows(IllegalArgumentException.class, () -> requireNonEmpty(invalid, "name"));
        assertEquals(valid, requireNonEmpty(valid, "name"));
    }

    @Test
    void testRequireBlank()
    {
        final String invalid = "a";
        final String valid = " ";

        assertThrows(NullPointerException.class, () -> requireBlank(valid, null));
        assertThrows(IllegalArgumentException.class, () -> requireBlank(valid, ""));
        assertThrows(IllegalArgumentException.class, () -> requireBlank(valid, " "));

        assertThrows(IllegalArgumentException.class, () -> requireBlank(invalid, "name"));
        assertEquals(valid, requireBlank(valid, "name"));
    }

    @Test
    void testRequireNonBlank()
    {
        final String invalid = " ";
        final String valid = "a";

        assertThrows(NullPointerException.class, () -> requireNonBlank(valid, null));
        assertThrows(IllegalArgumentException.class, () -> requireNonBlank(valid, ""));
        assertThrows(IllegalArgumentException.class, () -> requireNonBlank(valid, " "));

        assertThrows(IllegalArgumentException.class, () -> requireNonBlank(invalid, "name"));
        assertEquals(valid, requireNonBlank(valid, "name"));
    }

    @Test
    void testRequireMatch()
    {
        final List<String> listForInvalid = asList("", "aaaa", "b", "bb", "bbb");
        final List<String> listForValid = asList("a", "aa", "aaa");
        final Pattern pattern = compile("a{1,3}");

        listForValid.forEach(valid -> assertThrows(NullPointerException.class, () -> requireMatch(valid, pattern, null)));
        listForValid.forEach(valid -> assertThrows(IllegalArgumentException.class, () -> requireMatch(valid, pattern, "")));
        listForValid.forEach(valid -> assertThrows(IllegalArgumentException.class, () -> requireMatch(valid, pattern, " ")));

        listForValid.forEach(valid -> assertThrows(NullPointerException.class, () -> requireMatch(valid, null, "name")));

        listForInvalid.forEach(invalid -> assertThrows(IllegalArgumentException.class, () -> requireMatch(invalid, pattern, "name")));
        listForValid.forEach(valid -> assertEquals(valid, requireMatch(valid, pattern, "name")));
    }

    private static void testRequireLengthHelper(
            final List<String> list)
    {
        assertThrows(NullPointerException.class, () -> requireLengthLessThan(list.get(0), Integer.MAX_VALUE, null));
        assertThrows(NullPointerException.class, () -> requireLengthLessThanOrEqual(list.get(0), Integer.MAX_VALUE, null));
        assertThrows(NullPointerException.class, () -> requireLength(list.get(0), Integer.MAX_VALUE, null));
        assertThrows(NullPointerException.class, () -> requireLengthGreaterThanOrEqual(list.get(0), Integer.MAX_VALUE, null));
        assertThrows(NullPointerException.class, () -> requireLengthGreaterThan(list.get(0), Integer.MAX_VALUE, null));

        assertThrows(IllegalArgumentException.class, () -> requireLengthLessThan(list.get(0), Integer.MAX_VALUE, ""));
        assertThrows(IllegalArgumentException.class, () -> requireLengthLessThanOrEqual(list.get(0), Integer.MAX_VALUE, ""));
        assertThrows(IllegalArgumentException.class, () -> requireLength(list.get(0), Integer.MAX_VALUE, ""));
        assertThrows(IllegalArgumentException.class, () -> requireLengthGreaterThanOrEqual(list.get(0), Integer.MAX_VALUE, ""));
        assertThrows(IllegalArgumentException.class, () -> requireLengthGreaterThan(list.get(0), Integer.MAX_VALUE, ""));

        assertThrows(IllegalArgumentException.class, () -> requireLengthLessThan(list.get(0), Integer.MAX_VALUE, " "));
        assertThrows(IllegalArgumentException.class, () -> requireLengthLessThanOrEqual(list.get(0), Integer.MAX_VALUE, " "));
        assertThrows(IllegalArgumentException.class, () -> requireLength(list.get(0), Integer.MAX_VALUE, " "));
        assertThrows(IllegalArgumentException.class, () -> requireLengthGreaterThanOrEqual(list.get(0), Integer.MAX_VALUE, " "));
        assertThrows(IllegalArgumentException.class, () -> requireLengthGreaterThan(list.get(0), Integer.MAX_VALUE, " "));

        assertThrows(NullPointerException.class, () -> requireLengthExclusive(list.get(0), 0, Integer.MAX_VALUE, null));
        assertThrows(NullPointerException.class, () -> requireLength(list.get(0), 0, Integer.MAX_VALUE, null));
        assertThrows(NullPointerException.class, () -> requireLengthMinimumExclusiveMaximumInclusive(list.get(0), 0, Integer.MAX_VALUE, null));
        assertThrows(NullPointerException.class, () -> requireLengthMinimumInclusiveMaximumExclusive(list.get(0), 0, Integer.MAX_VALUE, null));

        assertThrows(IllegalArgumentException.class, () -> requireLengthExclusive(list.get(0), 0, Integer.MAX_VALUE, ""));
        assertThrows(IllegalArgumentException.class, () -> requireLength(list.get(0), 0, Integer.MAX_VALUE, ""));
        assertThrows(IllegalArgumentException.class, () -> requireLengthMinimumExclusiveMaximumInclusive(list.get(0), 0, Integer.MAX_VALUE, ""));
        assertThrows(IllegalArgumentException.class, () -> requireLengthMinimumInclusiveMaximumExclusive(list.get(0), 0, Integer.MAX_VALUE, ""));

        assertThrows(IllegalArgumentException.class, () -> requireLengthExclusive(list.get(0), 0, Integer.MAX_VALUE, " "));
        assertThrows(IllegalArgumentException.class, () -> requireLength(list.get(0), 0, Integer.MAX_VALUE, " "));
        assertThrows(IllegalArgumentException.class, () -> requireLengthMinimumExclusiveMaximumInclusive(list.get(0), 0, Integer.MAX_VALUE, " "));
        assertThrows(IllegalArgumentException.class, () -> requireLengthMinimumInclusiveMaximumExclusive(list.get(0), 0, Integer.MAX_VALUE, " "));

        list.stream().map(String::length).forEach(bound -> list.stream().filter(value -> valueOf(value.length()).compareTo(bound) < 0).forEach(value -> assertEquals(value, requireLengthLessThan(value, bound, "name"))));
        list.stream().map(String::length).forEach(bound -> list.stream().filter(value -> valueOf(value.length()).compareTo(bound) <= 0).forEach(value -> assertEquals(value, requireLengthLessThanOrEqual(value, bound, "name"))));
        list.stream().map(String::length).forEach(bound -> list.stream().filter(value -> valueOf(value.length()).compareTo(bound) == 0).forEach(value -> assertEquals(value, requireLength(value, bound, "name"))));
        list.stream().map(String::length).forEach(bound -> list.stream().filter(value -> valueOf(value.length()).compareTo(bound) >= 0).forEach(value -> assertEquals(value, requireLengthGreaterThanOrEqual(value, bound, "name"))));
        list.stream().map(String::length).forEach(bound -> list.stream().filter(value -> valueOf(value.length()).compareTo(bound) > 0).forEach(value -> assertEquals(value, requireLengthGreaterThan(value, bound, "name"))));

        list.stream().map(String::length).forEach(bound -> list.stream().filter(value -> valueOf(value.length()).compareTo(bound) >= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireLengthLessThan(value, bound, "name"))));
        list.stream().map(String::length).forEach(bound -> list.stream().filter(value -> valueOf(value.length()).compareTo(bound) > 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireLengthLessThanOrEqual(value, bound, "name"))));
        list.stream().map(String::length).forEach(bound -> list.stream().filter(value -> valueOf(value.length()).compareTo(bound) != 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireLength(value, bound, "name"))));
        list.stream().map(String::length).forEach(bound -> list.stream().filter(value -> valueOf(value.length()).compareTo(bound) < 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireLengthGreaterThanOrEqual(value, bound, "name"))));
        list.stream().map(String::length).forEach(bound -> list.stream().filter(value -> valueOf(value.length()).compareTo(bound) <= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireLengthGreaterThan(value, bound, "name"))));

        list.stream().map(String::length).forEach(boundMinimum -> list.stream().map(String::length).forEach(boundMaximum -> list.stream().filter(value -> valueOf(value.length()).compareTo(boundMinimum) <= 0 || valueOf(value.length()).compareTo(boundMaximum) >= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireLengthExclusive(value, boundMinimum, boundMaximum, "name")))));
        list.stream().map(String::length).forEach(boundMinimum -> list.stream().map(String::length).forEach(boundMaximum -> list.stream().filter(value -> valueOf(value.length()).compareTo(boundMinimum) < 0 || valueOf(value.length()).compareTo(boundMaximum) > 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireLength(value, boundMinimum, boundMaximum, "name")))));
        list.stream().map(String::length).forEach(boundMinimum -> list.stream().map(String::length).forEach(boundMaximum -> list.stream().filter(value -> valueOf(value.length()).compareTo(boundMinimum) <= 0 || valueOf(value.length()).compareTo(boundMaximum) > 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireLengthMinimumExclusiveMaximumInclusive(value, boundMinimum, boundMaximum, "name")))));
        list.stream().map(String::length).forEach(boundMinimum -> list.stream().map(String::length).forEach(boundMaximum -> list.stream().filter(value -> valueOf(value.length()).compareTo(boundMinimum) < 0 || valueOf(value.length()).compareTo(boundMaximum) >= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireLengthMinimumInclusiveMaximumExclusive(value, boundMinimum, boundMaximum, "name")))));

        list.stream().map(String::length).forEach(boundMinimum -> list.stream().map(String::length).forEach(boundMaximum -> list.stream().filter(value -> valueOf(value.length()).compareTo(boundMinimum) > 0 && valueOf(value.length()).compareTo(boundMaximum) < 0).forEach(value -> assertEquals(value, requireLengthExclusive(value, boundMinimum, boundMaximum, "name")))));
        list.stream().map(String::length).forEach(boundMinimum -> list.stream().map(String::length).forEach(boundMaximum -> list.stream().filter(value -> valueOf(value.length()).compareTo(boundMinimum) >= 0 && valueOf(value.length()).compareTo(boundMaximum) <= 0).forEach(value -> assertEquals(value, requireLength(value, boundMinimum, boundMaximum, "name")))));
        list.stream().map(String::length).forEach(boundMinimum -> list.stream().map(String::length).forEach(boundMaximum -> list.stream().filter(value -> valueOf(value.length()).compareTo(boundMinimum) > 0 && valueOf(value.length()).compareTo(boundMaximum) <= 0).forEach(value -> assertEquals(value, requireLengthMinimumExclusiveMaximumInclusive(value, boundMinimum, boundMaximum, "name")))));
        list.stream().map(String::length).forEach(boundMinimum -> list.stream().map(String::length).forEach(boundMaximum -> list.stream().filter(value -> valueOf(value.length()).compareTo(boundMinimum) >= 0 && valueOf(value.length()).compareTo(boundMaximum) < 0).forEach(value -> assertEquals(value, requireLengthMinimumInclusiveMaximumExclusive(value, boundMinimum, boundMaximum, "name")))));
    }

    @Test
    void testRequireLength()
    {
        // noinspection NumericCastThatLosesPrecision,CharUsedInArithmeticContext
        testRequireLengthHelper(range(0, 6).mapToObj(i -> range(0, i + 1).mapToObj(index -> (char) ('a' + index)).map(String::valueOf).reduce("", String::concat)).collect(toList()));
    }
}
