@FunctionalInterface
interface I1 {
    String m1(int a, int b);
}

@FunctionalInterface
interface I2 {
    Person m2(Student s);
}

class Person {
    int c, d;
}

class Student extends Person {
}

public class Test03 {
    public static void main(String[] args) {
        I1 i1 = (a, b) -> { 
            System.out.println(a + b); 
            return "adarsh"; 
        };
        System.out.println(i1.m1(2, 3));

       // I2 i2 = (Student s)->{return new Person();};
       I2 i2 = (Student s)->new Person(); //both are correct
        Person p = i2.m2(new Student());
        System.out.println("Got a Person: " + p);
    }
}
