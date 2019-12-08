package main.tojava;

import java.util.ArrayList;
import java.util.List;

public class Utils {


    public static int[] convertLiteral(char literal) {
        switch (literal) {
            case '0':
                return new int[]{0, 1};
            case '1':
                return new int[]{1, 0};
            default:
                //case '-'
                return new int[]{0, 0};
        }
    }

    public static int[][] convertTripleMatrix(char[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        int[][] result = new int[height][width * 2];
        for (int i = 0; i < height; i++) {
            for (int j = 0, k = 0; j < width; j++) {
                int[] code = convertLiteral(matrix[i][j]);
                result[i][k++] = code[0];
                result[i][k++] = code[1];
            }
        }
        return result;
    }

    public static List<List<Integer>> convertMatrixToNestedList(int[][] matrix) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            result.add(new ArrayList<>());
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                result.get(i).add(matrix[i][j]);
            }
        }
        return result;
    }

    public static int[][] convertNestedListToMatrix(List<List<Integer>> nestedList) {
        int[][] result = new int[nestedList.size()][nestedList.get(0).size()];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = nestedList.get(i).get(j);
            }
        }
        return result;
    }

    public static boolean isRowsEqual(List<Integer> list1, List<Integer> list2) {
        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i)))
                return false;
        }
        return true;
    }

    public static List<Integer> multiplyLines(List<Integer> list1, List<Integer> list2) {
        List<Integer> result = new ArrayList<>();
        if (isConjectionsOrtogonal(list1, list2)) {
            return result;
        } else {

            for (int i = 0; i < list1.size(); i += 2) {
                int[] mult = multiplyLiterals(
                        new int[]{list1.get(i), list1.get(i + 1)},
                        new int[]{list2.get(i), list2.get(i + 1)}
                );
                result.add(mult[0]);
                result.add(mult[1]);
            }
            return result;
        }
    }

    public static int[] multiplyLiterals(int[] lit1, int[] lit2) {
        int[] result = new int[2];
        if (lit1[0] == 0 && lit1[1] == 0) {
            result[0] = lit2[0];
            result[1] = lit2[1];
        } else if (lit2[0] == 0 && lit2[1] == 0) {
            result[0] = lit1[0];
            result[1] = lit1[1];
        } else {
            result[0] = lit1[0] & lit2[0];
            result[1] = lit1[1] & lit2[1];
        }
        return result;
    }

    public static boolean isConjectionsOrtogonal(List<Integer> list1, List<Integer> list2) {
        for (int i = 0; i < list1.size(); i += 2) {
            int[] lit1 = new int[]{list1.get(i), list1.get(i + 1)};
            int[] lit2 = new int[]{list2.get(i), list2.get(i + 1)};
            if ((lit1[0] == 0 && lit1[1] == 1) && (lit2[0] == 1 && lit2[1] == 0) ||
                    (lit1[0] == 1 && lit1[1] == 0) && (lit2[0] == 0 && lit2[1] == 1)) {
                return true;
            }
        }
        return false;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
