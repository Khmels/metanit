package chapter3;

import org.w3c.dom.ls.LSOutput;

public class P12_Enum {

    public static void main(String[] args) {

        Day current = Day.MONDAY;
        System.out.println(current);    // MONDAY

        BookEnum b1 = new BookEnum("War and Peace", "L. Tolstoy", Type.NOVEL);
        System.out.printf("Book '%s' has a type %s", b1.name, b1.bookType);
        System.out.println();

        switch(b1.bookType){
            case NOVEL:
                System.out.println("Novel");
                break;
            case SCIENCE:
                System.out.println("Science");
                break;
            case SCIENCE_FICTION:
                System.out.println("Science fiction");
                break;
            case FANTASY:
                System.out.println("Fantasy");
                break;
        }

        System.out.println("---------------------");

        //--- Методы перечислений ---
        // Каждое перечисление имеет статический метод values().
        // Он возвращает массив всех констант перечисления:

        Type[] types = Type.values();
        for (Type s : types) {
            System.out.println(s);
        }

        // ordinal() возвращает порядковый номер определенной константы (нумерация начинается с 0):
        System.out.println("Поряндковый номер константы: " + b1.bookType.ordinal());
        System.out.println(Type.SCIENCE_FICTION.ordinal());

        System.out.println("---------------------");

        OperationEnum op = OperationEnum.SUM;
        System.out.println(op.action(10, 4));   // 14

        op = OperationEnum.MULTIPLY;
        System.out.println(op.action(6, 4));    // 24

    }
}

enum Day{

    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}

enum Type
{
    SCIENCE,
    NOVEL ,
    FANTASY,
    SCIENCE_FICTION
}

class BookEnum {

    String name;
    Type bookType;
    String author;

    BookEnum(String name, String author, Type type) {

        this.bookType = type;
        this.name = name;
        this.author = author;
    }
}

// Перечисления, как и обычные классы, могут определять конструкторы, поля и методы.
enum Color{
    RED("#FF0000"), BLUE("#0000FF"), GREEN("#00FF00");
    private String code;

    // конструктор по умолчанию приватный, то есть имеет модификатор private.
    // Любой другой модификатор будет считаться ошибкой.
    // Поэтому создать константы перечисления с помощью конструктора возможно только внутри перечисления.

    Color(String code){
        this.code = code;
    }

    /// Modifier 'public' not allowed here
///    public Color(String code, boolean p){
///
///   }
    public String getCode(){ return code;}
}

//можно определять методы для отдельных констант:

enum OperationEnum{
    SUM{
        @Override
        public int action(int x, int y)
        {
            return x + y;
        }
    },
    SUBTRACT{
        @Override
        public int action(int x, int y){ return x - y;}
    },
    MULTIPLY{
        @Override
        public int action(int x, int y){ return x * y;}
    };

    public abstract int action(int x, int y);
}