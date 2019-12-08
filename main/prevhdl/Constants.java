package main.prevhdl;

public class Constants {

    public static final int MATRIX_WIDTH = 6;
    public static final int INPUT_HEIGHT = 4;
    public static final int MATRIX_HEIGHT = INPUT_HEIGHT * factorial(MATRIX_WIDTH / 2);

    public static int factorial(int n) {
        if (n > 20) throw new IllegalArgumentException(n + " is out of range");
        return (1 > n) ? 1 : n * factorial(n - 1);
    }

}
