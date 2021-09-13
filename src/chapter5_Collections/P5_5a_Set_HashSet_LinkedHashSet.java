package chapter5_Collections;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class P5_5a_Set_HashSet_LinkedHashSet {

    //`java doc`
    //### public class LinkedHashSet<E> extends HashSet<E>
    //                                  implements Set<E>, Cloneable, java.io.Serializable

    /* HashSet constructors // конструкторы
        HashSet():                              создает пустой список
        HashSet(Collection<? extends E> col):   создает хеш-таблицу, в которую добавляет все элементы коллекции col
        HashSet(int capacity):                  параметр capacity указывает начальную емкость таблицы,
                                                которая по умолчанию равна 16
        HashSet(int capacity, float k):         параметр k или коэффициент заполнения,
                                                значение которого должно быть в пределах от 0.0 до 1.0,
                                                указывает, насколько должна быть заполнена емкость объектами прежде,
                                                чем произойдет ее расширение.
     */

    public static void main(String args[]) {
        // Создаем хэш-набор
        // Raw use of parameterized class 'LinkedHashSet'
        LinkedHashSet hs = new LinkedHashSet();

        // Добавляем элементы к хэш-набору
        hs.add("B");
        hs.add("A");
        hs.add("D");
        hs.add("E");
        hs.add("C");
        hs.add("F");
        hs.add(123);
        System.out.println(hs);

        HashSet<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("a");
        linkedHashSet.add("b");
        linkedHashSet.add("d");
        linkedHashSet.add("c");

        System.out.println(linkedHashSet);

        LinkedHashSet<Ch5_Person_public> people = new LinkedHashSet<>();
        people.add(new Ch5_Person_public("Mike"));
        people.add(new Ch5_Person_public("Tom"));
        // у класса Ch5_PersonArray не переопределен метод equals. Добавляется новый объект в коллекцию
        people.add(new Ch5_Person_public("Tom"));
        people.add(new Ch5_Person_public("Nick"));
        people.add(new Ch5_Person_public("Bob"));

        VerifyCollection<LinkedHashSet> verifyCollection = new VerifyCollection<>();
        verifyCollection.checkCollection(people);



    }
}

/*
LinkedHashSet поддерживает связанный список записей в наборе, в том порядке, в котором они были вставлены.
Это позволяет выполнять итерацию порядка вставки по набору.

То есть, при проходе по LinkedHashSet циклами с использованием итератора
элементы будут возвращены в том порядке, в котором они были вставлены.

Хэш-код затем используется как индекс, в котором хранятся данные, связанные с ключом.
Преобразование ключа в его хэш-код выполняется автоматически.
 */
