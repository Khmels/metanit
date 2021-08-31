package chapter2;



public class P3_Datatype {
    public static void main(String[] args) {

        //~~~~ Ctrl+Y - delete row and leave cursor

//        boolean: хранит значение true или false
        boolean isActive = false;
        boolean isAlive = true;

//        byte: хранит целое число от -128 до 127 и занимает 1 байт
        byte a = 3;
        byte b = 8;

//        short: хранит целое число от -32768 до 32767 и занимает 2 байта
        short c = 3;
        short d = 8;

//        int: хранит целое число от -2147483648 до 2147483647 и занимает 4 байта
        int e = 4;
        int f = 9;

//        long: хранит целое число от –9 223 372 036 854 775 808 до 9 223 372 036 854 775 807 и занимает 8 байт
        long g = 5;
        long h = 10;

//        double: хранит число с плавающей точкой от ±4.9*10-324 до ±1.8*10308 и занимает 8 байт
        double i = 8.5;
        double j = 2.7;
//        В качестве разделителя целой и дробной части в дробных литералах используется точка.

//        float: хранит число с плавающей точкой от -3.4*1038 до 3.4*1038 и занимает 4 байта
        float k = 8.5F;
        float l = 2.7F;

//        char: хранит одиночный символ в кодировке UTF-16 и занимает 2 байта, поэтому диапазон хранимых значений от 0 до 65535
        char m = '1';
        char n = 'A';

        // ---ЦЕЛЫЕ ЧИСЛА---
        byte o = 1;
        short p = 2;
        long r = 2121;

        /// long num = 9876543210; //Integer number too large
        //все целочисленные значения по умолчанию расцениваются как значения типа int, то компилятор укажет нам на ошибку
        long num = 9876543210L;
        long num1 = 9876543210l;

        int num111 = 0x6F; // 16-тиричная система, число 111
        int num8 = 010; // 8-ричная система, число 8
        int num13 = 0b1101; // 2-ичная система, число 13

        // ---С ПЛАВАЮЩЕЙ ЗАПЯТОЙ---
        int x = 123_456;
        int y = 234_567__789;
        System.out.println(x);  // 123456
        System.out.println(y);  // 234567789
        System.out.println("---------------------");

        //Java автоматически рассматривает этот литерал как значение типа double
        float fl = 30.6f;
        double db = 30.6;

        // ---СИМВОЛЫ ---
        char ch=102; // символ 'f'
        System.out.println(ch);
        char ch16='\u0066'; //16-тиричная система, символ 'f'
        System.out.println(ch16);
        System.out.println("---------------------");

        // --- СТРОКИ ---
        String hello = "Hello...";
        System.out.println(hello);
        String text = "Hello \nworld";
        System.out.println(text);

        /*
        \t	    Символ табуляции.
        \b	    Символ возврата в тексте на один шаг назад или удаление одного символа в строке (backspace).
        \n	    Символ перехода на новую строку.
        \r	    Символ возврата каретки.
        \f	    Прогон страницы.
        \'	    Символ одинарной кавычки.
        \"	    Символ двойной кавычки.
        \\	    Символ обратной косой черты (\).
         */

        //~~~~ Ctrl+F, then Ctrl+Alt+Shift+J

        System.out.println("test \t-----> \\t - табуляция");
        System.out.println("test \b-----> \\b - возврат на один шаг назад");
        System.out.println("test \n-----> \\n - новая строка");
        System.out.println("test \r-----> \\r - возврат каретки"); //удаляет все от начала
        System.out.println("test \f-----> \\f - прогон страницы");
        System.out.println("test \'-----> \\' - одинарная кавычка");
        System.out.println("test \"-----> \\\" - двойная кавычка");
        System.out.println("test \\-----> \\\\ - обратная косая черта");

        //--- СТРОКИ ---
        // Начиная с версии 15 Java поддерживает тестовые блоки (text blocks) - многострочный текст, облеченный в тройные кавычки.
        // Большой многострочный текст:
        String text2 = "Вот мысль, которой весь я предан,\n"+
                "Итог всего, что ум скопил.\n"+
                "Лишь тот, кем бой за жизнь изведан,\n"+
                "Жизнь и свободу заслужил.";
        System.out.println(text);

        /*
        String textJava15 = """
                Вот мысль, которой весь я предан,
                Итог всего, что ум скопил.
                Лишь тот, кем бой за жизнь изведан,
                Жизнь и свободу заслужил.
                """;
        System.out.println(text);
        */

        String costForPrint = "5$";
        System.out.println("Цена только для вас " +
                + costForPrint.charAt(0) + costForPrint.charAt(1) + getCurrencyName(costForPrint.charAt(1)));



    }
    public static String getCurrencyName(char symbol) {
        if (symbol == '$') {
            return " долларов";
        } else {
            throw new UnsupportedOperationException("Not implemented yet");
        }
    }
}
