package chapter6;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class P3_ClosingStream {

    public static void main(String[] args) {
        // Есть два способа закрытия файла.

        //--- Первый способ ---
        // традиционный заключается в использовании блока try..catch..finally.

        FileInputStream fin=null;
        try
        {
            fin = new FileInputStream("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\notes_new.txt");

            int i=-1;
            while((i=fin.read())!=-1){

                System.out.print((char)i);
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
            /*
            Поскольку при открытии или считывании файла может произойти ошибка ввода-вывода,
            то код считывания помещается в блок try.
            И чтобы быть уверенным, что поток в любом случае закроется, даже если при работе с ним возникнет ошибка,
            вызов метода close() помещается в блок finally
            */
        finally{

            /*
            И, так как метод close() также в случае ошибки может генерировать исключение IOException,
            то его вызов также помещается во вложенный блок try..catch
             */

            try{

                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }
        }

        //--- Второй способ ---

        /* с Java 7 можно использовать еще один способ, который автоматически вызывает метод close.

        Этот способ заключается в использовании конструкции try-with-resources (try-с-ресурсами).
        Данная конструкция работает с объектами, которые реализуют интерфейс AutoCloseable.

        Так как все классы потоков реализуют интерфейс Closeable,
        который в свою очередь наследуется от AutoCloseable,
        то их также можно использовать в данной конструкции
         */

        // Синтаксис конструкции следующий:
        // try(название_класса имя_переменной = конструктор_класса).
        // Данная конструкция также не исключает использования блоков catch.

        try(FileInputStream fin2=new FileInputStream("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\notes_new.txt"))
        {
            int i=-1;
            while((i=fin2.read())!=-1){

                System.out.print((char)i);
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

        // После окончания работы в блоке try у ресурса автоматически вызывается метод close().

        // Если нам надо использовать несколько потоков, которые после выполнения надо закрыть,
        // то можно указать объекты потоков через точку с запятой:

        try(FileInputStream fin2=new FileInputStream("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\notes.txt");
            FileOutputStream fos=new FileOutputStream("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\notes_new2.txt"))
        {
            byte[] buffer2 = new byte[fin2.available()];
            // считываем буфер
            fin2.read(buffer2, 0, buffer2.length-1);
            System.out.println(Arrays.toString(buffer2));
            // записываем из буфера в файл
            fos.write(buffer2, 1, buffer2.length-1);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}

/* При завершении работы с потоком его надо закрыть с помощью метода close(),
 который определен в интерфейсе Closeable. Метод close имеет следующее определение:
 void close() throws IOException

 При закрытии потока освобождаются все выделенные для него ресурсы, например, файл.
 В случае, если поток окажется не закрыт, может происходить утечка памяти.
 */


