package io.github.drewctaylor.require;

import java.util.regex.Pattern;

import static io.github.drewctaylor.require.Require.require;
import static io.github.drewctaylor.require.Require.requireHelper;
import static io.github.drewctaylor.require.Require.requireNonNull;
import static io.github.drewctaylor.require.Require.requireNonNullHelper;
import static io.github.drewctaylor.require.RequireBound.requireBoundExclusive;
import static io.github.drewctaylor.require.RequireBound.requireBoundInclusive;
import static io.github.drewctaylor.require.RequireBound.requireBoundMinimumExclusiveMaximumInclusive;
import static io.github.drewctaylor.require.RequireBound.requireBoundMinimumInclusiveMaximumExclusive;
import static io.github.drewctaylor.require.RequireBound.requireEqual;
import static io.github.drewctaylor.require.RequireBound.requireGreaterThan;
import static io.github.drewctaylor.require.RequireBound.requireGreaterThanOrEqual;
import static io.github.drewctaylor.require.RequireBound.requireLessThan;
import static io.github.drewctaylor.require.RequireBound.requireLessThanOrEqual;
import static java.lang.String.format;

/**
 * Require that a string is blank or non-blank, is empty or non-empty, matches a regular expression, or has a length
 * within bounds.
 */
public final class RequireString
{
    private RequireString()
    {
    }

    private static String requireNonBlankHelper(
            final String value,
            final String name)
    {
        return requireHelper(!value.isBlank(), value, format("%s must be non-blank; it is '%s'.", name, value));
    }

    /**
     * Return the given value, if empty; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  name                     the name of the given value
     * @return                          the given value, if empty
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value is not empty
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is empty
     */
    public static String requireEmpty(
            final String value,
            final String name)
    {
        requireNonNull(value, "value");
        requireName(name);

        return require(value.isEmpty(), value, format("%s must be empty.", name));
    }

    /**
     * Return the given value, if non-empty; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  name                     the name of the given value
     * @return                          the given value, if non-empty
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value is not empty
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is empty
     */
    public static String requireNonEmpty(
            final String value,
            final String name)
    {
        requireNonNull(value, "value");
        requireName(name);

        return require(!value.isEmpty(), value, format("%s must be non-empty.", name));
    }

    /**
     * Return the given value, if blank; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  name                     the name of the given value
     * @return                          the given value, if blank
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value is non-blank
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is empty
     */
    public static String requireBlank(
            final String value,
            final String name)
    {
        requireNonNull(value, "value");
        requireName(name);

        return require(value.isBlank(), value, format("%s must be blank; it is '%s'.", name, value));
    }

    /**
     * Return the given value, if non-blank; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  name                     the name of the given value
     * @return                          the given value, if non-blank
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value is blank
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is empty
     */
    public static String requireNonBlank(
            final String value,
            final String name)
    {
        requireNonNull(value, "value");
        requireName(name);

        return requireNonBlankHelper(value, name);
    }

    /**
     * Return the given value, if it matches the given regular expression; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  regex                    the given regular expression
     * @param  name                     the name of the given value
     * @return                          the given value, if it matches the given regular expression
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value does not match the given regular expression
     * @throws NullPointerException     if regular expression is null
     * @throws IllegalArgumentException if regular expresesion is empty
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is empty
     */
    public static String requireMatch(
            final String value,
            final String regex,
            final String name)
    {
        requireNonNull(value, "value");
        requireNonEmpty(regex, "regex");
        requireName(name);

        return require(Pattern.matches(regex, value), value, format("%s must match '%s'; it is '%s'.", name, regex, value));
    }

    /**
     * Return the given value, if length is less than the given maximum; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  maximum                  the given maximum
     * @param  name                     the name of the given value
     * @return                          the given value, if length less than the given maximum
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value length is not less than the given maximum
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is empty
     */
    public static String requireLengthLessThan(
            final String value,
            final int maximum,
            final String name)
    {
        requireNonNull(value, "value");
        requireName(name);

        return requireLessThan(value, String::length, maximum, name, "length");
    }

    /**
     * Return the given value, if length is less than or equal to the given maximum; otherwise, throw an
     * IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  maximum                  the given maximum
     * @param  name                     the name of the given value
     * @return                          the given value, if length is less than or equal to the given maximum
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value length is not less than or equal to the given maximum
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is empty
     */
    public static String requireLengthLessThanOrEqual(
            final String value,
            final int maximum,
            final String name)
    {
        requireNonNull(value, "value");
        requireName(name);

        return requireLessThanOrEqual(value, String::length, maximum, name, "length");
    }

    /**
     * Return the given value, if length is the given length; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  length                   the given length
     * @param  name                     the name of the given value
     * @return                          the given value, if length is the given length
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value length is not the given length
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is empty
     */
    public static String requireLength(
            final String value,
            final int length,
            final String name)
    {
        requireNonNull(value, "value");
        requireName(name);

        return requireEqual(value, String::length, length, name, "length");
    }

