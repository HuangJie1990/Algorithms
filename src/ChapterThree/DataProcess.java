package ChapterThree;

import edu.princeton.cs.algs4.In;

public class DataProcess {

    public static void main(String[] args){
        In in=new In(args[0]);
        while (!in.isEmpty()){
            String s=in.readString();
            if(s==".") s=" ";
            if(s==",") s=" ";
            if(s==":") s=" ";
            if(s=="?") s=" ";
            if(s=="!") s=" ";
            s=s.toLowerCase();
            System.out.print(s+" ");
        }
    }
}
