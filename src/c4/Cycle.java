package c4;

import c4.s1.Graph;

public class Cycle {

    private boolean marked[];
    private boolean hasCycle;

    public Cycle(Graph graph) {
        marked = new boolean[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            if (!marked[i]) {
                dfs(graph, i, i);
            }
        }
    }

    private void dfs(Graph graph, int s, int u) {
        marked[s] = true;
        for (int w : graph.adj(s)) {
            if (!marked[w]) {
                dfs(graph, w, s);
            } else {
                if (w != u) {
                    hasCycle = true;
                }
            }
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }
}
