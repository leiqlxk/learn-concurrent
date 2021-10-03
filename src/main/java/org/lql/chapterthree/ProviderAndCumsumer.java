package org.lql.chapterthree;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * Title: ProviderAndCumsumer <br>
 * ProjectName: learn-concurrent <br>
 * description: 生产者消费者 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/3 11:26 <br>
 */
@Slf4j(topic = "c.ProviderAndCumsumer")
public class ProviderAndCumsumer {

    public static void main(String[] args) throws InterruptedException {
        MessageQueue queue = new MessageQueue(2);

        for (int i = 0; i < 3; i++) {
            int id = i;
            new Thread(() -> {
                try {
                    queue.put(new Message(id, "值" + id));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "生产者" + i).start();
        }

        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    Message take = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "消费者").start();
    }
}

// 消息队列类,java线程之间通信
@Slf4j(topic = "c.MessageQueue")
class MessageQueue {
    // 消息队列集合
    private final LinkedList<Message> list = new LinkedList<>();
    // 消息队列容量
    private int capcity;

    public MessageQueue(int capcity) {
        this.capcity = capcity;
    }

    // 获取消息方法
    public Message take() throws InterruptedException {
        synchronized (list) {
            // 检查队列是否为空
            while (list.isEmpty()) {
                log.debug("队列为空，消费者线程等待");
                list.wait();
            }

            // 从队列头部获取消息返回
            Message message = list.removeFirst();
            log.debug("已消费消息 {}", message);
            list.notifyAll();
            return message;
        }
    }

    // 存入消息
    public void put(Message message) throws InterruptedException {
        synchronized (list) {
            // 检查队列是否已满
            while (list.size() == capcity) {
                log.debug("对列已满，生产者线程等待");
                list.wait();
            }

            // 将消息加入队列尾部
            list.addLast(message);
            log.debug("已生产消息 {}", message);
            list.notifyAll();
        }
    }
}

// 消息对象
final class Message {
    private int id;

    private Object value;

    public Message(int id, Object value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
