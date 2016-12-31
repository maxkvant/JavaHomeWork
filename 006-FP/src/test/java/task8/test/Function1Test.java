package task8.test;

import task8.Function1;
import task8.Function2;

import static org.junit.Assert.assertEquals;

public class Function1Test {
    @org.junit.Test
    public void composeTest() throws Exception {
        Function1 longToString = new Function1<String,Long>() {
            @Override
            public String apply(Long x) {
                return String.valueOf(x);
            }
        };
        Function1 sqr = new Function1<Long, Integer>() {
            @Override
            public Long apply(Integer x) {
                return Long.valueOf(x) * Long.valueOf(x);
            }
        };
        assertEquals("25", longToString.compose(sqr).apply(5));
        Function2 distance = new Function2<Long, Integer, Integer>() {
            public Long apply(Integer a, Integer b) {
                Long la = Long.valueOf(a);
                Long lb = Long.valueOf(b);
                return lb * lb + la * la;
            }
        };
        assertEquals("169", longToString.compose(distance).apply(5, 12));
    }

}