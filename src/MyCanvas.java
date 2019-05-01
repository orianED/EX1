import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;

import static java.lang.System.exit;

public class MyCanvas extends Canvas implements KeyListener, MouseListener, MouseMotionListener {
    private boolean clip;
    private Scene scene;
    private View view;
    private Transformations transformations;
    private Matrix CT, AT;
    private List<Vertex> vertexList;
    private char currTransformation, axis;
    private double Sx, Sy;
    private double Cx, Cy;
    private Clip cliping;
    private IFunc myFunc;

    public MyCanvas(IFunc myFunc) {
        this.myFunc = myFunc;
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        this.scene = new Scene();
        this.view = new View();
        this.clip = false;
        axis = 'X';
        //load default scene and view:
        scene.loadSCN("example3d.scn");
        vertexList = scene.getVertexList();
        view.loadView("example3d.viw");
        setSize(view.getVw() + 40, view.getVh() + 40);
        Cx = 20 + view.getVw() / 2;
        Cy = 20 + view.getVh() / 2;
        cliping = new Clip(20, 20, view.getVw() + 20, view.getVh() + 20);

        transformations = new Transformations();
        // initialize CT and AT to id matrix
        CT = new Matrix(null);
        CT.reset();
        AT = new Matrix(null);
        AT.reset();
        //window resize handle:
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Component viewPort = e.getComponent();
                view.setVw(viewPort.getWidth() - 40);
                view.setVh(viewPort.getHeight() - 40);
                Cx = 20 + view.getVw() / 2;
                Cy = 20 + view.getVh() / 2;
                setSize(e.getComponent().getWidth(), e.getComponent().getHeight());
                cliping = new Clip(20, 20, view.getVw() + 20, view.getVh() + 20);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        //draw bounds
        g.drawRect(20, 20, view.getVw(), view.getVh());

        Matrix TT = view.getMV2().mult(view.getP()).mult(CT).mult(AT).mult(view.getMV1());
        List<Vertex> newVL = TT.mult(vertexList);
        for (Edge e : this.scene.getEdgeList()) {
            if (clip) {
                List<Vertex> newLine = cliping.CSClip(newVL.get(e.getV1()), newVL.get(e.getV2()));
                if (newLine != null) {
                    g.drawLine((int) newLine.get(0).getX(), (int) newLine.get(0).getY(),
                            (int) newLine.get(1).getX(), (int) newLine.get(1).getY());
                }
            } else {
                g.drawLine((int) newVL.get(e.getV1()).getX(), (int) newVL.get(e.getV1()).getY(),
                        (int) newVL.get(e.getV2()).getX(), (int) newVL.get(e.getV2()).getY());
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char key = Character.toLowerCase(e.getKeyChar());

        switch (key) {
            case 'c':
                clip = !clip;
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
                    vertexList = scene.getVertexList();
                    AT.reset();
                    CT.reset();
                } else if (extension.equals("viw")) {
                    this.view.loadView(path);
                    setSize(view.getVw() + 40, view.getVh() + 40);
                    myFunc.resize();
                    Cx = 20 + view.getVw() / 2;
                    Cy = 20 + view.getVh() / 2;
                    cliping = new Clip(20, 20, view.getVw() + 20, view.getVh() + 20);
                }
                break;
            case 'r':
                AT.reset();
                break;
            case 'x':
                axis = 'X';
                break;
            case 'y':
                axis = 'Y';
                break;
            case 'z':
                axis = 'Z';
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
        double Dx = e.getX();
        double Dy = e.getY();

        double dx = Dx - Sx, dy = Dy - Sy;
        Vertex Vs = new Vertex(Sx - Cx, Sy - Cy, 0);
        Vertex Vd = new Vertex(Dx - Cx, Dy - Cy, 0);
        switch (currTransformation) {
            case 'T':
                CT = transformations.translate(dx * (view.getWw() / view.getVw()), dy * (-view.getWh() / view.getVh()));
                break;
            case 'S':
                double s = Vd.norm() / Vs.norm();
                CT = transformations.scale(s);
                break;
            case 'R':
                double tetaS = Math.atan2(Vs.getY(), Vs.getX());
                double tetaD = Math.atan2(Vd.getY(), Vd.getX());
                double teta = tetaD - tetaS;
                CT = view.getTl2().mult(transformations.rotate(axis, teta)).mult(view.getTl());
                break;
            default:
                break;
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //save the start position
        Sx = e.getX();
        Sy = e.getY();
        transformationByLocation(Sx, Sy);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        AT = CT.mult(AT);
        CT.reset();

        repaint();
    }

    private void transformationByLocation(double x, double y) {
        double vw = view.getVw();
        double vh = view.getVh();

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
