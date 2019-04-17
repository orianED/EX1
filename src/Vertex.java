public class Vertex {
    private double x, y, z;

    public Vertex(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vertex crossProduct(Vertex other) {
        double newX = y * other.getZ() - z * other.getY();
        double newY = z * other.getX() - x * other.getZ();
        double newZ = x * other.getY() - y * other.getX();

        return new Vertex(newX, newY, newZ);
    }

    public Vertex mult(double scalar) {
        return new Vertex(x * scalar, y * scalar, z * scalar);
    }

    public double norm() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
