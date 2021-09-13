package chapter3_OOP;

public class P3_15_GenericLimitation {
    /*
    универсальный параметр у обобщений по умолчанию может представлять любой тип.
    Однако иногда необходимо, чтобы параметр соответствовал только некоторому ограниченному набору типов.
    В этом случае применяются ограничения, которые позволяют указать базовый класс,
    которому должен соответствовать параметр.

    Для установки ограничения после универсального параметра ставится слово extends,
    после которого указывается базовый класс ограничения:
     */

    public static void main(String[] args) {
        AccountLimit acc1 = new AccountLimit("1876", 4500);
        AccountLimit acc2 = new AccountLimit("3476", 1500);

        Transaction<AccountLimit> tran1 = new Transaction<AccountLimit>(acc1,acc2, 4000);
        tran1.execute();
        System.out.println("---------------------");

        tran1 = new Transaction<AccountLimit>(acc1,acc2, 4000);
        tran1.execute();
        System.out.println("---------------------");

        // --- Обобщенные типы в качестве ограничений ---
        // В качестве ограничений могут выступать и другие обобщения, которые сами могут иметь ограничения:


        AccountLimitGen<String> accountLimitGen1 = new AccountLimitGen<String>("2876", 4500);
        AccountLimitGen<String> accountLimitGen2 = new AccountLimitGen<String>("4476", 1500);

        TransactionLimit<AccountLimitGen<String>> tranLimit = new TransactionLimit<AccountLimitGen<String>>(accountLimitGen1, accountLimitGen2,4000);
        tranLimit.execute();
        tranLimit = new TransactionLimit<AccountLimitGen<String>>(accountLimitGen1, accountLimitGen2,4000);
        tranLimit.execute();
        System.out.println("---------------------");


        // --- Интерфейсы в качестве оганичений ---
        AccountLimitInterface accountLimitInterface1 = new AccountLimitInterface("1235rwr", 5000);
        AccountLimitInterface accountLimitInterface2 = new AccountLimitInterface("2373", 4300);
        TransactionLimitByInterface<AccountLimitInterface> transactionLimitByInterface =
                new TransactionLimitByInterface<AccountLimitInterface>(accountLimitInterface1, accountLimitInterface2, 1560);
        tran1.execute();

    }
}

/*
в данном случае для параметра T в Transaction ограничением является класс AccountLimit.
То есть на место параметра T можно передать либо класс Account, либо один из его классов-наследников.
 */

/*
class AccountLimit{ }
class Transaction<T extends AccountLimit>{ }
 */

class Transaction<T extends AccountLimit>{

    private T from;     // с какого счета перевод
    private T to;       // на какой счет перевод
    private int sum;    // сумма перевода

    Transaction(T from, T to, int sum){
        this.from = from;
        this.to = to;
        this.sum = sum;
    }
    public void execute(){

        // компилятор будет распознавать объекты типа T как объекты типа Account.
        // И в этом случае можно вызывать у объектов типа T методы класса Account
        if (from.getSum() > sum)
        {
            from.setSum(from.getSum() - sum);
            to.setSum(to.getSum() + sum);
            System.out.printf("Account %s: %d \nAccount %s: %d \n",
                    from.getId(), from.getSum(),to.getId(), to.getSum());
        }
        else{
            System.out.println("Operation is invalid");
        }
    }
}
class AccountLimit{

    private String id;
    private int sum;

    AccountLimit(String id, int sum){
        this.id = id;
        this.sum = sum;
    }

    public String getId() { return id; }
    public int getSum() { return sum; }
    public void setSum(int sum) { this.sum = sum; }
}

/// не смогли бы использовать методы AccountLimit,
/// если не задать подобного ограничения:
/// Cannot resolve method 'getSum' in 'T'

/*
class TransactionLim<T>{
    private T from;     // с какого счета перевод
    private T to;       // на какой счет перевод
    private int sum;    // сумма перевода

    TransactionLim(T from, T to, int sum){
        this.from = from;
        this.to = to;
        this.sum = sum;
    }
    public void execute(){

        if (from.getSum() > sum)
        {
            from.setSum(from.getSum() - sum);
            to.setSum(to.getSum() + sum);
            System.out.printf("Account %s: %d \nAccount %s: %d \n",
                    from.getId(), from.getSum(),to.getId(), to.getSum());
        }
        else{
            System.out.printf("Operation is invalid");
        }
    }
}
*/

// --- Обобщенные типы в качестве ограничений ---------------------------

class TransactionLimit<T extends AccountLimitGen<String>>{

    private T from;     // с какого счета перевод
    private T to;       // на какой счет перевод
    private int sum;    // сумма перевода

    TransactionLimit(T from, T to, int sum){
        this.from = from;
        this.to = to;
        this.sum = sum;
    }
    public void execute(){

        if (from.getSum() > sum)
        {
            from.setSum(from.getSum() - sum);
            to.setSum(to.getSum() + sum);
            System.out.printf("Account %s: %d \nAccount %s: %d \n",
                    from.getId(), from.getSum(),to.getId(), to.getSum());
        }
        else{
            System.out.printf("Operation is invalid");
        }
    }
}
class AccountLimitGen<T>{

    private T id;
    private int sum;

    AccountLimitGen(T id, int sum){
        this.id = id;
        this.sum = sum;
    }

    public T getId() { return id; }
    public int getSum() { return sum; }
    public void setSum(int sum) { this.sum = sum; }
}

// --- Интерфейсы в качестве ограничений ---------------------------

interface AccountableLimit{
    String getId();
    int getSum();
    void setSum(int sum);
}

class AccountLimitInterface implements AccountableLimit{

    private String id;
    private int sum;

    AccountLimitInterface(String id, int sum){
        this.id = id;
        this.sum = sum;
    }

    @Override public String getId() { return id; }
    @Override public int getSum() { return sum; }
    @Override public void setSum(int sum) { this.sum = sum; }
}

class TransactionLimitByInterface<T extends AccountableLimit>{

    private T from;     // с какого счета перевод
    private T to;       // на какой счет перевод
    private int sum;    // сумма перевода

    TransactionLimitByInterface(T from, T to, int sum){
        this.from = from;
        this.to = to;
        this.sum = sum;
    }
    public void execute(){

        if (from.getSum() > sum)
        {
            from.setSum(from.getSum() - sum);
            to.setSum(to.getSum() + sum);
            System.out.printf("Account %s: %d \nAccount %s: %d \n",
                    from.getId(), from.getSum(),to.getId(), to.getSum());
        }
        else{
            System.out.printf("Operation is invalid");
            System.out.println();
        }
    }
}

// --- Множественные ограничения ---------------------------
class PersonLimit{}
interface AccountableLimited{}

class TransactionMultiLimit <T extends PersonLimit & AccountableLimited>{}
