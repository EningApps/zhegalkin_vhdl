package main.prevhdl;

import java.util.List;

import static main.prevhdl.Constants.MATRIX_HEIGHT;
import static main.prevhdl.Constants.MATRIX_WIDTH;
import static main.prevhdl.OrtogonalMethod.replaceInversions;
import static main.prevhdl.Utils.copyMatrix;
import static main.prevhdl.Utils.createMatrixWithEmptyValues;
import static main.prevhdl.Utils.printMatrix;


public class NonortogonalMethod {

    static int[][] input = new int[][]{
            {1, 0, 1, 0, 0, 1},
            {1, 0, 0, 0, 1, 0},
            {0, 0, 1, 0, 1, 0},
    };

    static char[][] input1 = new char[][]{
            {'1', '1', '0'},
            {'1', '-', '1'},
            {'-', '1', '1'},
    };

    public static void main(String[] args) {

        int height = input.length;
        int width = input[0].length;

        int[][] emptyMatrix = createMatrixWithEmptyValues(MATRIX_WIDTH, MATRIX_HEIGHT);
        copyMatrix(input, emptyMatrix);

        int[][] result = replaceConjections(emptyMatrix, width, height);
        int valuesHeight = Utils.getMatrixHeight(result);

        result = replaceInversions(result, width, valuesHeight);
        System.out.println("------------");
        printMatrix(result);
    }

    private static int[][] replaceConjections(int[][] matrix, int width, int height) {
        int currentHeight = height;

        if (currentHeight % 2 == 0) {

            for (int i = 0; i < currentHeight - 1; i++) {
                int[] line1 = Utils.getMatrixLine(matrix, i);
                int[] line2 = Utils.getMatrixLine(matrix, i + 1);
                int[] mult = Utils.multiplyLines(line1, line2);
                if (!Utils.isLineEmpty(mult)) {
                    Utils.addLineToMatrix(matrix, mult, currentHeight++);
                }
            }

        } else {

            for (int i = 0; i < currentHeight - 2; i++) {
                int[] line1 = Utils.getMatrixLine(matrix, i);
                int[] line2 = Utils.getMatrixLine(matrix, i + 1);
                int[] mult = Utils.multiplyLines(line1, line2);
                if (!Utils.isLineEmpty(mult)) {
                    Utils.addLineToMatrix(matrix, mult, currentHeight++);
                }
            }

            int[] lastLine = Utils.getMatrixLine(matrix, height - 1);

            for (int i = 0; i < currentHeight - 1; i++) {
                if (i == height - 1) {
                    continue;
                } else {
                    int[] line1 = Utils.getMatrixLine(matrix, i);
                    int[] mult = Utils.multiplyLines(line1, lastLine);
                    if (!Utils.isLineEmpty(mult)) {
                        Utils.addLineToMatrix(matrix, mult, currentHeight++);
                    }
                }
            }
        }
        return matrix;
    }
}
