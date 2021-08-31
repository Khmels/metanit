package chapter11;

public class P11_3_InteractionBetweenModules {
}

/*
Если определяют не один, а несколько модулей, которые используются в программе.
И при взаимодействии модулей есть некоторые особенности, которые следует учитывать.

Взаимодействие модулей друг с другом.
 */

/*
C:\Users\*userName*\IdeaProjects>   javac -d appmodules/operations operations/module-info.java operations/operation/edu/util/Factorial.java
C:\Users\*userName*\IdeaProjects>   javac -p appmodules -d appmodules/open open/module-info.java open/core/com/controller/Hello.java
 */

/*
С помощью параметра -d здесь указываем, что скомпилированный модуль будет помещаться в каталог appmodules/operations
(если по умолчанию такого каталога нет, то он будет автоматически будет создан).
 */

/*
Параметр -p представляет сокращение от --module-path и указывает, где искать используемые модули.
То есть в данном случае это папка appmodules, куда ранее был помещен скомпилированный модуль operations.

Параметр -d, указывает, куда будет помещаться скомпилированный модуль - то есть в папку appmodules/open.
 */

/*
Параметр  --module-path указывает на путь к модулю,
          --module      указывает на главный класс модуля.
При наборе команды вместо параметра --module-path можно указать его сокращение -p,
                 а вместо параметра --module                      - сокращение -m.
 */

/*
C:\Users\*userName*E\IdeaProjects>  java -p appmodules -m open/core.com.controller.Hello
 */