package chapter10_StreamAPI;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class P10_10_Collect {
    public static void main(String[] args) {


        //--- Первая версия метода collect

        /*   Первая версия метода принимает в качестве параметра функцию преобразования к коллекции:
                    <R,A> R collect(Collector<? super T,A,R> collector)

            Параметр R представляет тип результата метода,
            параметр Т - тип элемента в потоке,
            параметр А - тип промежуточных накапливаемых данных.

            В итоге параметр collector представляет функцию преобразования потока в коллекцию.
        */

        List<String> phonesList = new ArrayList<String>();
        Collections.addAll(phonesList, "iPhone 8", "HTC U12", "Huawei Nexus 6P",
                "Samsung Galaxy S9", "LG G6", "Xiaomi MI6", "ASUS Zenfone 2",
                "Sony Xperia Z5", "Meizu Pro 6", "Lenovo S850");

        List<String> filteredPhonesList = phonesList.stream()
                .filter(s->s.length()<10)
                .collect(Collectors.toList());

        for(String s : filteredPhonesList){
            System.out.println(s);
        }
        System.out.println("---------------------");

        Set<String> filteredPhonesSet = phonesList.stream()
                .filter(s->s.length()<20)
                .collect(Collectors.toSet());

        for(String s : filteredPhonesSet){
            System.out.println(s);
        }
        System.out.println("---------------------");

        // Для применения метода toMap() надо задать ключ и значение.

        Stream<Ch10_Phone> phonesStream1 = Stream.of(new Ch10_Phone[]{
                new Ch10_Phone("iPhone 8", 52000),
                new Ch10_Phone("Nokia 9", 35000),
                new Ch10_Phone("Samsung Galaxy S9", 48000),
                new Ch10_Phone("HTC U12", 36000)});


        Map<String, Integer> phones = phonesStream1
                .collect(Collectors.toMap(p->p.getName(), t->t.getPrice()));
        // Лямбда-выражение p->p.getName()  - получает значение для ключа элемента,
        //                  t->t.getPrice() - извлекает значение элемента.

        phones.forEach((k,v)->System.out.println(k + " " + v));
        System.out.println("---------------------");

        // Если нам надо создать какой-то определенный тип коллекции, например, HashSet,
        // то можно использовать специальные функции, которые определены в классах-коллекций.
        // toCollection(HashSet::new)

        Stream<String> phonesStream2 = Stream.of("iPhone 8", "HTC U12", "Huawei Nexus 6P",
                "Samsung Galaxy S9", "LG G6", "Xiaomi MI6", "ASUS Zenfone 2",
                "Sony Xperia Z5", "Meizu Pro 6", "Lenovo S850");

        HashSet<String> filteredPhones = phonesStream2.filter(s->s.length()<20).
                collect(Collectors.toCollection(HashSet::new));

        filteredPhones.forEach(s->System.out.println(s));
        System.out.println("---------------------");

        // Аналогичным образом можно получать другие коллекции, например, ArrayList:
        // ArrayList<String> result = phones.collect(Collectors.toCollection(ArrayList::new));

        //--- Вторая форма метода collect имеет три параметра:

        /*
        Вторая форма метода collect имеет три параметра:
                <R> R collect(Supplier<R> supplier, BiConsumer<R,? super T> accumulator, BiConsumer<R,R> combiner)

            supplier: создает объект коллекции
            accumulator: добавляет элемент в коллекцию
            combiner: бинарная функция, которая объединяет два объекта
         */

        Stream<String> phonesStream3 = Stream.of("iPhone 8", "HTC U12", "Huawei Nexus 6P",
                "Samsung Galaxy S9", "LG G6", "Xiaomi MI6", "ASUS Zenfone 2",
                "Sony Xperia Z5", "Meizu Pro 6", "Lenovo S850");

        ArrayList<String> filteredPhonesListCollect = phonesStream3.filter(s->s.length()<12)
                .collect(
                        ()->new ArrayList<String>(),            // создает ArrayList
                        (list, item)->list.add(item),           // добавляет в список элемент
                        (list1, list2)-> list1.addAll(list2));  // добавляет в список другой список

        filteredPhones.forEach(s->System.out.println(s));
        System.out.println("---------------------");
    }
}

/*
Большинство операций класса Stream, которые модифицируют набор данных, возвращают этот набор в виде потока.

Однако бывают ситуации, когда хотелось бы получить данные не в виде потока,
а в виде обычной коллекции, например, ArrayList или HashSet.
И для этого у класса Stream определен метод collect.
*/



/*
Эта функция представляет объект Collector, который определен в пакете java.util.stream.
Можно написать свою реализацию функции, однако Java уже предоставляет ряд встроенных функций,
определенных в классе Collectors:

toList(): преобразование к типу List
toSet(): преобразование к типу Set
toMap(): преобразование к типу Map
 */