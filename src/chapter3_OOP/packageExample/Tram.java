package chapter3_OOP.packageExample;

import chapter3_OOP.inheritanceExample.Train;

public class Tram extends Train {
    boolean isUrban;

    // если Train() с default модификатором конструктором, то его не видно в другом пакете

    // если нет конструктора - конструктор default - то наследование удается
}
