package chapter08_Lambda;

public class P8_1_Lambda {

    static int a = 10;
    static int b = 20;

    public static void main(String[] args) {

        Operationable operation;
        operation = (x,y)->x+y;

        int result = operation.calculate(10, 20);
        System.out.println(result); //30

        /* По факту лямбда-выражения являются в некотором роде сокращенной формой внутренних анонимных классов,
        которые ранее применялись в Java. В частности, предыдущий пример можно переписать следующим образом:
         */

        // Лучше всего думать о лямбда-выражении как о функции, а не об объекте,
        // и признать, что он может быть передан функциональному интерфейсу.

        Operationable op = new Operationable(){

            public int calculate(int x, int y){

                return x + y;
            }
        };
        int z = op.calculate(20, 10);
        System.out.println(z); // 30

        //Чтобы объявить и использовать лямбда-выражение, основная программа разбивается на ряд этапов:
        /*
        1. Определение ссылки на функциональный интерфейс:
                        Operationable operation;
        2. Создание лямбда-выражения:
                        operation = (x,y)->x+y;
                Причем параметры лямбда-выражения соответствуют параметрам единственного метода интерфейса Operationable,
                а результат соответствует возвращаемому результату метода интерфейса.
                При этом не надо использовать ключевое слово return для возврата результата из лямбда-выражения.
        3. Использование лямбда-выражения в виде вызова метода интерфейса:
                        int result = operation.calculate(10, 20);
         */

        // При этом для одного функционального интерфейса можно определить множество лямбда-выражений.
        Operationable operation1 = (int x, int y)-> x + y;
        Operationable operation2 = (int x, int y)-> x - y;
        Operationable operation3 = (int x, int y)-> x * y;

        System.out.println(operation1.calculate(20, 10)); //30
        System.out.println(operation2.calculate(20, 10)); //10
        System.out.println(operation3.calculate(20, 10)); //200

        //--- Отложенное выполнение ------------------------------
        /*
        Одним из ключевых моментов в использовании лямбд является отложенное выполнение (deferred execution).
        То есть определяем в одном месте программы лямбда-выражение и затем можем его вызывать при необходимости
        неопределенное количество раз в различных частях программы.

        Отложенное выполнение может потребоваться, к примеру, в следующих случаях:
            Выполнение кода отдельном потоке
            Выполнение одного и того же кода несколько раз
            Выполнение кода в результате какого-то события
            Выполнение кода только в том случае, когда он действительно необходим и если он необходим
         */


        //--- Передача параметров в лямбда-выражение ------------------------------
        /*
        Параметры лямбда-выражения должны соответствовать по типу параметрам метода из функционального интерфейса.
        При написании самого лямбда-выражения тип параметров писать необязательно, хотя в принципе это можно сделать, например:
                operation = (int x, int y)->x+y;
        Если метод не принимает никаких параметров, то пишутся пустые скобки, например:
                ()-> 30 + 20;
        Если метод принимает только один параметр, то скобки можно опустить:
                n-> n * n
         */

        //--- Терминальные лямбда-выражения ---
        /* Рассмотрено лямбда-выражения, которые возвращают определенное значение.
           Но также могут быть и терминальные лямбды, которые не возвращают никакого значения.
         */

        // Lambda can be replaced with method reference
        // Printable printer = System.out::println;
        Printable printer = s->System.out.println(s);
        printer.print("Hello Java!");


        //--- Лямбды и локальные переменные ------------------------------

        /*
        Лямбда-выражение может использовать переменные, которые объявлены во вне в более общей области видимости -
        на уровне класса или метода, в котором лямбда-выражение определено.

        Однако в зависимости от того, как и где определены переменные, могут различаться способы их использования в лямбдах.
         */

        //-- Использования переменных уровня класса
        Operation op2 = ()->{

            a=30;
            return a+b;
        };
        System.out.println(op2.calculate()); // 50
        System.out.println(a);               // 30 - значение x изменилось
        // Переменные a и b объявлены на уровне класса, и в лямбда-выражении мы их можем получить и даже изменить.

        //-- Локальные переменные на уровне метода:
        int n=70;
        int m=30;
        Operation op3 = ()->{

            // n=100; //- так нельзя сделать
            return m+n;
        };
        //! Variable used in lambda expression should be final or effectively final

        // n=100;  //- так тоже нельзя
        System.out.println(op3.calculate()); // 100
        // Локальные переменные уровня метода мы также можем использовать в лямбдах, но изменять их значение нельзя.
        // не в выражение, ни в вне.

        //--- Блоки кода в лямбда-выражениях ------------------------------

        /*
        Блочные выражения обрамляются фигурными скобками.
        В блочных лямбда-выражениях можно использовать внутренние вложенные блоки, циклы, конструкции if, switch, создавать переменные и т.д.

        Если блочное лямбда-выражение должно возвращать значение, то явным образом применяется оператор return:
        лямбда-выражение не может возвращать значение в каких-то ветках, а в других не возвращать.
        Например, (int x) -> { if (x <= 1) return -1; } является недопустимым.
         */

        Operationable operation4 = (int x, int y)-> {

            if(y==0)
                return 0;
            else
                return x/y;
        };

        System.out.println(operation.calculate(20, 10)); //2
        System.out.println(operation.calculate(20, 0)); //0

        //--- Обобщенный функциональный интерфейс ------------------------------

        /*
        Функциональный интерфейс может быть обобщенным, однако в лямбда-выражении использование обобщений не допускается.
        В этом случае нам надо типизировать объект интерфейса определенным типом, который потом будет применяться в лямбда-выражении.
         */

        OperationableGeneric<Integer> operation5 = (x, y)-> x + y;
        OperationableGeneric<String> operation6 = (x, y) -> x + y;

        System.out.println(operation5.calculate(20, 10)); //30
        System.out.println(operation6.calculate("20", "10")); //2010

        // при объявлении лямбда-выражения ему уже известно, какой тип параметры будут представлять и какой тип они будут возвращать.

    }
}

interface Operationable{
    int calculate(int x, int y); // 2 параметра (x, y)->{x+y}
}

interface Printable{
    void print(String s);       // один параметр x-> x*x
}

interface Operation{
    int calculate();            // без параметров ()->20+30
}

interface OperationableGeneric<T>{
    T calculate(T x, T y);
}



/*
Лямбда представляет набор инструкций, которые можно выделить в отдельную переменную
и затем многократно вызвать в различных местах программы.

Основу лямбда-выражения составляет лямбда-оператор, который представляет стрелку ->.
Этот оператор разделяет лямбда-выражение на две части: левая часть содержит список параметров выражения,
а правая собственно представляет тело лямбда-выражения, где выполняются все действия.

Лямбда-выражение не выполняется само по себе, а образует реализацию метода, определенного в функциональном интерфейсе.
При этом важно, что функциональный интерфейс должен содержать только один единственный метод без реализации.
 */