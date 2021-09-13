package chapter3_OOP;

public interface P3_10_Interface_Printable {
    /*
    Все методы интерфейса не имеют модификаторов доступа,
    но фактически по умолчанию доступ public,
    так как цель интерфейса - определение функционала для реализации его классом.
    Поэтому весь функционал должен быть открыт для реализации.
     */
    void print();

    /// Interface abstract methods cannot have body
    /// void printRealization(){}
}

interface P10_Interface_Default{
    // Метод по умолчанию - это обычный метод без модификаторов, который помечается ключевым словом default.
    // Затем в классе нам необязательно этот метод реализовать, хотя можно его и переопределить:
    default void print(){

        System.out.println("Undefined printable");
    }
}

class Book implements P3_10_Interface_Printable {

    String name;
    String author;

    Book(String name, String author){

        this.name = name;
        this.author = author;
    }

    // класс применяет интерфейс, он должен реализовать все методы интерфейса
    // класс не реализует какие-то методы интерфейса, то такой класс должен быть определен как абстрактный,
    // а его неабстрактные классы-наследники затем должны будут реализовать эти методы.
    @Override
    public void print() {

        System.out.printf("%s (%s) \n", name, author);
    }
}

class Journal implements P3_10_Interface_Printable {

    private String name;

    String getName(){
        return name;
    }

    Journal(String name){

        this.name = name;
    }
    public void print() {
        System.out.println(name);
    }
}

class Magazine implements P10_Interface_Default {
    private String name;

    String getName(){
        return name;
    }
    Magazine(String name){

        this.name = name;
    }
}

class MagazineOverride implements P10_Interface_Default {
    private String name;

    String getName(){
        return name;
    }
    MagazineOverride(String name){

        this.name = name;
    }

    @Override
    public void print() {
        System.out.print("This method was override. The name of magazine: ");
        System.out.printf("%s", this.name);
        System.out.println();

    }
}

// --- Статические методы ---
// Начиная с JDK 8 в интерфейсах доступны статические методы - они аналогичны методам класса

interface P10_Interface_Static{
    void print();

    static void read(){

        System.out.println("Static method. Read printable");
    }
}

// --- Приватные методы ---
/*
По умолчанию все методы в интерфейсе фактически имеют модификатор public.
Однако начиная с Java 9 мы также можем определять в интерфейсе методы с модификатором private.
Они могут быть статическими и нестатическими, но они не могут иметь реализации по умолчанию.
 */

class Calculation implements Calculable{

}
interface Calculable{

    default int sum(int a, int b){
        return sumAll(a, b);
    }
    default int sum(int a, int b, int c){
        return sumAll(a, b, c);
    }

    private int sumAll(int... values){
        int result = 0;
        for(int n : values){
            result += n;
        }
        return result;
    }
}

// --- Константы в интерфейсах ---
interface State{

    // Хотя такие константы также не имеют модификаторов,
    // но по умолчанию они имеют модификатор доступа public static final,
    // и поэтому их значение доступно из любого места программы.

    int OPEN = 1;
    int CLOSED = 0;

    // Modifiers are redundant for interface fields
    public static final int STATE_UNDEFINED = -1;

    void printState(int n);
}

class WaterPipe implements State{

    public void printState(int n){
        if(n==OPEN)
            System.out.println("Water is opened");
        //else if(n==0)
        else if(n==CLOSED)
            System.out.println("Water is closed");
        else
            System.out.println("State is invalid");
    }
}

// --- Множественная реализация интерфейсов ---

interface Printable {

    // методы интерфейса
}

interface Searchable {

    // методы интерфейса
}

class Document implements Printable, Searchable{

    // реализация класса
}

// --- Наследование интерфейсов ---
// Интерфейсы, как и классы, могут наследоваться:

// При применении этого интерфейса класс Book должен будет реализовать
// как методы интерфейса BookPrintable, так и методы базового интерфейса Printable.

interface A3_Printable extends Printable{

    void printToPDF();
}

// --- Вложенные интерфейсы ---
// При применении такого интерфейса нам надо указывать его полное имя вместе с именем класса:
// Printer.Printable

class Printer{
    interface Printable {

        void print();
    }
}

class Booklet implements Printer.Printable {

    String name;

    Booklet(String name){

        this.name = name;
    }
    public void print() {
        System.out.println(name);
    }
}


