package chapter09_Concurrency;

import java.util.concurrent.CountDownLatch;

public class P9_6_a2_Synchroniser_CountDownLatch {
    // Количество всадников
    private static final int RIDERS_COUNT = 5;
    // Объект синхронизации
    private static CountDownLatch latch;
    // Условная длина трассы
    private static final int TRACK_LENGTH = 3000;

    public static class Rider implements Runnable {
        private int riderNumber; // номер всадника
        private int riderSpeed ; // скорость всадника постоянная

        public Rider(int riderNumber, int riderSpeed) {
            this.riderNumber = riderNumber;
            this.riderSpeed  = riderSpeed;
        }

        @Override
        public void run() {
            try {
                System.out.printf("Всадник %d вышел к стартовой прямой\n", riderNumber);
                //  Уменьшаем счетчик на 1
                latch.countDown();

                // Метод await блокирует поток до тех пор, пока счетчик
                // CountDownLatch не обнулится
                latch.await();
                // Ожидание, пока всадник не преодолеет трассу
                Thread.sleep(TRACK_LENGTH / riderSpeed * 10);
                System.out.printf("Всадник %d финишировал\n", riderNumber);
            } catch (InterruptedException e) {
            }
        }
    }
    public static P9_6_a2_Synchroniser_CountDownLatch.Rider createRider(final int number) {
        return new P9_6_a2_Synchroniser_CountDownLatch.Rider(number, (int) (Math.random() * 10 + 5));
    }

    public static void main(String[] args) throws InterruptedException {
        // Определение объекта синхронизации
        latch = new CountDownLatch(RIDERS_COUNT + 3);
        // Создание потоков всадников
        for (int i = 1; i <= RIDERS_COUNT; i++) {
            new Thread(createRider(i)).start(); // new Rider(i, (int) (Math.random() * 10 + 5))).start();
            Thread.sleep(1000);
        }

        // Проверка наличия всех всадников на старте
        while (latch.getCount() > 3)
            Thread.sleep(100);

        Thread.sleep(1000);
        System.out.println("На старт!");
        latch.countDown();  // Уменьшаем счетчик на 1
        Thread.sleep(1000);
        System.out.println("Внимание!");
        latch.countDown(); // Уменьшаем счетчик на 1
        Thread.sleep(1000);
        System.out.println("Марш!");
        latch.countDown(); // Уменьшаем счетчик на 1

        // Счетчик обнулен, и все ожидающие этого события потоки разблокированы
    }
}


//--- Объект синхронизации CountDownLatch

/*Объект синхронизации потоков CountDownLatch представляет собой «защелку с обратным отсчетом» :
несколько потоков, выполняя определенный код, блокируются до тех пор, пока не будут выполнены заданные условия.

Количество условий определяются счетчиком.

Как только счетчик обнулится, т.е. будут выполнены все условия,
самоблокировки выполняемых потоков снимаются, и они продолжают выполнение кода.

Таким образом, CountDownLatch также, как и Semaphore, работает со счетчиком,
обнуление которого снимает самоблокировки выполняемых потоков.
 */

/* CountDownLatch constructors // конструкторы
        CountDownLatch(int number)      number определяет количество событий,
                                        которые должны произойти до того момента, когда будет снята самоблокировка.
*/

/*
CountDownLatch имеет два перегруженных метода await для самоблокировки :

    void await() throws InterruptedException;
    boolean await(long wait, TimeUnit unit) throws InterruptedException;

В первом методе ожидание длится до тех пор, пока счетчик CountDownLatch не достигнет нуля.
А во втором методе ожидание длится только в течение определенного периода времени, определяемого параметром ожидание wait.
 */

/*
Метод уменьшения счетчика countDown     - чтобы уменьшить счетчик объекта CountDownLatch
    void countDown();
 */

/*
Примером CountDownLatch может служить паром, собирающий определенное количество транспорта и пассажиров,
или экскурсовод, собирающий группу из заданного количества туристов.
 */
