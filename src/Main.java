import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Vertex> vertexList=new ArrayList<Vertex>();
        List<Edge> edgeList=new ArrayList<>();
        int numOfVertex, numOfEdges;
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader("example3d.scn");
            br = new BufferedReader(fr);
            numOfVertex = Integer.parseInt(br.readLine().replace("\n", ""));
            for (int i = 0; i <numOfVertex ; i++) {
                String sCurrentLine = br.readLine();
                String[] parts = sCurrentLine.split(" ");
                vertexList.add(new Vertex(Double.parseDouble(parts[0]),Double.parseDouble(parts[1]),Double.parseDouble(parts[2])));
            }
            numOfEdges = Integer.parseInt(br.readLine().replace("\n", ""));
            for (int i = 0; i <numOfEdges ; i++) {
                String sCurrentLine = br.readLine();
                String[] parts = sCurrentLine.split(" ");
                edgeList.add(new Edge(vertexList.get(Integer.parseInt(parts[0])),vertexList.get(Integer.parseInt(parts[1]))));
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



