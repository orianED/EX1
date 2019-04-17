import java.util.ArrayList;

/**
 * Created by hadar on 02/04/2019.
 */
public class Matrix {

    private double[][] m;

    public Matrix(double[][] m) {
        this.m = m;
    }

    public int getColNum() {
        int length = m[0] == null ? 0 : m[0].length;
        return length;
    }

    public int getRowNum() {
        return this.m.length;
    }

    public void reset() {
        this.m = new double[][]{
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
    }

    public Matrix mult(Matrix other) {
        double[][] res = new double[this.getColNum()][other.getRowNum()];

        for (int i = 0; i < this.getRowNum(); i++) {
            for (int j = 0; j < other.getColNum(); j++) {
                int sum = 0;
                for (int x = 0; x < this.getColNum(); x++) {
                    sum += this.m[i][x] * other.m[x][j];
                }
                res[i][j] = sum;
            }
        }
        return new Matrix(res);
    }
}
