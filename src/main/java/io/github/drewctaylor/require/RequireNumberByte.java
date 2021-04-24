package io.github.drewctaylor.require;

/**
 * Require that a Byte is positive, zero, or negative; require that a string represent a Byte.
 */
public final class RequireNumberByte
{
    private RequireNumberByte()
    {
    }

    private static final RequireNumberHelper<Byte> requireNumberHelper = new RequireNumberHelper<>((byte) 0, Byte::parseByte, "java.lang.Byte");

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
    public static Byte requirePositive(
            final Byte value,
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
    public static Byte requireZeroOrPositive(
            final Byte value,
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
    public static Byte requireZero(
            final Byte value,
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
    public static Byte requireZeroOrNegative(
            final Byte value,
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
    public static Byte requireNegative(
            final Byte value,
            final String name)
    {
        return requireNumberHelper.requireNegative(value, name);
    }

    /**
     * Return the given value as a Byte, if it represents a Byte; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  name                     the name of the given value
     * 
     * @return                          the given value as a Byte, if it represents a Byte
     * 
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value does not represent a Byte
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is blank
     */
    public static Byte requireByte(
            final String value,
            final String name)
    {
        return requireNumberHelper.requireNumber(value, name);
    }
}
