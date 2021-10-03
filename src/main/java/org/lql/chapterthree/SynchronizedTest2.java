package org.lql.chapterthree;

/**
 * Title: SynchornizedTest2 <br>
 * ProjectName: learn-concurrent <br>
 * description: synchronized 面向对象改进 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/1 21:18 <br>
 */
public class SynchronizedTest2 {

    static Room room = new Room();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                room.increment();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                room.decrement();
            }
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(room.get());
    }
}


class Room {
    private int counter = 0;

    public void increment() {
        synchronized (this) {
            counter++;
        }
    }

    public void decrement() {
        synchronized (this) {
            counter--;
        }
    }

    public int get() {
        synchronized (this) {
            return counter;
        }
    }
}
