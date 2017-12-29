package c4;

import ChapterOne.QuickFindUF;

public class Search {
    private Graph graph;
    private int s;
    private QuickFindUF quickFindUF;

    public Search(Graph graph, int s) {
        this.graph = graph;
        this.s = s;
        quickFindUF = new QuickFindUF(graph.V());
        for (int v = 0; v < graph.V(); v++) {
            for (int w : graph.adj(v)) {
                if (!quickFindUF.connected(w, v))
                    quickFindUF.union(w, v);
            }
        }
    }

    public boolean marked(int v) {
        return quickFindUF.connected(s, v);
    }


    public int count() {
        int count = 0;
        for (int v = 0; v < graph.V(); v++) {
            if (quickFindUF.connected(s, v)) {
                count++;
            }
        }
        return count;
    }
}
