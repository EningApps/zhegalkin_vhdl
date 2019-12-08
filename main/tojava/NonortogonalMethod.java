package main.tojava;

import javax.rmi.CORBA.Util;
import java.util.List;

import static main.tojava.Utils.*;

public class NonortogonalMethod {

    static char[][] input1 = new char[][]{
            {'1', '1', '0'},
            {'1', '-', '1'},
            {'-', '1', '1'},
    };

    public static void main(String[] args) {
        int[][] result = convertTripleMatrix(input1);
        List<List<Integer>> listMatrix = convertMatrixToNestedList(result);
        listMatrix = replaceConjections(listMatrix);
        listMatrix = OrtogonalMethod.replaceInversions(listMatrix);
        result = convertNestedListToMatrix(listMatrix);
        printMatrix(result);

    }

    private static List<List<Integer>> replaceConjections(List<List<Integer>> listMatrix) {
        int originalSize = listMatrix.size();
        if (originalSize % 2 == 0) {
            for (int i = 0; i < originalSize - 1; i++) {
                List<Integer> mult = multiplyLines(listMatrix.get(i), listMatrix.get(i + 1));
                if (!mult.isEmpty()) {
                    listMatrix.add(mult);
                }
            }
        } else {
            for (int i = 0; i < originalSize - 2; i++) {
                List<Integer> mult = multiplyLines(listMatrix.get(i), listMatrix.get(i + 1));
                if (!mult.isEmpty()) {
                    listMatrix.add(mult);
                }
            }
            List<Integer> lastLine = listMatrix.get(originalSize - 1);
            for (int i = 0; i < listMatrix.size() - 1; i++) {
                if (i == originalSize - 1) {
                    continue;
                } else {
                    List<Integer> mult = multiplyLines(listMatrix.get(i), lastLine);
                    if (!mult.isEmpty()) {
                        listMatrix.add(mult);
                    }
                }
            }
        }
        return listMatrix;
    }
}
