package WeightedGraphBuild;

/**
 * TODO
 * <p>
 *  @author 86080
 *  @date 2023/10/12 16:10
 *  @version 1.0
 */
public class WeightedEdge implements Comparable<WeightedEdge>{
    private int v,w,weight;
    public WeightedEdge(int v,int w,int weight){
        this.v= v;
        this.w= w;
        this.weight= weight;
    }

    public int getV() {
        return v;
    }

    public int getW() {
        return w;
    }

    @Override
    public int compareTo(WeightedEdge another) {
        return this.weight - another.weight;
    }
    @Override
    public String toString(){
        return String.format("(%d-%d: %d)", v, w, weight);
    }

    public int getWeight() {
        return weight;
    }
}
