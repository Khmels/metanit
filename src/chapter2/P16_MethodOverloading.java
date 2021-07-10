package chapter2;

public class P16_MethodOverloading {
    public static void main(String[] args) {

    }

    //перегрузкой методов (method overloading)
    //методы с одним и тем же именем, но с разными типами и/или количеством параметров

    static int sum(int x, int y){
        return x + y;
    }

    static double sum(double x, double y){
        return x + y;
    }

    static int sum(int x, int y, int z){
        return x + y + z;
    }

    /// возвращаемое значение не влияет на перегрузку
    /// 'sum(int, int)' is already defined in class
///    static int sum(int x, int y){
///        return x + y;
///    }
///
///    static double sum(int x, int y){
///        return x + y;
///    }

}
