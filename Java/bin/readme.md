# Functional Interfaces in Java 8 & 9

This document explains **Functional Interfaces** in Java 8 and 9, why they were introduced, their rules, inheritance cases, and predefined interfaces. It follows the structure from the provided table of contents.

---

## 1. What is functional interface (alias SAM)?
- A **Functional Interface** is an interface with exactly **one abstract method** (SAM: Single Abstract Method).
- It may contain **default methods** or **static methods**, but only one abstract method.
- Examples: `Runnable`, `Callable`, `Comparator`, `Predicate`.

---

## 2. Why functional interface?
- Java 8 introduced **Lambda Expressions** and **Method References** to make code concise.
- These features require a **target type** that represents a single behavior — hence **Functional Interfaces**.
- Without functional interfaces, lambdas would not have a type to bind to.

---

## 3. How can we create functional interface?
- Simply declare an interface with one abstract method.

```java
@FunctionalInterface
interface MyFunction {
    void execute();
}
## 4. Identifying correct functional interface?
- A correct functional interface must have **exactly one abstract method** (SAM).
- Default and static methods **do not count**.
- Methods from `java.lang.Object` (like `toString`, `hashCode`, `equals`) are ignored.

```java
@FunctionalInterface
interface CorrectFI {
    void doWork(); // Only one abstract method
    default void log() { System.out.println("Logging..."); } // Allowed
    static void info() { System.out.println("Info..."); }   // Allowed
}
# Java Functional Interfaces README

This README provides an overview of Java Functional Interfaces, starting from compiler considerations to predefined interfaces.

## 5. Compiler Thinking w.r.t Functional Interface

The compiler ensures:

- Only one abstract method exists.
- If `@FunctionalInterface` is used and more than one abstract method exists → compile-time error.
- Lambdas and method references rely on this single abstract method.

```java
@FunctionalInterface
interface WrongFI {
    void m1();
    // void m2();  // ❌ Compiler error if uncommented
}
```

## 6. Two Ways of Creating Functional Interface

### Effective Functional Interface

An interface that naturally has one abstract method.

```java
interface EffectiveFI {
    void run();
}
```

### Forced Functional Interface

An interface marked with `@FunctionalInterface` to enforce the rule at compile time.

```java
@FunctionalInterface
interface ForcedFI {
    void execute();
}
```

## 7. What is `@FunctionalInterface`?

An annotation introduced in Java 8 that:

- Ensures the interface has only one abstract method.
- Provides compile-time checking and serves as documentation.

```java
@FunctionalInterface
interface MyFunctional {
    void process();
}
```

## 8. Why `@FunctionalInterface`?

- Prevents accidental addition of multiple abstract methods.
- Increases readability.
- Helps other programmers recognize the intent quickly.

## 9. How Can We Tell the Compiler and Other Programmers an Interface is a Functional Interface?

- By applying the `@FunctionalInterface` annotation.
- Even without it, the compiler treats interfaces with a single abstract method as functional.
- The annotation makes it explicit and prevents errors.

## 10. Rules in Creating Functional Interface

- Must have exactly one abstract method.
- Can have:
  - Multiple default methods.
  - Multiple static methods.
  - Private methods (Java 9+).
- Can extend other interfaces as long as only one abstract method remains.
- Object methods (e.g., `toString`, `equals`, `hashCode`) don’t count as abstract methods.

## 11. Different Programming Elements Allowed and Not Allowed in Functional Interface & Java 9 Enhancement Effect

| Element                          | Allowed?                  |
|----------------------------------|---------------------------|
| `public static final` variables | ✅ Allowed               |
| `public abstract` methods       | ✅ Only one allowed      |
| `public static` inner classes   | ✅ Allowed               |
| `public default` methods        | ✅ Allowed               |
| `public static` methods         | ✅ Allowed               |
| `private static` & non-static methods (Java 9+) | ✅ Allowed       |
| Object class methods (`toString`, `equals`, `hashCode`) | Do not count as abstract methods |

## 12. Functional Interface with Inheritance Combination

### Case #1: Deriving FI from Other FI

✅ Allowed (still one abstract method).

```java
@FunctionalInterface
interface A { void test(); }

@FunctionalInterface
interface B extends A { } // Still functional
```

### Case #2: Deriving FI from Non-FI

✅ Allowed if only one abstract method is inherited.

```java
interface NonFI { void log(); }

@FunctionalInterface
interface C extends NonFI { } // Functional
```

### Case #3: Deriving FI from Non-FI with Default Method

✅ Allowed since default methods don’t count.

```java
interface NonFIWithDefault {
    default void log() { }
}

@FunctionalInterface
interface D extends NonFIWithDefault {
    void execute();
}
```

### Case #4: Deriving FI from Marker Interface

