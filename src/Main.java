import javafx.util.Pair;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Vertex> vertexList = new ArrayList<>();
        List<Edge> edgeList = new ArrayList<>();

        Frame myFrame = new Frame("EX1");
        MyCanvas myCanvas = new MyCanvas();
        myFrame.add(myCanvas);

        WindowAdapter myWindowAdapter = new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };

        myFrame.addWindowListener(myWindowAdapter);
        myFrame.pack();
        myFrame.setVisible(true);
    }

    private static void readSCN(List<Vertex> vertexList, List<Edge> edgeList) {
        int numOfVertex, numOfEdges;
        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader("example3d.scn");
            br = new BufferedReader(fr);
            //vertex
            numOfVertex = Integer.parseInt(br.readLine().replace("\n", ""));
            for (int i = 0; i < numOfVertex; i++) {
                String sCurrentLine = br.readLine();
                String[] parts = sCurrentLine.split(" ");
                vertexList.add(new Vertex(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2])));
            }
            //edges
            numOfEdges = Integer.parseInt(br.readLine().replace("\n", ""));
            for (int i = 0; i < numOfEdges; i++) {
                String sCurrentLine = br.readLine();
                String[] parts = sCurrentLine.split(" ");
                edgeList.add(new Edge(vertexList.get(Integer.parseInt(parts[0])), vertexList.get(Integer.parseInt(parts[1]))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}



