package org.lql.chapterseven;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Title: TestShutDown <br>
 * ProjectName: learn-concurrent <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/10 23:12 <br>
 */
@Slf4j(topic = "c.TestShutDown")
public class TestShutDown {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        Future<Integer> resut1 = pool.submit(() -> {
            log.debug("task 1 running...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("task 1 finish...");
            return 1;
        });

        Future<Integer> resut2 = pool.submit(() -> {
            log.debug("task 2 running...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("task 2 finish...");
            return 2;
        });

        Future<Integer> resut3 = pool.submit(() -> {
            log.debug("task 3 running...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("task 3 finish...");
            return 3;
        });

       /* log.debug("shutdown");
        pool.shutdown();
        pool.awaitTermination(3, TimeUnit.SECONDS);
        log.debug("other...");

        Future<Integer> resut4 = pool.submit(() -> {
            log.debug("task 4 running...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("task 4 finish...");
            return 4;
        });*/

        log.debug("shutdownNow");
        List<Runnable> runnables = pool.shutdownNow();
        log.debug("other...");
    }
}
