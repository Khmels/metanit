package chapter10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class P10_4_Sorted {

    public static void main(String[] args) {

        List<String> phones = new ArrayList<String>();
        Collections.addAll(phones, "iPhone X", "Nokia 9",
                "Samsung Galaxy S8", "LG G6", "Xiaomi MI10",
                "ASUS Zenfone 5", "Sony Xperia Z5", "Meizu Pro 6",
                "Pixel 5");

        phones.stream()
                .filter(p->p.length()<12)
                .sorted() // сортировка по возрастанию
                .forEach(s->System.out.println(s));

        System.out.println("---------------------");

        /*
        Однако данный метод не всегда подходит. метод сортирует объекты по возрастанию,
        но при этом заглавные и строчные буквы рассматриваются отдельно.
         */

        Stream<Ch10_Phone> phoneStream = Stream.of(new Ch10_Phone("iPhone X", "Apple", 600),
                new Ch10_Phone("Pixel 2", "Google", 500),
                new Ch10_Phone("iPhone 8", "Apple",450),
                new Ch10_Phone("Nokia 9", "HMD Global",150),
                new Ch10_Phone("Galaxy S9", "Samsung", 300));

        phoneStream.sorted(new PhoneComparator())
                .forEach(p->System.out.printf("%s (%s) - %d \n",
                        p.getName(), p.getCompany(), p.getPrice()));
    }
}

/*
Коллекции, на основе которых нередко создаются потоки,
же имеют специальные методы для сортировки содержимого.

Однако класс Stream также включает возможность сортировки.
Такую сортировку можно задействовать, когда у нас идет набор промежуточных операций с потоком,
которые создают новые наборы данных, и нам надо эти наборы отсортировать.

Для простой сортировки по возрастанию применяется метод sorted():
 */




