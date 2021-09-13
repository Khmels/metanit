package chapter06_IOStreams;

import java.io.File;
import java.io.IOException;

//'java doc'
//### public class File implements Serializable, Comparable<java.io.File> {}

/* File constructors // конструкторы
        File(String pathname)
        File(String parent, String child)
        File(File parent, String child)
        File(URI uri)
*/


public class P6_11_File {
    public static void main(String[] args) {
        // создать объект File для каталога
        File dir1 = new File("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\NewDir");
        dir1.mkdir();
        // создать объекты для файлов, которые находятся в каталоге
        File file1 = new File("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6", "Hello_File.txt");
        File file2 = new File(dir1, "Hello_File2.txt");
        try {
            file2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("---------------------");


        //--- Директории ---

        // определение объект для каталога
        File dir = new File("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\");
        // если объект представляет каталог
        if(dir.isDirectory())
        {
            // получение все вложенные объекты в каталоге
            for(File item : dir.listFiles()){

                if(item.isDirectory()){

                    System.out.println(item.getName() + "         \t folder");
                }
                else{

                    System.out.println(item.getName() + "   \t file");
                }
            }
        }
        System.out.println("---------------------");

        // объект для каталога
        File dir2 = new File("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\NewDir\\New");
        boolean created = dir2.mkdir();
        if(created)
            System.out.println("Folder has been created");
        // переименование каталога
        File newDir = new File("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\NewDirRenamed");
        dir2.renameTo(newDir);
        //удаление каталога
        boolean deleted = newDir.delete();
        if(deleted)
            System.out.println("Folder has been deleted");
        System.out.println("---------------------");


        //--- Файлы ---
        File dir3 = new File("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\NewDir");
        File myFile = new File(dir3,"Hello_File2.txt");
        System.out.println("File name: " + myFile.getName());
        System.out.println("Parent folder: " + myFile.getParent());
        System.out.println("---------------------");

        if(myFile.exists())
            System.out.println("File exists");
        else
            System.out.println("File not found");
        System.out.println("---------------------");

        System.out.println("File size: " + myFile.length());
        if(myFile.canRead())
            System.out.println("File can be read");
        else
            System.out.println("File can not be read");
        System.out.println("---------------------");

        if(myFile.canWrite())
            System.out.println("File can be written");
        else
            System.out.println("File can not be written");

        // создадим новый файл
        File newFile = new File("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\NewDir\\New2.txt");
        try
        {
            boolean createdFile = newFile.createNewFile();
            if(createdFile)
                System.out.println("File has been created");
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}

/*
Класс File, определенный в пакете java.io, не работает напрямую с потоками.
Его задачей является управление информацией о файлах и каталогах.
Хотя на уровне операционной системы файлы и каталоги отличаются, но в Java они описываются одним классом File.
 */
