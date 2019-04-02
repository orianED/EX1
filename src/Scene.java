import java.util.List;

/**
 * Created by hadar on 30/03/2019.
 */
public class Scene {

    private List<Vertex> vertexList;
    private List<Edge> EdgeList;

    public Scene(List<Vertex> vertexList, List<Edge> EdgeList) {
        this.vertexList = vertexList;
        this.EdgeList = EdgeList;
    }
}
