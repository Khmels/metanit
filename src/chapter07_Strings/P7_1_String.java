package chapter07_Strings;

public class P7_1_String {
    public static void main(String[] args) {
        String str1 = "Java";
        //IDEA 'new String()' is redundant
        String str2 = new String(); // пустая строка
        String str3 = new String(new char[] {'h', 'e', 'l', 'l', 'o'});
        String str4 = new String(new char[]{'w', 'e', 'l', 'c', 'o', 'm', 'e'}, 3, 4);//3 -начальный индекс, 4 -кол-во символов

        System.out.println(str1); // Java
        System.out.println(str2); //
        System.out.println(str3); // hello
        System.out.println(str4); // come

        /*
        String является неизменяемым (immutable).
        То есть при любых операциях над строкой, которые изменяют эту строку, фактически будет создаваться новая строка.
         */

        //Поскольку строка рассматривается как набор символов, то можно применить метод length()
        System.out.println(str1.length()); // 4

        String str5 = new String(new char[] {'h', 'e', 'l', 'l', 'o'});
        char[] helloArray = str1.toCharArray();

        // строка не указывает на объект // может быть пустой
        String s = "";
        if(s.length() == 0) {
            System.out.println("String is empty");
        }

        // специальный метод, который позволяет проверить строку на пустоту - isEmpty()
        if(s.isEmpty()) {
            System.out.println("String is empty");
        }

        // Переменная String может не указывать на какой-либо объект и иметь значение null:
        String sNull = null;   // строка не указывает на объект
        if(sNull == null) System.out.println("String is null");

        System.out.println("---------------------");

        // Значение null не эквивалентно пустой строке.
        // Method invocation 'length' will produce 'NullPointerException'
        try {
            if (sNull.length() == 0) System.out.println("String is empty");    // ! Ошибка
        }
        catch(NullPointerException ex){
            System.out.println("Null не равно пустой строке. Обращение к null - NullPointerException");
        }
        /* Так как переменная не указывает ни на какой объект String,
           то нельзя обращаться к методам объекта String.
           Чтобы избежать подобных ошибок, можно предварительно проверять строку на null:
         */
        if(sNull!=null && sNull.length()==0) {
            System.out.println("String is empty");
        }
    }
}

/* Основные методы класса String:

        concat():           объединяет строки
        valueOf():          преобразует объект в строковый вид
        join():             соединяет строки с учетом разделителя
        charAt():           возвращает символ строки по индексу
        getChars():         возвращает группу символов
        equals():           сравнивает строки с учетом регистра
        equalsIgnoreCase(): сравнивает строки без учета регистра
        regionMatches():    сравнивает подстроки в строках
        indexOf():          находит индекс первого вхождения подстроки в строку
        lastIndexOf():      находит индекс последнего вхождения подстроки в строку
        startsWith():       определяет, начинается ли строка с подстроки
        endsWith():         определяет, заканчивается ли строка на определенную подстроку
        replace():          заменяет в строке одну подстроку на другую
        trim():             удаляет начальные и конечные пробелы
        substring():        возвращает подстроку, начиная с определенного индекса до конца или до определенного индекса
        toLowerCase():      переводит все символы строки в нижний регистр
        toUpperCase():      переводит все символы строки в верхний регистр
 */