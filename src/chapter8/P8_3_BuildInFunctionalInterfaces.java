package chapter8;

import java.util.Scanner;
import java.util.function.*;

public class P8_3_BuildInFunctionalInterfaces {
    /*
    В JDK 8 вместе с самой функциональностью лямбда-выражений также было добавлено
    некоторое количество встроенных функциональных интерфейсов,
    которые можно использовать в различных ситуациях и в различные API в рамках JDK 8.

    В частности, ряд далее рассматриваемых интерфейсов широко применяется в Stream API -
    новом прикладном интерфейсе для работы с данными.
     */

    /*
    Predicate<T>
    Consumer<T>
    Function<T,R>
    Supplier<T>
    UnaryOperator<T>
    BinaryOperator<T>
     */
    public static void main(String[] args) {

        //--- Predicate<T>
        Predicate<Integer> isPositive = x -> x > 0;

        System.out.println(isPositive.test(5)); // true
        System.out.println(isPositive.test(-7)); // false

        //--- BinaryOperator<T>
        BinaryOperator<Integer> multiply = (x, y) -> x*y;

        System.out.println(multiply.apply(3, 5)); // 15
        System.out.println(multiply.apply(10, -2)); // -20

        //--- UnaryOperator<T>
        UnaryOperator<Integer> square = x -> x*x;
        System.out.println(square.apply(5)); // 25

        //--- Function<T,R>
        Function<Integer, String> convert = x -> String.valueOf(x) + " долларов";
        System.out.println(convert.apply(5)); // 5 долларов

        //--- Consumer<T>
        Consumer<Integer> printer = x-> System.out.printf("%d долларов \n", x);
        printer.accept(600); // 600 долларов

        //--- Supplier<T>
        Supplier<User> userFactory = ()->{

            Scanner in = new Scanner(System.in);
            System.out.println("Введите имя: ");
            String name = in.nextLine();
            return new User(name);
        };

        User user1 = userFactory.get();
        User user2 = userFactory.get();

        System.out.println("Имя user1: " + user1.getName());
        System.out.println("Имя user2: " + user2.getName());

    }
}

//'java doc'
//###
// @FunctionalInterface
// public interface Predicate<T> {
//      boolean test(T t);
// ... some others default and static methods
// }

/*
Функциональный интерфейс Predicate<T> проверяет соблюдение некоторого условия.
Если оно соблюдается, то возвращается значение true. В качестве параметра лямбда-выражение принимает объект типа T:
 */

interface Predicate2<T> {
    boolean test(T t);
}

/*
BinaryOperator<T> принимает в качестве параметра два объекта типа T,
выполняет над ними бинарную операцию и возвращает ее результат также в виде объекта типа T:
 */

interface BinaryOperator2<T> {
    T apply(T t1, T t2);
}

/*
UnaryOperator<T> принимает в качестве параметра объект типа T,
выполняет над ними операции и возвращает результат операций в виде объекта типа T:
 */

interface UnaryOperator2<T> {
    T apply(T t);
}

/*
Функциональный интерфейс Function<T,R> представляет функцию перехода от объекта типа T к объекту типа R:
 */

interface Function2<T, R> {
    R apply(T t);
}

/*
Consumer<T> выполняет некоторое действие над объектом типа T, при этом ничего не возвращая:
 */

interface Consumer2<T> {
    void accept(T t);
}

/*
Supplier<T> не принимает никаких аргументов, но должен возвращать объект типа T:
 */

interface Supplier2<T> {
    T get();
}