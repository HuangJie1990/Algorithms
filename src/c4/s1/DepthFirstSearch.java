package c4.s1;

public class DepthFirstSearch extends DFS {
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph graph, int s) {
        marked = new boolean[graph.V()];
        dfs(graph, s);
    }

    private void dfs(Graph graph, int s) {
        marked[s] = true;
        count++;
        for (int w : graph.adj(s)) {
            if (!marked[w]) {
                dfs(graph, w);
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public int count() {
        return count;
    }
}
