package chapter10;

import java.util.stream.Stream;

public class P10_3_FilteringIteratingDisplaying {
    public static void main(String[] args) {

        //--- Перебор элементов. Метод forEach ------------------------------
        /*
        Для перебора элементов потока применяется метод forEach(), который представляет терминальную операцию.
        В качестве параметра он принимает объект Consumer<? super String>,
        который представляет действие, выполняемое для каждого элемента набора.
         */

        Stream<String> citiesStream = Stream.of("Париж", "Лондон", "Мадрид","Берлин", "Брюссель");
        citiesStream.forEach(s -> System.out.println(s));
        System.out.println("---------------------");

        /* citiesStream.forEach(System.out::println);
            Lambda can be replaced with method reference
            здесь переадается ссылка на статический метод, который выводит строку на консоль.
        */

        //--- Фильтрация. Метод filter ------------------------------

        /*
        Для фильтрации элементов в потоке применяется метод filter(), который представляет промежуточную операцию.
        Он принимает в качестве параметра некоторое условие в виде объекта Predicate<T>
        и возвращает новый поток из элементов, которые удовлетворяют этому условию:
         */
        Stream<String> citiesStream2 = Stream.of("Париж", "Лондон", "Мадрид","Берлин", "Брюссель");
        citiesStream2.filter(s->s.length()==6).forEach(s->System.out.println(s));
        System.out.println("---------------------");

        // фильтр набора телефонов по цене:

        Stream<Phone> phoneStream = Stream.of
                (new Phone("iPhone 12", 1000),
                new Phone("Pixel 5", 900),
                new Phone("Samsung Galaxy S21", 950));

        phoneStream.filter(p->p.getPrice()<960).forEach(p->System.out.println(p.getName()));
        System.out.println("---------------------");

        //--- Отображение. Метод map ------------------------------

        /*
        Отображение или маппинг позволяет задать функцию преобразования одного объекта в другой,
        то есть получить из элемента одного типа элемент другого типа.

        Для отображения используется метод map, который имеет следующее определение:
            <R> Stream<R> map(Function<? super T, ? extends R> mapper)

        Передаваемая в метод map функция задает преобразование от объектов типа T к типу R.
        И в результате возвращается новый поток с преобразованными объектами.
         */

        Stream<Phone> phoneStream2 = Stream.of( new Phone("iPhone 12", 1000),
                                                new Phone("Pixel 5", 900),
                                                new Phone("Galaxy S21", 950));

//        phoneStream2
//                .map(p-> p.getName());               // помещает в новый поток только названия телефонов
//                .forEach(s->System.out.println(s));

        phoneStream2
                .map(p-> "название: " + p.getName() + "\t цена: " + p.getPrice())
                .forEach(s->System.out.println(s));
        System.out.println("---------------------");

        /*
        результирующий поток содержит только строки, только теперь названия соединяются с ценами.
        Для преобразования объектов в типы Integer, Long, Double
        определены специальные методы mapToInt(), mapToLong() и mapToDouble() соответственно.
        */

        //--- Плоское отображение. Метод flatMap ------------------------------

        /*
        Плоское отображение выполняется тогда, когда из одного элемента нужно получить несколько.
        Данную операцию выполняет метод flatMap:
            <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)
         */

        //То есть из одного объекта Phone нам надо получить два объекта с информацией, например, в виде строки.

        Stream<Phone> phoneStream3 = Stream.of( new Phone("iPhone 12", 1000),
                new Phone("Pixel 5", 900),
                new Phone("Galaxy S21", 950));

        phoneStream3
                .flatMap(p->Stream.of(
                        String.format("название: %s  цена без скидки: %d", p.getName(), p.getPrice()),
                        String.format("название: %s  цена со скидкой: %d", p.getName(), p.getPrice() - (int)(p.getPrice()*0.1)),
                        String.format("")
                ))
                .forEach(s->System.out.println(s));

    }

}

class Phone{

    private String name;
    private int price;

    public Phone(String name, int price){
        this.name=name;
        this.price=price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

