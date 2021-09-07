package chapter13_Add_Reflection;

public class SomeClass implements SomeInterface{
    String name;
    long id;
    String email;

    public SomeClass(String name, long id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
    }

    public SomeClass() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SomeClass{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public String rename(String s) {
        this.name = s;
        return "Name is changed to : " + s;
    }
}
