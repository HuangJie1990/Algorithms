package ChapterThree;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FrequencyCounter {

    private FrequencyCounter() {
    }

    public static void main(String[] args) {
        /*int minLength = Integer.parseInt(args[0]);
        ST<String, Integer> st = new BinarySearchST<>();
        In in = new In(args[1]);
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() < minLength) continue;
            if (!st.contains(word)) st.put(word, 1);
            else st.put(word, st.get(word) + 1);
        }

        String max = " ";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max)) max = word;
        }
        StdOut.println(max + " " + st.get(max));*/

        String[] s = new String[]{"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"};
        int[] ints = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
      /*  BST<String, Integer> st = new BST<>();
        System.out.println(st.isEmpty());
        for (int i = 0; i < s.length; i++) {
            st.put(s[i], ints[i]);
        }
        for(String key:st.keys()) System.out.println(String.format("%s's value is %d, %s's rank is %d, %s's size is %d.",key,st.get(key),key,st.rank(key),key,st.size(st.min(),key)));
        System.out.print(String.format("The size of st is %d.\nThe max key of st is %s.\nThe min key of st is %s.\n", st.size(), st.max(),st.min()));
        System.out.print(String.format("The rank of \"E\" is %d.\nThe rank of \"D\" is %d.\n",st.rank("E"),st.rank("D")));
        System.out.print(String.format("The rank of \"P\" is %d.\nThe rank of \"N\" is %d.\n",st.rank("P"),st.rank("N")));
        System.out.print(String.format("The size between \"E\" and \"P\" is %d.\nThe size between \"D\" and \"N\" is %d.\n",st.size("E","P"),st.size("D","N")));
        System.out.print(String.format("The floor of \"E\" is %s. The ceiling of \"E\" is %s.\n",st.floor("E"),st.ceiling("E")));
        System.out.print(String.format("The floor of \"N\" is %s. The ceiling of \"N\" is %s.\n",st.floor("N"),st.ceiling("N")));
        System.out.print(String.format("The floor of \"Y\" is %s. The ceiling of \"Y\" is %s.\n",st.floor("Y"),st.ceiling("Y")));
        System.out.println();
        st.put("H",null);
        for(String key:st.keys()) System.out.println(String.format("%s's value is %d, %s's rank is %d, %s's size is %d",key,st.get(key),key,st.rank(key),key,st.size(st.min(),key)));
        System.out.println();
        st.delete("N");
        for(String key:st.keys()) System.out.println(String.format("%s's value is %d, %s's rank is %d, %s's size is %d",key,st.get(key),key,st.rank(key),key,st.size(st.min(),key)));
        System.out.println();
        st.delete("E");
        for(String key:st.keys()) System.out.println(String.format("%s's value is %d, %s's rank is %d, %s's size is %d",key,st.get(key),key,st.rank(key),key,st.size(st.min(),key)));
        for (int i = 0; i < st.size(); i++) {
            System.out.print(st.select(i)+" ");
        }*/

        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>(31);
        for (int i = 0; i < s.length; i++) {
            st.put(s[i], ints[i]);
        }
        for (String key : st.keys()) {
            System.out.println(String.format("%s's value is %d.",key,st.get(key)));
        }
    }
}
