package chapter13_Add_Reflection;

import org.w3c.dom.ls.LSOutput;

import java.lang.reflect.Modifier;

public class Ch13_Reflection {
    public static void main(String[] args) {

        //--- Работа с классами

        // Raw use of parameterized class 'Class'
        Class mClassObject = SomeClass.class;


        try {
            //Class mClassObject2 = Class.forName("SomeClass");
            Class mClassObject3 = Class.forName("chapter13_Add_Reflection.SomeClass");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //--- Получаем название класса
        // getName() , который вернет полное имя класса с пакетом:
        String fullClassName = mClassObject.getName();
        System.out.println(fullClassName);
        // getSimpleName(), который вернет только название класса без имени пакета:
        String justClassName = mClassObject.getSimpleName();
        System.out.println(justClassName);
        System.out.println("---------------------");

        //--- Работа с модификаторами доступа
        /*
        Модификаторы представляют собой ключевые слова public, static, private и т.д.
        Можно получить модификаторы с помощью метода getModifiers():
         */

        int classModifiers = mClassObject.getModifiers();
        System.out.println(classModifiers);
        System.out.println("---------------------");
        /*
        в переменной int, где каждый модификатор — это битовый флаг, который может быть установлен или сброшен
         */

        /*
        можно проверить модификаторы, используя следующие методы в классе java.lang.reflect.Modifier.
        возврат - boolean - true or false
         */

        System.out.println("is abstract? \t\t" + Modifier.isAbstract(classModifiers));

        System.out.println("is final? \t\t"+ Modifier.isFinal(classModifiers));

        System.out.println("is interface? \t\t"+ Modifier.isInterface(classModifiers));
        System.out.println("is native? \t\t" + Modifier.isNative(classModifiers));

        System.out.println("is private? \t\t"+Modifier.isPrivate(classModifiers));
        System.out.println("is protected? \t\t"+Modifier.isProtected(classModifiers));
        System.out.println("is public? \t\t"+Modifier.isPublic(classModifiers));

        System.out.println("is static? \t\t"+Modifier.isStatic(classModifiers));
        System.out.println("is strict? \t\t"+Modifier.isStrict(classModifiers));
        System.out.println("is synchronized? \t\t"+Modifier.isSynchronized(classModifiers));
        System.out.println("is transient? \t\t"+Modifier.isTransient(classModifiers));
        System.out.println("is volatile? \t\t"+Modifier.isVolatile(classModifiers));
    }


}


// javadevblog.com/polnoe-rukovodstvo-po-java-reflection-api-refleksiya-na-primerah.html


/*
Рефлексия в Java — это механизм, с помощью которого можно вносить изменения и получать информацию о классах,
интерфейсах, полях и методах во время выполнения, при этом не зная имен этих классов, методов и полей.

Кроме того, Reflection API дает возможность создавать новые экземпляры классов, вызывать методы,
а также получать или устанавливать значения полей.
 */

/*
Java Reflection API
Рефлексия — мощная концепция, которая лежит в основе большинства современных Java/Java EE фреймворков и библиотек.
, для Java классическими примерами являются:

JUnit – фреймворк для модульного тестирования. Он использует рефлексию для парсинга аннотаций (например, @Test)
        для получения описанных программистом тестовых методов и дальнейшего их выполнения.

Spring – фреймворк для разработки приложений на Java платформе,
        в основе которого лежит внедрение зависимостей (инверсия управления).

Список можно продолжать бесконечно: от веб-контейнеров до решения задач объектно-реляционного отображения (ORM).
Их всех объединяет одно: они используют Java рефлексию, потому что не имеют доступа
и представления к определенных пользователем классах, методах, интерфейсах и т.д.
 */

/*
Ограничения при работе с рефлексией в Java
Почему мы не должны использовать рефлексию в обычном программировании, когда уже есть доступ к интерфейсам и классам.

Причин несколько:

Низкая производительность —         поскольку рефлексия в Java определяет типы динамически,
                                    то она сканирует classpath, чтобы найти класс для загрузки,
                                    в результате чего снижается производительность программы.

Ограничения системы безопасности —  рефлексия требует разрешения времени выполнения,
                                    которые не могут быть доступны для систем,
                                    работающих под управлением менеджера безопасности (Java Security Manager).

Нарушения безопасности приложения — с помощью рефлексии мы можем получить доступ к части кода,
                                    к которой мы не должны получать доступ.
                                    Например, мы можем получить доступ к закрытым полям класса и менять их значения.
                                    Это может быть серьезной угрозой безопасности.

Сложность в поддержке —             код, написанный с помощью рефлексии трудно читать и отлаживать,
                                    что делает его менее гибким и трудно поддерживаемым.
 */

/*
В этом разделе мы рассмотрим важные методы при работе с java.lang.Class:

Все типы в Java, включая примитивные типы и массивы имеют связанный с ними java.lang.Class объект.
Если знаем название класса во время компиляции, то сможем получить объект следующим образом:

     Class mClassObject = SomeObject.class

Если не знаем имя во время компиляции, но знаем имя класса во время выполнения, то можно сделать так:

    Class mClassObject = Class.forName("здесь имя класса")
 */

/*
При использовании метода Class.forName() мы должны указать полное имя класса.
То есть имя класса, включая все имена пакетов.

Например, если SomeObject находится в пакете com.javadevblog.app,
то полным именем вашего класса является строка: com.javadevblog.app.SomeObject.

Метод Class.forName() может бросить исключение ClassNotFoundException,
если класс не будет найден в classpath во время выполнения.
 */