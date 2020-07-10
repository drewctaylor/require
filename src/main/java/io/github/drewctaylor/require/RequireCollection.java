package io.github.drewctaylor.require;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.github.drewctaylor.require.Require.require;
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
import static io.github.drewctaylor.require.RequireNumberInteger.requireZeroOrPositive;
import static java.lang.String.format;
import static java.lang.System.lineSeparator;
import static java.util.Map.Entry;
import static java.util.Map.entry;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.Spliterator.ORDERED;
import static java.util.Spliterators.spliteratorUnknownSize;
import static java.util.function.Function.identity;
import static java.util.stream.Stream.iterate;
import static java.util.stream.StreamSupport.stream;

/**
 * Require a collection to be empty or non-empty, to have a size within bounds, to have at least one element meet a
 * requirement, or to have all elements meet a requirement.
 */
public final class RequireCollection
{
    private RequireCollection()
    {
    }

    /**
     * Return the given collection, if empty; otherwise, throw an IllegalArgumentException.
     *
     * @param  collection               the given collection
     * @param  name                     the name of the given collection
     * @param  <TYPE>                   the type of the collection element
     * @param  <COLLECTION>             the type of the collection
     * @return                          the given collection, if empty
     * @throws NullPointerException     if collection is null
     * @throws IllegalArgumentException if collection is not empty
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <TYPE, COLLECTION extends Collection<TYPE>> COLLECTION requireEmpty(
            final COLLECTION collection,
            final String name)
    {
        requireNonNull(collection, "collection");
        requireName(name);

        return require(collection.isEmpty(), collection, format("%s must be empty; it was '%s'.", name, collection.size()));
    }

    /**
     * Return the given collection, if non-empty; otherwise, throw an IllegalArgumentException.
     *
     * @param  collection               the given collection
     * @param  name                     the name of the given collection
     * @param  <TYPE>                   the type of the collection element
     * @param  <COLLECTION>             the type of the collection
     * @return                          the given collection, if non-empty
     * @throws NullPointerException     if collection is null
     * @throws IllegalArgumentException if collection is not empty
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <TYPE, COLLECTION extends Collection<TYPE>> COLLECTION requireNonEmpty(
            final COLLECTION collection,
            final String name)
    {
        requireNonNull(collection, "collection");
        requireName(name);

        return require(!collection.isEmpty(), collection, format("%s must be non-empty.", name));
    }

    /**
     * Return the given collection, if size is less than the given maximum; otherwise, throw an IllegalArgumentException.
     *
     * @param  collection               the given collection
     * @param  maximum                  the given maximum
     * @param  name                     the name of the given collection
     * @param  <TYPE>                   the type of the collection element
     * @param  <COLLECTION>             the type of the collection
     * @return                          the given collection, if size less than the given maximum
     * @throws NullPointerException     if collection is null
     * @throws IllegalArgumentException if collection size is not less than the given maximum
     * @throws IllegalArgumentException if maximum is not zero or positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <TYPE, COLLECTION extends Collection<TYPE>> COLLECTION requireSizeLessThan(
            final COLLECTION collection,
            final int maximum,
            final String name)
    {
        requireNonNull(collection, "collection");
        requireZeroOrPositive(maximum, "maximum");
        requireName(name);

        return requireLessThan(collection, Collection::size, maximum, name, "size");
    }

    /**
     * Return the given collection, if size is less than or equal to the given maximum; otherwise, throw an
     * IllegalArgumentException.
     *
     * @param  collection               the given collection
     * @param  maximum                  the given maximum
     * @param  name                     the name of the given collection
     * @param  <TYPE>                   the type of the collection element
     * @param  <COLLECTION>             the type of the collection
     * @return                          the given collection, if size is less than or equal to the given maximum
     * @throws NullPointerException     if collection is null
     * @throws IllegalArgumentException if collection size is not less than or equal to the given maximum
     * @throws IllegalArgumentException if maximum is not zero or positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <TYPE, COLLECTION extends Collection<TYPE>> COLLECTION requireSizeLessThanOrEqual(
            final COLLECTION collection,
            final int maximum,
            final String name)
    {
        requireNonNull(collection, "collection");
        requireZeroOrPositive(maximum, "maximum");
        requireName(name);

        return requireLessThanOrEqual(collection, Collection::size, maximum, name, "size");
    }

    /**
     * Return the given collection, if size is the given size; otherwise, throw an IllegalArgumentException.
     *
     * @param  collection               the given collection
     * @param  size                     the given size
     * @param  name                     the name of the given collection
     * @param  <TYPE>                   the type of the collection element
     * @param  <COLLECTION>             the type of the collection
     * @return                          the given collection, if size is the given size
     * @throws NullPointerException     if collection is null
     * @throws IllegalArgumentException if collection size is not the given size
     * @throws IllegalArgumentException if size is not zero or positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <TYPE, COLLECTION extends Collection<TYPE>> COLLECTION requireSize(
            final COLLECTION collection,
            final int size,
            final String name)
    {
        requireNonNull(collection, "collection");
        requireZeroOrPositive(size, "size");
        requireName(name);

        return requireEqual(collection, Collection::size, size, name, "size");
    }

    /**
     * Return the given collection, if size is greater than or equal to the given minimum; otherwise, throw an
     * IllegalArgumentException.
     *
     * @param  collection               the given collection
     * @param  minimum                  the given minimum
     * @param  name                     the name of the given collection
     * @param  <TYPE>                   the type of the collection element
     * @param  <COLLECTION>             the type of the collection
     * @return                          the given collection, if size is greater than or equal to the given minimum
     * @throws NullPointerException     if collection is null
     * @throws IllegalArgumentException if collection size is not greater than or equal to the given minimum
     * @throws IllegalArgumentException if minimum is not zero or positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <TYPE, COLLECTION extends Collection<TYPE>> COLLECTION requireSizeGreaterThanOrEqual(
            final COLLECTION collection,
            final int minimum,
            final String name)
    {
        requireNonNull(collection, "collection");
        requireZeroOrPositive(minimum, "minimum");
        requireName(name);

        return requireGreaterThanOrEqual(collection, Collection::size, minimum, name, "size");
    }

    /**
     * Return the given collection, if size is greater than the given minimum; otherwise, throw an IllegalArgumentException.
     *
     * @param  collection               the given collection
     * @param  minimum                  the given minimum
     * @param  name                     the name of the given collection
     * @param  <TYPE>                   the type of the collection element
     * @param  <COLLECTION>             the type of the collection
     * @return                          the given collection, if size is greater than the given minimum
     * @throws NullPointerException     if collection is null
     * @throws IllegalArgumentException if collection size is not greater than the given minimum
     * @throws IllegalArgumentException if minimum is not zero or positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <TYPE, COLLECTION extends Collection<TYPE>> COLLECTION requireSizeGreaterThan(
            final COLLECTION collection,
            final int minimum,
            final String name)
    {
        requireNonNull(collection, "collection");
        requireZeroOrPositive(minimum, "minimum");
        requireName(name);

        return requireGreaterThan(collection, Collection::size, minimum, name, "size");
    }

    /**
     * Return the given collection, if size is between the given minimum and the given maximum; otherwise, throw an
     * IllegalArgumentException.
     *
     * @param  collection               the given collection
     * @param  minimum                  the given minimum
     * @param  maximum                  the given maximum
     * @param  name                     the name of the given collection
     * @param  <TYPE>                   the type of the collection element
     * @param  <COLLECTION>             the type of the collection
     * @return                          the given collection, if size is between the given minimum and the given maximum
     * @throws NullPointerException     if collection is null
     * @throws IllegalArgumentException if collection size is not between the given minimum and the given maximum
     * @throws IllegalArgumentException if minimum is not zero or positive
     * @throws IllegalArgumentException if maximum is not zero or positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <TYPE, COLLECTION extends Collection<TYPE>> COLLECTION requireSizeExclusive(
            final COLLECTION collection,
            final int minimum,
            final int maximum,
            final String name)
    {
        requireNonNull(collection, "collection");
        requireZeroOrPositive(minimum, "minimum");
        requireZeroOrPositive(maximum, "maximum");
        requireName(name);

        return requireBoundExclusive(collection, Collection::size, minimum, maximum, name, "size");
    }

    /**
     * Return the given collection, if size is between the given minimum and the given maximum; otherwise, throw an
     * IllegalArgumentException.
     *
     * @param  collection               the given collection
     * @param  minimum                  the given minimum
     * @param  maximum                  the given maximum
     * @param  name                     the name of the given collection
     * @param  <TYPE>                   the type of the collection element
     * @param  <COLLECTION>             the type of the collection
     * @return                          the given collection, if size is between the given minimum and the given maximum
     * @throws NullPointerException     if collection is null
     * @throws IllegalArgumentException if collection size is not between the given minimum and the given maximum
     * @throws IllegalArgumentException if minimum is not zero or positive
     * @throws IllegalArgumentException if maximum is not zero or positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <TYPE, COLLECTION extends Collection<TYPE>> COLLECTION requireSize(
            final COLLECTION collection,
            final int minimum,
            final int maximum,
            final String name)
    {
        requireNonNull(collection, "collection");
        requireZeroOrPositive(minimum, "minimum");
        requireZeroOrPositive(maximum, "maximum");
        requireName(name);

        return requireBoundInclusive(collection, Collection::size, minimum, maximum, name, "size");
    }

    /**
     * Return the given collection, if size is between the given minimum and the given maximum; otherwise, throw an
     * IllegalArgumentException.
     *
     * @param  collection               the given collection
     * @param  minimum                  the given minimum
     * @param  maximum                  the given maximum
     * @param  name                     the name of the given collection
     * @param  <TYPE>                   the type of the collection element
     * @param  <COLLECTION>             the type of the collection
     * @return                          the given collection, if size is between the given minimum and the given maximum
     * @throws NullPointerException     if collection is null
     * @throws IllegalArgumentException if collection size is not between the given minimum and the given maximum
     * @throws IllegalArgumentException if minimum is not zero or positive
     * @throws IllegalArgumentException if maximum is not zero or positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <TYPE, COLLECTION extends Collection<TYPE>> COLLECTION requireSizeMinimumExclusiveMaximumInclusive(
            final COLLECTION collection,
            final int minimum,
            final int maximum,
            final String name)
    {
        requireNonNull(collection, "collection");
        requireZeroOrPositive(minimum, "minimum");
        requireZeroOrPositive(maximum, "maximum");
        requireName(name);

        return requireBoundMinimumExclusiveMaximumInclusive(collection, Collection::size, minimum, maximum, name, "size");
    }

    /**
     * Return the given collection, if size is between the given minimum and the given maximum; otherwise, throw an
     * IllegalArgumentException.
     *
     * @param  collection               the given collection
     * @param  minimum                  the given minimum
     * @param  maximum                  the given maximum
     * @param  name                     the name of the given collection
     * @param  <TYPE>                   the type of the collection element
     * @param  <COLLECTION>             the type of the collection
     * @return                          the given collection, if size is between the given minimum and the given maximum
     * @throws NullPointerException     if collection is null
     * @throws IllegalArgumentException if collection size is not between the given minimum and the given maximum
     * @throws IllegalArgumentException if minimum is not zero or positive
     * @throws IllegalArgumentException if maximum is not zero or positive
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <TYPE, COLLECTION extends Collection<TYPE>> COLLECTION requireSizeMinimumInclusiveMaximumExclusive(
            final COLLECTION collection,
            final int minimum,
            final int maximum,
            final String name)
    {
        requireNonNull(collection, "collection");
        requireZeroOrPositive(minimum, "minimum");
        requireZeroOrPositive(maximum, "maximum");
        requireName(name);

        return requireBoundMinimumInclusiveMaximumExclusive(collection, Collection::size, minimum, maximum, name, "size");
    }

    private static <T1, T2, T3> Stream<T3> zip(
            final Stream<T1> stream1,
            final Stream<T2> stream2,
            final BiFunction<T1, T2, T3> biFunction)
    {
        return stream(spliteratorUnknownSize(new Iterator<>()
        {
            private final Iterator<T1> iterator1 = stream1.sequential().iterator();
            private final Iterator<T2> iterator2 = stream2.sequential().iterator();

            @Override
            public boolean hasNext()
            {
                return iterator1.hasNext() && iterator2.hasNext();
            }

            @Override
            public T3 next()
            {
                return biFunction.apply(iterator1.next(), iterator2.next());
            }
        }, ORDERED), false);
    }

    private static <TYPE, COLLECTION extends Collection<TYPE>> Stream<Optional<Entry<Integer, RuntimeException>>> requireCollection(
            final COLLECTION collection,
            final Function<TYPE, TYPE> require)
    {
        return zip(iterate(0, i -> i + 1), collection.stream(), Map::entry).map(entry ->
        {
            try
            {
                require.apply(entry.getValue());
                return empty();
            }
            catch (final RuntimeException runtimeException)
            {
                return of(entry(entry.getKey(), runtimeException));
            }
        });
    }

    private static String requireMessage(
            final Stream<Optional<Entry<Integer, RuntimeException>>> stream)
    {
        return stream
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(entry -> String.format("%s: %s", entry.getKey(), entry.getValue().getMessage()))
                .collect(Collectors.joining(lineSeparator()));
    }

    private static IllegalArgumentException requireForAllException(
            final String parameterName,
            final String fieldName,
            final Stream<Optional<Entry<Integer, RuntimeException>>> stream)
    {
        return new IllegalArgumentException(format("Every %s of %s must meet the requirement:%n%s",
                fieldName,
                parameterName,
                requireMessage(stream)));
    }

    private static IllegalArgumentException requireThereExistsException(
            final String parameterName,
            final String fieldName,
            final Stream<Optional<Entry<Integer, RuntimeException>>> stream)
    {
        return new IllegalArgumentException(format("At least one %s of %s must exist that meets the requirement:%n%s",
                fieldName,
                parameterName,
                requireMessage(stream)));
    }

    /**
     * Return the given collection, if all elements meet the given requirement.
     *
     * @param  collection               the given collection
     * @param  require                  the given requirement
     * @param  name                     the name of the given collection
     * @param  <TYPE>                   the type of the collection element
     * @param  <COLLECTION>             the type of the collection
     * @return                          the given collection, if all elements meet the given requirement.
     * @throws NullPointerException     if collection is null
     * @throws NullPointerException     if require is null
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <TYPE, COLLECTION extends Collection<TYPE>> COLLECTION requireForAll(
            final COLLECTION collection,
            final Function<TYPE, TYPE> require,
            final String name)
    {
        requireNonNull(collection, "collection");
        requireNonNull(require, "require");
        requireName(name);

        return requireForAll(collection, identity(), require, name, "element");
    }

    /**
     * Return the given collection, if all elements of the derivative collection meet the given requirement.
     *
     * @param  collection               the given collection
     * @param  get                      the function for the derivative collection
     * @param  require                  the given requirement
     * @param  parameterName            the name of the given collection
     * @param  fieldName                the name of the derivative collection
     * @param  <T1>                     the type of the collection
     * @param  <T2>                     the type of the derivative collection element
     * @param  <COLLECTION>             the type of the derivative collection
     * @return                          the given collection, if all elements meet the given requirement.
     * @throws NullPointerException     if collection is null
     * @throws NullPointerException     if get is null
     * @throws NullPointerException     if require is null
     * @throws NullPointerException     if parameterName is null
     * @throws IllegalArgumentException if parameterName is blank
     * @throws NullPointerException     if fieldName is null
     * @throws IllegalArgumentException if fieldName is blank
     */
    public static <T1, T2, COLLECTION extends Collection<T2>> T1 requireForAll(
            final T1 collection,
            final Function<T1, COLLECTION> get,
            final Function<T2, T2> require,
            final String parameterName,
            final String fieldName)
    {
        requireNonNull(collection, "collection");
        requireNonNull(get, "get");
        requireNonNull(require, "require");
        requireName(parameterName);
        requireName(fieldName);

        final Supplier<Stream<Optional<Entry<Integer, RuntimeException>>>> stream = () -> requireCollection(get.apply(collection), require);

        if (stream.get().anyMatch(Optional::isPresent))
        {
            throw requireForAllException(parameterName, fieldName, stream.get());
        }

        return collection;
    }

