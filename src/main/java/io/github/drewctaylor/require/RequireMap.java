package io.github.drewctaylor.require;

import java.util.Map;
import java.util.function.Function;

import static io.github.drewctaylor.require.RequireBound.requireBoundExclusive;
import static io.github.drewctaylor.require.RequireBound.requireBoundInclusive;
import static io.github.drewctaylor.require.RequireBound.requireBoundMinimumExclusiveMaximumInclusive;
import static io.github.drewctaylor.require.RequireBound.requireBoundMinimumInclusiveMaximumExclusive;
import static io.github.drewctaylor.require.RequireBound.requireEqual;
import static io.github.drewctaylor.require.RequireBound.requireGreaterThan;
import static io.github.drewctaylor.require.RequireBound.requireGreaterThanOrEqual;
import static io.github.drewctaylor.require.RequireBound.requireLessThan;
import static io.github.drewctaylor.require.RequireBound.requireLessThanOrEqual;
import static io.github.drewctaylor.require.RequireCollection.requireForAll;
import static io.github.drewctaylor.require.RequireCollection.requireThereExists;

public final class RequireMap
{
    private RequireMap()
    {
    }

    public static <KEY, VALUE, MAP extends Map<KEY, VALUE>> MAP requireEmpty(
            final MAP value,
            final String name)
    {
        RequireCollection.requireEmpty(value.keySet(), name);
        return value;
    }

    public static <KEY, VALUE, MAP extends Map<KEY, VALUE>> MAP requireNonEmpty(
            final MAP value,
            final String name)
    {
        RequireCollection.requireNonEmpty(value.keySet(), name);
        return value;
    }

    public static <KEY, VALUE, MAP extends Map<KEY, VALUE>> MAP requireSizeLessThan(
            final MAP value,
            final int maximum,
            final String name)
    {
        return requireLessThan(value, Map::size, maximum, name, "size");
    }

    public static <KEY, VALUE, MAP extends Map<KEY, VALUE>> MAP requireSizeLessThanOrEqual(
            final MAP value,
            final int maximum,
            final String name)
    {
        return requireLessThanOrEqual(value, Map::size, maximum, name, "size");
    }

    public static <KEY, VALUE, MAP extends Map<KEY, VALUE>> MAP requireSize(
            final MAP value,
            final int length,
            final String name)
    {
        return requireEqual(value, Map::size, length, name, "size");
    }

    public static <KEY, VALUE, MAP extends Map<KEY, VALUE>> MAP requireSizeGreaterThanOrEqual(
            final MAP value,
            final int minimum,
            final String name)
    {
        return requireGreaterThanOrEqual(value, Map::size, minimum, name, "size");
    }

    public static <KEY, VALUE, MAP extends Map<KEY, VALUE>> MAP requireSizeGreaterThan(
            final MAP value,
            final int minimum,
            final String name)
    {
        return requireGreaterThan(value, Map::size, minimum, name, "size");
    }

    public static <KEY, VALUE, MAP extends Map<KEY, VALUE>> MAP requireSizeExclusive(
            final MAP value,
            final int minimumExclusive,
            final int maximumExclusive,
            final String name)
    {
        return requireBoundExclusive(value, Map::size, minimumExclusive, maximumExclusive, name, "size");
    }

    public static <KEY, VALUE, MAP extends Map<KEY, VALUE>> MAP requireSizeInclusive(
            final MAP value,
            final int minimumInclusive,
            final int maximumInclusive,
            final String name)
    {
        return requireBoundInclusive(value, Map::size, minimumInclusive, maximumInclusive, name, "size");
    }

    public static <KEY, VALUE, MAP extends Map<KEY, VALUE>> MAP requireSizeMinimumExclusiveMaximumInclusive(
            final MAP value,
            final int minimumExclusive,
            final int maximumInclusive,
            final String name)
    {
        return requireBoundMinimumExclusiveMaximumInclusive(value, Map::size, minimumExclusive, maximumInclusive, name, "size");
    }

    public static <KEY, VALUE, MAP extends Map<KEY, VALUE>> MAP requireSizeMinimumInclusiveMaximumExclusive(
            final MAP value,
            final int minimumInclusive,
            final int maximumExclusive,
            final String name)
    {
        return requireBoundMinimumInclusiveMaximumExclusive(value, Map::size, minimumInclusive, maximumExclusive, name, "size");
    }

    public static <KEY, VALUE, MAP extends Map<KEY, VALUE>> MAP requireForAllKey(
            final MAP value,
            final Function<KEY, KEY> require,
            final String name)
    {
        return requireForAll(value, Map::keySet, require, name, "key");
    }

    public static <KEY, VALUE, MAP extends Map<KEY, VALUE>> MAP requireThereExistsKey(
            final MAP value,
            final Function<KEY, KEY> require,
            final String name)
    {
        return requireThereExists(value, Map::keySet, require, name, "key");
    }

    public static <KEY, VALUE, MAP extends Map<KEY, VALUE>> MAP requireForAllValue(
            final MAP value,
            final Function<VALUE, VALUE> require,
            final String name)
    {
        return requireForAll(value, Map::values, require, name, "value");
    }

    public static <KEY, VALUE, MAP extends Map<KEY, VALUE>> MAP requireThereExistsValue(
            final MAP value,
            final Function<VALUE, VALUE> require,
            final String name)
    {
        return requireThereExists(value, Map::values, require, name, "value");
    }
}
