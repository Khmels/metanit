package chapter5_Collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class P6_10_Iterator_ListIterator {
    public static void main(String[] args) {
        ArrayList<String> states = new ArrayList<String>();
        states.add("Germany");
        states.add("France");
        states.add("Italy");
        states.add("Spain");

        Iterator<String> iter = states.iterator();
        while (iter.hasNext()) {

            System.out.println(iter.next());
        }
        System.out.println("---------------------");


        //  Returns a list iterator over the elements in this list (in proper sequence),
        //  starting at the specified position in the list.
        ListIterator<String> listIterator = states.listIterator(1);
        while (listIterator.hasNext()){
            System.out.println(listIterator.next()); // France, Italy, Spain
        }
    }
}

/*
Одним из ключевых методов интерфейса Collection является метод Iterator<E> iterator().
Он возвращает итератор - то есть объект, реализующий интерфейс Iterator.
 */

//`java doc`
//### public interface Iterator <E>{}

/* Методы:
        E next();
        boolean hasNext();
        void remove();
 */

/*
Реализация интерфейса предполагает, что с помощью вызова метода next() можно получить следующий элемент.
С помощью метода hasNext() можно узнать, есть ли следующий элемент, и не достигнут ли конец коллекции.
И если элементы еще имеются, то hasNext() вернет значение true.

Метод hasNext() следует вызывать перед методом next(),
так как при достижении конца коллекции метод next() выбрасывает исключение NoSuchElementException.

И метод remove() удаляет текущий элемент, который был получен последним вызовом next()
 */

/* Интерфейс Iterator предоставляет ограниченный функционал. \
Гораздо больший набор методов предоставляет другой итератор - интерфейс ListIterator.
Данный итератор используется классами, реализующими интерфейс List, то есть классами LinkedList, ArrayList и др.
Интерфейс ListIterator расширяет интерфейс Iterator.
 */

//`java doc`
//### public interface ListIterator<E> extends Iterator<E>

/* Дополнительные методы:

        boolean hasNext():      возвращает true, если в коллекции имеется следующий элемент, иначе возвращает false
        boolean hasPrevious():  возвращает true, если в коллекции имеется предыдущий элемент, иначе возвращает

        E next():               возвращает текущий элемент и переходит к следующему,
                                    если такого нет, то генерируется исключение NoSuchElementException
        E previous():           возвращает текущий элемент и переходит к предыдущему,
                                    если такого нет, то генерируется исключение NoSuchElementException

        int nextIndex():        возвращает индекс следующего элемента. Если такого нет, то возвращается размер списка
        int previousIndex():    возвращает индекс предыдущего элемента. Если такого нет, то возвращается число -1

        void remove():          даляет текущий элемент из списка.
                                Таким образом, этот метод должен быть вызван после методов next() или previous(),
                                наче будет сгенерировано исключение IlligalStateException
        void set(E obj):        присваивает текущему элементу,
                                выбранному вызовом методов next() или previous(), ссылку на объект obj
        void add(E obj):        вставляет объект obj перед элементом, который должен быть возвращен следующим вызовом next()
 */
