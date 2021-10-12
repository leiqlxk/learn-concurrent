package org.lql.chapterseven;

import lombok.extern.slf4j.Slf4j;

import java.sql.SQLOutput;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Title: TestForkJoin <br>
 * ProjectName: learn-concurrent <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/12 20:32 <br>
 */
@Slf4j(topic = "c.TestForkJoin")
public class TestForkJoin {

    public static void main(String[] args) {

        ForkJoinPool pool = new ForkJoinPool(4);
        System.out.println(pool.invoke(new MyTask(5)));

        // new MyTask(5): 5 + new MyTask(4) : 4 + MyTask(3) ... 2 + new MyTask(1)

    }

}

@Slf4j(topic = "c.MyTask")
class MyTask extends RecursiveTask<Integer> {

    private int n;

    public MyTask(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "{" + n + "}";
    }

    @Override
    protected Integer compute() {

        // 终止条件
        if (n == 1) {
            log.debug("join() {}", n);
            return 1;
        }

        MyTask t1 = new MyTask(n - 1);
        // 让一个线程去执行此任务
        t1.fork();
        log.debug("fork() {} + {}", n, t1);

        // 获取t1的结果
        int result = n + t1.join();
        log.debug("join() {} + {} = {}", n, t1, result);
        return result;
    }
}
