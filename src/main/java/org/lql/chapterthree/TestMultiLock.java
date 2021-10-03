package org.lql.chapterthree;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Title: TestMultiLock <br>
 * ProjectName: learn-concurrent <br>
 * description: 测试多把锁 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/3 22:40 <br>
 */
@Slf4j(topic = "c.TestMultiLock")
public class TestMultiLock {

    public static void main(String[] args) {
        BigRoom bigRoom = new BigRoom();
        new Thread(bigRoom::study,"小南").start();

        new Thread(bigRoom::sleep,"小女").start();
    }
}

@Slf4j(topic = "c.BigRoom")
class BigRoom{
    private final Object studyRoom = new Object();
    private final Object bedRoom = new Object();
    public void sleep() {
        synchronized (bedRoom) {
            log.debug("sleeping 2 小时");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void study() {
        synchronized (studyRoom) {
            log.debug("study 1 小时");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
