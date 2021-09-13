package chapter05_Collections;

import java.util.*;

public class P5_2a_List_Vector {

    //`java doc`
    //### public class Vector<E> extends AbstractList<E>
    //                          implements List<E>, RandomAccess, Cloneable, java.io.Serializable {}

    /* Vector constructors // конструкторы :

            Vector() :                              стандартный вектор, который имеет начальный размер 10.
            Vector(Collection<? extends E> c) :     вектор, содержащий элементы коллекции c.
            Vector(int initialCapacity) :           вектор, начальная емкость которого определяется initialCapacity.
            Vector(int initialCapacity, int capacityIncrement) :
                                                    вектор, чья начальная емкость задается initialCapacity
                                                    и инкремент которого определяется capacityIncrement.
                                                    Инкремент определяет количество элементов,
                                                    которые будут выделяться каждый раз, когда вектор будет изменен.

     */

    public static void main(String args[]) {
        // начальный размер 3, шаг 2
        //Raw use of parameterized class 'Vector'
        //
        Vector v = new Vector(3, 2);
        System.out.println("Начальный размер: " + v.size());
        System.out.println("Начальная емкость: " + v.capacity());

        v.addElement(Integer.valueOf(1));
        v.addElement(Integer.valueOf(2));
        v.addElement(Integer.valueOf(2));
        v.addElement(Integer.valueOf(2));
        //   v.addElement(new -Integer- (2));
        // idea зачеркивает
        // Unnecessary boxing 'new Integer(2)'
        // 'Integer(int)' is deprecated
        // @Deprecated(since = "9")

        System.out.println("Размер после четырех сложений: " + v.size());
        System.out.println("Емкость после четырех сложений: " + v.capacity());

        // перечислить элементы в векторе.
        Enumeration vEnum = v.elements();
        System.out.println("Элементы в векторе:");

        while(vEnum.hasMoreElements()) {
            System.out.print(vEnum.nextElement() + " ");
        }
        System.out.println();

        v.addElement(new Double(5.45));
        System.out.println("Текущая емкость: " + v.capacity());
        v.addElement(new Integer(7));
        System.out.println("Текущий размер " + v.size() + ", емкость: " + v.capacity());

        while(vEnum.hasMoreElements()) {
            System.out.print(vEnum.nextElement() + " ");
        }
        System.out.println();

        Enumeration vEnum2 = v.elements();
        while(vEnum2.hasMoreElements()) {
            System.out.print("[" + vEnum2.nextElement() + "], ");
        }
        System.out.println();
        System.out.println("Первый элемент: " + (Integer)v.firstElement());
        System.out.println("Последний элемент: " + (Integer)v.lastElement());

        Vector<Integer> integerVector = new Vector<>();
        integerVector.add(1);
        integerVector.add(Integer.valueOf(2));
        // If a new Integer instance is not required,
        // this method should generally be used in preference to the constructor Integer(int),
        // as this method is likely to yield significantly better space
        // and time performance by caching frequently requested values.
        integerVector.add(Integer.valueOf(3));
        integerVector.add((int) 2.4); // double добавить нельзя

        Vector<String> strings = new Vector<>();
        strings.add("a");
        strings.add("b");
        strings.add("c");
        strings.add("d");

        for (String st: strings) {
            System.out.println(st);
        }

        Vector<Ch5_Person_public>  people = new Vector<>();
        people.add(new Ch5_Person_public("Tom"));
        people.add(new Ch5_Person_public("Bob"));
        people.add(new Ch5_Person_public("Dan"));
        people.add(new Ch5_Person_public("Sam"));

        VerifyCollection<Vector> verifyCollection = new VerifyCollection<>();
        verifyCollection.checkCollection(people);
    }
}
