package chapter5;
import java.util.LinkedList;

public class P4_List_LinkedList {

    //`java doc`
    //### public class LinkedList<E> extends AbstractSequentialList<E>
    //                               implements List<E>, Deque<E>, Cloneable, java.io.Serializable {}

    /* LinkedList constructors // конструкторы :
            LinkedList():                               создает пустой список
            LinkedList(Collection<? extends E> col):    создает список, в который добавляет все элементы коллекции col
    */

    public static void main(String[] args) {

        LinkedList<String> states = new LinkedList<String>();

        // добавим в список ряд элементов
        states.add("Germany");
        states.add("France");
        states.addLast("Great Britain"); // добавляем на последнее место
        states.addFirst("Spain"); // добавляем на первое место
        states.add(1, "Italy"); // добавляем элемент по индексу 1

        System.out.printf("List has %d elements \n", states.size());
        System.out.println(states.get(1));
        states.set(1, "Portugal");
        for(String state : states){

            System.out.println(state);
        }
        // проверка на наличие элемента в списке
        if(states.contains("Germany")){

            System.out.println("List contains Germany");
        }

        states.remove("Germany");
        states.removeFirst(); // удаление первого элемента
        states.removeLast(); // удаление последнего элемента

        LinkedList<Ch5_Person_public> people = new LinkedList<Ch5_Person_public>();
        people.add(new Ch5_Person_public("Mike"));
        people.addFirst(new Ch5_Person_public("Tom"));
        people.addLast(new Ch5_Person_public("Nick"));
        people.remove(1); // удаление второго элемента

        for(Ch5_Person_public p : people){

            System.out.println(p.getName());
        }
        Ch5_Person_public first = people.getFirst();
        System.out.println(first.getName()); // вывод первого элемента

        VerifyCollection<LinkedList> verifyCollection = new VerifyCollection<>();
        verifyCollection.checkCollection(people);
    }
}

// Обобщенный класс LinkedList<E> представляет структуру данных в виде связанного списка.
// Он наследуется от класса AbstractSequentialList и реализует интерфейсы List, Dequeue и Queue.
// То есть он соединяет функциональность работы со списком и фукциональность очереди.

 /*
    LinkedList содержит все те методы, которые определены в интерфейсах List, Queue, Deque. Некоторые из них:
        addFirst() / offerFirst(): добавляет элемент в начало списка
        addLast() / offerLast(): добавляет элемент в конец списка
        removeFirst() / pollFirst(): удаляет первый элемент из начала списка
        removeLast() / pollLast(): удаляет последний элемент из конца списка
        getFirst() / peekFirst(): получает первый элемент
        getLast() / peekLast(): получает последний элемент
     */

