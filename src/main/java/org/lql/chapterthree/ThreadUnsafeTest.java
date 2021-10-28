package org.lql.chapterthree;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * Title: ThreadUnsafeTest <br>
 * ProjectName: learn-concurrent <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/28 15:05 <br>
 */
@Slf4j(topic = "c.ThreadUnsafeTest")
public class ThreadUnsafeTest {
    static final int THREAD_NUMBER = 2;
    static final int LOOP_NUMBER = 200;

    public static void main(String[] args) {

        ThreadUnsafe test = new ThreadUnsafe();
        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(() -> {
                test.method1(LOOP_NUMBER);
            }, "Thread" + i).start();
        }
    }
}

@Slf4j(topic = "c.ThreadUnsafe")
class ThreadUnsafe {
    ArrayList<String> list = new ArrayList<>();

    public void method1(int loopNumber) {
        for (int i = 0; i < loopNumber; i++) {
            // { 临界区, 会产生竞态条件
            method2();
            method3();
            // } 临界区
        }
    }

    private void method2() {
        log.debug("1");
        list.add("1");
    }

    private void method3() {
        log.debug("2");
        list.remove(0);
    }
}
