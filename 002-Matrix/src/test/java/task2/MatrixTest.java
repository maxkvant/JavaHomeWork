package task2;

import org.junit.Test;
import task2.Matrix.MatrixException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MatrixTest {
    @Test
    public void getSpiralTest() throws MatrixException {
        Matrix matrix = new Matrix(new int[][]{
                new int[]{0, 1, 2, 3, 4},
                new int[]{5, 6, 7, 8, 9},
                new int[]{10, 11, 12, 13, 14},
                new int[]{15, 16, 17, 18, 19},
                new int[]{20, 21, 22, 23, 24}
        });

        List<Integer> expected = Arrays.asList(12, 13, 18, 17, 16, 11, 6, 7, 8, 9, 14, 19, 24, 23, 22, 21, 20, 15, 10, 5, 0, 1, 2, 3, 4);
        List<Integer> actual = matrix.getSpiral();
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @org.junit.Test
    public void toStringTest() throws MatrixException {
        Matrix matrix1 = new Matrix(new int[][]{new int[]{1}});
        assertEquals("1 \n", matrix1.toString());

        Matrix matrix2 = new Matrix(new int[][]{
                new int[]{3, 2, 1},
                new int[]{4, 3, 2},
                new int[]{5, 4, 3}
        });
        assertEquals("3 2 1 \n4 3 2 \n5 4 3 \n", matrix2.toString());
    }

    @org.junit.Test
    public void sortColumnsTest() throws MatrixException {
        Matrix matrix = new Matrix(new int[][]{
                new int[]{3, 1, 2},
                new int[]{4, 3, 2},
                new int[]{5, 4, 3}
        });
        matrix.sortColumns();
        assertEquals("1 2 3 \n3 2 4 \n4 3 5 \n", matrix.toString());
    }
}