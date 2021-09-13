package chapter3_OOP.inheritanceExample;

public class Cat extends Animal{

//    String tail;
//    String color;

    String tail = "Изначальное значение tail в классе Cat";
    String color;

    static int catsCount = 37;


    public Cat() {
        System.out.println("Отработал конструктор Cat!");
    }

    // конструктор успешно работает, хотя в классе Cat нет полей brain и heart
    public Cat(String brain, String heart, String tail) {
        this.brain = brain;
        this.heart = heart;
        this.tail = tail;
        System.out.println("Отработал конструктор Cat с параметрами!");
    }

    public Cat(String brain, String heart, String tail, String color) {
        // super() всегда должно стоять в конструкторе первым
        super(brain, heart);
        this.tail = tail;
        this.color = color;
    }


    public static void main(String[] args) {
        // при создании объекта в первую очередь вызывается конструктор его базового класса,
        // а только потом — конструктор самого класса, объект которого создаем.
        // чтобы не дублировать общие поля двух классов
        Cat cat = new Cat();

        System.out.println("---------------------");

        Cat catIdent = new Cat("мозг", "сердце", "хвостик");

        Cat catColor = new Cat("Мозг", "Сердце", "Хвост", "Белый");

        Cat catInit = new Cat("Мозг", "Сердце", "Хвост");
    }
}




