package Dynamic;

/**
 * TODO
 * <p>动态开点线段树
 *  @author 86080
 *  @date 2023/11/12 22:29
 *  @version 1.0
 */
public class RangeModule {
    SegmentTree sg;
    private static final int MAX_RANGE = (int)1e9 + 7;
    public RangeModule() {
        sg = new SegmentTree();
    }

    public void addRange(int left, int right) {
        sg.update(left,right - 1,sg.root,1,MAX_RANGE,1);
    }

    public boolean queryRange(int left, int right) {
        return sg.query(left,right - 1,sg.root,1,MAX_RANGE);
    }

    public void removeRange(int left, int right) {
        sg.update(left,right-1,sg.root,1,MAX_RANGE,-1);
    }

}
class SegmentTree{
    class Node{
        private Node lchild,rchild;
        //add 是懒惰标记
        private int add;

        boolean isCover;
    }
    private int N = (int) 1e9;
    public  Node root;

    public SegmentTree(){
        root = new Node();
    }
    public void update(int queryL,int queryR,Node node,int l,int r,int val){
        if(queryL <= l && queryR >= r){
            node.isCover = val == 1;
            node.add = val;
            return;
        }
        int mid =l + (r -l)/2;
        pushDown(node);
        if(queryL <= mid){
            update(queryL,queryR,node.lchild,l,mid,val);
        }
        if(queryR > mid){
            update(queryL,queryR,node.rchild,mid + 1,r,val);
        }
        pushUp(node);
    }
    //需要更新父亲节点的值
    private void pushUp(Node node) {
        node.isCover = node.lchild.isCover && node.rchild.isCover;
    }

    // 从Node节点 [l,r]查找是否有覆盖[queryL..queryR]的节点 // Node.add =  1 表示覆盖；-1 表示取消覆盖
    public boolean query(int queryL,int queryR,Node node,int l,int r){
        if(queryL <= l && queryR >= r){
            return node.isCover;
        }
        int mid = l + (r - l)/2;
        pushDown(node);
        boolean ans = true;
        if(queryL <= mid){
            ans = ans && query(queryL,queryR,node.lchild,l,mid);
        }
        if(queryR > mid){
            ans = ans && query(queryL,queryR,node.rchild,mid+1,r);
        }
        return ans;
    }
    private void pushDown(Node node){
        if(node.lchild == null){
            node.lchild = new Node();
        }
        if(node.rchild == null){
            node.rchild = new Node();
        }
        if(node.add == 0){
            //没有懒惰标记不下推
            return;
        }
        node.lchild.isCover = node.add == 1;
        node.rchild.isCover = node.add == 1;
        node.lchild.add = node.add;
        node.rchild.add = node.add;
        node.add = 0;
    }
}
