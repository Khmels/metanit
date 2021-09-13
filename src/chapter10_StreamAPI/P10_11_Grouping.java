package chapter10_StreamAPI;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class P10_11_Grouping {
    public static void main(String[] args) {

        //--- Collectors.groupingBy

        /*
        Чтобы сгруппировать данные по какому-нибудь признаку, нам надо использовать в связке метод collect() объекта Stream
        и метод Collectors.groupingBy().
         */

        /*
        для создания групп в метод phoneStream.collect() передается вызов функции Collectors.groupingBy(),
        которая с помощью выражения Phone::getCompany группирует объекты по компании.
         */

        Stream<Ch10_Phone> phoneStream = Stream.of(new Ch10_Phone("iPhone X", "Apple", 600),
                new Ch10_Phone("Pixel 2", "Google", 500),
                new Ch10_Phone("iPhone 8", "Apple",450),
                new Ch10_Phone("Galaxy S9", "Samsung", 440),
                new Ch10_Phone("Galaxy S8", "Samsung", 340));

        Map<String, List<Ch10_Phone>> phonesByCompany = phoneStream.collect(
                Collectors.groupingBy(Ch10_Phone::getCompany));

        for(Map.Entry<String, List<Ch10_Phone>> item : phonesByCompany.entrySet()){

            System.out.println(item.getKey());
            for(Ch10_Phone phone : item.getValue()){

                System.out.println(phone.getName());
            }
            System.out.println();
        }

        System.out.println("---------------------");

        //--- Collectors.partitioningBy

        /*
        Метод Collectors.partitioningBy() имеет похожее действие,
        только он делит элементы на группы по принципу, соответствует ли элемент определенному условию.
         */

        /*
        В данном случае с помощью условия p->p.getCompany()=="Apple" смотрим, принадлежит ли телефон компании Apple.
        Если телефон принадлежит этой компании, то он попадает в одну группу, если нет, то в другую.
         */

        Stream<Ch10_Phone> phoneStream2 = Stream.of(new Ch10_Phone("iPhone X", "Apple", 600),
                new Ch10_Phone("Pixel 2", "Google", 500),
                new Ch10_Phone("iPhone 8", "Apple",450),
                new Ch10_Phone("Galaxy S9", "Samsung", 440),
                new Ch10_Phone("Galaxy S8", "Samsung", 340));

        Map<Boolean, List<Ch10_Phone>> phonesByCompany2 = phoneStream2.collect(
                Collectors.partitioningBy(p->p.getCompany()=="Apple"));

        for(Map.Entry<Boolean, List<Ch10_Phone>> item : phonesByCompany2.entrySet()){

            System.out.println(item.getKey());

            for(Ch10_Phone phone : item.getValue()){

                System.out.println(phone.getName());
            }
            System.out.println();
        }
        System.out.println("---------------------");


        //--- Collectors.counting

        /*
        Метод Collectors.counting применяется в Collectors.groupingBy() для вычисления количества элементов в каждой группе
         */

        Stream<Ch10_Phone> phoneStream3 = Stream.of(new Ch10_Phone("iPhone X", "Apple", 600),
                new Ch10_Phone("Pixel 2", "Google", 500),
                new Ch10_Phone("iPhone 8", "Apple",450),
                new Ch10_Phone("Galaxy S9", "Samsung", 440),
                new Ch10_Phone("Galaxy S8", "Samsung", 340));

        Map<String, Long> phonesByCompany3 = phoneStream3.collect(
                Collectors.groupingBy(Ch10_Phone::getCompany, Collectors.counting()));

        for(Map.Entry<String, Long> item : phonesByCompany3.entrySet()){

            System.out.println(item.getKey() + " - " + item.getValue());
        }

        System.out.println("---------------------");

        //--- Collectors.summing

        /*
        Метод Collectors.summing применяется для подсчета суммы. В зависимости от типа данных,
        к которым применяется метод, он имеет следующие формы: summingInt(), summingLong(), summingDouble().
         */

        /*
        С помощью выражения Collectors.summingInt(Phone::getPrice)) указываем,
        что для каждой компании будет вычислять совокупная цена всех ее смартфонов.

        И поскольку вычисляется результат - сумма для значений типа int,
        то в качестве типа возвращаемой коллекции используется тип Map<String, Integer>
         */

        Stream<Ch10_Phone> phoneStream4 = Stream.of(new Ch10_Phone("iPhone X", "Apple", 600),
                new Ch10_Phone("Pixel 2", "Google", 500),
                new Ch10_Phone("iPhone 8", "Apple",450),
                new Ch10_Phone("Galaxy S9", "Samsung", 440),
                new Ch10_Phone("Galaxy S8", "Samsung", 340));

        Map<String, Integer> phonesByCompany4 = phoneStream4.collect(
                Collectors.groupingBy(Ch10_Phone::getCompany, Collectors.summingInt(Ch10_Phone::getPrice)));

        for(Map.Entry<String, Integer> item : phonesByCompany4.entrySet()){

            System.out.println(item.getKey() + " - " + item.getValue());
        }

        System.out.println("---------------------");

        //--- maxBy и minBy

        /*
        Методы maxBy и minBy применяются для подсчета минимального и максимального значения в каждой группе.
        В качестве параметра эти методы принимают функцию компаратора, которая нужна для сравнения значений.
         */

        Stream<Ch10_Phone> phoneStream5 = Stream.of(new Ch10_Phone("iPhone X", "Apple", 600),
                new Ch10_Phone("Pixel 2", "Google", 500),
                new Ch10_Phone("iPhone 8", "Apple",450),
                new Ch10_Phone("Galaxy S9", "Samsung", 440),
                new Ch10_Phone("Galaxy S8", "Samsung", 340));

        Map<String, Optional<Ch10_Phone>> phonesByCompany5 = phoneStream5.collect(
                Collectors.groupingBy(Ch10_Phone::getCompany,
                        Collectors.minBy(Comparator.comparing(Ch10_Phone::getPrice))));

        for(Map.Entry<String, Optional<Ch10_Phone>> item : phonesByCompany5.entrySet()){

            System.out.println(item.getKey() + " - " + item.getValue().get().getName());
        }

        System.out.println("----------");

        Stream<Ch10_Phone> phoneStream51 = Stream.of(new Ch10_Phone("iPhone X", "Apple", 600),
                new Ch10_Phone("Pixel 2", "Google", 500),
                new Ch10_Phone("iPhone 8", "Apple",450),
                new Ch10_Phone("Galaxy S9", "Samsung", 440),
                new Ch10_Phone("Galaxy S8", "Samsung", 340));

        Map<String, Optional<Ch10_Phone>> phonesByCompany51 = phoneStream51.collect(
                Collectors.groupingBy(Ch10_Phone::getCompany,
                        Collectors.maxBy(Comparator.comparing(Ch10_Phone::getPrice))));

        for(Map.Entry<String, Optional<Ch10_Phone>> item : phonesByCompany51.entrySet()){

            System.out.println(item.getKey() + " - " + item.getValue().get().getName());
        }
        System.out.println("---------------------");

        //--- summarizing

        /*
        Методы summarizingInt(), summarizingLong(), summarizingDouble() позволяют объединить в набор значения соответствующих типов
         */

        /*
        Метод Collectors.summarizingInt(Phone::getPrice)) создает набор, в который помещаются цены для всех телефонов каждой из групп.
        Данный набор инкапсулируется в объекте IntSummaryStatistics.
        Если бы применяли методы summarizingLong() или summarizingDouble(),
        то соответственно бы получали объекты LongSummaryStatistics или DoubleSummaryStatistics.
         */


        Stream<Ch10_Phone> phoneStream6 = Stream.of(new Ch10_Phone("iPhone X", "Apple", 600),
                new Ch10_Phone("Pixel 2", "Google", 500),
                new Ch10_Phone("iPhone 8", "Apple",450),
                new Ch10_Phone("Galaxy S9", "Samsung", 440),
                new Ch10_Phone("Galaxy S8", "Samsung", 340));

        Map<String, IntSummaryStatistics> priceSummary = phoneStream6.collect(
                Collectors.groupingBy(Ch10_Phone::getCompany,
                        Collectors.summarizingInt(Ch10_Phone::getPrice)));

        for(Map.Entry<String, IntSummaryStatistics> item : priceSummary.entrySet()){

            System.out.println(item.getKey() + " - " + item.getValue().getAverage());
        }
        System.out.println("---------------------");

        /*
        У этих объектов есть ряд методов, который позволяют выполнить различные атомарные операции над набором:
            getAverage():   возвращает среднее значение
            getCount():     возвращает количество элементов в наборе
            getMax():       возвращает максимальное значение
            getMin():       возвращает минимальное значение
            getSum():       возвращает сумму элементов
            accept():       добавляет в набор новый элемент
         */

        //--- mapping

        /*
        Метод mapping позволяет дополнительно обработать данные
        и задать функцию отображения объектов из потока на какой-нибудь другой тип данных.
         */

        /*
        Выражение Collectors.mapping(Phone::getName, Collectors.toList()) указывает,
        что в группу будут выделятся названия смартфонов, причем группа будет представлять объект List
         */

        Stream<Ch10_Phone> phoneStream7 = Stream.of(new Ch10_Phone("iPhone X", "Apple", 600),
                new Ch10_Phone("Pixel 2", "Google", 500),
                new Ch10_Phone("iPhone 8", "Apple",450),
                new Ch10_Phone("Galaxy S9", "Samsung", 440),
                new Ch10_Phone("Galaxy S8", "Samsung", 340));

        Map<String, List<String>> phonesByCompany7 = phoneStream7.collect(
                Collectors.groupingBy(Ch10_Phone::getCompany,
                        Collectors.mapping(Ch10_Phone::getName, Collectors.toList())));

        for(Map.Entry<String, List<String>> item : phonesByCompany7.entrySet()){

            System.out.println(item.getKey());
            for(String name : item.getValue()){
                System.out.println("\t" + name);
            }
        }

    }
}

