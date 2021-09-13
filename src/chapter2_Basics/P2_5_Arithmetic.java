package chapter2_Basics;

public class P2_5_Arithmetic {
    public static void main(String[] args) {
        int a0 = 10;
        int b0 = 7;
        int c0 = a0 + b0;  // 17
        int d0 = 4 + b0;  // 11

        int a1 = 10;
        int b1 = 7;
        int c1 = a1 - b1;  // 3
        int d1 = 4 - a1;  // -6

        int a2 = 10;
        int b2 = 7;
        int c2 = a2 * b2;  // 70
        int d2 = b2 * 5;  // 35

        int a3 = 20;
        int b3 = 5;
        int c3 = a3 / b3;  // 4
        double d3 = 22.5 / 4.5;  // 5.0

        float k = 10 / 4;     // 2
        System.out.println(k);

        double k1 = 10.0 / 4;     // 2.5
        System.out.println(k1);

        //mod
        int a4 = 33;
        int b4 = 5;
        int c4 = a4 % b4;  // 3
        int d4 = 22 % 4; // 2 (22 - 4*5 = 2)
        System.out.println("---------------------");

        //---increment---
        int aPrefI = 8;
        int bPrefI = ++aPrefI;
        System.out.println(aPrefI);  // 9
        System.out.println(bPrefI);  // 9

        int aPostI = 8;
        int bPostI = aPostI++;
        System.out.println(aPostI);  // 9
        System.out.println(bPostI);  // 8

        //---decrement---
        int aPrefD = 8;
        int bPrefD = --aPrefD;
        System.out.println(aPrefD);  // 7
        System.out.println(bPrefD);  // 7

        int aPostD = 8;
        int bPostD = aPostD--;
        System.out.println(aPostD);  // 7
        System.out.println(bPostD);  // 8

        //--- ПРИОРИТЕТ АРИФМЕТИЧЕСКИХ ОПЕРАЦИЙ ---
        /*
            ++ (инкремент),     -- (декремент)
            * (умножение),      / (деление),        % (остаток от деления)
            + (сложение),       - (вычитание)
         */
        System.out.println("---------------------");

        int a = 8;
        int b = 7;
        int c = a + 5 * ++b;
        System.out.println(c);  // 48

        int d = 8;
        int e = 7;
        int f = (d + 5) * ++e;
        System.out.println(f);  // 104

        //---Ассоциативность операций---
        /*
        Левоассоциативные операторы, которые выполняются слева направо
        Правоассоциативные операторы, которые выполняются справа налево
         */

        int x = 10 / 5 * 2;
        // (10 / 5) * 2 или как 10 / (5 * 2) ?   //ПЕРВЫЙ ВАРИАНТ

        double fin = 2.0 - 1.1;
        System.out.println(fin);
        //следует использовать BigDecimal, тк для числа 0.1 не существует двоичного представления
        //также как и для других дробных значений
    }
}
