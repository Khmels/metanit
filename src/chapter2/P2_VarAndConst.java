package chapter2;

public class P2_VarAndConst {
    public static void main(String[] args) {
        int x;      // объявление переменной
        x = 10;     // присвоение значения
        System.out.println(x);  // 10

        int a = 10;     // объявление и инициализация переменной
        System.out.println(a);  // 10

        /*
        int x;
        System.out.println(x); //   !!! initialize variable x
         */

        //Через запятую можно объявить сразу несколько переменных одного типа:
        int z, y;   //Variable 'x' is already defined in the scope
                    //~~~~ Alt+Shift+Enter - navigate to previous declared variable 'x'
        z = 10;
        y = 25;
        System.out.println(z);  // 10
        System.out.println(y);  // 25

        //Также можно их сразу инициализировать:
        int m = 8, n = 15;
        System.out.println(m);  // 8
        System.out.println(n);  // 15

        //Отличительной особенностью переменных является то, что можно в процессе работы программы изменять их значение:
        int c = 10;
        System.out.println(c);  // 10
        c = 25;
        System.out.println(c);  // 25

        //Начиная с Java 10 в язык было добавлено ключевое слово var, которое также позволяет определять переменную:
        var l = 10;
        System.out.println(l);  // 10

        /*
        var k;      // ! Ошибка, переменная не инициализирована
                    //Variable 'k' is assigned but never accessed
        k = 10;
        */

        //Constants
        final int LIMIT = 5;
        System.out.println(LIMIT);  // 5
        ////LIMIT=57; // так уже не можем написать, так как LIMIT - константа
        //Cannot assign a value to final variable 'LIMIT'

    }
}
