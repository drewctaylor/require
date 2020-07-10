package io.github.drewctaylor.require;

import java.math.BigDecimal;

/**
 * Require that a BigDecimal is positive, zero, or negative; require that a string represent a BigDecimal.
 */
public final class RequireNumberBigDecimal
{
    private RequireNumberBigDecimal()
    {
    }

    private static final RequireNumberHelper<BigDecimal> requireNumberHelper = new RequireNumberHelper<>(BigDecimal.ZERO, BigDecimal::new, "java.math.BigDecimal");

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
    public static BigDecimal requirePositive(
            final BigDecimal value,
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
    public static BigDecimal requireZeroOrPositive(
            final BigDecimal value,
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
    public static BigDecimal requireZero(
            final BigDecimal value,
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
    public static BigDecimal requireZeroOrNegative(
            final BigDecimal value,
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
    public static BigDecimal requireNegative(
            final BigDecimal value,
            final String name)
    {
        return requireNumberHelper.requireNegative(value, name);
    }

    /**
     * Return the given value as a BigDecimal, if it represents a BigDecimal; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  name                     the name of the given value
     * @return                          the given value as a BigDecimal, if it represents a BigDecimal
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value does not represent a BigDecimal
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is blank
     */
    public static BigDecimal requireBigDecimal(
            final String value,
            final String name)
    {
        return requireNumberHelper.requireNumber(value, name);
    }
}
