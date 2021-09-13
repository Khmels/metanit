package chapter02_Basics;

public class P2_11_Cycles {
    public static void main(String[] args) {
        //--- Цикл for ---
        /*
            for ([инициализация счетчика]; [условие]; [изменение счетчика])
            {
                // действия
            }
         */

        for (int i = 1; i <= 9; i++){
            System.out.printf("Квадрат числа %d равен %d \n", i, i * i);
        }

        //Бесконечный цикл
//        int i = 1;
//        for (; ;){
//            System.out.printf("Квадрат числа %d равен %d \n", i, i * i);
//        }
        System.out.println("---------------------");

        int k = 1;
        for (; k<10;){
            System.out.printf("Квадрат числа %d равен %d \n", k, k * k);
            k++;
        }

        System.out.println("---------------------");
        // в одном цикле несколько переменных
        int n = 10;
        for(int i=0, j = n - 1; i < j; i++, j--){
            System.out.println(i);
            System.out.println(j);
            System.out.println(i * j);
            System.out.println("----------");
        }

        //--- Цикл do ---
        int j = 7;
        do{
            System.out.println(j);
            j--;
        }
        while (j > 0);

        // Хотя переменная j изначально меньше 0, цикл все равно один раз выполнится.
        int j1 = -1;
        do{
            System.out.println("at least one time "+j1);
            j1--;
        }
        while (j1 > 0);
        System.out.println("---------------------");

        //--- Цикл while ---

        int m = 6;
        while (m > 0){
            System.out.println(m);
            m--;
        }

        System.out.println("---------------------");

        //--- Операторы continue и break ---
        // break позволяет выйти из цикла в любой его момент,
        // даже если цикл не закончил свою работу
        System.out.println("----------break-----------");
        for (int i = 0; i < 10; i++){
            if (i == 5)
                break;
            System.out.println(i);
        }

        System.out.println("----------continue-----------");
        // continue - цикл не завершался,
        // а просто переходил к следующей итерации
        for (int i = 0; i < 10; i++){
            if (i == 5)
                continue;
            System.out.println(i);
        }

    }
}
