package org.lql.chapterseven;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * Title: TestSubmit <br>
 * ProjectName: learn-concurrent <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/10 22:50 <br>
 */
@Slf4j(topic = "c.TestSubmit")
public class TestSubmit {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        String result = pool.invokeAny(Arrays.asList(
                () -> {
                    log.debug("begin");
                    TimeUnit.SECONDS.sleep(1);
                    log.debug("end");
                    return "1";
                },
                () -> {
                    log.debug("begin");
                    TimeUnit.MILLISECONDS.sleep(500);
                    log.debug("end");
                    return "2";
                },
                () -> {
                    log.debug("begin");
                    TimeUnit.SECONDS.sleep(2);
                    log.debug("end");
                    return "3";
                }
        ));

        log.debug("{}", result);
    }

    // invokeAll
    private static void method2(ExecutorService pool) throws InterruptedException {
        List<Future<Object>> futures = pool.invokeAll(Arrays.asList(
                () -> {
                    log.debug("begin");
                    TimeUnit.SECONDS.sleep(1);
                    return "1";
                },
                () -> {
                    log.debug("begin");
                    TimeUnit.MILLISECONDS.sleep(500);
                    return "2";
                },
                () -> {
                    log.debug("begin");
                    TimeUnit.SECONDS.sleep(2);
                    return "3";
                }
        ));

        futures.forEach(item -> {
            try {
                log.debug("{}", item.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    // submit
    private static void method1(ExecutorService pool) throws InterruptedException, ExecutionException {
        Future<String> future = pool.submit(() -> {
            log.debug("running");
            TimeUnit.SECONDS.sleep(1);
            return "ok";
        });

        log.debug("{}", future.get());
    }
}
