package io.github.drewctaylor.require;

import static io.github.drewctaylor.require.RequireString.requireName;
import static io.github.drewctaylor.require.RequireString.requireNonBlank;
import static java.lang.String.format;

/**
 * Require that a value is null or non-null; require that an expression is true.
 */
public final class Require
{
    private Require()
    {
    }

    private static <TYPE, EXCEPTION extends RuntimeException> TYPE requireHelper(
            final boolean expression,
            final TYPE value,
            final EXCEPTION runtimeException)
    {
        if (!expression)
        {
            throw runtimeException;
        }

        return value;
    }

    static <TYPE> TYPE requireHelper(
            final boolean expression,
            final TYPE value,
            final String message)
    {
        return require(expression, value, new IllegalArgumentException(message));
    }

    static <TYPE> TYPE requireNonNullHelper(
            final TYPE value,
            final String name)
    {
        return requireHelper(value != null, value, new NullPointerException(format("%s must be non-null.", name)));
    }

    /**
     * Return the given value, if the given expression is true; otherwise, throw the given runtime exception.
     *
     * @param  expression           the given expression
     * @param  value                the given value
     * @param  runtimeException     the given runtime exception
     * @param  <TYPE>               the type of the given value
     * @param  <EXCEPTION>          the type of the given runtime exception
     * @return                      the given value, if the given expression is true
     * @throws EXCEPTION            if expression is false
     * @throws NullPointerException if exception is null
     */
    public static <TYPE, EXCEPTION extends RuntimeException> TYPE require(
            final boolean expression,
            final TYPE value,
            final EXCEPTION runtimeException)
    {
        requireNonNullHelper(runtimeException, "runtimeException");

        return requireHelper(expression, value, runtimeException);
    }

    /**
     * Return the given value, if the given expression is true; otherwise, throw an IllegalArgumentException with the given
     * message.
     *
     * @param  expression               the given expression
     * @param  value                    the given value
     * @param  message                  the given message
     * @param  <TYPE>                   the type of the given value
     * @return                          the given value, if the given expression is true
     * @throws IllegalArgumentException if expression is false
     * @throws NullPointerException     if message is null
     * @throws IllegalArgumentException if message is blank
     */
    public static <TYPE> TYPE require(
            final boolean expression,
            final TYPE value,
            final String message)
    {
        requireNonBlank(message, "message");

        return require(expression, value, new IllegalArgumentException(message));
    }

    /**
     * Return the given value, if non-null; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  name                     the name of the given value
     * @param  <TYPE>                   the type of the given value
     * @return                          the given value, if non-null
     * @throws IllegalArgumentException if value is null
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is blank
     */
    public static <TYPE> TYPE requireNonNull(
            final TYPE value,
            final String name)
    {
        requireName(name);

        return requireNonNullHelper(value, name);
    }

    /**
     * Return the given value, if null; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  name                     the name of the given value
     * @param  <TYPE>                   the type of the given value
     * @return                          the given value, if null
     * @throws IllegalArgumentException if value is not null
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is blank
     */
    public static <TYPE> TYPE requireNull(
            final TYPE value,
            final String name)
    {
        requireName(name);

        return require(value == null, value, format("%s must be null; it is '%s'.", name, value));
    }

}
