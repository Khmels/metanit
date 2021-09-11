package chapter13_Add_Reflection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Reflectable(name="reflectable",  value = "какие-то метаданные")
public class SomeClass implements SomeInterface{
    String name;
    long id;
    String email;
    public String publicInfo;
    private String mPrivateString = null;
    @Reflectable(name="reflectable",  value = "какие-то метаданные поля")
    public String mField = null;
    protected List<String> simpleList = new ArrayList<String>(Arrays.asList("1", "2", "3"));


    public SomeClass(String name, long id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
    }

    public SomeClass(String privateString){
        this.mPrivateString = privateString;
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


    private String getPrivateString(){
        return this.mPrivateString;
    }

    @Reflectable(name="reflectable",  value = "какие-то метаданные метода")
    public void sayHello(){
        System.out.println("Hello, annotation");
    }

    public static void sayBye(
            @Reflectable(name="reflectable", value="какие-то метаданные параметров") String param){
    }

    public List<String> getList(){
        return this.simpleList;
    }

}
