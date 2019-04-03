import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by hadar on 30/03/2019.
 */
public class Scene {

    private List<Vertex> vertexList;
    private List<Edge> edgeList;

    public Scene(List<Vertex> vertexList, List<Edge> EdgeList) {
        this.vertexList = vertexList;
        this.edgeList = EdgeList;
    }
    public void readSCN(String filePath) {
        int numOfVertex, numOfEdges;
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(filePath);
            br = new BufferedReader(fr);
            //vertex
            numOfVertex = Integer.parseInt(br.readLine().replace("\n", ""));
            for (int i = 0; i < numOfVertex; i++) {
                String sCurrentLine = br.readLine();
                String[] parts = sCurrentLine.split(" ");
                this.vertexList.add(new Vertex(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2])));
            }
            //edges
            numOfEdges = Integer.parseInt(br.readLine().replace("\n", ""));
            for (int i = 0; i < numOfEdges; i++) {
                String sCurrentLine = br.readLine();
                String[] parts = sCurrentLine.split(" ");
                this.edgeList.add(new Edge(this.vertexList.get(Integer.parseInt(parts[0])), this.vertexList.get(Integer.parseInt(parts[1]))));
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
