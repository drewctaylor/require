package io.github.drewctaylor.require.number.test;

import io.github.drewctaylor.require.number.RequireBigDecimal;
import io.github.drewctaylor.require.number.RequireBigInteger;
import io.github.drewctaylor.require.number.RequireByte;
import io.github.drewctaylor.require.number.RequireDouble;
import io.github.drewctaylor.require.number.RequireFloat;
import io.github.drewctaylor.require.number.RequireInteger;
import io.github.drewctaylor.require.number.RequireLong;
import io.github.drewctaylor.require.number.RequireShort;
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

        nonNumericStringList.stream().forEach(nonNumericString -> assertThrows(IllegalArgumentException.class, () -> require.apply(nonNumericString, "name")));
    }

    @Test
    void testRequireBigDecimal()
    {
        testRequireNumber(
                iterate(-1L, l -> l - 1).limit(5).map(BigDecimal::valueOf).collect(toList()),
                iterate(1L, l -> l + 1).limit(5).map(BigDecimal::valueOf).collect(toList()),
                BigDecimal.ZERO,
                BigDecimal::toString,
                iterate((int) 'a', i -> i + 1).limit(5).map(i -> valueOf((char) (int) i)).collect(toList()),
                RequireBigDecimal::requireNegative,
                RequireBigDecimal::requirePositive,
                RequireBigDecimal::requireZero,
                RequireBigDecimal::requireZeroOrNegative,
                RequireBigDecimal::requireZeroOrPositive,
                RequireBigDecimal::requireBigDecimal);
    }

    @Test
    void testRequireBigInteger()
    {
        testRequireNumber(
                iterate(-1L, l -> l - 1).limit(5).map(BigInteger::valueOf).collect(toList()),
                iterate(1L, l -> l + 1).limit(5).map(BigInteger::valueOf).collect(toList()),
                BigInteger.ZERO,
                BigInteger::toString,
                iterate((int) 'a', i -> i + 1).limit(5).map(i -> valueOf((char) (int) i)).collect(toList()),
                RequireBigInteger::requireNegative,
                RequireBigInteger::requirePositive,
                RequireBigInteger::requireZero,
                RequireBigInteger::requireZeroOrNegative,
                RequireBigInteger::requireZeroOrPositive,
                RequireBigInteger::requireBigInteger);
    }

    @Test
    void testRequireByte()
    {
        testRequireNumber(
                iterate((byte) -1, b -> (byte) (b - 1)).limit(5).collect(toList()),
                iterate((byte) 1, b -> (byte) (b + 1)).limit(5).collect(toList()),
                (byte) 0,
                Object::toString,
                iterate((int) 'a', i -> i + 1).limit(5).map(i -> valueOf((char) (int) i)).collect(toList()),
                RequireByte::requireNegative,
                RequireByte::requirePositive,
                RequireByte::requireZero,
                RequireByte::requireZeroOrNegative,
                RequireByte::requireZeroOrPositive,
                RequireByte::requireByte);
    }

    @Test
    void testRequireDouble()
    {
        testRequireNumber(
                iterate(-1.0, d -> d - 1).limit(5).collect(toList()),
                iterate(1.0, d -> d + 1).limit(5).collect(toList()),
                0.0,
                Object::toString,
                iterate((int) 'a', i -> i + 1).limit(5).map(i -> valueOf((char) (int) i)).collect(toList()),
                RequireDouble::requireNegative,
                RequireDouble::requirePositive,
                RequireDouble::requireZero,
                RequireDouble::requireZeroOrNegative,
                RequireDouble::requireZeroOrPositive,
                RequireDouble::requireDouble);
    }

    @Test
    void testRequireFloat()
    {
        testRequireNumber(
                iterate(-1.0f, f -> f - 1).limit(5).collect(toList()),
                iterate(1.0f, f -> f + 1).limit(5).collect(toList()),
                0.0f,
                Object::toString,
                iterate((int) 'a', i -> i + 1).limit(5).map(i -> valueOf((char) (int) i)).collect(toList()),
                RequireFloat::requireNegative,
                RequireFloat::requirePositive,
                RequireFloat::requireZero,
                RequireFloat::requireZeroOrNegative,
                RequireFloat::requireZeroOrPositive,
                RequireFloat::requireFloat);
    }

    @Test
    void testRequireInt()
    {
        testRequireNumber(
                iterate(-1, i -> i - 1).limit(5).collect(toList()),
                iterate(1, i -> i + 1).limit(5).collect(toList()),
                0,
                Object::toString,
                iterate((int) 'a', i -> i + 1).limit(5).map(i -> valueOf((char) (int) i)).collect(toList()),
                RequireInteger::requireNegative,
                RequireInteger::requirePositive,
                RequireInteger::requireZero,
                RequireInteger::requireZeroOrNegative,
                RequireInteger::requireZeroOrPositive,
                RequireInteger::requireInteger);
    }

    @Test
    void testRequireLong()
    {
        testRequireNumber(
                iterate(-1L, l -> l - 1).limit(5).collect(toList()),
                iterate(1L, l -> l + 1).limit(5).collect(toList()),
                0L,
                Object::toString,
                iterate((int) 'a', i -> i + 1).limit(5).map(i -> valueOf((char) (int) i)).collect(toList()),
                RequireLong::requireNegative,
                RequireLong::requirePositive,
                RequireLong::requireZero,
                RequireLong::requireZeroOrNegative,
                RequireLong::requireZeroOrPositive,
                RequireLong::requireLong);
    }

    @Test
    void testRequireShort()
    {
        testRequireNumber(
                iterate((short) -1, sh -> (short) (sh - 1)).limit(5).collect(toList()),
                iterate((short) 1, sh -> (short) (sh + 1)).limit(5).collect(toList()),
                (short) 0,
                Object::toString,
                iterate((int) 'a', i -> i + 1).limit(5).map(i -> valueOf((char) (int) i)).collect(toList()),
                RequireShort::requireNegative,
                RequireShort::requirePositive,
                RequireShort::requireZero,
                RequireShort::requireZeroOrNegative,
                RequireShort::requireZeroOrPositive,
                RequireShort::requireShort);
    }
}
