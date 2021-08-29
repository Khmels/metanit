package chapter9;

import java.util.concurrent.Callable;

public class P9_2_CreationAndExecutionOfThreads {

    // Для создания нового потока можно создать новый класс, либо наследуя его от класса Thread,
    //                                                          либо реализуя в классе интерфейс Runnable.

    public static void main(String[] args) {

        //--- Наследование от класса Thread ---------------------

        System.out.println("Main thread started...");

        // В методе main для запуска потока JThread у него вызывается метод start(),
        // после чего начинается выполнение того кода, который определен в методе run:

        // По сути этот метод как раз и вызывает переопределенный метод run() класса NewThread

        new NewThread("NewThread").start();
        System.out.println("Main thread finished...");
        // Обратите внимание, что главный поток завершает работу раньше, чем порожденный им дочерний поток NewThread
        System.out.println("---------------------");


        //Аналогично созданию одного потока можоно запускать сразу несколько потоков:
        System.out.println("Main thread started...");
        for(int i=1; i < 6; i++)
            new NewThread("NewThreadIndependent " + i).start();
        System.out.println("Main thread finished...");
        System.out.println("---------------------");


        //--- Ожидание завершения потока
        // чтоб Main thread завершается самым последним:
        // Для этого надо применить метод join().
        // В этом случае текущий поток будет ожидать завершения потока, для которого вызван метод join:
        System.out.println("---------------------");
        System.out.println("Main thread started...");
        NewThread t= new NewThread("NewThreadJoined ");
        t.start();
        /// Unhandled exception: java.lang.InterruptedException

        try {
            t.join();
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted", t.getName());
        }

        System.out.println("Main thread finished...");
        System.out.println("---------------------");
        /*
        Метод join() заставляет вызвавший поток (в данном случае Main thread)
        ожидать завершения вызываемого потока, для которого и применяется метод join (в данном случае JThread).
         */

        /*
        Если в программе используется несколько дочерних потоков,
        и надо, чтобы Main thread завершался после дочерних, то для каждого дочернего потока надо вызвать метод join.
         */

        //--- Реализация интерфейса Runnable ---------------------

        System.out.println("Main thread started...");
        Thread myThread = new Thread(new MyThread(),"MyThreadRunnable");
        myThread.start();
        System.out.println("Main thread finished...");

        // Реализация интерфейса Runnable во многом аналогична переопределению класса Thread.
        // Также в методе run определяется простейший код, который усыпляет поток на 1000 миллисекунд.

        // Поскольку Runnable фактически представляет функциональный интерфейс, который определяет один метод,
        // то объект этого интерфейса можно представить в виде лямбда-выражения:
        System.out.println("Main thread started...");
        Runnable r = ()->{
            System.out.printf("%s started... \n", Thread.currentThread().getName());
            try{
                Thread.sleep(500);
            }
            catch(InterruptedException e){
                System.out.println("Thread has been interrupted");
            }
            System.out.printf("%s finished... \n", Thread.currentThread().getName());
        };

        Thread myThreadFunc = new Thread(r,"MyThread");
        myThreadFunc.start();
        System.out.println("Main thread finished...");

        //--- Через интерфейс Collable<t>  ---------------------
        // похож на Runnable, но есть нюансы (см. javadoc).

        /*
        Вызываемый интерфейс похож на Runnable, поскольку оба они предназначены для классов,
        экземпляры которых потенциально выполняются другим потоком.

        Runnable, однако, не возвращает результат и не может выдать проверенное исключение.
         */

        /*
        A Callable должен реализовать метод call() , в то время как a Runnable должен реализовать метод run() .
        Callable может возвращать значение, но Runnable не может.
        Callable может вызвать проверенное исключение, но Runnable не может.
        Callable можно использовать с ExecutorService#invokeXXX(Collection<? extends Callable<T>> tasks) методами, но Runnable не может быть.
         */

        /*
        интерфейс Runnable не может делать все, что делает Callable.
        Runnable существует с Java 1.0, но Callable был введен только в Java 1.5 для обработки случаев использования,
        которые Runnable не поддерживает
         */

    }
}

//--- Наследование от класса Thread ----------
class NewThread extends Thread {

    //  В конструктор своего класса потока можно передать различные данные,
    //  но главное, чтобы в нем вызывался конструктор базового класса Thread, в который передается имя потока.
    NewThread(String name){
        super(name);
    }

    /*
    в NewThread переопределяется метод run(), код которого собственно и будет представлять весь тот код, который выполняется в потоке.
    В методе main для запуска потока JThread у него вызывается метод start(), после чего начинается выполнение того кода, который определен в методе run:
     */
    @Override
    public void run(){

        System.out.printf("%s started... \n", Thread.currentThread().getName());
        try{
            Thread.sleep(500);
        }
        catch(InterruptedException e){
            System.out.println("Thread has been interrupted");
        }
        System.out.printf("%s finished... \n", Thread.currentThread().getName());
    }
}

//'java doc'
//### interface Runnable{
//
//    void run();
//}

//В методе run() собственно определяется весь тот код, который выполняется при запуске потока.

// После определения объекта Runnable он передается в один из конструкторов класса Thread:
// Thread(Runnable runnable, String threadName)



//--- Реализация интерфейса Runnable ----------
class MyThread implements Runnable {

    @Override
    public void run(){

        System.out.printf("%s started... \n", Thread.currentThread().getName());
        try{
            System.out.printf("\t Maybe, I should sleep");
            System.out.println();
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            System.out.println("Thread has been interrupted");
        }
        System.out.printf("%s finished... \n", Thread.currentThread().getName());
    }
}

//--- Реализация интерфейса Callable ----------
class MyThreadCall implements Callable<String>{

    @Override
    public String call() throws Exception {
        return null;
    }
}

/*
public interface Runnable {
    void run();
}

public interface Callable<V> {
    V call() throws Exception;
}
 */
