package DFSApplication;

import java.util.ArrayList;
import java.util.Collections;
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
public class SingleSourcePath {
    private int s;
    private Graph G;
    private boolean visited[];
    private int pre[];
    //单源最短路径 s是源节点
    public SingleSourcePath(Graph graph,int s){
        this.G = graph;
        G.validateVertex(s);
        visited = new boolean [G.V()];
        pre = new int[G.V()];
        for (int i = 0; i < pre.length; i++) {
            pre[i] = -1;
        }
        dfs(s,s);

    }
    private void dfs(int v,int parent){
        pre[v] = parent;
        visited[v] = true;
        for (int w: G.adj(v)) {
            if(!visited[w]){
                dfs(w,v);
            }
        }
    }
    public boolean isConnectedTo(int t){
        G.validateVertex(t);
        return visited[t];
    }
    public Iterable<Integer> path(int t ){
        ArrayList<Integer> res = new ArrayList<>();
        if(!isConnectedTo(t)){
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
        SingleSourcePath ss = new SingleSourcePath(g,0);
        System.out.println("0-6 = " + ss.path(6));
        System.out.println("0-5 = " + ss.path(5));
    }
}

