package io.github.drewctaylor.require;

/**
 * Require that a Integer is positive, zero, or negative; require that a string represent a Integer.
 */
public final class RequireNumberInteger
{
    private RequireNumberInteger()
    {
    }

    private static final RequireNumberHelper<Integer> requireNumberHelper = new RequireNumberHelper<>(0, Integer::parseInt, "java.lang.Integer");

    /**
     * Return the given value, if positive; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  name                     the name of the given value
     * @return                          the given value if positive
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value is not positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is blank
     */
    public static Integer requirePositive(
            final Integer value,
            final String name)
    {
        return requireNumberHelper.requirePositive(value, name);
    }

    /**
     * Return the given value, if zero or positive; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  name                     the name of the given value
     * @return                          the given value if zero or positive
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value is not zero or not positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is blank
     */
    public static Integer requireZeroOrPositive(
            final Integer value,
            final String name)
    {
        return requireNumberHelper.requireZeroOrPositive(value, name);
    }

    /**
     * Return the given value, if zero; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  name                     the name of the given value
     * @return                          the given value if zero
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value is not zero
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is blank
     */
    public static Integer requireZero(
            final Integer value,
            final String name)
    {
        return requireNumberHelper.requireZero(value, name);
    }

    /**
     * Return the given value, if zero or negative; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  name                     the name of the given value
     * @return                          the given value if zero or negative
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value is not zero or not negative
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is blank
     */
    public static Integer requireZeroOrNegative(
            final Integer value,
            final String name)
    {
        return requireNumberHelper.requireZeroOrNegative(value, name);
    }

    /**
     * Return the given value, if negative; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  name                     the name of the given value
     * @return                          the given value if negative
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value is not negative
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is blank
     */
    public static Integer requireNegative(
            final Integer value,
            final String name)
    {
        return requireNumberHelper.requireNegative(value, name);
    }

    /**
     * Return the given value as a Integer, if it represents a Integer; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  name                     the name of the given value
     * @return                          the given value as a Integer, if it represents a Integer
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value does not represent a Integer
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is blank
     */
    public static Integer requireInteger(
            final String value,
            final String name)
    {
        return requireNumberHelper.requireNumber(value, name);
    }
}
