package chapter3;

public class P8_AbstractProgram {
    public static void main(String[] args) {
        /// 'P8_Abstract' is abstract; cannot be instantiated
        /// P8_Abstract p8Abstract = new P8_Abstract();

        EmployeeAbstract sam = new EmployeeAbstract("Sam", "Leman Brothers");
        sam.display();
        ClientAbstract bob = new ClientAbstract("Bob", "Leman Brothers");
        bob.display();

    }
}

