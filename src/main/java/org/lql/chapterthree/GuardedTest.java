package org.lql.chapterthree;

import lombok.extern.slf4j.Slf4j;
import org.lql.util.Downloader;

import java.io.IOException;
import java.util.List;

/**
 * Title: GuardedTest <br>
 * ProjectName: learn-concurrent <br>
 * description: 保护性暂停模式 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/2 23:17 <br>
 */
@Slf4j(topic = "c.GuardedTest")
public class GuardedTest {

    public static void main(String[] args) {
        GuardedObject guardedObject = new GuardedObject();
        // 线程1 等待线程2的下载结果
        new Thread(() -> {
            try {
                // 等待结果
                log.debug("等待结果");
                List<String> list = (List<String>)guardedObject.get();
                log.debug("结果大小：{}", list.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            log.debug("执行下载");
            try {
                List<String> download = Downloader.download("https://www.baidu.com");
                guardedObject.complete(download);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}

class GuardedObject {
    // 结果
    private Object response;

    // 获取结果
    public Object get() throws InterruptedException {
        synchronized (this) {
            // 没有结果，虚假唤醒
            while (response == null) {
                this.wait();
            }

            return response;
        }
    }

    // 产生结果
    public void complete(Object response) {
        synchronized (this) {
            // 给结果成员变量赋值
            this.response = response;
            this.notifyAll();
        }
    }
}
