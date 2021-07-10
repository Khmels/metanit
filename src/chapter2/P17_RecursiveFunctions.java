package chapter2;

public class P17_RecursiveFunctions {
    public static void main(String[] args) {

        System.out.println(factorial(5));
        System.out.println(factorialCycle(6));
        System.out.println(factorial(7));
        System.out.println(factorialCycle(2));
        System.out.println("---------------------");
        int n= 20;
        System.out.println(n + "-й член последовательности Фибоначчи: " + fibonachi(n));
        fibonacci(20);
        fibonacci(20);

    }

    // Рекурсивная функция обязательно должна иметь некоторый базовый вариант,
    // который использует оператор return и который помещается в начале функции.

    static int factorial(int x){
        if (x == 1){
            return 1;
        }
        return x * factorial(x - 1);
    }

    static int factorialCycle(int x){
        int result=1;
        for (int i = 1; i <= x; i++)
        {
            result *= i;
        }
        return  result;
    }

    static int fibonachi(int n){
        if (n == 0){
            return 0;
        }
        if (n == 1){
            return 1;
        }
        else{
            return fibonachi(n - 1) + fibonachi(n - 2);
        }
    }

    static int fibonacci(int n){
        int prev=0, next=1, result=0;
        for (int i = 0; i < n-1; i++) {
            result=prev+next;
            prev=next;
            next=result;
            System.out.print(result + " ");
        }
        return result;
    }

    /*
    fibonacci(n - 1) + fibonacci(n - 2)
    - это очень неправильно.
    Проблема в том, что он вызывает фибоначчи не 50 раз, а гораздо больше.
    Сначала он вызывает фибоначчи(49)+фибоначчи(48),
    затем фибоначчи(48)+фибоначчи(47) и фибоначчи(47)+фибоначчи(46)
    С каждым разом он становился все хуже по Фибоначчи(n),
    поэтому сложность экспоненциальна.
     */
}
