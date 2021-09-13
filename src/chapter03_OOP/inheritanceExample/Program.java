package chapter03_OOP.inheritanceExample;

public class Program {
    public static void main(String[] args) {
        MetroTrain mt = new MetroTrain();
        mt.name = "Yellow";
        mt.id = 101;
        mt.length = 40.5;
        mt.isMetro = true;
        mt.displayTrainInfo();

        Train train = new Train();
        train.displayTrainInfo();
    }
}
