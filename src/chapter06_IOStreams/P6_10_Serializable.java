package chapter06_IOStreams;

import java.io.*;
import java.util.ArrayList;

public class P6_10_Serializable {

    //'java doc'
    //### public interface Serializable { }

    //'java doc'
    //### public class ObjectOutputStream extends OutputStream implements ObjectOutput, ObjectStreamConstants{}

    /* ObjectOutputStream constructors // конструкторы
            ObjectOutputStream(OutputStream out) throws IOException
    */

    //'java doc'
    //### public class ObjectInputStream extends InputStream implements ObjectInput, ObjectStreamConstants {}

    /* ObjectInputStream constructors // конструкторы
            ObjectInputStream(InputStream in) throws IOException
    */

    public static void main(String[] args) {

        try(
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\person2.dat")))
        {
            Person p = new Person("Sam", 31, 173, false);
            Person_Serial pS = new Person_Serial("Sam", 31, 173, false);
            oos.writeObject(pS);
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
        System.out.println("---------------------");

        //--- Десериализация. Класс ObjectInputStream ---

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\person.dat")))
        {
            Person p=(Person)ois.readObject();
            System.out.printf("Name: %s \t Age: %d \n", p.getName(), p.getAge());
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
        System.out.println("---------------------");

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\person2.dat")))
        {
            Person_Serial p=(Person_Serial)ois.readObject();
            System.out.printf("Name: %s \t Age: %d \n", p.getName(), p.getAge());
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
        System.out.println("---------------------");

        // --- Несколько объектов

        String filename = "people.dat";
        // создадим список объектов, которые будем записывать
        ArrayList<Person_Serial> people = new ArrayList<Person_Serial>();
        people.add(new Person_Serial("Tom", 30, 175, false));
        people.add(new Person_Serial("Sam", 33, 178, true));

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename)))
        {
            oos.writeObject(people);
            System.out.println("File has been written");
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }

        // десериализация в новый список

        ArrayList<Person_Serial> newPeople= new ArrayList<Person_Serial>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename)))
        {
            // IDEA: Unchecked cast: 'java.lang.Object' to 'java.util.ArrayList<chapter6.Person_Serial>
            newPeople=(ArrayList<Person_Serial>)ois.readObject();
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }

        for(Person_Serial p : newPeople)
            System.out.printf("Name: %s \t Age: %d \n", p.getName(), p.getAge());

        //--- Исключение данных из сериализации
        // По умолчанию сериализуются все переменные объекта.
        // Однако, возможно, мы хотим, чтобы некоторые поля были исключены из сериализации.
        // Для этого они должны быть объявлены с модификатором transient.
        // Например, исключим из сериализации объекта Person переменные height и married:
    }
}

/* Сериализация представляет процесс записи состояния объекта в поток,
 соответственно процесс извлечения или восстановления состояния объекта из потока называется десериализацией.

 Сериализация очень удобна, когда идет работа со сложными объектами.

 */


/*  Интерфейс Serializable
сериализовать можно только те объекты, которые реализуют интерфейс Serializable.
Этот интерфейс не определяет никаких методов, просто он служит указателем системе,
что объект, реализующий его, может быть сериализован.
 */

// Для сериализации объектов в поток используется класс ObjectOutputStream

/* Методы ObjectOutputStream

    void close():                       закрывает поток
    void flush():                       очищает буфер и сбрасывает его содержимое в выходной поток
    void write(byte[] buf):             записывает в поток массив байтов
    void write(int val):                записывает в поток один младший байт из val
    void writeBoolean(boolean val):     записывает в поток значение boolean
    void writeByte(int val):            записывает в поток один младший байт из val
    void writeChar(int val):            записывает в поток значение типа char, представленное целочисленным значением
    void writeDouble(double val):       записывает в поток значение типа double
    void writeFloat(float val):         записывает в поток значение типа float
    void writeInt(int val):             записывает целочисленное значение int
    void writeLong(long val):           записывает значение типа long
    void writeShort(int val):           записывает значение типа short
    void writeUTF(String str):          записывает в поток строку в кодировке UTF-8
    void writeObject(Object obj):       записывает в поток отдельный объект
 */

/* Методы ObjectInputStream

    void close():                       закрывает поток
    int skipBytes(int len):             пропускает при чтении несколько байт, количество которых равно len
    int available():                    возвращает количество байт, доступных для чтения
    int read():                         считывает из потока один байт и возвращает его целочисленное представление
    boolean readBoolean():              считывает из потока одно значение boolean
    byte readByte():                    считывает из потока один байт
    char readChar():                    считывает из потока один символ char
    double readDouble():                считывает значение типа double
    float readFloat():                  считывает из потока значение типа float
    int readInt():                      считывает целочисленное значение int
    long readLong():                    считывает значение типа long
    short readShort():                  считывает значение типа short
    String readUTF():                   считывает строку в кодировке UTF-8
    Object readObject():                считывает из потока объект
 */