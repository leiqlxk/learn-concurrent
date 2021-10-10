package org.lql.chapterseven;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Title: TestExecutors <br>
 * ProjectName: learn-concurrent <br>
 * description: Executors创建线程池 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/10 22:30 <br>
 */
@Slf4j(topic = "c.TestExecutors")
public class TestExecutors {

    // 单线程线程池，在需要任务串行执行时使用，并且使用线程池后，在发生异常结束线程后会立即重启一个线程执行后续任务
    public static void main(String[] args) {
        ExecutorService pool = Executors.newSingleThreadExecutor();

        pool.execute(() -> {
            log.debug("1");
            int i = 1 / 0;
        });

        pool.execute(() -> {
            log.debug("2");
        });

        pool.execute(() -> {
            log.debug("3");
        });
    }

    // 固定大小线程池，都是核心线程，并且配合的是无界队列
    public static void test1(String[] args) {

//        ExecutorService pool = Executors.newFixedThreadPool(2);
        ExecutorService pool = Executors.newFixedThreadPool(2, new ThreadFactory() {
            private AtomicInteger t = new AtomicInteger(1);
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "mypool_t" + t.getAndIncrement());
            }
        });

        pool.execute(() -> {
            log.debug("1");
        });

        pool.execute(() -> {
            log.debug("2");
        });

        pool.execute(() -> {
            log.debug("3");
        });
    }
}
