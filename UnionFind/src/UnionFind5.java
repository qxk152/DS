/**
 * TODO
 *
 * @author 86080
 * @version 1.0
 * @date 2023/08/30 23:17
 */
public class UnionFind5 implements UF {
    //parent【i】 表示的是第i个元素的父亲
    private  int [] parent;
    // rank[i] 是以i为根的集合的树的深度
    private  int [] rank;

    public UnionFind5(int size) {
        parent = new int[size];
        rank = new  int[size];
        for(int i = 0 ; i < size;i++){
            parent[i] = i; //指向自己
            rank[i] = 1;
        }
    }

    private int find(int p){
        if(p < 0 || p > parent.length){
            throw new IllegalArgumentException("out of bound");
        }
        while(p!=parent[p]){
            //将p的父亲 指向p父亲的父亲
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if(rank[pRoot] < rank[qRoot]){
            parent[pRoot] = qRoot;
        }else if(rank[pRoot] > rank[qRoot]){
            parent[qRoot] = pRoot;
        }else{
            //相等的时候
            parent[qRoot] = pRoot;
            rank[pRoot] +=1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }
}
