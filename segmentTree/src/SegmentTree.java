/**
 * TODO
 *
 * @author 86080
 * @version 1.0
 * @date 2022/09/06 11:52
 */
public class SegmentTree <E>{
    //tree来存某个区间的节点的值
    private E []tree;
    private E []data;
    private Merger<E> merge; //传入合并元素的规则
    //保存一个副本
    public SegmentTree(E arr[],Merger<E> merge) {
        this.merge = merge;
        data =  (E[])new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree =  (E[])new Object[4*arr.length];
        buildSegmentTree(0,0,data.length-1);
    }
    //递归创建线段树 在数组的treeIndex位置 节点范围为[l,r]集合的值的融合的线段树 l代表集合的左边界索引 r 右边界
    private void buildSegmentTree(int treeIndex,int l ,int r) {
        //集合中只有一个元素就结束了
        if(l == r){
            tree[treeIndex] = data[l];
            return;
        }
        //创建左右子树
        int leftIndex = leftChild(treeIndex);
        int rightIndex = rightChild(treeIndex);
        int mid = l + (r-l)/2; //（r+l）/2 也可以不过可能r+l超出范围
        buildSegmentTree(leftIndex,l,mid);
        buildSegmentTree(rightIndex,mid+1,r);
        //用左右节点的集合 得到父节点的值的集合
        tree[treeIndex] = merge.merge(tree[leftIndex],tree[rightIndex]);
    }
    //要查询的集合范围
    public E query(int queryL,int queryR){
        if(queryL<0 || queryL>=data.length||queryR<0 || queryR>=data.length){
            throw new IllegalArgumentException("index is illegal");
        }
        return query(0,0,data.length-1,queryL,queryR);


    }
    // 在treeIndex 为根，[l,r]范围中查询 区间[queryl,queryR]的值
    // [l,r]就是treeIndex 为根线段树的表示的元素索引集合
    private E query(int treeIndex,int l,int r,int queryL,int queryR) {
        if(queryL == l && queryR == r){
            return tree[treeIndex];
        }
        int leftIndex = leftChild(treeIndex);
        int rightIndex = rightChild(treeIndex);
        int mid = l + (r-l)/2;
        if(queryL >= mid +1){
            //去右子树中查询
            return query(rightIndex,mid+1,r,queryL,queryR);
        }else if(queryR <= mid){
            return query(leftIndex,l,mid,queryL,queryR);
        }
        //说明在左子树中有一部分 右子树中有一部分
        E leftRes = query(leftIndex,l,mid,queryL,mid);
        E rightRes = query(rightIndex,mid+1,r,mid+1,queryR);
        return merge.merge(leftRes,rightRes);
    }

    public int size(){
        return data.length;
    }
    //获取index索引的值
    public E get(int index){
        if(index<0 || index >=data.length){
            throw new IllegalArgumentException("index illegal");
        }
        return data[index];
    }

    public int leftChild(int index){
        return 2*index+1;
    }
    public int rightChild(int index){
        return 2*index+2;
    }



    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        for (int i = 0; i < tree.length; i++) {
            if(tree[i] != null){
                stringBuilder.append(tree[i]);
            }else{
                stringBuilder.append("null");
            }
            if(i != tree.length -1){
                stringBuilder.append(",");
            }
        }

        stringBuilder.append(']');
        return stringBuilder.toString();
    }
    
}
