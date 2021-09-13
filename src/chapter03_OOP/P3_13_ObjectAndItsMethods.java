package chapter03_OOP;

public class P3_13_ObjectAndItsMethods {
    public static void main(String[] args) {
        PersonObject personObject = new PersonObject("ClassPerson");
        // --- toString() ---
        System.out.println(personObject.toString());    // chapter3.PersonObject@2d98a335

        // --- hashCode ---
        System.out.println(personObject.hashCode());    // 664223387

        // --- Получение типа объекта и метод getClass ---
        System.out.println(personObject.getClass());    // class chapter3.PersonObject

        // --- Метод equals ---

        PersonObject tom = new PersonObject("Tom");
        PersonObject bob = new PersonObject("Bob", "Dilan");
        System.out.println(tom.equals(bob));            // false

        // false
        PersonObject tom2 = new PersonObject("Tom");
        System.out.println(tom.equals(tom2));           // false, после переопределения - true

        // после переопределения
        PersonObject bob2 = new PersonObject("Bob", "Johnson");
        System.out.println(bob.equals(bob2));           // false

    }


}

class PersonObject {

    private String name;
    private String surname;

    public PersonObject(String name){

        this.name=name;
    }

    public PersonObject(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        String toString = "Name: " + this.name;
        return toString;
    }

//    @Override
//    public String toString(){
//
//        return "Person " + name;
//    }

    //свой алгоритм определения хэш-кода объекта
    @Override
    public int hashCode(){

        return 10 * name.hashCode() + 12345;
    }

    @Override
    public boolean equals(Object obj){

        // equals принимает в качестве параметра объект любого типа,
        // который затем приводиться к текущему, если они являются объектами одного класса

        // Оператор instanceof позволяет выяснить,
        // является ли переданный в качестве параметра объект объектом определенного класса (класса Person).
        // Если объекты принадлежат к разным классам, то их сравнение не имеет смысла, и возвращается значение false.
        if (!(obj instanceof PersonObject)) return false;

        PersonObject p = (PersonObject)obj;
        if (p.surname != null){
            return this.name.equals(p.name)&&this.surname.equals(p.surname);}
        else
            return this.name.equals(p.name);
    }
}
