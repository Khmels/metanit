package chapter3.inheritanceExample;

///There is no default constructor available in 'chapter3.inheritanceExample.Train'
public class MetroTrain extends Train{
    boolean isMetro;

    @Override
    public void displayTrainInfo() {
        System.out.printf("Название: %s, номер: %d, длина: %f, метро?: %s", super.name, super.id, super.length, this.isMetro);
        System.out.println();
    }
}
