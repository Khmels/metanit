package chapter10;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public class P10_9_Optional {

    public static void main(String[] args) {

        /*
        Ряд операций сведения, такие как min, max, reduce, возвращают объект Optional<T>.

        Этот объект фактически обертывает результат операции.
        После выполнения операции с помощью метода get() объекта Optional мы можем получить его значение:
         */

        ArrayList<Integer> numbers = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Optional<Integer> min = numbers.stream().min(Integer::compare);
        System.out.println(min.get());  // 1

        // если список numbers пустой - Optional пустой
        ArrayList<Integer> numbers2 = new ArrayList<Integer>();
        Optional<Integer> mi2 = numbers2.stream().min(Integer::compare);
        ///! Exception in thread "main" java.util.NoSuchElementException: No value present
        ///  System.out.println(mi2.get());
        System.out.println("---------------------");

        //--- Проверка isPresent() ------------------------------

        /*
        это предварительная проверка наличия значения в Optional с помощью метода isPresent().
        Он возврашает true, если значение присутствует в Optional, и false, если значение отсутствует
         */

        ArrayList<Integer> numbers3 = new ArrayList<Integer>();
        Optional<Integer> min3 = numbers3.stream().min(Integer::compare);
        if(min3.isPresent()){

            System.out.println(min.get());
        }
        else System.out.println("Список пуст");
        System.out.println("---------------------");

        //--- Проверка orElse() ------------------------------

        /*
        Метод orElse() позволяет определить альтернативное значение, которое будет возвращаться,
        если Optional не получит из потока какого-нибудь значения:
         */

        // пустой список
        ArrayList<Integer> numbers4 = new ArrayList<Integer>();
        Optional<Integer> min4 = numbers4.stream().min(Integer::compare);
        System.out.println(min4.orElse(-1)); // -1

        // непустой список
        numbers4.addAll(Arrays.asList(new Integer[]{4,5,6,7,8,9}));
        min4 = numbers4.stream().min(Integer::compare);
        System.out.println(min4.orElse(-1)); // 4
        System.out.println("---------------------");

        //--- orElseGet ------------------------------

        /*
        Метод orElseGet() позволяет задать функцию, которая будет возвращать значение по умолчанию:
         */

        ArrayList<Integer> numbers5 = new ArrayList<Integer>();
        Optional<Integer> min5 = numbers5.stream().min(Integer::compare);
        Random rnd = new Random();
        System.out.println(min.orElseGet(()->rnd.nextInt(100)));
        System.out.println("---------------------");

        //--- orElseThrow ------------------------------

        /*
        Метод orElseThrow позволяет сгенерировать исключение, если Optional не содержит значения:
         */

        ArrayList<Integer> numbers6 = new ArrayList<Integer>();
        Optional<Integer> min6 = numbers6.stream().min(Integer::compare);
        // генеррация исключения IllegalStateException
        // Exception in thread "main" java.lang.IllegalStateException
        // System.out.println(min6.orElseThrow(IllegalStateException::new));

        //--- Обработка полученного значения

        /*
        Метод ifPresent() определяет действия со значением в Optional, если значение имеется:
         */

        ArrayList<Integer> numbers7 = new ArrayList<Integer>();
        numbers7.addAll(Arrays.asList(new Integer[]{4,5,6,7,8,9}));
        Optional<Integer> min7 = numbers7.stream().min(Integer::compare);
        min.ifPresent(v->System.out.println(v)); // 4
        System.out.println("---------------------");

        /*
        метод ifPresent передается функция, которая принимает один параметр - значение из Optional.
        В данном случае полученное минимальное число выводится на консоль.

        Но если бы массив numbers был бы пустым, и соответственно Optional не сдержало бы никакого значения,
        то никакой ошибки бы не было.
         */

        /*
        Метод ifPresentOrElse() позволяет определить альтернативную логику на случай, если значение в Optional отсутствует:
         */

        ArrayList<Integer> numbers8 = new ArrayList<Integer>();
        Optional<Integer> min8 = numbers8.stream().min(Integer::compare);
        min8.ifPresentOrElse(
                v -> System.out.println(v),
                () -> System.out.println("Value not found")
        );
        System.out.println("---------------------");

        /*
        В метод ifPresentOrElse передается две функции.
        Первая обрабатывает значение в Optional, если оно присутствует.
        Вторая функция представляет действия, которые выполняются, если значение в Optional отсутствует.
         */
    }

}
