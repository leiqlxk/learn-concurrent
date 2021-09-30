package org.lql.chaptertwo;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: CreateThread <br>
 * ProjectName: learn-concurrent <br>
 * description: 直接使用Thread创建线程对象 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/1 0:32 <br>
 */
@Slf4j(topic =  "c.CreateThread")
public class CreateThread {

    public static void main(String[] args) {
        Thread t1 = new Thread("t1"){
            @Override
            public void run() {
                log.debug("running");
            }
        };

        t1.start();
        log.debug("running");
    }
}
