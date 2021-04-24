[![Workflow Maven Deploy](https://github.com/drewctaylor/require/workflows/workflow-maven-deploy/badge.svg)](https://github.com/drewctaylor/require/workflows/workflow-maven-deploy/badge.svg)
[![Code Coverage](https://codecov.io/gh/drewctaylor/require/branch/trunk/graph/badge.svg)](https://codecov.io/gh/drewctaylor/require)

# Require

This library provides methods that allow you to ensure that arguments to a method satisfy requirements. If an argument 
does not satisfy a requirement, the method throws an informative runtime exception. In the case of `requireNonNull`, the 
runtime exception is a `NullPointerException`; in all other cases, the runtime exception is an `IllegalArgumentException`. 
Examples of the messages include:

* if `requireNonNull(null, "parameter")` fails: 

  `parameter must be non-null.`

* if `requireNonEmpty("", "string")` fails: 

  `string must be non-empty.`

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

* if `requireBoundExclusive('d', 'a', 'c', "letter")` fails: 

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
    
## To Use

1) Update the `pom.xml` to include a reference to the sonatype staging and snapshot repositories.

   For example:

    ```xml
    <repositories>
        <repository>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
            <id>ossrh</id>
            <url>https://oss.sonatype.org/service/local/staging/deploy/maven2</url>
        </repository>
        <repository>
            <releases>
                <enabled>false</enabled>
            </releases>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
            <id>ossrh-snapshot</id>
            <url>https://oss.sonatype.org/content/repositories/snapshots</url>
        </repository>
    </repositories>
    ```

2) Update the `pom.xml` to include the library. 

    For example:
    
    ```xml
    <dependencies>
        <dependency>
            <groupId>io.github.drewctaylor</groupId>
            <artifactId>require</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
    </dependencies>
    ```
