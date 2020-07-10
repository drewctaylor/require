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

public final class RequireCollection
{
    private RequireCollection()
    {
    }

    public static <TYPE, COLLECTION extends Collection<TYPE>> COLLECTION requireEmpty(
            final COLLECTION value,
            final String name)
    {
        return require(value.isEmpty(), value, format("%s must be empty; it was '%s'.", name, value.size()));
    }

    public static <TYPE, COLLECTION extends Collection<TYPE>> COLLECTION requireNonEmpty(
            final COLLECTION value,
            final String name)
    {
        return require(!value.isEmpty(), value, format("%s must be non-empty.", name));
    }

    public static <TYPE, COLLECTION extends Collection<TYPE>> COLLECTION requireSizeLessThan(
            final COLLECTION value,
            final int maximum,
            final String name)
    {
        return requireLessThan(value, Collection::size, maximum, name, "size");
    }

    public static <TYPE, COLLECTION extends Collection<TYPE>> COLLECTION requireSizeLessThanOrEqual(
            final COLLECTION value,
            final int maximum,
            final String name)
    {
        return requireLessThanOrEqual(value, Collection::size, maximum, name, "size");
    }

    public static <TYPE, COLLECTION extends Collection<TYPE>> COLLECTION requireSize(
            final COLLECTION value,
            final int length,
            final String name)
    {
        return requireEqual(value, Collection::size, length, name, "size");
    }

    public static <TYPE, COLLECTION extends Collection<TYPE>> COLLECTION requireSizeGreaterThanOrEqual(
            final COLLECTION value,
            final int minimum,
            final String name)
    {
        return requireGreaterThanOrEqual(value, Collection::size, minimum, name, "size");
    }

    public static <TYPE, COLLECTION extends Collection<TYPE>> COLLECTION requireSizeGreaterThan(
            final COLLECTION value,
            final int minimum,
            final String name)
    {
        return requireGreaterThan(value, Collection::size, minimum, name, "size");
    }

    public static <TYPE, COLLECTION extends Collection<TYPE>> COLLECTION requireSizeExclusive(
            final COLLECTION value,
            final int minimumExclusive,
            final int maximumExclusive,
            final String name)
    {
        return requireBoundExclusive(value, Collection::size, minimumExclusive, maximumExclusive, name, "size");
    }

    public static <TYPE, COLLECTION extends Collection<TYPE>> COLLECTION requireSizeInclusive(
            final COLLECTION value,
            final int minimumInclusive,
            final int maximumInclusive,
            final String name)
    {
        return requireBoundInclusive(value, Collection::size, minimumInclusive, maximumInclusive, name, "size");
    }

    public static <TYPE, COLLECTION extends Collection<TYPE>> COLLECTION requireSizeMinimumExclusiveMaximumInclusive(
            final COLLECTION value,
            final int minimumExclusive,
            final int maximumInclusive,
            final String name)
    {
        return requireBoundMinimumExclusiveMaximumInclusive(value, Collection::size, minimumExclusive, maximumInclusive, name, "size");
    }

    public static <TYPE, COLLECTION extends Collection<TYPE>> COLLECTION requireSizeMinimumInclusiveMaximumExclusive(
            final COLLECTION value,
            final int minimumInclusive,
            final int maximumExclusive,
            final String name)
    {
        return requireBoundMinimumInclusiveMaximumExclusive(value, Collection::size, minimumInclusive, maximumExclusive, name, "size");
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
            final COLLECTION value,
            final Function<TYPE, TYPE> require)
    {
        return zip(iterate(0, i -> i + 1), value.stream(), Map::entry).map(entry ->
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

    public static <TYPE, COLLECTION extends Collection<TYPE>> COLLECTION requireForAll(
            final COLLECTION value,
            final Function<TYPE, TYPE> require,
            final String name)
    {
        return requireForAll(value, identity(), require, name, "element");
    }

    public static <T1, T2, COLLECTION extends Collection<T2>> T1 requireForAll(
            final T1 value,
            final Function<T1, COLLECTION> get,
            final Function<T2, T2> require,
            final String parameterName,
            final String fieldName)
    {
        final Supplier<Stream<Optional<Entry<Integer, RuntimeException>>>> stream = () -> requireCollection(get.apply(value), require);

        if (stream.get().anyMatch(Optional::isPresent))
        {
            throw requireForAllException(parameterName, fieldName, stream.get());
        }

        return value;
    }

    public static <TYPE, COLLECTION extends Collection<TYPE>> COLLECTION requireThereExists(
            final COLLECTION value,
            final Function<TYPE, TYPE> require,
            final String name)
    {
        return requireThereExists(value, identity(), require, name, "element");
    }

    public static <T1, T2, COLLECTION extends Collection<T2>> T1 requireThereExists(
            final T1 value,
            final Function<T1, COLLECTION> get,
            final Function<T2, T2> require,
            final String parameterName,
            final String fieldName)
    {
        final Supplier<Stream<Optional<Entry<Integer, RuntimeException>>>> stream = () -> requireCollection(get.apply(value), require);

        if (stream.get().allMatch(Optional::isPresent))
        {
            throw requireThereExistsException(parameterName, fieldName, stream.get());
        }

        return value;
    }
}
