package chapter11.demo.example.demo;
import javax.swing.JOptionPane;

public class Hello {
    public static void main(String[] args) {
        //System.out.println("Hello modules!");
        JOptionPane.showMessageDialog(null, "Hello Demo Module!");
        // Package 'javax.swing' is declared in module 'java.desktop', but module 'metanit' does not read it
        // Add 'requires java.desktop` directive to module-info.java
    }
}

/*
Для вывода сообщения используется статический метод showMessageDialog() класса JOptionPane,
который расположен в пакете javax.swing.JOptionPane.

Чтобы использовать данный класс, он импортируется в начале файла.
Однако поскольку мы используем данный класс в отдельном модуле,
то просто импортировать класс или целый пакет недостаточно.

Надо указать нашему модулю, чтобы он использовал модуль, где определен данный класс.
 */

/*
Как узнать, в каком модуле расположен тот или иной класс? Если речь идет о встроенных классах,
то для этого надо смотреть документацию, причем сразу по Java (JDK) 9+.
В начале описания класса, как правило, указывается, к какому модулю он принадлежит.
 */

/*
документация нам сообщает, что класс JOptionPane расположен в модуле java.desktop.
Теперь укажем нашему модулю, что нам надо использовать модуль java.desktop.
 */

/*
После оператора requires указывается название модуля, от которого зависит наш модуль.
Соответственно если наш модуль использует несколько других модулей,
то также с помощью оператора require можно указать все используемые модули.
 */