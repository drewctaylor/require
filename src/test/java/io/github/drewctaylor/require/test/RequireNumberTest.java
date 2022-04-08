package io.github.drewctaylor.require.test;

import io.github.drewctaylor.require.RequireNumberBigDecimal;
import io.github.drewctaylor.require.RequireNumberBigInteger;
import io.github.drewctaylor.require.RequireNumberByte;
import io.github.drewctaylor.require.RequireNumberDouble;
import io.github.drewctaylor.require.RequireNumberFloat;
import io.github.drewctaylor.require.RequireNumberInteger;
import io.github.drewctaylor.require.RequireNumberLong;
import io.github.drewctaylor.require.RequireNumberShort;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import static java.lang.String.valueOf;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.iterate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class RequireNumberTest
{
    private static <TYPE extends Comparable<TYPE>> void testRequireNumber(
            final List<TYPE> negativeList,
            final List<TYPE> positiveList,
            final TYPE zero,
            final Function<TYPE, String> string,
            final List<String> nonNumericStringList,
            final BiFunction<TYPE, String, TYPE> requireNegative,
            final BiFunction<TYPE, String, TYPE> requirePositive,
            final BiFunction<TYPE, String, TYPE> requireZero,
            final BiFunction<TYPE, String, TYPE> requireZeroOrNegative,
            final BiFunction<TYPE, String, TYPE> requireZeroOrPositive,
            final BiFunction<String, String, TYPE> require)
    {
        assertThrows(NullPointerException.class, () -> requireNegative.apply(null, "name"));
        assertThrows(NullPointerException.class, () -> requirePositive.apply(null, "name"));
        assertThrows(NullPointerException.class, () -> requireZero.apply(null, "name"));
        assertThrows(NullPointerException.class, () -> requireZeroOrNegative.apply(null, "name"));
        assertThrows(NullPointerException.class, () -> requireZeroOrPositive.apply(null, "name"));
        assertThrows(NullPointerException.class, () -> require.apply(null, "name"));

        assertThrows(NullPointerException.class, () -> requireNegative.apply(zero, null));
        assertThrows(NullPointerException.class, () -> requirePositive.apply(zero, null));
        assertThrows(NullPointerException.class, () -> requireZero.apply(zero, null));
        assertThrows(NullPointerException.class, () -> requireZeroOrNegative.apply(zero, null));
        assertThrows(NullPointerException.class, () -> requireZeroOrPositive.apply(zero, null));
        assertThrows(NullPointerException.class, () -> require.apply(string.apply(zero), null));

        assertThrows(IllegalArgumentException.class, () -> requireNegative.apply(zero, ""));
        assertThrows(IllegalArgumentException.class, () -> requirePositive.apply(zero, ""));
        assertThrows(IllegalArgumentException.class, () -> requireZero.apply(zero, ""));
        assertThrows(IllegalArgumentException.class, () -> requireZeroOrNegative.apply(zero, ""));
        assertThrows(IllegalArgumentException.class, () -> requireZeroOrPositive.apply(zero, ""));
        assertThrows(IllegalArgumentException.class, () -> require.apply(string.apply(zero), ""));

        assertThrows(IllegalArgumentException.class, () -> requireNegative.apply(zero, " "));
        assertThrows(IllegalArgumentException.class, () -> requirePositive.apply(zero, " "));
        assertThrows(IllegalArgumentException.class, () -> requireZero.apply(zero, " "));
        assertThrows(IllegalArgumentException.class, () -> requireZeroOrNegative.apply(zero, " "));
        assertThrows(IllegalArgumentException.class, () -> requireZeroOrPositive.apply(zero, " "));
        assertThrows(IllegalArgumentException.class, () -> require.apply(string.apply(zero), " "));

        negativeList.stream().forEach(negative -> assertEquals(negative, requireNegative.apply(negative, "name")));
        negativeList.stream().forEach(negative -> assertThrows(IllegalArgumentException.class, () -> requirePositive.apply(negative, "name")));
        negativeList.stream().forEach(negative -> assertThrows(IllegalArgumentException.class, () -> requireZero.apply(negative, "name")));
        negativeList.stream().forEach(negative -> assertEquals(negative, requireZeroOrNegative.apply(negative, "name")));
        negativeList.stream().forEach(negative -> assertThrows(IllegalArgumentException.class, () -> requireZeroOrPositive.apply(negative, "name")));
        negativeList.stream().forEach(negative -> assertEquals(negative, require.apply(string.apply(negative), "name")));

        positiveList.stream().forEach(positive -> assertThrows(IllegalArgumentException.class, () -> requireNegative.apply(positive, "name")));
        positiveList.stream().forEach(positive -> assertEquals(positive, requirePositive.apply(positive, "name")));
        positiveList.stream().forEach(positive -> assertThrows(IllegalArgumentException.class, () -> requireZero.apply(positive, "name")));
        positiveList.stream().forEach(positive -> assertThrows(IllegalArgumentException.class, () -> requireZeroOrNegative.apply(positive, "name")));
        positiveList.stream().forEach(positive -> assertEquals(positive, requireZeroOrPositive.apply(positive, "name")));
        positiveList.stream().forEach(positive -> assertEquals(positive, require.apply(string.apply(positive), "name")));

        assertThrows(IllegalArgumentException.class, () -> requireNegative.apply(zero, "name"));
        assertThrows(IllegalArgumentException.class, () -> requirePositive.apply(zero, "name"));
        assertEquals(zero, requireZero.apply(zero, "name"));
        assertEquals(zero, requireZeroOrNegative.apply(zero, "name"));
        assertEquals(zero, requireZeroOrPositive.apply(zero, "name"));
        assertEquals(zero, require.apply(string.apply(zero), "name"));

        nonNumericStringList.stream().forEach(nonNumericString -> assertThrows(NullPointerException.class, () -> require.apply(nonNumericString, null)));
        nonNumericStringList.stream().forEach(nonNumericString -> assertThrows(IllegalArgumentException.class, () -> require.apply(nonNumericString, "")));
        nonNumericStringList.stream().forEach(nonNumericString -> assertThrows(IllegalArgumentException.class, () -> require.apply(nonNumericString, " ")));
        nonNumericStringList.stream().forEach(nonNumericString -> assertThrows(IllegalArgumentException.class, () -> require.apply(nonNumericString, "name")));
    }

    @Test
    void testRequireBigDecimal()
    {
        testRequireNumber(
                iterate(-1L, l -> l - 1L).limit(5L).map(BigDecimal::valueOf).collect(toList()),
                iterate(1L, l -> l + 1L).limit(5L).map(BigDecimal::valueOf).collect(toList()),
                BigDecimal.ZERO,
                BigDecimal::toString,
                iterate((int) 'a', i -> i + 1).limit(5L).map(i -> valueOf((char) (int) i)).collect(toList()),
                RequireNumberBigDecimal::requireNegative,
                RequireNumberBigDecimal::requirePositive,
                RequireNumberBigDecimal::requireZero,
                RequireNumberBigDecimal::requireZeroOrNegative,
                RequireNumberBigDecimal::requireZeroOrPositive,
                RequireNumberBigDecimal::requireBigDecimal);
    }

    @Test
    void testRequireBigInteger()
    {
        testRequireNumber(
                iterate(-1L, l -> l - 1L).limit(5L).map(BigInteger::valueOf).collect(toList()),
                iterate(1L, l -> l + 1L).limit(5L).map(BigInteger::valueOf).collect(toList()),
                BigInteger.ZERO,
                BigInteger::toString,
                iterate((int) 'a', i -> i + 1).limit(5L).map(i -> valueOf((char) (int) i)).collect(toList()),
                RequireNumberBigInteger::requireNegative,
                RequireNumberBigInteger::requirePositive,
                RequireNumberBigInteger::requireZero,
                RequireNumberBigInteger::requireZeroOrNegative,
                RequireNumberBigInteger::requireZeroOrPositive,
                RequireNumberBigInteger::requireBigInteger);
    }

    @Test
    void testRequireByte()
    {
        testRequireNumber(
                iterate((byte) -1, b -> (byte) (b - 1)).limit(5L).collect(toList()),
                iterate((byte) 1, b -> (byte) (b + 1)).limit(5L).collect(toList()),
                (byte) 0,
                Object::toString,
                iterate((int) 'a', i -> i + 1).limit(5L).map(i -> valueOf((char) (int) i)).collect(toList()),
                RequireNumberByte::requireNegative,
                RequireNumberByte::requirePositive,
                RequireNumberByte::requireZero,
                RequireNumberByte::requireZeroOrNegative,
                RequireNumberByte::requireZeroOrPositive,
                RequireNumberByte::requireByte);
    }

    @Test
    void testRequireDouble()
    {
        testRequireNumber(
                iterate(-1.0, d -> d - 1.0).limit(5L).collect(toList()),
                iterate(1.0, d -> d + 1.0).limit(5L).collect(toList()),
                0.0,
                Object::toString,
                iterate((int) 'a', i -> i + 1).limit(5L).map(i -> valueOf((char) (int) i)).collect(toList()),
                RequireNumberDouble::requireNegative,
                RequireNumberDouble::requirePositive,
                RequireNumberDouble::requireZero,
                RequireNumberDouble::requireZeroOrNegative,
                RequireNumberDouble::requireZeroOrPositive,
                RequireNumberDouble::requireDouble);
    }

    @Test
    void testRequireFloat()
    {
        testRequireNumber(
                iterate(-1.0f, f -> f - 1.0F).limit(5L).collect(toList()),
                iterate(1.0f, f -> f + 1.0F).limit(5L).collect(toList()),
                0.0f,
                Object::toString,
                iterate((int) 'a', i -> i + 1).limit(5L).map(i -> valueOf((char) (int) i)).collect(toList()),
                RequireNumberFloat::requireNegative,
                RequireNumberFloat::requirePositive,
                RequireNumberFloat::requireZero,
                RequireNumberFloat::requireZeroOrNegative,
                RequireNumberFloat::requireZeroOrPositive,
                RequireNumberFloat::requireFloat);
    }

    @Test
    void testRequireInt()
    {
        testRequireNumber(
                iterate(-1, i -> i - 1).limit(5L).collect(toList()),
                iterate(1, i -> i + 1).limit(5L).collect(toList()),
                0,
                Object::toString,
                iterate((int) 'a', i -> i + 1).limit(5L).map(i -> valueOf((char) (int) i)).collect(toList()),
                RequireNumberInteger::requireNegative,
                RequireNumberInteger::requirePositive,
                RequireNumberInteger::requireZero,
                RequireNumberInteger::requireZeroOrNegative,
                RequireNumberInteger::requireZeroOrPositive,
                RequireNumberInteger::requireInteger);
    }

    @Test
    void testRequireLong()
    {
        testRequireNumber(
                iterate(-1L, l -> l - 1L).limit(5L).collect(toList()),
                iterate(1L, l -> l + 1L).limit(5L).collect(toList()),
                0L,
                Object::toString,
                iterate((int) 'a', i -> i + 1).limit(5L).map(i -> valueOf((char) (int) i)).collect(toList()),
                RequireNumberLong::requireNegative,
                RequireNumberLong::requirePositive,
                RequireNumberLong::requireZero,
                RequireNumberLong::requireZeroOrNegative,
                RequireNumberLong::requireZeroOrPositive,
                RequireNumberLong::requireLong);
    }

    @Test
    void testRequireShort()
    {
        testRequireNumber(
                iterate((short) -1, sh -> (short) (sh - 1)).limit(5L).collect(toList()),
                iterate((short) 1, sh -> (short) (sh + 1)).limit(5L).collect(toList()),
                (short) 0,
                Object::toString,
                iterate((int) 'a', i -> i + 1).limit(5L).map(i -> valueOf((char) (int) i)).collect(toList()),
                RequireNumberShort::requireNegative,
                RequireNumberShort::requirePositive,
                RequireNumberShort::requireZero,
                RequireNumberShort::requireZeroOrNegative,
                RequireNumberShort::requireZeroOrPositive,
                RequireNumberShort::requireShort);
    }
}
