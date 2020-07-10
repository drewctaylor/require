package io.github.drewctaylor.require;

import java.util.Map;
import java.util.function.Function;

import static io.github.drewctaylor.require.Require.requireName;
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
import static io.github.drewctaylor.require.RequireCollection.requireForAll;
import static io.github.drewctaylor.require.RequireCollection.requireThereExists;
import static io.github.drewctaylor.require.RequireNumberInteger.requireZeroOrPositive;

/**
 * Require a map to be empty or non-empty, to have a size within bounds, to have at least one key meet a requirement, to
 * have all keys meet a requirement, to have at least one value meet a requirement, or to have all values meet a
 * requirement.
 */

public final class RequireMap
{
    private RequireMap()
    {
    }

    /**
     * Return the given map, if empty; otherwise, throw an IllegalArgumentException.
     *
     * @param  map                      the given map
     * @param  name                     the name of the given map
     * @param  <KEY>                    the type of map key
     * @param  <VALUE>                  the type of the map value
     * @param  <MAP>                    the type of the map
     * @return                          the given map, if empty
     * @throws NullPointerException     if map is null
     * @throws IllegalArgumentException if map is not empty
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <KEY, VALUE, MAP extends Map<KEY, VALUE>> MAP requireEmpty(
            final MAP map,
            final String name)
    {
        requireNonNull(map, "map");
        requireName(name);

        RequireCollection.requireEmpty(map.keySet(), name);
        return map;
    }

    /**
     * Return the given map, if non-empty; otherwise, throw an IllegalArgumentException.
     *
     * @param  map                      the given map
     * @param  name                     the name of the given map
     * @param  <KEY>                    the type of map key
     * @param  <VALUE>                  the type of the map value
     * @param  <MAP>                    the type of the map
     * @return                          the given map, if non-empty
     * @throws NullPointerException     if map is null
     * @throws IllegalArgumentException if map is not empty
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <KEY, VALUE, MAP extends Map<KEY, VALUE>> MAP requireNonEmpty(
            final MAP map,
            final String name)
    {
        requireNonNull(map, "map");
        requireName(name);

        RequireCollection.requireNonEmpty(map.keySet(), name);
        return map;
    }

    /**
     * Return the given map, if size is less than the given maximum; otherwise, throw an IllegalArgumentException.
     *
     * @param  map                      the given map
     * @param  maximum                  the given maximum
     * @param  name                     the name of the given map
     * @param  <KEY>                    the type of map key
     * @param  <VALUE>                  the type of the map value
     * @param  <MAP>                    the type of the map
     * @return                          the given map, if size less than the given maximum
     * @throws NullPointerException     if map is null
     * @throws IllegalArgumentException if map size is not less than the given maximum
     * @throws IllegalArgumentException if maximum is not zero or positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <KEY, VALUE, MAP extends Map<KEY, VALUE>> MAP requireSizeLessThan(
            final MAP map,
            final int maximum,
            final String name)
    {
        requireNonNull(map, "map");
        requireZeroOrPositive(maximum, "maximum");
        requireName(name);

        return requireLessThan(map, Map::size, maximum, name, "size");
    }

    /**
     * Return the given map, if size is less than or equal to the given maximum; otherwise, throw an
     * IllegalArgumentException.
     *
     * @param  map                      the given map
     * @param  maximum                  the given maximum
     * @param  name                     the name of the given map
     * @param  <KEY>                    the type of map key
     * @param  <VALUE>                  the type of the map value
     * @param  <MAP>                    the type of the map
     * @return                          the given map, if size is less than or equal to the given maximum
     * @throws NullPointerException     if map is null
     * @throws IllegalArgumentException if map size is not less than or equal to the given maximum
     * @throws IllegalArgumentException if maximum is not zero or positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <KEY, VALUE, MAP extends Map<KEY, VALUE>> MAP requireSizeLessThanOrEqual(
            final MAP map,
            final int maximum,
            final String name)
    {
        requireNonNull(map, "map");
        requireZeroOrPositive(maximum, "maximum");
        requireName(name);

        return requireLessThanOrEqual(map, Map::size, maximum, name, "size");
    }

    /**
     * Return the given map, if size is the given size; otherwise, throw an IllegalArgumentException.
     *
     * @param  map                      the given map
     * @param  size                     the given size
     * @param  name                     the name of the given map
     * @param  <KEY>                    the type of map key
     * @param  <VALUE>                  the type of the map value
     * @param  <MAP>                    the type of the map
     * @return                          the given map, if size is the given size
     * @throws NullPointerException     if map is null
     * @throws IllegalArgumentException if map size is not the given size
     * @throws IllegalArgumentException if size is not zero or positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <KEY, VALUE, MAP extends Map<KEY, VALUE>> MAP requireSize(
            final MAP map,
            final int size,
            final String name)
    {
        requireNonNull(map, "map");
        requireZeroOrPositive(size, "size");
        requireName(name);

        return requireEqual(map, Map::size, size, name, "size");
    }

    /**
     * Return the given map, if size is greater than or equal to the given size; otherwise, throw an
     * IllegalArgumentException.
     *
     * @param  map                      the given map
     * @param  minimum                  the given minimum
     * @param  name                     the name of the given map
     * @param  <KEY>                    the type of map key
     * @param  <VALUE>                  the type of the map value
     * @param  <MAP>                    the type of the map
     * @return                          the given map, if size is greater than or equal to the given size
     * @throws NullPointerException     if map is null
     * @throws IllegalArgumentException if map size is not greater than or equal to the given size
     * @throws IllegalArgumentException if minimum is not zero or positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <KEY, VALUE, MAP extends Map<KEY, VALUE>> MAP requireSizeGreaterThanOrEqual(
            final MAP map,
            final int minimum,
            final String name)
    {
        requireNonNull(map, "map");
        requireZeroOrPositive(minimum, "minimum");
        requireName(name);

        return requireGreaterThanOrEqual(map, Map::size, minimum, name, "size");
    }

    /**
     * Return the given map, if size is greater than the given size; otherwise, throw an IllegalArgumentException.
     *
     * @param  map                      the given map
     * @param  minimum                  the given minimum
     * @param  name                     the name of the given map
     * @param  <KEY>                    the type of map key
     * @param  <VALUE>                  the type of the map value
     * @param  <MAP>                    the type of the map
     * @return                          the given map, if size is greater than the given size
     * @throws NullPointerException     if map is null
     * @throws IllegalArgumentException if map size is not greater than the given size
     * @throws IllegalArgumentException if minimum is not zero or positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <KEY, VALUE, MAP extends Map<KEY, VALUE>> MAP requireSizeGreaterThan(
            final MAP map,
            final int minimum,
            final String name)
    {
        requireNonNull(map, "map");
        requireZeroOrPositive(minimum, "minimum");
        requireName(name);

        return requireGreaterThan(map, Map::size, minimum, name, "size");
    }

    /**
     * Return the given map, if size is between the given minimum and the given maximum; otherwise, throw an
     * IllegalArgumentException.
     *
     * @param  map                      the given map
     * @param  minimum                  the given minimum
     * @param  maximum                  the given maximum
     * @param  name                     the name of the given map
     * @param  <KEY>                    the type of map key
     * @param  <VALUE>                  the type of the map value
     * @param  <MAP>                    the type of the map
     * @return                          the given map, if size is between the given minimum and the given maximum
     * @throws NullPointerException     if map is null
     * @throws IllegalArgumentException if map size is not between the given minimum and the given maximum
     * @throws IllegalArgumentException if minimum is not zero or positive
     * @throws IllegalArgumentException if maximum is not zero or positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <KEY, VALUE, MAP extends Map<KEY, VALUE>> MAP requireSizeExclusive(
            final MAP map,
            final int minimum,
            final int maximum,
            final String name)
    {
        requireNonNull(map, "map");
        requireZeroOrPositive(minimum, "minimum");
        requireZeroOrPositive(maximum, "maximum");
        requireName(name);

        return requireBoundExclusive(map, Map::size, minimum, maximum, name, "size");
    }

    /**
     * Return the given map, if size is between the given minimum and the given maximum; otherwise, throw an
     * IllegalArgumentException.
     *
     * @param  map                      the given map
     * @param  minimum                  the given minimum
     * @param  maximum                  the given maximum
     * @param  name                     the name of the given map
     * @param  <KEY>                    the type of map key
     * @param  <VALUE>                  the type of the map value
     * @param  <MAP>                    the type of the map
     * @return                          the given map, if size is between the given minimum and the given maximum
     * @throws NullPointerException     if map is null
     * @throws IllegalArgumentException if map size is not between the given minimum and the given maximum
     * @throws IllegalArgumentException if minimum is not zero or positive
     * @throws IllegalArgumentException if maximum is not zero or positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <KEY, VALUE, MAP extends Map<KEY, VALUE>> MAP requireSize(
            final MAP map,
            final int minimum,
            final int maximum,
            final String name)
    {
        requireNonNull(map, "map");
        requireZeroOrPositive(minimum, "minimum");
        requireZeroOrPositive(maximum, "maximum");
        requireName(name);

        return requireBoundInclusive(map, Map::size, minimum, maximum, name, "size");
    }

    /**
     * Return the given map, if size is between the given minimum and the given maximum; otherwise, throw an
     * IllegalArgumentException.
     *
     * @param  map                      the given map
     * @param  minimum                  the given minimum
     * @param  maximum                  the given maximum
     * @param  name                     the name of the given map
     * @param  <KEY>                    the type of map key
     * @param  <VALUE>                  the type of the map value
     * @param  <MAP>                    the type of the map
     * @return                          the given map, if size is between the given minimum and the given maximum
     * @throws NullPointerException     if map is null
     * @throws IllegalArgumentException if map size is not between the given minimum and the given maximum
     * @throws IllegalArgumentException if minimum is not zero or positive
     * @throws IllegalArgumentException if maximum is not zero or positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <KEY, VALUE, MAP extends Map<KEY, VALUE>> MAP requireSizeMinimumExclusiveMaximumInclusive(
            final MAP map,
            final int minimum,
            final int maximum,
            final String name)
    {
        requireNonNull(map, "map");
        requireZeroOrPositive(minimum, "minimum");
        requireZeroOrPositive(maximum, "maximum");
        requireName(name);

        return requireBoundMinimumExclusiveMaximumInclusive(map, Map::size, minimum, maximum, name, "size");
    }

    /**
     * Return the given map, if size is between the given minimum and the given maximum; otherwise, throw an
     * IllegalArgumentException.
     *
     * @param  map                      the given map
     * @param  minimum                  the given minimum
     * @param  maximum                  the given maximum
     * @param  name                     the name of the given map
     * @param  <KEY>                    the type of map key
     * @param  <VALUE>                  the type of the map value
     * @param  <MAP>                    the type of the map
     * @return                          the given map, if size is between the given minimum and the given maximum
     * @throws NullPointerException     if map is null
     * @throws IllegalArgumentException if map size is not between the given minimum and the given maximum
     * @throws IllegalArgumentException if minimum is not zero or positive
     * @throws IllegalArgumentException if maximum is not zero or positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <KEY, VALUE, MAP extends Map<KEY, VALUE>> MAP requireSizeMinimumInclusiveMaximumExclusive(
            final MAP map,
            final int minimum,
            final int maximum,
            final String name)
    {
        requireNonNull(map, "map");
        requireZeroOrPositive(minimum, "minimum");
        requireZeroOrPositive(maximum, "maximum");
        requireName(name);

        return requireBoundMinimumInclusiveMaximumExclusive(map, Map::size, minimum, maximum, name, "size");
    }

    /**
     * Return the given map, if all keys meet the given requirement.
     *
     * @param  map                      the given map
     * @param  require                  the given requirement
     * @param  name                     the name of the given map
     * @param  <KEY>                    the type of map key
     * @param  <VALUE>                  the type of the map value
     * @param  <MAP>                    the type of the map
     * @return                          the given map, if all keys meet the given requirement.
     * @throws NullPointerException     if map is null
     * @throws NullPointerException     if require is null
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <KEY, VALUE, MAP extends Map<KEY, VALUE>> MAP requireForAllKey(
            final MAP map,
            final Function<KEY, KEY> require,
            final String name)
    {
        requireNonNull(map, "map");
        requireNonNull(require, "require");
        requireName(name);

        return requireForAll(map, Map::keySet, require, name, "key");
    }

    /**
     * Return the given map, if at least one key meets the given requirement.
     *
     * @param  map                      the given map
     * @param  require                  the given requirement
     * @param  name                     the name of the given map
     * @param  <KEY>                    the type of map key
     * @param  <VALUE>                  the type of the map value
     * @param  <MAP>                    the type of the map
     * @return                          the given map, if at least one key meets the given requirement.
     * @throws NullPointerException     if map is null
     * @throws NullPointerException     if require is null
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <KEY, VALUE, MAP extends Map<KEY, VALUE>> MAP requireThereExistsKey(
            final MAP map,
            final Function<KEY, KEY> require,
            final String name)
    {
        requireNonNull(map, "map");
        requireNonNull(require, "require");
        requireName(name);

        return requireThereExists(map, Map::keySet, require, name, "key");
    }

    /**
     * Return the given map, if all values meet the given requirement.
     *
     * @param  map                      the given map
     * @param  require                  the given requirement
     * @param  name                     the name of the given map
     * @param  <KEY>                    the type of map key
     * @param  <VALUE>                  the type of the map value
     * @param  <MAP>                    the type of the map
     * @return                          the given map, if all values meet the given requirement.
     * @throws NullPointerException     if map is null
     * @throws NullPointerException     if require is null
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <KEY, VALUE, MAP extends Map<KEY, VALUE>> MAP requireForAllValue(
            final MAP map,
            final Function<VALUE, VALUE> require,
            final String name)
    {
        requireNonNull(map, "map");
        requireNonNull(require, "require");
        requireName(name);

        return requireForAll(map, Map::values, require, name, "map");
    }

    /**
     * Return the given map, if at least one value meets the given requirement.
     *
     * @param  map                      the given map
     * @param  require                  the given requirement
     * @param  name                     the name of the given map
     * @param  <KEY>                    the type of map key
     * @param  <VALUE>                  the type of the map value
     * @param  <MAP>                    the type of the map
     * @return                          the given map, if at least one value meets the given requirement.
     * @throws NullPointerException     if map is null
     * @throws NullPointerException     if require is null
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <KEY, VALUE, MAP extends Map<KEY, VALUE>> MAP requireThereExistsValue(
            final MAP map,
            final Function<VALUE, VALUE> require,
            final String name)
    {
        requireNonNull(map, "map");
        requireNonNull(require, "require");
        requireName(name);

        return requireThereExists(map, Map::values, require, name, "map");
    }
}
