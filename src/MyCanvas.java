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

    public MyCanvas() {
        setSize(420, 420);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        this.scene = new Scene();
        this.view = new View();
        this.clip = false;
    }

    @Override
    public void paint(Graphics g) {
        List<Vertex> newVL = new ArrayList<>();
        for (Edge e : this.scene.getEdgeList()) {
            g.drawLine((int) e.getV1().getX() * 40, (int) e.getV1().getY() * 40, (int) e.getV2().getX() * 40, (int) e.getV2().getY() * 40);
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
                    Matrix MV1 = view.getMV1();
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
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

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
