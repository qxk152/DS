/**
 * TODO
 *
 * @author 86080
 * @version 1.0
 * @date 2022/10/18 13:57
 */
public class UnionFind1 implements UF {
    private int[] id;

    public UnionFind1(int size) {
        id = new int[size];
        for (int i = 0; i < id.length; i++) {
            id[i] = i; //就是id的索引代表的元素值 id的值代表元素所属集合id
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
    /*
     * @param p:
      * @return int
     * @author 86080
     * @description TODO 返回元素所属的集合id
     * @date 2023/8/30 22:22
     */
    private int find(int p){
        if(p < 0 || p > id.length){
            throw new IllegalArgumentException("out of bound");
        }
        return id[p];
    }

    @Override
    public void unionElements(int p, int q) {
        //让两个元素所属不同的集合的id相同
        int pId = find(p);
        int qId = find(q);
        if(pId == qId){
            return;
        }
        for (int i = 0; i < id.length; i++) {
            if(id[i] == pId){
                id[i] = qId;
            }
        }
    }

    @Override
    public int getSize() {
        return 0;
    }


}
