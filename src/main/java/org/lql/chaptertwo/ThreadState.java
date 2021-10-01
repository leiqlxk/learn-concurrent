package org.lql.chaptertwo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Title: ThreadState <br>
 * ProjectName: learn-concurrent <br>
 * description: 线程状态演示 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/1 16:19 <br>
 */
@Slf4j(topic = "c.ThreadState")
public class ThreadState {

    public static void main(String[] args) {
        // new
        Thread t1 = new Thread(() -> log.debug("running"), "t1");

        // runnable
        Thread t2 = new Thread(() -> {
            while (true) {

            }
        }, "t2");
        t2.start();

        // terminated
        Thread t3 = new Thread(() -> {
            log.debug("running");
        }, "t3");
        t3.start();

        // timed waiting
        Thread t4 = new Thread(() -> {
            synchronized (ThreadState.class) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t4");
        t4.start();

        // waiting
        Thread t5 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t5");
        t5.start();

        // blocked
        Thread t6 = new Thread(() -> {
            synchronized (ThreadState.class) {
                try {
                    TimeUnit.MILLISECONDS.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t6");
        t6.start();

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.debug("t1 state {}", t1.getState());
        log.debug("t2 state {}", t2.getState());
        log.debug("t3 state {}", t3.getState());
        log.debug("t4 state {}", t4.getState());
        log.debug("t5 state {}", t5.getState());
        log.debug("t6 state {}", t6.getState());
    }
}
