package DFSApplication;

/**
 * TODO
 *
 * @author 86080
 * @version 1.0
 * @date 2023/09/11 13:43
 */
import java.util.ArrayList;
import java.util.TreeSet;
import GraphBuild.AdjSet;
import GraphBuild.Graph;
public class BipartitionDectection {

    private Graph G;
    private boolean visited[];
    private int [] colors;
    private boolean isBipartite = true;
    public BipartitionDectection(Graph graph){
        this.G = graph;
        visited = new boolean [G.V()];
        colors = new int[G.V()];
        //0 1 代表两种不同的颜色
        for (int i = 0; i < colors.length; i++) {
            colors[i] = -1;
        }
        //有可能有多个联通分量
        for (int v = 0; v < G.V(); v++) {
            if (!visited[v])
                if(!dfs(v,0)){
                    isBipartite = false;
                }
        }

    }
    private boolean dfs(int v,int color){
        visited[v] = true;
        colors[v] = color;
        for (int w: G.adj(v)) {
            if(!visited[w]){
                if(!dfs(w,1 - color))
                    return false;
            }else if(colors[w] == colors[v]){
                //已经访问过w了 如果这两个节点的颜色相同那么冲突
                return false;
            }

        }
        return true;

    }

    public boolean isBipartite() {
        return isBipartite;
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("./Graph/g.txt");
        BipartitionDectection bipartitionDectection = new BipartitionDectection(g);
        System.out.println("order = " + bipartitionDectection.isBipartite());
    }
}


