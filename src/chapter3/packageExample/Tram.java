package chapter3.packageExample;

import chapter3.inheritanceExample.Train;

public class Tram extends Train {
    boolean isUrban;

    // если Train() с default модификатором конструктором, то его не видно в другом пакете

    // если нет конструктора - конструктор default - то наследование удается
}
