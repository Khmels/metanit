package chapter5;

public class Ch5_Person_public implements Comparable<Ch5_Person_public>{

    private String name;
    public Ch5_Person_public(String value){

        name=value;
    }
    String getName(){return name;}

    @Override
    public int compareTo(Ch5_Person_public o) {
        return this.name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return "Ch5_PersonArray{" +
                "name='" + name + '\'' +
                '}';
    }
}
