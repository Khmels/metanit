package chapter3_OOP.inheritanceExample;

public class Train {
    String name;
    int id;
    double length;

    // protected +, public +, default +, private -
    // protected - виден из другого пакета

//    Train() {
//    }
//
//    public Train(String name, int id, double length) {
//        this.name = name;
//        this.id = id;
//        this.length = length;
//    }

    public void displayTrainInfo(){
        System.out.println("Название: "+ name + ", номер: " + id + ", длина: " + length);
    }
}