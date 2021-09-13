package chapter10_StreamAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class P10_6_SkipAndLimit {

    public static void main(String[] args) {

        Stream<String> phoneStream = Stream.of("iPhone 7", "Lumia 950", "Samsung Galaxy S7", "LG G5", "Nexus 7");

        phoneStream.skip(2)
                .limit(2)
                .forEach(s->System.out.println(s));
        System.out.println("---------------------");

        List<String> phones = new ArrayList<String>(Arrays.asList(
                "iPhone 6S", "Lumia 950", "iPhone 6",
                "Samsung Galaxy S6", "LG G4", "Xiaomi MI 5",
                "ASUS Zenfone 2", "Sony Xperia Z5", "Meizu Pro 5",
                "Lenovo S 850"));

        int pageSize = 3;                       // количество элементов на страницу
        Scanner scanner = new Scanner(System.in);
        while(true){

            System.out.println("Введите номер страницы: ");
            int page = scanner.nextInt();

            if(page<1) break;                   // если число меньше 1, выход из цикла

            phones.stream().skip((page-1) * pageSize)
                    .limit(pageSize)
                    .forEach(s->System.out.println(s));
        }

    }
}

/*
        Метод skip(long n) используется для пропуска n элементов.
        Этот метод возвращает новый поток, в котором пропущены первые n элементов.

        Метод limit(long n) применяется для выборки первых n элементов потоков.
        Этот метод также возвращает модифицированный поток, в котором не более n элементов.

Зачастую эта пара методов используется вместе для создания эффекта постраничной навигации.

Вполне может быть, что метод skip может принимать в качестве параметра число большее,
чем количество элементов в потоке.
В этом случае будут пропущены все элементы, а в результирующем потоке будет 0 элементов.

И если в метод limit передается число, большее, чем количество элементов,
то просто выбираются все элементы потока.
 */
