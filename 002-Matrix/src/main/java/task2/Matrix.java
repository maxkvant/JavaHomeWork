package task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Class stores Matrix (2k-1) * (2k-1)
 */

public class Matrix {
    private int [][] arr;

    public Matrix (int [][] arr) throws MatrixException {
        if (arr == null) {
            throw new MatrixException("null matrix");
        }
        this.arr = arr;
        int n = arr.length;
        if (n % 2 == 0) {
            throw new MatrixException("matrix N not odd");
        }
        for (int[] a : arr) {
            if (a.length != n) {
                throw new MatrixException("matrix not N x N");
            }
        }
    }

    /**
     * returns elements of this matrix in spiral order
     */

    public List<Integer> getSpiral() {
        int n = arr.length;
        int i = n / 2, j = n / 2;
        List<Integer> res = new ArrayList<>();
        for (int l = -1; l < n; l += 2) {
            for (int k = 0; k < l; k++) {
                res.add(arr[i][j]);
                i++;
            }
            for (int k = 0; k < l + 1; k++) {
                res.add(arr[i][j]);
                j--;
            }
            for (int k = 0; k < l + 1; k++) {
                res.add(arr[i][j]);
                i--;
            }
            for (int k = 0; k < l + 2; k++) {
                res.add(arr[i][j]);
                j++;
            }
        }
        return res;
    }

    private void rotate() {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int p = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = p;
            }
        }
    }

    /**
     * sort columns by first element
     */

    public void sortColumns() {
        rotate();
        Arrays.sort(arr, Comparator.comparing((int[] a) -> a[0]));
        rotate();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int v[] : arr) {
            for (int x : v) {
                res.append(x + " ");
            }
            res.append("\n");
        }
        return res.toString();
    }

    public static class MatrixException extends Exception {
        MatrixException(String message) {
            super(message);
        }
    }
}