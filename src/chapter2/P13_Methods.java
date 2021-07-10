package chapter2;

public class P13_Methods {
    /*
          [модификаторы] тип_возвращаемого_значения название_метода ([параметры]){
          // тело метода
          }
     */

    public static void main(String[] args) {
        ///hello(); //Non-static method 'hello()' cannot be referenced from a static context
        welcome(11);
    }

    // чтобы вызвать в методе main другие методы,
    // которые определены в одном классе с методом main,
    // они должны иметь модификатор static.

    void hello(){
        System.out.println("Hello");
    }

    static void welcome(int javaVersion){
        System.out.println("Welcome to Java " + javaVersion);
    }
}


