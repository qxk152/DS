public class SkipList {
    private static final double SKIPLIST_P = 0.25;
    private static final int MAX_LEVEL = 32;
    Node head ; //头节点
    int curLevel ; //当前的最大层数

    public SkipList(){
        head = new Node(null,MAX_LEVEL);
        curLevel = 0;
    }
    //首先生成随机的层数，找到curLevel中小于随机层数的层中进行插入，如果随机层数> 当前层数 那么就更新
    public void add(int value){
        int level = randomLevel();
        Node newNode = new Node(value,level);
        Node updateNode = head;
        // 计算出当前value 索引的实际层数，从该层开始添加索引
        //对于小于curLevel的层数全部添加顶点
        for (int i = curLevel - 1; i >= 0 ;i -- ){
            updateNode = findClosest(updateNode,value,i);
            if(i < level){
                // 如level=2时,只要求索引为0,1的层进行插入
                if(updateNode.next[i] == null){
                    updateNode.next[i] = newNode;
                }else {
                    //执行正常的插入操作
                    newNode.next[i] = updateNode.next[i];
                    updateNode.next[i] = newNode;
                }
            }
        }
        if (level > curLevel){
            for(int i = curLevel;i < level;i++){
                head.next[i] = newNode;
            }
            //更新此时最大层
            curLevel = level;
        }
    }
    /*
    从curNode开始:
    寻找level层内最靠近value的左边节点(如value=6,5->6->7,停留在5;如target=6,5->7->8,停留在5)
    */
    private Node findClosest(Node cur,int value,int levelIndex){
        while(cur.next[levelIndex] != null && cur.next[levelIndex].val < value){
            cur = cur.next[levelIndex];
        }
        return cur;
    }


    // 该 randomLevel 方法会随机生成 1~MAX_LEVEL 之间的数，且 ：
    //        1/4 的概率返回 1
    //        1/16 的概率返回 2
    //        1/64 的概率返回 3 以此类推
    private int randomLevel(){
        int level = 1 ;
        while(Math.random() < SKIPLIST_P && level < MAX_LEVEL){
            level++;
        }
        return level;
    }
    /*
        查找跳表内是否存在target元素
    */
    public boolean search(int target) {
        Node searchNode = head;
        for(int i = curLevel - 1; i >= 0;i --){
            // searchNode停留在<=target的左边
            searchNode = findClosest(searchNode,target,i);
            if(searchNode.next[i] != null && searchNode.next[i].val == target){
                return true;
            }
        }
        return  false;
    }

    /*
    往跳表内删除元素num
    */
    public boolean erase(int target) {
        Node delNode = head;
        // 删除成功标记
        boolean flag = false;
        for (int i = curLevel - 1; i >= 0;i --){
            delNode = findClosest(delNode,target,i);
            // 可能有多个相同的值(如果存在多个num删除其中任意一个即可)
            // 当出现多个重复值且前面节点层数低后面层数高,一层一层删下来其实删除的不是同一个节点
            // 但是不会影响最终结果
            if(delNode.next[i]!= null && delNode.next[i].val == target){
                delNode.next[i] = delNode.next[i].next[i];
                flag = true;
            }
        }
        return  flag;
    }

     class Node{
        Integer val;
        Node[] next;
        public Node(Integer val,Integer size){
            this.val = val;
            this.next = new Node[size];
        }
        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }

}