package chapter06_IOStreams;

import java.io.*;

public class P6_7_DataOutputStream_DataInputStream {

    //'java doc'
    //### public class DataOutputStream extends FilterOutputStream implements DataOutput {}

    /* DataOutputStream constructors // конструкторы
            DataOutputStream(OutputStream out)
    */

    //'java doc'
    //### public class DataInputStream extends FilterInputStream implements DataInput {}

    /* DataInputStream constructors // конструкторы
            DataInputStream(InputStream in)
    */

    public static void main(String[] args) {

        Person tom = new Person("Tom", 34, 1.68, false);
        // запись в файл
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\data.bin")))
        {
            // записывание значения
            dos.writeUTF(tom.name);
            dos.writeInt(tom.age);
            dos.writeDouble(tom.height);
            dos.writeBoolean(tom.married);
            System.out.println("File has been written");
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        System.out.println("---------------------");
        // обратное считывание из файла
        try(DataInputStream dos = new DataInputStream(new FileInputStream("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\data.bin")))
        {
            // считывание значения
            String name = dos.readUTF();
            int age = dos.readInt();
            double height = dos.readDouble();
            boolean married = dos.readBoolean();
            System.out.printf("Name: %s  Age: %d  Height: %f  Married: %b",
                    name, age, height, married);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

    }
}

/*
Класс DataOutputStream представляет поток вывода и предназначен для записи данных примитивных типов, таких, как int, double и т.д
 */

/* Методы DataOutputStream:
        writeBoolean(boolean v) :   записывает в поток булевое однобайтовое значение
        writeByte(int v):           записывает в поток 1 байт, которые представлен в виде целочисленного значения
        writeChar(int v):           записывает 2-байтовое значение char
        writeDouble(double v):      записывает в поток 8-байтовое значение double
        writeFloat(float v):        записывает в поток 4-байтовое значение float
        writeInt(int v):            записывает в поток целочисленное значение int
        writeLong(long v):          записывает в поток значение long
        writeShort(int v):          записывает в поток значение short
        writeUTF(String str):       записывает в поток строку в кодировке UTF-8
 */

/*
Класс DataInputStream действует противоположным образом - он считывает из потока данные примитивных типов.
 */

/* Методы DataInputStream:
        boolean readBoolean():      считывает из потока булевое однобайтовое значение
        byte readByte():            считывает из потока 1 байт
        char readChar():            считывает из потока значение char
        double readDouble():        считывает из потока 8-байтовое значение double
        float readFloat():          считывает из потока 4-байтовое значение float
        int readInt():              считывает из потока целочисленное значение int
        long readLong():            считывает из потока значение long
        short readShort():          считывает значение short
        String readUTF():           считывает из потока строку в кодировке UTF-8
        int skipBytes(int n):       пропускает при чтении из потока n байтов
 */


