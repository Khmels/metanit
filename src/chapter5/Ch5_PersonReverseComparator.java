package chapter5;

import java.util.Comparator;

public class Ch5_PersonReverseComparator implements Comparator<Ch5_PersonNew_default>{
    @Override
    public int compare(Ch5_PersonNew_default o1, Ch5_PersonNew_default o2) {
        return o2.getName().compareTo(o1.getName());
    }
}

class Ch5_PersonNameLengthComparator implements Comparator<Ch5_PersonNew_default>{

    @Override
    public int compare(Ch5_PersonNew_default o1, Ch5_PersonNew_default o2) {
        return o1.getName().length()-o2.getName().length();
    }
}

// с JDK 8 в механизм работы компараторов были внесены некоторые дополнения.
// В частности, теперь можно применять сразу несколько компараторов по принципу приоритета.

class Ch5_PersonAgeComparator implements Comparator<Ch5_PersonNew_default>{

    public int compare(Ch5_PersonNew_default a, Ch5_PersonNew_default b){

        if(a.getAge()> b.getAge())
            return 1;
        else if(a.getAge()< b.getAge())
            return -1;
        else
            return 0;
    }
}

// public interface Comparator<T>
// int compare(T o1, T o2);

/*
 * Метод compare также возвращает числовое значение - если оно отрицательное,
 * то объект o1 предшествует объекту o2, иначе - наоборот. А если метод возвращает ноль, то объекты равны.
 */