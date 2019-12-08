package main.prevhdl;

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

    public static boolean isRowsEqual(int[] row1, int[] row2) {
        for (int i = 0; i < row1.length; i++) {
            if (row1[i] != row2[i])
                return false;
        }
        return true;
    }

    public static int[] multiplyLines(int[] line1, int[] line2) {
        int[] result = createEmptyLine(line1.length);
        if (isConjectionsOrtogonal(line1, line2)) {
            return result;
        } else {

            for (int i = 0; i < line1.length; i += 2) {
                int[] mult = multiplyLiterals(line1[i], line1[i + 1], line2[i], line2[i + 1]);
                result[i] = mult[0];
                result[i + 1] = mult[1];
            }
            return result;
        }
    }

    public static int[] multiplyLiterals(int lit11, int lit12, int lit21, int lit22) {
        int[] result = new int[2];
        if (lit11 == 0 && lit12 == 0) {
            result[0] = lit21;
            result[1] = lit22;
        } else if (lit21 == 0 && lit22 == 0) {
            result[0] = lit11;
            result[1] = lit12;
        } else {
            result[0] = lit11 & lit21;
            result[1] = lit12 & lit22;
        }
        return result;
    }

    public static boolean isConjectionsOrtogonal(int[] line1, int[] line2) {
        for (int i = 0; i < line1.length; i += 2) {
            if ((line1[i] == 0 && line1[i + 1] == 1) && (line2[i] == 1 && line2[i + 1] == 0) ||
                    (line1[i] == 1 && line1[i + 1] == 0) && (line2[i] == 0 && line2[i + 1] == 1)) {
                return true;
            }
        }
        return false;
    }

    public static int[] createEmptyLine(int width) {
        int[] line = new int[width];
        for (int i = 0; i < width; i++) {
            line[i] = -1;
        }
        return line;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] copyWithoutLine(int[][] original, int width, int height, int lineToRemove) {
        int[][] newMatrix = createMatrixWithEmptyValues(width, height);

        for (int i = 0, j = 0; i < height; i++) {

            if (i == lineToRemove) {
                continue;
            }

            for (int k = 0; k < width; k++) {

                newMatrix[j][k] = original[i][k];
            }

            j++;
        }
        return newMatrix;
    }

    public static void addLineToMatrix(int[][] matrix, int[] line, int index) {
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[index][i] = line[i];
        }
    }

    public static int[] getMatrixLine(int[][] matrix, int i) {
        int width = matrix[i].length;
        int[] line = new int[width];
        for (int j = 0; j < width; j++) {
            line[j] = matrix[i][j];
        }
        return line;
    }

    public static int[][] createMatrixWithEmptyValues(int width, int height) {
        int[][] result = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                result[i][j] = -1;
            }
        }
        return result;
    }

    public static void copyMatrix(int[][] from, int[][] to) {
        int height = from.length;
        int width = from[0].length;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                to[i][j] = from[i][j];
            }
        }
    }

    public static boolean isLineEmpty(int[] line) {
        for (int i = 0; i < line.length; i++) {
            if (line[i] != -1) {
                return false;
            }
        }
        return true;
    }

    public static int getMatrixHeight(int[][] matrix) {
        int height = 0;
        for (int i = 0; i < matrix.length; i++) {
            int[] line = getMatrixLine(matrix, i);
            if (!isLineEmpty(line)) {
                height++;
            }
        }
        return height;
    }
}
