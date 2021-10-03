package org.lql.chapterthree;

import org.junit.Test;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Title: LockRemove <br>
 * ProjectName: learn-concurrent <br>
 * description: 锁取消测试 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/2 21:03 <br>
 */
public class LockRemoveTest {

    @Test
    public void test() throws RunnerException {
        Options options = new OptionsBuilder()
                .include(LockRemove.class.getSimpleName())
                .build();
        new Runner(options).run();
    }
}
