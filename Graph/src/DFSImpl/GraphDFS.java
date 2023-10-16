package DFSImpl;

import java.util.ArrayList;
import java.util.TreeSet;
import GraphBuild.AdjSet;
import GraphBuild.Graph;

/**
 * TODO
 *
 * @author 86080
 * @version 1.0
 * @date 2023/09/09 23:12
 */
public class GraphDFS {
    private ArrayList<Integer> pre = new ArrayList<>();
    private ArrayList<Integer> post = new ArrayList<>();
    private Graph G;
    private boolean visited[];
    public GraphDFS(Graph graph){
        this.G = graph;
        visited = new boolean [G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!visited[v])
                dfs(v);
        }

    }
    private void dfs(int v){
        visited[v] = true;
        pre.add(v);
        for (int w: G.adj(v)) {
            if(!visited[w]){
                dfs(w);
            }
        }
        post.add(v);
    }

    public Iterable<Integer> pre(){
        return pre;
    }
    public Iterable<Integer> post(){
        return post;
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("./Graph/g.txt");
        GraphDFS graphDFS = new GraphDFS(g);
        System.out.println("order = " + graphDFS.pre());
        System.out.println("order = " + graphDFS.post());
    }
}
