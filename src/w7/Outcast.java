package w7;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * @author
 * @create 2018-02-25-16:56
 **/
public class Outcast {
    // constructor takes a WordNet object
    private final WordNet wordnet;

    public Outcast(WordNet wordnet) {
        this.wordnet = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        int max = -1;
        String smax = "";
        for (int i = 0; i < nouns.length; i++) {
            if (!wordnet.isNoun(nouns[i])) continue;
            int length = 0;
            for (int j = 0; j < nouns.length; j++) {
                if (j == i) continue;
                if (!wordnet.isNoun(nouns[j])) continue;
                length += wordnet.distance(nouns[i], nouns[j]);
            }
            if (length > max) {
                max = length;
                smax = nouns[i];
            }
        }
        return smax;
    }

    // see test client below
    public static void main(String[] args) {
        Stopwatch stopwatch1=new Stopwatch();
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
        StdOut.println(stopwatch1.elapsedTime());
    }
}
