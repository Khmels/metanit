package chapter05_Collections;

import java.util.HashMap;
import java.util.WeakHashMap;

public class P6_9a_Map_WeakHashMap {

    //`java doc`
    //### public class WeakHashMap<K,V> extends AbstractMap<K,V>
    //                                  implements Map<K,V> {

    /* WeakHashMap constructors // конструкторы
        WeakHashMap(int initialCapacity, float loadFactor):     empty, with the given initial capacity and the given load factor.
        WeakHashMap(int initialCapacity):                       empty, with the given initial capacity and the default load factor (0.75)
        WeakHashMap():                                          empty, with the default initial capacity (16) and load factor (0.75)
        WeakHashMap(Map<? extends K, ? extends V> m):           with the same mappings as the specified map.
                                                                The WeakHashMap is created with the default load factor (0.75)
                                                                and an initial capacity sufficient to hold the
                                                                mappings in the specified map
     */

    // betacode.net/13583/java-weakhashmap

    public static void main(String[] args) throws InterruptedException {
        WeakHashMap<Integer, String> weakHashMap = new WeakHashMap<>();

        // (1) Create a reference objK1 points to AnyObject("S1") object.
        Ch5_AnyObject objK1 = new Ch5_AnyObject("S1");
        Ch5_AnyObject objK2 = new Ch5_AnyObject("S2");

        // Create WeakHashMap object:
        WeakHashMap<Ch5_AnyObject,Integer> map = new  WeakHashMap<Ch5_AnyObject,Integer>();

        map.put(objK1, 1000);
        map.put(objK2, 2000);

        System.out.println(map);

        // (2) Set reference objK1 to null.
        objK1 = null;

        // Garbage Collector is called
        System.gc();
        System.out.println("\n --- After GC (WeakHashMap): --- \n");

        Thread.sleep(3000);
        System.out.println(map);

        //(1) - создается ссылка objK1 и указывается на объект AnyObject("S1"), который является ключом WeakHashMap.
        //(2) - ссылка objK1 указывает на нулевое значение null. В настоящее время больше нет никаких сильных ссылок,
        // указывающих на объект AnyObject("S1").
        //
        // Хотя он используется в качестве ключа of WeakHashMap,
        // он по-прежнему считается подходящим для удаления сборщиком мусора (Garbage Collector).
        // Поскольку GC считается более сильным, чем WeakHashMap.

        System.out.println("---------------------");
        System.out.println();
        System.out.println();
        // --- ОБЫчНЫЙ HashMap ---

        // Create HashMap object:
        Ch5_AnyObject objK3 = new Ch5_AnyObject("S1");
        Ch5_AnyObject objK4 = new Ch5_AnyObject("S2");

        HashMap<Ch5_AnyObject,Integer> hashMap = new  HashMap<Ch5_AnyObject,Integer>();

        hashMap.put(objK3, 1000);
        hashMap.put(objK4, 2000);

        System.out.println(hashMap);

        // (2) Set reference objK1 to null.
        objK3 = null;

        // Garbage Collector is called
        System.gc();
        System.out.println("\n --- After GC (HashMap): --- \n");

        Thread.sleep(3000);
        System.out.println(hashMap);
        System.out.println("---------------------");
        System.out.println();
        System.out.println();

        // ------------ Primitive Keys ------------------------
        /*
        Integer key1 = 1000;
        Double key2 = 2000.2;
        String key3 = "SomeKey";
         */

        /*
        Объекты, созданные из примитивных значений, которые не используют оператор "new",
        не будут удалены by GC, если он используется в качестве ключа of WeakHashMap
         */

        // (1) Create a reference objI1 points to a primitive value.
        Integer objI1 = 100;
        Integer objI2 = 200;

        // Create WeakHashMap object:
        WeakHashMap<Integer, String> mapPrimitive = new WeakHashMap<Integer, String>();

        mapPrimitive.put(objI1, "One Hundred");
        mapPrimitive.put(objI2, "Two Hundred");

        System.out.println(mapPrimitive);

        // (2) Set reference objI1 to null.
        objI1 = null;

        // Garbage Collector is called
        System.gc();
        System.out.println("\n --- After GC (Primitive Keys): --- \n");

        Thread.sleep(5000);
        System.out.println(mapPrimitive);
        System.out.println("---------------------");
        System.out.println();
        System.out.println();

        // ------------ Object Keys ------------------------
        /*
        AnyObject objK1 = new AnyObject("S1");
        String objK2 = new String("S2");
        Integer objK3 = new Integer(1000);
        String objK4 = new String("S4");
         */

        /*
        Объекты, созданные с помощью оператора "new", будут удалены из памяти by GC, если нет сильной ссылки,
        указывающей на него, даже если он используется в качестве ключа of WeakHashMap.
        Как уже упоминалось выше, GC считается более сильным, чем WeakHashMap.
         */

        // (1) Create a reference objK5 points to String object.
        Ch5_AnyObject objK5 = new Ch5_AnyObject("S1");
        String objK6 = new String("S2");
        Integer objK7 = new Integer(1000);
        String objK8 = new String("S4");

        // Create WeakHashMap object:
        WeakHashMap<Object,Integer> mapObjects = new  WeakHashMap<Object,Integer>();

        mapObjects.put(objK5, 1000);
        mapObjects.put(objK6, 2000);
        mapObjects.put(objK7, 3000);
        mapObjects.put(objK8, 4000);

        System.out.println(mapObjects);

        // (2) Set references objK5, objK6, objK7 to null.
        objK5 = null;
        objK6 = null;
        objK7 = null;

        // Garbage Collector is called
        System.gc();
        System.out.println("\n --- After GC (Object Keys): --- \n");

        Thread.sleep(3000);
        System.out.println(mapObjects);
        System.out.println();
        System.out.println();
        /*
        Объекты, созданные оператором "new", будут удалены из памяти by GC,
        программе требуется больше памяти и если нет сильных ссылок, указывающих на нее.
        В этом случае вам не нужно активно вызывать метод System.gc().
         */

        //--------- Пример про память ---------

        // (1) Create a reference objK1 points to String object.
        Ch5_AnyObject obj1 = new Ch5_AnyObject("S1");
        String obj2 = new String("S2");
        Integer obj3 = new Integer(1000);
        String obj4 = new String("S4");
        String obj5 = "S5"; // Primitive Key

        // Create WeakHashMap object:
        WeakHashMap<Object,Integer> weakHashMap1 = new  WeakHashMap<Object,Integer>();

        weakHashMap1.put(obj1, 1000);
        weakHashMap1.put(obj2, 2000);
        weakHashMap1.put(obj3, 3000);
        weakHashMap1.put(obj4, 4000);
        weakHashMap1.put(obj5, 5000);

        int ORIGIN_MAP_SIZE = weakHashMap1.size();

        System.out.println(weakHashMap1);

        int i = 0;
        while(true)  {
            if(weakHashMap1.size() < ORIGIN_MAP_SIZE) {
                System.out.println("WeakHashMap Size: " + weakHashMap1.size());
                System.out.println(weakHashMap1);
                break;
            }
            i++;
            // (2) Make the memory full by creating lots of Strings
            String s = new String("String" + i);
            System.out.println("Create new String: " + s);
            System.out.println("   >>> Now WeakHashMap size is: " + map.size());
        }

        // `В принципе, WeakHashMap - это решение для экономии памяти, которое полезно,
        // если вам нужен объект Map для хранения сопоставлений для ключей, которые вы знаете одновременно.
        //
        // Ключи, которые больше не нужны, автоматически удаляются by GC.

    }

}

/*
WeakHashMap - класс, аналогичный классу HashMap, используют метод хеширования для хранения и извлечения данных.

Разница в том, что если объект указан в качестве ключа of WeakHashMap,
он может быть удален из памяти Garbage Collector (GC) (сборщиком мусора), если он больше не используется в другом месте,
более сильном, чем GC.

Как только ключ удаляется by GC, соответствующее отображение также удаляется из WeakHashMap.
В обычном смысле, если объект где-то используется, он полезен и не может быть удален из памяти.

Однако WeakHashMap специально разработан и считается более слабым, чем GC, объектом,
которого все еще может быть удален из памяти.
 */

/*
Garbage Collector of Java работает автоматически для удаления неиспользуемых объектов из памяти.
Вы можете активно вызывать его с помощью метода System.gc(), но нет никакой гарантии, что он вступит в силу немедленно.
 */