✅ Allowed (marker has no abstract method).

```java
interface Marker { }

@FunctionalInterface
interface E extends Marker {
    void work();
}
```

### Case #5: Deriving FI from Other FI Without Using `@FunctionalInterface`

✅ Still functional, annotation is optional.

```java
interface F extends A { } // Valid
```

### Case #6: Deriving FI from Multiple FIs

⚠️ Allowed only if exactly one abstract method remains.

```java
@FunctionalInterface
interface G1 { void m1(); }

@FunctionalInterface
interface G2 { default void m2() { } }

@FunctionalInterface
interface G3 extends G1, G2 { } // Valid
```

### Case #7: Deriving FI from One FI and Non-FI

⚠️ Allowed if only one abstract method remains.

```java
interface NonFI2 { default void log() { } }

@FunctionalInterface
interface H extends A, NonFI2 { } // Valid
```

## 13. Generic Functional Interface

Functional interfaces can be generic.

```java
@FunctionalInterface
interface MyFunction<T> {
    T process(T input);
}
```

Example:

```java
MyFunction<String> upper = s -> s.toUpperCase();
System.out.println(upper.process("java")); // JAVA
```

## 14. Predefined Functional Interfaces (`java.util.function` Package)

Java 8 introduced many built-in functional interfaces:

- `Predicate<T>` → takes `T`, returns `boolean`.
- `Consumer<T>` → takes `T`, returns `void`.
- `Supplier<T>` → no input, returns `T`.
- `Function<T,R>` → takes `T`, returns `R`.
- `UnaryOperator<T>` → takes `T`, returns `T`.
- `BinaryOperator<T>` → takes `(T,T)`, returns `T`.

Examples:

```java
Predicate<Integer> isEven = n -> n % 2 == 0;
System.out.println(isEven.test(10)); // true

Consumer<String> printer = s -> System.out.println(s);
printer.accept("Hello World");

Supplier<Double> random = () -> Math.random();
System.out.println(random.get());

Function<String, Integer> length = s -> s.length();
System.out.println(length.apply("Functional Interface"));
```


# Lambda Expressions in Java

## 📌 Introduction

Lambda Expressions were introduced in **Java 8** to bring **functional programming** concepts into Java. They allow us to write cleaner, more concise, and more readable code by eliminating boilerplate when implementing functional interfaces (interfaces with a single abstract method).

Instead of creating anonymous classes, Lambda Expressions let us express instances of functional interfaces in a simple and elegant way.

---

## ✅ Why Use Lambda Expressions?

* **Conciseness** → Reduce boilerplate code compared to anonymous classes.
* **Readability** → Makes the intention of code clearer.
* **Functional Programming** → Enables using functional-style operations like `map()`, `filter()`, and `reduce()`.
* **Parallelism** → Works seamlessly with the Java Stream API for parallel processing.
* **Cleaner Event Handling** → Simplifies GUI and event-driven programming.

---

## 📝 Syntax of Lambda Expressions

### General Syntax

```java
(parameters) -> expression
(parameters) -> { statements; }
```

### Variations

1. **No parameters**

   ```java
   () -> System.out.println("Hello World");
   ```

2. **Single parameter (no parentheses needed)**

   ```java
   x -> x * x
   ```

3. **Multiple parameters**

   ```java
   (a, b) -> a + b
   ```

4. **With a block of code**

   ```java
   (int a, int b) -> {
       int sum = a + b;
       return sum;
   }
   ```

---

## 🔄 Example Without and With Lambda

### Without Lambda (Anonymous Class)

```java
Runnable r = new Runnable() {
    public void run() {
        System.out.println("Running without Lambda");
    }
};
new Thread(r).start();
```

### With Lambda

```java
Runnable r = () -> System.out.println("Running with Lambda");
new Thread(r).start();
```

---

## ⚡ Functional Interfaces and Lambda

Lambda expressions can only be used with **functional interfaces**.
A **functional interface** is an interface that has exactly one abstract method (SAM – Single Abstract Method).

Example:

```java
@FunctionalInterface
interface MyFunction {
    int add(int a, int b);
}
```

Using Lambda:

```java
MyFunction f = (a, b) -> a + b;
System.out.println(f.add(5, 3)); // Output: 8
```

---

## 🔍 Alternatives Before Lambda

Before Java 8, the main ways to achieve similar behavior were:

* **Anonymous Inner Classes** – verbose and repetitive.
* **Separate Class Implementations** – too much boilerplate for small tasks.

Example (Anonymous Inner Class):

```java
Comparator<String> comp = new Comparator<String>() {
    public int compare(String s1, String s2) {
        return s1.compareTo(s2);
    }
};
```

Equivalent with Lambda:

```java
Comparator<String> comp = (s1, s2) -> s1.compareTo(s2);
```

