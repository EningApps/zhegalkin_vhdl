package main.prevhdl;

import static main.prevhdl.Constants.MATRIX_HEIGHT;
import static main.prevhdl.Constants.MATRIX_WIDTH;
import static main.prevhdl.Utils.*;


public class OrtogonalMethod {

    static int[][] input = new int[][]{
            {0, 1, 1, 0, 1, 0},
            {1, 0, 0, 1, 1, 0},
            {1, 0, 1, 0, 0, 1},
            {1, 0, 1, 0, 1, 0}
    };

    static char[][] input1 = new char[][]{
            {'0', '1', '1'},
            {'1', '0', '1'},
            {'1', '1', '0'},
            {'1', '1', '1'}
    };

    static char[][] input2 = new char[][]{
            {'0', '0', '0', '1'},
            {'1', '0', '0', '0'},
            {'0', '0', '1', '0'},
            {'1', '1', '1', '0'},
            {'0', '1', '-', '0'},
    };

    public static void main(String[] args) {
        int height = input.length;
        int width = input[0].length;

        int[][] emptyMatrix = createMatrixWithEmptyValues(MATRIX_WIDTH, MATRIX_HEIGHT);
        copyMatrix(input, emptyMatrix);

        int[][] result = replaceInversions(emptyMatrix, width, height);

        System.out.println("RESULT:");
        Utils.printMatrix(result);

    }

    public static int[][] replaceInversions(int[][] matrix, int width, int height) {
        int currentHeight = height;
        for (int i = 0; i < currentHeight; i++) {
            for (int j = 0; j < width; j += 2) {
                boolean isInversion = matrix[i][j] == 0 && matrix[i][j + 1] == 1;
                if (isInversion) {

                    int inversionIndex = j;

                    int[] newConjection1 = new int[width];
                    int[] newConjection2 = new int[width];

                    for (int k = 0; k < width; k += 2) {
                        if (k == inversionIndex) {
                            //'1'
                            newConjection1[k] = 1;
                            newConjection1[k + 1] = 0;
                            //'-'
                            newConjection2[k] = 0;
                            newConjection2[k + 1] = 0;
                        } else {
                            int[] lit = new int[]{matrix[i][k], matrix[i][k + 1]};
                            newConjection1[k] = lit[0];
                            newConjection1[k + 1] = lit[1];
                            newConjection2[k] = lit[0];
                            newConjection2[k + 1] = lit[1];
                        }
                    }
                    System.out.println("BEFORE REMOVAL i=" + i + " currentHeight = " + currentHeight);
                    Utils.printMatrix(matrix);
                    matrix = copyWithoutLine(matrix, MATRIX_WIDTH, MATRIX_HEIGHT, i--);
                    System.out.println("AFTER REMOVAL i=" + i + " currentHeight = " + currentHeight);
                    Utils.printMatrix(matrix);
                    currentHeight--;
                    addLineToMatrix(matrix, newConjection1, currentHeight);
                    System.out.println("AFTER COPY1 i=" + i + " currentHeight = " + currentHeight);
                    Utils.printMatrix(matrix);
                    currentHeight++;
                    addLineToMatrix(matrix, newConjection2, currentHeight);
                    currentHeight++;
                    System.out.println("AFTER COPY2 i=" + i + " currentHeight = " + currentHeight);
                    Utils.printMatrix(matrix);
                    break;
                }
            }
        }
        matrix = removeSameLines(matrix, width, currentHeight);
        return matrix;
    }

    private static int[][] removeSameLines(int[][] matrix, int width, int height) {

        for (int i = 0; i < height - 1; i++) {
            for (int j = i + 1; j < height; j++) {
                int[] line1 = Utils.getMatrixLine(matrix, i);
                int[] line2 = Utils.getMatrixLine(matrix, j);
                if (isRowsEqual(line1, line2)) {

                    matrix = copyWithoutLine(matrix, width, height, i);
                    matrix = copyWithoutLine(matrix, width, height, j - 1);

                    break;
                }
            }

        }
        return matrix;
    }
}
