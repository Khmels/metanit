package chapter9;

public class P9_4a_Deadlock {

    static class Friend {
        private final String name;
        public Friend(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public synchronized void bow(Friend bower) {
            System.out.format("%s: %s" + "  has bowed to me!%n", this.name, bower.getName());
            bower.bowBack(this);
        }
        public synchronized void bowBack(Friend bower) {
            System.out.printf("%s: %s"
                            + " has bowed back to me!%n",
                    this.name, bower.getName());
        }
    }

    public static void main(String[] args) {
        final Friend archibald =
                new Friend("Archibald");
        final Friend gerald =
                new Friend("Gerald");
        new Thread(new Runnable() {
            @Override
            public void run() {
                // System.out.println("Thread 1");
                archibald.bow(gerald);
                // System.out.println("Th: gerald bowed to archibald");
            }
        }).start();

        //Replace with lambda
        new Thread(() -> {
            //  System.out.println("Thread 2");
            gerald.bow(archibald);
            //  System.out.println("2.gerald waiting alph bowed");
        }).start();
    }

}

/*
При входе в синхронизированный метод объекта, его лок запирается, а когда из метода выходят, он освобождается (или отпирается).

Теперь о нитях. Назовем первую нить Archibald (с большой буквы, чтобы отличить от объекта archibald).
Вот что она делает (обозначим ее буквой A):

A: archibald.bow(gerald) — получает лок alphonse;
A: gerald.bowBack(archibald) — получает лок gaston;
A: возвращается из обоих методов, тем самым освобождая лок.

А вот чем в это время занята нить Gaston:

G: gerald.bow(archibald) — получает лок gaston;
G: archibald.bowBack(gerald) — получает лок alphonse;
G: возвращается из обоих методов, тем самым освобождая лок.
 */

/*
Теперь сведем эти данные вместе и получим ответ.
Нити могут переплетаться (то есть, их события совершатся) в разных порядках.
Дедлок получится, например, если порядок будет таким:

A: archibald.bow(gerald) — получает лок archibald
G: gerald.bow(archibald) — получает лок gerald
G: пытается вызвать archibald.bowBack(gerald), но блокируется, ожидая лока archibald
A: пытается вызвать gerald.bowBack(archibald), но блокируется, ожидая лока gerald

В этом случае обе нити заблокированы и каждая ожидает, что другая отдаст лок.
Но ни одна это не сделает, потому что для этого нужно завершить свой метод, а он заблокирован другой нитью.
Поэтому они застряли на месте, случился deadlock.
 */

    //возможно и другое переплетение, в котором одна из нитей успеет завершиться до начала второй:
/*
A: archibald.bow(gerald) — получает лок alphonse
A: gerald.bowBack(archibald) — получает лок gaston
A: возвращается из обоих методов, открывая оба лока

G: gerald.bow(archibald) — получает лок gaston
G: archibald.bowBack(gerald) — получает лок alphonse
G: возвращается из обоих методов, открывая оба лока
 */

// Когда результат зависит от порядка одновременно происходящих событий (запланированного порядка или скорости выполнения),
// такой процесс называется race condition по-русски — «состояние гонки».

// Не все race condition потенциально производят дедлок,
// однако, дедлоки происходят только в race condition.
