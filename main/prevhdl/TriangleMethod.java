package main.prevhdl;

public class TriangleMethod {

    private static int[] input = new int[]{0, 1, 1, 0, 1, 0, 0, 1};

    public static void main(String[] args) throws Exception {

        if (!isPowerOfTwo(input.length)) {
            throw new Exception("Incorrect input");
        }

        int literalsCount = log(input.length, 2);

        int[][] resultMatrix = new int[input.length][literalsCount * 2];

        if (input.length == 1) {
            resultMatrix[0][0] = input[0];
            resultMatrix[0][1] = input[1];
        } else {

            int[] coefs = calculateCoefs();

            for (int i = 0; i < coefs.length; i++) {
                System.out.print(coefs[i] + " ");
            }

            for (int i = 0, matrixIndex = 0; i < coefs.length; i++) {
                if (coefs[i] == 1) {
                    putConjectionAtLine(i, literalsCount, resultMatrix, matrixIndex);
                    matrixIndex++;
                }
            }
        }

        int[][] a1 = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},

        };

        int[][] a2 = new int[3][3];
        //Utils.copyWithoutLine(a1, a2, 2);

        System.out.println();
    }

    private static int[] calculateCoefs() {
        int size = input.length;
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            matrix[0][i] = input[i];
        }
        for (int i = 1; i < size; i++) {
            for (int j = 1; j < size; j++) {
                matrix[i][j] = (matrix[i - 1][j - 1] + matrix[i - 1][j]) % 2;
            }
        }
        int[] coefs = new int[size];
        for (int i = 0; i < size; i++) {
            coefs[i] = matrix[i][i];
        }
        return coefs;
    }

    private static void putConjectionAtLine(int lineNumber, int literalsCount, int[][] resultMatrix, int matrixIndex) {
        int matrixWidth = literalsCount * 2;

        if (lineNumber == 0) {
            for (int i = 0; i < matrixWidth; i += 2) {
                resultMatrix[matrixIndex][i] = 1;
                resultMatrix[matrixIndex][i + 1] = 0;
            }
            return;
        }
        for (int i = literalsCount, j = matrixWidth - 1; i > 0; i--, j -= 2) {
            if ((lineNumber & 1 << i - 1) != 0) {
                resultMatrix[matrixIndex][j] = 1;
                resultMatrix[matrixIndex][j - 1] = 0;
            } else {
                resultMatrix[matrixIndex][j] = 0;
                resultMatrix[matrixIndex][j - 1] = 0;
            }
        }
    }

    private static int log(int x, int base) {
        return (int) (Math.log(x) / Math.log(base));
    }

    private static boolean isPowerOfTwo(int number) {
        return number > 0 && ((number & (number - 1)) == 0);
    }
}
