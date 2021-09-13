package chapter6_IOStreams;

import java.io.*;

public class P6_5_BufferedInputStream_BufferedOutputStream {

    //'java doc'
    //### public class BufferedInputStream extends FilterInputStream {

    /* BufferedInputStream constructors // конструкторы
            BufferedInputStream(InputStream in)
            BufferedInputStream(InputStream in, int size)
    */

    //'java doc'
    //### public class BufferedOutputStream extends FilterOutputStream{}

    /* BufferedOutputStream constructors // конструкторы
            BufferedOutputStream(OutputStream out)
            BufferedOutputStream(OutputStream out, int size)
    */


    public static void main(String[] args) {

        //--- Класс BufferedInputStream ---

        String textIn = "Hello world! -IN";
        byte[] buffer = textIn.getBytes();
        ByteArrayInputStream in = new ByteArrayInputStream(buffer);

        // вместо ByteArrayInputStream может использоваться любой другой класс, который унаследован от InputStream.
        try(BufferedInputStream bis = new BufferedInputStream(in)){

            int c;
            while((c=bis.read())!=-1){

                System.out.print((char)c);
            }
        }
        catch(Exception e){

            System.out.println(e.getMessage());
        }
        System.out.println();

        /*
        Фактические все то же самое можно было сделать и с помощью одного ByteArrayInputStream, не прибегая к буферизированному потоку.
        Класс BufferedInputStream просто оптимизирует производительность при работе с потоком ByteArrayInputStream.
         */

        //--- Класс BufferedOutputStream ---
        String textOut = "Hello world! -OUT"; // строка для записи
        byte[] buffer2 = textOut.getBytes();
        try(FileOutputStream out=new FileOutputStream("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\hello2.txt");
            BufferedOutputStream bos = new BufferedOutputStream(out))
        {
            // перевод строки в байты
            bos.write(buffer2, 0, buffer2.length);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}

/*
Для оптимизации операций ввода-вывода используются буферизуемые потоки.
Эти потоки добавляют к стандартным специальный буфер в памяти,
с помощью которого повышается производительность при чтении и записи потоков.
 */
