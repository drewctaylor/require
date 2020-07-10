package io.github.drewctaylor.require.test;

import io.github.drewctaylor.require.RequireString;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;
import java.util.regex.Pattern;

import static io.github.drewctaylor.require.Require.requireNonNull;
import static io.github.drewctaylor.require.Require.requireNull;
import static io.github.drewctaylor.require.RequireBound.requireBoundExclusive;
import static io.github.drewctaylor.require.RequireBound.requireGreaterThan;
import static io.github.drewctaylor.require.RequireCollection.requireForAll;
import static io.github.drewctaylor.require.RequireCollection.requireNonEmpty;
import static io.github.drewctaylor.require.RequireCollection.requireSizeExclusive;
import static io.github.drewctaylor.require.RequireCollection.requireSizeGreaterThan;
import static io.github.drewctaylor.require.RequireCollection.requireThereExists;
import static io.github.drewctaylor.require.RequireNumberInteger.requireInteger;
import static io.github.drewctaylor.require.RequireNumberInteger.requirePositive;
import static io.github.drewctaylor.require.RequireString.requireEmpty;
import static io.github.drewctaylor.require.RequireString.requireLengthExclusive;
import static io.github.drewctaylor.require.RequireString.requireLengthGreaterThan;
import static io.github.drewctaylor.require.RequireString.requireMatch;
import static io.github.drewctaylor.require.RequireString.requireNonBlank;
import static java.util.Collections.emptyList;
import static java.util.List.of;

final class ExampleTest
{
    @SuppressWarnings("MethodWithMultipleReturnPoints")
    private static <TYPE> String messageFor(
            final Supplier<TYPE> supplier)
    {
        try
        {
            supplier.get();
            return "";
        }
        catch (final RuntimeException runtimeException)
        {
            return runtimeException.getMessage();
        }
    }

    @SuppressWarnings(
    { "UseOfSystemOutOrSystemErr", "JUnitTestMethodWithNoAssertions" })
    @Test
    void testExample()
    {
        System.out.printf("* if `requireNonNull(null, \"parameter\")` fails: %n%n  `%s`%n%n", messageFor(() -> requireNonNull(null, "parameter")));
        System.out.printf("* if `requireNonEmpty(\"\", \"string\")` fails: %n%n  `%s`%n%n", messageFor(() -> RequireString.requireNonEmpty("", "string")));
        System.out.printf("* if `requireNonBlank(\" \", \"string\")` fails: %n%n  `%s`%n%n", messageFor(() -> requireNonBlank(" ", "string")));
        System.out.printf("* if `requireMatch(\"b\", \"a*\", \"string\")` fails: %n%n  `%s`%n%n", messageFor(() -> requireMatch("b", Pattern.compile("a*"), "string")));
        System.out.printf("* if `requireInteger(\"1.1\", \"number\")` fails: %n%n  `%s`%n%n", messageFor(() -> requireInteger("1.1", "number")));
        System.out.printf("* if `requireLengthGreaterThan(\"\", 1, \"string\")` fails: %n%n  `%s`%n%n", messageFor(() -> requireLengthGreaterThan("", 1, "string")));
        System.out.printf("* if `requireLengthExclusive(\"123\", 0, 2, \"string\")` fails: %n%n  `%s`%n%n", messageFor(() -> requireLengthExclusive("123", 0, 2, "string")));
        System.out.printf("* if `requirePositive(-1, \"number\")` fails: %n%n  `%s`%n%n", messageFor(() -> requirePositive(-1, "number")));
        System.out.printf("* if `requireGreaterThan('a', 'b', \"letter\")` fails: %n%n  `%s`%n%n", messageFor(() -> requireGreaterThan('a', 'b', "letter")));
        System.out.printf("* if `requireBoundExclusive('d', 'a', 'c', \"letter\")` fails: %n%n  `%s`%n%n", messageFor(() -> requireBoundExclusive('d', 'a', 'c', "letter")));
        System.out.printf("* if `requireNonEmpty(emptyList(), \"list\")` fails: %n%n  `%s`%n%n", messageFor(() -> requireNonEmpty(emptyList(), "list")));
        System.out.printf("* if `requireSizeGreaterThan(of(1, 2), 2, \"list\")` fails: %n%n  `%s`%n%n", messageFor(() -> requireSizeGreaterThan(of(1, 2), 2, "list")));
        System.out.printf("* if `requireSizeExclusive(of(1, 2), 0, 1, \"list\")` fails: %n%n  `%s`%n%n", messageFor(() -> requireSizeExclusive(of(1, 2), 0, 1, "list")));
        System.out.printf("* if `requireForAll(of(1, 2, 3, 4), element -> requireGreaterThan(element, 2, \"number\"), \"list\")` fails: %n%n  ```%n  %s%n  ```%n%n", messageFor(() -> requireForAll(of(1, 2, 3, 4), element -> requireGreaterThan(element, 2, "number"), "list")).replaceAll("\n", "\n  "));
        System.out.printf("* if `requireThereExists(of(1, 2, 3, 4), element -> requireGreaterThan(element, 4, \"number\"), \"list\")` fails: %n%n  ```%n  %s%n  ```%n%n", messageFor(() -> requireThereExists(of(1, 2, 3, 4), element -> requireGreaterThan(element, 4, "number"), "list")).replaceAll("\n", "\n  "));

        System.out.printf("* if `requireNull(\"\", \"parameter\")` fails: %n%n  `%s`%n%n", messageFor(() -> requireNull("", "parameter")));
        System.out.printf("* if `requireEmpty(\" \", \"string\")` fails: %n%n  `%s`%n%n", messageFor(() -> requireEmpty(" ", "string")));
        System.out.printf("* if `requireBlank(\"string\", \"string\")` fails: %n%n  `%s`%n%n", messageFor(() -> RequireString.requireBlank("string", "string")));
    }
}
