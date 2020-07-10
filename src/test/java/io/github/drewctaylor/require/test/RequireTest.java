package io.github.drewctaylor.require.test;

import org.junit.jupiter.api.Test;

import static io.github.drewctaylor.require.Require.require;
import static io.github.drewctaylor.require.Require.requireNonNull;
import static io.github.drewctaylor.require.Require.requireNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class RequireTest
{
    @Test
    void testRequireException()
    {
        assertThrows(NullPointerException.class, () -> require(true, null, (RuntimeException) null));

        assertThrows(NullPointerException.class, () -> require(false, null, new NullPointerException("")));

        // noinspection RedundantCast
        assertNull(require(true, (Object) null, new NullPointerException("")));
    }

    @Test
    void testRequireMessage()
    {
        assertThrows(NullPointerException.class, () -> require(true, null, (String) null));
        assertThrows(IllegalArgumentException.class, () -> require(true, null, ""));
        assertThrows(IllegalArgumentException.class, () -> require(true, null, " "));

        assertThrows(IllegalArgumentException.class, () -> require(false, null, "name"));

        // noinspection RedundantCast
        assertNull(require(true, (Object) null, "name"));
    }

    @Test
    void testRequireNonNull()
    {
        final Object invalid = null;
        final var valid = new Object();

        assertThrows(NullPointerException.class, () -> requireNonNull(valid, null));
        assertThrows(IllegalArgumentException.class, () -> requireNonNull(valid, ""));
        assertThrows(IllegalArgumentException.class, () -> requireNonNull(valid, " "));

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
        assertThrows(IllegalArgumentException.class, () -> requireNonNull(valid, " "));

        assertThrows(IllegalArgumentException.class, () -> requireNull(invalid, "name"));
        assertEquals(valid, requireNull(valid, "name"));
    }
}
