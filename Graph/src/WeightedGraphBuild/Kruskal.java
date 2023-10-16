package WeightedGraphBuild;

import java.util.ArrayList;
import java.util.Collections;

/**
 * TODO
 * <p>
 *  @author 86080
 *  @date 2023/10/12 15:59
 *  @version 1.0
 */
public class Kruskal {
    WeightedGraph G;

    //最小生成树就是边的集合
    ArrayList<WeightedEdge> mst;

    public Kruskal(WeightedGraph G){
        this.G = G;
        mst = new ArrayList<>();
        //检测图是联通图 不是就结束判断
        CC cc = new CC(G);
        if(cc.count() > 1){
            //非联通图
            return;
        }
        ArrayList<WeightedEdge> edges = new ArrayList<>();
        //每次拿最小的权值的边加入mst 只要不成环就加入
        for(int v = 0; v <G.V();v++){
            for(int w : G.adj(v)){
                //因为是无向图 所以只要加入一条边即可ab ba不加入
                if(v < w){
                    edges.add(new WeightedEdge(v,w,G.getWeight(v,w)));
                }
            }
        }
        //按照权值进行排序
        Collections.sort(edges);
        //利用并查集判断是否存在环
        //如果一个边两个顶点之间已经联通（处于同一个集合中）那么在添加这条边就会出现环
        UF uf = new UF(G.V());
        for(WeightedEdge edge:edges){
            int v = edge.getV();
            int w = edge.getW();
            if(!uf.isConnected(v,w)){
                mst.add(edge);
                uf.unionElements(v,w);
            }
        }

    }
    public ArrayList<WeightedEdge> result(){
        return mst;
    }

    public static void main(String[] args) {
        WeightedGraph G = new WeightedGraph("./Graph/wg.txt");
        Kruskal kruskal = new Kruskal(G);
        ArrayList<WeightedEdge> result = kruskal.result();
        System.out.print(result);
    }
}
