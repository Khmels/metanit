package chapter5;

import java.util.*;

public class P9a_Map_EnumMap {

    //`java doc`
    //### public class EnumMap<K extends Enum<K>, V> extends AbstractMap<K, V>
    //                                               implements java.io.Serializable, Cloneable

    /* EnumMap constructors // конструкторы
            EnumMap(Class<K> keyType):          аргумент - Enum
            EnumMap(EnumMap<K, ? extends V> m): аргумент -  EnumMap
            EnumMap(Map<K, ? extends V> m):     аргумент -  Map.
                                                The specified map must contain at least one mapping (not empty)
                                                (in order to determine the new enum map's key type from an existing entry)
                                                If the specified map contains more than one enum type,
                                                the constructor will throw ClassCastException.
     */

    public static void main(String[] args) {
        EnumMap<DayOfWeek, String> activityMap = new EnumMap<>(DayOfWeek.class);

        activityMap.put(DayOfWeek.MONDAY, "Soccer");
        activityMap.put(DayOfWeek.TUESDAY, "Basketball");
        System.out.println(activityMap);
        System.out.println("---------------------");

        EnumMap<DayOfWeek, String> activityMapCopy = new EnumMap<>(activityMap);
        System.out.println(activityMapCopy.size());
        System.out.println(activityMapCopy.get(DayOfWeek.MONDAY));
        System.out.println(activityMapCopy.containsValue("Basketball"));
        System.out.println("---------------------");

        Map<DayOfWeek, String> ordinaryMap = new HashMap<>();
        ordinaryMap.put(DayOfWeek.MONDAY, "Tennis");
        System.out.println(ordinaryMap);
        System.out.println("---------------------");

        EnumMap<DayOfWeek,String> enumMap = new EnumMap<>(ordinaryMap);
        enumMap.putAll(activityMap); //перезапись по ключу
        enumMap.put(DayOfWeek.WEDNESDAY, "Hiking");
        System.out.println(enumMap);
        System.out.println("---------------------");

        //? null as Value - ALLOWED
        System.out.println("Contains Saturday? " + activityMap.containsKey(DayOfWeek.SATURDAY));
        System.out.println("Contains null value? "  + activityMap.containsValue(null));
        System.out.println(activityMap.put(DayOfWeek.SATURDAY, null)); // print value = null
        System.out.println("Contains Saturday? " + activityMap.containsKey(DayOfWeek.SATURDAY));
        System.out.println("Contains null value? "  + activityMap.containsValue(null));

        activityMap.remove(DayOfWeek.MONDAY);   //  remove(key) returns the previous value associated with the key,
                                                // or null if there was no mapping for the key.
        System.out.println(activityMap);
        System.out.println("---------------------");

        // remove(key, value) removes the entry for the specified key
        // only if the key is currently mapped to the specified value.
        activityMap.put(DayOfWeek.MONDAY, "Soccer");
        System.out.println(activityMap.remove(DayOfWeek.MONDAY, "Hiking")); // false
        System.out.println(activityMap.remove(DayOfWeek.MONDAY, "Soccer")); // true

        activityMap.put(DayOfWeek.MONDAY, "Soccer");
        activityMap.put(DayOfWeek.WEDNESDAY, "Hiking");
        activityMap.put(DayOfWeek.THURSDAY, "Karate");
        activityMap.put(DayOfWeek.FRIDAY, "Chess");
        activityMap.put(DayOfWeek.SUNDAY, "Baseball");


        //like with ordinary maps, with any EnumMap, we can have 3 different views or sub-collections.
        Collection<String> values = activityMap.values();
        System.out.println("Values: " + values);

        Set<DayOfWeek> enumSet =  activityMap.keySet();
        System.out.println("Keys: " + enumSet);

        Set<Map.Entry<DayOfWeek, String>> entries= activityMap.entrySet();
        System.out.println("Entries: " + "\n" + entries);
    }
}

/*
EnumMap is a Map implementation that exclusively takes Enum as its keys.
 */


