package chapter5_Collections;

import java.util.IdentityHashMap;

public class P6_9a_Map_IdentityHashMap {

    //`java doc`
    //### public class IdentityHashMap<K,V> extends AbstractMap<K,V>
    //                                      implements Map<K,V>, java.io.Serializable, Cloneable

    /* IdentityHashMap constructors // конструкторы
            IdentityHashMap():                      пустой объект с макс.ожидаемым размером по умолчанию (21).
            IdentityHashMap(int expectedMaxSize):   пустой объект с заданным максимальным ожидаемым размером.

                                                    Добавление большего, чем ожидалось, сопоставления expectedMaxSize
                                                    в IdentityHashMap приведет к росту внутренней структуры данных,
                                                    что может занять немного времени.

            IdentityHashMap(Map<? extends K, ? extends V> m): объект с отображениями, скопированными с указанной Map.
     */

    public static void main(String[] args) {
        IdentityHashMap<Integer, String > identityHashMap = new IdentityHashMap<>();
        identityHashMap.putIfAbsent(1,"One");
        identityHashMap.putIfAbsent(2,"One");
        identityHashMap.putIfAbsent(1,"One");
        System.out.println(identityHashMap);
        System.out.println("---------------------");


        String key1 = "Tom";
        String key2 = new String("Tom");
        String key3 = "Tom";

        // key1 == key2 ? false
        System.out.println("key1 == key2 ? " + (key1== key2)); // false
        System.out.println("key1 == key2 ? " + (key1== key3)); // true
        System.out.println("---------------------");

        IdentityHashMap<String, String> map = new IdentityHashMap<String, String>();

        map.put(key1, "Value 1");
        map.put(key2, "Value 2");
        map.put(key3, "Value 3"); //перезапись
        map.put("key4", null);
        map.put(null, null);



        System.out.println("Map Size: " + map.size());
        System.out.println(map);
    }
}

/*
Как говорит Джей Би Низет, разница в соответствующем конструкторе APIs обусловлена "second thoughts" дизайнерами в том,
что было проще всего понять программистам. (Старый способ излишне раскрывает аспекты внутреннего дизайна.)

Таким образом, очевидно, что существует разница в способе управления размером
внутреннего массива в HashMap и IdentityHashMap.

max size = capacity * load factor.
 */


/*
IdentityHashMap<K,V> является классом, который реализует интерфейс Map<K,V>.
IdentityHashMap полностью поддерживает все функции, указанные в интерфейсе Map, включая дополнительные функции.

В основном IdentityHashMap очень похож на HashMap, они используют метод хеширования (hashing technique)
для улучшения доступа к данным и производительности хранения.

Однако они отличаются тем, как хранятся данные и как сравниваются ключи (key).
IdentityHashMap использует оператор == для сравнения двух ключей, в то время как HashMap использует метод equals.
 */

/*
В принципе, все функции IdentityHashMap соответствуют спецификации интерфейса Map,
исключением того, что он использует оператор == для сравнения ключей,
что является незначительным нарушением спецификации интерфейса Map (требуется метод equals для сравнения ключей).
 */
