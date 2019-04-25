/**
 * Created by hadar on 30/03/2019.
 */
public class Transformations {

    public Matrix translate(double Tx, double Ty) {
        double[][] m = new double[][]{
                {1, 0, 0, Tx},
                {0, 1, 0, Ty},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        return new Matrix(m);
    }

    public Matrix scale(double s) {
        double[][] m = new double[][]{
                {s, 0, 0, 0},
                {0, s, 0, 0},
                {0, 0, s, 0},
                {0, 0, 0, 1}
        };
        return new Matrix(m);
    }

    public Matrix rotate(char axis, double teta) {
        double[][] m = new double[4][4];
        double cosT = Math.cos(teta);
        double sinT = Math.sin(teta);

        switch (axis) {
            case 'X':
                m = new double[][]{
                        {cosT, -sinT, 0, 0},
                        {sinT, cosT, 0, 0},
                        {0, 0, 1, 0},
                        {0, 0, 0, 1}
                };
                break;
            case 'Y':
                m = new double[][]{
                        {1, 0, 0, 0},
                        {0, cosT, -sinT, 0},
                        {0, sinT, cosT, 0},
                        {0, 0, 0, 1}
                };
                break;
            case 'Z':
                m = new double[][]{
                        {1, 0, 0, 0},
                        {0, 1, 0, 0},
                        {0, 0, cosT, -sinT},
                        {0, 0, sinT, cosT}
                };
                break;
            default:
                break;
        }

        return new Matrix(m);
    }
}
