package chapter5;

import java.util.ArrayDeque;

public class P3_Queue_ArrayDeque {

    //`java doc`
    //### public class ArrayDeque<E> extends AbstractCollection<E>
    //                               implements Deque<E>, Cloneable, Serializable

    /*  ArrayDeque constructors // конструкторы:
            ArrayDeque():                               создает пустую очередь
            ArrayDeque(Collection<? extends E> col):    создает очередь, наполненную элементами из коллекции col
            ArrayDeque(int capacity):                   создает очередь с начальной емкостью capacity.
    */

    public static void main(String[] args) {

        ArrayDeque<String> states = new ArrayDeque<String>();
        // стандартное добавление элементов
        states.add("Germany");
        states.addFirst("France");      // добавляем элемент в самое начало
        states.push("Great Britain");   // добавляем элемент в самое начало
        states.addLast("Spain");        // добавляем элемент в конец коллекции
        states.add("Italy");

        // получаем первый элемент без удаления
        String sFirst = states.getFirst();
        System.out.println(sFirst);     // Great Britain
        // получаем последний элемент без удаления
        String sLast = states.getLast();
        System.out.println(sLast);      // Italy

        System.out.printf("--- Queue size: %d --- \n", states.size());  // 5

        // перебор коллекции
        while(states.peek()!=null){
            // извлечение c начала
            System.out.println(states.pop());
        }

        System.out.println("---------------------");

        // очередь из объектов Person
        ArrayDeque<Ch5_Person_public> people = new ArrayDeque<Ch5_Person_public>();
        people.addFirst(new Ch5_Person_public("Tom"));
        people.add(new Ch5_Person_public("Nick"));
        people.addLast(new Ch5_Person_public("Nick"));

        // перебор без извлечения
        for(Ch5_Person_public p : people){

            System.out.println(p.getName());
        }

        VerifyCollection<ArrayDeque> verifyCollection = new VerifyCollection<>();
        verifyCollection.checkCollection(people);
    }
}

/*
    FIFO (first in - first out).
    То есть чем раньше был добавлен элемент в коллекцию, тем раньше он из нее удаляется.
    Это стандартная модель однонаправленной очереди.
*/

//`java doc`
//### public interface Queue<E> extends Collection<E> {}

    /*
    E element():            возвращает, но не удаляет, элемент из начала очереди.
                                Если очередь пуста, генерирует исключение NoSuchElementException
    boolean offer(E obj):   добавляет элемент obj в конец очереди.
                                Если элемент удачно добавлен, возвращает true, иначе - false
    E peek():               возвращает без удаления элемент из начала очереди.
                                Если очередь пуста, возвращает значение null
    E poll():               возвращает с удалением элемент из начала очереди.
                                Если очередь пуста, возвращает значение null
    E remove():             возвращает с удалением элемент из начала очереди.
                                Если очередь пуста, генерирует исключение NoSuchElementException
     */

//`java doc`
//### public interface Deque<E> extends Queue<E>

    /*
    void addFirst(E obj):   добавляет элемент в начало очереди
    void addLast(E obj):    добавляет элемент obj в конец очереди
    E getFirst():           возвращает без удаления элемент из головы очереди.
                                Если очередь пуста, генерирует исключение NoSuchElementException
    E getLast():            возвращает без удаления последний элемент очереди. Если очередь пуста, генерирует исключение NoSuchElementException
    boolean offerFirst(E obj):  добавляет элемент obj в самое начало очереди.
                                Если элемент удачно добавлен, возвращает true, иначе - false
    boolean offerLast(E obj):   добавляет элемент obj в конец очереди.
                                Если элемент удачно добавлен, возвращает true, иначе - false
    E peekFirst():          возвращает без удаления элемент из начала очереди.
                                Если очередь пуста, возвращает значение null
    E peekLast():           возвращает без удаления последний элемент очереди.
                                Если очередь пуста, возвращает значение null
    E pollFirst():          возвращает с удалением элемент из начала очереди.
                                Если очередь пуста, возвращает значение null
    E pollLast():           возвращает с удалением последний элемент очереди.
                                Если очередь пуста, возвращает значение null
    E pop():                возвращает с удалением элемент из начала очереди.
                                Если очередь пуста, генерирует исключение NoSuchElementException
    void push(E element):   добавляет элемент в самое начало очереди

    E removeFirst():        возвращает с удалением элемент из начала очереди.
                                Если очередь пуста, генерирует исключение NoSuchElementException
    E removeLast():         возвращает с удалением элемент из конца очереди.
                                Если очередь пуста, генерирует исключение NoSuchElementException
    boolean removeFirstOccurrence(Object obj): удаляет первый встреченный элемент obj из очереди.
                            Если удаление произшло, то возвращает true, иначе возвращает false.
    boolean removeLastOccurrence(Object obj): удаляет последний встреченный элемент obj из очереди.
                            Если удаление произшло, то возвращает true, иначе возвращает false.
     */

    /*  наличие методов pop и push позволяет классам, реализующим этот элемент, действовать в качестве стека.
    В тоже время имеющийся функционал также позволяет создавать двунаправленные очереди,
    что делает классы, применяющие данный интерфейс, довольно гибкими.
     */

