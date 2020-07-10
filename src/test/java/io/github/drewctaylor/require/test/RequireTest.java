package io.github.drewctaylor.require.test;

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static io.github.drewctaylor.require.Require.require;
import static io.github.drewctaylor.require.Require.requireNonNull;
import static io.github.drewctaylor.require.Require.requireNull;
import static io.github.drewctaylor.require.RequireBound.requireBoundExclusive;
import static io.github.drewctaylor.require.RequireBound.requireGreaterThan;
import static io.github.drewctaylor.require.RequireCollection.requireForAll;
import static io.github.drewctaylor.require.RequireCollection.requireNonEmpty;
import static io.github.drewctaylor.require.RequireCollection.requireSizeExclusive;
import static io.github.drewctaylor.require.RequireCollection.requireSizeGreaterThan;
import static io.github.drewctaylor.require.RequireCollection.requireThereExists;
import static io.github.drewctaylor.require.RequireString.requireLengthExclusive;
import static io.github.drewctaylor.require.RequireString.requireLengthGreaterThan;
import static io.github.drewctaylor.require.RequireString.requireMatch;
import static io.github.drewctaylor.require.RequireString.requireNonBlank;
import static io.github.drewctaylor.require.number.RequireInteger.requireInteger;
import static io.github.drewctaylor.require.number.RequireInteger.requirePositive;
import static java.util.Collections.emptyList;
import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class RequireTest
{
    @Test
    void testRequire()
    {
        assertThrows(NullPointerException.class, () -> require(true, (Object) null, (RuntimeException) null));

        assertThrows(NullPointerException.class, () -> require(false, (Object) null, new NullPointerException()));
        assertEquals((Object) null, require(true, (Object) null, new NullPointerException()));

        assertThrows(NullPointerException.class, () -> require(true, (Object) null, (String) null));
        assertThrows(IllegalArgumentException.class, () -> require(true, (Object) null, ""));

        assertThrows(IllegalArgumentException.class, () -> require(false, (Object) null, "name"));
        assertEquals((Object) null, require(true, (Object) null, "name"));
    }

    @Test
    void testRequireNonNull()
    {
        final Object invalid = null;
        final var valid = new Object();

        assertThrows(NullPointerException.class, () -> requireNonNull(valid, null));
        assertThrows(IllegalArgumentException.class, () -> requireNonNull(valid, ""));

        assertThrows(NullPointerException.class, () -> requireNonNull(invalid, "name"));
        assertEquals(valid, requireNonNull(valid, "name"));
    }

    @Test
    void testRequireNull()
    {
        final var invalid = new Object();
        final Object valid = null;

        assertThrows(NullPointerException.class, () -> requireNonNull(valid, null));
        assertThrows(IllegalArgumentException.class, () -> requireNonNull(valid, ""));

        assertThrows(IllegalArgumentException.class, () -> requireNull(invalid, "name"));
        assertEquals(valid, requireNull(valid, "name"));
    }
}
