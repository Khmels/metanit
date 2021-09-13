package chapter10_StreamAPI;

import java.util.stream.Stream;

public class P10_5_SubStreamAndCombining {

    public static void main(String[] args) {
        //Ряд методов Stream API возвращают подпотоки или объединенные потоки на основе уже имеющихся потоков.

        //--- takeWhile ------------------------------

        /*
        етод takeWhile() выбирает из потока элементы, пока они соответствуют условию.
        Если попадается элемент, который не соответствует условию, то метод завершает свою работу.
        Выбранные элементы возвращаются в виде потока.
         */

        Stream<Integer> numbers = Stream.of(-3, -2, -1, 0, 1, 2, 3, -4, -5);
        numbers.takeWhile(n -> n < 0)
                .forEach(n -> System.out.println(n));

        /*
        При этом несмотря на то, что в потоке больше отрицательных чисел, но метод завершает работу,
        как только обнаружит первое число, которое не соответствует условию.
        В этом и состоит отличие, например, от метода filter().
         */
        System.out.println("---------------------");

        Stream<Integer> numbers2 = Stream.of(-3, -2, -1, 0, 1, 2, 3, -4, -5);
        numbers2.sorted()
                .takeWhile(n -> n < 0)
                .forEach(n -> System.out.println(n));
        System.out.println("---------------------");

        //--- dropWhile ------------------------------

        /*
        Метод dropWhile() выполняет обратную задачу - он пропускает элементы потока,
        которые соответствуют условию до тех пор, пока не встретит элемент, который НЕ соответствует условию:
         */

        Stream<Integer> numbers3 = Stream.of(-3, -2, -1, 0, 1, 2, 3, -4, -5);
        numbers3.dropWhile(n -> n < 0)
                .forEach(n -> System.out.println(n));
        System.out.println("---------------------");

        Stream<Integer> numbers4 = Stream.of(-3, -2, -1, 0, 1, 2, 3, -4, -5);
        numbers4.sorted().dropWhile(n -> n < 0)
                .forEach(n -> System.out.println(n));
        System.out.println("---------------------");

        //--- concat ------------------------------

        /*
        Статический метод concat() объединяет элементы двух потоков, возвращая объединенный поток
         */

        Stream<String> people1 = Stream.of("Tom", "Bob", "Sam");
        Stream<String> people2 = Stream.of("Alice", "Kate", "Sam");

        Stream.concat(people1, people2).sorted().forEach(n -> System.out.println(n));
        System.out.println("---------------------");

        //--- distinct ------------------------------

        /*
        Метод distinct() возвращает только ункальные элементы в виде потока
         */

        Stream<String> people = Stream.of("Tom", "Bob", "Sam", "Tom", "Alice", "Kate", "Sam");
        people.distinct().forEach(p -> System.out.println(p));
    }
}
