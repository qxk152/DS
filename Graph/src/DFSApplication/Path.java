package DFSApplication;

/**
 * TODO
 *
 * @author 86080
 * @version 1.0
 * @date 2023/09/11 13:25
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;
import GraphBuild.AdjSet;
import GraphBuild.Graph;

/**
 * TODO
 *s 到t  是否有路径
 * @author 86080
 * @version 1.0
 * @date 2023/09/09 23:12
 */
public class Path {
    private int s;
    private int t;
    private Graph G;
    private boolean visited[];
    private int pre[];
    //单源最短路径 s是源节点
    public Path(Graph graph,int s,int t){
        this.G = graph;
        G.validateVertex(s);
        G.validateVertex(t);
        this.t = t;
        this.s = s;
        visited = new boolean [G.V()];
        pre = new int[G.V()];
        for (int i = 0; i < pre.length; i++) {
            pre[i] = -1;
        }
        dfs(s,s);

    }
    private boolean dfs(int v,int parent){
        pre[v] = parent;
        visited[v] = true;
        if(v == t){
            return true;
        }
        for (int w: G.adj(v)) {
            if(!visited[w]){
                if(dfs(w,v)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isConnectedTo(){
        return visited[t];
    }
    public Iterable<Integer> path(){
        ArrayList<Integer> res = new ArrayList<>();
        if(!isConnectedTo()){
            return res;
        }
        int cur = t;
        while(cur != s){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        return  res;
    }


    public static void main(String[] args) {
        Graph g = new AdjSet("./Graph/g.txt");
//        DFSImpl.GraphDFS graphDFS = new DFSImpl.GraphDFS(g);
        Path ss = new Path(g,0,6);
        System.out.println("0-6 = " + ss.path());

    }
}


