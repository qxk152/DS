package DFSApplication;

/**
 * TODO
 *
 * @author 86080
 * @version 1.0
 * @date 2023/09/11 13:33
 */

import java.util.ArrayList;
import java.util.TreeSet;
import GraphBuild.AdjSet;
import GraphBuild.Graph;
public class CycleDectecion {

    private Graph G;
    private boolean visited[];
    private boolean hasCycle;
    public CycleDectecion(Graph graph){
        this.G = graph;
        visited = new boolean [G.V()];
        //有可能有多个联通分量
        for (int v = 0; v < G.V(); v++) {
            if (!visited[v])
                dfs(v,v);
        }

    }
    private void dfs(int v,int parent){
        visited[v] = true;

        for (int w: G.adj(v)) {
            if(!visited[w]){
                dfs(w,v);
            }else if(parent != w){
                hasCycle =  true;
            }

        }

    }

    public boolean isHasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("./Graph/g.txt");
        DFSImpl.GraphDFS graphDFS = new DFSImpl.GraphDFS(g);
        System.out.println("order = " + graphDFS.pre());
        System.out.println("order = " + graphDFS.post());
    }
}

