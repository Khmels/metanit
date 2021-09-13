package chapter09_Multithreading;

import java.util.concurrent.Exchanger;

public class P9_6_a4_Synchroniser_Exchanger {
    // Обменник почтовыми письмами
    private static Exchanger<P9_6_a4_Synchroniser_Exchanger.Letter> exchanger;

    static String msg1 = "Почтальон %s получил письма : %s, %s\n";
    static String msg2 = "Почтальон %s выехал из %s в %s\n";
    static String msg3 = "Почтальон %s приехал в пункт Д\n";
    static String msg4 = "Почтальон %s получил письма для %s\n";
    static String msg5 = "Почтальон %s привез в %s : %s, %s\n";

    public static class Postman implements Runnable {
        private String   id;
        private String   departure;
        private String   destination;
        private P9_6_a4_Synchroniser_Exchanger.Letter[] letters;

        public Postman(String id, String departure, String destination, P9_6_a4_Synchroniser_Exchanger.Letter[] letters)
        {
            this.id          = id;
            this.departure   = departure;
            this.destination = destination;
            this.letters     = letters;
        }

        @Override
        public void run() {
            try {
                System.out.printf(msg1, id, letters[0], letters[1]);
                System.out.printf(msg2, id, departure, destination);
                Thread.sleep(((long) Math.random() * 10 + 4) * 1000);
                System.out.printf(msg3, id);
                // Самоблокировка потока для обмена письмами
                letters[1] = exchanger.exchange(letters[1]);
                // Обмен письмами
                System.out.printf(msg4, id, destination);
                Thread.sleep(((long) Math.random() * 10 + 4) * 1000);
                System.out.printf(msg5, id, destination, letters[0], letters[1]);
            } catch (InterruptedException e) {
            }
        }
    }

    public static class Letter {
        private String address;
        public Letter(final String address) {
            this.address = address;
        }
        public String toString()
        {
            return address;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        exchanger = new Exchanger<P9_6_a4_Synchroniser_Exchanger.Letter>();

        // Формирование отправлений
        P9_6_a4_Synchroniser_Exchanger.Letter[] posts1 = new P9_6_a4_Synchroniser_Exchanger.Letter[2];
        P9_6_a4_Synchroniser_Exchanger.Letter[] posts2 = new P9_6_a4_Synchroniser_Exchanger.Letter[2];
        posts1[0] = new P9_6_a4_Synchroniser_Exchanger.Letter("п.В - Петров"           );
        posts1[1] = new P9_6_a4_Synchroniser_Exchanger.Letter("п.Г - Киса Воробьянинов");
        posts2[0] = new P9_6_a4_Synchroniser_Exchanger.Letter("п.Г - Остап Бендер"     );
        posts2[1] = new P9_6_a4_Synchroniser_Exchanger.Letter("п.В - Иванов"           );

        // Отправление почтальонов
        new Thread(new P9_6_a4_Synchroniser_Exchanger.Postman("a", "А", "В", posts1)).start();
        Thread.sleep(100);
        new Thread(new P9_6_a4_Synchroniser_Exchanger.Postman("б", "Б", "Г", posts2)).start();
    }

}

//--- Объект синхронизации Exchanger

/*
Класс Exchanger (обменник) предназначен для упрощения процесса обмена данными между двумя потоками исполнения.
Принцип действия класса Exchanger связан с ожиданием того, что два отдельных потока должны вызвать его метод exchange.

Как только это произойдет, Exchanger произведет обмен данными, предоставляемыми обоими потоками.

Обменник является обобщенным классом, он параметризируется типом объекта передачи :
    Exchanger<V>();

Необходимо отметить, что обменник поддерживает передачу NULL значения,
что дает возможность использовать его для передачи объекта в одну сторону или места синхронизации двух потоков.


Exchanger содержит перегруженный метод exchange, имеющий следующие формы :
    V exchange(V buffer) throws InterruptedException        Параметр buffer является ссылкой на обмениваемые данные.
                                                            Метод возвращает данные из другого потока исполнения.
    V exchange(V buffer, long wait, TimeUnit unit)
                        throws InterruptedException;        позволяет определить время ожидания.
                                                            Параметры wait и unit:
            ожидание длится только в течение определенного периода времени, определяемого параметром ожидание wait.
            время ожидания указывается в единицах unit объекта перечисления TimeUnit, определяюший временно́е разбиение.

Метод exchange, вызванный в одном потоке, не завершится успешно до тех пор, пока он не будет вызван из второго потока исполнения.
 */

/*
В примере использования объекта синхронизации Exchanger два почтальона из пунктов А и Б отправляются в соседние поселки В и Г доставить письма.
Каждый из почтальонов должен доставить по письму в каждый из поселков.
Чтобы не делать лишний круг, они встречаются в промежуточном поселке Д и обмениваются одним письмом.

В результате этого каждому из почтальонов придется доставить письма только в один поселок.
В примере все «шаги» почтальонов фиксируются выводом соответствующих сообщений в консоль.
 */
