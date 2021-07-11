package chapter3;

public class P5_ObjectsAsParams {
    public static void main(String[] args) {
        // при передаче объектов в качестве значения
        // передается копия ссылки на область в памяти, где расположен этот объект.

        PersonParams kate = new PersonParams("Kate");
        System.out.println(kate.getName());     // Kate
        changeName(kate);
        System.out.println(kate.getName());     // Alice

        PersonParams willow = new PersonParams("Willow");
        changePerson(willow);
        System.out.println(willow.getName());   // willow - изменения не произошло
                                                // willow хранит ссылку на старый объект
    }

    /*
    в метод будет передаваться копия ссылки на область памяти, в которой находится объект PersonParams,
    переменная kate и параметр p метода changeName будут указывать на один и тот же объект в памяти.
    Поэтому после выполнения метода у объекта kate, который передается в метод, будет изменено имя с "Kate" на "Alice".
     */

    static void changeName(PersonParams p){
        p.setName("Alice");
    }

    // другой случай
    static void changePerson(PersonParams p){
        p = new PersonParams("Alice");    // p указывает на новый объект
        p.setName("Ann");                       // изменяется новый объект
    }
}

class PersonParams{

    private String name;

    PersonParams(String name){
        this.name = name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){

        return this.name;
    }
}