package chapter5;

import org.w3c.dom.ls.LSOutput;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class P8_Map_HashMap {

    // `java doc`
    //### public class HashMap<K,V> extends AbstractMap<K,V>
    //                              implements Map<K,V>, Cloneable, Serializable {


    /* HashMap constructors // конструкторы:
            HashMap(int initialCapacity, float loadF):  создает стандартный HashMap по умолчанию
            HashMap(int initialCapacity):               инициализирует емкость хэш-карты для заданного capacity.
            HashMap() :                                 создает дерево, в которое добавляет эл. сортированного набора set
            HashMap(Map<? extends K, ? extends V> m):   инициализирует хэш-мэп, используя элементы Map данного объекта m.
     */

    public static void main(String[] args) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Первый", 1);
        hashMap.put("Второй", 2);
        hashMap.put("Третий", 3);

        System.out.println(hashMap); // нет ни порядка, ни сортировки
        System.out.println("---------------------");
        System.out.println("EntrySet: \n" + hashMap.entrySet());
        System.out.println("---------------------");

        System.out.println(hashMap.putIfAbsent("Червертый", 4)); // вставилось новое значение, возврат null
        System.out.println(hashMap);
        System.out.println(hashMap.putIfAbsent("Червертый", 5)); // вставилось новое значение, возврат Value по Key - 4
        System.out.println(hashMap);

        System.out.println("---------------------");

        HashMap<Integer, String> hashMap1 = new HashMap<>();
        hashMap1.put(1,"Один");
        hashMap1.put(2,"Два");
        hashMap1.put(3,"Три");
        hashMap1.put(4,"Четыре");

        hashMap1.put(4, "Сорок четыре");

        System.out.println(hashMap1); // перезаписывает по ключу

        System.out.println("---------------------");

        Map<Integer, String> states = new HashMap<Integer, String>();
        states.put(5, "Germany");
        states.put(1, "Spain");
        states.put(4, "France");
        states.put(3, "Italy");
        states.put(2, "Italy");


        // объект по ключу 2
        String getByKey = states.get(2);
        System.out.println("Get Value by Key: " + getByKey);

        int key = 1;
        String value= "Italy";
        System.out.println("Мапа содержит ключ " + key + "? Ответ: " + states.containsKey(key));
        System.out.println("Мапа содержит значение? " + value + "? Ответ: " + states.containsValue(value));
        System.out.println("---------------------");

        // весь набор ключей
        Set<Integer> keys = states.keySet();
        System.out.println("Set of Keys: " + keys);
        System.out.println("---------------------");


        // получить набор всех значений
        Collection<String> values = states.values();
        System.out.println("Collection of Values: " + values);
        System.out.println("---------------------");

        Integer integer1 = 1;
        Integer integer2 = 2;
        Integer integer3 = 3;
        Integer integer4 = new Integer(4);
        System.out.printf("Sorted object?      %s <=> %s\n", integer1.hashCode(), integer2.hashCode());

        //заменить элемент
        states.replace(1, "Poland");
        // удаление элемента по ключу 2
        states.remove(2);

        // перебор элементов
        for(Map.Entry<Integer, String> item : states.entrySet()){

            System.out.printf("Key: %d  Value: %s \n", item.getKey(), item.getValue());
        }

        Map<String, Ch5_Person_public> people = new HashMap<String, Ch5_Person_public>();
        people.put("1240i54", new Ch5_Person_public("Tom"));
        people.put("1564i55", new Ch5_Person_public("Bill"));
        people.put("4540i56", new Ch5_Person_public("Nick"));

        /*
        Чтобы добавить или заменить элемент, используется метод put, либо replace,
        а чтобы получить его значение по ключу - метод get.

        С помощью других методов интерфейса Map также производятся другие манипуляции над элементами:
        перебор, получение ключей, значений, удаление.
         */

        for(Map.Entry<String, Ch5_Person_public> item : people.entrySet()){

            System.out.printf("Key: %s  Value: %s \n", item.getKey(), item.getValue().getName());
        }
        System.out.println("---------------------");

        //??? Дубликаты    --- DENIED Key // перезапись

        people.put("4540i56", new Ch5_Person_public("Nick"));
        Ch5_Person_public dupPerson = new Ch5_Person_public("Duplicate");
        people.put("4540i57", new Ch5_Person_public("Duplicate"));
        people.put("4540i58", new Ch5_Person_public("Duplicate"));
        for(Map.Entry<String, Ch5_Person_public> item : people.entrySet()){

            System.out.printf("Key: %s  Value: %s \n", item.getKey(), item.getValue().getName());
        }
        System.out.println("---------------------");

        //??? null key      --- ALLOWED

        people.put(null, new Ch5_Person_public("NullKey"));
        people.put(null, new Ch5_Person_public("NullKey2")); // перезапись
        for(Map.Entry<String, Ch5_Person_public> item : people.entrySet()){

            System.out.printf("Key: %s  Value: %s \n", item.getKey(), item.getValue().getName());
        }
        System.out.println("---------------------");

        //??? null value      --- ALLOWED

        people.put("4540i59", null);
        people.put(null, null);         // перезапись
        for(Map.Entry<String, Ch5_Person_public> item : people.entrySet()){

            System.out.printf("Key: %s  Value: %s \n", item.getKey(), item.getValue());
        }
        System.out.println("---------------------");
    }
}

