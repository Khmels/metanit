package chapter3;

public class P6_NestedAndInnerClasses {
    public static void main(String[] args) {

        PersonNested tom = new PersonNested("Tom", "qwerty");
        tom.displayPerson();
        tom.account.displayAccount();

        System.out.println("---------------------");

        PersonNested1 bob = new PersonNested1("BOB", "qwerty1");
        bob.account.displayId();
        bob.displayPerson();
        System.out.println("New account will have id:");
        bob.newAccount().displayId();

        System.out.println("---------------------");
        /// 'chapter3.PersonNested' is not an enclosing class
        /// PersonNested.Account account = new PersonNested.Account("qwerty");

        // так возможно, через new - нет
        PersonNested1.Account account = bob.newAccount();
        account.displayId();

        System.out.println("---------------------");

        PersonNested2 john = new PersonNested2("John", "qwerty2");
        john.setAccount("1234567");


        System.out.println("---------------------");

        Math.Factorial fact = Math.getFactorial(6);
        System.out.printf("Факториал числа %d равен %d \n", fact.getKey(), fact.getResult());

    }
}

class PersonNested{

    private String name;
    Account account;

    PersonNested(String name, String password){
        this.name = name;
        account = new Account(password);
    }
    public void displayPerson(){
        // внешний класс имеет доступ ко всем членам внутреннего класса, в тч к полям и методам с модификатором private.
        System.out.printf("Person \t Name: %s \t Password: %s \n", name, account.password);
    }

    // inner class - внутренний класс. Объекты могут быть созданы только внутри внешнего класса.
    public class Account{
        private String password;

        Account(String pass){
            this.password = pass;
        }

        void displayAccount(){
            // Ссылку на объект внешнего класса из внутреннего класса можно получить с помощью выражения Внешний_класс.this,
            // имеет доступ ко всем полям внешнего класса, в том числе закрытым с помощью модификатора private
            System.out.printf("Account Login: %s \t Password: %s \n", PersonNested.this.name, password);
        }
    }
}

class PersonNested1{

    private String name;
    private int id;
    Account account;

    PersonNested1(String name, String password){
        this.name = name;
        account = new Account(password);
    }
    public void displayPerson(){
        // внешний класс имеет доступ ко всем членам внутреннего класса, в тч к полям и методам с модификатором private.
        System.out.printf("Person \t Name: %s \t Password: %s \n", name, account.password);
    }

    // так возможно, но мало смысла
    public Account newAccount(){
        Account acc = new Account("12345");
        return acc;
    }


    // inner class - внутренний класс. Объекты могут быть созданы только внутри внешнего класса.
    public class Account{
        private String password;

        Account(String pass){
            this.password = pass;
        }

        void displayAccount(){
            // Ссылку на объект внешнего класса из внутреннего класса можно получить с помощью выражения Внешний_класс.this,
            System.out.printf("Account Login: %s \t Password: %s \n", PersonNested1.this.name, password);
        }

        // имеет доступ ко всем полям внешнего класса, в том числе закрытым с помощью модификатора private
        void displayId(){
            id++;
            System.out.println("id= "+id);
        }
    }
}

class PersonNested2{

    private String name;
    Account account;

    PersonNested2(String name, String password){
        this.name = name;
        account = new Account(password);
    }
    public void displayPerson(){
        // внешний класс имеет доступ ко всем членам внутреннего класса, в тч к полям и методам с модификатором private.
        System.out.printf("Person \t Name: %s \t Password: %s \n", name, account.password);
    }

    // можно объявить внутри любого контекста, в том числе внутри метода и даже в цикле:
    public void setAccount (String password){

        // названия могут повторяться в отличие от nested
        class Account{

            void display(){
                System.out.printf("Account Login: %s \t Password: %s \n", name, password);
            }
        }
        Account account = new Account();
        account.display();
    }

    // inner class - внутренний класс. Объекты могут быть созданы только внутри внешнего класса.

    public class Account{
        private String password;

        Account(String pass){
            this.password = pass;
        }

        void displayAccount(){
            // Ссылку на объект внешнего класса из внутреннего класса можно получить с помощью выражения Внешний_класс.this,
            System.out.printf("Account Login: %s \t Password: %s \n", PersonNested2.this.name, password);
        }
    }
}

// --- Статические вложенные классы ---
// class Math не может быть, потому что есть такой в P4_StaticMemberAndModifier.java
// class Math renamed to MathStat

class Math{
    // Статические вложенные классы позволяют скрыть некоторую комплексную информацию внутри внешнего класса
    // вложенный класс для хранения данных о вычислении факториала.
    // It behaves like a static member of the outer class.
    // this class doesn’t have any control over instance variables and methods of the outer object
    public static class Factorial{

        private int result;
        private int key;

        public Factorial(int number, int x){

            result=number;
            key = x;
        }

        public int getResult(){
            return result;
        }

        public int getKey(){
            return key;
        }
    }

    public static Factorial getFactorial(int x){

        int result=1;
        for (int i = 1; i <= x; i++){

            result *= i;
        }
        return new Factorial(result, x);
    }
}