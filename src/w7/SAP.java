package w7;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;

/**
 * @author huangjie
 * @create 2018-02-25-14:01
 **/
public class SAP {

    private final Digraph digraph;
    private final HashMap<Children, Ancestor> map;


    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null) throw new IllegalArgumentException("argument is null");
        digraph = new Digraph(G);
        map = new HashMap<>();
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        validate(v);
        validate(w);
        Children c1 = new Children(v, w);
        if (map.containsKey(c1)) return map.get(c1).length;
        Children c2 = new Children(w, v);
        if (map.containsKey(c2)) return map.get(c2).length;
        findAncestor(v, w);
        return map.get(c1).length;
    }

    // a common ancestor of v and w thar participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        validate(v);
        validate(w);
        Children c1 = new Children(v, w);
        if (map.containsKey(c1)) return map.get(c1).ancestor;
        Children c2 = new Children(w, v);
        if (map.containsKey(c2)) return map.get(c2).ancestor;
        findAncestor(v, w);
        return map.get(c1).ancestor;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null) throw new IllegalArgumentException("argument v is null");
        if (w == null) throw new IllegalArgumentException("argument w is null");
        for (int i : v) validate(i);
        for (int i : w) validate(i);
        BreadthFirstDirectedPaths bfdpv = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfdpw = new BreadthFirstDirectedPaths(digraph, w);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < digraph.V(); i++) {
            if (bfdpv.hasPathTo(i) && bfdpw.hasPathTo(i)) {
                int length = bfdpv.distTo(i) + bfdpw.distTo(i);
                if (length < min) {
                    min = length;
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null) throw new IllegalArgumentException("argument v is null");
        if (w == null) throw new IllegalArgumentException("argument w is null");
        for (int i : v) validate(i);
        for (int i : w) validate(i);
        BreadthFirstDirectedPaths bfdpv = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfdpw = new BreadthFirstDirectedPaths(digraph, w);
        int ancestor = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < digraph.V(); i++) {
            if (bfdpv.hasPathTo(i) && bfdpw.hasPathTo(i)) {
                int length = bfdpv.distTo(i) + bfdpw.distTo(i);
                if (length < min) {
                    ancestor = i;
                    min = length;
                }
            }
        }
        return ancestor;
    }

    private void findAncestor(int v, int w) {
        BreadthFirstDirectedPaths bfdpv = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfdpw = new BreadthFirstDirectedPaths(digraph, w);
        int ancestor = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < digraph.V(); i++) {
            if (bfdpv.hasPathTo(i) && bfdpw.hasPathTo(i)) {
                int length = bfdpv.distTo(i) + bfdpw.distTo(i);
                if (length < min) {
                    ancestor = i;
                    min = length;
                }
            }
        }
        if (ancestor == -1) {
            map.put(new Children(v, w), new Ancestor(-1, -1));
            return;
        }
        map.put(new Children(v, w), new Ancestor(ancestor, min));
    }

    private void validate(int v) {
        if (v < 0 || v >= digraph.V()) throw new IllegalArgumentException("illegal vertex");
    }

    private static class Children {
        private final int v;
        private final int w;

        public Children(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Children children = (Children) o;

            if (v != children.v) return false;
            return w == children.w;
        }

        @Override
        public int hashCode() {
            int result = v;
            result = 31 * result + w;
            return result;
        }
    }

    private class Ancestor {
        private final int ancestor;
        private final int length;

        Ancestor(int ancestor, int length) {
            this.ancestor = ancestor;
            this.length = length;
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph digraph = new Digraph(in);
        SAP sap = new SAP(digraph);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
