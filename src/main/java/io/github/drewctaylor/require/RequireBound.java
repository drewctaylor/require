package io.github.drewctaylor.require;

import java.util.function.Function;

import static io.github.drewctaylor.require.Require.require;
import static java.lang.String.format;

public final class RequireBound
{
    private RequireBound()
    {
    }

    public static <TYPE extends Comparable<TYPE>> TYPE requireLessThan(
            final TYPE value,
            final TYPE maximum,
            final String name)
    {
        return require(value.compareTo(maximum) < 0, value, format("%s must be less than '%s'; it is '%s'.", name, maximum, value));
    }

    public static <TYPE extends Comparable<TYPE>> TYPE requireLessThanOrEqual(
            final TYPE value,
            final TYPE maximum,
            final String name)
    {
        return require(value.compareTo(maximum) <= 0, value, format("%s must be less than or equal to '%s'; it is '%s'.", name, maximum, value));
    }

    public static <TYPE extends Comparable<TYPE>> TYPE requireEqual(
            final TYPE value,
            final TYPE target,
            final String name)
    {
        return require(value.compareTo(target) == 0, value, format("%s must be equal to '%s'; it is '%s'.", name, target, value));
    }

    public static <TYPE extends Comparable<TYPE>> TYPE requireGreaterThanOrEqual(
            final TYPE value,
            final TYPE minimum,
            final String name)
    {
        return require(value.compareTo(minimum) >= 0, value, format("%s must be greater than or equal to '%s'; it is '%s'.", name, minimum, value));
    }

    public static <TYPE extends Comparable<TYPE>> TYPE requireGreaterThan(
            final TYPE value,
            final TYPE minimum,
            final String name)
    {
        return require(value.compareTo(minimum) > 0, value, format("%s must be greater than '%s'; it is '%s'.", name, minimum, value));
    }

    public static <TYPE extends Comparable<TYPE>> TYPE requireBoundExclusive(
            final TYPE value,
            final TYPE minimumExclusive,
            final TYPE maximumExclusive,
            final String name)
    {
        return require(value.compareTo(maximumExclusive) < 0 && value.compareTo(minimumExclusive) > 0, value, format("%s must be greater than '%s' and less than '%s'; it is '%s'.", name, minimumExclusive, maximumExclusive, value));
    }

    public static <TYPE extends Comparable<TYPE>> TYPE requireBoundInclusive(
            final TYPE value,
            final TYPE minimumInclusive,
            final TYPE maximumInclusive,
            final String name)
    {
        return require(value.compareTo(maximumInclusive) <= 0 && value.compareTo(minimumInclusive) >= 0, value, format("%s must be greater than or equal to '%s' and less than or equal to '%s'; it is '%s'.", name, minimumInclusive, maximumInclusive, value));
    }

    public static <TYPE extends Comparable<TYPE>> TYPE requireBoundMinimumExclusiveMaximumInclusive(
            final TYPE value,
            final TYPE minimumExclusive,
            final TYPE maximumInclusive,
            final String name)
    {
        return require(value.compareTo(maximumInclusive) <= 0 && value.compareTo(minimumExclusive) > 0, value, format("%s must be greater than '%s' and less than or equal to '%s'; it is '%s'.", name, minimumExclusive, maximumInclusive, value));
    }

    public static <TYPE extends Comparable<TYPE>> TYPE requireBoundMinimumInclusiveMaximumExclusive(
            final TYPE value,
            final TYPE minimumInclusive,
            final TYPE maximumExclusive,
            final String name)
    {
        return require(value.compareTo(maximumExclusive) < 0 && value.compareTo(minimumInclusive) >= 0, value, format("%s must be greater than or equal to '%s' and less than '%s'; it is '%s'.", name, minimumInclusive, maximumExclusive, value));
    }

    public static <T1, T2 extends Comparable<T2>> T1 requireLessThan(
            final T1 value,
            final Function<T1, T2> get,
            final T2 maximum,
            final String parameterName,
            final String fieldName)
    {
        return require(get.apply(value).compareTo(maximum) < 0, value, format("%s %s must be less than '%s'; it is '%s'.", parameterName, fieldName, maximum, get.apply(value)));
    }

