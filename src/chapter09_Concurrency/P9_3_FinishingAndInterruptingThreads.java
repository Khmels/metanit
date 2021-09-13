package chapter09_Concurrency;

public class P9_3_FinishingAndInterruptingThreads {
    public static void main(String[] args) {

        //--- Завершение потока ---

        System.out.println("Main thread started...");
        MyThreadCloseableRunnable myThread = new MyThreadCloseableRunnable();
        Thread tR = new Thread(myThread,"MyThreadCloseable");
        tR.start();
        //new Thread(myThread,"MyThread").start();

        /*
        вначале запускается дочерний поток: new Thread(myThread,"MyThread").start().
        Затем на 1100 миллисекунд останавливаем Main thread
        и потом вызываем метод myThread.disable(), который переключает в потоке флаг isActive. И дочерний поток завершается.
        */
        try{
            Thread.sleep(1100);
            myThread.disable();
            tR.join();
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            System.out.println("Thread has been interrupted");
        }
        System.out.println("Main thread finished...");
        System.out.println("---------------------");

        //--- Метод interrupt ---
        /*
        Еще один способ вызова завершения или прерывания потока представляет метод interrupt().
        Вызов этого метода устанавливает у потока статус, что он прерван.

        Сам метод возвращает true, если поток может быть прерван, в ином случае возвращается false.
         */

        //  При этом сам вызов этого метода НЕ завершает поток, он только устанавливает статус:
        //  в частности, метод isInterrupted() класса Thread будет возвращать значение true.
        //
        //  Можно проверить значение возвращаемое данным методом и прозвести некоторые действия.

        System.out.println("Main thread started...");
        ThreadInterruptAble t1 = new ThreadInterruptAble("ThreadInterruptAble");
        t1.start();
        try{
            Thread.sleep(10);
            t1.interrupt();
            Thread.sleep(50);
        }
        catch(InterruptedException e){
            System.out.println("Thread has been interrupted");
        }
        System.out.println("Main thread finished...");
        System.out.println("---------------------");

        /*
        В классе, который унаследован от Thread, можно получить статус текущего потока с помощью метода isInterrupted().
        И пока этот метод возвращает false, можно выполнять цикл.

        А после того, как будет вызван метод interrupt, isInterrupted() возвратит true, и соответственно произойдет выход из цикла.
         */

        //---  Runnable .isInterrupted()

        /*  Если основная функциональность заключена в классе, который реализует интерфейс Runnable,
            то там можно проверять статус потока с помощью метода Thread.currentThread().isInterrupted():
        */

        System.out.println("Main thread started...");
        ThreadRunnable myThreadRunnable = new ThreadRunnable();
        Thread tR2 = new Thread(myThreadRunnable,"MyThreadThreadRunnable");
        tR2.start();
        try{
            Thread.sleep(10);
            tR2.interrupt();

            Thread.sleep(50);
        }
        catch(InterruptedException e){
            System.out.println("Thread has been interrupted");
        }
        System.out.println("Main thread finished...");
        System.out.println("---------------------");

        /*
        Однако при получении статуса потока с помощью метода isInterrupted() следует учитывать,
        что если мы обрабатываем в цикле исключение InterruptedException в блоке catch,
        то при перехвате исключения статус потока автоматически сбрасывается, и после этого isInterrupted будет возвращать false.
         */

        ThreadInterruptAbleException t2 = new ThreadInterruptAbleException("ThreadInterruptAble");
        t2.start();
        try{
            Thread.sleep(100);
            t2.interrupt();
            t2.join();
            Thread.sleep(50);
        } catch (InterruptedException ex){
            System.out.println("Thread has been interrupted");
        }
        System.out.println("---------------------");

        ThreadInterruptAbleWhileException t3 = new ThreadInterruptAbleWhileException();
        // Thread-0 будет вместо имени
        t3.start();
        System.out.println(t3.getName());
        try{
            Thread.sleep(100);
            t3.interrupt();
            t3.join();
            Thread.sleep(50);
        } catch (InterruptedException ex){
            System.out.println("Thread has been interrupted");
        }
    }
}

