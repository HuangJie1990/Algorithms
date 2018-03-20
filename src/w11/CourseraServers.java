package w11;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author huangjie
 * @create 2018-03-20-16:12
 **/
public class CourseraServers {
    public static void main(String[] args) {
        In in = new In(args[0]);
        String s = in.readAll();
        String[] servers = s.split(",");
        for (String server : servers) StdOut.println(server+"\t"+"d3c33hcgiwev3.cloudfront.net");
    }
}
