import java.util.Arrays;

/**
 * TODO
 *
 * @author 86080
 * @version 1.0
 * @date 2022/08/31 18:17
 */
public class HeapSort {
    private HeapSort() {
    }

    public static <E extends Comparable<E>> void sort(E data[]) {
        MaxHeap<E> maxHeap = new MaxHeap<>();
        for (E e : data) {
            maxHeap.add(e);
        }
        //n个元素 每放一个元素需要logn的时间复杂度 所以是nlogn的复杂度 上下同理
        for (int i = data.length - 1; i >= 0; i--) {
            data[i] = maxHeap.extractMax();
        }

    }

    //优化后的堆排序
    public static <E extends Comparable<E>> void sort2(E data[]) {
        if (data.length <= 1) {
            return;
        }
        for(int i = (data.length - 2)/2;i >=0;i-- ){
            siftDown(data,i,data.length);
            //将整个数组调整为最大堆
        }
        for(int i = data.length -1;i >=0;i--){
            swap(data,0,i);
            siftDown(data,0,i);
        }
    }
    //[0，n)的最大堆中对索引为k 的节点 进行下沉
    private static  <E extends Comparable<E>> void siftDown(E data[],int k,int n){
        while(2 * k +1 < n){
            int j = 2*k+1;
            if(j+1 < n && data[j+1].compareTo(data[j])>0){
                j++;
            }
            if(data[k].compareTo(data[j])>0)
                break;
            swap(data,k,j);
            k = j;
        }

    }
    private static <E extends Comparable<E>>  void swap(E data[],int i,int j){
        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        Integer[] arr4 = Arrays.copyOf(arr, arr.length);
        Integer[] arr5 = Arrays.copyOf(arr, arr.length);

        SortingHelper.sortTest("MergeSort", arr);
        SortingHelper.sortTest("QuickSort2Ways", arr2);
        SortingHelper.sortTest("QuickSort3Ways", arr3);
        SortingHelper.sortTest("HeapSort", arr4);
        SortingHelper.sortTest("HeapSort2", arr5);
    }

}
