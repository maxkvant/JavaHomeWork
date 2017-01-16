package sp;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class SecondPartTasksTest {

    @Test
    public void testFindQuotes() {
        fail();
    }

    @Test
    public void testPiDividedBy4() {
        double eps = 1e-3;
        assertTrue(Math.abs(Math.PI / 4 - SecondPartTasks.piDividedBy4()) < eps);
    }

    @Test
    public void testFindPrinter() {
        TreeMap<String, List<String> > map1 = new TreeMap<>();
        List<String> l1 = Arrays.asList(new String[3]);
        map1.put("1", Arrays.asList(new String[]{"a"}));
        assertEquals("1", SecondPartTasks.findPrinter(map1));
        map1.put("2", Arrays.asList(new String[]{"a", "b", "d"}));
        assertEquals("2", SecondPartTasks.findPrinter(map1));
        map1.put("0", Arrays.asList(new String[]{"abcd", "e"}));
        assertEquals("0", SecondPartTasks.findPrinter(map1));
        map1.put("3", Arrays.asList(new String[]{"a", "b"}));
        assertEquals("0", SecondPartTasks.findPrinter(map1));
    }

    @Test
    public void testCalculateGlobalOrder() {
        fail();
    }
}