package chapter3;

public class P10_Interface {
    public static void main(String[] args) {
        // 'P10_Interface_Printable' is abstract; cannot be instantiated
        /// P10_Interface_Printable pr = new P10_Interface_Printable();
        /// pr.print();

        // Классы реализуют интерфейс Printable. динамически в программе можем создавать объекты Printable
        // как экземпляры обоих классов:

        P10_Interface_Printable printable = new Book("Java. Complete Reference", "H. Shildt");
        printable.print();      //  Java. Complete Reference (H. Shildt)
        printable = new Journal("Foreign Policy");
        printable.print();      // Foreign Policy

        // --- Интерфейсы в преобразованиях типов ---
        // класс Journal реализует интерфейс P10_Interface_Printable,
        // то переменная типа Printable может хранить ссылку на объект типа Journal:

        P10_Interface_Printable p =new Journal("Foreign Affairs");
        p.print();
        // Интерфейс не имеет метода getName, необходимо явное приведение
        String name = ((Journal)p).getName();
        System.out.println(name);

        System.out.println("---------------------");

        // --- Методы по умолчанию ---
        /*
        Ранее до JDK 8 при реализации интерфейса мы должны были обязательно реализовать все его методы в классе.
        А сам интерфейс мог содержать только определения методов без конкретной реализации.

        В JDK 8 была добавлена такая функциональность как методы по умолчанию.
        И теперь интерфейсы кроме определения методов могут иметь их реализацию по умолчанию,
        которая используется, если класс, реализующий данный интерфейс, не реализует метод.
         */


        P10_Interface_Default pDefault = new Magazine("The New York Times");
        pDefault.print();

        P10_Interface_Default p10InterfaceDefaultOverride = new MagazineOverride("People");
        p10InterfaceDefaultOverride.print();

        // --- Статические методы ---
        P10_Interface_Static.read();

        System.out.println("---------------------");

        // --- Приватные методы ---
        Calculable c = new Calculation();
        System.out.println(c.sum(1, 2));
        System.out.println(c.sum(1, 2, 4));

        System.out.println("---------------------");

        WaterPipe pipe = new WaterPipe();
        pipe.printState(1);
        pipe.printState(-1);

        System.out.println("---------------------");

        Booklet booklet = new Booklet("Some name");
        Printer.Printable bookletNested = new Booklet("Nested interface");
        booklet.print();
        bookletNested.print();
    }
}


