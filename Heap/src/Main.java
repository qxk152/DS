import java.util.Random;

/**
 * TODO
 *
 * @author 86080
 * @version 1.0
 * @date 2022/08/31 18:05
 */
public class Main {
    //isHeapify 是否使用直接将数组调整成堆还是向空堆中一个一个添加元素
    public static double  testHeapify(Integer[] testData,boolean isHeapify){
        long startTime = System.nanoTime();
        MaxHeap<Integer> maxHeap;
        if(isHeapify){
            maxHeap = new MaxHeap<>(testData);
        }else{
            maxHeap = new MaxHeap<>();
            for (int i = 0; i < testData.length; i++) {
                maxHeap.add(testData[i]);
            }
        }

        int[] arrs = new int[testData.length];
        for (int i = 0; i < testData.length; i++) {
            arrs[i] = maxHeap.extractMax();
        }
        for(int i = 1;i < testData.length;i++){
            if(arrs[i-1] < arrs[i]){
                throw new IllegalArgumentException("error");
            }
        }
        System.out.println("Heap is complete");
        long endTime = System.nanoTime();
        return (endTime -startTime )/1000000000.0;
    }
    public static void main(String[] args) {
        int n = 1000000;
        Random random = new Random();
        Integer [] testData = new Integer[n];
        for (int i = 0; i < testData.length; i++) {
            testData[i] = random.nextInt(Integer.MAX_VALUE);
        }
        System.out.println("without heapify:"+testHeapify(testData,false));
        System.out.println("with heapify:"+testHeapify(testData,true));
//0.031456
    }
}
