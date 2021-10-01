package org.lql.chaptertwo;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Title: TwoPhaseTermination <br>
 * ProjectName: learn-concurrent <br>
 * description: 两阶段终止 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/1 15:25 <br>
 */
@Slf4j(topic = "c.TwoPhaseTermination")
public class TwoPhaseTermination {

    public static void main(String[] args) throws InterruptedException {
        TwoPhase tp = new TwoPhase();
        tp.start();

        TimeUnit.SECONDS.sleep(4);
        tp.stop();
    }

}

@Slf4j(topic = "c.TwoPhase")
class TwoPhase {
    private Thread monitor;

    // 启动监控线程
    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                Thread current = Thread.currentThread();
                if (current.isInterrupted()) {
                    log.debug("料理后事");
                    break;
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                    log.debug("执行监控记录");
                } catch (InterruptedException e) {
                    log.debug("执行监控记录");
                    e.printStackTrace();
                    current.interrupt();
                }
            }
        }, "monitor");

        monitor.start();
    }

    // 停止监控线程
    public void stop() {
        monitor.interrupt();
    }
}
