package chapter09_Concurrency;

public class P9_4_ThreadSynchronized {

    public static void main(String[] args) {

        /*
        Если одновременно несколько потоков обратятся к общему ресурсу,
        то результаты выполнения программы могут быть неожиданными и даже непредсказуемыми.
         */
        CommonResource commonResource= new CommonResource();
        for (int i = 1; i < 6; i++){

            Thread t = new Thread(new CountThread(commonResource));
            t.setName("Thread "+ i);
            t.start();

        }

        /*
        порядок использования оператора synchronized для блокирования доступа к объекту.
        synchronized (object) {
            // other thread safe code
        }
         */
        CommonResourceSyn commonResourceSyn= new CommonResourceSyn();
        for (int i = 1; i < 6; i++){

            Thread t = new Thread(new CountThreadSyn(commonResourceSyn));
            t.setName("-----------Thread2 "+ i);
            t.start();
        }

        // То есть пока один поток не окончил работу с полем res.x, с ним начинает работать другой поток.

        // Чтобы избежать подобной ситуации, надо синхронизировать потоки.
        // Одним из способов синхронизации является использование ключевого слова synchronized.
        // Этот оператор предваряет блок кода или метод, который подлежит синхронизации.

        //--- synchronized метод --------------------
        /*
        При применении оператора synchronized к методу пока этот метод не завершит выполнение,
        монопольный доступ имеет только один поток - первый, который начал его выполнение. Для применения synchronized к методу, изменим классы программы:
         */
        CommonResourceSynMethods commonResourceSynMethods = new CommonResourceSynMethods();
        for (int i = 1; i < 6; i++){

            Thread t = new Thread(new CountThreadSynMethods(commonResourceSynMethods));
            t.setName("                         ThreadSyncMethods "+ i);
            t.start();
        }

        /*
        Результат работы в данном случае будет аналогичен примеру выше с блоком synchronized.
        Здесь опять в дело вступает монитор объекта CommonResourceSynMethods - общего объекта для всех потоков.
        Поэтому синхронизированным объявляется не метод run() в классе CountThreadSynMethods,
        а метод increment класса CommonResourceSynMethods.

        Когда первый поток начинает выполнение метода increment,
        он захватывает монитор объекта CommonResource.
        А все потоки также продолжают ожидать его освобождения.
         */
    }
}


class CommonResource{

    int x=0;
}

class CommonResourceSyn{

    int x=0;
}

class CommonResourceSynMethods{

    int x;
    synchronized void increment(){
        x=1;
        for (int i = 1; i < 5; i++){
            System.out.printf("%s %d \n", Thread.currentThread().getName(), x);
            x++;
            try{
                Thread.sleep(100);
            }
            catch(InterruptedException e){}
        }
    }
}

// public class CommonResourceSynMethods
//{
//    public void increment(){
//        synchronized (CommonResourceSynMethods.class) {
//            // ...
//        }
//    }
//}

class CountThread implements Runnable{

    CommonResource res;
    CountThread(CommonResource res){
        this.res=res;
    }
    public void run(){
        res.x=1;
        for (int i = 1; i < 5; i++){
            System.out.printf("%s %d \n", Thread.currentThread().getName(), res.x);
            res.x++;
            try{
                Thread.sleep(100);
            }
            catch(InterruptedException e){}
        }
    }
}

/*
Каждый объект в Java имеет ассоциированный с ним монитор.
Монитор представляет своего рода инструмент для управления доступа к объекту.
Когда выполнение кода доходит до оператора synchronized, монитор объекта res блокируется,
и на время его блокировки монопольный доступ к блоку кода имеет только один поток, который и произвел блокировку.

После окончания работы блока кода, монитор объекта res освобождается и становится доступным для других потоков.
После освобождения монитора его захватывает другой поток, а все остальные потоки продолжают ожидать его освобождения.
 */

class CountThreadSyn implements Runnable{

    CommonResourceSyn res;
    CountThreadSyn(CommonResourceSyn res){
        this.res=res;
    }
    public void run(){
        /*
        При создании синхронизированного блока кода после оператора synchronized идет объект-заглушка: synchronized(res).
        Причем в качестве объекта может использоваться только объект какого-нибудь класса, но не примитивного типа.
         */
        synchronized(res){
            res.x=1;
            //ожидается, что после выполнения цикла res.x будет равно 4.
            for (int i = 1; i < 5; i++){
                System.out.printf("%s %d \n", Thread.currentThread().getName(), res.x);
                res.x++;
                try{
                    Thread.sleep(100);
                }
                catch(InterruptedException e){}
            }
        }
    }
}

class CountThreadSynMethods implements Runnable{

    CommonResourceSynMethods res;
    CountThreadSynMethods(CommonResourceSynMethods res){
        this.res=res;
    }

    public void run(){
        res.increment();
    }
}

/*
Некоторые важные замечания использования synchronized
    - Синхронизация в Java гарантирует, что два потока не могут выполнить синхронизированный метод одновременно.
    - Оператор synchronized можно использовать только с методами и блоками кода,
        которые могут быть как статическими, так и не статическими.
    - Если один из потоков начинает выполнять синхронизированный метод или блок, то этот метод/блок блокируются.
        Когда поток выходит из синхронизированного метода или блока JVM снимает блокировку.
        Блокировка снимается, даже если поток покидает синхронизированный метод после завершения из-за каких-либо ошибок или исключений.
    - Синхронизация в Java вызывает исключение NullPointerException,
        если объект, используемый в синхронизированном блоке, не определен, т.е. равен null.
    - Синхронизированные методы в Java вносят дополнительные затраты на производительность приложения.
        Поэтому следует использовать синхронизацию, когда она абсолютно необходима.
    - В соответствии со спецификацией языка нельзя использовать synchronized в конструкторе, т.к. приведет к ошибке компиляции.
 */

/*
Взаимная блокировка --- англ. deadlock
    С использованием блокировок необходимо быть очень внимательным, чтобы не создать «взаимоблокировку».
    Этот термин означает, что один из потоков ждет от другого освобождения заблокированного им ресурса,
    в то время как сам также заблокировал один из ресурсов, доступа к которому ждет второй поток.
    В данном процессе могут участвовать два и более потоков.

    Основные условия возникновения взаимоблокировок в многопотоковом приложении :
        - наличие ресурсов, которые должны быть доступны только одному потоку в произвольный момент времени;
        - при захвате ресурса поток пытается захватить еще один уникальный ресурс;
        - отсутствует механизм освобождения ресурса при продолжительном его удержании;
        - во время исполнения несколько потоков могут захватить разные уникальные ресурсы и ждать друг от друга их освобождения.
 */