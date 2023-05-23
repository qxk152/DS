/**
 * TODO
 *
 * @author 86080
 * @version 1.0
 * @date 2022/09/02 12:13
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {
    MaxHeap<E> maxHeap;
    public PriorityQueue(){
        maxHeap = new MaxHeap();
    }
    @Override
    public int size() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }


    @Override
    public E dequeue() {
        return maxHeap.extractMax();
    }

    @Override
    public E getFront() {
        return maxHeap.findMax();
    }
}
