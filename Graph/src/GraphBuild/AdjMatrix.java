package GraphBuild;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * TODO
 *
 * @author 86080
 * @version 1.0
 * @date 2023/09/09 21:45
 */
public class AdjMatrix implements Graph {
    private int V;
    private int E;
    private int [][] adj;

    public AdjMatrix(String fileName){
        File file = new File(fileName);
        //不需要手动关闭流
        try(Scanner scanner = new Scanner(file)){
            V = scanner.nextInt();
            if(V < 0)
                throw new IllegalArgumentException("V must be non-negative");
            adj = new int[V][V];
            E = scanner.nextInt();
            if(E < 0)
                throw new IllegalArgumentException("E must be non-negative");
            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt();
                validateVertex(a);
                int b = scanner.nextInt();
                validateVertex(b);
                // 没有自环边和平行边
                if(a == b) throw new IllegalArgumentException("Self Loop is Detected!");
                if(adj[a][b] == 1) throw new IllegalArgumentException("Parallel Edges are Detected!");
                adj[a][b] = 1;
                adj[b][a] = 1;
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    //判断传来的顶点是否合法
    public void validateVertex(int v){
        if(v < 0|| v >= V){
            throw new IllegalArgumentException("vertex " + v +"is invalid");
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d,E = %d\n",V,E));

        for(int i = 0 ; i < V;i++){
            for(int j = 0; j < V;j++){
                sb.append(String.format("%d ",adj[i][j]));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }
    //判断顶点v和w是否有边
    public boolean hasEdge(int v, int w){
        validateVertex(v);
        validateVertex(w);
        return adj[v][w] == 1;
    }
    //拿到顶点v 的邻居点
    public ArrayList<Integer> adj(int v){
        validateVertex(v);
        ArrayList<Integer> res = new ArrayList<>();
        for(int i = 0; i < V; i ++)
            if(adj[v][i] == 1)
                res.add(i);
        return res;
    }
    //求一个顶点的度
    public int degree(int v){
        return adj(v).size();
    }


    //项目的起始的相对目录是在Graph下和src同级
    public static void main(String[] args) {
        AdjMatrix adjMatrix = new AdjMatrix("./GraphBuild.Graph/g.txt");
        System.out.println(adjMatrix);
    }
}
