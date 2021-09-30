package org.lql.chaptertwo;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: CreateThread <br>
 * ProjectName: learn-concurrent <br>
 * description: 使用Runnable配合Thread创建线程对象，使任务和线程分离 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/1 0:32 <br>
 */
@Slf4j(topic =  "c.CreateThread2")
public class CreateThread2 {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log.debug("running");
            }
        };

        Runnable runnable1 = () -> log.debug("running2");

        Thread t1 = new Thread(runnable,"t1");
        Thread t2 = new Thread(runnable1,"t2");

        t1.start();
        t2.start();
        log.debug("running");
    }
}
