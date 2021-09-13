package chapter04_Exceptions;

public class P4_2_ExceptionClasses {
    public static void main(String[] args) {
        try{
            System.out.println(divisionByZero(12,0));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        // --- методы Exception ---
        // все классы исключений наследуются от класса Exception, то все они наследуют ряд его методов.
        // наиболее важные:

        //getMessage()      возвращает сообщение об исключении
        //getStackTrace()   возвращает массив, содержащий трассировку стека исключения
        //printStackTrace() отображает трассировку стека

        System.out.println(division(1,1));
        System.out.println("---------------------");
        try {
            System.out.println(division(1,0));
        }
        catch (Exception e){
            System.out.println("e.getClass(): " + e.getClass());
            System.out.println("e.getMessage(): " + e.getMessage());
            System.out.println("e.getStackTrace(): " + e.getStackTrace());
            e.printStackTrace();
        }
    }

    public static double division(int a, int b){
        double res = a/b;
        return res;
    }

    public static double divisionByZero(int a, int b) throws Exception {
        double res;
        if(b != 0) {
            return res = a / b;
        }
        else {
            throw new Exception("Второе число не должно быть 0");
        }
    }



}

// Базовым классом для всех исключений является класс Throwable
// От него уже наследуются два класса: Error и Exception

// Класс Error описывает внутренние ошибки в исполняющей среде Java.
// Программист имеет очень ограниченные возможности для обработки подобных ошибок.
// IDEA может выдать Error
// Error:(12, 41) java: ')' expected

    /*
    Собственно исключения наследуются от класса Exception.
    Среди этих исключений следует выделить класс RuntimeException.

    RuntimeException является базовым классом для группы непроверяемых исключений (unchecked exceptions) -
    компилятор не проверяет факт обработки таких исключений и
    их можно не указывать вместе с оператором throws в объявлении метода.

    Такие исключения являются следствием ошибок разработчика,
    например, неверное преобразование типов или выход за пределы массива.
     */

/* Некоторые из классов непроверяемых исключений:
    ArithmeticException:        исключение, возникающее при делении на ноль
    IndexOutOfBoundException:   индекс вне границ массива
    IllegalArgumentException:   использование неверного аргумента при вызове метода
    NullPointerException:       использование пустой ссылки
    NumberFormatException:      ошибка преобразования строки в число
*/

    /*
    Все остальные классы, образованные от класса Exception, называются проверяемыми исключениями (checked exceptions)
     */

/*  Некоторые из классов проверяемых исключений:
    CloneNotSupportedException: класс, для объекта которого вызывается клонирование, не реализует интерфейс Cloneable
    InterruptedException: поток прерван другим потоком
    ClassNotFoundException: невозможно найти класс
    ClassCastException: неправильное приведение типов
 */

// Подобные исключения обрабатываются с помощью конструкции try..catch.
// Либо можно передать обработку методу, который будет вызывать данный метод, указав исключения после оператора throws:

class PeopleClone implements Cloneable {
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class AnimalClone implements Cloneable {
    @Override
    protected Object clone()  {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return new AnimalClone();
    }
}

/*
The way your code works is pretty close to the "canonical" way to write it.
I'd throw an AssertionError within the catch, though. It signals that that line should never be reached.

catch (CloneNotSupportedException e) {
    throw new AssertionError(e);
}
 */

/*
Do you absolutely have to use clone? Most people agree that Java's clone is broken.
Josh Bloch on Design - Copy Constructor versus Cloning

"If you've read the item about cloning in my book, especially if you read between the lines,
you will know that I think clone is deeply broken. [...] It's a shame that Cloneable is broken, but it happens."

You may read more discussion on the topic in his book Effective Java 2nd Edition, Item 11: Override clone judiciously.
He recommends instead to use a copy constructor or copy factory.

He went on to write pages of pages on how, if you feel you must, you should implement clone. But he closed with this:

"Is all this complexities really necessary? Rarely.
If you extend a class that implements Cloneable, you have little choice but to implement a well-behaved clone method.
Otherwise, you are better off providing alternative means of object copying, or simply not providing the capability"

The emphasis was his, not mine.

Since you made it clear that you have little choice but to implement clone,
here's what you can do in this case: make sure that MyObject extends java.lang.Object implements java.lang.Cloneable.

If that's the case, then you can guarantee that you will NEVER catch a CloneNotSupportedException.
Throwing AssertionError as some have suggested seems reasonable,
but you can also add a comment that explains why the catch block will never be entered in this particular case.
 */