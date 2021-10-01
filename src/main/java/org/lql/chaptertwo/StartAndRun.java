package org.lql.chaptertwo;

import lombok.extern.slf4j.Slf4j;
import org.lql.constants.Constants;
import org.lql.util.FileReader;

/**
 * Title: StartAndRun <br>
 * ProjectName: learn-concurrent <br>
 * description: start 和 run区别 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/1 11:11 <br>
 */
@Slf4j(topic = "c.StartAndRun")
public class StartAndRun {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.debug("running...");
            FileReader.read(Constants.MP4_FULL_PATH);
        }, "t1");

        // 直接调run相当于普通方法调用，并不会启动线程
//        t1.run();

        // 线程状态信息,new
        System.out.println(t1.getState());

        // 启动线程需要使用start方法
        t1.start();

        // runnable
        System.out.println(t1.getState());

        log.debug("do other things...");
    }
}
