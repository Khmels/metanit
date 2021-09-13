package chapter02_Basics;

import java.util.Scanner;

public class P2_4_Console {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println("Bye world...");

        System.out.print("Hello world! ");
        System.out.print("Bye world..." + "\n");
        System.out.println("---------------------");

        System.out.print("Hello world \n");

        int x=5;
        int y=6;
        System.out.println("x=" + x + "; y=" + y);

        //функция для форматированного вывода, унаследованная от языка С: System.out.printf()
        int a=5;
        int b=6;
        System.out.printf("a=%d; b=%d \n", a, b);

        String separator = "---------------------";
        System.out.println(separator + ", length = " + separator.length());

        /*
        Спецификаторы и соответствующие им аргументы.
        %d: d означает, что данный спецификатор для вывода целочисленных значений.
        %x: для вывода шестнадцатеричных чисел
        %f: для вывода чисел с плавающей точкой
        %e: для вывода чисел в экспоненциальной форме, например, 1.3e+01
        %c: для вывода одиночного символа
        %s: для вывода строковых значений
         */

        String name = "Tom";
        int age = 30;
        float height = 1.7f;

        System.out.printf("Name: %s  Age: %d  Height: %.2f \n", name, age, height);

        //--- ВВОД С КОНСОЛИ ---
        System.out.println(separator);
//        Scanner in = new Scanner(System.in);
//        System.out.print("Input a number: ");
//        int num = in.nextInt();
//
//        System.out.printf("Your number: %d \n", num);
//        in.close();

        Scanner in2 = new Scanner(System.in);
        System.out.print("Input name: ");
        String nameInput = in2.nextLine();
        System.out.print("Input age: ");
        int ageInput = in2.nextInt();
        System.out.print("Input height: ");
        float heightInput = in2.nextFloat();
        System.out.printf("Name: %s  Age: %d  Height: %.2f \n", nameInput, ageInput, heightInput);
        in2.close();
    }
}
