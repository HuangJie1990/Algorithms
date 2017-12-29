package c4;


import c4.s2.Digraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class DepthFirstOrder {

    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph digraph) {
        pre = new Queue<Integer>();
        post = new Queue<>();
        reversePost = new Stack<>();
        marked = new boolean[digraph.V()];
        for (int v = 0; v < digraph.V(); v++) {
            if (!marked[v]) dfs(digraph, v);
        }
    }

    private void dfs(Digraph digraph, int v) {
        pre.enqueue(v);

        marked[v] = true;
        for (int w : digraph.adj(v)) {
            if (!marked[w])
                dfs(digraph, w);
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}