    /**
     * Return the given value, if length is greater than or equal to the given length; otherwise, throw an
     * IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  minimum                  the given minimum
     * @param  name                     the name of the given value
     * @return                          the given value, if length is greater than or equal to the given length
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value length is not greater than or equal to the given length
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is empty
     */
    public static String requireLengthGreaterThanOrEqual(
            final String value,
            final int minimum,
            final String name)
    {
        requireNonNull(value, "value");
        requireName(name);

        return requireGreaterThanOrEqual(value, String::length, minimum, name, "length");
    }

    /**
     * Return the given value, if length is greater than the given length; otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  minimum                  the given minimum
     * @param  name                     the name of the given value
     * @return                          the given value, if length is greater than the given length
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value length is not greater than the given length
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is empty
     */
    public static String requireLengthGreaterThan(
            final String value,
            final int minimum,
            final String name)
    {
        requireNonNull(value, "value");
        requireName(name);

        return requireGreaterThan(value, String::length, minimum, name, "length");
    }

    /**
     * Return the given value, if length is between the given minimum and the given maximum (exclusive); otherwise, throw an
     * IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  minimumExclusive         the given minimum
     * @param  maximumExclusive         the given maximum
     * @param  name                     the name of the given value
     * @return                          the given value, if length is between the given minimum and the given maximum
     *                                  (exclusive)
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value length is not between the given minimum and the given maximum (exclusive)
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is empty
     */
    public static String requireLengthExclusive(
            final String value,
            final int minimumExclusive,
            final int maximumExclusive,
            final String name)
    {
        requireNonNull(value, "value");
        requireName(name);

        return requireBoundExclusive(value, String::length, minimumExclusive, maximumExclusive, name, "length");
    }

    /**
     * Return the given value, if length is between the given minimum and the given maximum (inclusive); otherwise, throw an
     * IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  minimumInclusive         the given minimum
     * @param  maximumInclusive         the given maximum
     * @param  name                     the name of the given value
     * @return                          the given value, if length is between the given minimum and the given maximum
     *                                  (inclusive)
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value length is not between the given minimum and the given maximum (inclusive)
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is empty
     */
    public static String requireLengthInclusive(
            final String value,
            final int minimumInclusive,
            final int maximumInclusive,
            final String name)
    {
        requireNonNull(value, "value");
        requireName(name);

        return requireBoundInclusive(value, String::length, minimumInclusive, maximumInclusive, name, "length");
    }

    /**
     * Return the given value, if length is between the given minimum (exclusive) and the given maximum (inclusive);
     * otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  minimumExclusive         the given minimum
     * @param  maximumInclusive         the given maximum
     * @param  name                     the name of the given value
     * @return                          the given value, if length is between the given minimum (exclusive) and the given
     *                                  maximum (inclusive)
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value length is not between the given minimum (exclusive) and the given maximum
     *                                  (inclusive)
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is empty
     */
    public static String requireLengthMinimumExclusiveMaximumInclusive(
            final String value,
            final int minimumExclusive,
            final int maximumInclusive,
            final String name)
    {
        requireNonNull(value, "value");
        requireName(name);

        return requireBoundMinimumExclusiveMaximumInclusive(value, String::length, minimumExclusive, maximumInclusive, name, "length");
    }

    /**
     * Return the given value, if if length is between the given minimum (inclusive) and the given maximum (exclusive);
     * otherwise, throw an IllegalArgumentException.
     *
     * @param  value                    the given value
     * @param  minimumInclusive         the given minimum
     * @param  maximumExclusive         the given maximum
     * @param  name                     the name of the given value
     * @return                          the given value, if length is between the given minimum (inclusive) and the given
     *                                  maximum (exclusive)
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value length is not between the given minimum (inclusive) and the given maximum
     *                                  (exclusive)
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is empty
     */
    public static String requireLengthMinimumInclusiveMaximumExclusive(
            final String value,
            final int minimumInclusive,
            final int maximumExclusive,
            final String name)
    {
        requireNonNull(value, "value");
        requireName(name);

        return requireBoundMinimumInclusiveMaximumExclusive(value, String::length, minimumInclusive, maximumExclusive, name, "length");
    }

    /**
     * Return the given name, if non-empty; otherwise, throw an IllegalArgumentException.
     *
     * @param  name                     the given name
     * @return                          the given name, if non-empty.
     * @throws NullPointerException     if value is null
     * @throws IllegalArgumentException if value is
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException if name is empty
     */
    public static String requireName(
            final String name)
    {
        requireNonNullHelper(name, "name");

        return requireNonBlankHelper(name, "name");
    }
}