package chapter3;

public class P14_Generics {
    // Обобщения
    // Обобщения или generics (обобщенные типы и методы) позволяют нам уйти от жесткого определения используемых типов.

    public static void main(String[] args) {
        Account acc = new Account(2334, 5000); // id - число
        int acc1Id = (int)acc.getId();
        System.out.println(acc1Id);

        AccountObjectID accountObjectID1 = new AccountObjectID(1234, 10000);        // id - число
        System.out.println(accountObjectID1.getId());
        AccountObjectID accountObjectID2 = new AccountObjectID("sid5523", 5000);    // id - строка
        System.out.println(accountObjectID2.getId());

        // Однако тогда можно столкнуться с проблемой безопасности типов. Например, в следующем случае ошибка:
        AccountObjectID accObj = new AccountObjectID("2345", 5000);
        /// java.lang.ClassCastException
        /// class java.lang.String cannot be cast to class java.lang.Integer
        /// int accObjID = (int)accObj.getId();
        /// System.out.println(accObjID);
        System.out.println(accObj.toString());

        //  переменной даннного класса и создании объекта после имени класса в угловых скобках нужно указать,
        //  какой именно тип будет использоваться вместо универсального параметра
        AccountGeneric<String> accountGeneric1 = new AccountGeneric<String>("2345", 5000);
        String accGenId1 = accountGeneric1.getId();
        System.out.println(accGenId1);

        // При этом надо учитывать, что они работают только с объектами, но не работают с примитивными типами.
        // То есть можно написать Account<Integer>, но не можно использовать тип int или double, например, Account<int>.
        AccountGeneric<Integer> accountGeneric2 = new AccountGeneric<Integer>(2345, 5000);
        Integer accGenId2 = accountGeneric2.getId();
        System.out.println(accGenId2);

        System.out.println("---------------------");

        // --- Обобщенные интерфейсы ---

        // При реализации интерфейса - конкретный тип
        Accountable<String> accImpTypeInterface1= new AccountImp("1234rwr", 5000);
        AccountImp accImpTypeClass1 = new AccountImp("1235", 4300);
        System.out.println(accImpTypeInterface1.getId());
        System.out.println(accImpTypeClass1.getId());



        // Параметр остаестя в классе
        // Explicit type argument String can be replaced with <>
        Accountable<String> accImpTypeClass2 = new AccountImpGeneric<String>("2373", 4300);
        Accountable<String> accImpTypeInterface2 = new AccountImpGeneric<>("2373rwr", 5000);

        // Raw use of parameterized class 'AccountImpGeneric'
                // * A raw type is a name for a generic interface or class without its type argument:
        // Unchecked assignment: 'chapter3.AccountImpGeneric' to 'chapter3.AccountImpGeneric<java.lang.String>'
        // Unchecked call to 'AccountImpGeneric(T, int)' as a member of raw type 'chapter3.AccountImpGeneric'
        AccountImpGeneric<String> accountImpGeneric = new AccountImpGeneric("23345", 7000);

        // реализованный классб но с другим параметром
        AccountImpGeneric<Integer> accountImpGeneric2 = new AccountImpGeneric<>(3345,9000);

        System.out.println(accImpTypeInterface2.getId());
        System.out.println(accImpTypeClass2.getId());
        System.out.println(accountImpGeneric.getId());
        System.out.println(accountImpGeneric2.getId());

        System.out.println("---------------------");

        // --- Обобщенные методы ---
        PrinterGeneric printer = new PrinterGeneric();
        String[] people = {"Tom", "Alice", "Sam", "Kate", "Bob", "Helen"};
        Integer[] numbers = {23, 4, 5, 2, 13, 456, 4};
        printer.print(people);
        printer.print(numbers);

        System.out.println("---------------------");


        // аналогичный синтаксис
        // Explicit type arguments can be inferred
        printer.<String>print(people);
        printer.<Integer>print(numbers);

        System.out.println("---------------------");
        System.out.println(("--- Использование нескольких универсальных параметров ---"));
        // --- Использование нескольких универсальных параметров ---

        AccountMoreGenerics<String,Integer> accountMoreGenerics = new AccountMoreGenerics<>("First", 2);
        System.out.println(accountMoreGenerics.getId() + ", " +  accountMoreGenerics.getSum());

        AccountMoreGenerics<String, Double> accountMoreGenerics2 = new AccountMoreGenerics<String, Double>("354", 5000.87);
        String id = accountMoreGenerics2.getId();
        Double sum = accountMoreGenerics2.getSum();
        System.out.printf("Id: %s  Sum: %f \n", id, sum);

        // --- Обобщенные конструкторы ---
        System.out.println(("--- Использование обобщенных конструкторов ---"));

        AccountGenericConstructor accountGenericConstructor = new AccountGenericConstructor("cid2373", 5000);
        AccountGenericConstructor accountGenericConstructor2 = new AccountGenericConstructor(53757, 4000);
        System.out.println(accountGenericConstructor.getId());
        System.out.println(accountGenericConstructor2.getId());




    }

}

