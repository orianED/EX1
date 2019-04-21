import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class MyCanvas extends Canvas implements KeyListener, MouseListener, MouseMotionListener {
    private boolean clip;
    private Scene scene;
    private View view;
    private Matrix CT, AT;
    private char currTransformation;

    public MyCanvas() {
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        this.scene = new Scene();
        this.view = new View();
        this.clip = false;

        scene.loadSCN("example3d.scn");
        view.loadView("example3d.viw");
        setSize(view.getVw() + 40, view.getVh() + 40);

        // initialize CT and AT to identifier matrix
        CT = new Matrix(null);
        CT.reset();
        AT = new Matrix(null);
        AT.reset();
    }

    @Override
    public void paint(Graphics g) {
        //draw bounds
        g.drawRect(20, 20, view.getVw(), view.getVw());

        Matrix TT = view.getMV2().mult(view.getP()).mult(CT).mult(AT).mult(view.getMV1());
        List<Vertex> newVL = TT.mult(scene.getVertexList());
        for (Edge e : this.scene.getEdgeList()) {
            g.drawLine((int) newVL.get(e.getV1()).getX(), (int) newVL.get(e.getV1()).getY(),
                    (int) newVL.get(e.getV2()).getX(), (int) newVL.get(e.getV2()).getY());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char key = Character.toLowerCase(e.getKeyChar());

        switch (key) {
            case 'c':
                this.clip = true;
                break;
            case 'l':
                JFileChooser chooser = new JFileChooser();
                File workingDirectory = new File(System.getProperty("user.dir"));
                chooser.setCurrentDirectory(workingDirectory);
                chooser.showOpenDialog(null);

                String path = chooser.getSelectedFile().getAbsolutePath();
                String extension = path.substring(path.lastIndexOf('.') + 1);
                if (extension.equals("scn")) {
                    this.scene.loadSCN(path);
                } else if (extension.equals("viw")) {
                    this.view.loadView(path);
                    setSize(this.view.getVw() + 40, this.view.getVh() + 40);
                }
                break;
            case 'r':
                break;
            case 'x':
                break;
            case 'y':
                break;
            case 'z':
                break;
            case 'q':
                exit(1);
                break;
            default:
                break;
        }
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
        transformationByLocation(x, y);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    private void transformationByLocation(double x, double y) {
        double vw = view.getVw();
        double vh = view.getVh();

        System.out.println(x + "," + y);
        if (x < 20 || x > vw + 20 || y < 20 || y > vh + 20) {
            currTransformation = 'N';
        } else if (x >= vw / 3 + 20 && x < 2 * vw / 3 + 20 && y > vh / 3 + 20 && y < 2 * vh / 3 + 20) {
            currTransformation = 'T';
        } else if ((x > 20 && x < vw / 3 + 20 || x > 2 * vw / 3 + 20 && x < vw + 20) &&
                (y > 20 && y < vh / 3 + 20 || y > 2 * vh / 3 + 20 && y < vh + 20)) {
            currTransformation = 'R';
        } else {
            currTransformation = 'S';
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
