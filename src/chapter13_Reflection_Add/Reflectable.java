package chapter13_Reflection_Add;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.TYPE)
public @interface Reflectable {
    public String name();
    public String value();
}


/*
две аннотации, которые применяются только к аннотациям при создании:

@Retention(RetentionPolicy.RUNTIME) и
@Target(ElementType.TYPE)               указывают как будет использоваться пользовательская аннотация.

@Retention(RetentionPolicy.RUNTIME)     указыввет на то, что аннотация будет использована в рантайме,
                                        следовательно можно получить к ней доступ с помощью рефлексии.

@Target(ElementType.TYPE)               указывает на то, что мы можем использовать аннотацию на интерфейсах и классах.
 */