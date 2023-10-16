package BFSImpl;

import DFSImpl.GraphDFS;
import GraphBuild.AdjSet;
import GraphBuild.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * TODO
 *
 * @author 86080
 * @version 1.0
 * @date 2023/09/11 14:07
 */
public class GraphBFS {
    private boolean [] visited;
    private Graph G;
    private  ArrayList<Integer> order;
    public GraphBFS(Graph G) {
        this.G = G;
        this.visited = new boolean[G.V()];
        this.order = new ArrayList<>();
        for (int i = 0; i < G.V(); i++) {
            if(!visited[i]){
                bfs(i);
            }
        }
    }

    private void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        visited[s] = true;
        queue.add(s);
        while(!queue.isEmpty()){
            int v = queue.remove();
            order.add(v);
            for(int w:G.adj(v)){
                if(!visited[w]){
                    queue.add(w);
                    visited[w] = true;
                }
            }
        }
    }
    public Iterable<Integer> order(){
        return order;
    }

    public static void main(String[] args) {
        Graph g = new AdjSet("./Graph/g.txt");
        GraphBFS graphDFS = new GraphBFS(g);
        System.out.println("order = " + graphDFS.order());
    }
}
