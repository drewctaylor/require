[![Workflow Maven Package](https://github.com/drewctaylor/require/workflows/workflow-maven-package/badge.svg)](https://github.com/drewctaylor/require/workflows/workflow-maven-package/badge.svg)
[![Code Coverage](https://codecov.io/gh/drewctaylor/require/branch/master/graph/badge.svg)](https://codecov.io/gh/drewctaylor/require)

# Require

This library provides methods that allow you to require that arguments to a method satisfy requirements; for example, 
you may require that

* an argument is not null:

  `requireNonNull(null, "parameter")`

* a `String` argument is not blank: 

  `requireNonBlank(" ", "string")`

* a `String` argument matches a given regular expression: 

  `requireMatch("b", "a*", "string")`
  
* a `String` argument represents an integer:
 
  `requireInteger("1.1", "string")`

* a `String` argument has a length greater than a given length: 

  `requireLengthGreaterThan("", 1, "string")`

* a `String` argument has a length that is within bounds: 

  `requireLengthExclusive("123", 0, 2, "string")`

* a numeric argument is positive:

  `requirePositive(-1, "number")`
  
* a `Comparable` argument is greater than a given value:  

  `requireGreaterThan('a', 'b', "letter")`
  
* a `Comparable` argument is within bounds:  

  `requireBoundExclusive('d', 'a', 'c',"letter")`
  
* a `Collection` argument is not empty:  

  `requireSizeGreaterThan(of(1, 2), 2, "list")`

* a `Collection` argument has a size greater than a given size: 

  `requireForAll(of(1, 2, 3, 4), element -> requireGreaterThan(element, 2, "number"), "list")`

* a `Collection` argument has a size that is within bounds: 

  `requireSizeExclusive(of(1, 2), 0, 1, "list")`
  
* a `Collection` argument has elements that satisfy requirements:

  ```
  requireForAll(of(1, 2, 3, 4), element -> requireGreaterThan(element, 2, "number"), "list");
  requireThereExists(of(1, 2, 3, 4), element -> requireGreaterThan(element, 4, "number"), "list");
  ```
  
If the argument does not satisfy the requirement, the method throws an informative runtime exception. In the case of
`requireNonNull`, the runtime exception is a `NullPointerException`; in all other cases, the runtime exception is an 
`IllegalArgumentException`. Examples of the messages include:

* if `requireNonNull(null, "parameter")` fails: 

  `parameter must be non-null.`

* if `requireNonBlank(" ", "string")` fails: 

  `string must be non-blank; it is ' '.`

* if `requireMatch("b", "a*", "string")` fails: 

  `string must match 'a*'; it is 'b'.`

* if `requireInteger("1.1", "number")` fails: 

  `number must be a java.lang.Integer; it is '1.1'.`

* if `requireLengthGreaterThan("", 1, "string")` fails: 

  `string length must be greater than '1'; it is '0'.`

* if `requireLengthExclusive("123", 0, 2, "string")` fails: 

  `string length must be greater than '0' and less than '2'; it is '3'.`

* if `requirePositive(-1, "number")` fails: 

  `number must be positive; it is '-1'.`

* if `requireGreaterThan('a', 'b', "letter")` fails: 

  `letter must be greater than 'b'; it is 'a'.`

* if `requireBoundExclusive('d', 'a', 'c',"letter")` fails: 

  `letter must be greater than 'a' and less than 'c'; it is 'd'.`

* if `requireNonEmpty(emptyList(), "list")` fails: 

  `list must be non-empty.`

* if `requireSizeGreaterThan(of(1, 2), 2, "list")` fails: 

  `list size must be greater than '2'; it is '2'.`

* if `requireSizeExclusive(of(1, 2), 0, 1, "list")` fails: 

  `list size must be greater than '0' and less than '1'; it is '2'.`

* if `requireForAll(of(1, 2, 3, 4), element -> requireGreaterThan(element, 2, "number"), "list")` fails: 

  ```
  Every element of list must meet the requirement:
  0: number must be greater than '2'; it is '1'.
  1: number must be greater than '2'; it is '2'.
  ```

* if `requireThereExists(of(1, 2, 3, 4), element -> requireGreaterThan(element, 4, "number"), "list")` fails: 

  ```
  At least one element of list must exist that meets the requirement:
  0: number must be greater than '4'; it is '1'.
  1: number must be greater than '4'; it is '2'.
  2: number must be greater than '4'; it is '3'.
  3: number must be greater than '4'; it is '4'.
  ```
  
## To Use Require

To use require:

1) Update your `~/.m2/settings.xml` to include your github username or github email address and your [github personal access token](https://help.github.com/en/github/authenticating-to-github/creating-a-personal-access-token-for-the-command-line).

    For example:

    ```xml
    <settings>
        <servers>
            <server>
                <id>github</id>
                <username>your-github-username-or-email-address</username>
                <password>your-github-personal-access-token</password>
            </server>
        </servers>
    </settings>
    ```

2) Update your `pom.xml` to include a reference to the plugin repository.

    For example:

    ```xml
    <pluginRepositories>
        <pluginRepository>
            <id>github</id>
            <name>GitHub Packages</name>
            <url>https://maven.pkg.github.com/drewctaylor/require</url>
        </pluginRepository>
    </pluginRepositories>
    ```

3) Update your `pom.xml` to include the plugin. 

    For example:
    
    ```xml
    <dependencies>
        <dependency>
            <groupId>io.github.drewctaylor</groupId>
            <artifactId>require</artifactId>
            <version>${project.version}</version>
        </dependency>
    </dependencies>
    ```
