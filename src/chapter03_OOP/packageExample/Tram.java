package chapter03_OOP.packageExample;

import chapter03_OOP.inheritanceExample.Train;

public class Tram extends Train {
    boolean isUrban;

    // если Train() с default модификатором конструктором, то его не видно в другом пакете

    // если нет конструктора - конструктор default - то наследование удается
}
