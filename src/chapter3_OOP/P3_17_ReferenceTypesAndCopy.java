package chapter3_OOP;

public class P3_17_ReferenceTypesAndCopy {
    public static void main(String[] args) {
        PersonForCopy tom = new PersonForCopy("Tom", 23);
        tom.display();      // Person Tom
        PersonForCopy bob = tom;
        bob.setName("Bob");
        tom.display();      // Person Bob
        bob.display();      // Person Bob
        // несмотря на то, что изменяется только объект bob, вместе с ним изменяется и объект tom.
        // Потому что после присвоения они указывают на одну и ту же область в памяти,
        // где собственно данные об объекте Person и его полях и хранятся.

        System.out.println("---------------------");

        // Однако данный способ осуществляет неполное копирование и подойдет,
        // если клонируемый объект не содержит сложных объектов.
        try{
            PersonClone tomClone = new PersonClone("Tom", 23);
            PersonClone bobClone = tomClone.clone();
            bobClone.setName("Bob");
            tomClone.display();      // Person Tom
            bobClone.display();      // Person Bob
        }
        catch(CloneNotSupportedException ex){

            System.out.println("Cloneable not implemented");
        }

        System.out.println("---------------------");

        // Объект сложнее
        try{
            BookClone book = new BookClone("Koly shhe zviri govoryly", "Ivan Franko");
            BookClone book2 = book.clone();
            book2.setAuthor("Lesya Ukrainka");
            System.out.println(book2.getAuthor());  // Lesya Ukrainka
            System.out.println(book.getAuthor());   // Lesya Ukrainka
        }
        catch(CloneNotSupportedException ex){

            System.out.println("Cloneable not implemented");
        }

        System.out.println("---------------------");

        // В этом случае необходимо выполнить полное копирование.
        // Для этого надо определить метод клонирования у класса Author:

        try{
            BookClone2 book3 = new BookClone2("Koly shhe zviri govoryly", "Ivan Franko");
            BookClone2 book4 = book3.clone();
            book4.setAuthor("Lesya Ukrainka");
            System.out.println(book3.getAuthor());  // Ivan Franko
            System.out.println(book4.getAuthor());  // Lesya Ukrainka
        }
        catch(CloneNotSupportedException ex){

            System.out.println("Cloneable not implemented");
        }

    }
}

class PersonForCopy{
    private String name;
    private int age;

    PersonForCopy(String name, int age){
        this.name=name;
        this.age=age;
    }
    void setName(String name){
        this.name = name;
    }
    void setAge(int age){
        this.age = age;
    }
    void display(){
        System.out.printf("Person Name: %s \n", name);
    }
}

class PersonClone implements Cloneable{
    private String name;
    private int age;

    PersonClone(String name, int age){
        this.name=name;
        this.age=age;
    }
    void setName(String name){
        this.name = name;
    }
    void setAge(int age){
        this.age = age;
    }
    void display(){
        System.out.printf("Person %s \n", name);
    }

    // Реализация этого метода просто возвращает вызов метода clone для родительского класса -
    // то есть класса Object с преобразованием к типу Person.

    // на случай если класс не поддерживает клонирование,
    // метод должен выбрасывать исключение CloneNotSupportedException, что определяется с помощью оператора throws.

    @Override
    protected PersonClone clone() throws CloneNotSupportedException {
        return (PersonClone) super.clone();
    }
}

class BookClone implements Cloneable{

    private String name;
    private Author author;

    public void setName(String n){ name=n;}
    public String getName(){ return name;}

    public void setAuthor(String n){ author.setName(n);}
    public String getAuthor(){ return author.getName();}

    BookClone(String name, String author){

        this.name = name;
        this.author = new Author(author);
    }

    public String toString(){

        return "Книга '" + name + "' (автор " +  author + ")";
    }

    public BookClone clone() throws CloneNotSupportedException{

        return (BookClone) super.clone();
    }
}

class Author{

    private String name;

    public void setName(String n){ name=n;}
    public String getName(){ return name;}

    public Author(String name){

        this.name=name;
    }
}

// полное копирование - реализовать метод Clone() и в вложенных объектах

class AuthorClone implements Cloneable{

    private String name;

    public void setName(String n){ name=n;}
    public String getName(){ return name;}

    public AuthorClone(String name){

        this.name=name;
    }

    public AuthorClone clone() throws CloneNotSupportedException{

        return (AuthorClone) super.clone();
    }
}

class BookClone2 implements Cloneable{
    private String name;
    private AuthorClone author;

    public void setName(String n){ name=n;}
    public String getName(){ return name;}

    public void setAuthor(String n){ author.setName(n);}
    public String getAuthor(){ return author.getName();}

    BookClone2(String name, String author){

        this.name = name;
        this.author = new AuthorClone(author);
    }

    public String toString(){

        return "Книга '" + name + "' (автор " +  author + ")";
    }

    public BookClone2 clone() throws CloneNotSupportedException{

        BookClone2 newBook = (BookClone2) super.clone();
        newBook.author=(AuthorClone) author.clone();
        return newBook;
    }
}