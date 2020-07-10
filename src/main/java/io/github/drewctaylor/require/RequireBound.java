package io.github.drewctaylor.require;

import java.util.function.Function;

import static io.github.drewctaylor.require.Require.require;
import static io.github.drewctaylor.require.Require.requireName;
import static io.github.drewctaylor.require.Require.requireNonNull;
import static java.lang.String.format;

/**
 * Require a comparable type to be within bounds.
 */
public final class RequireBound
{
    private RequireBound()
    {
    }

    /**
     * Return the given value, if less than the given maximum; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  maximum                  the given maximum
     * @param  name                     the name of the given value
     * @param  <TYPE>                   the type of the given value
     * @return                          the given value, if less than the given maximum
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value not less than the given maximum
     * @throws NullPointerException     if maximum is null
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is blank
     */
    public static <TYPE extends Comparable<TYPE>> TYPE requireLessThan(
            final TYPE value,
            final TYPE maximum,
            final String name)
    {
        requireNonNull(value, "value");
        requireNonNull(maximum, "maximum");
        requireName(name);

        return require(value.compareTo(maximum) < 0, value, format("%s must be less than '%s'; it is '%s'.", name, maximum, value));
    }

    /**
     * Return the given value, if less than or equal to the given maximum; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  maximum                  the given maximum
     * @param  name                     the name of the given value
     * @param  <TYPE>                   the type of the given value
     * @return                          the given value, if less than or equal to the given maximum
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value not less than or equal to the given maximum
     * @throws NullPointerException     if maximum is null
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is blank
     */
    public static <TYPE extends Comparable<TYPE>> TYPE requireLessThanOrEqual(
            final TYPE value,
            final TYPE maximum,
            final String name)
    {
        requireNonNull(value, "value");
        requireNonNull(maximum, "maximum");
        requireName(name);

        return require(value.compareTo(maximum) <= 0, value, format("%s must be less than or equal to '%s'; it is '%s'.", name, maximum, value));
    }

