package GraphBuild;

public interface Graph {

    int V();
    int E();
    boolean hasEdge(int v, int w);
    Iterable<Integer> adj(int v);
    int degree(int v);
    void validateVertex(int v);}