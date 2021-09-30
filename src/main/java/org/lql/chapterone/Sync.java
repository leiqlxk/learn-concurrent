package org.lql.chapterone;

import lombok.extern.slf4j.Slf4j;
import org.lql.constants.Constants;
import org.lql.util.FileReader;

/**
 * Title: Sync <br>
 * ProjectName: learn-concurrent <br>
 * description: 同步等待 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/9/30 20:53 <br>
 */
@Slf4j(topic = "c.Sync")
public class Sync {

    public static void main(String[] args) {
        FileReader.read(Constants.MP4_FULL_PATH);
        log.debug("do other things");
    }
}
