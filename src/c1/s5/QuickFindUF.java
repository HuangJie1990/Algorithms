package c1.s5;

public class QuickFindUF extends UF {

    public QuickFindUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
        count = N;
    }

    @Override
    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        if (pid == qid) return;
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) id[i] = qid;
        }
        count--;
    }

    @Override
    public int find(int i) {
        return id[i];
    }

    @Override
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }
}
