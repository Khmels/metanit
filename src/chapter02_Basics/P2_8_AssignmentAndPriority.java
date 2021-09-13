package chapter02_Basics;

public class P2_8_AssignmentAndPriority {
    public static void main(String[] args) {

        int a = 5;
        a += 10;    // 15
        a -= 3;     // 12
        a *= 2;     // 24
        a /= 3;     // 8
        a %= 3;     // 2
        a <<= 4;    // 32
        a >>= 2;    // 8
        System.out.println(a);  // 8

        boolean cond = true;
        cond &= true;   //true  - true and true
        cond &= false;  //false - true and false
        cond &= true;   //false  - false and true

        cond |= false;  //false - false or false
        cond |= true;   //true  - false or true

        cond ^= false;  //true  - true vs false = true
        cond ^= true;   //false - true vs true = false

        System.out.println(cond);

        boolean cond2 = false;
        cond2 &= false;     //false - false and false
        cond2 &= true;      //false - false and true

        cond2 |= false;   //false - false or false
        cond2 |= true;    //true  - false or true

        cond2 ^= false;   //true  - true vs false = true
        cond2 ^= true;    //false - true vs true = false

        System.out.println(cond2);

    }
}

/*
    c=b;    просто присваивается одно значение другому
    c+=b;   переменной c присваивается результат сложения c и b
    c-=b;   переменной c присваивается результат вычитания b из c
    c*=b;   переменной c присваивается результат произведения c и b
    c/=b;   переменной c присваивается результат деления c на b
    c%=b;   переменной c присваивается остаток от деления c на b
    c&=b;   переменной c присваивается значение c&b
    c|=b;   переменной c присваивается значение c|b
    c^=b;   переменной c присваивается значение c^b
    c<<=b;  переменной c присваивается значение c<<b
    c>>=b;  переменной c присваивается значение c>>b
    c>>>=b; переменной c присваивается значение c>>>b
 */

/*
    expr++ expr--
    ++expr --expr +expr -expr ~ !
    * / %
    + -
    << >> >>>
    < > <= >= instanceof
    == !=
    &
    ^
    |
    &&
    ||
    ? : (тернарный оператор)
    =, +=, -=, *=, /=, %=, &=, ^=, |=, <<=, >>=, >>>= (операторы присваивания)

    Чем выше оператор в этой таблице, тем больше его приоритет. При этом скобки повышают приоритет операции, используемой в выражении.
 */