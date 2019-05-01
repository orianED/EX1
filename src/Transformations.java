/**
 * Name: Orian Edri ID: 308335454
 * Name: Hadar Sabag ID: 312497126
 */

public class Transformations {
    /**
     * performs translate transformation on matrix
     * @param Tx x translate param
     * @param Ty y translate param
     * @return result matrix
     */
    public Matrix translate(double Tx, double Ty) {
        double[][] m = new double[][]{
                {1, 0, 0, Tx},
                {0, 1, 0, Ty},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        return new Matrix(m);
    }

    /**
     * performs scale (by s) transformation on matrix
     * @param s scaling size
     * @return result matrix
     */
    public Matrix scale(double s) {
        double[][] m = new double[][]{
                {s, 0, 0, 0},
                {0, s, 0, 0},
                {0, 0, s, 0},
                {0, 0, 0, 1}
        };
        return new Matrix(m);
    }

    /**
     * performs rotate (by teta on axis) transformation on matrix
     * @param axis rotate on axis
     * @param teta the angle
     * @return the result matrix
     */
    public Matrix rotate(char axis, double teta) {
        double[][] m = new double[4][4];
        double cosT = Math.cos(teta);
        double sinT = Math.sin(teta);

        switch (axis) {
            case 'X':
                m = new double[][]{
                        {1, 0, 0, 0},
                        {0, cosT, -sinT, 0},
                        {0, sinT, cosT, 0},
                        {0, 0, 0, 1}
                };
                break;
            case 'Y':
                m = new double[][]{
                        {cosT, 0, -sinT, 0},
                        {0, 1, 0, 0},
                        {sinT, 0, cosT, 0},
                        {0, 0, 0, 1}
                };
                break;
            case 'Z':
                m = new double[][]{
                        {cosT, -sinT, 0, 0},
                        {sinT, cosT, 0, 0},
                        {0, 0, 1, 0},
                        {0, 0, 0, 1}
                };
                break;
            default:
                break;
        }

        return new Matrix(m);
    }
}
