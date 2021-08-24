package chapter6;

import org.w3c.dom.ls.LSOutput;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class P2_FileOutputStream_FileInputStream {

//`java doc
//### public class FileOutputStream extends OutputStream

/* FileOutputStream constructors // конструкторы
        FileOutputStream(String filePath)
        FileOutputStream(File fileObj)
        FileOutputStream(String filePath, boolean append)
        FileOutputStream(File fileObj, boolean append)
 */

//`java doc
//### public class FileInputStream extends InputStream

/* FileInputStream constructors // конструкторы
        FileInputStream(File file)
        FileInputStream(FileDescriptor fdObj)
        FileInputStream(String name)
*/


    public static void main(String[] args) {

        //--- FileOutputStream ---
        String text = "Hello world!"; // строка для записи
        String nextLine = "\n";
        byte[] bufferOut = nextLine.getBytes();

        //try c ресурсами
        try(FileOutputStream fos=new FileOutputStream("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\notes.txt"))

        {
            // перевод строки в байты

            fos.write(bufferOut[0]);   // запись первого байта
            //fos.write(buffer[1]); // запись первого байта // \n - один элемент

            bufferOut = text.getBytes();
            fos.write(bufferOut, 0, bufferOut.length);

        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        System.out.println("The file has been written");
        System.out.println("Array has elements: " + new String(bufferOut));
        System.out.println("---------------------");



        //--- FileInputStream ---
        try(FileInputStream fin=new FileInputStream("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\notes.txt"))
        {
            System.out.printf("File size: %d bytes \n", fin.available());

            int i=-1;
            while((i=fin.read())!=-1){

                System.out.print((char)i);
            }
            System.out.println();
            int length = fin.readAllBytes().length;
            System.out.println("How many elements left? " + length);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        System.out.println("---------------------");

        try {
            FileInputStream fin=new FileInputStream("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\notes.txt");
            byte[] buffer = new byte[fin.available()];
            // считаем файл в буфер
            fin.read(buffer, 0, fin.available());
            fin.close();
            System.out.println("File data:");
            for(int i=0; i<buffer.length;i++){

                System.out.print((char)buffer[i]);
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        System.out.println();
        System.out.println("---------------------");

        try(FileInputStream fin=new FileInputStream("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\notes.txt");
            FileInputStream fin2=new FileInputStream("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\notes.txt");
            FileOutputStream fos=new FileOutputStream("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\notes_new.txt"))
        {
            byte[] buffer = new byte[fin.available()]; // нельзя 2 раза прочитать из буфера полностью
            int bufferLength = fin.available();
            byte[] buffer2 = new byte[fin2.available()];
            // считываем буфер
            fin.read(buffer, 6, buffer.length-6);
            System.out.println(Arrays.toString(buffer));
            System.out.println(new String(buffer));
            System.out.println("---------------------");


            fin.read(buffer, 0, bufferLength);
            System.out.println(Arrays.toString(buffer)); // записалась та часть, что не была прочитана вначале
            System.out.println(new String(buffer));
            System.out.println("---------------------");


            int result = fin2.read(buffer2, 0, buffer2.length);
            System.out.println(Arrays.toString(buffer2));
            System.out.println(result);

            // записываем из буфера в файл
            fos.write(buffer, 0, buffer.length);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        System.out.println("---------------------");

        //По одному байту за раз
        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\notes.txt");
            out = new FileOutputStream("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\notes_out.txt");
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } catch (IOException e){
            e.getMessage();
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}






