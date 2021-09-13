package chapter7_Strings;

public class P7_2_StringOperations {
    public static void main(String[] args) {

        //--- Соединение строк ---
        // ("+"), concat(), join()

        String str1 = "Java";
        String str2 = "Hello";
        String str3 = str1 + " " + str2;

        System.out.println(str3); // Hello Java

        String str4 = "Год " + 2021;
        /*
        метод valueOf() класса String. Данный метод имеет множество перегрузок и преобразует практически все типы данных к строке.
        Для преобразования объектов различных классов метод valueOf вызывает метод toString() этих классов.
         */

        String str5 = "Java";
        String str6 = "Hello";
        str6 = str6.concat(str5); // HelloJava

        // join() позволяет объединить строки с учетом разделителя
        // join является статическим

        String str7 = String.join(" ", str2, str1); // Hello Java

        //--- Извлечение символов и подстрок
        //char charAt(int index)

        String str = "Java";
        char c = str.charAt(2);
        System.out.println(c); // v

        // для массива символов
        // getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)

        String str8 = "Hello world!";
        int startIndex = 6;
        int endIndex = 11;
        char[] dst=new char[endIndex - startIndex];
        str8.getChars(startIndex, endIndex, dst, 0);
        System.out.println(dst); // world

        //--- Сравнение строк
        // equals() (с учетом регистра) и equalsIgnoreCase() (без учета регистра)
        // для строк не применяется знак равенства ==. Вместо него надо использовать метод equals()
        // метод regionMatches() сравнивает отдельные подстроки в рамках двух строк


        String str9 = "Hello";
        String str10 = "hello";

        System.out.println(str9.equals(str10)); // false
        System.out.println(str9.equalsIgnoreCase(str10)); // true

        // boolean regionMatches(int toffset, String other, int oofset, int len)
        // boolean regionMatches(boolean ignoreCase, int toffset, String other, int oofset, int len)

        String str11 = "Hello world";
        String str12 = "I work";
        boolean result = str11.regionMatches(6, str12, 2, 3);
        System.out.println(result); // true

        /*
        int compareTo(String str) и int compareToIgnoreCase(String str) также позволяют сравнить две строки,
        но при этом они также позволяют узнать больше ли одна строка, чем другая или нет.
        Если возвращаемое значение больше 0, то первая строка больше второй, если меньше нуля,
        то, наоборот, вторая больше первой. Если строки равны, то возвращается 0
         */

        /*
        Для определения больше или меньше одна строка, чем другая, используется лексикографический порядок.
        То есть, например, строка "A" меньше, чем строка "B", так как символ 'A' в алфавите стоит перед символом 'B'.

        Если первые символы строк равны, то в расчет берутся следующие символы. Например:
         */

        String str13 = "hello";
        String str14 = "world";
        String str15 = "worl";

        System.out.println(str13.compareTo(str14)); // -15 - str1 меньше чем strt2
        System.out.println(str14.compareTo(str15)); // 1 - str1 больше чем str3

        //--- Поиск в строке

        // Метод indexOf() находит индекс первого вхождения подстроки в строку,
        // а метод lastIndexOf() - индекс последнего вхождения. Если подстрока не будет найдена, то оба метода возвращают -1:

        String str16 = "Hello world";
        int index1 = str16.indexOf('l');         // 2
        int index2 = str16.indexOf("wo");        // 6
        int index3 = str16.lastIndexOf('l'); // 9

        // Метод startsWith() позволяют определить начинается ли строка с определенной подстроки,
        // а метод endsWith() позволяет определить заканчивается строка на определенную подстроку:

        String str17 = "myFile.exe";
        boolean start = str17.startsWith("my"); //true
        boolean end = str17.endsWith("exe"); //true

        //--- Замена в строке
        // replace() позволяет заменить в строке одну последовательность символов на другую

        String str18 = "Hello world";
        String replStr1 = str18.replace('l', 'd'); // Heddo wordd
        String replStr2 = str18.replace("Hello", "Bye"); // Bye world
        System.out.println(replStr1);
        System.out.println(replStr2);

        //--- Обрезка строки
        // trim() позволяет удалить начальные и конечные пробелы:

        String str19 = "  hello world  ";
        str19 = str19.trim(); // hello world

        // substring() возвращает подстроку, начиная с определенного индекса до конца или до определенного индекса:

        String str20 = "Hello world";
        String substr1 = str20.substring(6); // world
        String substr2 = str20.substring(3,5); //lo

        //--- Изменение регистра
        // toLowerCase() переводит все символы строки в нижний регистр, а метод toUpperCase() - в верхний
        String str21 = "Hello World";
        System.out.println(str21.toLowerCase()); // hello world
        System.out.println(str21.toUpperCase()); // HELLO WORLD

        //--- Разбивка строки
        // split() позволяет разбить строку на подстроки по определенному разделителю.
        // Разделитель - какой-нибудь символ или набор символов передается в качестве параметра в метод

        System.out.println("---------------------");
        String text = "FIFA will never regret it";
        String[] words = text.split(" ");
        for(String word : words){
            System.out.println(word);
        }


    }
}
