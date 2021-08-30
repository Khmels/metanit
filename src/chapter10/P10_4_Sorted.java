package chapter10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

        Stream<PhoneFullInfo> phoneStream = Stream.of(new PhoneFullInfo("iPhone X", "Apple", 600),
                new PhoneFullInfo("Pixel 2", "Google", 500),
                new PhoneFullInfo("iPhone 8", "Apple",450),
                new PhoneFullInfo("Nokia 9", "HMD Global",150),
                new PhoneFullInfo("Galaxy S9", "Samsung", 300));

        phoneStream.sorted(new PhoneComparator())
                .forEach(p->System.out.printf("%s (%s) - %d \n",
                        p.getName(), p.getCompany(), p.getPrice()));
    }
}

/*
Коллекции, на основе которых нередко создаются потоки,
же имеют специальные методы для сортировки содержимого.

Однако класс Stream также включает возможность сортировки.
Такую сортировку мы можем задействовать, когда у нас идет набор промежуточных операций с потоком,
которые создают новые наборы данных, и нам надо эти наборы отсортировать.

Для простой сортировки по возрастанию применяется метод sorted():
 */

class PhoneFullInfo{

    private String name;
    private String company;
    private int price;

    public PhoneFullInfo(String name, String comp, int price){
        this.name=name;
        this.company=comp;
        this.price = price;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    public String getCompany() { return company; }
}

class PhoneComparator implements Comparator<PhoneFullInfo> {

    @Override
    public int compare(PhoneFullInfo a, PhoneFullInfo b){

        return a.getName().toUpperCase().compareTo(b.getName().toUpperCase());
    }
}
