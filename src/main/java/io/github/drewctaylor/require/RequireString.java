package io.github.drewctaylor.require;

import java.util.regex.Pattern;

import static io.github.drewctaylor.require.Require.require;
import static io.github.drewctaylor.require.Require.requireName;
import static io.github.drewctaylor.require.Require.requireNonBlankHelper;
import static io.github.drewctaylor.require.Require.requireNonNull;
import static io.github.drewctaylor.require.RequireBound.requireBoundExclusive;
import static io.github.drewctaylor.require.RequireBound.requireBoundInclusive;
import static io.github.drewctaylor.require.RequireBound.requireBoundMinimumExclusiveMaximumInclusive;
import static io.github.drewctaylor.require.RequireBound.requireBoundMinimumInclusiveMaximumExclusive;
import static io.github.drewctaylor.require.RequireBound.requireEqual;
import static io.github.drewctaylor.require.RequireBound.requireGreaterThan;
import static io.github.drewctaylor.require.RequireBound.requireGreaterThanOrEqual;
import static io.github.drewctaylor.require.RequireBound.requireLessThan;
import static io.github.drewctaylor.require.RequireBound.requireLessThanOrEqual;
import static io.github.drewctaylor.require.RequireNumberInteger.requireZeroOrPositive;
import static java.lang.String.format;

/**
 * Require a string to be empty or non-empty, to have a length within bounds, to be blank or non-blank, or to match a
 * regular expression.
 */
public final class RequireString
{
    private RequireString()
    {
    }

