package chapter9_Multithreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class P9_10a_LocksCondition2 {
    // (The ArrayBlockingQueue class provides this functionality, so there is no reason to implement this sample usage class.)

    //A Condition implementation can provide behavior and semantics that is different from that of the Object monitor methods,
    // such as guaranteed ordering for notifications, or not requiring a lock to be held when performing notifications.
    //
    // If an implementation provides such specialized semantics then the implementation must document those semantics.
    //
    // Note that Condition instances are just normal objects and can themselves be used as the target in a synchronized statement,
    // and can have their own monitor wait and notify methods invoked.
    //
    // Acquiring the monitor lock of a Condition instance, or using its monitor methods,
    // has no specified relationship with acquiring the Lock associated with that Condition
    // or the use of its waiting and signalling methods.
    //
    // It is recommended that to avoid confusion you never use Condition instances in this way, except perhaps within their own implementation.
    // Except where noted, passing a null value for any parameter will result in a NullPointerException being thrown.
}

class BoundedBuffer<E> {
    final Lock lock = new ReentrantLock();
    final Condition notFull  = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[100];
    int putptr, takeptr, count;

    public void put(E x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length)
                notFull.await();
            items[putptr] = x;
            if (++putptr == items.length) putptr = 0;
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            E x = (E) items[takeptr];
            if (++takeptr == items.length) takeptr = 0;
            --count;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}