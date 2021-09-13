package chapter10_StreamAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class P10_2_CreationStream {
    public static void main(String[] args) {
        ArrayList<String> cities = new ArrayList<String>();
        Collections.addAll(cities, "Париж", "Лондон", "Мадрид");
        cities.stream() // получаем поток
                .filter(s->s.length()==6) // применяем фильтрацию по длине строки
                .forEach(s->System.out.println(s)); // выводим отфильтрованные строки на консоль
                // Lambda can be replaced with method reference
                //.forEach(System.out::println);

        System.out.println("---------------------");

        // С помощью каждой промежуточной операции, которая применяется к потоку, можно получить поток с учетом модификаций.

        ArrayList<String> cities2 = new ArrayList<String>();
        Collections.addAll(cities2, "Париж", "Лондон", "Мадрид");

        Stream<String> citiesStream = cities2.stream();         // получение потока
        citiesStream = citiesStream.filter(s->s.length()==6);   // применение фильтрации по длине строки
        citiesStream.forEach(s->System.out.println(s));         // вывод отфильтрованных строки на консоль

        //! Важно, что после использования терминальных операций другие терминальные или промежуточные операции
        // к этому же потоку не могут быть применены, поток уже употреблен.

        /// Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed

        /// citiesStream.forEach(s->System.out.println(s)); // терминальная операция употребляет поток
        /// long number = citiesStream.count();             // здесь ошибка, так как поток уже употреблен
        /// System.out.println(number);
        /// citiesStream = citiesStream.filter(s->s.length()>5); // тоже нельзя, так как поток уже употреблен

        System.out.println("---------------------");

        // метод Arrays.stream(T[] array), который создает поток данных из массива:

        Stream<String> citiesStream2 = Arrays.stream(new String[]{"Париж", "Лондон", "Мадрид"}) ;
        citiesStream2.forEach(s->System.out.println(s));    // вывод всех элементов массива

        System.out.println("---------------------");

        IntStream intStream = Arrays.stream(new int[]{1,2,4,5,7});
        intStream.forEach(i->System.out.print( i + ", "));
        System.out.println();
        System.out.println("---------------------");

        LongStream longStream = Arrays.stream(new long[]{100,250,400,237,123456789});
        longStream.forEach(l->System.out.print(l+ "; "));
        System.out.println();
        System.out.println("---------------------");

        DoubleStream doubleStream = Arrays.stream(new double[] {1.2, 3.4, 5.6, 7.8, 9.101, 0.987654321});
        doubleStream.forEach(d->System.out.print("[" + d + "] "));
        System.out.println();
        System.out.println("---------------------");

        //статический метод of(T..values) класса Stream:

        Stream<String> citiesStream3 =Stream.of("Париж", "Лондон", "Мадрид");
        citiesStream3.forEach(s->System.out.println(s));
        System.out.println("---------------------");

        // можно передать массив
        String[] cities3 = {"Париж", "Лондон", "Мадрид"};
        Stream<String> citiesStream4 =Stream.of(cities3);
        System.out.println(citiesStream4.count());
        System.out.println("---------------------");

        IntStream intStream2 = IntStream.of(1,2,4,5,7);
        intStream2.forEach(i->System.out.print( i + ", "));
        System.out.println();
        System.out.println("---------------------");

        LongStream longStream2 = LongStream.of(100,250,400,5843787,237);
        longStream2.forEach(l->System.out.print(l+ "; "));
        System.out.println();
        System.out.println("---------------------");

        DoubleStream doubleStream2 = DoubleStream.of(3.4, 6.7, 9.5, 8.2345, 121);
        doubleStream2.forEach(d->System.out.print("[" + d + "] "));
        doubleStream2.forEach(d->System.out.print("[" + d + "] "));
        System.out.println();
        System.out.println("---------------------");
    }
}

/*
Для создания потока данных можно применять различные методы.
В качестве источника потока можем использовать коллекции.

В частности, в JDK 8 в интерфейс Collection, который реализуется всеми классами коллекций, были добавлены два метода для работы с потоками:

    default Stream<E> stream:           возвращается поток данных из коллекции
    default Stream<E> parallelStream:   возвращается параллельный поток данных из коллекции
 */

/*
Фактически жизненный цикл потока проходит следующие три стадии:
    - Создание потока
    - Применение к потоку ряда промежуточных операций
    - Применение к потоку терминальной операции и получение результата
 */