    /**
     * Return the given value, if the given target; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  target                   the given target
     * @param  name                     the name of the given value
     * @param  <TYPE>                   the type of the given value
     * @return                          the given value, if the given target
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value not the given target
     * @throws IllegalArgumentException if not zero or positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <TYPE extends Comparable<TYPE>> TYPE requireEqual(
            final TYPE value,
            final TYPE target,
            final String name)
    {
        requireNonNull(value, "value");
        requireNonNull(target, "target");
        requireName(name);

        return require(value.compareTo(target) == 0, value, format("%s must be equal to '%s'; it is '%s'.", name, target, value));
    }

    /**
     * Return the given value, if greater than or equal to the given minimum; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  minimum                  the given minimum
     * @param  name                     the name of the given value
     * @param  <TYPE>                   the type of the given value
     * @return                          the given value, if greater than or equal to the given minimum
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value not greater than or equal to the given minimum
     * @throws NullPointerException     if minimum is null
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <TYPE extends Comparable<TYPE>> TYPE requireGreaterThanOrEqual(
            final TYPE value,
            final TYPE minimum,
            final String name)
    {
        requireNonNull(value, "value");
        requireNonNull(minimum, "minimum");
        requireName(name);

        return require(value.compareTo(minimum) >= 0, value, format("%s must be greater than or equal to '%s'; it is '%s'.", name, minimum, value));
    }

    /**
     * Return the given value, if greater than the given minimum; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  minimum                  the given minimum
     * @param  name                     the name of the given value
     * @param  <TYPE>                   the type of the given value
     * @return                          the given value, if greater than the given minimum
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value not greater than the given minimum
     * @throws NullPointerException     if minimum is null
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <TYPE extends Comparable<TYPE>> TYPE requireGreaterThan(
            final TYPE value,
            final TYPE minimum,
            final String name)
    {
        requireNonNull(value, "value");
        requireNonNull(minimum, "minimum");
        requireName(name);

        return require(value.compareTo(minimum) > 0, value, format("%s must be greater than '%s'; it is '%s'.", name, minimum, value));
    }

    /**
     * Return the given value, if between the given minimum and the given maximum; otherwise, throw an
     * IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  minimum                  the given minimum
     * @param  maximum                  the given maximum
     * @param  name                     the name of the given value
     * @param  <TYPE>                   the type of the given value
     * @return                          the given value, if between the given minimum and the given maximum
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value not between the given minimum and the given maximum
     * @throws NullPointerException     if minimum is null
     * @throws NullPointerException     if maximum is null
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <TYPE extends Comparable<TYPE>> TYPE requireBoundInclusive(
            final TYPE value,
            final TYPE minimum,
            final TYPE maximum,
            final String name)
    {
        requireNonNull(value, "value");
        requireNonNull(minimum, "minimum");
        requireNonNull(maximum, "maximum");
        requireName(name);

        return require(value.compareTo(maximum) <= 0 && value.compareTo(minimum) >= 0, value, format("%s must be greater than or equal to '%s' and less than or equal to '%s'; it is '%s'.", name, minimum, maximum, value));
    }

    /**
     * Return the given value, if between the given minimum and the given maximum; otherwise, throw an
     * IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  minimum                  the given minimum
     * @param  maximum                  the given maximum
     * @param  name                     the name of the given value
     * @param  <TYPE>                   the type of the given value
     * @return                          the given value, if between the given minimum and the given maximum
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value not between the given minimum and the given maximum
     * @throws NullPointerException     if minimum is null
     * @throws NullPointerException     if maximum is null
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <TYPE extends Comparable<TYPE>> TYPE requireBoundExclusive(
            final TYPE value,
            final TYPE minimum,
            final TYPE maximum,
            final String name)
    {
        requireNonNull(value, "value");
        requireNonNull(minimum, "minimum");
        requireNonNull(maximum, "maximum");
        requireName(name);

        return require(value.compareTo(maximum) < 0 && value.compareTo(minimum) > 0, value, format("%s must be greater than '%s' and less than '%s'; it is '%s'.", name, minimum, maximum, value));
    }

    /**
     * Return the given value, if between the given minimum and the given maximum; otherwise, throw an
     * IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  minimum                  the given minimum
     * @param  maximum                  the given maximum
     * @param  name                     the name of the given value
     * @param  <TYPE>                   the type of the given value
     * @return                          the given value, if between the given minimum and the given maximum
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value not between the given minimum and the given maximum
     * @throws NullPointerException     if minimum is null
     * @throws NullPointerException     if maximum is null
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <TYPE extends Comparable<TYPE>> TYPE requireBoundMinimumExclusiveMaximumInclusive(
            final TYPE value,
            final TYPE minimum,
            final TYPE maximum,
            final String name)
    {
        requireNonNull(value, "value");
        requireNonNull(minimum, "minimum");
        requireNonNull(maximum, "maximum");
        requireName(name);

        return require(value.compareTo(maximum) <= 0 && value.compareTo(minimum) > 0, value, format("%s must be greater than '%s' and less than or equal to '%s'; it is '%s'.", name, minimum, maximum, value));
    }

    /**
     * Return the given value, if between the given minimum and the given maximum; otherwise, throw an
     * IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  minimum                  the given minimum
     * @param  maximum                  the given maximum
     * @param  name                     the name of the given value
     * @param  <TYPE>                   the type of the given value
     * @return                          the given value, if between the given minimum and the given maximum
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value not between the given minimum and the given maximum
     * @throws NullPointerException     if minimum is null
     * @throws NullPointerException     if maximum is null
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <TYPE extends Comparable<TYPE>> TYPE requireBoundMinimumInclusiveMaximumExclusive(
            final TYPE value,
            final TYPE minimum,
            final TYPE maximum,
            final String name)
    {
        requireNonNull(value, "value");
        requireNonNull(minimum, "minimum");
        requireNonNull(maximum, "maximum");
        requireName(name);

        return require(value.compareTo(maximum) < 0 && value.compareTo(minimum) >= 0, value, format("%s must be greater than or equal to '%s' and less than '%s'; it is '%s'.", name, minimum, maximum, value));
    }

    /**
     * Return the given value, if the derivative value is less than the given maximum; otherwise, throw an
     * IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  get                      the function for the derivative value
     * @param  maximum                  the given maximum
     * @param  parameterName            the name of the given value
     * @param  fieldName                the name of the derivative value
     * @param  <T1>                     the type of the given value
     * @param  <T2>                     the type of the derivative value
     * @return                          the given value, if the derivative value less than the given maximum
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value not less than the given maximum
     * @throws NullPointerException     if maximum is null
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <T1, T2 extends Comparable<T2>> T1 requireLessThan(
            final T1 value,
            final Function<T1, T2> get,
            final T2 maximum,
            final String parameterName,
            final String fieldName)
    {
        requireNonNull(value, "value");
        requireNonNull(get, "get");
        requireNonNull(maximum, "maximum");
        requireName(parameterName);
        requireName(fieldName);

        return require(get.apply(value).compareTo(maximum) < 0, value, format("%s %s must be less than '%s'; it is '%s'.", parameterName, fieldName, maximum, get.apply(value)));
    }

    /**
     * Return the given value, if the derivative value is less than or equal to the given maximum; otherwise, throw an
     * IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  get                      the function for the derivative value
     * @param  maximum                  the given maximum
     * @param  parameterName            the name of the given value
     * @param  fieldName                the name of the derivative value
     * @param  <T1>                     the type of the given value
     * @param  <T2>                     the type of the derivative value
     * @return                          the given value, if the derivative value is less than or equal to the given maximum
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value not less than or equal to the given maximum
     * @throws NullPointerException     if maximum is null
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <T1, T2 extends Comparable<T2>> T1 requireLessThanOrEqual(
            final T1 value,
            final Function<T1, T2> get,
            final T2 maximum,
            final String parameterName,
            final String fieldName)
    {
        requireNonNull(value, "value");
        requireNonNull(get, "get");
        requireNonNull(maximum, "maximum");
        requireName(parameterName);
        requireName(fieldName);

        return require(get.apply(value).compareTo(maximum) <= 0, value, format("%s %s must be less than or equal to '%s'; it is '%s'.", parameterName, fieldName, maximum, get.apply(value)));
    }

    /**
     * Return the given value, if the derivative value is the given target; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  get                      the function for the derivative value
     * @param  target                   the given target
     * @param  parameterName            the name of the given value
     * @param  fieldName                the name of the derivative value
     * @param  <T1>                     the type of the given value
     * @param  <T2>                     the type of the derivative value
     * @return                          the given value, if the derivative value is the given target
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value not the given target
     * @throws IllegalArgumentException if not zero or positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <T1, T2 extends Comparable<T2>> T1 requireEqual(
            final T1 value,
            final Function<T1, T2> get,
            final T2 target,
            final String parameterName,
            final String fieldName)
    {
        requireNonNull(value, "value");
        requireNonNull(get, "get");
        requireNonNull(target, "target");
        requireName(parameterName);
        requireName(fieldName);

        return require(get.apply(value).compareTo(target) == 0, value, format("%s %s must be equal to '%s'; it is '%s'.", parameterName, fieldName, target, get.apply(value)));
    }

    /**
     * Return the given value, if the derivative value is greater than or equal to the given minimum; otherwise, throw an
     * IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  get                      the function for the derivative value
     * @param  minimum                  the given minimum
     * @param  parameterName            the name of the given value
     * @param  fieldName                the name of the derivative value
     * @param  <T1>                     the type of the given value
     * @param  <T2>                     the type of the derivative value
     * @return                          the given value, if the derivative value is greater than or equal to the given
     *                                  minimum
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value not greater than or equal to the given minimum
     * @throws NullPointerException     if minimum is null
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <T1, T2 extends Comparable<T2>> T1 requireGreaterThanOrEqual(
            final T1 value,
            final Function<T1, T2> get,
            final T2 minimum,
            final String parameterName,
            final String fieldName)
    {
        requireNonNull(value, "value");
        requireNonNull(get, "get");
        requireNonNull(minimum, "minimum");
        requireName(parameterName);
        requireName(fieldName);

        return require(get.apply(value).compareTo(minimum) >= 0, value, format("%s %s must be greater than or equal to '%s'; it is '%s'.", parameterName, fieldName, minimum, get.apply(value)));
    }

    /**
     * Return the given value, if the derivative value is greater than the given minimum; otherwise, throw an
     * IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  get                      the function for the derivative value
     * @param  minimum                  the given minimum
     * @param  parameterName            the name of the given value
     * @param  fieldName                the name of the derivative value
     * @param  <T1>                     the type of the given value
     * @param  <T2>                     the type of the derivative value
     * @return                          the given value, if the derivative value is greater than the given minimum
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value not greater than the given minimum
     * @throws NullPointerException     if minimum is null
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <T1, T2 extends Comparable<T2>> T1 requireGreaterThan(
            final T1 value,
            final Function<T1, T2> get,
            final T2 minimum,
            final String parameterName,
            final String fieldName)
    {
        requireNonNull(value, "value");
        requireNonNull(get, "get");
        requireNonNull(minimum, "minimum");
        requireName(parameterName);
        requireName(fieldName);

        return require(get.apply(value).compareTo(minimum) > 0, value, format("%s %s must be greater than '%s'; it is '%s'.", parameterName, fieldName, minimum, get.apply(value)));
    }

    /**
     * Return the given value, if the derivative value is between the given minimum and the given maximum; otherwise, throw
     * an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  get                      the function for the derivative value
     * @param  minimum                  the given minimum
     * @param  maximum                  the given maximum
     * @param  parameterName            the name of the given value
     * @param  fieldName                the name of the derivative value
     * @param  <T1>                     the type of the given value
     * @param  <T2>                     the type of the derivative value
     * @return                          the given value, if the derivative value is between the given minimum and the given
     *                                  maximum
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value not between the given minimum and the given maximum
     * @throws NullPointerException     if minimum is null
     * @throws NullPointerException     if maximum is null
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <T1, T2 extends Comparable<T2>> T1 requireBoundInclusive(
            final T1 value,
            final Function<T1, T2> get,
            final T2 minimum,
            final T2 maximum,
            final String parameterName,
            final String fieldName)
    {
        requireNonNull(value, "value");
        requireNonNull(get, "get");
        requireNonNull(minimum, "minimum");
        requireNonNull(maximum, "maximum");
        requireName(parameterName);
        requireName(fieldName);

        return require(get.apply(value).compareTo(maximum) <= 0 && get.apply(value).compareTo(minimum) >= 0, value, format("%s %s must be greater than or equal to '%s' and less than or equal to '%s'; it is '%s'.", parameterName, fieldName, minimum, maximum, get.apply(value)));
    }

    /**
     * Return the given value, if the derivative value is between the given minimum and the given maximum; otherwise, throw
     * an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  get                      the function for the derivative value
     * @param  minimum                  the given minimum
     * @param  maximum                  the given maximum
     * @param  parameterName            the name of the given value
     * @param  fieldName                the name of the derivative value
     * @param  <T1>                     the type of the given value
     * @param  <T2>                     the type of the derivative value
     * @return                          the given value, if the derivative value is between the given minimum and the given
     *                                  maximum
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value not between the given minimum and the given maximum
     * @throws NullPointerException     if minimum is null
     * @throws NullPointerException     if maximum is null
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <T1, T2 extends Comparable<T2>> T1 requireBoundExclusive(
            final T1 value,
            final Function<T1, T2> get,
            final T2 minimum,
            final T2 maximum,
            final String parameterName,
            final String fieldName)
    {
        requireNonNull(value, "value");
        requireNonNull(get, "get");
        requireNonNull(minimum, "minimum");
        requireNonNull(maximum, "maximum");
        requireName(parameterName);
        requireName(fieldName);

        return require(get.apply(value).compareTo(maximum) < 0 && get.apply(value).compareTo(minimum) > 0, value, format("%s %s must be greater than '%s' and less than '%s'; it is '%s'.", parameterName, fieldName, minimum, maximum, get.apply(value)));
    }

    /**
     * Return the given value, if the derivative value is between the given minimum and the given maximum; otherwise, throw
     * an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  get                      the function for the derivative value
     * @param  minimum                  the given minimum
     * @param  maximum                  the given maximum
     * @param  parameterName            the name of the given value
     * @param  fieldName                the name of the derivative value
     * @param  <T1>                     the type of the given value
     * @param  <T2>                     the type of the derivative value
     * @return                          the given value, if the derivative value is between the given minimum and the given
     *                                  maximum
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value not between the given minimum and the given maximum
     * @throws NullPointerException     if minimum is null
     * @throws NullPointerException     if maximum is null
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <T1, T2 extends Comparable<T2>> T1 requireBoundMinimumExclusiveMaximumInclusive(
            final T1 value,
            final Function<T1, T2> get,
            final T2 minimum,
            final T2 maximum,
            final String parameterName,
            final String fieldName)
    {
        requireNonNull(value, "value");
        requireNonNull(get, "get");
        requireNonNull(minimum, "minimum");
        requireNonNull(maximum, "maximum");
        requireName(parameterName);
        requireName(fieldName);

        return require(get.apply(value).compareTo(maximum) <= 0 && get.apply(value).compareTo(minimum) > 0, value, format("%s %s must be greater than '%s' and less than or equal to '%s'; it is '%s'.", parameterName, fieldName, minimum, maximum, get.apply(value)));
    }

    /**
     * Return the given value, if the derivative value is between the given minimum and the given maximum; otherwise, throw
     * an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  get                      the function for the derivative value
     * @param  minimum                  the given minimum
     * @param  maximum                  the given maximum
     * @param  parameterName            the name of the given value
     * @param  fieldName                the name of the derivative value
     * @param  <T1>                     the type of the given value
     * @param  <T2>                     the type of the derivative value
     * @return                          the given value, if the derivative value is between the given minimum and the given
     *                                  maximum
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value not between the given minimum and the given maximum
     * @throws NullPointerException     if minimum is null
     * @throws NullPointerException     if maximum is null
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <T1, T2 extends Comparable<T2>> T1 requireBoundMinimumInclusiveMaximumExclusive(
            final T1 value,
            final Function<T1, T2> get,
            final T2 minimum,
            final T2 maximum,
            final String parameterName,
            final String fieldName)
    {
        requireNonNull(value, "value");
        requireNonNull(get, "get");
        requireNonNull(minimum, "minimum");
        requireNonNull(maximum, "maximum");
        requireName(parameterName);
        requireName(fieldName);

        return require(get.apply(value).compareTo(maximum) < 0 && get.apply(value).compareTo(minimum) >= 0, value, format("%s %s must be greater than or equal to '%s' and less than '%s'; it is '%s'.", parameterName, fieldName, minimum, maximum, get.apply(value)));
    }
}
