package org.lql.chapterseven;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Title: TestForkJion2 <br>
 * ProjectName: learn-concurrent <br>
 * description: 拆分优化 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/12 20:47 <br>
 */
@Slf4j(topic = "c.TestForkJion2")
public class TestForkJion2 {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(4);
        System.out.println(pool.invoke(new AddTak(1, 5)));
    }
}

@Slf4j(topic = "c.AddTak")
class AddTak extends RecursiveTask<Integer> {

    private int begin;
    private int end;

    public AddTak(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String toString() {
        return "{" + begin + "," + end + '}';
    }

    @Override
    protected Integer compute() {
        if (begin == end) {
            log.debug("join() {}", begin);
            return begin;
        }

        if (end - begin == 1) {
            log.debug("join() {} + {} = {}", begin, end, begin + end);
            return begin + end;
        }

        int mid = (begin + end) / 2;

        AddTak t1 = new AddTak(begin, mid);
        t1.fork();
        AddTak t2 = new AddTak(mid + 1, end);
        t2.fork();
        log.debug("fork() {} + {} = ?", t1, t2);

        int result = t1.join() + t2.join();
        log.debug("join() {} + {} = {}", t1, t2, result);

        return result;
    }
}
