package chapter3_OOP;

public class P3_3_AccessModifiers {
    public static void main(String[] args) {

        Person2 kate = new Person2("Kate", 32, "Baker Street", "+12334567");
        kate.displayName();     // норм, метод public
        kate.displayAge();      // норм, метод имеет модификатор по умолчанию
        kate.displayPhone();    // норм, метод protected
        //kate.displayAddress();  /// ! Ошибка, метод private

        System.out.println(kate.name);      // норм, модификатор по умолчанию
        System.out.println(kate.address);   // норм, модификатор public
        System.out.println(kate.age);       // норм, модификатор protected
        //System.out.println(kate.phone);   /// ! Ошибка, модификатор private

        System.out.println("---------------------");

        PersonEncapsulation alonso = new PersonEncapsulation("Alonso", 30);
        alonso.getName();
        alonso.getAge();

        System.out.println("Name: " +  alonso.getName() + "Age: " + alonso.getAge());
        alonso.setAge(31);
        System.out.println(alonso.getAge());      // 33
        alonso.setAge(123450);
        System.out.println(alonso.getAge());      // 33

}
}

//невохможно назвать Person, тк в пакете уже присутствует
class Person2 {

    String name;
    protected int age;
    public String address;
    private String phone;

    public Person2(String name, int age, String address, String phone) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.phone = phone;
    }

    public void displayName() {
        System.out.printf("Name: %s \n", name);
    }

    void displayAge() {
        System.out.printf("Age: %d \n", age);
    }

    private void displayAddress() {
        System.out.printf("Address: %s \n", address);
    }

    protected void displayPhone() {
        System.out.printf("Phone: %s \n", phone);
    }
}

/*
    public:     публичный, общедоступный класс или член класса.
                Поля и методы, объявленные с модификатором public, видны другим классам из текущего пакета и из внешних пакетов.

    private:    закрытый класс или член класса, противоположность модификатору public.
                Закрытый класс или член класса доступен только из кода в том же классе.

    protected:  такой класс или член класса доступен из любого места в текущем классе или пакете или в производных классах,
                даже если они находятся в других пакетах

    default:    Отсутствие модификатора у поля или метода класса предполагает применение к нему модификатора по умолчанию.
                Такие поля или методы видны всем классам в текущем пакете.
 */

//--- Инкапсуляция ---
// Использование различных модификаторов гарантирует, что данные не будут искажены или изменены не надлежащим образом.
// Подобное сокрытие данных внутри некоторой области видимости называется инкапсуляцией.

class PersonEncapsulation{

    private String name;
    private int age;

    public PersonEncapsulation(String name, int age){
        this.name = name;
        this.age = age;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getAge(){
        return this.age;
    }
    public void setAge(int age){
        if(age > 0 && age < 110)
            this.age = age;
    }

    //  Методы setName, setAge и наподобие еще называют мьютейтерами (mutator), так как они изменяют значения поля
    // Методы getName, getAge и наподобие называют аксессерами (accessor), так как с их помощью мы получаем значение поля.
}