package org.lql.chapterone;

import org.junit.Test;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Title: MyBenchmarkTest <br>
 * ProjectName: learn-concurrent <br>
 * description: 基准测试 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/1 0:13 <br>
 */
public class MyBenchmarkTest {

    @Test
    public void test() throws RunnerException {
        Options options = new OptionsBuilder()
                .include(MyBenchmark.class.getSimpleName())
//                .output("/temp/mybenchmark.log")
                .build();
        new Runner(options).run();
    }
}
