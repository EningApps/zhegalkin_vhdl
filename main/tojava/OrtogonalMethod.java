package main.tojava;

import java.util.ArrayList;
import java.util.List;

import static main.tojava.Utils.*;


public class OrtogonalMethod {

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
        int[][] result = convertTripleMatrix(input1);
        List<List<Integer>> listMatrix = convertMatrixToNestedList(result);
        listMatrix = replaceInversions(listMatrix);
        result = convertNestedListToMatrix(listMatrix);
        printMatrix(result);
    }

    public static List<List<Integer>> replaceInversions(List<List<Integer>> listMatrix) {
        for (int i = 0; i < listMatrix.size(); i++) {
            List<Integer> row = listMatrix.get(i);
            for (int j = 0; j < row.size(); j += 2) {
                boolean isInversion = row.get(j) == 0 && row.get(j + 1) == 1;
                if (isInversion) {
                    int inversionIndex = j;
                    List<Integer> newConjection1 = new ArrayList<>();
                    List<Integer> newConjection2 = new ArrayList<>();
                    for (int k = 0; k < row.size(); k += 2) {
                        if (k == inversionIndex) {
                            int[] lit = convertLiteral('1');
                            newConjection1.add(lit[0]);
                            newConjection1.add(lit[1]);
                            int[] space = convertLiteral('-');
                            newConjection2.add(space[0]);
                            newConjection2.add(space[1]);
                        } else {
                            int[] lit = new int[]{row.get(k), row.get(k + 1)};
                            newConjection1.add(lit[0]);
                            newConjection1.add(lit[1]);
                            newConjection2.add(lit[0]);
                            newConjection2.add(lit[1]);
                        }
                    }
                    listMatrix.remove(i--);
                    listMatrix.add(newConjection1);
                    listMatrix.add(newConjection2);
                    break;
                }
            }
        }
        listMatrix = removeSameLines(listMatrix);
        return listMatrix;
    }

    private static List<List<Integer>> removeSameLines(List<List<Integer>> nestedList) {
        for (int i = 0; i < nestedList.size() - 1; i++) {
            for (int j = i + 1; j < nestedList.size(); j++) {
                if (isRowsEqual(nestedList.get(i), nestedList.get(j))) {
                    nestedList.remove(i);
                    nestedList.remove(j - 1);
                    break;
                }
            }

        }
        return nestedList;
    }
}
