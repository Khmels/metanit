package chapter5;

import java.util.HashSet;

public class P5_Set_HasHSet {

    //`java doc`
    //### public class HashSet<E> extends AbstractSet<E>
    //                            implements Set<E>, Cloneable, java.io.Serializable {}

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

    public static void main(String[] args) {

        HashSet<String> states = new HashSet<String>();

        // добавим в список ряд элементов
        states.add("Germany");
        states.add("France");
        states.add("Italy");



        // пытаемся добавить элемент, который уже есть в коллекции
        boolean isAdded = states.add("Germany"); // тк строка то, срабатывает equals

        System.out.println(isAdded);    // false

        System.out.printf("Set contains %d elements \n", states.size());    // 3

        for(String state : states){

            System.out.println(state);
        }

        // удаление элемента
        states.remove("Germany");

        System.out.println("---------------------");

        // хеш-таблица объектов Person
        HashSet<Ch5_Person_public> people = new HashSet<Ch5_Person_public>();
        people.add(new Ch5_Person_public("Mike"));
        people.add(new Ch5_Person_public("Tom"));
        // у класса Ch5_PersonArray не переопределен метод equals. Добавляется новый объект в коллекцию
        people.add(new Ch5_Person_public("Tom"));
        people.add(new Ch5_Person_public("Nick"));
        people.add(new Ch5_Person_public("Bob"));
        System.out.printf("Set contains %d elements \n", people.size());    //


        // проверка через ссылки
        Ch5_Person_public bob1 = new Ch5_Person_public("Bob");
        Ch5_Person_public bob2 = new Ch5_Person_public("Bobby");
        Ch5_Person_public bob3 = new Ch5_Person_public( "undefined");
        bob3 = bob2;

        people.add(bob1);
        people.add(bob2);
        people.add(bob3); // не добавилось, тк один и тот же объект

        System.out.printf("Set contains %d elements \n", people.size());

        System.out.printf("New objects      %s <=> %s\n", new Ch5_Person_public("Bobby").hashCode(), new Ch5_Person_public("Bobby").hashCode());
        System.out.printf("Created earlier  %s <=> %s\n", new Ch5_Person_public("Bobby").hashCode(), bob2.hashCode());
        System.out.printf("Same object      %s <=> %s\n", bob2.hashCode(), bob3.hashCode());         // равен
        System.out.printf("Two strings      %s <=> %s\n", "Bobby".hashCode(), "Bobby".hashCode());   // равен
        System.out.println();

        for(Ch5_Person_public p : people){

            System.out.println(p.getName());
        }

        // тк в проверке коппируется тот же объект, то добавление заново не происходит
        Verify<HashSet> verify =new Verify<>();
        verify.checkCollection(people);
    }
}

/*
    Интерфейс Set расширяет интерфейс Collection и представляет набор уникальных элементов.
    Set не добавляет новых методов, только вносит изменения унаследованные.
    В частности, метод add() добавляет элемент в коллекцию и возвращает true, если в коллекции еще нет такого элемента.
     */

    /*
    Класс HashSet представляет хеш-таблицу.
    Он наследует свой функционал от класса AbstractSet, а также реализует интерфейс Set.

    Класс HashSet не добавляет новых методов, реализуя лишь те,
    что объявлены в родительских классах и применяемых интерфейсах:

    Хеш-таблица представляет такую структуру данных, в которой все объекты имеют уникальный ключ или хеш-код.
    Данный ключ позволяет уникально идентифицировать объект в таблице.
    */
