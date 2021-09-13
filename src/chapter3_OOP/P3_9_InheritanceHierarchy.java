package chapter3_OOP;

public class P3_9_InheritanceHierarchy {
    public static void main(String[] args) {

        PersonIH tom = new PersonIH("Tom");
        tom.display();
        PersonIH sam = new EmployeeIH("Sam", "Oracle");
        sam.display();
        PersonIH bob = new ClientIH("Bob", "DeutscheBank", 3000);
        bob.display();

        // Объект подкласса также представляет объект суперкласса
        Object tomOObject = new PersonIH("Tom");
        Object samObject = new EmployeeIH("Sam", "Oracle");
        Object kateObject = new ClientIH("Kate", "DeutscheBank", 2000);

        // Это так называемое восходящее преобразование или upcasting.
        // (от подкласса внизу к суперклассу вверху иерархии)
        // Такое преобразование осуществляется автоматически.
        PersonIH bobPerson = new ClientIH("Bob", "DeutscheBank", 3000);
        PersonIH alicePerson = new EmployeeIH("Alice", "Google");

        // объект Person не всегда является объектом Employee или Client
        // нисходящее преобразование или downcasting от суперкласса к подклассу
        // автоматически не выполняется.

        // преобразование типов
        Object samAuto = new EmployeeIH("Sam", "Oracle");

        // нисходящее преобразование от Object к типу Employee
        EmployeeIH emp = (EmployeeIH)samAuto;
        emp.display();
        System.out.println(emp.getCompany());

        // примеры
        Object kate = new ClientIH("Kate", "DeutscheBank", 2000);
        ((PersonIH)kate).display();

        Object samObj = new EmployeeIH("Sam", "Oracle");
        ((EmployeeIH)samObj).display();


        //переменная типа Object хранит ссылку на объект Client
        Object carol = new ClientIH("Kate", "DeutscheBank", 2000);

///        //не представляет объект типа Employee, ошибка во время выполнения
///        EmployeeIH empDown = (EmployeeIH) carol;
///        empDown.display();
///        // или так
///        ((EmployeeIH)carol).display();

     // --- Оператор instanceof ---
        Object carrol = new ClientIH("Kate", "DeutscheBank", 2000);
        if(carrol instanceof EmployeeIH){

            EmployeeIH employeeCarol = (EmployeeIH) kate;
            employeeCarol.display();
            System.out.println("Employee");
        }
        else if (carrol instanceof ClientIH){

            ClientIH clientCarrol = (ClientIH) kate;
            clientCarrol.display();
        }

        else{

            System.out.println("Conversion is invalid. Person is undefined");
        }

        /////   Java_16
        /*
        Object kate16 = new ClientIH("Kate", "DeutscheBank", 2000);
        /// Patterns in 'instanceof' are not supported at language level '11'

        // Проверяет, представляет ли переменная kate16 класс ClientIH,
        // и если представляет (то есть оператор instanceof возвращает true),
        // то создает переменную clientKate16 типа ClientIH.

        if(kate16 instanceof ClientIH clientKate16){

            clientKate16.display();
        }
        else{

            System.out.println("Conversion is invalid");
        }
         */
    }
}

// класс человека
class PersonIH {

    private String name;

    public String getName() { return name; }

    public PersonIH(String name){

        this.name=name;
    }

    public void display(){

        System.out.printf("Person %s \n", name);
    }
}
// служащий некоторой компании
class EmployeeIH extends PersonIH{

    private String company;

    public EmployeeIH(String name, String company) {

        super(name);
        this.company = company;
    }
    public String getCompany(){ return company; }

    public void display(){

        System.out.printf("Employee %s works in %s \n", super.getName(), company);
    }
}
// класс клиента банка
class ClientIH extends PersonIH{

    private int sum; // Переменная для хранения суммы на счете
    private String bank;

    public ClientIH(String name, String bank, int sum) {

        super(name);
        this.bank=bank;
        this.sum=sum;
    }

    public void display(){

        System.out.printf("Client %s has account in %s \n", super.getName(), bank);
    }

    public String getBank(){ return bank; }
    public int getSum(){ return sum; }
}