    /**
     * Return the given string, if length is less than the given maximum; otherwise, throw an IllegalArgumentException.
     *
     * @param  string                   the given string
     * @param  maximum                  the given maximum
     * @param  name                     the name of the given string
     * @return                          the given string, if length less than the given maximum
     * @throws NullPointerException     if string is null
     * @throws IllegalArgumentException if string length is not less than the given maximum
     * @throws IllegalArgumentException if maximum is not zero or positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static String requireLengthLessThan(
            final String string,
            final int maximum,
            final String name)
    {
        requireNonNull(string, "string");
        requireZeroOrPositive(maximum, "maximum");
        requireName(name);

        return requireLessThan(string, String::length, maximum, name, "length");
    }

    /**
     * Return the given string, if length is less than or equal to the given maximum; otherwise, throw an
     * IllegalArgumentException.
     *
     * @param  string                   the given string
     * @param  maximum                  the given maximum
     * @param  name                     the name of the given string
     * @return                          the given string, if length is less than or equal to the given maximum
     * @throws NullPointerException     if string is null
     * @throws IllegalArgumentException if string length is not less than or equal to the given maximum
     * @throws IllegalArgumentException if maximum is not zero or positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static String requireLengthLessThanOrEqual(
            final String string,
            final int maximum,
            final String name)
    {
        requireNonNull(string, "string");
        requireZeroOrPositive(maximum, "maximum");
        requireName(name);

        return requireLessThanOrEqual(string, String::length, maximum, name, "length");
    }

    /**
     * Return the given string, if length is the given length; otherwise, throw an IllegalArgumentException.
     *
     * @param  string                   the given string
     * @param  length                   the given length
     * @param  name                     the name of the given string
     * @return                          the given string, if length is the given length
     * @throws NullPointerException     if string is null
     * @throws IllegalArgumentException if string length is not the given length
     * @throws IllegalArgumentException if length is not zero or positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static String requireLength(
            final String string,
            final int length,
            final String name)
    {
        requireNonNull(string, "string");
        requireZeroOrPositive(length, "length");
        requireName(name);

        return requireEqual(string, String::length, length, name, "length");
    }

    /**
     * Return the given string, if length is greater than or equal to the given length; otherwise, throw an
     * IllegalArgumentException.
     *
     * @param  string                   the given string
     * @param  minimum                  the given minimum
     * @param  name                     the name of the given string
     * @return                          the given string, if length is greater than or equal to the given length
     * @throws NullPointerException     if string is null
     * @throws IllegalArgumentException if string length is not greater than or equal to the given length
     * @throws IllegalArgumentException if minimum is not zero or positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static String requireLengthGreaterThanOrEqual(
            final String string,
            final int minimum,
            final String name)
    {
        requireNonNull(string, "string");
        requireZeroOrPositive(minimum, "minimum");
        requireName(name);

        return requireGreaterThanOrEqual(string, String::length, minimum, name, "length");
    }

    /**
     * Return the given string, if length is greater than the given length; otherwise, throw an IllegalArgumentException.
     *
     * @param  string                   the given string
     * @param  minimum                  the given minimum
     * @param  name                     the name of the given string
     * @return                          the given string, if length is greater than the given length
     * @throws NullPointerException     if string is null
     * @throws IllegalArgumentException if string length is not greater than the given length
     * @throws IllegalArgumentException if minimum is not zero or positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static String requireLengthGreaterThan(
            final String string,
            final int minimum,
            final String name)
    {
        requireNonNull(string, "string");
        requireZeroOrPositive(minimum, "minimum");
        requireName(name);

        return requireGreaterThan(string, String::length, minimum, name, "length");
    }

    /**
     * Return the given string, if length is between the given minimum and the given maximum; otherwise, throw an
     * IllegalArgumentException.
     *
     * @param  string                   the given string
     * @param  minimum                  the given minimum
     * @param  maximum                  the given maximum
     * @param  name                     the name of the given string
     * @return                          the given string, if length is between the given minimum and the given maximum
     * @throws NullPointerException     if string is null
     * @throws IllegalArgumentException if string length is not between the given minimum and the given maximum
     * @throws IllegalArgumentException if minimum is not zero or positive
     * @throws IllegalArgumentException if maximum is not zero or positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static String requireLength(
            final String string,
            final int minimum,
            final int maximum,
            final String name)
    {
        requireNonNull(string, "string");
        requireZeroOrPositive(minimum, "minimum");
        requireZeroOrPositive(maximum, "maximum");
        requireName(name);

        return requireBoundInclusive(string, String::length, minimum, maximum, name, "length");
    }

    /**
     * Return the given string, if length is between the given minimum and the given maximum; otherwise, throw an
     * IllegalArgumentException.
     *
     * @param  string                   the given string
     * @param  minimum                  the given minimum
     * @param  maximum                  the given maximum
     * @param  name                     the name of the given string
     * @return                          the given string, if length is between the given minimum and the given maximum
     * @throws NullPointerException     if string is null
     * @throws IllegalArgumentException if string length is not between the given minimum and the given maximum
     * @throws IllegalArgumentException if minimum is not zero or positive
     * @throws IllegalArgumentException if maximum is not zero or positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static String requireLengthExclusive(
            final String string,
            final int minimum,
            final int maximum,
            final String name)
    {
        requireNonNull(string, "string");
        requireZeroOrPositive(minimum, "minimum");
        requireZeroOrPositive(maximum, "maximum");
        requireName(name);

        return requireBoundExclusive(string, String::length, minimum, maximum, name, "length");
    }

    /**
     * Return the given string, if length is between the given minimum and the given maximum; otherwise, throw an
     * IllegalArgumentException.
     *
     * @param  string                   the given string
     * @param  minimum                  the given minimum
     * @param  maximum                  the given maximum
     * @param  name                     the name of the given string
     * @return                          the given string, if length is between the given minimum and the given maximum
     * @throws NullPointerException     if string is null
     * @throws IllegalArgumentException if string length is not between the given minimum and the given maximum
     * @throws IllegalArgumentException if minimum is not zero or positive
     * @throws IllegalArgumentException if maximum is not zero or positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static String requireLengthMinimumExclusiveMaximumInclusive(
            final String string,
            final int minimum,
            final int maximum,
            final String name)
    {
        requireNonNull(string, "string");
        requireZeroOrPositive(minimum, "minimum");
        requireZeroOrPositive(maximum, "maximum");
        requireName(name);

        return requireBoundMinimumExclusiveMaximumInclusive(string, String::length, minimum, maximum, name, "length");
    }

    /**
     * Return the given string, if length is between the given minimum and the given maximum; otherwise, throw an
     * IllegalArgumentException.
     *
     * @param  string                   the given string
     * @param  minimum                  the given minimum
     * @param  maximum                  the given maximum
     * @param  name                     the name of the given string
     * @return                          the given string, if length is between the given minimum and the given maximum
     * @throws NullPointerException     if string is null
     * @throws IllegalArgumentException if string length is not between the given minimum and the given maximum
     * @throws IllegalArgumentException if minimum is not zero or positive
     * @throws IllegalArgumentException if maximum is not zero or positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static String requireLengthMinimumInclusiveMaximumExclusive(
            final String string,
            final int minimum,
            final int maximum,
            final String name)
    {
        requireNonNull(string, "string");
        requireZeroOrPositive(minimum, "minimum");
        requireZeroOrPositive(maximum, "maximum");
        requireName(name);

        return requireBoundMinimumInclusiveMaximumExclusive(string, String::length, minimum, maximum, name, "length");
    }

    /**
     * Return the given string, if empty; otherwise, throw an IllegalArgumentException.
     *
     * @param  string                   the given string
     * @param  name                     the name of the given string
     * @return                          the given string, if empty
     * @throws NullPointerException     if string is null
     * @throws IllegalArgumentException if string is not empty
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static String requireEmpty(
            final String string,
            final String name)
    {
        requireNonNull(string, "string");
        requireName(name);

        return require(string.isEmpty(), string, format("%s must be empty; it was '%s'.", name, string));
    }

    /**
     * Return the given string, if non-empty; otherwise, throw an IllegalArgumentException.
     *
     * @param  string                   the given string
     * @param  name                     the name of the given string
     * @return                          the given string, if non-empty
     * @throws NullPointerException     if string is null
     * @throws IllegalArgumentException if string is not empty
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static String requireNonEmpty(
            final String string,
            final String name)
    {
        requireNonNull(string, "string");
        requireName(name);

        return require(!string.isEmpty(), string, format("%s must be non-empty.", name));
    }

    /**
     * Return the given string, if blank; otherwise, throw an IllegalArgumentException.
     *
     * @param  string                   the given string
     * @param  name                     the name of the given string
     * @return                          the given string, if blank
     * @throws NullPointerException     if string is null
     * @throws IllegalArgumentException if string is non-blank
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static String requireBlank(
            final String string,
            final String name)
    {
        requireNonNull(string, "string");
        requireName(name);

        return require(string.isBlank(), string, format("%s must be blank; it is '%s'.", name, string));
    }

    /**
     * Return the given string, if non-blank; otherwise, throw an IllegalArgumentException.
     *
     * @param  string                   the given string
     * @param  name                     the name of the given string
     * @return                          the given string, if non-blank
     * @throws NullPointerException     if string is null
     * @throws IllegalArgumentException if string is blank
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static String requireNonBlank(
            final String string,
            final String name)
    {
        requireNonNull(string, "string");
        requireName(name);

        return requireNonBlankHelper(string, name);
    }

    /**
     * Return the given string, if it matches the given pattern; otherwise, throw an IllegalArgumentException.
     *
     * @param  string                   the given string
     * @param  pattern                  the given pattern
     * @param  name                     the name of the given string
     * @return                          the given string, if it matches the given regular expression
     * @throws NullPointerException     if string is null
     * @throws IllegalArgumentException if string does not match the given regular expression
     * @throws NullPointerException     if pattern is null
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static String requireMatch(
            final String string,
            final Pattern pattern,
            final String name)
    {
        requireNonNull(string, "string");
        requireNonNull(pattern, "pattern");
        requireName(name);

        return require(pattern.matcher(string).matches(), string, format("%s must match '%s'; it is '%s'.", name, pattern, string));
    }
}