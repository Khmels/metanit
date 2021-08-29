package chapter9;

import java.util.concurrent.Exchanger;

public class P9_7_Exchanger {
    public static void main(String[] args) {

        Exchanger<String> ex = new Exchanger<String>();
        new Thread(new PutThread(ex)).start();
        new Thread(new GetThread(ex)).start();
    }

}

class PutThread implements Runnable{

    Exchanger<String> exchanger;
    String message;

    PutThread(Exchanger<String> ex){

        this.exchanger=ex;
        message = "Hello Java!";
    }
    public void run(){

        try{
            // PutThread отправляет в буфер сообщение "Hello Java!":
            // в ответ метод exchange возвращает данные, которые отправил в буфер другой поток.

            System.out.println("PutThread has sent: " + message);
            message=exchanger.exchange(message);

            // То есть происходит обмен данными. Хотя необязательно получать данные, можно просто их отправить:
            //exchanger.exchange(message);
            System.out.println("PutThread has received: " + message);
        }
        catch(InterruptedException ex){
            System.out.println(ex.getMessage());
        }
    }
}
class GetThread implements Runnable{

    Exchanger<String> exchanger;
    String message;

    GetThread(Exchanger<String> ex){

        this.exchanger=ex;
        message = "Hello World!";
    }
    public void run(){

        try{
            System.out.println("GetThread has sent: " + message);
            message=exchanger.exchange(message);
            System.out.println("GetThread has received: " + message);
        }
        catch(InterruptedException ex){
            System.out.println(ex.getMessage());
        }
    }
}

/*
Класс Exchanger предназначен для обмена данными между потоками.
Он является типизированным и типизируется типом данных, которыми потоки должны обмениваться.

Обмен данными производится с помощью единственного метода этого класса exchange():

V exchange(V x) throws InterruptedException             Параметр x представляет буфер данных для обмена

V exchange(V x, long timeout, TimeUnit unit)
throws InterruptedException, TimeoutException           Вторая форма метода также определяет параметр timeout - время ожидания
                                                                unit - тип временных единиц, применяемых для параметра timeout.
*/

