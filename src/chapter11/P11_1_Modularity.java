package chapter11;

public class P11_1_Modularity {
}

/*
С выходом JDK 9 в языке Java появилась новая возможность - модульность.
Модульность позволяет разбить код на отдельные структурные единицы - модули.

Фактически модуль представляет группу пакетов или ресурсов, объединенных в одно целое и к которым можно обращаться по имени модуля.
 */

/*
До Java 9 было несколько уровней инкапсуляции функционала.
Первый уровень представлял класс, в котором мы могли определить переменные и методы с различным уровнем доступа.
Следующий уровень представлял пакет, который, в свою очередь, представлял коллекцию классов.
Однако со временем этих уровней оказалось недостаточно.

И модуль стал следующим уровнем инкапсуляции, который объединял несколько пакетов.
 */

/*
Модуль состоит из группы пакетов.
Включает список все пакетов, которые входят в модуль, и список всех модулей, от которых зависит данный модуль.

Дополнительно (но необязательно) он может включать файлы ресурсов или файлы нативных библиотек.
 */

/*
В качестве названия модуля может использоваться произвольный идентификатор из алфавитно-цифровых символов и знаков подчеркивания.
Но рекомендуется, чтобы название модуля соответствовало названию, которого начинаются пакеты этого модуля.
 */

/*
Затем для компиляции модуля выполним следующую команду:
        javac demo/module-info.java demo/com/example/hello/Hello.java

        C:\Users\*userName*\IdeaProjects>javac demo/module-info.java demo/com/example/hello/Hello.java

        C:\Users\*userName*\IdeaProjects\metanit>javac module-info.java src/chapter11/example/com/demo/Hello.java
        C:\Users\*userName*\IdeaProjects\metanit\src>javac module-info.java chapter11/example/com/demo/Hello.java


После компиляции модуля demo выполним программу с помощью следующей команды:
        java  --module-path demo --module demo/com.example.hello.Hello

        C:\Users\*userName*\IdeaProjects>java  --module-path demo --module demo/com.example.hello.Hello
        C:\Users\*userName*\IdeaProjects>java --module-path metanit --module metanit/chapter11.example.com.demo.Hello

Параметр --module-path указывает на путь к модулю, а --module - на главный класс модуля.
При наборе команды вместо параметра --module-path можно указать его сокращение -p, а вместо параметра --module - сокращение -m.
 */

/*
C:\Users\*userName*\IdeaProjects\metanit\src>javac module-info.java chapter11/example/com/demo/Hello.java
C:\Users\*userName*\IdeaProjects>java --module-path metanit --module metanit/chapter11.example.com.demo.Hello

файлы
C:\Users\*userName*\IdeaProjects\metanit\src\chapter11\example\com\demo\Hello.java
C:\Users\*userName*\IdeaProjects\metanit\src\module-info.java
 */