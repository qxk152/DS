/**
 * TODO
 *
 * @author 86080
 * @version 1.0
 * @date 2023/08/30 22:33
 */
public class UnionFind2 implements UF {
    //parent【i】 表示的是第i个元素的父亲
    private  int [] parent;

    public UnionFind2(int size) {
        parent = new int[size];
        for(int i = 0 ; i < size;i++){
            parent[i] = i; //指向自己
        }
    }
    private int find(int p){
        if(p < 0 || p > parent.length){
            throw new IllegalArgumentException("out of bound");
        }
        while(p!=parent[p]){
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
        if(pRoot == qRoot){
            return;
        }
        parent[pRoot] = qRoot;
    }

    @Override
    public int getSize() {
        return 0;
    }
}
