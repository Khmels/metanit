package chapter09_Concurrency;

import java.util.concurrent.CyclicBarrier;

public class P9_6_a3_Synchroniser_CyclicBarrier {
    private static CyclicBarrier ferryBarrier;
    private static final int FERRY_BOAT_SIZE = 3;

    // Переправляющий автомобили паром
    public static class FerryBoat implements Runnable {
        @Override
        public void run() {
            try {
                // Задержка на переправе
                System.out.println("\t\nЗагрузка автомобилей");
                Thread.sleep(500);
                System.out.println("\tПаром переправил автомобили");
            } catch (InterruptedException e) {}
        }
    }

    // Класс автомобиля
    public static class Car implements Runnable {
        private int carNumber;

        public Car(int carNumber) {
            this.carNumber = carNumber;
        }

        @Override
        public void run() {
            try {
                System.out.printf("К переправе подъехал автомобиль %d\n", carNumber);
                // Вызов метода await при подходе к барьеру;
                // поток блокируется в ожидании прихода остальных потоков
                ferryBarrier.await();
                System.out.printf("\t\t\tАвтомобиль %d продолжил движение\n", carNumber);
            } catch (Exception e) {}
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ferryBarrier = new CyclicBarrier(FERRY_BOAT_SIZE, new P9_6_a3_Synchroniser_CyclicBarrier.FerryBoat());

        for (int i = 1; i < 10; i++) {
            new Thread(new P9_6_a3_Synchroniser_CyclicBarrier.Car(i)).start();
            Thread.sleep(400);
        }
    }
}

//--- Объект синхронизации CyclicBarrier

/*
Объект синхронизации CyclicBarrier представляет собой барьерную синхронизацию, используемую, как правило, в распределенных вычислениях.

Особенно эффективно использование барьеров при циклических расчетах.
При барьерной синхронизации алгоритм расчета делят на несколько потоков.
С помощью барьера организуют точку сбора частичных результатов вычислений, в которой подводится итог этапа вычислений.

В исходном коде барьер для группы потоков означает,
что каждый поток должен остановиться в определенном месте и ожидать прихода остальных потоков группы.

Как только все потоки достигнут барьера, их выполнение продолжится.
 */

/* CyclicBarrier constructors // конструкторы

        CyclicBarrier(int count)                    количество потоков, которые должны достигнуть барьера,
                                                        чтобы после этого одновременно продолжить выполнение кода.
        CyclicBarrier(int count, Runnable class);   дополнительно задается реализующий интерфейс Runnable класс,
                                                        который должен быть запущен после прихода к барьеру всех потоков
                                                        Поток запускать самостоятельно НЕ НУЖНО. CyclicBarrier это делает автоматически.
*/

/*
Для указания потоку о достижении барьера нужно вызвать один из перегруженных методов await :

        void await() throws InterruptedException    ожидание длится до тех пор, пока счетчик CountDownLatch не достигнет нуля.

        boolean await(long wait, TimeUnit unit) throws InterruptedException
                                ожидание длится только в течение определенного периода времени, определяемого параметром ожидание wait.
                                время ожидания указывается в единицах unit объекта перечисления TimeUnit, определяюший временно́е разбиение.
*/

/*
Циклический барьер CyclicBarrier похож на CountDownLatch.
Главное различие между ними связано с тем, что «защелку» нельзя использовать повторно после того, как её счётчик обнулится,
а барьер можно использовать (в цикле).

С точки зрения API циклический барьер CyclicBarrier
    - имеет только метод самоблокировки await
    - не имеет метода декрементации счетчика,
    - также позволяет подключить и автоматически запускать
      дополнительный потоковый класс при достижении барьера всех исполняемых потоков.
 */
