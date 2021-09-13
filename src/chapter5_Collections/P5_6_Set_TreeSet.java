package chapter5_Collections;

import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;

public class P5_6_Set_TreeSet {

    //`java doc`
    //### public class TreeSet<E> extends AbstractSet<E>
    //                            implements NavigableSet<E>, Cloneable, java.io.Serializable


    /* TreeSet constructors // конструкторы:
            TreeSet():                                  создает пустое дерево
            TreeSet(Collection<? extends E> col):       создает дерево, в которое добавляет все элементы коллекции col
            TreeSet(SortedSet <E> set):                 создает дерево, в которое добавляет эл. сортированного набора set
            TreeSet(Comparator<? super E> comparator):  создает пустое дерево, где все добавляемые элементы
                                                        впоследствии будут отсортированы компаратором.
     */

    public static void main(String[] args) {
        TreeSet<String> states = new TreeSet<String>();

        // добавим в список ряд элементов
        states.add("Germany");
        states.add("France");
        states.add("Italy");
        states.add("Spain");
        states.add("Great Britain");

        System.out.printf("TreeSet contains %d elements \n", states.size());

        // удаление элемента
        states.remove("Germany");
        for(String state : states){

            System.out.println(state);
        }
        System.out.println("---------------------");
        states.add("Germany");


        for(String state : states){

            System.out.println(state);
        }

        System.out.println("---------------------");
        System.out.println("First: " + states.first());     // получим первый - самый меньший элемент
        System.out.println("Last: " + states.last());      // получим последний - самый больший элемент
        System.out.println("---------------------");

        // получим поднабор от одного элемента до другого
        SortedSet<String> set = states.subSet("Germany", "Italy"); // первое включитально, второе  - нет
        System.out.println("Поднабор: " + set);

        // элемент из набора, который больше текущего
        String greater = states.higher("Germany");
        System.out.println("Greater: " + greater);
        // элемент из набора, который меньше текущего
        String lower = states.lower("Germany");
        System.out.println("Lower: " + lower);

        System.out.println("---------------------");

        // возвращаем набор в обратном порядке
        NavigableSet<String> navSet = states.descendingSet();
        // нету такого метода
        // TreeSet<String> descSet = states.descendingSet();

        // возвращаем набор в котором все элементы меньше текущего
        SortedSet<String> setLower=states.headSet("Great Britain");     // невключительно
        // возвращаем набор в котором все элементы больше текущего
        SortedSet<String> setGreater=states.tailSet("Great Britain"); //   включительно

        System.out.println("Reversed:" + navSet);
        System.out.println("Меньше текущего: " + setLower);
        System.out.println("Больше текущего: " + setGreater);

        VerifyCollection<TreeSet> verifyCollection = new VerifyCollection<>();
        verifyCollection.checkCollection(states);
    }
}

/*
SortedSet
    Интерфейс SortedSet предназначен для создания коллекций,
    который хранят элементы в отсортированном виде (сортировка по возрастанию).
    SortedSet расширяет интерфейс Set, поэтому такая коллекция опять же хранит только уникальные значения.
 */


    /*
    E first():                              возвращает первый элемент набора
    E last():                               возвращает последний элемент набора
    SortedSet<E> headSet(E end):            возвращает объект SortedSet, элементы первичного набора до элемента end
    SortedSet<E> subSet(E start, E end):    возвращает объект SortedSet, элементы первичного набора между start и end
    SortedSet<E> tailSet(E start):          возвращает объект SortedSet, элементы первичного набора с элемента start
    */


/*NavigableSet
    Интерфейс NavigableSet расширяет интерфейс SortedSet и
    позволяет извлекать элементы на основании их значений.
*/

/*
    E ceiling(E obj):                       ищет в наборе наименьший элемент e, который больше obj (e >=obj).
                                            Если такой элемент найден, то он возвращается в качестве результата.
                                            Иначе возвращается null.

    E floor(E obj):                         ищет в наборе наибольший элемент e, который меньше элемента obj (e <=obj).
                                            Если такой элемент найден, то он возвращается в качестве результата.
                                            Иначе возвращается null.

    E higher(E obj):                        ищет в наборе наименьший элемент e, который больше элемента obj (e >obj).
                                            Если такой элемент найден, то он возвращается в качестве результата.
                                            Иначе возвращается null.

    E lower(E obj):                         ищет в наборе наибольший элемент e, который меньше элемента obj (e <obj).
                                            Если такой элемент найден, то он возвращается в качестве результата.
                                            Иначе возвращается null.

    E pollFirst():                          возвращает первый элемент и удаляет его из набора
    E pollLast():                           возвращает последний элемент и удаляет его из набора

    NavigableSet<E> descendingSet():        возвращает объект NavigableSet,
                                            который содержит элементы первичного набора NavigableSet в обратном порядке

    NavigableSet<E> headSet(E upperBound, boolean incl):    возвращает объект NavigableSet,
                                            который содержит элементы первичного набора NavigableSet до upperBound.
                                            Параметр incl при значении true,
                                            позволяет включить в выходной набор элемент upperBound

    NavigableSet<E> tailSet(E lowerBound, boolean incl):     возвращает объект NavigableSet,
                                            который содержит все элементы первичного набора NavigableSet с lowerBound.
                                            Параметр incl при значении true,
                                            позволяет включить в выходной набор элемент lowerBound

    NavigableSet<E> subSet(E lowerBound, boolean lowerIncl, E upperBound, boolean highIncl): возвращает NavigableSet,
                                            который содержит все элементы первичного набора NavigableSet
                                            от lowerBound до upperBound.
 */

    /*
    TreeSet
    Обобщенный класс TreeSet<E> представляет структуру данных в виде дерева,
    в котором все объекты хранятся в отсортированном виде по возрастанию.

    TreeSet является наследником класса AbstractSet и реализует интерфейс NavigableSet, а следовательно, и интерфейс SortedSet.
    TreeSet поддерживает все стандартные методы для вставки и удаления элементов
    */


