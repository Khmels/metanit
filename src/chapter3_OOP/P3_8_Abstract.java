package chapter3_OOP;

public abstract class P3_8_Abstract {
    private String name;
    public String getName() { return name; }

    //Но главное отличие состоит в том,
    // что не можем использовать конструктор абстрактного класса для создания его объекта.
    // Например, следующим образом:

    public P3_8_Abstract(String name) {
        this.name = name;
    }

    public P3_8_Abstract(){

    }

    //Производный класс обязан переопределить и реализовать все абстрактные методы,
    // которые имеются в базовом абстрактном классе.
    // Также следует учитывать, что если класс имеет хотя бы один абстрактный метод,
    // то данный класс должен быть определен как абстрактный.

    public abstract void display();

    // add method body OR make abstract
    public void nonAbstract() {

    }
}

class EmployeeAbstract3 extends P3_8_Abstract {

    private String bank;

    public EmployeeAbstract3(String name, String company) {

        super(name);
        this.bank = company;
    }

    @Override
    public void display(){
        System.out.printf("Employee Name: %s \t Bank: %s \n", super.getName(), bank);
    }
}

class ClientAbstract3 extends P3_8_Abstract
{
    private String bank;

    public ClientAbstract3(String name, String company) {

        super(name);
        this.bank = company;
    }

    @Override
    public void display(){
        System.out.printf("Client Name: %s \t Bank: %s \n", super.getName(), bank);
    }
}


