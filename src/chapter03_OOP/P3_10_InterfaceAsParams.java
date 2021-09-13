package chapter03_OOP;

public class P3_10_InterfaceAsParams {
    public static void main(String[] args) {
        PrintableParams printable = createPrintable("Foreign Affairs",false);
        printable.print();

        PrintableParams printableParams = createPrintable("People", true);
        printableParams.print();

        read(new BookParam("Java for impatiens", "Cay Horstmann"));
        read(new JournalParam("Java Daily News"));
    }

    static void read(PrintableParams p){
        // Метод read() в качестве параметра принимает объект интерфейса Printable,
        // поэтому в этот метод можно передать как объект Book, так и объект Journal.
        p.print();
    }

    static PrintableParams createPrintable(String name, boolean option){
        // createPrintable() возвращает объект Printable,
        // поэтому также можно возвратить как объект Book, так и Journal.
        if(option)
            return new BookParam(name, "Undefined");
        else
            return new JournalParam(name);
    }
}

interface PrintableParams{

    void print();
}
class BookParam implements PrintableParams{

    String name;
    String author;

    BookParam(String name, String author){

        this.name = name;
        this.author = author;
    }

    @Override
    public void print() {
        System.out.printf("%s (%s) \n", name, author);
    }
}
class JournalParam implements PrintableParams {

    private String name;

    String getName(){
        return name;
    }

    JournalParam(String name){

        this.name = name;
    }

    @Override
    public void print() {
        System.out.println(name);
    }
}