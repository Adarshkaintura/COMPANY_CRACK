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
