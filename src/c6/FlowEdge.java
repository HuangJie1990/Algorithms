package c6;

/**
 * 流量网络中边的API
 *
 * @author huangjie
 * @create 2018-03-06-11:40
 **/
public class FlowEdge {
    private final int v;
    private final int w;
    private final double capacity;
    private double flow;

    public FlowEdge(int v, int w, double cap) {
        this.v = v;
        this.w = w;
        this.capacity = cap;
        flow = 0.0;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public int other(int vertex) {
        if (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new RuntimeException("Inconsistent edge");
    }

    public double capacity() {
        return capacity;
    }

    public double flow() {
        return flow;
    }

    //v的剩余容量
    public double residualCapacity(int vertex) {
        if (vertex == v) return flow;
        else if (vertex == w) return capacity - flow;
        else throw new RuntimeException("Inconsistent edge");
    }

    //将v的流量增加到delta
    public void addResidualFlowTo(int vertex, double delta) {
        if (vertex == v) flow -= delta;
        else if (vertex == w) flow += delta;
        else throw new RuntimeException("Inconsistent edge");
    }

    public String toString() {
        return String.format("%d->%d %.2f %.2f", v, w, capacity, flow);
    }
}