    public static <T1, T2 extends Comparable<T2>> T1 requireLessThanOrEqual(
            final T1 value,
            final Function<T1, T2> get,
            final T2 maximum,
            final String parameterName,
            final String fieldName)
    {
        return require(get.apply(value).compareTo(maximum) <= 0, value, format("%s %s must be less than or equal to '%s'; it is '%s'.", parameterName, fieldName, maximum, get.apply(value)));
    }

    public static <T1, T2 extends Comparable<T2>> T1 requireEqual(
            final T1 value,
            final Function<T1, T2> get,
            final T2 target,
            final String parameterName,
            final String fieldName)
    {
        return require(get.apply(value).compareTo(target) == 0, value, format("%s %s must be equal to '%s'; it is '%s'.", parameterName, fieldName, target, get.apply(value)));
    }

    public static <T1, T2 extends Comparable<T2>> T1 requireGreaterThanOrEqual(
            final T1 value,
            final Function<T1, T2> get,
            final T2 minimum,
            final String parameterName,
            final String fieldName)
    {
        return require(get.apply(value).compareTo(minimum) >= 0, value, format("%s %s must be greater than or equal to '%s'; it is '%s'.", parameterName, fieldName, minimum, get.apply(value)));
    }

    public static <T1, T2 extends Comparable<T2>> T1 requireGreaterThan(
            final T1 value,
            final Function<T1, T2> get,
            final T2 minimum,
            final String parameterName,
            final String fieldName)
    {
        return require(get.apply(value).compareTo(minimum) > 0, value, format("%s %s must be greater than '%s'; it is '%s'.", parameterName, fieldName, minimum, get.apply(value)));
    }

    public static <T1, T2 extends Comparable<T2>> T1 requireBoundExclusive(
            final T1 value,
            final Function<T1, T2> get,
            final T2 minimumExclusive,
            final T2 maximumExclusive,
            final String parameterName,
            final String fieldName)
    {
        return require(get.apply(value).compareTo(maximumExclusive) < 0 && get.apply(value).compareTo(minimumExclusive) > 0, value, format("%s %s must be greater than '%s' and less than '%s'; it is '%s'.", parameterName, fieldName, minimumExclusive, maximumExclusive, get.apply(value)));
    }

    public static <T1, T2 extends Comparable<T2>> T1 requireBoundInclusive(
            final T1 value,
            final Function<T1, T2> get,
            final T2 minimumInclusive,
            final T2 maximumInclusive,
            final String parameterName,
            final String fieldName)
    {
        return require(get.apply(value).compareTo(maximumInclusive) <= 0 && get.apply(value).compareTo(minimumInclusive) >= 0, value, format("%s %s must be greater than or equal to '%s' and less than or equal to '%s'; it is '%s'.", parameterName, fieldName, minimumInclusive, maximumInclusive, get.apply(value)));
    }

    public static <T1, T2 extends Comparable<T2>> T1 requireBoundMinimumExclusiveMaximumInclusive(
            final T1 value,
            final Function<T1, T2> get,
            final T2 minimumExclusive,
            final T2 maximumInclusive,
            final String parameterName,
            final String fieldName)
    {
        return require(get.apply(value).compareTo(maximumInclusive) <= 0 && get.apply(value).compareTo(minimumExclusive) > 0, value, format("%s %s must be greater than '%s' and less than or equal to '%s'; it is '%s'.", parameterName, fieldName, minimumExclusive, maximumInclusive, get.apply(value)));
    }

    public static <T1, T2 extends Comparable<T2>> T1 requireBoundMinimumInclusiveMaximumExclusive(
            final T1 value,
            final Function<T1, T2> get,
            final T2 minimumInclusive,
            final T2 maximumExclusive,
            final String parameterName,
            final String fieldName)
    {
        return require(get.apply(value).compareTo(maximumExclusive) < 0 && get.apply(value).compareTo(minimumInclusive) >= 0, value, format("%s %s must be greater than or equal to '%s' and less than '%s'; it is '%s'.", parameterName, fieldName, minimumInclusive, maximumExclusive, get.apply(value)));
    }
}
