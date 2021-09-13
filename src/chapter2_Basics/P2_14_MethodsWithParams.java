package chapter2_Basics;

public class P2_14_MethodsWithParams {
    public static void main(String[] args) {
        int a =1;
        int b=2;
        // значения называют аргументами
        sum(a,b);          //3
        sum(2,3);    //5
        sum(5,a);       //6
        sum(b,10);      //12

        display("Tom", 34);
        displayPretty("Bob", 28);
        displayPretty("Sam", 23);

        System.out.println("---------------------");

        sum(1);
        sum(2,2);
        sum(1,2,3,4);
        sum("Сумма чего-то там: ", 1, 2, 3, 4, 5);
    }

    //--- Метод с параметрами ---
    static void sum(int x, int y){
        int z = x + y;
        System.out.println(z);
    }

    static void display(String name, int age){
        System.out.print(name);
        System.out.print(", ");
        System.out.println(age);
    }

    static void displayPretty(String name, int age){
        System.out.printf("Name: %s, Age: %d", name, age);
        System.out.println();
    }

    //--- Параметры переменной длины ---
    // если неизвестно, сколько будет передано параметров
    // Троеточие  перед названием параметра int ...nums указывает на то,
    // что он будет необязательным и будет представлять массив
    static void sum(int ...nums){
        int result =0;
        for(int n: nums)
            result += n;
        System.out.println(result);
    }

    //если несколько параметров, то необязательный параметр должен указываться в конце:
    static void sum(String message, int ...nums){
        System.out.println(message);
        int result =0;
        for(int x:nums)
            result+=x;
        System.out.println(result);
    }
}
