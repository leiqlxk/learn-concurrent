package org.lql.chapterthree;

import lombok.extern.slf4j.Slf4j;
import org.lql.util.HeaderUtil;
import org.openjdk.jol.info.ClassLayout;

import static org.lql.util.HeaderUtil.*;

/**
 * Title: TestBiase <br>
 * ProjectName: learn-concurrent <br>
 * description: 偏向锁测试 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/2 16:24 <br>
 */
@Slf4j(topic = "c.TestBiased")
public class TestBiased {

    public static void main(String[] args) {
        DogBiased dogBiased = new DogBiased();
        log.debug("synchronized 前");
        log.debug(getMarkWord(ClassLayout.parseInstance(dogBiased).toPrintable(), true));

        synchronized (dogBiased) {
            log.debug("synchronized 中");
            log.debug(getMarkWord(ClassLayout.parseInstance(dogBiased).toPrintable(), true));
        }

        log.debug("synchronized 后");
        log.debug(getMarkWord(ClassLayout.parseInstance(dogBiased).toPrintable(),true));
    }
}

class DogBiased {}
