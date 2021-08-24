package chapter6;

import java.io.*;

public class P6_PrintStream_PrintWriter_InputStreamReader {

    //'java doc'
    //### public class PrintStream extends FilterOutputStream implements Appendable, Closeable

    /* PrintStream constructors // конструкторы
            PrintStream(OutputStream out)
            PrintStream(OutputStream out, boolean autoFlush)
            PrintStream(OutputStream out, boolean autoFlush, String encoding) throws UnsupportedEncodingException
            PrintStream(OutputStream out, boolean autoFlush, Charset charset)

            PrintStream(String fileName) throws FileNotFoundException
            PrintStream(String fileName, String csn) throws FileNotFoundException, UnsupportedEncodingException
            PrintStream(String fileName, Charset charset) throws IOException

            PrintStream(File file) throws FileNotFoundException
            PrintStream(File file, String csn) throws FileNotFoundException, UnsupportedEncodingException
            PrintStream(File file, Charset charset) throws IOException
    */

    //'java doc'
    //### public class PrintWriter extends Writer {}

    /* PrintWriter constructors // конструкторы
            PrintWriter(Writer out)
            PrintWriter(Writer out, boolean autoFlush)
            PrintWriter(OutputStream out)
            PrintWriter(OutputStream out, boolean autoFlush)
            PrintWriter(OutputStream out, boolean autoFlush, Charset charset)
            PrintWriter(String fileName) throws FileNotFoundException
            PrintWriter(String fileName, String csn) FileNotFoundException, UnsupportedEncodingException
            PrintWriter(String fileName, Charset charset) throws IOException
            PrintWriter(File file) throws FileNotFoundException
            PrintWriter(File file, String csn) throws FileNotFoundException, UnsupportedEncodingException
            PrintWriter(File file, Charset charset) throws IOException
    */


    public static void main(String[] args) {
        String text = "Приветос вразброс!"; // строка для записи
        try(FileOutputStream fos=new FileOutputStream("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\notes_new3.txt");
            PrintStream printStream = new PrintStream(fos))
        {
            printStream.println(text);
            System.out.println("Запись в файл произведена");
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

        PrintStream printStream2 = null;
        try {
            printStream2 = new PrintStream("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\notes_new4.txt");
            printStream2.print(text);
            System.out.println("Запись в файл произведена снова");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            printStream2.close();
        }

        try(PrintStream printStream = new PrintStream("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\notes_new5.txt"))
        {
            printStream.print("Hello World!");
            printStream.println("Welcome to Java!");
            printStream.printf("Name: %s Age: %d \n", "Tom", 34);

            String message = "PrintStream";
            byte[] message_toBytes = message.getBytes();
            printStream.write(message_toBytes);

            System.out.println("The file has been written");
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        System.out.println("---------------------");


        System.out.println("---------------------");

        //--- InputStreamReader ---
        System.out.println("Введите символы, символ 'q' для выхода.");
        InputStreamReader inStRe = null;

        try {
            inStRe = new InputStreamReader(System.in);
            char a = ' ';
//            do {
//                a = (char) inStRe.read();
//                System.out.print(a);
//            } while(a != 'q');
            while ( a != 'q'){
                a = (char) inStRe.read();
                if (a != 'q') {
                    System.out.print(a);
                }else
                    System.out.println(a);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inStRe != null) {
                try {
                    inStRe.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //--------- PrintWriter ---------
        /*
         Его можно использовать как для вывода информации на консоль, так и в файл или любой другой поток вывода
         */
        try(PrintWriter pw = new PrintWriter(System.out))
        {
            pw.println("Finish");
        }
    }
}

/*
Класс PrintStream - это именно тот класс, который используется для вывода на консоль.
Когда выводиться на консоль некоторую информацию с помощью вызова System.out.println(), то тем самым задействуется PrintStream,
так как переменная out в классе System как раз и представляет объект класса PrintStream, а метод println() - это метод класса PrintStream.
 */

/*  Параметры конструкторов:

    Параметр outputStream - это объект OutputStream, в который производится запись.
    Параметр autoFlushingOn - при значении true позволяет автоматически записывать данные в поток вывода. По умолчанию этот параметр равен false.
    Параметр charSet - позволяет указать кодировку символов.
*/

/* Методы:

    println():  вывод строковой информации с переводом строки
    print():    вывод строковой информации без перевода строки
    printf():   форматированный вывод
 */

/*
PrintWriter реализует интерфейсы Appendable, Closable и Flushable,
и поэтому после использования представляемый им поток надо закрывать
 */


/*
Стандартные потоки

Все языки программирования обеспечивают поддержку стандартного ввода/вывода,
где программа пользователя может произвести ввод посредством клавиатуры и осуществить вывод на экран компьютера.
Если вы знакомы с языками программирования C либо C++, вам должны быть известны три стандартных устройства STDIN, STDOUT и STDERR.
Аналогичным образом, Java предоставляет следующие три стандартных потока:

Стандартный ввод – используется для перевода данных в программу пользователя,
клавиатура обычно используется в качестве стандартного потока ввода, представленного в виде System.in.

Стандартный вывод – производится для вывода данных, полученных в программе пользователя,
и обычно экран компьютера используется в качестве стандартного потока вывода, представленного в виде System.out.

Стандартная ошибка – используется для вывода данных об ошибке, полученной в программе пользователя,
чаще всего экран компьютера служит в качестве стандартного потока сообщений об ошибках, представленного в виде System.err.

 */