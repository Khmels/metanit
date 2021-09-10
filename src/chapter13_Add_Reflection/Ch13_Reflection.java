package chapter13_Add_Reflection;

import org.w3c.dom.ls.LSOutput;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;

public class Ch13_Reflection {
    public static void main(String[] args) {

        //--- Работа с классами ------------------------------------------------------------------------

        // Raw use of parameterized class 'Class'
        Class mClassObject = SomeClass.class;

        Class<SomeClass> mClassObjectIdent = SomeClass.class;


        try {
            //Class mClassObject2 = Class.forName("SomeClass");
            Class mClassObject3 = Class.forName("chapter13_Add_Reflection.SomeClass");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //--- Название класса ------------------------------------------------------------------------
        // getName() , который вернет полное имя класса с пакетом:
        String fullClassName = mClassObject.getName();
        System.out.println(fullClassName);
        // getSimpleName(), который вернет только название класса без имени пакета:
        String justClassName = mClassObject.getSimpleName();
        System.out.println(justClassName);
        System.out.println("---------------------");

        //--- Модификаторы доступа ------------------------------------------------------------------------
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
        System.out.println("---------------------");

        //--- Информация о пакете ------------------------------------------------------------------------

        Package packageInfo = mClassObject.getPackage();
        System.out.println(packageInfo.getName());
        System.out.println("---------------------");

        /* также можно получить доступ к информации, указанной для данного пакета в Manifest файле внутри JAR файла.
        этот пакет находится в пути к классам. Подробнее о Package читайте  оф. документации: java.lang.Package.
         */

        //--- Объект суперкласса ------------------------------------------------------------------------

        Class superclass = mClassObject.getSuperclass();
        System.out.println(superclass.getName());
        System.out.println("---------------------");
        // если нет наследования, то java.lang.Object

        //--- Реализованные интерфейсы ------------------------------------------------------------------------

        Class[] interfaces = mClassObject.getInterfaces();
        for(Class cl: interfaces){
            System.out.println(cl.getName());
        }
        System.out.println("---------------------");

        /*
        Класс может реализовать много интерфейсов. Поэтому возвращается массив объектов Class.
        В Java Reflection API интерфейсы также представлены объектами Class

        Метод вернул только интерфейсы, которые реализует указанный класс, а не его суперкласс
         */

        //--- Конструкторы ------------------------------------------------------------------------

        //Raw use of parameterized class 'Constructor'
//        Constructor [] constructors = mClassObject.getConstructors();   // SomeClass.class
//        for(Constructor constructor: constructors){
//            System.out.println(constructor.getName());
//        }

        Constructor<?> [] constructors = mClassObjectIdent.getConstructors();   // SomeClass.class
        for(Constructor<?> constructor: constructors){
            System.out.println(constructor.getName() + ", number of parameters " + constructor.getParameterCount() +  "; "
                    + constructor.getModifiers() + "; ");
            // Returns the name of this constructor, as a string.
            // This is the binary name of the constructor's declaring class.
        }
        System.out.println("-----------");


        //Если известны параметры конкретного конструктора, то массив можно не получать, а работать уже с известным.

        /*
        Если ошиблись с аргументом конструктора, то будет выброшено исключение NoSuchMethodException.
        newInstance требует обработать исключения IllegalAccessException, InstantiationException, InvocationTargetException
         */

        try {
            Constructor<?> constructor =
                    mClassObjectIdent.getConstructor(String.class, long.class, String.class);

                    // mClassObjectIdent.getConstructor(new Class[]{String.class, long.class, String.class});
            SomeClass myObject = (SomeClass)
                    /// unchecked
                    /// Exception in thread "main" java.lang.IllegalArgumentException: wrong number of arguments
                    constructor.newInstance("constructor-arg1", 1234567890, "constructor-arg1");
            System.out.println(myObject.toString());
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("---------------------");

        //IDEA: 'catch' branch identical to 'NoSuchMethodException' branch

        /*
        try{ ....
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
         */

        //--- Параметры конструктора

        for(Constructor<?> constructor: constructors){
            // получить типы параметров какого-то конструктора можно так:
            // length 0 для конструктора без параметров (ничего не выводит)
            Class[] parameterTypes = constructor.getParameterTypes();
            for (Class cl:parameterTypes) {
                System.out.println(cl.getName());
            }
        }
        System.out.println("---------------------");

        //--- Создание нового объекта

        Constructor constructor = null;
        try {
            constructor = SomeClass.class.getConstructor();
            SomeClass myObject = (SomeClass)
                    constructor.newInstance();
            System.out.println(myObject.toString());
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("---------------------");

        //--- Поля ------------------------------------------------------------------------

        /*
        Используя рефлексию можно работать с полями — переменными-членами класса.
        Java класс java.lang.reflect.Field: с помощью него в рантайме можно устанавливать значения и получать данные с полей.
         */

        Field[] fields = mClassObject.getFields();
        // Каждый элемент массива содержит экземпляр public поля, объявленного в классе.

        //Если известно имя поля, к которому необходимо получить доступ, достаточно:
        //Field 'id' is not public - поэтому exception
        Field field = null;
        try {
            field = mClassObject.getField("publicInfo");
            // название поля
            System.out.println(field.getName());
            // тип поля
            System.out.println(field.getType().getName());

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        System.out.println("---------------------");

        // установка значения полю
        try {
            field = mClassObject.getField("publicInfo");
            System.out.println(field);

            SomeClass instance = new SomeClass();
            SomeClass instance2 = new SomeClass();

            instance.publicInfo = "defaultValue";

            Object value = field.get(instance);

            field.set(instance2, value);
            System.out.println(instance2.publicInfo);

            /*
            Параметр instance, который передается в методы для получения и установки значения поля,
            должен быть экземпляром класса, которому принадлежит само поле.
             */
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("---------------------");


        //--- Методы ------------------------------------------------------------------------

        int i=1;
        Method[] methods = mClassObject.getMethods();
        for (Method m: methods){
            System.out.println(i + ".   " + m.getName() + "\t\t\t\t" + m.toString());
            i++;
        }
        System.out.println("---------------------");

         /*
        не нужно получать массив со всеми методами, если известны точные типы параметров метода, который необходимо использовать.
        Например, есть метод под названием «sayHello«, который принимает String в качестве параметра.
         */
        try {
            Method method = mClassObject.getMethod("setName", String.class);
            // если метод() без параметров, то нужно передать null в методе getMethod():
            Method method1 = mClassObject.getMethod("getName", null);
            System.out.println(method.toString());
            System.out.println(method1.toString());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println("---------------------");

        //--- Параметры метода и типы возвращаемых значений
        try {
            Method method = mClassObject.getMethod("setName", String.class);
            System.out.println("method.toString() = " + method.toString());
            Class<?>[] parameters =  method.getParameterTypes();
            System.out.println("Arrays.toString(parameters) = " + Arrays.toString(parameters));

            System.out.println("method.getParameterTypes().toString() = " + method.getParameterTypes().toString());
            System.out.println("method.getReturnType().toString() = " + method.getReturnType().toString());
            System.out.println("----------");
            // если метод() без параметров, то нужно передать null в методе getMethod():
            Method method1 = mClassObject.getMethod("getName", null);
            // Call to 'toString()' on array
            // IDEA: wrap with 'java.util.Arrays.toString()' expression
            System.out.println(Arrays.toString(method1.getParameterTypes()));
            System.out.println("returnType: " + method1.getReturnType().toString());
            System.out.println(method1.toString());

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println("---------------------");

        //--- Вызов метода с помощью Java рефлексии

        Method method = null;
        try {
            SomeClass objectToInvokeOn = new SomeClass();
            method = mClassObject.getDeclaredMethod("setEmail", String.class);
            method.invoke(objectToInvokeOn, "newEmail");
            System.out.println("objectToInvokeOn.email = " + objectToInvokeOn.email);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException noSuchMethodException) {
            noSuchMethodException.printStackTrace();
        }
        System.out.println("---------------------");

        /*
        objectToInvokeOn    имеет тип Object и является объектом, для которого вызвать метод.
        parameterTypes      имеет тип Class[] и представляет собой параметры, которые метод принимает на вход.
        params              имеет тип Object[] и представляет собой пераметры, которые переданы методы.
         */

        /*
        Следует отметить: если метод() — статический, то вызов метода с помощью рефлексии можно переписать так:
        method.invoke(null, params)
         */

        //--- Методы установки и получения значения Get, Set ---------------------------
        printGettersOrSetters(mClassObject);
        System.out.println("---------------------");

        //--- Приватные поля и методы ------------------------------------------------------------------------

        /*
        Чтобы получить доступ к закрытому полю нужно будет использовать метод
        Class.getDeclaredField(String name) или Class.getDeclaredFields() метод.

        Методы Class.getField(String name) и Class.getFields() возвращают только public поля
         */

        SomeClass privateObject = new SomeClass("какое-то приватное значение");
        //--- Доступ к приватным полям с помощью рефлексии
        try {
            Field privateStringField = SomeClass.class.getDeclaredField("mPrivateString");

            System.out.println("privateStringField.getType().toString() = " + privateStringField.getType().toString());
            privateStringField.setAccessible(true);
            System.out.println("privateStringField.getName() = " + privateStringField.getName());
            System.out.println("privateStringField.getModifiers() = " + privateStringField.getModifiers());
            String fieldValue = (String) privateStringField.get(privateObject);
            System.out.println("значение приватного поля = " + fieldValue);
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        /*
        метод getDeclaredField, который имеет доступ лишь к полям,
        объявленным в конкретном классе и не имеет доступ к полям суперкласса.
         */

        /*
        Field.setAccessible(true), который отключает проверку доступа для указанного поля.
        Теперь можно работать с ним с помощью рефлексии, даже если у него был private, protected или default доступ.
        Без использования рефлексии этот метод все также приватный и компилятор не позволит нам обратиться к нему.
         */
        System.out.println("---------------------");

        //--- Доступ к приватным методам с помощью рефлексии

        /*
        Class.getMethod(String name, Class[] parameterTypes) и Class.getMethods() имеют доступ лишь к public методам.

        Доступ к закрытым методам осуществляется с помощью методов
        Class.getDeclaredMethod(String name, Class[] parameterTypes) или Class.getDeclaredMethods().
         */

        SomeClass privateObjectMethods = new SomeClass("Какое-то значение");

        Method privateStringMethod = null;
        try {
            privateStringMethod = SomeClass.class.getDeclaredMethod("getPrivateString", null);
            System.out.println("privateStringMethod.getParameterCount() = " + privateStringMethod.getParameterCount());
            System.out.println("privateStringMethod.getReturnType().toString() = " + privateStringMethod.getReturnType().toString());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        privateStringMethod.setAccessible(true);

        String returnValue = null;
        try {
            //void method
            returnValue = (String) privateStringMethod.invoke(privateObject, null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println("значение, которое возвращает private метод = " + returnValue);
        System.out.println("---------------------");

        //--- Аннотации ------------------------------------------------------------------------


        /*
        С помощью Java рефлексии мы можем обрабатывать аннотации в рантайме.
        Аннотации также могут быть обработаны с помощью рефлексии.
         */

        /* помощью рефлексии можно работать с аннотациями класса, метода или поля в рантайме.
        getAnnotations() для получения всех аннотаций класса в виде массива объектов Annotation.
        Если же нам конкретная аннотация, то получить к ней доступ можно непосредственно
         */

        //--- Аннотации над классом

        Annotation[] annotations = mClassObject.getAnnotations();

        for (Annotation annotation : annotations) {
            if(annotation instanceof Reflectable) {
                Reflectable mAnnotation = (Reflectable) annotation;
                System.out.println("Аннотация класса");
                System.out.println("name: " + mAnnotation.name());
                System.out.println("value: " + mAnnotation.value());
            }
            for (Annotation a:annotations){
                System.out.println("a.annotationType().toString() = " + a.annotationType().toString());
            }
        }
        System.out.println("-----------");


        Annotation annotation = mClassObject.getAnnotation(Reflectable.class);
        Reflectable mAnnotationCreated = (Reflectable) annotation;
        System.out.println("name direct: " + mAnnotationCreated.name());
        System.out.println("value direct: " + mAnnotationCreated.value());
        System.out.println("---------------------");

        //--- Аннотации на методах

        /*
        Аннотации на методах работают точно также, как и на примере выше, единственная разница в получении списка аннотаций.
        Если выше мы использовали метод getAnnotations(), то для аннотаций на методах можно вызывать метод
        getDeclaredAnnotations() — получение всех аннотаций с указанного метода.
         */

        try {
            Method methodAnnotated =
                    mClassObject.getMethod("sayHello", null);
            Annotation[] annotationsForMethod = methodAnnotated.getDeclaredAnnotations();

            for(Annotation an : annotationsForMethod) {
                if(annotation instanceof Reflectable) {
                    Reflectable mAnnotationMethod = (Reflectable) annotation;
                    System.out.println("a.annotationType().toString() = " + an.annotationType().toString());
                    System.out.println("name: " + mAnnotationMethod.name());
                    System.out.println("value: " + mAnnotationMethod.value());
                }
            }

            // явное получение
            Reflectable reAnnotationMethod = methodAnnotated.getAnnotation(Reflectable.class);
            System.out.println("reAnnotationMethod.name() = " + reAnnotationMethod.name());
            System.out.println("reAnnotationMethod.value() = " + reAnnotationMethod.value());

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println("---------------------");

        //--- Аннотации в параметрах метода

        Method methodAnnotatedParams =
                null;
        try {
            methodAnnotatedParams = mClassObject.getMethod("sayBye", new Class[]{String.class});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        Annotation[][] paramAnnotations = methodAnnotatedParams.getParameterAnnotations();
        Class[] paramTypes = methodAnnotatedParams.getParameterTypes();

        int iP = 0;
        for (Annotation[] annotationPar : paramAnnotations){
            Class parameterType = paramTypes[iP++];

            for (Annotation annotationMethodParam : annotations) {
                if (annotationMethodParam instanceof Reflectable) {
                    Reflectable mAnnotation = (Reflectable) annotation;
                    System.out.println("param: " + parameterType.getName());
                    System.out.println("name: " + mAnnotation.name());
                    System.out.println("value: " + mAnnotation.value());
                }
            }
        }
        /*
        двумерный массив аннотаций на методе:
        внутренний массив каждого элемента является набором аннотаций для каждого параметра (аргумента метода).
         */

        System.out.println("---------------------");

        //--- Аннотации на полях

        try {
            Field fieldAnnotated = mClassObject.getField("mField");
            Annotation annotationOfField = fieldAnnotated.getAnnotation(Reflectable.class);
            Reflectable reflectableFieldAnnotation = (Reflectable) annotationOfField;
            System.out.println("reflectableFieldAnnotation.name() = " + reflectableFieldAnnotation.name());
            System.out.println("reflectableFieldAnnotation.value() = " + reflectableFieldAnnotation.value());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }



    }

    /*
    Кроме обычных методов класса мы также можем работать с методам get и set с помощью рефлексии.
    Получить эти методы можно несколькими способами: явно указать геттер или сеттер при работе
    или обойти все доступные в классе методы и проверить их на то являются ли они методами get или set:

    Чтобы определить геттер или сеттер нам нужно определить некоторые правила:

    Геттер — метод, имя которого начинается на ‘get’. Он не принимает аргументы и обязательно возвращает какое-то значение.
    Сеттер — метод, имя которого начинается на ‘set’. Он принимает 1 параметр.

    Обычно сеттеры не возвращают значение, однако при опеределении метода к сеттеру возвращаемый параметр не учитывается.
     */

    public static void printGettersOrSetters(Class aClass){
        Method[] methods = aClass.getMethods();

        for(Method method : methods){
            if(isGetter(method)) System.out.println("getter: " + method);
        }
        System.out.println();
        for(Method method : methods){
            if(isSetter(method)) System.out.println("setter: " + method);
        }
    }

    public static boolean isGetter(Method method){
        if (!method.getName().startsWith("get")) {
            return false;
        }
        if (method.getParameterTypes().length != 0) {
            return false;
        }
        if (void.class.equals(method.getReturnType())) {
            return false;
        }
        return true;
    }

    public static boolean isSetter(Method method){
        if (!method.getName().startsWith("set")) {
            return false;
        }
        if (method.getParameterTypes().length != 1) {
            return false;
        }
        return true;
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