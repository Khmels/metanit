package chapter3;

public class P11_CallbackUsingInterfaces {
    // Суть обратного вызова состоит в том, что мы создаем действия, которые вызываются при других действиях.
    // То есть одни действия вызываются другими действиями.

    // Интерфейсы в данном качестве особенно широко используются в различных графических API - AWT, Swing, JavaFX,
    // где обработка событий объектов - элементов графического интерфейса особенно актуальна.

}

class EventsApp {
    public static void main(String[] args) {

        // через конструктор устанавливается обработчик нажатия кнопки.
        // И при каждом вызове метода button.click() будет вызываться этот обработчик.
        Button button = new Button(new ButtonClickHandler());
        button.click();
        button.click();
        button.click();

        Button button1 = new Button(new ButtonRightClick());
        button1.click();
        button1.click();
        button1.click();

        // Вместо того, чтобы создавать отдельные классы, реализующие интерфейс EventHandler,
        // обработчики задаются в виде анонимных объектов, которые реализуют интерфейс EventHandler.
        Button tvButton = new Button(new EventHandler(){

            private boolean on = false;
            public void execute(){

                if(on) {
                    System.out.println("Телевизор выключен..");
                    on=false;
                }
                else {
                    System.out.println("Телевизор включен!");
                    on=true;
                }
            }
        });

        // создавать отдельные классы, реализующие интерфейс EventHandler,
        Button printButton = new Button(new PrintHandler());

        tvButton.click();
        printButton.click();
        tvButton.click();

    }

}

interface EventHandler{

    void execute();
}

    // реализация EventHandler в виде класса ButtonClickHandler.
    // И в основной программе объект этого класса передается в конструктор Button.

class ButtonClickHandler implements EventHandler{

    @Override
    public void execute() {
        System.out.println("Кнопка нажата");
    }
}

class ButtonRightClick implements EventHandler{

    @Override
    public void execute() {
        System.out.println("Вызвано контекстное меню");
    }
}

    // класс Button, который в конструкторе принимает объект интерфейса EventHandler
    // и в методе click (имитация нажатия) вызывает метод execute этого объекта

class Button{

    EventHandler handler;

    Button(EventHandler action) {
        this.handler = action;
    }

    public void click(){

        handler.execute();
    }
}

// почему не так?
/*
class Button{

    public void click(){

        System.out.println("Кнопка нажата!");
    }
}
//
на момент определения класса не всегда бывают точно известны те действия, которые должны производиться.
Особенно если класс Button и класс основной программы находятся в разных пакетах, библиотеках.
К тому же может быть несколько кнопок - объектов Button и для каждого объекта надо определить свое действие.
 */

class PrintHandler implements EventHandler{
    @Override
    public void execute() {
        System.out.println("Запущена печать на принтере...");
    }
}



