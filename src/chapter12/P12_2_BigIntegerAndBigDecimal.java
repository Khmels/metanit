package chapter12;

import java.math.BigDecimal;
import java.math.BigInteger;

public class P12_2_BigIntegerAndBigDecimal {

    public static void main(String[] args) {

        BigInteger a = BigInteger.valueOf(2147483647);
        BigInteger b = BigInteger.valueOf(2147483641);
        //a = a * b;                // так нельзя
        a = a.multiply(b);
        System.out.println(a);      // 4611686001247518727

        long x = a.longValue();
        System.out.println(x);      // 4611686001247518727

        System.out.println("---------------------");

        BigDecimal c = BigDecimal.valueOf(2325.06);
        BigDecimal d = BigDecimal.valueOf(215.06);
        c = c.subtract(d.multiply(BigDecimal.valueOf(2.1)));
        System.out.println(c);      // 1873.434
        double y = c.doubleValue();
        System.out.println(y);      // 1873.434

        /*
        Стоит отметить, несмотря на то, что объекты BigInteger и BigDecimal представляют числа,
        не возможно применять с ними стандартные арифметические операции.
        Все математические действия с данными объектами идут через их методы.
         */
    }
}

/*
Встроенные примитивные числовые типы не всегда могут подходить для определенных программ.
Например, необходимо хранить и использовать в программе очень большие числа,
выходят за пределы допустимых значений для типов long и double.

В этом случае для работы с числовыми данными можно использовать два дополнительных типа из пакета java.math
      BigInteger (для целочисленных данных)
      BigDecimal (для чисел с плавающей точкой).
 */


/*
Основные методы класса BigInteger:
    BigInteger add(BigInteger other):       возвращает сумму двух чисел
    BigInteger subtract(BigInteger other):  возвращает разность двух чисел
    BigInteger multiply(BigInteger other):  возвращает произведение двух чисел
    BigInteger divide(BigInteger other):    возвращает частное двух чисел
    BigInteger mod(BigInteger other):       возвращает остаток от целочисленного деления двух чисел
    BigInteger sqrt():                      возвращает квадратный корень числа
    int compareTo(BigInteger other):        сравнивает два числа. Возвращает -1, если текущий объект меньше числа other,
                                                                              1, если текущий объект больше и 0 - если числа равны
    static BigInteger valueOf(long x):      возвращает объект BigInteger, значение которого равно числу, переданному в качестве параметра
    int intValue():                         конвертирует объект BigInteger в объект int
    byte byteValue():                       преобразует объект BigInteger в byte
    short shortValue():                     преобразует объект BigInteger в short
    long longValue():                       преобразует объект BigInteger в long
*/

/*
Основные методы класса BigDecimal:
    BigDecimal add(BigDecimal other):       возвращает сумму двух чисел
    BigDecimal subtract(BigDecimal other):  возвращает разность двух чисел
    BigDecimal multiply(BigDecimal other):  возвращает произведение двух чисел
    BigDecimal divide(BigDecimal other):    возвращает частное двух чисел
    BigDecimal divide(BigDecimal other,
                    RoundingMode mode):     результат деления двух чисел, округленное в соответствии с режимом mode
    int compareTo(BigDecimal other):        сравнивает два числа. Возвращает -1, если текущий объект меньше числа other,
                                                                              1, если текущий объект больше и 0 - если числа равны
    static BigDecimal valueOf(double x):    возвращает объект BigDecimal, значение которого равно числу, переданному в качестве параметра
    double doubleValue():                   преобразует объект BigDecimal в double
    float floatValue():                     преобразует объект BigDecimal в float
 */