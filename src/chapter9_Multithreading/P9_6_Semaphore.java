package chapter9_Multithreading;

import java.util.concurrent.Semaphore;

public class P9_6_Semaphore {
    /*
    В Java семафоры представлены классом Semaphore, который располагается в пакете java.util.concurrent.
     */
    public static void main(String[] args) {

        Semaphore sem = new Semaphore(1); // 1 разрешение
        CommonResourceSemaphore res = new CommonResourceSemaphore();
        // Потоки представлены классом CountThread, который получает семафор и выполняет некоторые действия над ресурсом.
        new Thread(new CountThreadSemaphore(res, sem, "CountThread 1")).start();
        new Thread(new CountThreadSemaphore(res, sem, "CountThread 2")).start();
        new Thread(new CountThreadSemaphore(res, sem, "CountThread 3")).start();

        //Семафоры отлично подходят для решения задач, где надо ограничивать доступ.


    }

}
class CommonResourceSemaphore{

    int x=0;
}

class CountThreadSemaphore implements Runnable{

    CommonResourceSemaphore res;
    Semaphore sem;
    String name;
    CountThreadSemaphore(CommonResourceSemaphore res, Semaphore sem, String name){
        this.res=res;
        this.sem=sem;
        this.name=name;
    }

    public void run(){

        try{
            System.out.println(name + " ожидает разрешение");
            sem.acquire();
            res.x=1;
            for (int i = 1; i < 5; i++){
                System.out.println(this.name + ": " + res.x);
                res.x++;
                Thread.sleep(100);
            }
        }
        catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
        System.out.println(name + " освобождает разрешение");
        sem.release();
    }
}

/*
Для управления доступом к ресурсу семафор использует счетчик, представляющий количество разрешений.
Если значение счетчика больше нуля, то поток получает доступ к ресурсу, при этом счетчик уменьшается на единицу.

После окончания работы с ресурсом поток освобождает семафор, и счетчик увеличивается на единицу.
Если же счетчик равен нулю, то поток блокируется и ждет, пока не получит разрешение от семафора.
 */

/*
Установить количество разрешений для доступа к ресурсу можно с помощью конструкторов класса Semaphore:
        Semaphore(int permits)                  указывает на количество допустимых разрешений для доступа к ресурсу.
        Semaphore(int permits, boolean fair)    Параметр fair во втором конструкторе позволяет установить очередность получения доступа.

                                                Если он равен true, то разрешения будут предоставляться ожидающим потокам в том порядке,
                                                в каком они запрашивали доступ.

                                                Если же он равен false, то разрешения будут предоставляться в неопределенном порядке.
 */

/*
Для получения разрешения у семафора надо вызвать метод acquire(), который имеет две формы:
        void acquire() throws InterruptedException
        void acquire(int permits) throws InterruptedException
Для получения одного разрешения применяется первый вариант, а для получения нескольких разрешений - второй вариант.
После вызова этого метода пока поток не получит разрешение, он блокируется.
 */

/*
После окончания работы с ресурсом полученное ранее разрешение надо освободить с помощью метода release():
        void release()
        void release(int permits)
Первый вариант метода освобождает одно разрешение, а второй вариант - количество разрешений, указанных в permits.
 */


