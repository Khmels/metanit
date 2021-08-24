package chapter6;

import java.io.*;
import java.util.Arrays;

public class P4_ByteArrayInputStream_ByteArrayOutputStream {

//`java doc`
//### public class ByteArrayInputStream extends InputStream{}

/* ByteArrayInputStream constructors // конструкторы
        ByteArrayInputStream(byte buf[])
        ByteArrayInputStream(byte buf[], int offset, int length)
*/

//'java doc'
//### public class ByteArrayOutputStream extends OutputStream {}

/* ByteArrayOutputStream constructors // конструкторы
        ByteArrayOutputStream()
        ByteArrayOutputStream(int size)
*/


    public static void main(String[] args) {

        //--------------- Чтение массива байтов и класс ByteArrayInputStream ---------------

        byte[] arrayIn1 = new byte[]{1, 3, 5, 7};
        ByteArrayInputStream byteStream1 = new ByteArrayInputStream(arrayIn1);
        int i;
        while((i=byteStream1.read())!=-1){

            System.out.println(i);
        }
        // Closing a ByteArrayInputStream has no effect.
        // The methods in this class can be called after the stream has been closed without generating an IOException.

        // Throws: java.io.IOException – if an I/O error occurs.

        try {
            byteStream1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String text = "Hello world!";
        byte[] arrayIn2 = text.getBytes();

        // считывается 5 символов
        ByteArrayInputStream byteStream2 = new ByteArrayInputStream(arrayIn2, 0, 5);
        int c;
        while((c=byteStream2.read())!=-1){

            System.out.print((char)c + " ");
        }
        byte[] newArray = byteStream2.readAllBytes();  //нечего считывать
        // When this stream reaches end of stream, further invocations of this method will return an empty byte array.
        System.out.println(new String(newArray));
        System.out.println("---------------------");

        //! В отличие от других классов потоков для закрытия
        // объекта ByteArrayInputStream не требуется вызывать метод close.


        //--------------- Запись массива байт и класс ByteArrayOutputStream ---------------

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String text2 = "Hello World! I`m the ByteArrayOutputStream";
        byte[] buffer = text2.getBytes();

        /*
        Как и в других потоках вывода в классе ByteArrayOutputStream определен метод write,
        который записывает в поток некоторые данные.

        В данном случае мы записываем в поток массив байтов.
        Этот массив байтов записывается в объекте ByteArrayOutputStream в защищенное поле buf,
        которое представляет также массив байтов (protected byte[] buf).
         */
        try{
            baos.write(buffer);
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }

        // массив байтов в строку
        System.out.println(baos.toString());
        System.out.println("---------------------");

        // массив байтов и выводим по символьно
        byte[] array = baos.toByteArray();
        int size = baos.size();
        int sizeOfArray = array.length;
        for(byte b: array){

            System.out.print((char)b + ".");
        }
        System.out.println();
        System.out.println("Size baos: " + size + "; ArrayLength: " + sizeOfArray);

        byte[] array2 = baos.toByteArray();
        System.out.println();
        System.out.println(Arrays.toString(array2)); // данные остаются
        System.out.println("---------------------");

        /*
        С помощью метода writeTo мы можем вывести массив байт в другой поток.
        Данный метод в качестве параметра принимает объект OutputStream, в который производится запись массива байт:
         */


        ByteArrayOutputStream baosTransfer = new ByteArrayOutputStream();
        String textTransfer = "Hello World!";
        byte[] bufferTransfer = textTransfer.getBytes();
        try{
            baosTransfer.write(bufferTransfer);
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }

        try(FileOutputStream fos = new FileOutputStream("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\hello.txt")){

            baosTransfer.writeTo(fos);
        }
        catch(IOException e){

            System.out.println(e.getMessage());
        }
        System.out.println(new String(baosTransfer.toByteArray())); // данные остаются
        /*
        Как и для объектов ByteArrayInputStream, для ByteArrayOutputStream не надо явным образом закрывать поток с помощью метода close.
         */
    }

}
