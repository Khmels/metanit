package chapter3.inheritanceExample;

public class Animal {

    String brain;
    String heart;

    public Animal() {
        System.out.println("Отработал конструктор Animal");
    }

    public Animal(String brain, String heart) {
        this.brain = brain;
        this.heart = heart;
    }

}

