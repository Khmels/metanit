package chapter6_IOStreams;

import java.io.Serializable;

public class Person_Serial implements Serializable {

    private String name;
    private int age;
    private transient double height;    //исключает из сериализации
    private transient boolean married;  //исключает из сериализации

    Person_Serial(String n, int a, double h, boolean m){

        name=n;
        age=a;
        height=h;
        married=m;
    }
    String getName() {return name;}
    int getAge(){ return age;}
    double getHeight(){return height;}
    boolean getMarried(){return married;}
}

/*
У каждого класса, реализующего Serializable, должно быть поле,
содержащее уникальный идентификатор версии сериализованного класса, оно объявляется следующим образом:

private static final long serialVersionUID = 1L;

Значение идентификатора (в данном случае 1, но может быть любым) должно быть разным у разных классов.
Оно вычисляется по содержимому класса - полям, методам и порядку их объявления.

И если поменяем в нашем классе тип поля или количество полей, то идентификатор изменится.
Идентификатор тоже записывается в файл при сериализации класса.

Когда пытаемся провести десериализацию, то есть восстановить объект из набора байт,
значение идентификатора сравнивается со значением serialVersionUID класса в нашей программе.

Если значения не совпадают, будет выброшено исключение java.io.InvalidClassException.
Чтобы избежать таких ситуаций, просто вручную задаем для нашего класса этот идентификатор
(иначе его значение будет непредсказуемо генерироваться компилятором).

Помимо стандартной сериализации, можно создать свою, записывающую данные в нужном формате.
Для этого вместо Serializable класс должен реализовывать интерфейс Externalizable
с 2 методами writeExternal (сериализация) и readExternal (десериализация).
Идентификатор в этом случае не нужен.
 */

