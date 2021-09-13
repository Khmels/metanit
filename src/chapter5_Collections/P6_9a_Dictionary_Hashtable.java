package chapter5_Collections;

import java.util.Enumeration;
import java.util.Hashtable;

public class P6_9a_Dictionary_Hashtable {

    //`java doc`
    //### public class Hashtable<K,V> extends Dictionary<K,V>
    //                                implements Map<K,V>, Cloneable, java.io.Serializable {

    /* Hashtable constructors // конструкторы:
            Hashtable(int initialCapacity, float loadFactor):   empty hashtable with the specified initial capacity
                                                                    and the specified load factor
            Hashtable(int initialCapacity):                     empty hashtable with the specified initial capacity
                                                                    and default load factor (0.75)
            Hashtable():                                        empty Hashtable, default initial capacity (11), load factor (0.75)
            Hashtable(Map<? extends K, ? extends V> t):         with the same mappings as the given Map.
                                                                    The hashtable is created with an initial capacity sufficient to
                                                                hold the mappings in the given Map and a default load factor (0.75).
    */

    public static void main(String[] args) {
        Hashtable<String, Double> hashtable = new Hashtable<>();

        hashtable.put("Маша", new Double(3434.34));
        hashtable.put("Катя", new Double(123.22));
        hashtable.put("Олег", new Double(1378.00));
        hashtable.put("Денис", new Double(99.22));
        hashtable.put("Антон", new Double(-19.08));

        // Показывает все балансы в хэш-таблицы.
        Enumeration names = hashtable.keys();

        while(names.hasMoreElements()) {
            String str = (String) names.nextElement();
            System.out.println(str + ": " + hashtable.get(str));
        }
        System.out.println();

        // Вносим 1,000 в аккаунт Маши.
        double bal = hashtable.get("Маша");
        hashtable.put("Маша", new Double(bal + 1000));
        System.out.println("Новый баланс для Маши: " + hashtable.get("Маша"));

    }
}

//`java doc`
//### public abstract class Dictionary<K,V>

//Constructor Dictionary()

/*
Является абстрактным классом, который представляет хранение ключей/значений, работает также, как Map.
Класс Dictionary устаревший. Вы должны реализовать интерфейс Map, чтобы получить функционал хранения ключей/значений.
 */

/* Методы

    Enumeration elements():     Возвращает перечислений значений, содержащихся в словаре.
    Object get(Object key):     Возвращает объект, который содержит значение, связанное с ключом.
                                Если ключ не находится в словаре, возвращается нулевой объект.
    boolean isEmpty()           Возвращает true, если словарь пустой. Возвращает false, если содержит как минимум один ключ.
    Enumeration keys():         Возвращает перечисление ключей, содержащихся в словаре.
    Object put(Object key, Object value): Вставляет ключ и его значение в словарь.
                                Возвращает ноль, если ключ еще не находится в словаре, возвращает значение,
                                связанное с ключом, если ключ уже в словаре.
	Object remove(Object key):  Удаляет ключ и его значение. Возвращает значение, связанное с ключом.
	                            Если ключ не находится в словаре, возвращается ноль.
	int size():                 Возвращает количество записей в словаре.
 */

/*
    Интерфейс Enumeration –     определяет методы, с помощью которых можно перечислить элементы в коллекции объектов.
                                Этот устаревший интерфейс был заменен Iterator.

    The main difference between Iterator and Enumeration is removal of the element while traversing the collection.
    Iterator can remove the element during traversal of collection as it has remove() method.
    Enumeration does not have remove() method.

    Enumeration works on legacy java classes Vector, Stack and HashTable etc(These are Synchronized collections).

    Enumeration                     Iterator
    ----------------                ----------------
    hasMoreElements()               hasNext()
    nextElement()                   next()
    N/A                             remove()

    Partly true:
    2) Enumeration is fail-safe in nature.
    It does not throw ConcurrentModificationException if Collection is modified during the traversal.

    Iterator is fail-fast in nature.
    It throws ConcurrentModificationException if a Collection is modified while iterating other than its own remove() method.
 */

/*
Класс Hashtable в Java был частью оригинального java.util и представляет собой конкретную реализацию Dictionary.

Однако, Java 2 переработал Hashtable, чтобы он также реализовал интерфейс Map.
Таким образом, Hashtable теперь интегрирован в структуру коллекций. Он схож с HashMap, но синхронизован.
 */

