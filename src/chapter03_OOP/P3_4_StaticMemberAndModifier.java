package chapter03_OOP;

public class P3_4_StaticMemberAndModifier {
    public static void main(String[] args) {

        PersonStatic tom = new PersonStatic();
        PersonStatic bob = new PersonStatic();

        tom.displayId();    // Id = 1
        bob.displayId();    // Id = 2
        System.out.println(PersonStatic.counter); // 3
        System.out.println(PersonStatic.counter); // 3
        System.out.println(PersonStatic.counter); // 3

        // изменяем Person.counter
        PersonStatic.counter = 8;

        PersonStatic sam = new PersonStatic();
        sam.displayId();    // Id = 8

        System.out.println(PersonStatic.counter); // получаем значение 9

        PersonStatic archibald = new PersonStatic();
        System.out.println(PersonStatic.counter); //10
        archibald.displayId(); // показывает для текущего

        PersonStatic arnold = new PersonStatic();
        System.out.println(PersonStatic.counter); //11 - для нового
        arnold.displayId(); // показывает для текущего

        System.out.println("---------------------");

        double radius = 60;
        System.out.printf("Radius: %f \n", radius);             // 60
        System.out.printf("Area: %f \n", MathStat.PI * radius);     // 188,4

        // out представляет статическую константу класса System.
        // Поэтому обращение к ней идет без создания объекта класса System.
        System.out.println("hello");

        System.out.println("---------------------");

        PersonStaticInit bill = new PersonStaticInit();
        PersonStaticInit basil = new PersonStaticInit();

        bill.displayId();    // Id = 105
        basil.displayId();    // Id = 106

        System.out.println("---------------------");

        PersonStaticMethod.displayCounter();    // Counter: 1

        PersonStaticMethod bradley = new PersonStaticMethod();
        PersonStaticMethod brenton = new PersonStaticMethod();

        PersonStaticMethod.displayCounter();    // Counter: 3

        System.out.println("---------------------");

        System.out.println(Operation.sum(457, 213));          // 670
        System.out.println(Operation.subtract(457, 213));     // 244
        System.out.println(Operation.multiply(457, 213));      // 97341


    }
}

class PersonStatic{

    // При создании объектов класса для каждого объекта создается своя копия нестатических обычных полей.
    // статические поля являются ОБЩИМИ ДЛЯ ВСЕГО КЛАССА. Поэтому они могут использоваться без создания объектов класса.
    private int id;
    static int counter=1;

    PersonStatic(){
        id = counter++;
    }
    public void displayId(){

        System.out.printf("Id: %d \n", id);
    }
}

//--- Статические константы ---
class MathStat {
    public static final double PI = 3.14;
}

//--- Статические инициализаторы ---
class PersonStaticInit{

    private int id;
    static int counter;

    //вызов статического инициализатора производится только перед созданием самого первого объекта класса.
    static{
        counter = 105;
        System.out.println("Static initializer");
    }
    PersonStaticInit(){
        id=counter++;
        System.out.println("Constructor");
    }
    public void displayId(){
        System.out.printf("Id: %d \n", id);
    }
}

//--- Статические методы ---
class PersonStaticMethod{

    private int id;
    private static int counter = 1;

    PersonStaticMethod(){
        id = counter++;
    }
    // Static Method
    public static void displayCounter(){
        ///System.out.println(id);
        System.out.printf("Counter: %d \n", counter);
    }
    public void displayId(){

        System.out.printf("Id: %d \n", id);
    }
}

/*
При использовании статических методов надо учитывать ограничения:
в статических методах можем вызывать только другие статические методы и использовать только статические переменные.
 */

/*
методы определяются как статические, когда методы не затрагиют состояние объекта,
то есть его нестатические поля и константы,
и для вызова метода нет смысла создавать экземпляр класса. Например:
 */

class Operation{

    static int sum(int x, int y){
        return x + y;
    }
    static int subtract(int x, int y){
        return x - y;
    }
    static int multiply(int x, int y){
        return x * y;
    }
}