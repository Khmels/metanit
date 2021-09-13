package chapter05_Collections;

import java.util.LinkedHashMap;
import java.util.Map;

public class P6_8a_Map_HashMap_LinkedHashMap {

    // `java doc`
    //### public class LinkedHashMap<K,V> extends HashMap<K,V>
    //                                      implements Map<K,V>


    /*  HashMap<K,V> constructors // конструкторы:
            LinkedHashMap():	                            по умолчанию знач. начальной емкости (16) и коэффициентом загрузки (0.75)
            LinkedHashMap(int initialCapacity):	            c значением коэф. (0.75) и начальной емкостью initialCapacity
            LinkedHashMap(int initialCapacity, float loadFactor):   с начальной емкости initialCapacity
                                                                    и коэффициентом. загрузки loadFactor
            LinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder):  с начальной емкости initialCapacity,
                                                                                    и коэффициентом загрузки loadFactor.
                                                                                    accessOrder - the ordering mode:
                                                                                    true for access-order,
                                                                                    false for insertion-order
            LinkedHashMap(Map<? extends K,? extends V> m): с определением структуры согласно объекта-параметра.

     */
    public static void main(String[] args) {
        // Представление списка товаров набором значений (key, value)
        Map<String, Double> linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Apple", new Double(91.98));
        linkedHashMap.put("Sony" , new Double(16.76));
        linkedHashMap.put("Dell" , new Double(30.47));
        linkedHashMap.put("HP"   , new Double(33.91));
        linkedHashMap.put("IBM"  , new Double(181.71));

        // Отображение в консоле содержания объекта map linkedHashMap
        System.out.println("Содержимое : " + linkedHashMap);

        // Представление записей набора в цикле
        System.out.println("Значения записей : ");
        for (String key : linkedHashMap.keySet()) {
            System.out.println(key + ":\t" +
                    linkedHashMap.get(key));
        }
        System.out.println("---------------------");

        // Получение значения цены IBM по ключу
        System.out.println("Значение цены IBM : " + linkedHashMap.get("IBM"));
        // Размер linkedHashMap
        System.out .println("Размер linkedHashMap : " + linkedHashMap.size());

        // Проверка наличия записей
        System.out.println("Является ли linkedHashMap пустым ? : " + linkedHashMap.isEmpty());

        // Проверка наличия записей по ключу и значению
        System.out.println("linkedHashMap содержит запись Sony : " + linkedHashMap.containsKey("Sony"));
        System.out.println("LinkedHashMap содержит запись со значением 77.0? : " + linkedHashMap.containsValue(77.0));
        // Удаление записи Dell
        System.out.println("Значение по удаленному ключу: " + linkedHashMap.remove("Dell"));
        System.out.println(linkedHashMap);
        // Очистка набора данных
        linkedHashMap.clear();

        System.out.println("---------------------");

        Map<Integer,String> insertOrder = new LinkedHashMap<>(16,0.75f,false);
        Map<Integer,String> accessOrder = new LinkedHashMap<>(16,0.75f,true);

        insertOrder.put (1,"a");
        insertOrder.put (3,"c");
        insertOrder.put (2,"b");
        insertOrder.put (4,"b");
        String inO = insertOrder.get(3);
//        System.out.println(inO);

        accessOrder.put (1,"a");
        accessOrder.put (3,"c");
        accessOrder.put (2,"b");
        accessOrder.put (4,"b");
        String acO = accessOrder.get(3);
//        System.out.println(acO);
//        System.out.println("---------------------");

        System.out.println("Insert order: " + insertOrder);
        System.out.println("Access order, key 3: " + accessOrder);
        System.out.println("---------------------");
        // Доступ к новому значению (первому)
        inO = insertOrder.get(1);
        acO = accessOrder.get(1);
        System.out.println("Insert order: " + insertOrder);
        System.out.println("Access order, key 1: " + accessOrder); // the most recently accessed key (1) is last
        acO = accessOrder.get(2);
        System.out.println("Access order, key 2: " + accessOrder); // the most recently accessed key (1) is last



    }
}


/*
LinkedHashMap — это упорядоченная реализация хэш-таблицы,
в которой имеются двунаправленные связи между элементами подобно LinkedList.
Это преимущество имеет и недостаток — увеличение памяти, которое занимет коллекция.
 */

/*
The entries of a LinkedHashMap can be iterated either in the order the keys were first added to the Map
(that's the default behavior) or
according to access order (i.e. the most recently accessed entry will be the last entry iterated over).

By passing true to the accessOrder parameter in that constructor,
you are saying you wish to iterate over the entries according to access order (and not insertion order).
 */
