package ShortestPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * TODO 时间复杂度O(v^2)
 * <p>
 *  @author 86080
 *  @date 2023/10/13 22:03
 *  @version 1.0
 */
public class Dijkstra {
    //源点s
    private int s;

    private WeightedGraph G;

    private int [] dis;

    //是否已经确定了最小值
    private boolean [] visited;

    private int pre[]; // 记录最短路径中顶点v的上一个节点
    //用来存储优先队列中节点v和dis[v]
    private class Node implements Comparable<Node>{
        public int v, dis;
        public Node(int v,int dis){
            this.v = v;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node another) {
            return dis - another.dis;
        }
    }
    public Dijkstra(WeightedGraph G,int s){
        this.G = G;
        G.validateVertex(s);
        this.s = s;
        dis = new int[G.V()];
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        //初始化每一个距离为无穷
        Arrays.fill(dis,Integer.MAX_VALUE);
        Arrays.fill(pre,-1);
        //源点到自己的最短距离为0
        dis[s] = 0;
        pre[s] = s;
        //注意不能将visted[s] = true 因为 在第一次循环 会找到源点作为最小点
        //不断循环确认每一个点到源点的最短路径

        while (true){
            int curDis = Integer.MAX_VALUE, cur = -1;
            //找到还没确认最短路径顶点中dis的值最小的那个 记录最短距离 以及顶点编号
            for(int i = 0; i < G.V();i++){

                if(!visited[i] && dis[i] < curDis){
                    curDis = dis[i];
                    cur = i;
                }
            }
            if(cur == -1){
                //找完了 退出循环
                break;
            }
            //找到了当前dis中未确定顶点的最小值
            visited[cur] = true;
            // 更新 从该节点出发所能到的所有节点到源点的值
            for(int w: G.adj(cur)){
                //如果未确定 且从cur到w 与dis[cur] 的和 小于 源点到w的权值 dis[w] 更新dis[w]
                if(!visited[w] && G.getWeight(cur,w) + dis[cur] < dis[w]){
                    dis[w] =G.getWeight(cur,w) + dis[cur];
                    pre[w] = cur;
                }
            }
        }
    }
    public void DijkstraOptimal(WeightedGraph G,int s){
        this.G = G;
        G.validateVertex(s);
        this.s = s;
        dis = new int[G.V()];
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        //初始化每一个距离为无穷
        Arrays.fill(dis,Integer.MAX_VALUE);
        Arrays.fill(pre,-1);
        //源点到自己的最短距离为0
        dis[s] = 0;
        pre[s] = s;
        //注意不能将visted[s] = true 因为 在第一次循环 会找到源点作为最小点
        //不断循环确认每一个点到源点的最短路径

        PriorityQueue<Node> pq = new PriorityQueue<Node>(); //默认是小顶堆
        pq.add(new Node(s,0));
        while (!pq.isEmpty()){
            //利用堆找到最小的dis[v]的编号v
            int cur = pq.remove().v; //最小值的顶点
            //由于堆中可能存有多个重复的数据
            if(visited[cur]){
                continue;
            }
            //找到了当前dis中未确定顶点的最小值
            visited[cur] = true;
            // 更新 从该节点出发所能到的所有节点到源点的值
            for(int w: G.adj(cur)){
                //如果未确定 且从cur到w 与dis[cur] 的和 小于 源点到w的权值 dis[w] 更新dis[w]
                if(!visited[w] && G.getWeight(cur,w) + dis[cur] < dis[w]){
                    dis[w] =G.getWeight(cur,w) + dis[cur];
                    //因为dis[w] 更新所以重新加入一个新的Node （可能会出现重复的w 但是因为每次都拿最小值 不影响）
                    pq.add(new Node(w,dis[w]));
                    pre[w] = cur;
                }
            }
        }
    }
    /*
     * @param v:
      * @return boolean
     * @author 86080
     * @description TODO 看传入的点v到源点s是否有路径
     * @date 2023/10/13 22:43
     */
    public boolean isConnectedTo(int v){
        G.validateVertex(v);
        //显然联通图 一定会把节点设为true false就是不连通图
        return visited[v];
    }
    /*
     * @param v:
      * @return int
     * @author 86080
     * @description TODO 看节点v到源点的最短路径是多少
     * @date 2023/10/13 22:45
     */
    public int disTo(int v){
        G.validateVertex(v);
        return dis[v];
    }
    //源点到终止点t的 最短路径
    public Iterable<Integer> path(int t){
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
        return res;
    }

    public static void main(String[] args) {
        WeightedGraph weightedGraph = new WeightedGraph("./Graph/wgSP.txt");
        Dijkstra dijkstra = new Dijkstra(weightedGraph,0);
//        dijkstra.DijkstraOptimal(weightedGraph,0);
        for (int i = 0; i < weightedGraph.V(); i++) {
            System.out.println(dijkstra.disTo(i));
        }
        System.out.println(dijkstra.path(3));

    }


}
