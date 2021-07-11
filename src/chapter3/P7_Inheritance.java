package chapter3;

public class P7_Inheritance {
    public static void main(String[] args) {

        PersonInheritance tom = new PersonInheritance("Tom");
        tom.display();

        // не соответсвует сайту. Просит создать конструктор
        /*
        Employee sam = new Employee("Sam");
        sam.display();
         */
        Employee sam = new Employee();
        sam.getName();
        sam.display();
    }
}

class PersonInheritance {

    private String name;
    public String getName(){ return name; }

    //2. wasn't on site  - необходимо создать конструктор, чтоб можно было наследоваться
    public PersonInheritance() {
    }

    public PersonInheritance(String name){

        this.name=name;
    }

    public void display(){

        System.out.println("Name: " + name);
    }
}

/// 1. There is no default constructor available in 'chapter3.PersonIn'
// Производный класс имеет доступ ко всем методам и полям базового класса
// (даже если базовый класс находится в другом пакете) кроме тех, которые определены с модификатором private
class Employee extends PersonInheritance{

}

class Employee2 extends PersonInheritance{
    private String company;

    public Employee2(String name, String company) {

        super(name);
        this.company=company;
    }
    public void work(){
        System.out.printf("%s works in %s \n", getName(), company);
    }
}