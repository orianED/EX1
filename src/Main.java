import javafx.util.Pair;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

interface IFunc{
    void resize();
}

public class Main {

    public static void main(String[] args) {
        Frame myFrame = new Frame("EX1");
        MyCanvas myCanvas = new MyCanvas(myFrame::pack);
        myFrame.add(myCanvas);

        WindowAdapter myWindowAdapter = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };

        myFrame.addWindowListener(myWindowAdapter);
        myFrame.pack();
        myFrame.setVisible(true);
    }


}



