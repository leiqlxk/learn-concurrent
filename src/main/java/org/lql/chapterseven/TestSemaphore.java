package org.lql.chapterseven;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Title: TestSemaphore <br>
 * ProjectName: learn-concurrent <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/26 20:58 <br>
 */
@Slf4j(topic = "c.TestSemaphore")
public class TestSemaphore {

    public static void main(String[] args) {
        // 创建semaphore对象
        Semaphore semaphore = new Semaphore(3);

        // 10个线程同时运行
        for (int i = 0; i <10; i++) {
            new Thread(() -> {
                // 获得许可
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                log.debug("running....");
                try {
                    TimeUnit.SECONDS.sleep(1);
                    log.debug("end...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}
