package io.github.drewctaylor.require;

/**
 * Require that a Long is positive, zero, or negative; require that a string represent a Long.
 */
public final class RequireNumberLong
{
    private RequireNumberLong()
    {
    }

    private static final RequireNumberHelper<Long> requireNumberHelper = new RequireNumberHelper<>(0L, Long::parseLong, Long.class);

    /**
     * Return the given value, if positive; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  name                     the name of the given value
     * 
     * @return                          the given value if positive
     * 
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value is not positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is blank
     */
    public static Long requirePositive(
            final Long value,
            final String name)
    {
        return requireNumberHelper.requirePositive(value, name);
    }

    /**
     * Return the given value, if zero or positive; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  name                     the name of the given value
     * 
     * @return                          the given value if zero or positive
     * 
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value is not zero or not positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is blank
     */
    public static Long requireZeroOrPositive(
            final Long value,
            final String name)
    {
        return requireNumberHelper.requireZeroOrPositive(value, name);
    }

    /**
     * Return the given value, if zero; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  name                     the name of the given value
     * 
     * @return                          the given value if zero
     * 
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value is not zero
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is blank
     */
    public static Long requireZero(
            final Long value,
            final String name)
    {
        return requireNumberHelper.requireZero(value, name);
    }

    /**
     * Return the given value, if zero or negative; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  name                     the name of the given value
     * 
     * @return                          the given value if zero or negative
     * 
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value is not zero or not negative
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is blank
     */
    public static Long requireZeroOrNegative(
            final Long value,
            final String name)
    {
        return requireNumberHelper.requireZeroOrNegative(value, name);
    }

    /**
     * Return the given value, if negative; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  name                     the name of the given value
     * 
     * @return                          the given value if negative
     * 
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value is not negative
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is blank
     */
    public static Long requireNegative(
            final Long value,
            final String name)
    {
        return requireNumberHelper.requireNegative(value, name);
    }

    /**
     * Return the given value as a Long, if it represents a Long; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  name                     the name of the given value
     * 
     * @return                          the given value as a Long, if it represents a Long
     * 
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value does not represent a Long
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is blank
     */
    public static Long requireLong(
            final String value,
            final String name)
    {
        return requireNumberHelper.requireNumber(value, name);
    }
}
