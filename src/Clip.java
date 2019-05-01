/**
 * Name: Orian Edri ID: 308335454
 * Name: Hadar Sabag ID: 312497126
 */

import java.util.ArrayList;
import java.util.List;

public class Clip {
    private final int INSIDE = 0;
    private final int LEFT = 1;
    private final int BUTTOM = 4;
    private final int RIGHT = 2;
    private final int TOP = 8;
    private final int minX, minY, maxX, maxY;

    public Clip(int minX, int minY, int maxX, int maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    private int computeCode(Vertex v) {
        int code = INSIDE;

        if (v.getX() < minX)
            code |= LEFT;
        else if (v.getX() > maxX)
            code |= RIGHT;
        else if (v.getY() < minY)
            code |= BUTTOM;
        else if (v.getY() > maxY)
            code |= TOP;

        return code;
    }

    /**
     * clip algorithm cohen-st implementation
     * @param v1 line vertex
     * @param v2 line vertex
     * @return new vertex of clipped line
     */
    public List<Vertex> CSClip(Vertex v1, Vertex v2) {
        int code1 = computeCode(v1);
        int code2 = computeCode(v2);
        boolean accept = false;

        Vertex newV1 = new Vertex(v1.getX(), v1.getY(), v1.getZ());
        Vertex newV2 = new Vertex(v2.getX(), v2.getY(), v2.getZ());
        List<Vertex> newLine = new ArrayList<>(2);
        while (true) {
            if (code1 == 0 && code2 == 0) {
                newLine.add(newV1);
                newLine.add(newV2);
                break;
            } else if ((code1 & code2) != INSIDE) {
                return null;
            } else {
                int codeOut;
                double x = 0, y = 0;

                if (code1 != 0)
                    codeOut = code1;
                else
                    codeOut = code2;

                if ((codeOut & TOP) != INSIDE) {
                    x = newV1.getX() + (newV2.getX() - newV1.getX()) * (maxY - newV1.getY()) / (newV2.getY() - newV1.getY());
                    y = maxY;
                } else if ((codeOut & BUTTOM) != INSIDE) {
                    x = newV1.getX() + (newV2.getX() - newV1.getX()) * (minY - newV1.getY()) / (newV2.getY() - newV1.getY());
                    y = minY;
                } else if ((codeOut & RIGHT) != INSIDE) {
                    y = newV1.getY() + (newV2.getY() - newV1.getY()) * (maxX - newV1.getX()) / (newV2.getX() - newV1.getX());
                    x = maxX;
                } else if ((codeOut & LEFT) != INSIDE) {
                    y = newV1.getY() + (newV2.getY() - newV1.getY()) * (minX - newV1.getX()) / (newV2.getX() - newV1.getX());
                    x = minX;
                }

                if (codeOut == code1) {
                    newV1.setX(x);
                    newV1.setY(y);
                    code1 = computeCode(newV1);
                } else {
                    newV2.setX(x);
                    newV2.setY(y);
                    code2 = computeCode(newV2);
                }
            }
        }
        return newLine;
    }
}