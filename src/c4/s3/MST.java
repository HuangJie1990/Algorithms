package c4.s3;

/**
 * 最小生成树的API
 *
 * @author huangjie
 * @create 2018-02-27-9:14
 **/
public abstract class MST {
    public abstract Iterable<Edge> edges();

    public abstract double weight();

    public abstract Iterable<Edge> mfes();
}
