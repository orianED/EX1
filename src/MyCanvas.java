import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class MyCanvas extends Canvas implements KeyListener, MouseListener, MouseMotionListener {
    private boolean clip;
    private Scene scene;

    public MyCanvas() {
        setSize(420, 420);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        this.scene = new Scene();
        this.clip = false;
    }

    @Override
    public void paint(Graphics g) {
        List<Vertex> newVL = new ArrayList<>();
        newVL = t.mult(this.scene.getVL);

    }

    @Override
    public void keyTyped(KeyEvent e) {
        char key = Character.toLowerCase(e.getKeyChar());

        switch (key) {
            case 'c':
                this.clip = true;
                this.repaint();
                break;
            case 'l':
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                String path = chooser.getSelectedFile().getAbsolutePath();
                String extension = path.substring(path.lastIndexOf('.') + 1);
                if (extension.equals("scn")) {
                    this.scene.loadSCN(path);
                } else if (extension.equals("viw")) {
                    //TODO
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
