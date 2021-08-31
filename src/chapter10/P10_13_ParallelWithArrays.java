package chapter10;

import java.util.Arrays;

public class P10_13_ParallelWithArrays {

    public static void main(String[] args) {

        //--- parallelSetAll

        /*
        В метод Arrays.parallelSetAll передается два параметра:
        изменяемый массив и функция, которая устанавливает элементы массива.
        Эта функция перебирает все элементы и в качестве параметра получает индекс текущего перебираемого элемента.
         */

        int[] values = new int[7];
        Arrays.parallelSetAll(values, i -> i*10);
        for(int i: values) {
            System.out.println(i);
        }

        System.out.println("---------------------");

        Ch10_Phone[] phones = new Ch10_Phone[]{new Ch10_Phone("iPhone 8", 54000),
                new Ch10_Phone("Pixel 2", 45000),
                new Ch10_Phone("Samsung Galaxy S9", 40000),
                new Ch10_Phone("Nokia 9", 32000)};

        Arrays.parallelSetAll(phones, i -> {
            phones[i].setPrice(phones[i].getPrice()-10000);
            return phones[i];
        });

        /*
        лямбда-выражение в методе Arrays.parallelSetAll представляет блок кода.
        И так как лямбда-выражение должно возвращать объект, то надо явным образом использовать оператор return.

        В этом лямбда-выражении опять же функция получает индексы перебираемых элементов,
        и по этим индексам мы можем обратиться к элементам массива и их изменить.
         */

        for(Ch10_Phone p: phones){
            System.out.printf("%s - %d \n", p.getName(), p.getPrice());
        }

        System.out.println("---------------------");


        //--- parallelSort
        /*
        public static <T extends Comparable<? super T>> void parallelSort(T[] a)
        public static <T> void parallelSort(T[] a, Comparator<? super T> cmp)

         */

        int[] nums = {30, -4, 5, 29, 7, -8};
        Arrays.parallelSort(nums);
        for(int i: nums){
            System.out.println(i);
        }

        System.out.println("---------------------");


        Ch10_Phone[] phones2 = new Ch10_Phone[]{new Ch10_Phone("iPhone 8", 54000),
                new Ch10_Phone("Pixel 2", 45000),
                new Ch10_Phone("Samsung Galaxy S9", 40000),
                new Ch10_Phone("Nokia 9", 32000)};

        Arrays.parallelSort(phones2,new PhoneComparator());

        for(Ch10_Phone p: phones)
            System.out.println(p.getName());

        System.out.println("---------------------");

        //--- parallelPrefix

        /*
        Метод parallelPrefix() походит для тех случаев, когда надо получить элемент массива или объект того же типа,
        что и элементы массива, который обладает некоторыми признаками.
         */

        int[] numbers = {1, 2, 3, 4, 5, 6};
        Arrays.parallelPrefix(numbers, (x, y) -> x * y);

        /*
        Лямбда-выражение из Arrays.parallelPrefix, которое представляет бинарную функцию,
        получает два элемента и выполняет над ними операцию.

        Результат операции сохраняется и передается в следующий вызов бинарной функции.
         */

        for(int i: numbers)
            System.out.println(i);


    }



}

/*
В JDK 8 к классу Arrays было добавлено ряд методов,
которые позволяют в параллельном режиме совершать обработку элементов массива.

И хотя данные методы формально не входят в Stream API, но реализуют схожую функциональность, что и параллельные потоки:

    parallelSetAll(): устанавливает элементы массива с помощью лямбда-выражения
    parallelSort(): сортирует массив
    parallelPrefix(): вычисляет некоторое значение для элементов массива (например, сумму элементов)
 */
