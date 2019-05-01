/**
 * Name: Orian Edri ID: 308335454
 * Name: Hadar Sabag ID: 312497126
 */

import java.util.ArrayList;
import java.util.List;

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

    /**
     * set matrix to I
     */
    public void reset() {
        this.m = new double[][]{
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
    }

    /**
     * multiply 2 matrix
     * @param other matrix
     * @return result matrix
     */
    public Matrix mult(Matrix other) {
        double[][] res = new double[this.getColNum()][other.getRowNum()];

        for (int i = 0; i < this.getRowNum(); i++) {
            for (int j = 0; j < other.getColNum(); j++) {
                double sum = 0;
                for (int x = 0; x < this.getColNum(); x++) {
                    sum += this.m[i][x] * other.m[x][j];
                }
                res[i][j] = sum;
            }
        }
        return new Matrix(res);
    }

    /**
     * multiply matrix and vertex
     * @param v vertex
     * @return result vertex
     */
    public Vertex mult(Vertex v) {
        //padding the vector with 1 to make it 4*1
        double x = m[0][0] * v.getX() + m[0][1] * v.getY() + m[0][2] * v.getZ() + m[0][3] * 1;
        double y = m[1][0] * v.getX() + m[1][1] * v.getY() + m[1][2] * v.getZ() + m[1][3] * 1;
        double z = m[2][0] * v.getX() + m[2][1] * v.getY() + m[2][2] * v.getZ() + m[2][3] * 1;

        return new Vertex(x, y, z);
    }

    /**
     * multiply list of vertex
     * @param vl vertexes to multiply
     * @return list of results
     */
    public List<Vertex> mult(List<Vertex> vl) {
        List<Vertex> new_vl = new ArrayList<>(vl.size());

        for (Vertex v : vl) {
            new_vl.add(this.mult(v));
        }

        return new_vl;
    }
}
