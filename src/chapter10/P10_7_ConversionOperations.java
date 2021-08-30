package chapter10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class P10_7_ConversionOperations {
    public static void main(String[] args) {

        //--- count ------------------------

        /*
        Метод   count() возвращает количество элементов в потоке данных:
         */

        ArrayList<String> names = new ArrayList<String>();
        names.addAll(Arrays.asList(new String[]{"Tom", "Sam", "Bob", "Alice"}));
        System.out.println(names.stream().count());  // 4

        // количество элементов с длиной не больше 3 символов
        System.out.println(names.stream().filter(n->n.length()<=3).count());  // 3

        //--- findFirst и findAny ------------------------

        /*
        Метод   findFirst() извлекает из потока первый элемент,
                findAny() извлекает случайный объект из потока (нередко так же первый):
         */

        ArrayList<String> names2 = new ArrayList<String>();
        names2.addAll(Arrays.asList(new String[]{"Tom", "Sam", "Bob", "Alice"}));

        Optional<String> first = names2.stream().findFirst();
        System.out.println(first.get());    // Tom

        Optional<String> any = names2.stream().findAny();
        System.out.println(any.get());      // Tom

        System.out.println("---------------------");

        //--- allMatch, anyMatch, noneMatch ------------------------

        /*
        группа операций сведения возвращает логическое значение true или false:

            boolean allMatch(Predicate<? super T> predicate):   возвращает true,
                                                                    если все элементы потока удовлетворяют условию в предикате
            boolean anyMatch(Predicate<? super T> predicate):   возвращает true,
                                                                    если хоть один элемент потока удовлетворяют условию в предикате
            boolean noneMatch(Predicate<? super T> predicate):  возвращает true,
                                                                    если ни один из элементов в потоке не удовлетворяет условию в предикате
         */

        ArrayList<String> names3 = new ArrayList<String>();
        names3.addAll(Arrays.asList(new String[]{"Tom", "Sam", "Bob", "Alice"}));
        names3.forEach(s -> System.out.println(s));
        System.out.println("---------------------");

        boolean anyMatch = names3.stream().anyMatch(s->s.length()>3);
        System.out.println("Есть ли в потоке строка, длина которой больше 3: " + anyMatch);    // true

        boolean allMatch = names3.stream().allMatch(s->s.length()==3);
        System.out.println("Все ли имена имеют длину 3 символа: " + allMatch);    // false

        // НЕТ ЛИ в потоке строки "Bill". Если нет, то true, если есть, то false
        boolean noneMatch = names3.stream().noneMatch(s->s=="Bill");
        System.out.println("НЕТ ЛИ в потоке строки \"Bill\". Если нет, то true, если есть, то false: "+ noneMatch);   // true
        System.out.println("---------------------");

        //--- min и max ------------------------

        /*
        Поскольку данные в потоке могут представлять различные типы, в том числе сложные классы,
        то в качестве параметра в эти методы передается объект интерфейса Comparator,
        который указывает, как сравнивать объекты:
                Optional<T> min(Comparator<? super T> comparator)
                Optional<T> max(Comparator<? super T> comparator)
         */

        // Оба метода возвращают элемент потока (минимальный или максимальный), обернутый в объект Optional.

        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.addAll(Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9}));

        Optional<Integer> min = numbers.stream().min(Integer::compare);
        Optional<Integer> max = numbers.stream().max((s,s1)->s.compareTo(s1));
        // Can be replaced with Comparator.naturalOrder
        Optional<Integer> max1 = numbers.stream().max(Comparator.naturalOrder());

        // чтобы получить непосредственно результат операции из Optional, необходимо вызвать метод get().
        System.out.println(min.get());      // 1
        System.out.println(max.get());      // 9
        System.out.println(max1.get());     // 9
        System.out.println("---------------------");

        /*
        Интерфейс Comparator - это функциональный интерфейс, который определяет один метод compare, принимающий
        два сравниваемых объекта и возвращающий число
        (если первый объект больше, возвращается положительное число, иначе возвращается отрицательное число).

        Поэтому вместо конкретной реализации компаратора можно передать лямбда-вырашение или метод,
        который соответствует методу compare интерфейса Comparator.

        Поскольку сравниваются числа, то в метод передается в качестве компаратора статический метод Integer.compare()
         */
        ArrayList<Ch10_Phone> phones = new ArrayList<Ch10_Phone>();
        phones.addAll(Arrays.asList(new Ch10_Phone[]{
                new Ch10_Phone("iPhone 8", 52000),
                new Ch10_Phone("Nokia 9", 35000),
                new Ch10_Phone("Samsung Galaxy S9", 48000),
                new Ch10_Phone("HTC U12", 36000)
        }));



        // Для определения функциональности сравнения в классе Phone реализован статический метод compare,
        // который соответствует сигнатуре метода compare интерфейса Comparator.
        //
        // И в методах min и max применяем этот статический метод для сравнения объектов.
        Ch10_Phone min2 = phones.stream().min(Ch10_Phone::compare).get();
        Ch10_Phone max2 = phones.stream().max(Ch10_Phone::compare).get();
        Ch10_Phone max3 = phones.stream().max((p1, p2)->p1.compareTo(p2)).get();

        System.out.printf("MIN Name: %s Price: %d \n", min2.getName(), min2.getPrice());
        System.out.printf("MAX Name: %s Price: %d \n", max2.getName(), max2.getPrice());
        System.out.printf("MAX Name: %s Price: %d \n", max3.getName(), max2.getPrice());
    }

}
/*
Операции сведения представляют терминальные операции, которые возвращают некоторое значение - результат операции.
В Stream API есть ряд операций сведения.
 */