package io.github.drewctaylor.require.test;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.function.Function;

import static io.github.drewctaylor.require.RequireBound.requireBoundExclusive;
import static io.github.drewctaylor.require.RequireBound.requireBoundInclusive;
import static io.github.drewctaylor.require.RequireBound.requireBoundMinimumExclusiveMaximumInclusive;
import static io.github.drewctaylor.require.RequireBound.requireBoundMinimumInclusiveMaximumExclusive;
import static io.github.drewctaylor.require.RequireBound.requireEqual;
import static io.github.drewctaylor.require.RequireBound.requireGreaterThan;
import static io.github.drewctaylor.require.RequireBound.requireGreaterThanOrEqual;
import static io.github.drewctaylor.require.RequireBound.requireLessThan;
import static io.github.drewctaylor.require.RequireBound.requireLessThanOrEqual;
import static java.lang.String.valueOf;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.iterate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class RequireBoundTest
{
    private static <TYPE extends Comparable<TYPE>> void testRequireBoundHelper(
            final List<TYPE> list)
    {
        list.stream().forEach(bound -> list.stream().filter(value -> value.compareTo(bound) < 0).forEach(value -> assertThrows(NullPointerException.class, () -> requireLessThan(value, bound, null))));
        list.stream().forEach(bound -> list.stream().filter(value -> value.compareTo(bound) <= 0).forEach(value -> assertThrows(NullPointerException.class, () -> requireLessThanOrEqual(value, bound, null))));
        list.stream().forEach(bound -> list.stream().filter(value -> value.compareTo(bound) == 0).forEach(value -> assertThrows(NullPointerException.class, () -> requireEqual(value, bound, null))));
        list.stream().forEach(bound -> list.stream().filter(value -> value.compareTo(bound) >= 0).forEach(value -> assertThrows(NullPointerException.class, () -> requireGreaterThanOrEqual(value, bound, null))));
        list.stream().forEach(bound -> list.stream().filter(value -> value.compareTo(bound) > 0).forEach(value -> assertThrows(NullPointerException.class, () -> requireGreaterThan(value, bound, null))));

        list.stream().forEach(bound -> list.stream().filter(value -> value.compareTo(bound) < 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireLessThan(value, bound, ""))));
        list.stream().forEach(bound -> list.stream().filter(value -> value.compareTo(bound) <= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireLessThanOrEqual(value, bound, ""))));
        list.stream().forEach(bound -> list.stream().filter(value -> value.compareTo(bound) == 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireEqual(value, bound, ""))));
        list.stream().forEach(bound -> list.stream().filter(value -> value.compareTo(bound) >= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireGreaterThanOrEqual(value, bound, ""))));
        list.stream().forEach(bound -> list.stream().filter(value -> value.compareTo(bound) > 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireGreaterThan(value, bound, ""))));

        list.stream().forEach(bound -> list.stream().filter(value -> value.compareTo(bound) < 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireLessThan(value, bound, " "))));
        list.stream().forEach(bound -> list.stream().filter(value -> value.compareTo(bound) <= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireLessThanOrEqual(value, bound, " "))));
        list.stream().forEach(bound -> list.stream().filter(value -> value.compareTo(bound) == 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireEqual(value, bound, " "))));
        list.stream().forEach(bound -> list.stream().filter(value -> value.compareTo(bound) >= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireGreaterThanOrEqual(value, bound, " "))));
        list.stream().forEach(bound -> list.stream().filter(value -> value.compareTo(bound) > 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireGreaterThan(value, bound, " "))));

        list.stream().forEach(bound -> list.stream().filter(value -> value.compareTo(bound) < 0).forEach(value -> assertEquals(value, requireLessThan(value, bound, "name"))));
        list.stream().forEach(bound -> list.stream().filter(value -> value.compareTo(bound) <= 0).forEach(value -> assertEquals(value, requireLessThanOrEqual(value, bound, "name"))));
        list.stream().forEach(bound -> list.stream().filter(value -> value.compareTo(bound) == 0).forEach(value -> assertEquals(value, requireEqual(value, bound, "name"))));
        list.stream().forEach(bound -> list.stream().filter(value -> value.compareTo(bound) >= 0).forEach(value -> assertEquals(value, requireGreaterThanOrEqual(value, bound, "name"))));
        list.stream().forEach(bound -> list.stream().filter(value -> value.compareTo(bound) > 0).forEach(value -> assertEquals(value, requireGreaterThan(value, bound, "name"))));

        list.stream().forEach(bound -> list.stream().filter(value -> value.compareTo(bound) >= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireLessThan(value, bound, "name"))));
        list.stream().forEach(bound -> list.stream().filter(value -> value.compareTo(bound) > 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireLessThanOrEqual(value, bound, "name"))));
        list.stream().forEach(bound -> list.stream().filter(value -> value.compareTo(bound) != 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireEqual(value, bound, "name"))));
        list.stream().forEach(bound -> list.stream().filter(value -> value.compareTo(bound) < 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireGreaterThanOrEqual(value, bound, "name"))));
        list.stream().forEach(bound -> list.stream().filter(value -> value.compareTo(bound) <= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireGreaterThan(value, bound, "name"))));

        list.stream().forEach(boundMinimum -> list.stream().forEach(boundMaximum -> list.stream().filter(value -> value.compareTo(boundMinimum) <= 0 || value.compareTo(boundMaximum) >= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireBoundExclusive(value, boundMinimum, boundMaximum, "name")))));
        list.stream().forEach(boundMinimum -> list.stream().forEach(boundMaximum -> list.stream().filter(value -> value.compareTo(boundMinimum) < 0 || value.compareTo(boundMaximum) > 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireBoundInclusive(value, boundMinimum, boundMaximum, "name")))));
        list.stream().forEach(boundMinimum -> list.stream().forEach(boundMaximum -> list.stream().filter(value -> value.compareTo(boundMinimum) <= 0 || value.compareTo(boundMaximum) > 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireBoundMinimumExclusiveMaximumInclusive(value, boundMinimum, boundMaximum, "name")))));
        list.stream().forEach(boundMinimum -> list.stream().forEach(boundMaximum -> list.stream().filter(value -> value.compareTo(boundMinimum) < 0 || value.compareTo(boundMaximum) >= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireBoundMinimumInclusiveMaximumExclusive(value, boundMinimum, boundMaximum, "name")))));

        list.stream().forEach(boundMinimum -> list.stream().forEach(boundMaximum -> list.stream().filter(value -> value.compareTo(boundMinimum) > 0 && value.compareTo(boundMaximum) < 0).forEach(value -> assertEquals(value, requireBoundExclusive(value, boundMinimum, boundMaximum, "name")))));
        list.stream().forEach(boundMinimum -> list.stream().forEach(boundMaximum -> list.stream().filter(value -> value.compareTo(boundMinimum) >= 0 && value.compareTo(boundMaximum) <= 0).forEach(value -> assertEquals(value, requireBoundInclusive(value, boundMinimum, boundMaximum, "name")))));
        list.stream().forEach(boundMinimum -> list.stream().forEach(boundMaximum -> list.stream().filter(value -> value.compareTo(boundMinimum) > 0 && value.compareTo(boundMaximum) <= 0).forEach(value -> assertEquals(value, requireBoundMinimumExclusiveMaximumInclusive(value, boundMinimum, boundMaximum, "name")))));
        list.stream().forEach(boundMinimum -> list.stream().forEach(boundMaximum -> list.stream().filter(value -> value.compareTo(boundMinimum) >= 0 && value.compareTo(boundMaximum) < 0).forEach(value -> assertEquals(value, requireBoundMinimumInclusiveMaximumExclusive(value, boundMinimum, boundMaximum, "name")))));
    }

    private static <T1, T2 extends Comparable<T2>> void testRequireBoundHelper(
            final List<T1> list,
            final Function<T1, T2> get)
    {
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) < 0).forEach(value -> assertThrows(NullPointerException.class, () -> requireLessThan(value, get, get.apply(bound), null, "fieldName"))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) <= 0).forEach(value -> assertThrows(NullPointerException.class, () -> requireLessThanOrEqual(value, get, get.apply(bound), null, "fieldName"))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) == 0).forEach(value -> assertThrows(NullPointerException.class, () -> requireEqual(value, get, get.apply(bound), null, "fieldName"))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) >= 0).forEach(value -> assertThrows(NullPointerException.class, () -> requireGreaterThanOrEqual(value, get, get.apply(bound), null, "fieldName"))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) > 0).forEach(value -> assertThrows(NullPointerException.class, () -> requireGreaterThan(value, get, get.apply(bound), null, "fieldName"))));

        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) < 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireLessThan(value, get, get.apply(bound), "", "fieldName"))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) <= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireLessThanOrEqual(value, get, get.apply(bound), "", "fieldName"))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) == 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireEqual(value, get, get.apply(bound), "", "fieldName"))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) >= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireGreaterThanOrEqual(value, get, get.apply(bound), "", "fieldName"))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) > 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireGreaterThan(value, get, get.apply(bound), "", "fieldName"))));

        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) < 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireLessThan(value, get, get.apply(bound), " ", "fieldName"))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) <= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireLessThanOrEqual(value, get, get.apply(bound), " ", "fieldName"))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) == 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireEqual(value, get, get.apply(bound), " ", "fieldName"))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) >= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireGreaterThanOrEqual(value, get, get.apply(bound), " ", "fieldName"))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) > 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireGreaterThan(value, get, get.apply(bound), " ", "fieldName"))));

        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) < 0).forEach(value -> assertThrows(NullPointerException.class, () -> requireLessThan(value, get, get.apply(bound), "parameterName", null))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) <= 0).forEach(value -> assertThrows(NullPointerException.class, () -> requireLessThanOrEqual(value, get, get.apply(bound), "parameterName", null))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) == 0).forEach(value -> assertThrows(NullPointerException.class, () -> requireEqual(value, get, get.apply(bound), "parameterName", null))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) >= 0).forEach(value -> assertThrows(NullPointerException.class, () -> requireGreaterThanOrEqual(value, get, get.apply(bound), "parameterName", null))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) > 0).forEach(value -> assertThrows(NullPointerException.class, () -> requireGreaterThan(value, get, get.apply(bound), "parameterName", null))));

        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) < 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireLessThan(value, get, get.apply(bound), "parameterName", ""))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) <= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireLessThanOrEqual(value, get, get.apply(bound), "parameterName", ""))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) == 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireEqual(value, get, get.apply(bound), "parameterName", ""))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) >= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireGreaterThanOrEqual(value, get, get.apply(bound), "parameterName", ""))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) > 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireGreaterThan(value, get, get.apply(bound), "parameterName", " "))));

        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) < 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireLessThan(value, get, get.apply(bound), "parameterName", " "))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) <= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireLessThanOrEqual(value, get, get.apply(bound), "parameterName", " "))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) == 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireEqual(value, get, get.apply(bound), "parameterName", " "))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) >= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireGreaterThanOrEqual(value, get, get.apply(bound), "parameterName", " "))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) > 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireGreaterThan(value, get, get.apply(bound), "parameterName", " "))));

        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) < 0).forEach(value -> assertEquals(value, requireLessThan(value, get, get.apply(bound), "parameterName", "fieldName"))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) <= 0).forEach(value -> assertEquals(value, requireLessThanOrEqual(value, get, get.apply(bound), "parameterName", "fieldName"))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) == 0).forEach(value -> assertEquals(value, requireEqual(value, get, get.apply(bound), "parameterName", "fieldName"))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) >= 0).forEach(value -> assertEquals(value, requireGreaterThanOrEqual(value, get, get.apply(bound), "parameterName", "fieldName"))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) > 0).forEach(value -> assertEquals(value, requireGreaterThan(value, get, get.apply(bound), "parameterName", "fieldName"))));

        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) >= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireLessThan(value, get, get.apply(bound), "parameterName", "fieldName"))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) > 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireLessThanOrEqual(value, get, get.apply(bound), "parameterName", "fieldName"))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) != 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireEqual(value, get, get.apply(bound), "parameterName", "fieldName"))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) < 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireGreaterThanOrEqual(value, get, get.apply(bound), "parameterName", "fieldName"))));
        list.stream().forEach(bound -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(bound)) <= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireGreaterThan(value, get, get.apply(bound), "parameterName", "fieldName"))));

        list.stream().forEach(boundMinimum -> list.stream().forEach(boundMaximum -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(boundMinimum)) <= 0 || get.apply(value).compareTo(get.apply(boundMaximum)) >= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireBoundExclusive(value, get, get.apply(boundMinimum), get.apply(boundMaximum), "parameterName", "fieldName")))));
        list.stream().forEach(boundMinimum -> list.stream().forEach(boundMaximum -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(boundMinimum)) < 0 || get.apply(value).compareTo(get.apply(boundMaximum)) > 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireBoundInclusive(value, get, get.apply(boundMinimum), get.apply(boundMaximum), "parameterName", "fieldName")))));
        list.stream().forEach(boundMinimum -> list.stream().forEach(boundMaximum -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(boundMinimum)) <= 0 || get.apply(value).compareTo(get.apply(boundMaximum)) > 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireBoundMinimumExclusiveMaximumInclusive(value, get, get.apply(boundMinimum), get.apply(boundMaximum), "parameterName", "fieldName")))));
        list.stream().forEach(boundMinimum -> list.stream().forEach(boundMaximum -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(boundMinimum)) < 0 || get.apply(value).compareTo(get.apply(boundMaximum)) >= 0).forEach(value -> assertThrows(IllegalArgumentException.class, () -> requireBoundMinimumInclusiveMaximumExclusive(value, get, get.apply(boundMinimum), get.apply(boundMaximum), "parameterName", "fieldName")))));

        list.stream().forEach(boundMinimum -> list.stream().forEach(boundMaximum -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(boundMinimum)) > 0 && get.apply(value).compareTo(get.apply(boundMaximum)) < 0).forEach(value -> assertEquals(value, requireBoundExclusive(value, get, get.apply(boundMinimum), get.apply(boundMaximum), "parameterName", "fieldName")))));
        list.stream().forEach(boundMinimum -> list.stream().forEach(boundMaximum -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(boundMinimum)) >= 0 && get.apply(value).compareTo(get.apply(boundMaximum)) <= 0).forEach(value -> assertEquals(value, requireBoundInclusive(value, get, get.apply(boundMinimum), get.apply(boundMaximum), "parameterName", "fieldName")))));
        list.stream().forEach(boundMinimum -> list.stream().forEach(boundMaximum -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(boundMinimum)) > 0 && get.apply(value).compareTo(get.apply(boundMaximum)) <= 0).forEach(value -> assertEquals(value, requireBoundMinimumExclusiveMaximumInclusive(value, get, get.apply(boundMinimum), get.apply(boundMaximum), "parameterName", "fieldName")))));
        list.stream().forEach(boundMinimum -> list.stream().forEach(boundMaximum -> list.stream().filter(value -> get.apply(value).compareTo(get.apply(boundMinimum)) >= 0 && get.apply(value).compareTo(get.apply(boundMaximum)) < 0).forEach(value -> assertEquals(value, requireBoundMinimumInclusiveMaximumExclusive(value, get, get.apply(boundMinimum), get.apply(boundMaximum), "parameterName", "fieldName")))));
    }

    @Test
    void testRequireBound()
    {
        testRequireBoundHelper(iterate(-5L, l -> l + 1L).limit(11L).map(BigDecimal::valueOf).collect(toList()));
        testRequireBoundHelper(iterate(-5L, l -> l + 1L).limit(11L).map(BigInteger::valueOf).collect(toList()));
        testRequireBoundHelper(iterate((byte) -5, b -> (byte) (b + 1)).limit(11L).collect(toList()));
        testRequireBoundHelper(iterate(-5.0, d -> d + 1.0).limit(11L).collect(toList()));
        testRequireBoundHelper(iterate(-5.0f, f -> f + 1.0F).limit(11L).collect(toList()));
        testRequireBoundHelper(iterate(-5, i -> i + 1).limit(11L).collect(toList()));
        testRequireBoundHelper(iterate(-5L, l -> l + 1L).limit(11L).collect(toList()));
        testRequireBoundHelper(iterate((short) -5, sh -> (short) (sh + 1)).limit(11L).collect(toList()));
        testRequireBoundHelper(iterate((short) -5, sh -> (short) (sh + 1)).limit(11L).collect(toList()));
        testRequireBoundHelper(iterate((int) 'a', i -> i + 1).limit(11L).map(i -> (char) (int) i).collect(toList()));
        testRequireBoundHelper(iterate((int) 'a', i -> i + 1).limit(11L).map(i -> valueOf((char) (int) i)).collect(toList()));

        testRequireBoundHelper(iterate(-5L, l -> l + 1L).limit(11L).map(BigDecimal::valueOf).collect(toList()), identity());
        testRequireBoundHelper(iterate(-5L, l -> l + 1L).limit(11L).map(BigInteger::valueOf).collect(toList()), identity());
        testRequireBoundHelper(iterate((byte) -5, b -> (byte) (b + 1)).limit(11L).collect(toList()), identity());
        testRequireBoundHelper(iterate(-5.0, d -> d + 1.0).limit(11L).collect(toList()), identity());
        testRequireBoundHelper(iterate(-5.0f, f -> f + 1.0F).limit(11L).collect(toList()), identity());
        testRequireBoundHelper(iterate(-5, i -> i + 1).limit(11L).collect(toList()), identity());
        testRequireBoundHelper(iterate(-5L, l -> l + 1L).limit(11L).collect(toList()), identity());
        testRequireBoundHelper(iterate((short) -5, sh -> (short) (sh + 1)).limit(11L).collect(toList()), identity());
        testRequireBoundHelper(iterate((short) -5, sh -> (short) (sh + 1)).limit(11L).collect(toList()), identity());
        testRequireBoundHelper(iterate((int) 'a', i -> i + 1).limit(11L).map(i -> (char) (int) i).collect(toList()), identity());
        testRequireBoundHelper(iterate((int) 'a', i -> i + 1).limit(11L).map(i -> valueOf((char) (int) i)).collect(toList()), identity());
    }
}
