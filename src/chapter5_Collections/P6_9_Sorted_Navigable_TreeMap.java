package chapter5_Collections;

import java.util.*;

public class P6_9_Sorted_Navigable_TreeMap {
    //`java doc`
    //### public class TreeMap<K,V> extends AbstractMap<K,V>
    //                              implements NavigableMap<K,V>, Cloneable, java.io.Serializable

    /* TreeMap<K,V> constructors // конструкторы:
            TreeMap():                                  Пустой конструктор создания объекта без упорядочивания данных
                                                        (using the natural ordering of its keys)
            TreeMap(Comparator<? super K> comparator):  создания объекта с упорядочиванием значений согласно comparator
            TreeMap(Map<? extends K, ? extends V> m):   с определением структуры согласно объекту-параметра
                                                        (все элементы из отображения map)
            TreeMap(SortedMap<K, ? extends V> m):       с определением структуры и сортировки согласно объекту-параметра.
                                                        (все элементы и сортировка из отображения map)
     */

    public static void main(String[] args) {
        TreeMap<Integer, String> states = new TreeMap<Integer, String>();
        states.put(10, "Germany");
        states.put(2, "Spain");
        states.put(14, "France");
        states.put(3, "Italy");

        System.out.println(states);     // отсортировано по ключу
        System.out.println("---------------------");

        // получим объект по ключу 2
        String getByKey = states.get(2);
        System.out.println("Get Value by Key: " + getByKey);
        System.out.println("---------------------");

        int key = 5;
        String value= "Germany";
        System.out.println("Мапа содержит ключ " + key + "? Ответ: " + states.containsKey(key));
        System.out.println("Мапа содержит значение? " + value + "? Ответ: " + states.containsValue(value));
        System.out.println("---------------------");

        // перебор элементов
        for(Map.Entry<Integer, String> item : states.entrySet()){

            System.out.printf("Key: %d  Value: %s \n", item.getKey(), item.getValue());
        }
        System.out.println("---------------------");

        // набор ключей
        //Set<Integer> keys = states.keySet();
        System.out.println("Set of Keys: " + states.keySet());
        System.out.println("---------------------");

        // набор всех значений
        //Collection<String> values = states.values();
        System.out.println("Collection of Values: " + states.values());
        System.out.println("---------------------");


        // получаем все объекты, которые стоят после объекта с ключом 4
        Map<Integer, String> tailMap = states.tailMap(4);
        System.out.println("Tail Map: " + tailMap);

        // получаем все объекты, которые стоят после объекта с ключом 4
        Map<Integer, String> tailMapInclude = states.tailMap(3,true);
        System.out.println("Tail Map Include: " + tailMapInclude);


        // получаем все объекты, которые стоят до объекта с ключом 10
        Map<Integer, String> headMap = states.headMap(10);
        System.out.println("Head Map: " +  headMap);

        // получаем все объекты, которые стоят до объекта с ключом 10
        Map<Integer, String> headMapInclude = states.headMap(10, true);
        System.out.println("Head Map Include: " +  headMapInclude);

        // первый с удалением
        Map.Entry<Integer, String> cropFirstElement = states.pollFirstEntry();
        System.out.println(cropFirstElement.toString());

        // последний элемент дерева
        Map.Entry<Integer, String> lastItem = states.lastEntry();
        System.out.println("---------------------");

        System.out.printf("Last item has key %d value %s \n",lastItem.getKey(), lastItem.getValue());
        System.out.println("---------------------");

        SortedMap <Integer, String> sortedMap = states.subMap(1,true,14,false);
        TreeMap<Integer, String> treeMap = new TreeMap<>(sortedMap);
        System.out.println(sortedMap.keySet().toString() + "\n" + sortedMap.values().toString());

        System.out.println("---------------------");
        Map<String, Ch5_Person_public> people = new TreeMap<String, Ch5_Person_public>();
        people.put("1240i5", new Ch5_Person_public("Tom"));
        people.put("1564i5", new Ch5_Person_public("Bill"));
        people.put("4540i5", new Ch5_Person_public("Nick"));

        for(Map.Entry<String, Ch5_Person_public> item : people.entrySet()){

            System.out.printf("Key: %s  Value: %s \n", item.getKey(), item.getValue().getName());
        }

        //??? Дубликаты    --- DENIED Key

        people.put("1564i5", new Ch5_Person_public("Bill"));
        Ch5_Person_public dupPersonTree = new Ch5_Person_public("Duplicate");
        people.put("4540i57", dupPersonTree);
        people.put("4540i58", dupPersonTree);
        for(Map.Entry<String, Ch5_Person_public> item : people.entrySet()){

            System.out.printf("Key: %s  Value: %s \n", item.getKey(), item.getValue().getName());
        }
        System.out.println("---------------------");

        //??? null key      --- DENIED

        //people.put(null, new Ch5_Person_public("NullKey"));
        for(Map.Entry<String, Ch5_Person_public> item : people.entrySet()){

            System.out.printf("Key: %s  Value: %s \n", item.getKey(), item.getValue().getName());
        }
        System.out.println("---------------------");

        //??? null value      --- ALLOWED

        people.put("4540i59", null);
        //people.put(null, null);
        for(Map.Entry<String, Ch5_Person_public> item : people.entrySet()){

            System.out.printf("Key: %s  Value: %s \n", item.getKey(), item.getValue());
        }
        System.out.println("---------------------");

        /*  Кроме собственно методов интерфейса Map класс TreeMap реализует методы интерфейса NavigableMap.
        Например, получить все объекты до или после определенного ключа с помощью методов headMap и tailMap.
        Также получить первый и последний элементы и провести ряд дополнительных манипуляций с объектами.
         */

    }
}

