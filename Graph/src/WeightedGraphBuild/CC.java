package WeightedGraphBuild;

import java.util.ArrayList;

/**
 * TODO
 * <p>
 *  @author 86080
 *  @date 2023/10/12 16:15
 *  @version 1.0
 *  联通图检测
 */
public class CC {
    private WeightedGraph G;
    private int[] visited;
    private int cccount = 0;

    public CC(WeightedGraph G){

        this.G = G;
        visited = new int[G.V()];
        for(int i = 0; i < visited.length; i ++)
            visited[i] = -1;

        for(int v = 0; v < G.V(); v ++)
            if(visited[v] == -1){
                dfs(v, cccount);
                cccount ++;
            }
    }

    public void dfs(int v, int ccid) {
        visited[v] = ccid;
        for(int w : G.adj(v)){
            if(visited[w] == -1){
                dfs(w,cccount);
            }
        }

    }
    public int count(){
        return cccount;
    }

    public boolean isConnected(int v, int w){
        G.validateVertex(v);
        G.validateVertex(w);
        return visited[v] == visited[w];
    }
    public ArrayList<Integer>[] components(){
        ArrayList<Integer>[] res = new ArrayList[cccount];
        for(int i = 0; i < cccount; i ++)
            res[i] = new ArrayList<Integer>();

        for(int v = 0; v < G.V(); v ++)
            res[visited[v]].add(v);
        return res;
    }
}
