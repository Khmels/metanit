package chapter6_IOStreams;

import java.io.*;
import java.util.Arrays;

public class P6_8_FileWriter_FileReader {

    //'java doc'
    //### public class FileWriter extends OutputStreamWriter{}

    /* FileWriter constructors // конструкторы
            FileWriter(String fileName) throws IOException
            FileWriter(String fileName, boolean append) throws IOException
            FileWriter(File file) throws IOException
            FileWriter(File file, boolean append) throws IOException
            FileWriter(FileDescriptor fd)
            FileWriter(String fileName, Charset charset)
            FileWriter(String fileName, Charset charset, boolean append)
            FileWriter(File file, Charset charset) throws IOException
            FileWriter(File file, Charset charset, boolean append) throws IOException
    */

    //'java doc'
    //### public class FileReader extends InputStreamReader {}

    /* FileReader constructors // конструкторы
            FileReader(String fileName) throws FileNotFoundException
            FileReader(File file) throws FileNotFoundException
            FileReader(FileDescriptor fd)
            FileReader(String fileName, Charset charset) throws IOException
            FileReader(File file, Charset charset) throws IOException
    */


    public static void main(String[] args) {
        //--- FileWriter ---
        try(FileWriter writer = new FileWriter("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\notes_FileWriter.txt", false))
        {
            // запись всей строки
            String text = "Hello World! I'm FileWriter";
            writer.write(text);
            // запись по символам
            writer.append('\n');
            writer.append(')');

            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

        //--- FileReader ---
        try(FileReader reader = new FileReader("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\notes_FileWriter.txt"))
        {
            // чтение посимвольно
            int c;
            System.out.println("Let`s read from file:");
            while((c=reader.read())!=-1){

                System.out.print((char)c);
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        System.out.println();
        System.out.println("---------------------");

        // считывание в промежуточный буфер из массива символов
        try(FileReader reader = new FileReader("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\notes_FileWriter.txt"))
        {
            //char[] buf = new char[1];
            //char[] buf = new char[1000];
            char[] buf = new char[256];
            int c;
            // считывается последовательно символы из файла в массив из 256 символов,
            // пока не дойдет до конца файла. в этом случае метод read возвратит число -1
            while((c = reader.read(buf))>0){

                /*
                Поскольку считанная порция файла может быть меньше 256 символов (например, в файле всего 73 символа),
                и если количество считанных данных меньше размера буфера (256),
                то выполняем копирование массива с помощью метода Arrays.copy.

                То есть фактически обрезаем массив buf, оставляя в нем только те символы, которые считаны из файла.
                 */
                if(c < 256){
                    buf = Arrays.copyOf(buf, c);
                }
                System.out.print(buf);
                System.out.println();
                System.out.println(buf.length);
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

    }

}

/*
Хотя с помощью ранее рассмотренных классов можно записывать текст в файлы,
однако они предназначены прежде всего дл работы с бинарными потоками данных,
и их возможностей для полноценной работы с текстовыми файлами недостаточно.

И для этой цели служат совсем другие классы, которые являются наследниками абстрактных классов Reader и Writer.
 */

