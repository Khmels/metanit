package chapter10;

import java.util.Optional;
import java.util.stream.Stream;

public class P10_8_Reduce {

    public static void main(String[] args) {

        //--- reduce()

        /*
        Метод reduce выполняет терминальные операции сведения, возвращая некоторое значение - результат операции.
                Optional<T> reduce(BinaryOperator<T> accumulator)
                T reduce(T identity, BinaryOperator<T> accumulator)
                U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner)
         */

        //--- Optional<T> reduce(BinaryOperator<T> accumulator)

        Stream<Integer> numbersStream = Stream.of(1, 2, 3, 4, 5, 6);
        Optional<Integer> result = numbersStream.reduce((x, y) -> x * y);
        // 'Optional.get()' without 'isPresent()' check
        System.out.println(result.get()); // 720
        System.out.println("---------------------");

//        if (result.isPresent()){
//        System.out.println(result.get());} // 720

        // Can be replaced with single expression in functional style
        // result.ifPresent(System.out::println);

        /*
        Объект BinaryOperator<T> представляет функцию,
        которая принимает два элемента и выполняет над ними некоторую операцию,
        возвращая результат.

        При этом метод reduce сохраняет результат и затем опять же применяет к этому результату
        и следующему элементу в наборе бинарную операцию.

        Фактически в данном случае получим результат, который будет равен: n1 op n2 op n3 op n4 op n5 op n6,
        где op - это операция (в данном случае умножения), а n1, n2, ... - элементы из потока.
         */

        //еще один пример - объединение слов в предложение:
        Stream<String> wordsStream = Stream.of("Введение", "в", "Stream", "API");
        Optional<String> sentence = wordsStream.reduce((x,y)->x + " " + y);
        System.out.println(sentence.get());
        System.out.println("---------------------");

        //--- T reduce(T identity, BinaryOperator<T> accumulator)

        /*
        T identity - элемент, который предоставляет начальное значение для функции из второго параметра,
        а также предоставляет значение по умолчанию, если поток не имеет элементов.

        BinaryOperator<T> accumulator, как и первая форма метода reduce, представляет ассоциативную функцию,
        которая запускается для каждого элемента в потоке и принимает два параметра.

        Первый параметр представляяет промежуточный результат функции, а второй параметр - следующий элемент в потоке.

        T result = identity;
        for (T element : this stream)
            result = accumulator.apply(result, element)
        return result;
         */

        /*
        То есть при первом вызове функция accumulator в качестве первого параметра принимает значение identity,
        а в качестве второго параметра - первый элемент потока.

        При втором вызове первым параметром служит результат первого вызова функции accumulator,
        а вторым параметром - второй элемент в потоке и так далее.

        Фактически здесь выполняется следующая цепь операций: identity op n1 op n2 op n3 op n4...
         */

        Stream<Integer> numberStream = Stream.of(-4, 3, -2, 1);
        int identity = 1;
        int result2 = numberStream.reduce(identity, (x,y)->x * y);
        System.out.println(result2);  // 24
        // 1*-4=-4; -4*3=-12; -12*-2=24; 24*1=24
        System.out.println("---------------------");

        //--- U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner)

        /*
        Необходимо найти сумму цен тех телефонов, у которых цена меньше определенного значения.
        Для этого используем третью версию метода reduce
         */

        Stream<Ch10_Phone> phoneStream = Stream.of(new Ch10_Phone("iPhone 7", 1000),
                new Ch10_Phone("Lumia 950", 900),
                new Ch10_Phone("Samsung Galaxy S7", 950),
                new Ch10_Phone("LG G5", 850));

        int sum = phoneStream.parallel().reduce(0,
                (x,y)-> {
                    if(y.getPrice()<1000)
                        return x + y.getPrice();
                    else
                        return x + 0;
                },
                (x,y)->x+y);

        System.out.println(sum); // 2700


        /*
        Опять же здесь в качестве первого параметра идет значение по умолчанию - 0.
        Второй параметр производит бинарную операцию, которая получает промежуточное значение - суммарную цену текущего и предыдущего значений.
        Третий параметр представляет бинарную операцию, которая суммирует все промежуточные вычисления.
         */

        //что-то не совсем верное

    }
}


