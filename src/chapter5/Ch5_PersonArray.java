package chapter5;

public class Ch5_PersonArray implements Comparable<Ch5_PersonArray>{

    private String name;
    public Ch5_PersonArray(String value){

        name=value;
    }
    String getName(){return name;}

    @Override
    public int compareTo(Ch5_PersonArray o) {
        return this.name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return "Ch5_PersonArray{" +
                "name='" + name + '\'' +
                '}';
    }
}

