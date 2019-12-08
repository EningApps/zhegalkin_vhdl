package main.tojava;

public class TriangleMethod {

    private static int[] input = new int[]{0, 1, 1, 0, 1, 0, 0, 1};

    public static void main(String[] args) throws Exception {

        if (!isPowerOfTwo(input.length)) {
            throw new Exception("Incorrect input");
        }

        int literalsCount = log(input.length, 2);

        String result;

        if (input.length == 1) {
            result = String.valueOf(input[0]);
        } else {

            int[] coefs = calculateCoefs();
            for (int i = 0; i < coefs.length; i++) {
                System.out.print(coefs[i] + " ");
            }
            String zhegalkinPolinomial = "";
            for (int i = 0; i < coefs.length; i++) {
                if (coefs[i] == 1) {
                    zhegalkinPolinomial += getConjectionAtLine(i, literalsCount) + " ⊕ ";
                }
            }
            result = zhegalkinPolinomial.substring(0, zhegalkinPolinomial.length() - 2);
        }

        System.out.println();
        System.out.println(result);
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

    private static String getConjectionAtLine(int lineNumber, int literalsCount) {
        if (lineNumber == 0) {
            return "1";
        }
        String conjection = "";
        for (int i = literalsCount; i > 0; i--) {
            if ((lineNumber & 1 << i - 1) != 0) {
                conjection += "X" + i;
            }
        }
        return conjection;
    }

    private static int log(int x, int base) {
        return (int) (Math.log(x) / Math.log(base));
    }

    private static boolean isPowerOfTwo(int number) {
        return number > 0 && ((number & (number - 1)) == 0);
    }
}
