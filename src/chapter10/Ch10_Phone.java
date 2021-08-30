package chapter10;

import java.util.Comparator;

public class Ch10_Phone implements Comparable<Ch10_Phone>{

    private String name;
    private String company;
    private int price;

    public Ch10_Phone(String name, int price){
        this.name=name;
        this.price=price;
    }

    public Ch10_Phone(String name, String comp, int price){
        this.name=name;
        this.company=comp;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getCompany() { return company; }


    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static int compare(Ch10_Phone p1, Ch10_Phone p2) {
        if (p1.getPrice() > p2.getPrice())
            return 1;
        return -1;
    }

    @Override
    public int compareTo(Ch10_Phone p2) {
        if (this.getPrice() > p2.getPrice())
            return 1;
        else if (this.getPrice() == p2.getPrice())
            return 0;
        else return -1;
    }
}


class PhoneComparator implements Comparator<Ch10_Phone> {

    @Override
    public int compare(Ch10_Phone a, Ch10_Phone b){

        return a.getName().toUpperCase().compareTo(b.getName().toUpperCase());
    }
}



