package chapter9;

import java.util.ArrayList;
import java.util.concurrent.Phaser;

public class P9_6_a5_Synchroniser_Phaser {
    private static Phaser phaser; // Главный поток

    private static String WAIT  = " ждет на станции ";
    private static String ENTER = " вошел в вагон"   ;
    private static String EXIT  = " вышел из вагона ";
    private static String SPACE = "    ";
    private static String OPEN  = "    ... открытие дверей ...";
    private static String CLOSE = "    ... закрытие дверей ...";

    public static class Passenger implements Runnable {
        int id;
        int departure;
        int destination;

        public Passenger(int id, int departure, int destination)
        {
            this.id          = id;
            this.departure   = departure;
            this.destination = destination;
            System.out.println(this + WAIT + departure);
        }

        @Override
        public void run()
        {
            try {
                System.out.println(SPACE + this + ENTER);
                //---------------------------------
                // Заявляем об участии и ждем станции назначения
                while (phaser.getPhase() < destination)
                    phaser.arriveAndAwaitAdvance();
                //---------------------------------
                Thread.sleep(500);
                System.out.println(SPACE + this + EXIT);
                // Отмена регистрации
                phaser.arriveAndDeregister();
            } catch (InterruptedException e) {}
        }
        @Override
        public String toString() {
            return "Пассажир " + id + " {" + departure + " -> " + destination + '}';
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Регистрация объекта синхронизации
        phaser = new Phaser(1);

        ArrayList<P9_6_a5_Synchroniser_Phaser.Passenger> passengers = new ArrayList<>();
        // Формирование массива пассажиров
        for (int i = 1; i < 5; i++) {
            if ((int) (Math.random() * 2) > 0)
                // Этот пассажир проезжает одну станцию
                passengers.add(new P9_6_a5_Synchroniser_Phaser.Passenger(10 + i, i, i + 1));

            if ((int) (Math.random() * 2) > 0) {
                // Этот пассажир едет до конечной
                P9_6_a5_Synchroniser_Phaser.Passenger p = new P9_6_a5_Synchroniser_Phaser.Passenger(20 + i, i, 5);
                passengers.add(p);
            }
        }

        // Фазы 0 и 6 - конечные станции
        // Фазы 1...5 - промежуточные станции
        for (int i = 0; i < 7; i++) {
            switch (i) {
                case 0:
                    System.out.println("Метро вышло из депо");
                    // Нулевая фаза, участников нет
                    phaser.arrive();
                    break;
                case 6:
                    // Завершаем синхронизацию
                    System.out.println("Метро ушло в депо");
                    phaser.arriveAndDeregister();
                    break;
                default:
                    int currentStation = phaser.getPhase();
                    System.out.println("Станция " + currentStation);
                    // Проверка наличия пассажиров на станции
                    for (P9_6_a5_Synchroniser_Phaser.Passenger pass : passengers)
                        if (pass.departure == currentStation) {
                            // Регистрация участника
                            phaser.register();
                            new Thread (pass).start();
                        }
                    System.out.println(OPEN);

                    // Phaser ожидает завершения фазы всеми участниками
                    phaser.arriveAndAwaitAdvance();

                    System.out.println(CLOSE);
            }
        }
    }
}

//--- Объект синхронизации Phaser

/*
Phaser (фазировщик), как и CyclicBarrier, является реализацией объекта синхронизации типа «Барьер» (CyclicBarrier).

В отличии от CyclicBarrier, Phaser предоставляет больше гибкости.
Чтобы лучше понять Phaser, можно привести два наглядно демонстрирующих его использование примера.

В качестве первого примера можно рассмотреть несколько потоков исполнения, реализующих процесс обработки заказов из трех стадий.
    На первой стадии отдельные потоки исполнения проверяют сведения о клиенте, наличие товара на складе и их стоимость.
    На второй стадии вычисляется стоимость заказа и стоимость доставки.
    На заключительной стадии подтверждается оплата и определяется ориентировочное время доставки.

Во втором примере несколько потоков реализуют перевозку пассажиров городским транспортом.
    Пассажиры ожидают транспорт на разных остановках.
    Транспорт, останавливаясь на остановках, одних пассажиров «сажает», других «высаживает».

В этих примерах общим является то, что один объект синхронизации Phaser, исполняющий роль заказа и транспорта,
играет главную роль, а другие потоки вступают в работу при определенном состоянии Phaser.

Таким образом, класс Phaser позволяет определить объект синхронизации, ожидающий завершения определенной фазы.
После этого он переходит к следующей фазе и снова ожидает ее завершения.
*/

/*
Важные особенности Phaser :

Phaser может иметь несколько фаз (барьеров).

Если количество фаз равно 1, то плавно переходим к CyclicBarrier
(осталось только все исполнительные потоки остановить у барьера).

Каждая фаза (цикл синхронизации) имеет свой номер.
Количество участников-потоков для каждой фазы жестко не задано и может меняться.
Исполнительный поток может регистрироваться в качестве участника и отменять свое участие;
Исполнительный поток не обязан ожидать, пока все остальные участники соберутся у барьера.
Достаточно только сообщить о своем прибытии.

При создании экземпляр класса Phaser находится в нулевой фазе.

В очередном состоянии (фазе) синхронизатор находится в ожидании до тех пор,
пока все зарегистрированные потоки не завершат данную фазу.

Потоки извещают об этом, вызывая один из методов arrive() или arriveAndAwaitAdvance().
 */

/* Phaser constructors // конструкторы
        Phaser()                            создает объект Phaser без каких-либо участников
        Phaser(int parties)                 parties определяет количество участников, которые должны пройти все фазы
        Phaser(Phaser parent)               дополнительно устанавливают родительский объект Phaser
        Phaser(Phaser parent, int parties)  дополнительно устанавливают родительский объект Phaser
                                                и регистрирует передаваемое в конструктор количество участников
*/
