package chapter6;

import java.io.*;

public class P1_IOStream_Reader_Writer {
    public static void main(String[] args) {

        InputStream inputStream = new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };

        OutputStream outputStream = new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }
        };

        Reader reader = new Reader() {
            @Override
            public int read(char[] cbuf, int off, int len) throws IOException {
                return 0;
            }

            @Override
            public void close() throws IOException {

            }
        };

        Writer writer = new Writer() {
            @Override
            public void write(char[] cbuf, int off, int len) throws IOException {

            }

            @Override
            public void flush() throws IOException {

            }

            @Override
            public void close() throws IOException {

            }
        };

    }

}

//--- Абстрактные классы InputStream и OutputStream ---

//`java doc`
//### public abstract class InputStream implements Closeable {}

/* InputStream базовый для всех классов, управляющих байтовыми потоками ввода
    Методы:

    int available():            возвращает количество байтов, доступных для чтения в потоке

    void close():               закрывает поток

    int read():                 возвращает целочисленное представление следующего байта в потоке.
                                Когда в потоке не останется доступных для чтения байтов, данный метод возвратит число -1

    int read(byte[] buffer):    считывает байты из потока в массив buffer. После чтения возвращает число считанных байтов.
                                Если ни одного байта не было считано, то возвращается число -1

    int read(byte[] buffer, int offset, int length): считывает некоторое количество байтов,
                                равное length, из массива buffer.
                                При этом считанные байты помещаются в массиве, начиная со смещения offset,
                                то есть с элемента buffer[offset]. Метод возвращает число успешно прочитанных байтов.

    long skip(long number):     пропускает в потоке при чтении некоторое количество байт, которое равно number
 */

//`java doc`
//### public abstract class OutputStream implements Closeable, Flushable {}

// OutputStream базовый классом для всех классов, которые работают с бинарными потоками записи.

/*    Методы:

    void close():               закрывает поток

    void lush():                очищает буфер вывода, записывая все его содержимое

    void write(int b):          записывает в выходной поток один байт, который представлен целочисленным параметром b

    void write(byte[] buffer):  записывает в выходной поток массив байтов buffer.

    void write(byte[] buffer, int offset, int length): записывает в выходной поток некоторое число байтов,
                                равное length, из массива buffer,
                                начиная со смещения offset, то есть с элемента buffer[offset].
 */

//--- Абстрактные классы Reader и Writer ---

//`java doc`
//### public abstract class Reader implements Readable, Closeable {}

// Абстрактный класс Reader предоставляет функционал для чтения текстовой информации. Рассмотрим его основные :

/*    Методы:

    abstract void close():      закрывает поток ввода

    int read():                 возвращает целочисленное представление следующего символа в потоке.
                                Если таких символов нет, и достигнут конец файла, то возвращается число -1

    int read(char[] buffer):    считывает в массив buffer из потока символы, количество которых равно длине массива buffer.
                                Возвращает количество успешно считанных символов. При достижении конца файла возвращает -1

    int read(CharBuffer buffer): считывает в объект CharBuffer из потока символы.
                                Возвращает количество успешно считанных символов. При достижении конца файла возвращает -1

    abstract int read(char[] buffer, int offset, int count): считывает в массив buffer, начиная со смещения offset,
                                из потока символы, количество которых равно count

    long skip(long count):      пропускает количество символов, равное count. Возвращает число успешно пропущенных символов
*/

//`java doc`
//### public abstract class Writer implements Appendable, Closeable, Flushable {

// Класс Writer определяет функционал для всех символьных потоков вывода.

/* Основные методы:

    Writer append(char c):              добавляет в конец выходного потока символ c. Возвращает объект Writer
    Writer append(CharSequence chars):  добавляет в конец выходного потока набор символов chars. Возвращает объект Writer
    abstract void close():              закрывает поток
    abstract void flush():              очищает буферы потока
    void write(int c):                  записывает в поток один символ, который имеет целочисленное представление
    void write(char[] buffer):          записывает в поток массив символов
    abstract void write(char[] buffer, int off, int len) : записывает в поток только несколько символов из массива buffer.
                                        Причем количество символов равно len, а отбор символов из массива начинается с индекса off

    void write(String str):             записывает в поток строку
    void write(String str, int off, int len): записывает в поток из строки некоторое количество символов, которое равно len,
                                        причем отбор символов из строки начинается с индекса off
 */

// Функционал, описанный классами Reader и Writer, наследуется непосредственно классами символьных потоков,
// в частности классами FileReader и FileWriter соответственно, предназначенными для работы с текстовыми файлами.

/*
Отличительной чертой многих языков программирования является работа с файлами и потоками.
В Java основной функционал работы с потоками сосредоточен в классах из пакета java.io.

Ключевым понятием здесь является понятие потока.
Хотя понятие "поток" в программировании довольно перегружено и может обозначать множество различных концепций.
В данном случае применительно к работе с файлами и вводом-выводом мы будем говорить о потоке (stream), как об абстракции,
которая используется для чтения или записи информации (файлов, сокетов, текста консоли и т.д.).

Поток связан с реальным физическим устройством с помощью системы ввода-вывода Java.
У нас может быть определен поток, который связан с файлом и через который можно вести чтение или запись файла.
Это также может быть поток, связанный с сетевым сокетом, с помощью которого можно получить или отправить данные в сети.

Все эти задачи: чтение и запись различных файлов, обмен информацией по сети, ввод-ввывод в консоли мы будем решать в Java с помощью потоков.

Объект, из которого можно считать данные, называется потоком ввода,
а объект, в который можно записывать данные, - потоком вывода.
Например, если надо считать содержание файла, то применяется поток ввода, а если надо записать в файл - то поток вывода.

В основе всех классов, управляющих потоками байтов, находятся два абстрактных класса:
InputStream (представляющий потоки ввода) и OutputStream (представляющий потоки вывода)

Но поскольку работать с байтами не очень удобно,
то для работы с потоками символов были добавлены абстрактные классы
  Reader (для чтения потоков символов)
и Writer (для записи потоков символов).
 */
