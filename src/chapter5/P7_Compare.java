package chapter5;

import java.util.Comparator;
import java.util.TreeSet;

@SuppressWarnings("DuplicatedCode")
public class P7_Compare {
    public static void main(String[] args) {
        TreeSet<Ch5_PersonNew_default> tree = new TreeSet<>();

        /// java.lang.ClassCastException:
        // class chapter5.Ch5_PersonNew cannot be cast to class java.lang.Comparable

        // чтобы объекты Person можно было сравнить и сортировать, они должны применять интерфейс Comparable<E>.
        // При применении интерфейса он типизируется текущим классом
        Ch5_PersonNew_default person = new Ch5_PersonNew_default("Jack");
        Ch5_PersonNew_default personAge = new Ch5_PersonNew_default(25);
        personAge.setName("Tom");
        person.setAge(20);

        Ch5_PersonNew_default personId = new Ch5_PersonNew_default(1L);
        personId.setName("Alfred");
        personId.setAge(30);

        //tree.add(new Ch5_PersonNew()); // java.lang.NullPointerException - нет имени
        tree.add(person);
        tree.add(personAge);
        tree.add(personId);

        for (var eachPerson:tree) {
            System.out.println(eachPerson.toString());
        }

        // проблема, если разработчик не реализовал в своем классе, который мы хотим использовать, интерфейс Comparable,
        // либо реализовал, но не устраивает его функциональность, и хотим ее переопределить
        //
        // На этот случай есть еще более гибкий способ, предполагающий применение интерфейса Comparator<E>.

        System.out.println("---------------------");

        Ch5_PersonReverseComparator comparatorReverseName = new Ch5_PersonReverseComparator();
        TreeSet<Ch5_PersonNew_default> treeComparator = new TreeSet<>(comparatorReverseName);
        treeComparator.add(person);
        treeComparator.add(personAge);
        treeComparator.add(personId);

        for (var eachPerson:treeComparator) {
            System.out.println(eachPerson.toString());
        }

        System.out.println("---------------------");

        Ch5_PersonNameLengthComparator comparatorNameLength = new Ch5_PersonNameLengthComparator();
        TreeSet<Ch5_PersonNew_default> treeComparatorLength = new TreeSet<>(comparatorNameLength);
        treeComparatorLength.add(person);
        treeComparatorLength.add(personAge);
        treeComparatorLength.add(personId);

        for (var eachPerson:treeComparatorLength) {
            System.out.println(eachPerson.toString());
        }

        System.out.println("---------------------");

        //последовательное сравнение
        // class java.util.Comparator$$Lambda$14/0x000000084006f840 cannot be cast to class chapter5.Ch5_PersonNameLengthComparator
        // Ch5_PersonNameLengthComparator comparatorNameAndAge = (Ch5_PersonNameLengthComparator) new Ch5_PersonNameLengthComparator().thenComparing(new Ch5_PersonAgeComparator());

        Comparator<Ch5_PersonNew_default> comparatorNameAndAge = new Ch5_PersonNameLengthComparator().thenComparing(new Ch5_PersonAgeComparator());

        TreeSet<Ch5_PersonNew_default> treeDoubleCompare = new TreeSet<>(comparatorNameAndAge);

        /// java.lang.ClassCastException:


        Ch5_PersonNew_default person2 = new Ch5_PersonNew_default("Jack");
        person2.setAge(21);
        person.setAge(20);
        personAge.setAge(30);
        personId.setAge(40);

        treeDoubleCompare.add(person);
        treeDoubleCompare.add(person2);
        treeDoubleCompare.add(personAge);
        treeDoubleCompare.add(personId);

        for (var eachPerson:treeDoubleCompare) {
            System.out.println(eachPerson.toString());
        }

    }
}
