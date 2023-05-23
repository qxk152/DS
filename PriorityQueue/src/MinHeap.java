/**
 * TODO
 *
 * @author 86080
 * @version 1.0
 * @date 2022/09/05 17:30
 */
public class MinHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MinHeap(int capacity) {
        data = new Array<>(capacity);
    }
    public MinHeap(){
        data = new Array<>();
    }
    // 返回堆中的元素个数
    public int size(){
        return data.getSize();
    }

    // 返回一个布尔值, 表示堆中是否为空
    public boolean isEmpty(){
        return data.isEmpty();
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    private int parent(int index){
        if(index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        return (index - 1) / 2;
    }
    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index){
        return index * 2 + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index){
        return index * 2 + 2;
    }
    // 向堆中添加元素
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int k) {
        //有父节点并且该节点元素小于父节点
        while(k > 0 && data.get(k).compareTo(data.get(parent(k)))<0 ){
            data.swap(k,parent(k));
            k = parent(k);
        }


    }
    // 看堆中的最小元素
    public E findMin(){
        if(data.getSize() == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        return data.get(0);
    }

    // 取出堆中最小元素
    public E extractMin(){
        E e = findMin();
        data.swap(0,data.getSize() -1);
        data.removeLast();
        siftDown(0);
        return e;
    }

    private void siftDown(int k) {

        while(leftChild(k) < data.getSize()){
            int j = leftChild(k);
            if(j+1< data.getSize() && data.get(j).compareTo(data.get(j+1))>0){
                j++;
            }
            if(data.get(k).compareTo(data.get(j)) <= 0 )
                break;
            data.swap(j,k);
            k = j;
        }
    }

    // 取出堆中的最小元素，并且替换成元素e
    public E replace(E e){

        E ret = findMin();
        data.set(0, e);
        siftDown(0);
        return ret;
    }
    //传入数组找出第k大的元素
    public int findKthLargest(int[] nums, int k) {
        MinHeap<Integer> pq = new MinHeap<>();
        //放入k个元素
        for (int i = 0; i < k; i++) {
            pq.add(nums[i]);
        }
        //从第k+1个元素开始扫描
        for(int i = k;k < nums.length;i++){
            if(pq.findMin() < nums[i]){
                pq.replace(nums[i]);
            }
        }
        return pq.findMin();
    }

}
