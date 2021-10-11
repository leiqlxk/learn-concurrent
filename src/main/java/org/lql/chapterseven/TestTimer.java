package org.lql.chapterseven;

import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Title: TestTimer <br>
 * ProjectName: learn-concurrent <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/11 22:37 <br>
 */
@Slf4j(topic = "c.TestTimer")
public class TestTimer {

    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask task1 = new TimerTask() {

            @Override
            public void run() {
                log.debug("task 1");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                log.debug("task 2");
            }
        };

        log.debug("start...");
        timer.schedule(task1, 1000);
        timer.schedule(task2, 1000);
    }
}
