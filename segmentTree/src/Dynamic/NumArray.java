package Dynamic;

/**
 * TODO
 * <p>
 *  @author 86080
 *  @date 2023/11/13 09:35
 *  @version 1.0
 */
public class NumArray {
    int N ;
    Segment sg;
    public NumArray(int[] nums) {
        N = nums.length - 1;
        sg = new Segment();
        for (int i = 0; i <= N; i++) {
            sg.update(sg.root,  i, i,0, N, nums[i]);
        }
    }

    public void update(int index, int val) {
        sg.update(sg.root, index, index, 0, N,val);
    }

    public int sumRange(int left, int right) {
        return sg.query(sg.root, left, right,0, N);
    }

    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{1,3,5});
        numArray.sumRange(0,2);
    }
}
class Segment{
    class Node{
        Node left,right;
        int sum, add;
    }
    public Node root;

    public Segment(){
        root = new Node();
    }
    public void update(Node node , int queryL,int queryR,int l,int r,int val){
        if(queryL <= l && queryR >= r){
            node.sum = val;
            return;
        }
        int mid = l + (r-l)/2;
        pushDown(node,mid - l,r - mid);
        if(queryL <= mid){
            update(node.left,queryL,queryR,l,mid,val);
        }
        if(queryR > mid){
            update(node.right,queryL,queryR,mid+1,r,val);
        }
        pushUp(node);
    }
    public void pushUp(Node node){
        node.sum = node.left.sum + node.right.sum;
    }
    public int query(Node node , int queryL,int queryR,int l,int r){
        if(queryL <= l && queryR >= r){
            return node.sum;
        }
        int mid = l + (r- l)/2;
        pushDown(node,mid - l + 1,r - mid);
        int ans = 0;
        if(queryL <= mid){
            ans += query(node.left,queryL,queryR,l,mid);
        }
        if(queryR > mid){
            ans += query(node.right,queryL,queryR,mid+1,r);
        }
        return ans;
    }

    private void pushDown(Node node ,int lNums,int rNums) {
        if(node.left == null){
            node.left = new Node();
        }
        if(node.right == null){
            node.right = new Node();
        }
        if(node.add == 0){
            return ;
        }
        node.left.sum += node.add * lNums;
        node.right.sum += node.add * rNums;
        node.left.add = node.add;
        node.right.add = node.add;
        node.add = 0;
    }

}
