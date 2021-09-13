package chapter09_Concurrency;

public class P9_5a_Daemon {
    public static void main(String[] args) {
        StoreDaemon     store    = new StoreDaemon();
        ProducerDaemon  producer = new ProducerDaemon(store);
        ConsumerDaemon  consumer = new ConsumerDaemon(store);
//        new Thread(producer).start();
//        new Thread(consumer).start();

        Thread  tp = new Thread(producer);
        Thread  tc = new Thread(consumer);

        tp.setDaemon(true);
        tc.setDaemon(true);

        tp.start();
        tc.start();
        System.out.println(tp.isDaemon());
        System.out.println(tc.isDaemon());

//        System.out.println("\nГлавный поток завершен\n");
//        System.exit(0);

        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {}

        System.out.println("\nГлавный поток завершен\n");
        System.exit(0);

        /*
        Java приложение завершает работу тогда, когда завершает работу последний его поток.
        Даже если метод main() уже завершился, но еще выполняются порожденные им потоки, система будет ждать их завершения.

        Однако это правило не относится к потоков-демонам (daemon).
        Если завершился последний обычный поток процесса, и остались только daemon потоки,
        то они будут принудительно завершены и выполнение приложения закончится.

        Чаще всего daemon потоки используются для выполнения фоновых задач, обслуживающих процесс в течение его жизни.

        Объявить поток демоном достаточно просто. Для этого нужно перед запуском потока вызвать его метод setDaemon(true).
        Проверить, является ли поток daemon'ом можно вызовом метода isDaemon().
         */
    }
}

class StoreDaemon
{
    private int counter = 0;
    public synchronized void get()
    {
        while (counter < 1) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        counter--;
        System.out.println("-1 : товар забрали");
        System.out.println(
                "\tколичество товара на складе : " + counter);
        notify();
    }
    public synchronized void put() {
        while (counter >= 3) {
            try {
                wait();
            }catch (InterruptedException e) {}
        }
        counter++;
        System.out.println("\t\t+1 : товар добавили");
        System.out.println(
                "\t\t\tколичество товара на складе : " + counter);
        notify();
    }
}

class ProducerDaemon implements Runnable
{
    StoreDaemon store;
    ProducerDaemon(StoreDaemon store) {
        this.store=store;
    }
    @Override
    public void run() {
        for (int i = 1; i < 6; i++) {
            //System.out.println(i + " действие производителя");
            store.put();
        }
    }
}

class ConsumerDaemon implements Runnable
{
    StoreDaemon store;
    ConsumerDaemon(StoreDaemon store) {
        this.store=store;
    }
    @Override
    public void run(){
        for (int i = 1; i < 6; i++) {
            //System.out.println(i + " действие покупателя");
            store.get();
        }
    }
}
