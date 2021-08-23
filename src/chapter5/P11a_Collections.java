package chapter5;

import java.awt.*;
import java.util.*;

public class P11a_Collections {
    public static void main(String[] args) {
        LinkedList<String > linkedList = new LinkedList<>();
        linkedList.add(new String("Tom"));
        linkedList.add(new String("Sam"));
        linkedList.add(new String("Bob"));
        linkedList.add(new String("Jack"));
        System.out.println(linkedList);

        LinkedList<Integer> integerLinkedList = new LinkedList<Integer>(Arrays.asList(1,2,3,4,5));

        // Компаратор обратного порядка
        Comparator<String> r = Collections.reverseOrder();
        Comparator<Integer> rev = Collections.reverseOrder();

        // Raw use of parameterized class 'Comparator'
        Comparator raw = Collections.reverseOrder();

        // Сортировка списка с помощью компаратора
        Collections.sort(linkedList, r);
        integerLinkedList.sort(rev);

        integerLinkedList.sort(raw);
        linkedList.sort(raw);

        //Иитератор
        Iterator<String> li = linkedList.iterator();
        System.out.print("Список, отсортированный задом наперед: ");

        while(li.hasNext()) {
            System.out.print(li.next() + " ");
        }
        System.out.println();


        Collections.shuffle(linkedList);
        // Отображаем случайный список
        li = linkedList.iterator();
        System.out.print("Список перемешанный: ");

        while(li.hasNext()) {
            System.out.print(li.next() + " ");
        }

        System.out.println();
        System.out.println("Минимум: " + Collections.min(linkedList) + ", " + Collections.min(integerLinkedList));
        System.out.printf("Максимум: %s, %d", Collections.max(linkedList), Collections.max(integerLinkedList));
    }
}

/*
Структура коллекций определяет несколько алгоритмов, которые могут применяться к коллекциям и Map.

Эти алгоритмы определяются как статические методы в классе Collections.
Некоторые из методов могут генерировать ClassCastException,
которое возникает при попытке сравнить несовместимые типы

или UnsupportedOperationException, которое возникает, когда предпринимается попытка изменить немодифицируемую коллекцию.
 */