---

## 🌟 New Concepts Related to Lambdas

* **Functional Interfaces** (`@FunctionalInterface`)
* **Method References** (`ClassName::methodName`)
* **Streams API** (supports functional operations like map, filter, reduce)
* **Optional Class** (functional style handling of null values)

---

## 🚀 Example with Streams API

```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

names.stream()
     .filter(name -> name.startsWith("C"))
     .map(String::toUpperCase)
     .forEach(System.out::println);
```

**Output:**

```
CHARLIE
```

---

## 📚 Summary

* Lambda Expressions simplify the syntax of implementing functional interfaces.
* They reduce boilerplate and improve readability.
* Enable **functional programming** in Java via Streams API.
* Replace **anonymous classes** for short implementations.
* Introduce new concepts like **method references** and **functional interfaces**.

---

👉 With Lambda Expressions, Java became more **modern, functional, and expressive**, bridging the gap with other functional programming languages.


# 🛠 Steps to Create and Link a Lambda Expression in Java

## 1. Identify a Functional Interface (FI)

* A **Functional Interface** is required.
* It must contain **exactly one abstract method** (SAM – Single Abstract Method).
* Example:

```java
@FunctionalInterface
interface Calculator {
    int operate(int a, int b);
}
```

---

## 2. Write the Lambda Expression (LE)

* The Lambda Expression must match the **method signature** of the FI’s abstract method.
* Example:

```java
Calculator add = (a, b) -> a + b;
```

Here, `(a, b)` matches the two parameters of `operate(int, int)`, and `a + b` matches its return type `int`.

---

## 3. Compiler Checks (How Java Links LE to FI)

When you assign a Lambda Expression to a variable of a Functional Interface type, the **compiler performs the following steps**:

1. **Target Typing**

   * The compiler looks at the **context** where the lambda is used.
   * Example: `Calculator add = (a, b) -> a + b;` → Target type = `Calculator`.

2. **Single Abstract Method (SAM) Detection**

   * The compiler ensures that the target interface has **only one abstract method**.
   * If there are multiple abstract methods, it’s **not a valid FI** → compilation error.

3. **Signature Compatibility**

   * The lambda’s parameter list and return type must match the FI’s SAM.
   * Example: `(a, b) -> a + b` must match `int operate(int, int)`.
   * If return type mismatches (e.g., trying to return `String` for `int`), compilation fails.

4. **Type Inference**

   * The compiler infers the parameter types from the FI method if not explicitly declared.
   * Example:

     ```java
     (a, b) -> a + b      // types inferred as int
     (int a, int b) -> a + b  // explicit declaration
     ```

5. **Bytecode Translation (invokedynamic)**

   * Lambdas are **not compiled into anonymous inner classes**.
   * Instead, Java uses the `invokedynamic` instruction to dynamically link the Lambda to the FI at runtime, making it lightweight and efficient.

---

## 4. Syntax Rules for Linking LE → FI

✔ Must match **number of parameters**.
✔ Must match **parameter types** (either explicitly or inferred).
✔ Must match **return type**.
✔ Can **omit parameter types** if they can be inferred.
✔ Can **omit parentheses** if only one parameter.
✔ Can **omit braces** if only one expression.
✔ Cannot assign one lambda expression to **multiple unrelated FIs** at the same time.

---

## 🔍 Example: Rules in Action

```java
@FunctionalInterface
interface Printer {
    void print(String msg);
}

@FunctionalInterface
interface Adder {
    int add(int x, int y);
}

public class LambdaDemo {
    public static void main(String[] args) {
        // Correct
        Printer p = msg -> System.out.println(msg);  

        // Correct
        Adder a = (x, y) -> x + y;  

        // ❌ Incorrect: mismatched return type
        // Adder b = (x, y) -> "sum";  

        // ❌ Incorrect: target type missing
        // var wrong = (x, y) -> x + y;  // Compiler error: no FI to bind
    }
}
```

---

## 📚 Summary of Linking Process

1. **Context decides FI** → assignment or method parameter tells compiler the FI type.
2. **Compiler verifies SAM** → must have only one abstract method.
3. **Lambda parameters & return** must match FI method.
4. **Compiler infers types** when possible.
5. **Lambda is linked via invokedynamic**, not by generating anonymous class files.

---

## ✅ Complete Java Code

