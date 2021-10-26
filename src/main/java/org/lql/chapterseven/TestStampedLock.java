package org.lql.chapterseven;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * Title: TestStampedLock <br>
 * ProjectName: learn-concurrent <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/26 20:37 <br>
 */
@Slf4j(topic = "c.TestStampedLock")
public class TestStampedLock {

    public static void main(String[] args) throws InterruptedException {

        DataContainerStamped dataContainerStamped = new DataContainerStamped(1);
        new Thread(() -> {
            try {
                dataContainerStamped.read(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();
        TimeUnit.MILLISECONDS.sleep(500);
        new Thread(() -> {
            try {
                dataContainerStamped.read(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}

@Slf4j(topic = "c.DataContainer")
class DataContainerStamped {
    private int data;
    private final StampedLock lock = new StampedLock();

    public DataContainerStamped(int data) {
        this.data = data;
    }

    public int read(int readTime) throws InterruptedException {
        long stamp = lock.tryOptimisticRead();
        log.debug("optimistic read lock {}", stamp);
        TimeUnit.SECONDS.sleep(readTime);
        if (lock.validate(stamp)) {
            log.debug("read finish...{}", stamp);
            return data;
        }

        log.debug("updating to read lock...{}", stamp);
        try {
            stamp = lock.readLock();
            log.debug("read lock {}", stamp);
            TimeUnit.SECONDS.sleep(readTime);
            log.debug("read finish...{}", stamp);
            return data;
        }finally {
            log.debug("read unlock {}", stamp);
            lock.unlockRead(stamp);
        }
    }

    public void write(int newData) throws InterruptedException {
        long stamp = lock.writeLock();
        log.debug("write lock {}", stamp);
        try {
            TimeUnit.SECONDS.sleep(2);
            this.data = newData;
        }finally {
            log.debug("write unlock {}", stamp);
            lock.unlockWrite(stamp);
        }
    }

}
