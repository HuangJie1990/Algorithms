package w7;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author huangjie
 * @create 2018-02-25-14:00
 **/
public class WordNet {
    private final HashMap<String, Bag<Integer>> map;
    private final SAP sap;
    private final LinkedList<String> nouns;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null) throw new IllegalArgumentException("argument synsets is null");
        if (hypernyms == null) throw new IllegalArgumentException("argument hypernyms is null");
        In in = new In(synsets);
        map = new HashMap<>();
        nouns = new LinkedList<>();
        int n = 0;
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(",");
            int v = Integer.parseInt(a[0]);
            nouns.add(v, a[1]);
            String[] words = a[1].split(" ");
            for (String word : words) {
                if (!map.containsKey(word)) {
                    Bag<Integer> bag = new Bag<>();
                    bag.add(v);
                    map.put(word, bag);
                } else map.get(word).add(v);
            }
            n++;
        }
        in = new In(hypernyms);
        boolean[] roots = new boolean[n];
        Digraph digraph = new Digraph(n);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(",");
            int v = Integer.parseInt(a[0]);
            roots[v] = a.length == 1 ? false : true;
            for (int i = 1; i < a.length; i++) {
                int w = Integer.parseInt(a[i]);
                digraph.addEdge(v, w);
            }
        }
        int i = 0;
        for (boolean b : roots) if (!b) i++;
        if (i != 1) throw new IllegalArgumentException("digraph is not rooted digraph");
        DirectedCycle dc = new DirectedCycle(digraph);
        if (dc.hasCycle()) throw new IllegalArgumentException("digraph has cycle");
        sap = new SAP(digraph);
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return map.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) throw new IllegalArgumentException("argument is null");
        return map.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        validate(nounA);
        validate(nounB);
        Bag<Integer> v = map.get(nounA);
        Bag<Integer> w = map.get(nounB);
        return sap.length(v, w);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        validate(nounA);
        validate(nounB);
        Bag<Integer> v = map.get(nounA);
        Bag<Integer> w = map.get(nounB);
        int ancestor = sap.ancestor(v, w);
        return nouns.get(ancestor);
    }

    private void validate(String noun) {
        if (noun == null) throw new IllegalArgumentException("argument is null");
        if (!isNoun(noun)) throw new IllegalArgumentException("argument is not a WordNet noun");
    }

    // do unit testing of this class
    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Iterable<String> nouns = wordnet.nouns();
        while (!StdIn.isEmpty()) {
            String nounA = StdIn.readString();
            String nounB = StdIn.readString();
            int distance = wordnet.distance(nounA, nounB);
            String sap = wordnet.sap(nounA, nounB);
            StdOut.printf("distance = %d, sap = %s\n", distance, sap);
        }
    }
}
