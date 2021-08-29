package chapter3;

public class P1_ClassesAndObjects {
    public static void main(String[] args) {

        Person john = new Person(); // создание объекта
        john.displayInfo();

        // изменяем имя и возраст
        john.name ="John";
        john.age = 34;
        john.displayInfo();

        System.out.println("---------------------");

        PersonIdent bob = new PersonIdent();      // вызов первого конструктора без параметров
        bob.displayInfo();

        PersonIdent tom = new PersonIdent("Tom"); // вызов второго конструктора с одним параметром
        tom.displayInfo();

        PersonIdent sam = new PersonIdent("Sam", 25); // вызов третьего конструктора с двумя параметрами
        sam.displayInfo();

        System.out.println("---------------------");

        PersonPreIdent dylan = new PersonPreIdent();      // вызов первого конструктора без параметров
        dylan.displayInfo();

        PersonPreIdent matthew = new PersonPreIdent("Matthew"); // вызов второго конструктора с одним параметром
        matthew.displayInfo();

        PersonPreIdent neo = new PersonPreIdent("Neo", 25); // вызов третьего конструктора с двумя параметрами
        neo.displayInfo();

        System.out.println("---------------------");

        PersonInitialize alan = new PersonInitialize();
        alan.displayInfo();

        PersonInitialize allan = new PersonInitialize("Allan");
        allan.displayInfo();
    }
}

//только один класс может иметь модификатор public
class Person{
    String name;        // имя
    int age;            // возраст
    void displayInfo(){
        System.out.printf("Name: %s \tAge: %d\n", name, age);
    }
}

class PersonIdent{
    String name;
    int age;

    public PersonIdent() {
    }

    public PersonIdent(String name) {
        this.name = name;
    }

    public PersonIdent(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void displayInfo() {
        System.out.printf("Name: %s \tAge: %d\n", name, age);
    }
}

class PersonPreIdent {
    String name;    // имя
    int age;        // возраст
    PersonPreIdent()
    {
        name = "Undefined";
        age = 18;
    }

    // вариант с вызовом конструктора
    /*
        Person(String name)
        {
        this(name, 18);
        }
     */

    PersonPreIdent(String n)
    {
        name = n;
        age = 18;
    }
    PersonPreIdent(String n, int a)
    {
        name = n;
        age = a;
    }
    void displayInfo(){
        System.out.printf("Name: %s \tAge: %d\n", name, age);
    }
}

//--- Инициализаторы ---
/*
Кроме конструктора начальную инициализацию объекта можно проводить с помощью инициализатора объекта.
Инициализатор выполняется до любого конструктора.
То есть в инициализатор можно поместить код, общий для всех конструкторов:
 */

class PersonInitialize{

    String name;    // имя
    int age;        // возраст

    /*начало блока инициализатора*/
    {
        name = "Undefined";
        age = 18;
    }
    /*конец блока инициализатора*/

    PersonInitialize(){

    }
    PersonInitialize(String name){

        this.name = name;
    }
    PersonInitialize(String name, int age){

        this.name = name;
        this.age = age;
    }
    void displayInfo(){
        System.out.printf("Name: %s \tAge: %d\n", name, age);
    }
}