```java
// Functional Interface I1 with one parameter (void return)
interface I1 {
    void m1(int p);
}

// Functional Interface I2 with two parameters (void return)
interface I2 {
    void m1(int p, float f);
}

// Functional Interface I3 with return type (int return)
interface I3 {
    int m1(int p, int q);
}

public class Test02 {
    public static void main(String[] args) {

        // ✅ Correct: parameter type and return type match I1
        I1 i1 = (int p) -> { 
            System.out.println("LE1 p: " + p); 
        };

        // ❌ Error: incompatible parameter type (byte instead of int)
        // I1 i2 = (byte p) -> { System.out.println("LE1 p: " + p); };
        // CE: incompatible types: incompatible parameter types in lambda expression

        // ❌ Error: incompatible parameter type (long instead of int)
        // I1 i3 = (long p) -> { System.out.println("LE1 p: " + p); };
        // CE: incompatible types: incompatible parameter types in lambda expression

        // ❌ Error: incompatible parameter type (Integer object, not primitive int)
        // I1 i4 = (Integer p) -> { System.out.println("LE1 p: " + p); };
        // CE: incompatible types: incompatible parameter types in lambda expression

        // ❌ Error: parameter count mismatch (expects 1, provided 2)
        // I1 i5 = (int p, int q) -> { System.out.println("LE1 p: " + p); };
        // CE: incompatible types: incompatible parameter types in lambda expression

        // ❌ Error: return type mismatch (I1.m1() is void, but returning int)
        // I1 i6 = (int p) -> { System.out.println("LE1 p: " + p); return 5; };
        // CE: incompatible types: bad return type in lambda expression

        // ✅ Correct: matches I2(int, float)
        I2 i7 = (int p, float f) -> { 
            System.out.printf("\nLE2 p: %d f: %f", p, f); 
        };

        // ❌ Error: incompatible parameter type (double instead of int)
        // I2 i8 = (double p, float f) -> { 
        //     System.out.printf("\nLE2 p: %d f: %f", p, f); 
        // };
        // CE: incompatible types: incompatible parameter types in lambda expression

        // ❌ Error: parameter count mismatch (I2 expects 2, provided 3)
        // I2 i9 = (int p, float f, String s) -> { 
        //     System.out.printf("\nLE2 p: %d f: %f", p, f); 
        // };
        // CE: incompatible types: incompatible parameter types in lambda expression
          
        // ❌ Error: parameter order mismatch
        // I2 i10 = (float f, int p) -> { 
        //     System.out.printf("\nLE2 p: %d f: %f", p, f); 
        // };
        // CE: incompatible types: incompatible parameter types in lambda expression


        /* ================== I3 RETURN TYPE TESTS ================== */

        // ✅ Correct: returns int as required
        I3 j1 = (int a, int b) -> { return a + b; };

        // ✅ Correct: return without braces (expression body)
        I3 j2 = (a, b) -> a * b;

        // ❌ Error: missing return (method requires int return)
        // I3 j3 = (a, b) -> { System.out.println(a + b); };
        // CE: missing return statement

        // ❌ Error: incompatible return type (String instead of int)
        // I3 j4 = (a, b) -> { return "Result: " + (a + b); };
        // CE: bad return type in lambda expression: String cannot be converted to int

        // ❌ Error: incompatible return type (void instead of int)
        // I3 j5 = (a, b) -> { };
        // CE: missing return value

        // ❌ Error: multiple return types (inconsistent)
        // I3 j6 = (a, b) -> { if (a > b) return a; else return "bigger"; };
        // CE: bad return type in lambda expression: String cannot be converted to int

        // ✅ Correct: valid return with conditional
        I3 j7 = (a, b) -> { if (a > b) return a; else return b; };

        // Testing valid ones
        System.out.println("\nI3 valid returns:");
        System.out.println("j1(5, 3) = " + j1.m1(5, 3));
        System.out.println("j2(5, 3) = " + j2.m1(5, 3));
        System.out.println("j7(5, 3) = " + j7.m1(5, 3));
    }
}

```

---

## ⚠️ Types of Errors in Lambda Expressions

1. **Parameter Type Mismatch**

   * FI method expects `int`, but lambda defines `byte`, `long`, `double`, or wrapper `Integer`.
   * Example:

     ```java
     I1 i2 = (byte p) -> ... ; // ❌ Error
     ```

2. **Parameter Count Mismatch**

   * FI method has one parameter, but lambda defines two or more.
   * Example:

     ```java
     I1 i5 = (int p, int q) -> ... ; // ❌ Error
     ```

3. **Return Type Mismatch**

   * FI method returns `void`, but lambda returns a value.
   * Example:

     ```java
     I1 i6 = (int p) -> { return 5; }; // ❌ Error
     ```

4. **Extra Parameters in FI**

   * FI expects `(int, float)`, but lambda defines `(int, float, String)`.
   * Example:

     ```java
     I2 i9 = (int p, float f, String s) -> ... ; // ❌ Error
     ```

---

👉 This code compiles only with `i1` and `i7`. All others demonstrate **compile-time errors (CE)** because of **type mismatch, return mismatch, or wrong number of parameters**.

