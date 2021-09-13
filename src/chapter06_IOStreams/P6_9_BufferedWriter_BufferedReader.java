package chapter06_IOStreams;

import java.io.*;

public class P6_9_BufferedWriter_BufferedReader {

    //'java doc'
    //### public class BufferedWriter extends Writer{}

    /* BufferedWriter constructors // конструкторы
            BufferedWriter(Writer out)
            BufferedWriter(Writer out, int sz)
    */

    //'java doc'
    //### public class BufferedReader extends Reader{}

    /* BufferedReader constructors // конструкторы
            BufferedReader(Reader in, int sz)
            BufferedReader(Reader in)
    */


    public static void main(String[] args) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\notes_BufferedWriter.txt")))
        {
            String text = "Hello  World!\nHey! Teachers! Leave them kids alone.";
            bw.write(text);
            System.out.println("Запись завершена");
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        System.out.println("---------------------");


        try(BufferedReader br = new BufferedReader (new FileReader("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\notes_BufferedWriter.txt")))
        {
            // чтение посимвольно
            int c;
            while((c=br.read())!=-1){

                System.out.print((char)c);
            }
            System.out.println();
        }
        catch(IOException ex){
            // notes4.txt (Не удается найти указанный файл)
            System.out.println(ex.getMessage());
        }
        System.out.println("---------------------");


        // считывание построчно
        try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\notes_BufferedWriter.txt")))
        {
            //чтение построчно
            String s;
            while((s=br.readLine())!=null){

                System.out.println(s);
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }


        //--- Считывание с консоли в файл
        try(BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\KhmelSE\\IdeaProjects\\metanit\\src\\chapter6\\notes_FromConsole.txt")))
        {
            // чтение построчно
            String text;
            while(!(text=br.readLine()).equals("ESC")){

                bw.write(text + "\n");
                bw.flush();
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
/*
Класс BufferedWriter записывает текст в поток, предварительно буферизируя записываемые символы,
тем самым снижая количество обращений к физическому носителю для записи данных
 */

/*
Класс BufferedReader считывает текст из символьного потока ввода, буферизируя прочитанные символы.
Использование буфера призвано увеличить производительность чтения данных из потока
 */