/*

//`java doc`
//### public interface SortedMap<K,V> extends Map<K,V> {}

/*
Интерфейс SortedMap расширяет Map и создает отображение,
в котором все элементы отсортированы в порядке возрастания их ключей.
*/

/*SortedMap добавляет ряд методов:

        K firstKey():                       возвращает ключ первого элемента отображения
        K lastKey():                        возвращает ключ последнего элемента отображения
        SortedMap<K, V> headMap(K end):     возвращает отображение SortedMap, которые содержит все элементы
                                            оригинального SortedMap вплоть до элемента с ключом end
        SortedMap<K, V> tailMap(K start):   возвращает отображение SortedMap, которые содержит все элементы
                                            оригинального SortedMap, начиная с элемента с ключом start
        SortedMap<K, V> subMap(K start, K end): возвращает отображение SortedMap, которые содержит все элементы
                                            оригинального SortedMap вплоть от эл. с ключом start до эл. с ключом end

        Comparator comparator()            Возвращается компаратор вызывающей отсортированной Map.
                                            Если для вызванного Map используется естественный порядок, возвращается null.
*/

//`java doc`
//### public interface NavigableMap<K,V> extends SortedMap<K,V> {}

/*
Интерфейс NavigableMap расширяет интерфейс SortedMap и
беспечивает возможность получения элементов отображения относительно других элементов.
*/

/* NavigableMap основные методы:
        Map.Entry<K, V> ceilingEntry(K obj):    возвращает элемент с наименьшим ключом k,
                                                который больше или равен ключу obj (k >=obj).
                                                Если такого ключа нет, то возвращается null.

        Map.Entry<K, V> floorEntry(K obj):      возвращает элемент с наибольшим ключом k,
                                                который меньше или равен ключу obj (k <=obj).
                                                Если такого ключа нет, то возвращается null.

        Map.Entry<K, V> higherEntry():          возвращает элемент с наименьшим ключом k,
                                                который больше ключа obj (k >obj).
                                                Если такого ключа нет, то возвращается null.

        Map.Entry<K, V> lowerEntry():           возвращает элемент с наибольшим ключом k,
                                                который меньше ключа obj (k <obj).
                                                Если такого ключа нет, то возвращается null.

        Map.Entry<K, V> firstEntry():       возвращает первый элемент отображения
        Map.Entry<K, V> lastEntry():        возвращает последний элемент отображения
        Map.Entry<K, V> pollFirstEntry():   возвращает и одновременно удаляет первый элемент из отображения
        Map.Entry<K, V> pollLastEntry():    возвращает и одновременно удаляет последний элемент из отображения

        K ceilingKey(K obj):                возвращает наименьший ключ k, который больше или равен ключу obj (k >=obj).
                                                Если такого ключа нет, то возвращается null.
        K floorKey(K obj):                  возвращает наибольший ключ k, который меньше или равен ключу obj (k <=obj).
                                                Если такого ключа нет, то возвращается null.
        K lowerKey(K obj):                  возвращает наибольший ключ k, который меньше ключа obj (k <obj).
                                                Если такого ключа нет, то возвращается null.
        K higherKey(K obj):                 возвращает наименьший ключ k, который больше ключа obj (k >obj).
                                                Если такого ключа нет, то возвращается null.

        NavigableSet<K> descendingKeySet(): возвращает объект NavigableSet,
                                            который содержит все ключи отображения в обратном порядке

        NavigableSet<K> navigableKeySet():  возвращает объект NavigableSet,
                                            который содержит все ключи отображения

        NavigableMap<K, V> descendingMap(): возвращает отображение NavigableMap,
                                            которое содержит все элементы в обратном порядке

        NavigableMap<K, V> headMap(K upperBound, boolean incl): возвращает отображение NavigableMap, которое содержит
                                                                все элементы оригинального NavigableMap
                                                                вплоть от элемента с ключом upperBound.
                                                                Параметр incl при значении true указывает, что элемент
                                                                с ключом upperBound также включается в выходной набор.

        NavigableMap<K, V> tailMap(K lowerBound, boolean incl): возвращает отображение NavigableMap, которое содержит
                                                                все элементы оригинального NavigableMap,
                                                                начиная с элемента с ключом lowerBound.
                                                                Параметр incl при значении true указывает, что элемент
                                                                с ключом lowerBound также включается в выходной набор.

        NavigableMap<K, V> subMap(K lowerBound, boolean lowIncl, K upperBound, boolean highIncl): возвращает отображение
                                                                NavigableMap, которое содержит все элементы
                                                                оригинального NavigableMap от элемента
                                                                с ключом lowerBound до элемента с ключом upperBound.
                                                                Параметры lowIncl и highIncl при значении true включают
                                                                в выходной набор элементы с ключами
                                                                lowerBound и upperBound соответственно.

 */

