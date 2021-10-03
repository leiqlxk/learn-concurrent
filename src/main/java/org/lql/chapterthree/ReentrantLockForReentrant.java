package org.lql.chapterthree;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Title: ReentrantLock <br>
 * ProjectName: learn-concurrent <br>
 * description: ReentrantLock 可重入测试 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/3 23:35 <br>
 */
@Slf4j(topic = "c.ReentrantLock")
public class ReentrantLockForReentrant {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        try {
            log.debug("enter main...");
            method1();
        }finally {
            lock.unlock();
        }
    }

    public static void method1() {
        lock.lock();
        try {
            log.debug("enter method1...");
            method2();
        }finally {
            lock.unlock();
        }
    }

    private static void method2() {
        lock.lock();
        try {
            log.debug("enter method2...");
        }finally {
            lock.unlock();
        }
    }
}
