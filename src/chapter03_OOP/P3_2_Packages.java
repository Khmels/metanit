package chapter03_OOP;

import java.util.ArrayList;
import java.util.List;

// любой класс из java.util
//import java.util.*;

// Эти классы имеют статические методы.
// Благодаря операции статического импорта можно использовать эти методы без названия класса.
import static java.lang.System.*;
import static java.lang.Math.*;

public class P3_2_Packages {

    /*
    javac packageExample\Program.java

   //необходимо указывать пакет в пути
    java chapter3.packageExample.Program
     */

    //--- Импорт пакетов и классов ---


    public static void main(String[] args) {
        //полный путь  - без импорта
        java.util.Scanner in = new java.util.Scanner(System.in);
        in.close();
        //произошел импорт
        List<String> list = new ArrayList<String>();

        //если одинаковое название, то полный путь
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(1000);

        double result = sqrt(20);
        double random = random();
        out.println(result);
        out.print(random);

    }
}
