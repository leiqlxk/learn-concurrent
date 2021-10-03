package org.lql.chapterthree;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Title: ReentrantLockForInterrupt <br>
 * ProjectName: learn-concurrent <br>
 * description: ReentrantLock 可打断测试 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/3 23:42 <br>
 */
@Slf4j(topic = "c.ReentrantLockForInterrupt")
public class ReentrantLockForInterrupt {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            // 如果没有竞争那么此方法就会获取lock对象锁
            // 如果有竞争就进入阻塞队列，可以被其它线程用 interrupt方法打断
            try {
                log.debug("尝试获得锁");
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.debug("没有获得锁，返回");
                return;
            }

            try {
                log.debug("获取到锁");
            }finally {
                lock.unlock();
            }
        }, "t1");

        lock.lock();
        t1.start();

        TimeUnit.SECONDS.sleep(1);
        log.debug("打断 t1");
        t1.interrupt();
    }
}
