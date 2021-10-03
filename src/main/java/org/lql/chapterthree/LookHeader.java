package org.lql.chapterthree;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

import static org.lql.util.HeaderUtil.getMarkWord;

/**
 * Title: LookHeader <br>
 * ProjectName: learn-concurrent <br>
 * description: 查看头信息 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/2 16:14 <br>
 */
@Slf4j(topic = "c.LookHeader")
public class LookHeader {

    public static void main(String[] args) throws InterruptedException {
        DogBiased dog = new DogBiased();
        log.debug(getMarkWord(ClassLayout.parseInstance(dog).toPrintable(), true));

        TimeUnit.SECONDS.sleep(4);

        log.debug(getMarkWord(ClassLayout.parseInstance(new DogBiased()).toPrintable(), true));
    }
}

class Dog {}
