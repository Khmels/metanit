package chapter5;

import java.util.EmptyStackException;
import java.util.Stack;

public class P2a_List_Vector_Stack {

    //`java doc`
    //### public class Stack<E> extends Vector<E> {}

    /* Stack constructors // конструкторы :
            Stack():                                пустой стэк, LIFO
     */

    public static void main(String[] args) {
        Stack<String> strings = new Stack<>();
        System.out.println("Вместимость: " + strings.capacity() + ", размер: " + strings.size());
        System.out.println();

        strings.add("0");
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        strings.add("5");
        System.out.println(strings);

        System.out.println("---------------------");
        Stack st = new Stack();
        System.out.println("Initial stack: " + st);
        showPush(st, 42);
        showPush(st, 66);
        showPush(st, 99);
        System.out.println();
        showPop(st);
        showPop(st);
        showPop(st);
        try {
            showPop(st);
        } catch (EmptyStackException e) {
            System.out.println("Пустой стек");
        }

        Stack<Ch5_Person_public> people = new Stack<>();
        people.add(new Ch5_Person_public("Bob"));
        people.add(new Ch5_Person_public("Tom"));
        people.add(new Ch5_Person_public("Tom"));
        people.add(new Ch5_Person_public("Dan"));
        people.add(new Ch5_Person_public("Sam"));

        VerifyCollection<Stack> verifyCollection = new VerifyCollection<>();
        verifyCollection.checkCollection(people);

    }

    static void showPush(Stack st, int a) {
        st.push(a);
        System.out.println("Push - " + a );
        System.out.println("---Стек: " + st);
    }

    static void showPop(Stack st) {
        System.out.print("Shot -> ");
        Integer a = (Integer) st.pop();
        System.out.println(a);
        System.out.println("---Стек: " + st);
    }


}

// реализует стандартный стек last-in, first-out. LIFO

// Stack только определяет стандартный конструктор, который создает пустой стек.
// Stack включает все методы, определенные Vector, и самостоятельно добавляет несколько своих собственных.

