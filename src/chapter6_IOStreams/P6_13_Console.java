package chapter6_IOStreams;

import java.io.Console;

public class P6_13_Console {

    //'java doc'
    //### public final class Console implements Flushable{}

    /* Console constructors // конструкторы
        для получения объекта консоли надо вызвать статический метод System.console():
    */


    public static void main(String[] args) {
        Console console = System.console();

        if(console!=null){
            // считывание данные с консоли
            String login = console.readLine("Введите логин / Enter Login:");
            char[] password = console.readPassword("Введите пароль / Enter password:");

            console.printf("Введенный логин / Entered login: %s \n", login);
            console.printf("Введенный пароль / Entered password: %s \n", new String(password));
        }

        //! Важно, что доступ к консоли можно получить только из самой консоли.
        // При запуске из IDE вызов System.console() будет возвращать значение null.
        //
        // Поэтому при работе с консолью желательно проверять полученное значение на null.
        // Запускать программу неоходимо в командной строке
    }
}

/*
Специально для работы с консолью в Java определен класс Console, который хранится в пакете java.io.
Он не получает консольный ввод-вывод сам по себе, а использует уже имеющиеся потоки System.in и System.out.
Но в то же время Console значительно упрощает ряд операций, связанных с консолью.
 */

/* Методы:

    flush():                выводит на консоль все данные из буфера
    format():               выводит на консоль строку с использованием форматирования
    printf():               выводит на консоль строку с использованием форматирования (фактически то же самое, что и предыдущий метод)
    String readLine():      считывает с консоли введенную пользователем строку
    char[] readPassword():  считывает с консоли введенную пользователем строку, при этом символы строки не отображаются на консоли
 */
