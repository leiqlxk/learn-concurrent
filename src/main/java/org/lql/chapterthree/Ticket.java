package org.lql.chapterthree;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * Title: Ticket <br>
 * ProjectName: learn-concurrent <br>
 * description: TODO <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/1 22:17 <br>
 */
@Slf4j(topic = "c.Ticket")
public class Ticket {

    // random为线程安全
    static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        // 模拟多人购票
        TicketWindow window = new TicketWindow(100);
        // 卖出的票数统计
        List<Integer> amountList = new Vector<>();
        // 所有线程的集合
        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < 6000; i++) {
            Thread thread = new Thread(() -> {
                int amount = window.sell(randomAmount());
                amountList.add(amount);
            }, i + "");
            threadList.add(thread);
        }

        for (Thread t : threadList) {
            t.start();
        }

        for (Thread thread : threadList) {
            thread.join();
        }
        // 统计卖出的票数和剩余的票数
        log.debug("余票：{}", window.getCount());
        log.debug("卖出的票数：{}", amountList.stream().mapToInt(i -> i).sum());
    }

    // 随机1~5
    public static int randomAmount() {
        return random.nextInt(5) + 1;
    }

}

class TicketWindow {
    private int count;

    public TicketWindow(int i) {
        this.count = i;
    }

    // 获取余票数量
    public int getCount() {
        return count;
    }

    // 售票
    public synchronized int sell(int amount) {
        if (this.count >= amount) {
            this.count -= amount;
            return amount;
        }else {
            return 0;
        }
    }
}
