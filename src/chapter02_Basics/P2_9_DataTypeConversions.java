package chapter02_Basics;

public class P2_9_DataTypeConversions {
    public static void main(String[] args) {
        int a = 4;
        ///byte b = a; // ! Ошибка

        byte b = (byte)a;  // преобразование типов: от типа int к типу byte
        System.out.println(b); // 4

        //--- Явные и неявные преобразования ---
        /* Автоматические преобразования (неявное)

                      char  ---> int
            byte ---> short ---> int ---> long
                                 int ---> double
                      short ---> float ---> double

                                 int  __-> float    (потеря точности)
                                 long __-> float    (потеря точности)
                                 long __->  double  (потеря точности)
         */

        /*

         */

        /*
        widening - расширяющие преобразования, без проблем
         */

        byte с = 7;
        int d = с;  // преобразование от byte к int
        System.out.println(d);

        //--- Автоматические преобразования с потерей точности ---
        int e = 2147483647;
        float f = e;            // от типа int к типу float
        System.out.println(f);  // 2.14748365E9

        //--- Явные преобразования ---
        long g = 4;
        int h = (int) g;
        System.out.println(h);

        //---Потеря данных при преобразовании ---
        int j = 5;
        byte k = (byte) j;
        System.out.println("инт в байт: " + k);      // 5

        int j1 = 258;
        byte k1 = (byte) j1;
        System.out.println("инт в байт (больше 127): " + k1);     // 2

        // двоичная система 258 = 00000000 00000000 00000001 00000010
        // byte занимает 8 бит
        // двоичное числа int усекается до 8 правых разрядов (00000010),
        // что в десятичной системе дает число 2.

        //--- Усечение рациональных чисел до целых ---
        double l = 56.9898;
        int m = (int)l;
        System.out.println("усечение: " + m); //усечение, а не округление

        double n = 56.9898;
        int o = (int)Math.round(n);
        System.out.println("округление: " + o);

        //--- Преобразования при операциях
        /*
        если один из операндов к типу double, то и второй операнд к типу double
        если один из операндов к типу float, то и второй к типу float
        если один из операндов к типу long, то и второй к типу long
        иначе все операнды операции преобразуются к типу int
         */

        int aConv = 3;
        double bConv = 4.6;
        double cConv = aConv + bConv;
        System.out.println(cConv);        // 7.6

        byte aConv1 = 3;
        short bConv1 = 4;
        var cConv1 = (aConv1+bConv1);           // int - автоматически
        byte cConv2 = (byte)(aConv1+bConv1);    // явное приведение
        System.out.println(cConv1);
        System.out.println(cConv2);

        int charI = 'a' + 5;
        System.out.println(charI);  // 102

    }
}


