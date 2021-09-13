package chapter06_IOStreams;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.zip.*;

public class P6_12_ZipInputStream_ZipOutputStream {

    //'java doc'
    //### public class ZipOutputStream extends DeflaterOutputStream implements ZipConstants {}

    /* ZipOutputStream constructors // конструкторы
            ZipOutputStream(OutputStream out)
            ZipOutputStream(OutputStream out, Charset charset)
    */

    //'java doc'
    //### public class ZipInputStream extends InflaterInputStream implements ZipConstants {}

    /* ZipInputStream constructors // конструкторы
            ZipInputStream(InputStream in)
            ZipInputStream(InputStream in, Charset charset)
    */


    public static void main(String[] args) {

        String zipName = "C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\output.zip";

        //--- ZipOutputStream. Запись архивов
        String filename = "C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\NewDir\\New.txt";
        try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipName));
            FileInputStream fis= new FileInputStream(filename);)
        {
            ZipEntry entry1=new ZipEntry("new.txt");
            zout.putNextEntry(entry1);
            // считывание содержимого файла в массив byte
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            System.out.println(Arrays.toString(buffer));
            // добавление содержимого к архиву
            zout.write(buffer);
            // закрытие текущей записи для новой записи
            zout.closeEntry();

            /*
            После добавления объекта ZipEntry в поток нам также надо добавить в него и содержимое файла: zout.write(buffer)
            В конце надо закрыть ZipEntry с помощью метода closeEntry().
            После этого можно добавлять в архив новые файлы - в этом случае все вышеописанные действия для каждого нового файла повторяются
             */
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
        System.out.println("---------------------");


        //--- ZipInputStream. Чтение архивов

        /*
        Для считывания файлов из архива ZipInputStream использует метод getNextEntry(), который возвращает объект ZipEntry.
        Объект ZipEntry представляет отдельную запись в zip-архиве.
         */


        try(ZipInputStream zin = new ZipInputStream(new FileInputStream(zipName));
            ByteArrayOutputStream baos = new ByteArrayOutputStream())
        {
            ZipEntry entry;
            String name;
            long size;
            while((entry=zin.getNextEntry())!=null){

                name = entry.getName(); // название файла
                // the uncompressed size of the entry data, or -1 if not known
                size=entry.getSize();   // его размер в байтах //-1
                // При использовании ZipInputStream метод entry.getSize()
                // (и некоторые другие методы класса ZipEntry) будут возвращать -1, если zin не считан в какой-нибудь OutputStream.
                while (zin.available() > 0) {
                    baos.write(zin.read());
                }
                long size2=entry.getSize();
                System.out.printf("File name: %s \t File size: %d \t File size after read: %d \n ", name, size, size2);

                // распаковка
                FileOutputStream fout = new FileOutputStream("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6" + "\\" + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                System.out.println("Архив распакован");
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }

    }
}

/*
Функциональность для работы с таким видом файлов как zip-архивы.
Для этого в пакете java.util.zip определены два класса - ZipInputStream и ZipOutputStream
 */

//`java doc`
//### public class ZipEntry implements ZipConstants, Cloneable