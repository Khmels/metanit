package chapter5;

import java.util.ArrayList;
import java.util.List;

public class P2_ListAndArrayList {

    public static void main(String[] args) {

        //  ArrayList имеет следующие конструкторы:
        //  ArrayList():                              создает пустой список
        //  ArrayList(Collection <? extends E> col):  создает список, в который добавляются все элементы коллекции col.
        //  ArrayList (int capacity):                 создает список, который имеет начальную емкость capacity

        //`java doc`
        //### public class ArrayList<E> extends AbstractList<E>

        ArrayList<String> people = new ArrayList<String>();
        int capacity=10;
        ArrayList<String> subList = new ArrayList<>(capacity);

        System.out.println("initialCapacity: " + capacity + ", bub size = " + subList.size());
        System.out.println("---------------------");
        subList.add("0");
        subList.add("1");
        subList.add("2");
        subList.add("3");
        subList.add("4");
        subList.add("5");

        //`java doc`
        // public interface List<E> extends Collection<E>

        List<String> subList2 = subList.subList(0,2);
        for (String s: subList2) {
            System.out.println(s);
        }

        for (int i = 0; i < subList2.size(); i++) {
            System.out.printf("index s : %s", subList2.indexOf(subList2.get(i)), subList2.get(i));
            System.out.println();
        }

        System.out.println("---------------------");

        ArrayList<String> subArraylist = new ArrayList<>(subList);

        ArrayList<Integer> numbers = new ArrayList<>(10);

        // добавим в список ряд элементов
        people.add("Tom");
        people.add("Alice");
        people.add("Kate");
        people.add("Sam");
        people.add(1, "Bob"); // добавляем элемент по индексу 1

        System.out.println(people.get(1));// получаем 2-й объект
        System.out.println("---------------------");


        people.set(2, "Robert"); // установка нового значения для 3-го объекта

        System.out.printf("ArrayList has %d elements \n", people.size());

        for(String person : people){

            System.out.println(person);
        }

        System.out.println("---------------------");
        // проверяем наличие элемента
        if(people.contains("Tom")){

            System.out.println("ArrayList contains Tom");
        }

        // удаление конкретного элемента
        people.remove("Robert");
        // удаление по индексу
        people.remove(0);

        Object[] peopleArray = people.toArray();
        for(Object person : peopleArray){
            System.out.println(person);
        }
    }

}

/*
* Для создания простых списков применяется интерфейс List, который расширяет функцональность интерфейса Collection.
* Некоторые наиболее часто используемые методы интерфейса List:

    void add(int index, E obj):             добавляет в список по индексу index объект obj
    boolean addAll(int index,               добавляет в список по индексу index все элементы коллекции col.
        Collection<? extends E> col):       Если в результате добавления список был изменен, то возвращается true, иначе возвращается false
    E get(int index):                       возвращает объект из списка по индексу index
    int indexOf(Object obj):                возвращает индекс первого вхождения объекта obj в список. Если объект не найден, то возвращается -1
    int lastIndexOf(Object obj):            возвращает индекс последнего вхождения объекта obj в список. Если объект не найден, то возвращается -1
    ListIterator<E> listIterator ():        возвращает объект ListIterator для обхода элементов списка
    static <E> List<E> of(элементы):        создает из набора элементов объект List
    E remove(int index):                    удаляет объект из списка по индексу index, возвращая при этом удаленный объект
    E set(int index, E obj):                присваивает значение объекта obj элементу, который находится по индексу index
    void sort(Comparator<? super E> comp):  сортирует список с помощью компаратора comp
    List<E> subList(int start, int end):    получает набор элементов, которые находятся в списке между индексами start и end

По умолчанию в Java есть встроенная реализация этого интерфейса - класс ArrayList.
* Класс ArrayList представляет обобщенную коллекцию,
* которая наследует свою функциональность от класса AbstractList и применяет интерфейс List.
* */
