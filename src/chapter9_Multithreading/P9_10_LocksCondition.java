package chapter9_Multithreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class P9_10_LocksCondition {public static void main(String[] args) {

    StoreLock store = new StoreLock();
    ProducerLock producer = new ProducerLock(store);
    ConsumerLock consumer = new ConsumerLock(store);
    new Thread(producer).start();
    new Thread(consumer).start();
}
}
// Класс Магазин, хранящий произведенные товары
class StoreLock{
    private int product=0;
    ReentrantLock locker;
    Condition condition;

    StoreLock(){
        locker = new ReentrantLock();       // создается блокировка
        condition = locker.newCondition();  // условие, связанное с блокировкой
    }

    public void get() {

        locker.lock();
        try{
            // пока нет доступных товаров на складе, ожидаем
            while (product<1)
                condition.await();

            product--;
            System.out.println("\tПокупатель купил 1 товар");
            System.out.println("\tТоваров на складе: " + product);
            System.out.println("\t---------------------");

            // сигнализируем
            condition.signalAll();
        }
        catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        finally{
            locker.unlock();
        }
    }
    public void put() {

        locker.lock();
        try{
            // пока на складе 3 товара, ждем освобождения места
            while (product>=3)
                condition.await();

            product++;
            System.out.println("Производитель добавил 1 товар");
            System.out.println("Товаров на складе: " + product);
            System.out.println("---------------------");
            // сигнализируем
            condition.signalAll();
        }
        catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        finally{
            locker.unlock();
        }
    }
}
// класс Производитель
class ProducerLock implements Runnable{

    StoreLock store;
    ProducerLock(StoreLock store){
        this.store=store;
    }
    public void run(){
        for (int i = 1; i < 6; i++) {
            store.put();
        }
    }
}
// Класс Потребитель
class ConsumerLock implements Runnable{

    StoreLock store;
    ConsumerLock(StoreLock store){
        this.store=store;
    }
    public void run(){
        for (int i = 1; i < 6; i++) {
            store.get();
        }
    }
}

/*
Применение условий в блокировках позволяет добиться контроля над управлением доступом к потокам.
Условие блокировки представлет собой объект интерфейса Condition из пакета java.util.concurrent.locks.

Применение объектов Condition во многом аналогично использованию методов wait/notify/notifyAll класса Object.
В частности, можно использовать следующие методы интерфейса Condition:
    await:      поток ожидает, пока не будет выполнено некоторое условие и пока другой поток не вызовет методы signal/signalAll.
                    Во многом аналогичен методу wait класса Object
    signal:     сигнализирует, что поток, у которого ранее был вызван метод await(), может продолжить работу.
                    Применение аналогично использованию методу notify класса Object
    signalAll:  сигнализирует всем потокам, у которых ранее был вызван метод await(), что они могут продолжить работу.
                    Аналогичен методу notifyAll() класса Object
 */

/*
Эти методы вызываются из блока кода, который попадает под действие блокировки ReentrantLock.
Сначала, используя эту блокировку, надо получить объект Condition:

        ReentrantLock locker = new ReentrantLock();
        Condition condition = locker.newCondition();

Проверяется условие доступа. Если соблюдается условие, то поток ожидает, пока условие не изменится:
        while (условие)
            condition.await();

После выполнения всех действий другим потокам подается сигнал об изменении условия:
        condition.signalAll();
Важно в конце вызвать метод signal/signalAll, чтобы избежать возможности взаимоблокировки потоков.
 */