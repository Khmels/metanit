package chapter05_Collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class P5_1_CollectionTypes {

    public static void main(String[] args) {
        OwnCollection ownCollection = new OwnCollection();

        // Diamond operator is not applicable for non-parameterized types
        // Collection<Integer> ownCollection2 = new OwnCollection<>();

        Collection<Integer> ownCollection2 = new OwnCollection();

        System.out.println(ownCollection.isEmpty());
        // Result of 'ownCollection2.isEmpty()' is always 'true'
        System.out.println(ownCollection2.isEmpty()); // true
    }
}

class OwnCollection implements Collection {

    // int size ():
    // возвращает число элементов в коллекции
    @Override
    public int size() {
        return 0;
    }

    // boolean isEmpty ():
    // возвращает true, если коллекция пуста, иначе возвращает false
    @Override
    public boolean isEmpty() {
        return true;
    }

    // boolean contains (Object item):
    // возвращает true, если объект item содержится в коллекции, иначе возвращает false
    @Override
    public boolean contains(Object o) {
        return false;
    }

    // Iterator<E> iterator ():
    // возвращает объект Iterator для обхода элементов коллекции
    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    // boolean add (E item):
    // добавляет в коллекцию объект item. При удачном добавлении возвращает true, при неудачном - false
    @Override
    public boolean add(Object o) {
        return false;
    }

    // boolean remove (Object item):
    // возвращает true, если объект item удачно удален из коллекции, иначе возвращается false
    @Override
    public boolean remove(Object o) {
        return false;
    }

    // boolean addAll (Collection<? extends E> col):
    // добавляет в коллекцию все элементы из коллекции col. При удачном добавлении возвращает true, при неудачном - false
    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    // void clear (): удаляет все элементы из коллекции
    @Override
    public void clear() {

    }

    // boolean retainAll (Collection<?> col):
    // удаляет все объекты из текущей коллекции, кроме тех, которые содержатся в коллекции col.
    // Если текущая коллекция после удаления изменилась, возвращает true, иначе возвращается false
    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    // boolean removeAll (Collection<?> col):
    // удаляет все объекты коллекции col из текущей коллекции. Если текущая коллекция изменилась, возвращает true, иначе возвращается false
    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }


    // --- IDEA не выделила автоматически

    // Object[] toArray ():
    // возвращает массив, содержащий все элементы коллекции
    @Override
    public Object[] toArray(IntFunction generator) {
        return new Object[0];
    }

    @Override
    public boolean removeIf(Predicate filter) {
        return false;
    }

    @Override
    public Spliterator spliterator() {
        return null;
    }

    @Override
    public Stream stream() {
        return null;
    }

    @Override
    public Stream parallelStream() {
        return null;
    }

    @Override
    public void forEach(Consumer action) {

    }
}



// --- Интерфейс Collection ---

//`java doc`
//### public interface Collection<E> extends Iterable<E>{}

/*
Интерфейс Collection является обобщенным и расширяет интерфейс Iterable,
поэтому все объекты коллекций можно перебирать в цикле по типу for-each.


Среди методов интерфейса Collection можно выделить следующие:

    boolean add (E item):                           добавляет в коллекцию объект item.
                                                    При удачном добавлении возвращает true, при неудачном - false
    boolean addAll (Collection<? extends E> col):   добавляет в коллекцию все элементы из коллекции col.
                                                    При удачном добавлении возвращает true, при неудачном - false
    void clear ():                                  удаляет все элементы из коллекции

    boolean contains (Object item):                 возвращает true, если объект item содержится в коллекции,
                                                    иначе возвращает false
    boolean isEmpty ():                             возвращает true, если коллекция пуста,
                                                    иначе возвращает false
    Iterator<E> iterator ():                        возвращает объект Iterator для обхода элементов коллекции

    boolean remove (Object item):                   возвращает true, если объект item удачно удален из коллекции,
                                                    иначе возвращается false
    boolean removeAll (Collection<?> col):          удаляет все объекты коллекции col из текущей коллекции.
                                                    Если текущая коллекция изменилась, возвращает true, иначе - false
    boolean retainAll (Collection<?> col):          удаляет все объекты из текущей коллекции, кроме тех,
                                                    которые содержатся в коллекции col.
                                                    Если текущая коллекция изменилась, возвращает true, иначе - false
    int size ():                                    возвращает число элементов в коллекции

    Object[] toArray ():                            возвращает массив, содержащий все элементы коллекции

    Все эти и остальные методы, которые имеются в интерфейсе Collection, реализуются всеми коллекциями,
    поэтому в целом общие принципы работы с коллекциями будут одни и те же.
    Единообразный интерфейс упрощает понимание и работу с различными типами коллекций.

    Так, добавление элемента будет производиться с помощью метода add,
    который принимает добавляемый элемент в качестве параметра.

    Для удаления вызывается метод remove().

    Метод clear() будет очищать коллекцию, а метод size() возвращать количество элементов в коллекции.
 */


/* Interfaces
    Collection:     базовый интерфейс для всех коллекций и других интерфейсов коллекций

    Queue:          наследует интерфейс Collection и представляет функционал для структур данных в виде очереди
    Deque:          наследует интерфейс Queue и представляет функционал для двунаправленных очередей

    List:           наследует интерфейс Collection и представляет функциональность простых списков

    Set:            также расширяет интерфейс Collection и используется для хранения множеств уникальных объектов
    SortedSet:      расширяет интерфейс Set для создания сортированных коллекций
    NavigableSet:   расширяет интерфейс SortedSet для создания коллекций, в которых можно  поиск по соответствию

    ----------
    Map:            предназначен для созданий структур данных в виде словаря,
                    где каждый элемент имеет определенный ключ и значение.
                    В отличие от других интерфейсов коллекций не наследуется от интерфейса Collection
*/

/* Abstract Classes:

    AbstractCollection:     базовый абстрактный класс для других коллекций, который применяет интерфейс Collection

    AbstractList:           расширяет класс AbstractCollection и применяет интерфейс List,
                            для создания коллекций в виде списков

    AbstractSet:            расширяет класс AbstractCollection и применяет интерфейс Set
                            для создания коллекций в виде множеств

    AbstractQueue:          расширяет класс AbstractCollection и применяет интерфейс Queue,
                            для создания коллекций в виде очередей и стеков

    AbstractSequentialList: расширяет класс AbstractList и реализует интерфейс List.
                            для создания связанных списков

    AbstractMap:            применяет интерфейс Map, предназначен для создания наборов по типу словаря
                            с объектами в виде пары "ключ-значение"
*/

/*
С помощью применения интерфейсов и абстрактных классов реализуется  -
списки, множества, очереди, отображения и другие, среди которых можно выделить следующие:

    ArrayList:      простой список объектов
    LinkedList:     представляет связанный список
    Vector:         динамический массив, но Vector синхронизирован.
                                            Vector содержит много устаревших методов,
                                            которые не являются частью структуры коллекций.

    HashSet:        набор объектов или хеш-множество, где каждый элемент имеет ключ - уникальный хеш-код
    TreeSet:        набор отсортированных объектов в виде дерева
    LinkedHashSet:  связанное хеш-множество

    ArrayDeque:     класс двунаправленной очереди, в которой можно произвести вставку и удаление в начале, и в ее конце
    PriorityQueue:  очередь приоритетов

    HashMap: структура данных в виде словаря, в котором каждый объект имеет уникальный ключ и некоторое значение
    TreeMap: отсортированная структура данных в виде дерева, где каждый элемент имеет уникальный ключ и некоторое значение
*/
