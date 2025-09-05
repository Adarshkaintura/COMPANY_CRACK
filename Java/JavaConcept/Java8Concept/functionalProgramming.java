/**
 * FunctionalProgrammingDemo.java
 *
 * This file explains Functional Programming in Java 8,
 * Functional Interfaces, why they came, their need, rules,
 * and examples with comments for better understanding.
 */

import java.util.function.*; // Java 8 predefined functional interfaces

public class FunctionalProgrammingDemo {

    /**
     * ---------------------------------------------------------------
     * 1. WHY FUNCTIONAL PROGRAMMING IN JAVA 8?
     * ---------------------------------------------------------------
     * - Before Java 8, Java was purely Object-Oriented and imperative.
     * - It lacked concise ways to represent "behavior" as data.
     * - Other languages (Scala, Kotlin, etc.) already supported functional programming.
     * - Java 8 introduced:
     *      - Lambda expressions (->)
     *      - Functional Interfaces
     *      - Streams API
     *      - Method references
     *
     * -> This makes code more readable, less boilerplate, 
     *    and allows parallel and declarative programming.
     */

    // Traditional way (before Java 8) - Anonymous inner class
    interface OldPrinter {
        void print(String msg);
    }

    /**
     * ---------------------------------------------------------------
     * 2. FUNCTIONAL INTERFACE
     * ---------------------------------------------------------------
     * - A Functional Interface is an interface with ONLY ONE abstract method (SAM: Single Abstract Method).
     * - It can have multiple default or static methods (Java 8 feature).
     * - Used as the base for Lambda expressions and method references.
     *
     * Examples: Runnable, Callable, Comparator, Predicate, Consumer, Supplier, Function.
     */

    @FunctionalInterface
    interface MyFunctionalInterface {
        void doTask(); // Single Abstract Method (SAM)

        // Allowed: default method
        default void log(String msg) {
            System.out.println("LOG: " + msg);
        }

        // Allowed: static method
        static void info() {
            System.out.println("Functional Interface Info Method");
        }
    }

    /**
     * ---------------------------------------------------------------
     * 3. HOW TO CREATE A FUNCTIONAL INTERFACE
     * ---------------------------------------------------------------
     *  - Method 1: Define an interface with one abstract method.
     *  - Method 2: Use @FunctionalInterface annotation to force compiler check.
     */

    // Example without annotation (still functional interface)
    interface AnotherFunctionalInterface {
        int calculate(int a, int b);
    }

    // Example with @FunctionalInterface annotation
    @FunctionalInterface
    interface Addable {
        int add(int a, int b);
    }

    /**
     * ---------------------------------------------------------------
     * 4. USING FUNCTIONAL INTERFACE WITH LAMBDA EXPRESSIONS
     * ---------------------------------------------------------------
     * - Instead of creating a class or anonymous inner class,
     *   we can use lambda expressions to implement Functional Interfaces.
     */

    public static void lambdaExamples() {
        // Example 1: Old way
        OldPrinter printer1 = new OldPrinter() {
            public void print(String msg) {
                System.out.println("Old way: " + msg);
            }
        };
        printer1.print("Hello Java 7!");

        // Example 2: Using Lambda (Java 8 way)
        OldPrinter printer2 = (msg) -> System.out.println("Lambda way: " + msg);
        printer2.print("Hello Java 8!");
    }

    /**
     * ---------------------------------------------------------------
     * 5. PREDEFINED FUNCTIONAL INTERFACES (java.util.function package)
     * ---------------------------------------------------------------
     * - Predicate<T>   -> takes T, returns boolean
     * - Consumer<T>    -> takes T, returns void
     * - Supplier<T>    -> takes nothing, returns T
     * - Function<T,R>  -> takes T, returns R
     */

    public static void predefinedFunctionalInterfaces() {
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("Is 10 even? " + isEven.test(10));

        Consumer<String> consumer = msg -> System.out.println("Consumed: " + msg);
        consumer.accept("Hello World!");

        Supplier<Double> supplier = () -> Math.random();
        System.out.println("Random number: " + supplier.get());

        Function<String, Integer> lengthFunc = str -> str.length();
        System.out.println("Length of 'Java': " + lengthFunc.apply("Java"));
    }

    /**
     * ---------------------------------------------------------------
     * 6. RULES IN FUNCTIONAL INTERFACES
     * ---------------------------------------------------------------
     * - Must have exactly one abstract method.
     * - Can have any number of default or static methods.
     * - Can extend other interfaces only if still one SAM remains.
     * - @FunctionalInterface is optional but recommended.
     */

    public static void main(String[] args) {
        // 1. Lambda usage
        lambdaExamples();

        // 2. Functional interface with lambda
        Addable adder = (a, b) -> a + b;
        System.out.println("Sum: " + adder.add(5, 7));

        // 3. Predefined functional interfaces
        predefinedFunctionalInterfaces();

        // 4. Using default method in functional interface
        MyFunctionalInterface f = () -> System.out.println("Task executed!");
        f.doTask();
        f.log("Task completed");

        // 5. Static method in interface
        MyFunctionalInterface.info();
    }
}