class Account{

    private int id;
    private int sum;

    Account(int id, int sum){
        this.id = id;
        this.sum = sum;
    }

    public int getId() { return id; }
    public int getSum() { return sum; }
    public void setSum(int sum) { this.sum = sum; }
}

//  нередко для идентификатора используются и строковые значения или в качестве типа id использовать какой-то класс.
//  на первый взгляд можно решить данную проблему следующим образом: задать id как поле типа Object,
//  который является универсальным и базовым суперклассом для всех остальных типов:

class AccountObjectID{

    private Object id;
    private int sum;

    AccountObjectID(Object id, int sum){
        this.id = id;
        this.sum = sum;
    }

    public Object getId() { return id; }
    public int getSum() { return sum; }
    public void setSum(int sum) { this.sum = sum; }
}

//Обобщения позволяют не указывать конкретный тип, который будет использоваться. Поэтому определим класс Account как обобщенный
class AccountGeneric<T>{

    //Параметр T в угловых скобках называется универсальным параметром, так как вместо него можно подставить любой тип
    private T id;
    private int sum;

    AccountGeneric(T id, int sum){
        this.id = id;
        this.sum = sum;
    }

    public T getId() { return id; }
    public int getSum() { return sum; }
    public void setSum(int sum) { this.sum = sum; }
}

interface Accountable<T>{
    T getId();
    int getSum();
    void setSum(int sum);
}

// При реализации подобного интерфейса есть две стратегии.
// В данном случае реализована первая стратегия,
// когда при реализации для универсального параметра интерфейса задается конкретный тип (например, String).
// Тогда класс, реализующий интерфейс, жестко привязан к этому типу.

class AccountImp implements Accountable<String>{

    private String id;
    private int sum;

    AccountImp(String id, int sum){
        this.id = id;
        this.sum = sum;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int getSum() {
        return sum;
    }

    @Override
    public void setSum(int sum) {
        this.sum = sum;
    }
}

// Вторая стратегия представляет определение обобщенного класса, который также использует тот же универсальный параметр:
class AccountImpGeneric<T> implements Accountable<T>{

    private T id;
    private int sum;

    AccountImpGeneric(T id, int sum){
        this.id = id;
        this.sum = sum;
    }

    public T getId() { return id; }
    public int getSum() { return sum; }
    public void setSum(int sum) { this.sum = sum; }
}

class PrinterGeneric{

    // Особенностью обобщенного метода является использование универсального параметра
    // в объявлении метода после всех модификаторов и перед типом возвращаемого значения.
    public <T> void print(T[] items){
        for(T item: items){
            System.out.println(item);
        }
    }
}

class AccountMoreGenerics<T, S>{

    private T id;
    private S sum;

    AccountMoreGenerics(T id, S sum){
        this.id = id;
        this.sum = sum;
    }

    public T getId() { return id; }
    public S getSum() { return sum; }
    public void setSum(S sum) { this.sum = sum; }

}

class AccountGenericConstructor{

    private String id;
    private int sum;

    // В данном случае конструктор принимает параметр id, который представляет тип T.
    // В конструкторе его значение превращается в строку и сохраняется в локальную переменную.
    <T>AccountGenericConstructor(T id, int sum){
        this.id = id.toString();
        this.sum = sum;
    }

    public String getId() { return id; }
    public int getSum() { return sum; }
    public void setSum(int sum) { this.sum = sum; }
}

