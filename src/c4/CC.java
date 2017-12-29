package c4;

import edu.princeton.cs.algs4.Queue;

public class CC {
    private boolean marked[];
    private int count;
    private int id[];

    public CC(Graph graph) {
        marked = new boolean[graph.V()];
        id = new int[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            if (!marked[i]) {
                dfs(graph, i);
                count++;
            }
        }
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : graph.adj(v)) {
            if (!marked[w]) {
//                dfs(graph, w);
                bfs(graph,v);
            }
        }
    }

    private void bfs(Graph graph, int s) {
        Queue<Integer> queue = new Queue<Integer>();
        marked[s] = true;
        id[s] = count;
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : graph.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    id[w] = count;
                    queue.enqueue(w);
                }
            }
        }
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }
}
