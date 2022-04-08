package io.github.drewctaylor.require;

import java.util.function.Function;

import static io.github.drewctaylor.require.Require.require;
import static io.github.drewctaylor.require.Require.requireName;
import static io.github.drewctaylor.require.Require.requireNonNull;
import static java.lang.String.format;

final class RequireNumberHelper<TYPE extends Comparable<TYPE>>
{
    private final TYPE zero;
    private final Function<String, TYPE> parse;
    private final Class<TYPE> type;

    RequireNumberHelper(
            final TYPE zero,
            final Function<String, TYPE> parse,
            final Class<TYPE> type)
    {
        this.zero = zero;
        this.parse = parse;
        this.type = type;
    }

    TYPE requirePositive(
            final TYPE value,
            final String name)
    {
        requireNonNull(value, name);
        requireName(name);

        return require(value.compareTo(zero) > 0, value, format("%s must be positive; it is '%s'.", name, value));
    }

    TYPE requireZeroOrPositive(
            final TYPE value,
            final String name)
    {
        requireNonNull(value, name);
        requireName(name);

        return require(value.compareTo(zero) >= 0, value, format("%s must be zero or positive; it is '%s'.", name, value));
    }

    TYPE requireZero(
            final TYPE value,
            final String name)
    {
        requireNonNull(value, name);
        requireName(name);

        return require(value.compareTo(zero) == 0, value, format("%s must be zero; it is '%s'.", name, value));
    }

    TYPE requireZeroOrNegative(
            final TYPE value,
            final String name)
    {
        requireNonNull(value, name);
        requireName(name);

        return require(value.compareTo(zero) <= 0, value, format("%s must be zero or negative; it is '%s'.", name, value));
    }

    TYPE requireNegative(
            final TYPE value,
            final String name)
    {
        requireNonNull(value, name);
        requireName(name);

        return require(value.compareTo(zero) < 0, value, format("%s must be negative; it is '%s'.", name, value));
    }

    TYPE requireNumber(
            final String value,
            final String name)
    {
        requireNonNull(value, name);
        requireName(name);

        try
        {
            return parse.apply(value);
        }
        catch (final RuntimeException runtimeException)
        {
            throw new IllegalArgumentException(format("%s must be a %s; it is '%s'.", name, type.getCanonicalName(), value), runtimeException);
        }
    }
}
