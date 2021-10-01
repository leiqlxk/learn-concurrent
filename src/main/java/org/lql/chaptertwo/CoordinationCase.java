package org.lql.chaptertwo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Title: CoordinationCase <br>
 * ProjectName: learn-concurrent <br>
 * description: 统筹案例 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/1 16:42 <br>
 */
@Slf4j(topic = "c.CoordinationCase")
public class CoordinationCase {

    // 缺陷
    // 如果反过来要实现老王等小王的茶叶拿来了，老王泡茶，必须修改代码
    // 两个线程其实是各执行各的，如果要模拟老王把水壶交给小王泡茶，或模拟小王把茶叶交给老王泡茶
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.debug("洗水壶");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("烧开水");
            try {
                TimeUnit.SECONDS.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "老王");

        Thread t2 = new Thread(() -> {
            log.debug("洗茶壶");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("洗茶杯");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("拿茶叶");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("泡茶");
        }, "小王");

        t1.start();
        t2.start();
    }
}
