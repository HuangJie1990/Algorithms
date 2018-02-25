package w7;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.HashMap;

/**
 * @author huangjie
 * @create 2018-02-25-14:00
 **/
public class WordNet {
    private String[] nouns;
    private HashMap<String, Bag<Integer>> map;
    private SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null) throw new IllegalArgumentException("argument synsets is null");
        if (hypernyms == null) throw new IllegalArgumentException("argument hypernyms is null");
        map = new HashMap<>();
        In in = new In(synsets);
        int n = 0;
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(",");
            int v = Integer.parseInt(a[0]);
            if (!map.containsKey(a[1])) {
                Bag<Integer> bag = new Bag<>();
                bag.add(v);
                map.put(a[1], bag);
            } else map.get(a[1]).add(v);
            n++;
        }
        nouns = new String[n];
        for (String noun : map.keySet()) {
            for (int i : map.get(noun)) nouns[i] = noun;
        }
        in = new In(hypernyms);
        Digraph digraph = new Digraph(n);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(",");
            int v = Integer.parseInt(a[0]);
            for (int i = 1; i < a.length; i++) {
                int w = Integer.parseInt(a[i]);
                digraph.addEdge(v, w);
            }
        }
        sap = new SAP(digraph);
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        Bag<String> bag = new Bag<>();
        for (String noun : nouns) bag.add(noun);
        return bag;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) throw new IllegalArgumentException("argument is null");
        return map.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (nounA == null) throw new IllegalArgumentException("argument nounA is null");
        if (nounA == null) throw new IllegalArgumentException("argument nounB is null");
        Bag<Integer> v = map.get(nounA);
        Bag<Integer> w = map.get(nounB);
        return sap.length(v, w);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (nounA == null) throw new IllegalArgumentException("argument nounA is null");
        if (nounA == null) throw new IllegalArgumentException("argument nounB is null");
        Bag<Integer> v = map.get(nounA);
        Bag<Integer> w = map.get(nounB);
        int ancestor = sap.ancestor(v, w);
        return nouns[ancestor];
    }

    // do unit testing of this class
    public static void main(String[] args) {
    }
}
