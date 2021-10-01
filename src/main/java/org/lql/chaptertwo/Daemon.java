package org.lql.chaptertwo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Title: Daemon <br>
 * ProjectName: learn-concurrent <br>
 * description: 守护线程 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/1 15:51 <br>
 */
@Slf4j(topic = "c.Daemon")
public class Daemon {

    // 守护线程不能阻止主线程结束
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
            }
            log.debug("结束");
        }, "t1");

        // 设置为守护线程
        t1.setDaemon(true);
        t1.start();

        log.debug("结束");
    }


    // 只要还有非守护线程未停止运行程序就不会停止
    public static void test(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
            }
            log.debug("结束");
        }, "t1");

        t1.start();

        TimeUnit.SECONDS.sleep(1);
        log.debug("结束");
    }
}
