import java.util.TreeMap;

/**
 * TODO
 * 实现字典树
 * @author 86080
 * @version 1.0
 * @date 2022/12/28 21:35
 */
public class Trie {
    //只适用于Character类型
    private class Node{
        //代表当前节点是否是一个单词的结尾
        public boolean isWord;
        //存放要找的字符和其所在节点的映射
        public TreeMap<Character,Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }
        public Node(){
            this(false);
        }
    }

    private Node root;

    // 保存的是Trie中单词的数量不是节点的数量
    private int size;
    public Trie(){
        size = 0;
        root = new Node();
    }
    public int getSize(){
        return size;
    }
    // 向Trie中添加一个新的单词word
    public void add(String word){
        Node cur = root;
        for (int i = 0; i <word.length(); i++) {
            char c = word.charAt(i);
            //在next中查找是否有该字符
            if(cur.next.get(c) == null){
                cur.next.put(c,new Node());
            }
            //若有该字符 或者没有创建好之后把cur指向该字符对应的Node
            cur = cur.next.get(c);
        }
        //循环结束后 cur指向的是单词结尾的节点
        if(!cur.isWord){
            cur.isWord = true;
            size ++;
        }
    }

    // 查询单词word是否在Trie中
    public boolean contains(String word){
        Node cur = root;
        for(int i = 0 ; i < word.length() ; i ++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        //不能直接返回true 因为未必是之前存储过的单词
        return cur.isWord;
    }
    //// 查询是否在Trie中有单词以prefix为前缀
    public boolean isPrefix(String prefix){
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(cur.next.get(c) == null){
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }
}
