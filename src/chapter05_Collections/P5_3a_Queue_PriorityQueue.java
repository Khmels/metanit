package chapter05_Collections;

import java.util.PriorityQueue;

public class P5_3a_Queue_PriorityQueue {

    //`java doc`
    //### public class PriorityQueue<E> extends AbstractQueue<E>
    //                                  implements java.io.Serializable {}

    /*  PriorityQueue constructors // констуркторы:

            PriorityQueue():
            PriorityQueue<E> pq = new PriorityQueue<E>();
                                                        Creates a PriorityQueue with the default initial capacity (11)
                                                        that orders its elements according to their natural ordering.

            PriorityQueue(Collection<E> c):
            PriorityQueue<E> pq = new PriorityQueue<E>(Collection<E> c);
                                                        Creates a PriorityQueue containing the elements
                                                        in the specified collection.

            PriorityQueue(int initialCapacity):
            PriorityQueue<E> pq = new PriorityQueue<E>(int initialCapacity);
                                                        Creates a PriorityQueue with the specified initial capacity
                                                        that orders its elements according to their natural ordering.

            PriorityQueue(int initialCapacity, Comparator<E> comparator):
            PriorityQueue<E> pq = new PriorityQueue(int initialCapacity, Comparator<E> comparator);
                                                        Creates a PriorityQueue with the specified initial capacity that
                                                        orders its elements according to the specified comparator.

            PriorityQueue(PriorityQueue<E> c):
            PriorityQueue<E> pq = new PriorityQueue(PriorityQueue<E> c);
                                                        Creates a PriorityQueue containing the elements
                                                        in the specified priority queue.

            PriorityQueue(SortedSet<E> c):
            PriorityQueue<E> pq = new PriorityQueue<E>(SortedSet<E> c);
                                                        Creates a PriorityQueue containing the elements
                                                        in the specified sorted set.
     */

        public static <T> void main(String[] args) {


            PriorityQueue<String> states = new PriorityQueue<String>();
            // стандартное добавление элементов
            states.add("Italy");
            //states.offer("Germany");
            states.add("Germany");
            states.add("Great Britain");
            states.add("Austria");
            /*
                the two functions come from two different interfaces that PriorityQueue implements:
                    add() comes from Collection.
                    offer() comes from Queue.
            */


            // получаем первый элемент без удаления
            String stFirst = states.peek();
            System.out.println(stFirst);     // Austria

            System.out.printf("Queue size: %d \n", states.size());  // 2

            System.out.println("---------------------");
            for(String s : states) {
                // здесь можно делать что угодно с очередным элементом коллекции
                System.out.println(s);
            }

            System.out.println("---------------------");

            // получаем первый элемент с удалением
            String stLast = states.poll();
            System.out.println(stLast);      // Austria

            System.out.println("---------------------");

            for(String s : states) {
                // здесь можно делать что угодно с очередным элементом коллекции
                System.out.println(s);
            }

            System.out.printf("Queue size: %d \n", states.size());  // 3


            // очередь из объектов Person
            PriorityQueue<Ch5_Person_public> people = new PriorityQueue<Ch5_Person_public>();
            people.add(new Ch5_Person_public("Tom"));
            people.add(new Ch5_Person_public("Will"));
            people.add(new Ch5_Person_public("John"));
            people.add(new Ch5_Person_public("Nick"));
            people.add(new Ch5_Person_public("Albert"));

            System.out.println("---------------------");

            // Method invocation 'getName' may produce 'NullPointerException'
            System.out.println(people.peek().getName());     // Albert

            System.out.println("---------------------");

            /// java.lang.ClassCastException: class chapter5.Ch5_PersonArray cannot be cast to class java.lang.Comparable
            // перебор без извлечения
            for(Ch5_Person_public p : people) {
                System.out.println(p.getName());
            }

            System.out.println("-----INSERTION DUPLICATES----------------");

            int size = people.size();
            people.add(new Ch5_Person_public("Albert"));
            int newSize = people.size();


            if (size!=newSize) {
                System.out.println("---- dup ALLOWED ---");
            }
            else{
                System.out.println("---- dup DENIED ---");
            }
            for(Ch5_Person_public p : people) {
                System.out.println(p.getName());
            }
            System.out.printf("Queue size: %d \n", people.size());  // 6


            System.out.println("-----INSERTION NULL----------------");

            //
            try{
                people.add(null);
                System.out.println("---- null ALLOWED ---");
            }
            catch (NullPointerException e){
                System.out.println("---- null DENIED ---");
                e.getMessage();
            }

            for(Ch5_Person_public p : people) {
                System.out.println(p.getName());
            }
            System.out.printf("Queue size: %d \n", people.size());  // 6

            VerifyCollection<PriorityQueue> verifyCollection = new VerifyCollection<>();
            verifyCollection.checkCollection(people);


            System.out.println();
            System.out.println("--------INVERSE? + empty queue---------------");
            String[] strings = new String[people.size()];
            int i = people.size()-1;
            while (people.peek() != null ){
                strings[i] = people.poll().getName();
                //System.out.println(strings[i]);
                i--;
            }
            for (String s: strings) {
                System.out.println(s);
            }



    }
}


/*
//            Few important points on Priority Queue are as follows:
//
//            1.PriorityQueue doesn’t permit null.
//            2.We can’t create PriorityQueue of Objects that are non-comparable
//            3.PriorityQueue are unbound queues.
//            4.The head of this queue is the least element with respect to the specified ordering.
//                If multiple elements are tied for least value, the head is one of those elements — ties are broken arbitrarily.
//            5.Since PriorityQueue is not thread-safe, so java provides PriorityBlockingQueue class that implements
//                the BlockingQueue interface to use in java multithreading environment.
//            6.The queue retrieval operations poll,  remove,  peek, and element access the element at the head of the queue.
//            7.It provides O(log(n)) time for add and poll methods.
//            8.It inherits methods from AbstractQueue, AbstractCollection, Collection and Object class.
//             */