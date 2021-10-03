package org.lql.chapterthree;

/**
 * Title: SynchronizedTest <br>
 * ProjectName: learn-concurrent <br>
 * description: synchronized解决线程安全问题 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/1 21:03 <br>
 */
public class SynchronizedTest {

    static int counter = 0;
    static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (lock) {
                    counter++;
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (lock) {
                    counter--;
                }
            }
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(counter);
    }

}
