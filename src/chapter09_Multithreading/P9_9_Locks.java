package chapter09_Multithreading;

import java.util.concurrent.locks.ReentrantLock;

public class P9_9_Locks {
    public static void main(String[] args) {

        CommonResourceLock commonResource= new CommonResourceLock();
        ReentrantLock locker = new ReentrantLock(); // создаем заглушку
        for (int i = 1; i < 6; i++){

            Thread t = new Thread(new CountThreadLock(commonResource, locker));
            t.setName("Thread "+ i);
            t.start();
        }
    }
}

class CommonResourceLock{

    int x=0;
}

class CountThreadLock implements Runnable{

    CommonResourceLock res;
    ReentrantLock locker;
    CountThreadLock(CommonResourceLock res, ReentrantLock lock){
        this.res=res;
        locker = lock;
    }
    public void run(){

        locker.lock();      // устанавливаем блокировку
        /*
        После этого только один поток имеет доступ к критической секции,
        а остальные потоки ожидают снятия блокировки.
         */
        try{
            res.x=1;
            for (int i = 1; i < 5; i++){
                System.out.printf("%s %d \n", Thread.currentThread().getName(), res.x);
                res.x++;
                Thread.sleep(100);
            }
        }
        catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
        /*
        В блоке finally после всей окончания основной работы потока эта блокировка снимается.
        Причем делается это обязательно в блоке finally, так как в случае возникновения ошибки все остальные потоки окажутся заблокированными.
         */
        finally{
            locker.unlock(); // снимаем блокировку
        }
    }
}
/*
Для управления доступом к общему ресурсу в качестве альтернативы оператору synchronized можно использовать блокировки.
Функциональность блокировок заключена в пакете java.util.concurrent.locks.

1. Вначале поток пытается получить доступ к общему ресурсу.
2. Если он свободен, то на него накладывает блокировку.
3. После завершения работы блокировка с общего ресурса снимается.
4. Если же ресурс не свободен и на него уже наложена блокировка, то поток ожидает, пока эта блокировка не будет снята.
 */

/*
Классы блокировок реализуют интерфейс Lock, который определяет следующие методы:
    void lock():                                            ожидает, пока не будет получена блокировка
    void lockInterruptibly() throws InterruptedException:   ожидает, пока не будет получена блокировка, если поток не прерван
    boolean tryLock():                                      пытается получить блокировку, если блокировка получена, то возвращает true.
                                                    Если блокировка не получена, то возвращает false.
                                                    В отличие от метода lock() не ожидает получения блокировки,
                                                    если она недоступна
    void unlock():                                          снимает блокировку
    Condition newCondition():                               возвращает объект Condition, который связан с текущей блокировкой
 */

/*
Организация блокировки в общем случае довольно проста: для получения блокировки вызывается метод lock(),
а после окончания работы с общими ресурсами вызывается метод unlock(), который снимает блокировку.

Объект Condition позволяет управлять блокировкой.

Как правило, для работы с блокировками используется класс ReentrantLock из пакета java.util.concurrent.locks.
Данный класс реализует интерфейс Lock.
 */
