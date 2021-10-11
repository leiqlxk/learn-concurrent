package org.lql.chapterseven;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Title: TestScheduleExecutor <br>
 * ProjectName: learn-concurrent <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/11 22:45 <br>
 */
@Slf4j(topic = "c.TestScheduleExecutor")
public class TestScheduleExecutor {

    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);

        log.debug("start...");
        // 定时执行任务，间隔时间会因为前一个任务处理时间影响
       /* pool.scheduleAtFixedRate(() -> {
            log.debug("running");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, 1, TimeUnit.SECONDS);*/

        // 固定间隔时间
        pool.scheduleWithFixedDelay(() -> {
            log.debug("running");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, 1, TimeUnit.SECONDS);

        /*pool.schedule(() -> {
            log.debug("task1");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, TimeUnit.SECONDS);

        pool.schedule(() -> {
            log.debug("task2");
        }, 1, TimeUnit.SECONDS);*/
    }
}
