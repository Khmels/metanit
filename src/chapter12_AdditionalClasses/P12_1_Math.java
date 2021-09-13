package chapter12_AdditionalClasses;

import java.util.Scanner;

public class P12_1_Math {
    //Для выполнения различных математических операций в Java в пакете java.lang определен класс Math.
    public static void main(String[] args) {
        //Основніе методы класса Math
        int x = 1;
        double y = -1.2;

        // abs(double value): возвращает абсолютное значение для аргумента value
        double z = Math.abs(y);
        double abs = Math.abs(-13.5);           // 13.5

        // acos(double value): возвращает арккосинус value. Параметр value должен иметь значение от -1 до 1
        double acos = Math.acos(1);             // 0.0

        // asin(double value): возвращает арксинус value. Параметр value должен иметь значение от -1 до 1
        double asin = Math.asin(1);             // 0.0

        // atan(double value): возвращает арктангенс value
        double atan = Math.atan(1);             // 0.0

        // cbrt(double value): возвращает кубический корень числа value
        double cbrt = Math.cbrt(27);            // 3

        // ceil(double value): возвращает наименьшее целое число с плавающей точкой, которое не меньше value
        double ceil = Math.ceil(2.34);          // 3

        // cos(double d): возвращает косинус угла d
        double cos = Math.cos(45);

        // cosh(double d): возвращает гиперболический косинус угла d
        double cosh = Math.cosh(45);

        // exp(double d): возвращает основание натурального логарифма, возведенное в степень d
        double exp = Math.exp(100);

        // floor(double d): возвращает наибольшее целое число, которое не больше d
        double floor = Math.floor(1.56);        // 1

        // floorDiv(int a, int b): возвращает целочисленный результат деления a на b
        double floorDiv = Math.floorDiv(7, 2);  // 3

        // log(double a): возвращает натуральный логарифм числа a
        double log = Math.log(100);

        // log1p(double d): возвращает натуральный логарифм числа (d + 1)
        double log1p = Math.log1p(2);

        // log10(double d): возвращает десятичный логарифм числа d
        double log10 = Math.log10(100);

        // max(double a, double b): возвращает максимальное число из a и b
        double max = Math.max(100,101);

        // min(double a, double b): возвращает минимальное число из a и b
        double min = Math.min(100,101);

        // pow(double a, double b): возвращает число a, возведенное в степень b
        double pow = Math.pow(10,2);

        // random(): возвращает случайное число от 0.0 до 1.0
        double random = Math.random();

        // rint(double value): возвращает число double, которое представляет ближайшее к числу value целое число
        double rint = Math.rint(2.5);           // 2.0
        double rint2 = Math.rint(2.5001);       // 3.0

        // round(double d): возвращает число d, округленное до ближайшего целого числа
        double round = Math.round(2.5);         // 3
        double round2 = Math.round(2.5001);     // 3

        // scalb(double value, int factor): возвращает произведение числа value на 2 в степени factor
        double scalb = Math.scalb(5, 3);        // 5*2*2*2 = 40
        double scalb2 = Math.scalb(3, 4);       // 3*2*2*2*2 = 48

        // signum(double value): возвращает число 1: value положительное, -1: value отрицательное, value 0: то возвращает 0
        double signum = Math.signum(2.3);       // 1
        double signum2 = Math.signum(-2.3);     // -1

        // sin(double value): возвращает синус угла value
        double sin = Math.sin(45);

        // sinh(double value): возвращает гиперболический синус угла value
        double sinh = Math.sinh(45);

        // sqrt(double value): возвращает квадратный корень числа value
        double sqrt =  Math.sqrt(16);           // 4

        // tan(double value): возвращает тангенс угла value
        double tan =  Math.tan(30);

        // tanh(double value): возвращает тангенс угла value
        double tanh =  Math.tanh(30);

        // toDegrees(double value) переводит радианы в градусы
        double toDegrees =  Math.toDegrees(3.14159);    // 180

        // toRadians(double value) - градусы в радианы
        double toRadians = Math.toRadians(90);          // 1,57079....

        Scanner in = new Scanner(System.in);

        System.out.print("Введите радиус круга: ");
        int radius = in.nextInt();
        long area = Math.round(Math.PI * Math.pow(radius, 2));
        System.out.printf("Площадь круга с радиусом %d равна %d \n", radius, area);
    }
}

