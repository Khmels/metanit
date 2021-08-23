package chapter5;

import java.util.Collection;

public class VerifyCollection<T extends Collection> {
    public T collection;

    public void checkCollection(T collection ){
        System.out.println();
        System.out.println("-----INSERTION DUPLICATES----------------");
        int size = collection.size();
        Object[] array = collection.toArray();

        collection.add(array[0]);
        int newSize = collection.size();
        if (size!=newSize) {
            System.out.println("---- dup ALLOWED ---    , size: " + size + ", new size: " + newSize);
        }
        else{
            System.out.println("---- dup DENIED ---     , size: " + size + ", new size: " + newSize);
        }

        System.out.println();

        System.out.println("-----INSERTION NULL----------------------");
        try{
            size = collection.size();
            collection.add(null);
            newSize = collection.size();
            System.out.println("---- null ALLOWED ---   , size: " + size + ", new size: " + newSize);
            System.out.println();
        }
        catch (NullPointerException e){
            System.out.println("---- null DENIED ---    , size: " + size + ", new size: " + newSize);
            System.out.println();
            e.getMessage();
        }

        System.out.println("-----Checked collection------------------");
        for(Object person : collection){
//            if(person != null){
//            System.out.println(person.toString());
//            }
//            else{
//                System.out.println(" + null element");
//            }
            System.out.println(person);
        }

    }
}