/*
Примеры потоков ранее представляли поток как последовательный набор операций.
После выполнения последней операции завершался и поток.

Однако нередко имеет место и другая организация потока в виде бесконечного цикла.
Например, поток сервера в бесконечном цикле прослушивает определенный порт на предмет получения данных.
И в этом случае также можно предусмотреть механизм завершения потока.
 */

class MyThreadCloseableRunnable implements Runnable {

    // isActive указывает на активность потока.
    private boolean isActive;

    // c помощью метода disable() можно сбросить состояние этой переменной.
    void disable(){
        isActive=false;
    }

    MyThreadCloseableRunnable(){
        isActive = true;
    }

    @Override
    public void run(){

        System.out.printf("%s started... \n", Thread.currentThread().getName());
        int counter=1; // счетчик циклов
        while(isActive){
            System.out.println("Loop " + counter++);
            try{
                Thread.sleep(1500);
            }
            catch(InterruptedException e){
                System.out.println("Thread has been interrupted");
            }
        }
        System.out.printf("%s finished... \n", Thread.currentThread().getName());
    }
}


class ThreadInterruptAble extends Thread {

    ThreadInterruptAble(String name){
        super(name);
    }

    @Override
    public void run(){

        System.out.printf("%s started... \n", Thread.currentThread().getName());
        int counter=1; // счетчик циклов
        while(!isInterrupted()){

            System.out.println("Loop " + counter++);
        }
        System.out.printf("%s finished... \n", Thread.currentThread().getName());
    }
}

class ThreadRunnable implements Runnable {

    public void run(){

        System.out.printf("%s started... \n", Thread.currentThread().getName());
        int counter=1; // счетчик циклов
        while(!Thread.currentThread().isInterrupted()){

            System.out.println("Loop " + counter--);
        }
        System.out.printf("%s finished... \n", Thread.currentThread().getName());
    }
}

class ThreadInterruptAbleException extends Thread{

    ThreadInterruptAbleException(String name){
        super(name);
    }

    @Override
    public void run() {
        System.out.printf("%s started... \n", Thread.currentThread().getName());
        int counter=1; // счетчик циклов
        while(!isInterrupted()){

            System.out.println("Loop " + counter++);
            try{
                Thread.sleep(100);
            }
            catch(InterruptedException e){
                System.out.println(getName() + " has been interrupted");
                System.out.println(isInterrupted());    // false
                //interrupt();      // повторно сбрасываем состояние - иначе продолжается выполнение
                break;              // либо выход из цикла

                /*
                Когда поток вызовет метод interrupt, метод sleep сгенерирует исключение InterruptedException,
                и управление перейдет к блоку catch. Но если мы проверим статус потока, то увидим, что метод isInterrupted возвращает false.

                Как вариант, в этом случае мы можем повторно прервать текущий поток, опять же вызвав метод interrupt().
                Тогда при новой итерации цикла while метода isInterrupted возвратит true, и поизойдет выход из цикла.
                 */
            }
        }


        System.out.printf("%s finished... \n", Thread.currentThread().getName());
    }
}

class ThreadInterruptAbleWhileException extends Thread{


//    ThreadInterruptAbleWhileException(String name) {
//        super(name);
//    }

    @Override
    public void run() {
        System.out.printf("%s started... \n", Thread.currentThread().getName());
        int counter=1; // счетчик циклов
        try{
            while(!isInterrupted()){
                System.out.println("Loop " + counter++);
                Thread.sleep(100);
            }
        }
        catch(InterruptedException e){
            System.out.println(getName() + " has been interrupted");
        }

        System.out.printf("%s finished... \n", Thread.currentThread().getName());
    }
}