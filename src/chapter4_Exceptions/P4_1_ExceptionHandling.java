package chapter4_Exceptions;

public class P4_1_ExceptionHandling {
    public static void main(String[] args){

        try{
            int result = getFactorial(-6);

            System.out.println(result);
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }

        int res = getFactorialException(-2);
        System.out.println(res);
    }

    public static int getFactorial(int num) throws Exception{

        if(num<1) throw new Exception("The number is less than 1");
        int result=1;
        for(int i=1; i<=num;i++){

            result*=i;
        }
        return result;
    }

    // В качестве альтернативы можно и не использовать оператор throws, а обработать исключение прямо в методе:
    public static int getFactorialException(int num){

        int result=1;
        try{
            if(num<1) throw new Exception("The number is less than 1");

            for(int i=1; i<=num;i++){

                result*=i;
            }
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
            result=num;
        }
        return result;
    }


}
