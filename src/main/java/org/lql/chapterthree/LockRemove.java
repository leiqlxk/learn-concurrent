package org.lql.chapterthree;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Title: LockRemove <br>
 * ProjectName: learn-concurrent <br>
 * description: 锁消除测试 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/2 21:01 <br>
 */
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class LockRemove {
    static int x = 0;

    @Benchmark
    public void a() throws Exception {
        x++;
    }

    @Benchmark
    // JIT 即时编译器优化，分析object对象不可能被共享，则会取消锁
    public void b() throws Exception{
        Object o = new Object();
        synchronized (o) {
            x++;
        }
    }
}
