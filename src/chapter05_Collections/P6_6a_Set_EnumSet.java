package chapter05_Collections;

public class P6_6a_Set_EnumSet {

    //`javadoc`
    //### public abstract class EnumSet<E extends Enum<E>> extends AbstractSet<E>
    //                                                      implements Cloneable, java.io.Serializable{}

    //`javadoc`
    //### class JumboEnumSet<E extends Enum<E>> extends EnumSet<E>

    //`javadoc`
    //### class RegularEnumSet<E extends Enum<E>> extends EnumSet<E>

    /*
    You can't use the class directly because it is declared as package private.
    And you shouldn't, since it is described in its javadoc as a "private implementation class".
     */

    public static void main(String[] args) {
        /// 'EnumSet' is abstract; cannot be instantiated
        // EnumSet enumSet = new EnumSet<>();

    }
}
