package io.github.drewctaylor.require;

/**
 * Require that a Double is positive, zero, or negative; require that a string represent a Double.
 */
public final class RequireNumberDouble
{
    private RequireNumberDouble()
    {
    }

    private static final RequireNumberHelper<Double> requireNumberHelper = new RequireNumberHelper<>(0.0d, Double::parseDouble, "java.lang.Double");

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
    public static Double requirePositive(
            final Double value,
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
    public static Double requireZeroOrPositive(
            final Double value,
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
    public static Double requireZero(
            final Double value,
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
    public static Double requireZeroOrNegative(
            final Double value,
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
    public static Double requireNegative(
            final Double value,
            final String name)
    {
        return requireNumberHelper.requireNegative(value, name);
    }

    /**
     * Return the given value, as a Double, if it represents a Double; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  name                     the name of the given value
     * @return                          the given value as a Double, if it represents a Double
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value does not represent a Double
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is blank
     */
    public static Double requireDouble(
            final String value,
            final String name)
    {
        return requireNumberHelper.requireNumber(value, name);
    }
}
