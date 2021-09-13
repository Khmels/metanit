package chapter8_Lambda;

public class P8_2_LambdaAsParameters {
    public static void main(String[] args) {

        Expression func = (n)-> n%2==0; //остаток при деление на 2 равен 0 (четные)
        int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
         System.out.println(sum(nums, func)); // 20

        /*
        При этом можно не определять переменную интерфейса, а сразу передать в метод лямбда-выражение:
         */

        int x = sum(nums, (n)-> n > 5); // сумма чисел, которые больше 5
        System.out.println(x);  // 30

        //--- Ссылки на метод как параметры методов --------------------
        /*
        Начиная с JDK 8 в Java можно в качестве параметра в метод передавать ссылку на другой метод.
        В принципе данный способ аналогичен передаче в метод лямбда-выражения.
         */

        // Ссылка на метод передается в виде имя_класса::имя_статического_метода (если метод статический)
        //                            или объект_класса::имя_метода (если метод нестатический).

        int[] nums2 = { -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
        /*
        на месте второго параметра передается ExpressionHelper::isEven, то есть ссылка на статический метод isEven() класса ExpressionHelper.
        При этом методы, на которые идет ссылка, должны совпадать по параметрам и результату с методом функционального интерфейса.
         */
        System.out.println(sum(nums, ExpressionHelper::isEven));

        // При втором вызове метода sum отдельно создается объект Expression, который затем передается в метод:
        Expression exprStatic = ExpressionHelper::isEven;
        System.out.println(sum(nums, exprStatic));


        // для вызова нестатического метода, то в ссылке вместо имени класса применяется имя объекта этого класса:
        ExpressionHelper expressionHelper = new ExpressionHelper();
        Expression expr = expressionHelper::isPositive;
        System.out.println(sum(nums, expr));
        // Использование ссылок на методы в качестве параметров аналогично использованию лямбда-выражений.

        //--- Ссылки на конструкторы --------------------
        // Подобным образом можно использовать конструкторы: название_класса::new

        UserBuilder userBuilder = User::new;
        User user = userBuilder.create("Tom");
        System.out.println(user.getName());

        /*
        При использовании конструкторов методы функциональных интерфейсов должны принимать тот же список параметров,
        что и конструкторы класса, и должны возвращать объект данного класса
         */

        //--- Лямбды как результат методов --------------------
        // метод в Java может возвращать лямбда-выражение

        // В методе main можно вызвать этот метод action. Например, сначала получить его результат - лямбда-выражение,
        // которое присваивается переменной Operation. А затем через метод execute выполнить это лямбда-выражение:
        Operation2 func1 = action(1);
        int a = func1.execute(6, 5);
        System.out.println(a);              // 11

        // Либо можно сразу получить и тут же выполнить лямбда-выражение:
        int b = action(2).execute(8, 2);
        System.out.println(b);          // 6
    }


    /*
    В основном классе программы определяется метод sum(), который вычисляет сумму всех элементов массива,
    соответствующих некоторому условию.

    А само условие передается через параметр Expression func.
    Причем на момент написания метода sum можно абсолютно не знать, какое именно условие будет использоваться.

    Само же условие определяется в виде лямбда-выражения: (n)-> n%2==0
     */
    private static int sum (int[] numbers, Expression func)
    {
        int result = 0;
        for(int i : numbers)
        {
            if (func.ifEqual(i))
                result += i;
        }
        return result;
    }

    /*
    Метод action принимает в качестве параметра число и в зависимости от его значения возвращает то или иное лямбда-выражение.
    Оно может представлять либо сложение, либо вычитание, либо умножение, либо просто возвращает 0.
    Стоит учитывать, что формально возвращаемым типом метода action является интерфейс Operation,
    а возвращаемое лямбда-выражение должно соответствовать этому интерфейсу.
     */
    private static Operation2 action(int number){
        switch(number){
            case 1: return (x, y) -> x + y;
            case 2: return (x, y) -> x - y;
            case 3: return (x, y) -> x * y;
            default: return (x,y) -> 0;
        }
    }

}

/*
В данном случае определен функциональный интерфейс Operation2,
в котором метод execute принимает два значения типа int и возвращает значение типа int
 */
interface Operation2{
    int execute(int x, int y);
}

interface Expression{
    boolean ifEqual(int n);
}
/*
Функциональный интерфейс Expression определяет метод isEqual(), который возвращает true,
если в отношении числа n действует какое-нибудь равенство.
 */

/*
класс, в котором определены методы
 */
class ExpressionHelper{

    static boolean isEven(int n){

        return n%2 == 0;
    }

    boolean isPositive(int n){

        return n > 0;
    }
}

interface UserBuilder{
    User create(String name);
}

class User{

    private String name;
    String getName(){
        return name;
    }

    User(String n){
        this.name=n;
    }
}