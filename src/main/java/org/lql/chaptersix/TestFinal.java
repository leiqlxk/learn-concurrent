package org.lql.chaptersix;

/**
 * Title: TestFinal <br>
 * ProjectName: learn-concurrent <br>
 * description: final原理 <br>
 *
 * @author: leiql <br>
 * @version: 1.0 <br>
 * @since: 2021/10/7 23:48 <br>
 */
public class TestFinal {
    final static int A = 10;
    final static int B = Short.MAX_VALUE + 1;

    final int a = 20;
    final int b = Integer.MAX_VALUE;

    final void test() {}

}

class UseFinal1{
    public void test() {
        System.out.println(TestFinal.A);
        System.out.println(TestFinal.B);
        System.out.println(new TestFinal().a);
        System.out.println(new TestFinal().b);
        new TestFinal().test();
    }
}


class UseFinal2{
    public void test() {
        System.out.println(TestFinal.A);
    }
}
