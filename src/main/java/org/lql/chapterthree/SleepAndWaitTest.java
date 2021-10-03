package org.lql.chapterthree;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Title: SleepAndWaitTest <br>
 * ProjectName: learn-concurrent <br>
 * description: sleep和wait 释放锁的区别 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/2 22:09 <br>
 */
@Slf4j(topic = "c.SleepAndWaitTest")
public class SleepAndWaitTest {
    static final Object LOCK = new Object();

    // wait会释放锁
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (LOCK) {
                log.debug("获得锁");
                try {
                    LOCK.wait();
                    log.debug("被唤醒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        TimeUnit.SECONDS.sleep(1);
        synchronized (LOCK) {
            log.debug("获得锁");
            LOCK.notify();
        }
    }

    // sleep不会释放锁
    public static void test(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (LOCK) {
                log.debug("获得锁");
                try {
                    TimeUnit.SECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        TimeUnit.SECONDS.sleep(1);
        synchronized (LOCK) {
            log.debug("获得锁");
        }
    }

}
