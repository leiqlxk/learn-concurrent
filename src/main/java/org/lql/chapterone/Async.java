package org.lql.chapterone;

import lombok.extern.slf4j.Slf4j;
import org.lql.constants.Constants;
import org.lql.util.FileReader;

/**
 * Title: Async <br>
 * ProjectName: learn-concurrent <br>
 * description: 异步不等待 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/9/30 23:46 <br>
 */
@Slf4j(topic = "c.Async")
public class Async {

    public static void main(String[] args) {
        new Thread(() -> FileReader.read(Constants.MP4_FULL_PATH)).start();
        log.debug("do other things....");
    }
}