/*
Отображение (или карта) представляет собой объект, сохраняющий связи между ключами и значениями в виде пар "ключ-значение".
По заданному ключу можно найти его значение.

Ключи и значения являются объектами. Ключи должны быть уникальными, а значения могут быть дублированными.
В одних отображениях допускаются null ключи и null значения, а в других - они не допускаются.

Уникальность ключей определяет реализация метода equals().
Для корректной работы с картами необходимо переопределить методы equals() и hashCode().
Допускается добавление объектов без переопределения этих методов, но найти эти объекты в Map вы не сможете.
 */



/*  Интерфейс Map<K, V> представляет отображение или иначе говоря словарь,
    где каждый элемент представляет пару "ключ-

    При этом все ключи уникальные в рамках объекта Map.
    Такие коллекции облегчают поиск элемента, если нам известен ключ - уникальный идентификатор объекта.
 */

//`java doc`
//### public interface Map<K, V>

/* Среди методов интерфейса Map можно выделить следующие:

        void clear():                           очищает коллекцию
        boolean containsKey(Object k):          возвращает true, если коллекция содержит ключ k
        boolean containsValue(Object v):        возвращает true, если коллекция содержит значение v
        Set<Map.Entry<K, V>> entrySet():        возвращает набор элементов коллекции. Все элементы представляют объект Map.Entry
        boolean equals(Object obj):             возвращает true, если коллекция идентична коллекции, передаваемой через параметр obj
        boolean isEmpty:                        возвращает true, если коллекция пуста
        V get(Object k):                        возвращает значение объекта, ключ которого равен k.
                                                    Если такого элемента не окажется, то возвращается значение null
        V getOrDefault(Object k, V defaultValue): возвращает значение объекта, ключ которого равен k.
                                                    Если такого элемента не окажется, то возвращается значение defaultVlue
        V put(K k, V v):                        помещает в коллекцию новый объект с ключом k и значением v.
                                                    Если в коллекции уже есть объект с подобным ключом, то он перезаписывается.
                                                        После добавления возвращает предыдущее значение для ключа k,
                                                        если он уже был в коллекции.
                                                    Если же ключа еще не было в коллекции, то возвращается значение null
        V putIfAbsent(K k, V v):                помещает в коллекцию новый объект с ключом k и значением v,
                                                    если в коллекции еще нет элемента с подобным ключом.
        Set<K> keySet():                        возвращает набор всех ключей отображения
        Collection<V> values():                 возвращает набор всех значений отображения
        void putAll(Map<? extends K, ? extends V> map): добавляет в коллекцию все объекты из отображения map
        V remove(Object k):                     удаляет объект с ключом k
        int size():                             возвращает количество элементов коллекции
 */

/*
Чтобы положить объект в коллекцию, используется метод put, а чтобы получить по ключу - метод get.
Реализация интерфейса Map также позволяет получить наборы как ключей, так и значений.
А метод entrySet() возвращает набор всех элементов в виде объектов Map.Entry<K, V>.

Обобщенный интерфейс Map.Entry<K, V> представляет объект с ключом типа K и значением типа V.
*/

/* Map.Entry<K, V> Определяет следующие методы:

        boolean equals(Object obj):             возвращает true, если obj, представляющий интерфейс Map.Entry, идентичен текущему
        K getKey():                             возвращает ключ объекта отображения
        V getValue():                           возвращает значение объекта отображения
        V setValue(V v):                        устанавливает для текущего объекта значение v
        int hashCode():                         возвращает хеш-код данного объекта
 */

/*
Базовым классом для всех отображений является абстрактный класс AbstractMap,
который реализует большую часть методов интерфейса Map.

Наиболее распространенным классом отображений является HashMap,
который реализует интерфейс Map и наследуется от класса AbstractMap.
 */



