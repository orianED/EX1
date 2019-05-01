import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class View {
    private Vertex position, lookAt, up;
    private double l, r, b, t;
    private int vw, vh;
    private final Matrix P = new Matrix(new double[][]{{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 1}});

    public void loadView(String filePath) {
        BufferedReader br = null;
        FileReader fr = null;
        String line;

        try {
            br = new BufferedReader(new FileReader(filePath));
            //position:
            line = br.readLine();
            String[] splitted = line.split(" ");
            this.position = this.createVertex(splitted);
            //lookAt:
            line = br.readLine();
            splitted = line.split(" ");
            this.lookAt = this.createVertex(splitted);
            //up:
            line = br.readLine();
            splitted = line.split(" ");
            this.up = this.createVertex(splitted);
            //windows:
            line = br.readLine();
            splitted = line.split(" ");
            this.l = Double.parseDouble(splitted[1]);
            this.r = Double.parseDouble(splitted[2]);
            this.b = Double.parseDouble(splitted[3]);
            this.t = Double.parseDouble(splitted[4]);
            //viewport:
            line = br.readLine();
            splitted = line.split(" ");
            this.vw = Integer.parseInt(splitted[1]);
            this.vh = Integer.parseInt(splitted[2]);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private Vertex createVertex(String[] coordinates) {
        double x = Double.parseDouble(coordinates[1]);
        double y = Double.parseDouble(coordinates[2]);
        double z = Double.parseDouble(coordinates[3]);

        return new Vertex(x, y, z);
    }

    public Matrix getMV1() {
        Vertex zv = new Vertex(position.getX() - lookAt.getX(), position.getY() - lookAt.getY(), position.getZ() - lookAt.getZ());
        zv = zv.mult(1 / zv.norm());
        Vertex xv = up.crossProduct(zv);
        xv = xv.mult(1 / xv.norm());
        Vertex yv = zv.crossProduct(xv);
        double[][] mr = new double[][]{
                {xv.getX(), xv.getY(), xv.getZ(), 0},
                {yv.getX(), yv.getY(), yv.getZ(), 0},
                {zv.getX(), zv.getY(), zv.getZ(), 0},
                {0, 0, 0, 1}
        };
        Matrix R = new Matrix(mr);
        double[][] mt = new double[][]{
                {1, 0, 0, -position.getX()},
                {0, 1, 0, -position.getY()},
                {0, 0, 1, -position.getZ()},
                {0, 0, 0, 1}
        };
        Matrix T = new Matrix(mt);
        return R.mult(T);
    }

    public Matrix getT1() {
        double wcx = l + ((r - l) / 2);
        double wcy = b + ((t - b) / 2);
        double[][] mt1 = new double[][]{
                {1, 0, 0, -wcx},
                {0, 1, 0, -wcy},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        return new Matrix(mt1);
    }

    public Matrix getT2() {
        double[][] mt2 = new double[][]{
                {1, 0, 0, 20 + vw / 2},
                {0, 1, 0, 20 + vh / 2},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        return new Matrix(mt2);
    }

    public Matrix getMV2() {
        double[][] ms = {
                {vw / (r - l), 0, 0, 0},
                {0, -vh / (t - b), 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        Matrix S = new Matrix(ms);
        return getT2().mult(S).mult(getT1());
    }

    public Matrix getP() {
        return P;
    }

    public Matrix getTl() {
        Vertex Vd = new Vertex(lookAt.getX() - position.getX(), lookAt.getY() - position.getY(), lookAt.getZ() - position.getZ());
        double[][] mtl = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, Vd.norm()},
                {0, 0, 0, 1}
        };
        return new Matrix(mtl);
    }

    public Matrix getTl2() {
        Vertex Vd = new Vertex(lookAt.getX() - position.getX(), lookAt.getY() - position.getY(), lookAt.getZ() - position.getZ());
        double[][] mtl = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, -Vd.norm()},
                {0, 0, 0, 1}
        };
        return new Matrix(mtl);
    }

    public int getVw() {
        return vw;
    }

    public int getVh() {
        return vh;
    }

    public double getWw() {
        return r - l;
    }

    public double getWh() {
        return t - b;
    }

    public void setVw(int vw) {
        this.vw = vw;
    }

    public void setVh(int vh) {
        this.vh = vh;
    }
}
