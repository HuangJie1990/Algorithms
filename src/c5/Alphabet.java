package c5;


import edu.princeton.cs.algs4.StdOut;

public class Alphabet {

    public static final Alphabet BINARY = new Alphabet("01");
    public static final Alphabet DNA = new Alphabet("ACTG");
    public static final Alphabet OCTAL = new Alphabet("01234567");
    public static final Alphabet DECIMAL = new Alphabet("0123456789");
    public static final Alphabet HEXADECIMAL = new Alphabet("0123456789ABCDEF");
    public static final Alphabet PROTEIN = new Alphabet("ACDEFGHIKLMNPQRSTVWY");
    public static final Alphabet LOWERCASE = new Alphabet("abcdefghijklmnopqrstuvwxyz");
    public static final Alphabet UPPERCASE = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    public static final Alphabet BASE64 = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
    public static final Alphabet ASCII = new Alphabet(128);
    public static final Alphabet EXTENDED_ASCII = new Alphabet(256);
    public static final Alphabet UNICODE16 = new Alphabet(65536);
    private final int R;
    private char[] alphabet;
    private int[] inverse;

    public Alphabet(String alpha) {
        boolean[] unicode = new boolean[Character.MAX_VALUE];
        for (int i = 0; i < alpha.length(); i++) {
            char c = alpha.charAt(i);
            if (unicode[c]) throw new IllegalArgumentException("Illegal Alphabet: repeated character= '" + c + "'");
            unicode[c] = true;
        }

        alphabet = alpha.toCharArray();
        R = alphabet.length;
        inverse = new int[Character.MAX_VALUE];
        for (int i = 0; i < inverse.length; i++) {
            inverse[i] = -1;
        }
        for (int i = 0; i < R; i++) {
            inverse[alphabet[i]] = i;
        }
    }

    private Alphabet(int radix) {
        R = radix;
        alphabet = new char[radix];
        inverse = new int[radix];
        for (int i = 0; i < radix; i++) {
            alphabet[i] = (char) i;
        }
        for (int i = 0; i < radix; i++) {
            inverse[i] = i;
        }
    }

    public Alphabet() {
        this(256);
    }

    public static void main(String[] args) {
        int[] encoded1 = Alphabet.BASE64.toIndices("NowIsTheTimeForAllGoodMen");
        for (int i : encoded1) {
            StdOut.print(i + " ");
        }
        String decoded1 = Alphabet.BASE64.toChars(encoded1);
        StdOut.println(decoded1);

        int[] encoded2 = Alphabet.DNA.toIndices("AACGAACGGTTTACCCCG");
        for (int i : encoded2) {
            StdOut.print(i + " ");
        }
        String decoded2 = Alphabet.DNA.toChars(encoded2);
        StdOut.println(decoded2);

        int[] encoded3 = Alphabet.DECIMAL.toIndices("01234567890123456789");
        for (int i : encoded3) {
            StdOut.print(i + " ");
        }
        String decoded3 = Alphabet.DECIMAL.toChars(encoded3);
        StdOut.println(decoded3);
    }

    public char toChar(int index) {
        if (index < 0 || index > R) throw new IllegalArgumentException("index must between 0 and " + R + ": " + index);
        return alphabet[index];
    }

    public int toIndex(char c) {
        if (c >= inverse.length || inverse[c] == -1)
            throw new IllegalArgumentException("Character " + c + " not in alphabet");
        return inverse[c];
    }

    public int charAt(String s, int d) {
        if (d < s.length()) return inverse[s.charAt(d)];
        return -1;
    }

    public boolean contains(char c) {
        return inverse[c] != -1;
    }

    public int R() {
        return R;
    }

    public int lgR() {
        int lgR = 0;
        for (int i = R; i >= 1; i /= 2) {
            lgR++;
        }
        return lgR;
    }

    public int[] toIndices(String s) {
        int[] indices = new int[s.length()];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            indices[i] = toIndex(chars[i]);
        }
        return indices;
    }

    public String toChars(int[] indices) {
        char[] chars = new char[indices.length];
        for (int i = 0; i < indices.length; i++) {
            chars[i] = toChar(indices[i]);
        }
        return new String(chars);
    }
}
