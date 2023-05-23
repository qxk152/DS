/**
 * TODO
 *
 * @author 86080
 * @version 1.0
 * @date 2022/08/31 11:43
 */
//因为节点必须孩子节点小于顶点 必须可比较
public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }
    public MaxHeap() {
        data = new Array<>();
    }
    //传入一个数组 调整成为符合大顶堆的数组
    public MaxHeap(E[] arr){
        data = new Array<>(arr);
        //调整为堆
        for(int i = parent(data.getSize()-1) ;i >=0 ;i--){
            siftDown(i);
        }
    }
    //返回堆中的元素个数
    public int size(){
        return data.getSize();
    }
    public boolean isEmpty(){
        return data.isEmpty();
    }
    //返回一个完全二叉树表示的数组表示 一个索引为index节点的父亲节点的索引 0开始
    private int parent(int index){
        if(index == 0 ){
            throw new IllegalArgumentException("index 0 doesn't have parent");
        }
        return (index - 1)/2;
    }
    //返回一个完全二叉树表示的数组表示 一个索引为index节点的左孩子节点的索引 0开始
    private int leftChild(int index){
        return index * 2 + 1;
    }
    //返回一个完全二叉树表示的数组表示 一个索引为index节点的右孩子节点的索引 0开始
    private int rightChild(int index){
        return index * 2 + 2;
    }
    //向大顶堆添加元素
    public void add(E e){
        //将元素添加到数组的最后一个位置
        data.addLast(e);
        //将这一元素的索引传入进行上浮操作
        siftUp(data.getSize() - 1);
    }
    //将对索引为k的节点进行上浮操作
    private void siftUp(int k) {
        //k为0 根节点无父亲节点
        while(k > 0 && data.get(k).compareTo(data.get(parent(k))) > 0){
            data.swap(k,parent(k));
            k = parent(k);
        }
    }
    //找出最大元素 并返回
    public E findMax(){
        if(data.getSize() == 0){
            throw new IllegalArgumentException("cannnot find max value when heap is empty");
        }
        return data.get(0);
    }

    //取出堆中的最大元素 并返回
    public E extractMax(){
        E ret = findMax();
        //用数组最后一个元素和最大的元素交换位置
        data.swap(0,data.getSize() - 1);
        data.removeLast();
        //需要下沉的节点的索引
        siftDown(0);
        return ret;
    }

    private void siftDown(int index) {
        //是分支节点不是叶子节点就循环
        //如果左孩子节点的索引不在数组长度内那么右孩子一定不再
        while(leftChild(index) < data.getSize()){
            //一定有左孩子 不一定有右孩子
            int j = leftChild(index);
            //左孩子索引加1就是右孩子索引
            if(j + 1 < data.getSize() && data.get(j+1).compareTo(data.get(j))>0){
                j++; //有右孩子并且右孩子的值大 j指向右孩子
            }//data[j] 存放的是左右孩子的最大值
            if(data.get(index).compareTo(data.get(j))>=0){
                break;
            }
            data.swap(j,index);
            index = j;
        }

    }
    //取出最大的元素并将新的元素e替换堆顶元素
    public E replace(E e){
        E max = findMax();
        data.set(0,e);
        siftDown(0);
        return max;
    }
}
