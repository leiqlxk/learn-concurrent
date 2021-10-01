package org.lql.chaptertwo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Title: SleepAndYield <br>
 * ProjectName: learn-concurrent <br>
 * description: sleep 和 yield <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/1 11:18 <br>
 */
@Slf4j(topic = "c.SleepAndYield")
public class SleepAndYield {

    // 使用Timeunit替代sleep
    public static void test3(String[] args) throws InterruptedException {
        log.debug("enter");
        TimeUnit.SECONDS.sleep(1);
        log.debug("end");
    }

    // 使用打断唤醒sleep的线程
    public static void test2(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("enter sleep...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                log.debug("wake up...");
                e.printStackTrace();
            }
        }, "t1");

        t1.start();

        Thread.sleep(1000);
        log.debug("interrupt");
        t1.interrupt();
    }

    // 测试sleep线程状态
    public static void test1(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");

        t1.start();
        log.debug("t1 state: {}", t1.getState());

        Thread.sleep(2000);

        log.debug("t1 state: {}", t1.getState());
    }
}
