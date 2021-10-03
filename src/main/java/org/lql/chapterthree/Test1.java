package org.lql.chapterthree;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: Test1 <br>
 * ProjectName: learn-concurrent <br>
 * description: 线程安全问题 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/1 17:35 <br>
 */
@Slf4j(topic = "c.Test1")
public class Test1 {
    static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                counter++;
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                counter--;
            }
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("{}", counter);
    }
}
