package chapter3;

import java.util.Objects;

public class P18_Records {
    /*
    * Начиная с версии Java 16 в язык была добавлена новая функциональность - Records ("записи").
    * Records представляют классы, которые предназначены для создания контейнеров неизменяемых данных.
    * Кроме того, records позволяют упростить разработку, сократив объем кода.
    * */

    public static void main (String args[]){

        // Их значения устанавливаются в конструкторе. Больше никак их установить мы не можем.
        // Таким образом, после создания объекта Person они будут хранить неизменяемые данные.
        PersonRec tom = new PersonRec("Tom", 31);
        System.out.println(tom.toString());
        System.out.println(tom.name());
        System.out.println(tom.getName());

        /*  PersonRecord bob = new PersonRecord("Bob", 32);
        System.out.println(bob.age());
        System.out.println(bob.name());
        System.out.println(bob.equals(tom));
        System.out.println(bob.hashCode());
        System.out.println(bob.toString());

        PersonRecord tomas = new PersonRecord("Tom", 31);
        System.out.println(bob.equals(tomas));  // false
        System.out.println(tom.equals(tomas));  // true     */
    }

}

/*
record название (поле1, поле2,...полеN){
    // тело record
}
 */

class PersonRec {
    private final String name;
    private final int age;

    PersonRec(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // для получения значений name и age предусмотрены одноименные методы:
    String name() { return name; }
    int age() { return age; }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Person)) return false;
        Person other = (Person) o;
        return other.name == name && other.age == age;
    }

    public int hashCode() {
        return Objects.hash(name, age);
    }

    public String toString() {
        return String.format("Person[name=%s, age=%d]", name, age);
    }
}

    // Дальше идет список полей записи. То есть в данном случае определяется два поля - name и age.
    // Причем по умолчанию все они будут приватными и иметь модификатор final.
    // Также будет создаваться конструктор с двумя параметрами name и age.

    // А каждого поля автоматически будет создаваться одноименный общедоступный метод для получения значения это поля.
    // Например, для поля name создается метод name(), который возвращает значение поля name.

    // И также автоматически будут создаваться методы equals, hashCode и toString.
    // Вообщем, данная record будет полностью аналогична вышеопределенному классу,
    // но при этом содержит гораздо меньше кода.

// ***************************************************************************
/*
record PersonRecord(String name, int age) {

}
*/
    // фактически создание конструктора:
    // Этот конструктор называется каноническим.
    // Он принимает параметры, которые называются также как и поля record,
    // и передает полям значения соответствующих параметров.

// Person(String name, int age) {
//    this.name = name;
//    this.age = age;
//}

    /*Тем не менее при необходимости можно изменить логику конструктора - переопределить конструктор.
    Например, что если при создании объекта будет передан невалидный возраст?
    * */

/*
record Person(String name, int age) {

    Person{

        if(age<1 || age > 110){
            age = 18;
        }
    }
}

        // по сути:
Person(String name, int age) {
    if(age<1 || age > 110){
        age = 18;
    }
    this.name = name;
    this.age = age;
}

        // Можно полностью переопределить канонический конструктор:
record Person(String name, int age) {

    Person(String name, int age){

        if(age < 0 || age > 120) age = 18;

        this.name = name;
        this.age = age;
    }
}
        // Можно определять какие-то другие конструкторы, но все они должны вызывать канонический конструктор:
        // конструктор вызывает канонический конструктор,
        // передавая ему значения для полей name и age: this(firstName + " " + lastName, age).
record Person(String name, int age) {

    Person(String firstName, String lastName, int age){

        this(firstName + " " + lastName, age);  // Person[name=Tom Smith, age=36]

    }
}

 */


// --- Переопределение методов ---
/*
* Также можно переопределить методы, которые имеет record по умолчанию.
* А это методы equals(), hashCode() и toString() и методы,
* которые называются также, как и поля записи, и которые возвращают значения соответствующих полей.
*/

// Например, перепопределим для записи Person методы toString() и name():

/*
record Person(String name, int age) {

    public String name() { return "Mister " + name; }

    public String toString() {
        return String.format("Person %s, Age: %d", name, age);
    }
}
*/

// --- Ограничения records ---
/*
* Невозможно наследовать запись record от других классов.
* Также нельзя наследовать классы от records.
* Однако классы record могут реализовать интерфейсы.
* Кроме того, классы record не могут быть абстрактными.
* */

// В record нельзя явным образом определять нестатические поля и инициализаторы.
// Но можно определять статические переменные и инициализаторы, также как статические и нестатические методы:

/*
record Person(String name, int age){

    static int minAge;
    static{
        minAge = 18;
        System.out.println("Static initializer");
    }
}
*/


