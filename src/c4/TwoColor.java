package c4;

import c4.s1.Graph;

public class TwoColor {

    private boolean[] marked;
    private boolean[] colored;
    private boolean isTwoColorable = true;

    public TwoColor(Graph graph) {
        marked = new boolean[graph.V()];
        colored = new boolean[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            if (!marked[i]) {
                dfs(graph, i);
            }
        }
    }

    private void dfs(Graph graph, int s) {
        marked[s] = true;
        for (int w : graph.adj(s)) {
            if (!marked[w]) {
                colored[w] = !colored[s];
                dfs(graph, w);
            } else {
                if (colored[w] == colored[s]) {
                    isTwoColorable = false;
                }
            }
        }
    }

    public boolean isTwoColorable() {
        return isTwoColorable;
    }
}
