package task8.test;

import task8.Function2;

import static org.junit.Assert.assertEquals;

public class Function2Test {
    private final Function2<Integer, Integer, Integer> f = new Function2<Integer, Integer, Integer>() {
        @Override
        public Integer apply(Integer x, Integer y) {
            return x - y;
        }
    };

    @org.junit.Test
    public void applyTest() throws Exception {
        assertEquals((int)f.apply(4,-1), 5);
    }

    @org.junit.Test
    public void bind1Test() throws Exception {
        assertEquals((int)f.bind1(4).apply(-1), 5);
    }

    @org.junit.Test
    public void bind2Test() throws Exception {
        assertEquals((int)f.bind2(-1).apply(4), 5);
    }

    @org.junit.Test
    public void curryTest() throws Exception {
        assertEquals((int)f.curry().apply(-1).apply(4), 5);
    }

}