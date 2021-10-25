package org.lql.chapterseven;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Title: TestReadWriteLock <br>
 * ProjectName: learn-concurrent <br>
 * description: 读写锁测试 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/25 21:51 <br>
 */
@Slf4j(topic = "c.TestReadWriteLock")
public class TestReadWriteLock {

    public static void main(String[] args) {
        DataContainer dataContainer = new DataContainer();
        new Thread(() -> {
            dataContainer.write();
        }, "T1").start();

        new Thread(() -> {
            dataContainer.write();
        }, "T2").start();
    }
}

@Slf4j(topic = "c.DataContainer")
class DataContainer {
    private Object data;
    private ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock r = rw.readLock();
    private ReentrantReadWriteLock.WriteLock w = rw.writeLock();

    public Object read() {
        log.debug("获取读锁....");
        r.lock();
        try {
            log.debug("读取");
            TimeUnit.SECONDS.sleep(1);
            return data;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            log.debug("释放读锁....");
            r.unlock();
        }
    }

    public void write() {
        log.debug("获取写锁....");
        w.lock();
        try {
            log.debug("写入");
        }finally {
            log.debug("释放写锁....");
            w.unlock();
        }
    }
}
