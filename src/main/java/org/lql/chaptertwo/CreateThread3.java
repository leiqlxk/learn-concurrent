package org.lql.chaptertwo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Title: CreateThread <br>
 * ProjectName: learn-concurrent <br>
 * description: 使用FutureTask配合Thread创建线程对象，使任务和线程分离 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/1 0:32 <br>
 */
@Slf4j(topic =  "c.CreateThread3")
public class CreateThread3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            log.debug("running");
            Thread.sleep(1000);
            return 1;
        });

        Thread t1 = new Thread(futureTask,"t1");

        t1.start();

        System.out.println(futureTask.get());
        log.debug("running");
    }
}
