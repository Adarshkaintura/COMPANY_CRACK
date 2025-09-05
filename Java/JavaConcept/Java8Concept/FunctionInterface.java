public class FunctionalInterfaceDemo {
    public static void main(String[] args) {
        // 2) Functional Interface usage with Lambda
        Addition add = (a, b) -> a + b;
        System.out.println("Sum = " + add.add(10, 20));

        // 4) Calling default methods
        add.display();
        add.showMessage("Learning Functional Interfaces");

        // 5) Calling static methods
        Addition.info();
        Addition.showInfo();

        // 3) Using static inner class
        Addition.Helper.show();
        Addition.Helper.printHelper();

        // 6) Testing private methods indirectly
        add.callPrivateHelpers();
    }
}

@FunctionalInterface
interface Addition {
    // ---------------- Case 2: public abstract methods (only one allowed) ----------------
    int add(int x, int y);  // only one abstract method allowed in FI

    // ---------------- Case 1: public static final variables ----------------
    public static final String INFO = "Functional Interface Example";
    public static final int VERSION = 1;

    // ---------------- Case 3: public static inner classes ----------------
    public static class Helper {
        public static void show() {
            System.out.println("Inside static inner class of FI");
        }
        public static void printHelper() {
            System.out.println("Helper printing...");
        }
    }

    // ---------------- Case 4: public default methods ----------------
    default void display() {
        System.out.println("Default method in Functional Interface");
    }
    default void showMessage(String msg) {
        System.out.println("Message: " + msg);
    }

    // ---------------- Case 5: public static methods ----------------
    public static void info() {
        System.out.println(INFO);
    }
    public static void showInfo() {
        System.out.println("Version: " + VERSION);
    }

    // ---------------- Case 6: private static & non-static methods (Java 9+) ----------------
    private static void privateHelper() {
        System.out.println("Private static helper inside FI");
    }
    private void privateNonStatic() {
        System.out.println("Private non-static helper inside FI");
    }

    // Indirectly call private methods
    default void callPrivateHelpers() {
        privateHelper();
        privateNonStatic();
    }

    // ---------------- Case 7: java.lang.Object methods ----------------
    // These methods (toString, equals, hashCode) already come from Object class,
    // redeclaring them does not count as abstract methods.
}
