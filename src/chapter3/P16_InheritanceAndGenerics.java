package chapter3;

public class P16_InheritanceAndGenerics {
    // Обобщенные классы могут участвовать в иерархии наследования:
    // могут наследоваться от других, либо выполнять роль базовых классов.
    public static void main(String[] args) {
        DepositAccount<Integer> dAccount1 = new DepositAccount<>(20);
        System.out.println(dAccount1.getId());

        DepositAccount<String> dAccount2 = new DepositAccount<>("12345");
        System.out.println(dAccount2.getId());

        System.out.println("---------------------");

        DepositAccountOwnGeneric<Integer, String> dAccount3 = new DepositAccountOwnGeneric<>(20, "Tom");
        System.out.println(dAccount1.getId() + " : " + dAccount3.getName());

        DepositAccountOwnGeneric<String, Integer> dAccount4 = new DepositAccountOwnGeneric<>("12345", 23456);
        System.out.println(dAccount2.getId() + " : " + dAccount4.getName());

        // Можно привести объект
        // DepositAccount<Integer>  к Account<Integer>  или
        // DepositAccount<String>   к Account<String>:

        System.out.println("---------------------");

        DepositAccountConvert<Integer> depAccount = new DepositAccountConvert<>(10);
        // Casting 'depAccount' to 'AccountConvert<Integer>' is redundant
        AccountConvert<Integer> account = (AccountConvert<Integer>)depAccount;
        System.out.println(account.getId());

        DepositAccountConvert<String> depAccountStr = new DepositAccountConvert<>("101s");
        AccountConvert<String> accountConvert = (AccountConvert<String>)depAccountStr;
        System.out.println(accountConvert.getId());

        /// Но сделать то же самое с разнотипными объектами невозможно. Например, следующий код не будет работать:

        //// Inconvertible types;
        //// cannot cast 'chapter3.DepositAccountConvert<java.lang.Integer>'
        ////          to 'chapter3.AccountConvert<java.lang.String>'
////        DepositAccountConvert<Integer> depAccountFail = new DepositAccountConvert(10);
////        AccountConvert<String> accountConvert1 = (AccountConvert<String>)depAccountFail;


    }
}

    // --- Базовый обобщенный класс ---------------

class AccountBase<T>
{
    private T id;
    T getId(){return id;}

    AccountBase(T id)
    {
        this.id = id;
    }
}

//В конструкторе DepositAccount идет обращение к конструктору базового класса, в который передаются данные о типе.
class DepositAccount<T> extends AccountBase<T>{

    DepositAccount(T id){
        super(id);
    }
}

    // -- класс-наследник может добавлять и использовать какие-то свои параметры типов:
class DepositAccountOwnGeneric<T, S> extends AccountBase<T>{
    private S name;

    DepositAccountOwnGeneric(T id){
        super(id);
    }

    public S getName() {
        return name;
    }

    public DepositAccountOwnGeneric(T id, S name) {
        super(id);
        this.name = name;
    }
}

    // -- класс-наследник вообще может не быть обобщенным:

    // при наследовании явным образом указывается тип,
    // который будет использоваться конструкциями базового класса (Integer).
    // Затем в конструктор базового класса передается значение именно этого типа - в данном случае число 5.

class DepositAccountWithout extends AccountBase<Integer>{

    DepositAccountWithout(){
        super(5);
    }
}

    // --- Обобщенный класс-наследник ---------------

class AccountWithout
{
    private String name;
    String getName(){return name;}
    AccountWithout(String name)
    {
        this.name=name;
    }
}

//В этом случае использование конструкций базового класса в наследнике происходит как обычно.

class DepositAccountGeneric<T> extends AccountWithout{

    private T id;

    public T getId() {
        return id;
    }

    DepositAccountGeneric(String name, T id){
        super(name);
        this.id = id;
    }
}

// --- Преобразование обобщенных типов ---------------
// Объект одного обобщенного типа можно привести к другому типу, если они используют один и тот же тип

class AccountConvert<T>
{
    private T id;
    T getId(){return id;}
    AccountConvert(T id)
    {
        this.id = id;
    }
}

class DepositAccountConvert<T> extends AccountConvert<T>{

    DepositAccountConvert(T id){
        super(id);
    }
}
