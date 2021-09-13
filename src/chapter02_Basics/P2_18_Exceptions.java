package chapter02_Basics;

import java.util.Scanner;

public class P2_18_Exceptions {
    public static void main(String[] args) {

//        int[] numbers = new int[3];
//        numbers[4]=45;
//        System.out.println(numbers[4]);
//        // Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 4 out of bounds for length 3

//        try{
//            int[] numbersTry = new int[3];
//            numbersTry[4]=45;
//            System.out.println(numbersTry[4]);
//        }
//        catch(Exception ex){
//            ex.printStackTrace();
//        }
//        System.out.println("Программа завершена");

        System.out.println("---------------------");

        try{
            int[] numbers = new int[3];
            numbers[4]=45;
            System.out.println(numbers[4]);
        }
        catch(Exception ex){

            ex.printStackTrace();
        }
        finally{
            System.out.println("Блок finally");
        }
        System.out.println("Программа завершена");

        System.out.println("---------------------");

        //--- Обработка нескольких исключений ---
        int[] numbers = new int[3];
        try{
            numbers[2]=45;
            numbers[6]=Integer.parseInt("gfd");
        }
        catch(ArrayIndexOutOfBoundsException ex){
            System.out.println("Выход за пределы массива");
        }
        catch(NumberFormatException ex){
            System.out.println("Ошибка преобразования из строки в число");
        }

        //--- Оператор throw ---

        try{
            Scanner in = new Scanner(System.in);
            System.out.println("please input number");
            int x = in.nextInt();
            if(x>=30){
                throw new Exception("Число х должно быть меньше 30");
            }
            in.close();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        System.out.println("Программа завершена");

    }
}
