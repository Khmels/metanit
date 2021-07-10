package chapter2;

public class P10_Conditional {
    public static void main(String[] args) {

        //--- Конструкция if/else ---

        int num1 = 6;
        int num2 = 4;
        if(num1>num2){
            System.out.println("Первое число больше второго");
        }

        int num3 = 4;
        int num4 = 6;
        if(num3>num4){
            System.out.println("Первое число больше второго");
        }
        else{
            System.out.println("Первое число меньше второго");
        }

        int num5 = 6;
        int num6 = 6;
        if(num5>num6){
            System.out.println("Первое число больше второго");
        }
        else if(num5<num6){
            System.out.println("Первое число меньше второго");
        }
        else{
            System.out.println("Числа равны");
        }

        int num7 = 8;
        int num8 = 6;
        if(num7 > num8 && num7>7){
            System.out.println("Первое число больше второго и больше 7");
        }

        //--- Конструкция switch ---
        System.out.println("---------------------");

        int num = 8;
        switch(num){
            case 1:
                System.out.println("число равно 1");
                break;
            case 8:
                System.out.println("число равно 8");
                num++;
                break;
            case 9:
                System.out.println("число равно 9");
                break;
            default:
                System.out.println("число не равно 1, 8, 9");
        }

        System.out.println("---------------------");
        int numAnother = 8;
        switch(numAnother){
            case 1:
                System.out.println("число равно 1");
                break;
            case 8:
                System.out.println("число равно 8");
                numAnother++;
            case 9:
                System.out.println("число равно 9");
                break;
            default:
                System.out.println("число не равно 1, 8, 9");
        }
        System.out.println(num);
        System.out.println(numAnother);

        System.out.println("---------------------");

        int number = 3;
        int output = 0;
        switch(number){

            case 1:
                output = 3;
                break;
            case 2:
            case 3:
                output = 5;
                System.out.println("число равно " + number);
            case 4:
                output = 6;
            case 5:
                output = 12;
            default:
                output = 24;
        }
        System.out.println(output);

        System.out.println("---------------------");
        //--- Тернарная операция ---

        // [первый операнд - условие] ? [второй операнд] : [третий операнд].
        int x=3;
        int y=2;
        int z = x<y ? (x+y) : (x-y);
        //если условие true, то второй операнд; если равно false, то третий.
        System.out.println(z); // 1

    }
}
