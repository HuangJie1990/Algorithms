package w11;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

/**
 * @author huangjie
 * @create 2018-03-16-15:17
 **/
public class MoveToFront {

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        char[] position = new char[256];
        for (char i = 0; i < position.length; i++) {
            position[i] = i;
        }
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            BinaryStdOut.write(position[c]);
            for (int i = 0; i < 256; i++) {
                if (position[i] < position[c]) position[i]++;
            }
            position[c] = 0;
        }
        BinaryStdOut.close();
    }

    public static void decode() {
        char[] position = new char[256];
        for (char i = 0; i < position.length; i++) {
            position[i] = i;
        }
        while (!BinaryStdIn.isEmpty()) {
            char code = BinaryStdIn.readChar();
            char temp = position[code];
            BinaryStdOut.write(temp);
            for (int i = code; i >= 1; i--) {
                position[i] = position[i - 1];
            }
            position[0] = temp;
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        if (args[0].contentEquals("-")) MoveToFront.encode();
        else if (args[0].contentEquals("+")) MoveToFront.decode();
    }
}
