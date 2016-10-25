package task8.test;

import static org.junit.Assert.*;

import com.sun.org.apache.xalan.internal.xsltc.dom.StepIterator;
import task8.*;

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
        assertTrue(longToString.compose(sqr).apply(5).equals("25"));
    }

}