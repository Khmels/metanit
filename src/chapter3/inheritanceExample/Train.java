package chapter3.inheritanceExample;

public class Train {
    String name;
    int id;
    double length;

    // protected +, public +, default +, private -
    protected Train() {
    }

    public Train(String name, int id, double length) {
        this.name = name;
        this.id = id;
        this.length = length;
    }

    public void displayTrainInfo(){
        System.out.println("Название: "+ name + ", номер: " + id + ", длина: " + length);
    }
}