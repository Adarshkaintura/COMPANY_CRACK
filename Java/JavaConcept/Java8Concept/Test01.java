interface I1 {
  void m1(int p);
}

public class Test01 {

    //lambda expression Code 1
    //lambda expression cannot be made without functional interface
    public static void main(String[] args) {
       // int a -> System.out.println(a);
       //5
       //5+3

       //the aboev will give error as you are using direct literal 
       
       int a=0;
       int b=5+5;
       I1 i1=p->System.out.println(p); 
       System.out.println(a+b);
       System.out.println(i1);
       i1.m1(5);
    }
}
//syntax
/*
funciton interface i1 = (parameter list)->{
  expression
 }
 */