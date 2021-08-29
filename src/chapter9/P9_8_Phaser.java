package chapter9;

import org.w3c.dom.ls.LSOutput;

import java.util.concurrent.Phaser;

public class P9_8_Phaser {
    public static void main(String[] args) {

        // фазы выполняются тремя сторонами - главным потоком и двумя потоками PhaseThread.
        // Поэтому при создании объекта Phaser ему передается число 1 - главный поток, а в конструкторе PhaseThread вызывается метод register()
        Phaser phaser = new Phaser(1);
        new Thread(new PhaseThread(phaser, "PhaseThread 1")).start();
        new Thread(new PhaseThread(phaser, "PhaseThread 2")).start();

        // в принципе можно не использовать метод register, но тогда надо было бы указать
        // Phaser phaser = new Phaser(3), так как три стороны.

        // Фаза в каждой стороне представляет минимальный примитивный набор действий:
        // для потоков PhaseThread это вывод сообщения,
        // а для главного потока - подсчет текущей фазы с помощью метода getPhase().


        // при этом отсчет фаз начинается с нуля.
        // ждем завершения фазы 0
        int phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        // Каждая сторона завершает выполнение фазы вызовом метода phaser.arriveAndAwaitAdvance().
        // При вызове этого метода пока последняя сторона не завершит выполнение текущей фазы, все остальные стороны блокируются.
        System.out.println("Фаза " + phase + " завершена");
        try {
            Thread.sleep(500);
        }catch (InterruptedException exception){
            System.out.println("Something went wrong");
        }
        // ждем завершения фазы 1
        phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Фаза " + phase + " завершена");

        // ждем завершения фазы 2
        phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Фаза " + phase + " завершена");

        // После завершения выполнения последней фазы
        // происходит отмена регистрации всех сторон с помощью метода arriveAndDeregister().
        phaser.arriveAndDeregister();
    }
}

class PhaseThread implements Runnable{

    Phaser phaser;
    String name;

    PhaseThread(Phaser p, String n){

        this.phaser=p;
        this.name=n;
        phaser.register();
    }

    @Override
    public void run(){

        System.out.println(name + " выполняет фазу " + phaser.getPhase());
        try{
            Thread.sleep(500);
        }
        catch(InterruptedException ex){
            System.out.println(ex.getMessage());
        }
        phaser.arriveAndAwaitAdvance(); // сообщаем, что первая фаза достигнута (0)

        System.out.println(name + " выполняет фазу " + phaser.getPhase());
        try{
            Thread.sleep(500);
        }
        catch(InterruptedException ex){
            System.out.println(ex.getMessage());
        }
        phaser.arriveAndAwaitAdvance(); // сообщаем, что вторая фаза достигнута (1)

        System.out.println(name + " выполняет фазу " + phaser.getPhase());
        try{
            Thread.sleep(500);
        }
        catch(InterruptedException ex){
            System.out.println(ex.getMessage());
        }
        phaser.arriveAndDeregister(); // сообщаем о завершении фаз и удаляем с регистрации объекты (2)
    }
}



/*
Класс Phaser позволяет синхронизировать потоки, представляющие отдельную фазу или стадию выполнения общего действия.
Phaser определяет объект синхронизации, который ждет, пока не завершится определенная фаза.

Затем Phaser переходит к следующей стадии или фазе и снова ожидает ее завершения.
 */

/* Phaser constructors // конструкторы
        Phaser()                            создает объект Phaser без каких-либо сторон
        Phaser(int parties)                 parties указывает на количество сторон (потоков), которые должны выполнять все фазы действия.
        Phaser(Phaser parent)               устанавливают родительский объект Phaser
        Phaser(Phaser parent, int parties)  устанавливают родительский объект Phaser и количество сторон
*/

/*
Основные методы класса Phaser:

    int register():                 регистрирует сторону, которая выполняет фазы, и возвращает номер текущей фазы - обычно фаза 0
    int arrive():                   сообщает, что сторона завершила фазу и возвращает номер текущей фазы
    int arriveAndAwaitAdvance():    аналогичен методу arrive, только при этом заставляет phaser ожидать завершения фазы всеми остальными сторонами
    int arriveAndDeregister():      сообщает о завершении всех фаз стороной и снимает ее с регистрации.
                                        Возвращает номер текущей фазы или отрицательное число,
                                        если синхронизатор Phaser завершил свою работу
    int getPhase():                 возвращает номер текущей фазы
 */

/*
При работае с классом Phaser обычно сначала создается его объект.
Далее нам надо зарегистрировать все участвующие стороны.

Для регистрации в каждой стороне вызывается метод register(),
либо можно обойтись и без этого метода, передав нужное количество сторон в конструктор Phaser.

Затем каждая сторона выполняет некоторый набор действий, составляющих фазу.
А синхронизатор Phaser ждет, пока все стороны не завершат выполнение фазы.

Чтобы сообщить синхронизатору, что фаза завершена, сторона должна вызвать метод arrive() или arriveAndAwaitAdvance().
После этого синхронизатор переходит к следующей фазе.
 */


/*
Возможно, сообщения о выполнении фазы 1 выводится после сообщения об окончании фазы 0.
Что связано с многопоточностью - фазы завершились, но в одном потоке еще не выведено сообщение о завершении,
тогда как другие потоки уже начали выполнение следующей фазы. В любом случае все это происходит уже после завершения фазы.
 */