    /**
     * Return the given collection, if at least one element meets the given requirement.
     *
     * @param  collection               the given collection
     * @param  require                  the given requirement
     * @param  name                     the name of the given collection
     * @param  <TYPE>                   the type of the collection element
     * @param  <COLLECTION>             the type of the collection
     * @return                          the given collection, if at least one element meets the given requirement.
     * @throws NullPointerException     if collection is null
     * @throws NullPointerException     if require is null
     * @throws NullPointerException     if name is null
     * @throws IllegalArgumentException is name is blank
     */
    public static <TYPE, COLLECTION extends Collection<TYPE>> COLLECTION requireThereExists(
            final COLLECTION collection,
            final Function<TYPE, TYPE> require,
            final String name)
    {
        requireNonNull(collection, "collection");
        requireNonNull(require, "require");
        requireName(name);

        return requireThereExists(collection, identity(), require, name, "element");
    }

    /**
     * Return the given collection, if at least one element of the derivative collection meets the given requirement.
     *
     * @param  collection               the given collection
     * @param  get                      the function for the derivative collection
     * @param  require                  the given requirement
     * @param  parameterName            the name of the given collection
     * @param  fieldName                the name of the derivative collection
     * @param  <T1>                     the type of the collection
     * @param  <T2>                     the type of the derivative collection element
     * @param  <COLLECTION>             the type of the derivative collection
     * @return                          the given collection, if at least one element meets the given requirement.
     * @throws NullPointerException     if collection is null
     * @throws NullPointerException     if get is null
     * @throws NullPointerException     if require is null
     * @throws NullPointerException     if parameterName is null
     * @throws IllegalArgumentException if parameterName is blank
     * @throws NullPointerException     if fieldName is null
     * @throws IllegalArgumentException if fieldName is blank
     */
    public static <T1, T2, COLLECTION extends Collection<T2>> T1 requireThereExists(
            final T1 collection,
            final Function<T1, COLLECTION> get,
            final Function<T2, T2> require,
            final String parameterName,
            final String fieldName)
    {
        requireNonNull(collection, "collection");
        requireNonNull(get, "get");
        requireNonNull(require, "require");
        requireName(parameterName);
        requireName(fieldName);

        final Supplier<Stream<Optional<Entry<Integer, RuntimeException>>>> stream = () -> requireCollection(get.apply(collection), require);

        if (stream.get().allMatch(Optional::isPresent))
        {
            throw requireThereExistsException(parameterName, fieldName, stream.get());
        }

        return collection;
    }
}
