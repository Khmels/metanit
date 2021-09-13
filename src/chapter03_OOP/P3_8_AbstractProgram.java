package chapter03_OOP;

public class P3_8_AbstractProgram {
    public static void main(String[] args) {
        /// 'P8_Abstract' is abstract; cannot be instantiated
        /// P8_Abstract p8Abstract = new P8_Abstract();

        EmployeeAbstract3 sam = new EmployeeAbstract3("Sam", "Leman Brothers");
        sam.display();
        ClientAbstract3 bob = new ClientAbstract3("Bob", "Leman Brothers");
        bob.display();

    }
}

