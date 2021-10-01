package org.lql.chaptertwo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Title: Join <br>
 * ProjectName: learn-concurrent <br>
 * description: join <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/1 14:15 <br>
 */
@Slf4j(topic = "c.Join")
public class Join {

    static int r = 0;

    public static void main(String[] args) throws InterruptedException {
        test1();
    }

    private static void test1() throws InterruptedException {

        log.debug("开始");
        Thread t1 = new Thread(() -> {
            log.debug("开始");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("结束");
            r = 10;
        }, "t1");

        t1.start();
        // 哪个线程调用就等待哪个线程结束
        t1.join();
        log.debug("结果为：{}", r);
        log.debug("结束");
    }
}
