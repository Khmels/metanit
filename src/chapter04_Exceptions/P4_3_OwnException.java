package chapter04_Exceptions;

public class P4_3_OwnException {

    public static void main(String[] args) {
        try{
            int result = Factorial.getFactorial(0);
            System.out.println(result);
        }
        catch(FactorialException ex){

            System.out.println("Message: " + ex.getMessage());
            System.out.println("Number: " + ex.getNumber());
        }
    }
}

class Factorial{

    // так как это исключение не обрабатывается с помощью try..catch,
    // то передается обработка вызывающему методу, используя оператор throws:
    // public static int getFactorial(int num) throws FactorialException

    public static int getFactorial(int num) throws FactorialException{

        int result=1;

        // Для генерации исключения в методе вычисления факториала выбрасывается исключение с помощью оператора throw:
        // throw new FactorialException("Число не может быть меньше 1", num).

        if(num<1) throw new FactorialException("The number is less than 1", num);

        for(int i=1; i<=num;i++){

            result*=i;
        }
        return result;
    }
}

class FactorialException extends Exception{

    private int number;
    public int getNumber(){return number;}
    public FactorialException(String message, int num){

        super(message);
        number=num;
    }
}
