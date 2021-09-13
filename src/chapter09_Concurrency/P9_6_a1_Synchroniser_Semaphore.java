package chapter09_Concurrency;
import java.util.concurrent.Semaphore;

public class P9_6_a1_Synchroniser_Semaphore {
    private static final int COUNT_CONTROL_PLACES = 5;
    private static final int COUNT_RIDERS = 7;
    // места контроля
    private static boolean[] CONTROL_PLACES = null;
    // Семафор
    private static Semaphore semaphore = null;

    public static class Rider implements Runnable {
        private int riderNum;

        public Rider(int riderNum) {
            this.riderNum = riderNum;
        }

        @Override
        public void run() {
            System.out.printf("Всадник %d подошел к зоне контроля\n", riderNum);
            try {
                // Запрос разрешения
                semaphore.acquire();

                System.out.printf("\tвсадник %d проверяет наличие свободного контроллера\n", riderNum);

                int controlNum = -1;

                // поиск свободное место и подходим к контроллеру
                synchronized (CONTROL_PLACES) {
                    for (int i = 0; i < COUNT_CONTROL_PLACES; i++)
                        // Есть ли свободные контроллеры?
                        if (CONTROL_PLACES[i]) {
                            // Занимаем место
                            CONTROL_PLACES[i] = false;
                            controlNum = i;
                            System.out.printf("\tвсадник %d подошел к контроллеру %d.\n", riderNum, i);
                            break;
                        }
                }

                // Время проверки лошади и всадника
                Thread.sleep((int) (Math.random() * 10 + 1) * 1000);

                // Освобождение места контроля
                synchronized (CONTROL_PLACES) {
                    CONTROL_PLACES[controlNum] = true;
                }

                // Освобождение ресурса
                semaphore.release();
                System.out.printf("\t\t-----Всадник %d завершил проверку\n", riderNum);
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Определяем количество мест контроля
        CONTROL_PLACES = new boolean[COUNT_CONTROL_PLACES];
        // Флаги мест контроля [true - свободно, false - занято]
        for (int i = 0; i < COUNT_CONTROL_PLACES; i++)
            CONTROL_PLACES[i] = true;

        /*
         *  Определяем семафор со следующими параметрами :
         *  - количество разрешений 5
         *  - флаг очередности fair=true, т.е. очередь first-in first-out
         */
        semaphore = new Semaphore(COUNT_CONTROL_PLACES, true);

        for (int i = 1; i <= COUNT_RIDERS; i++) {
            new Thread(new P9_6_a1_Synchroniser_Semaphore.Rider(i)).start();
            Thread.sleep(400);
        }
    }
}


//--- Объект синхронизации Semaphore
/*
Как было отмечено выше, Semaphore ограничивает доступ к определенному участку кода, иначе говоря, к общему ресурсу,
в качестве которого могут выступать программые/аппаратные ресурсы или файловая система.

Для управления доступом к общему ресурсу Semaphore использует счетчик.
Если значение счетчика больше нуля, то поток исполнения получает разрешение,
после чего значение счетчика семафора уменьшается на единицу.

При значении счетчика равным нулю очередному потоку исполнения в доступе будет отказано,
и он будет заблокирован до тех пор, пока не будут освобождены ресурсы.

Как только один из потоков исполнения освободит ресурсы,
т.е. завершит исполнение определенного участка кода, то значение счетчика семафора увеличивается на единицу.

Если в это время имеется ожидающий разрешения другой поток исполнения, то он сразу же его получает.
 */

/*
Метод получения разрешения acquire  - чтобы получить у семафора разрешение
    void acquire()           throws InterruptedException
    void acquire(int number) throws InterruptedException
 */

/*
Освобождение ресурса release        - чтобы освободить разрешение у семафора
    void release()
    void release(int number)
 */

/*
В примере несколько всадников с лошадьми должны пройти контроль перед скачками.
Количество контроллеров меньше количества всадников, поэтому некоторые всадники будут дожидаться, пока не освободиться один из контроллеров.
 */

