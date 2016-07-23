package UnionFind;

/**
 * Created by KUBA on 2016-06-20.
 */
public class QuickUnion {
    private int[] id;
    private int count;

    public QuickUnion(int N){
        count = N;
        id = new int[N];
        for(int i = 0; i < N; i++)
            id[i] = i;
    }

    public int count(){
        return count;
    }

    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    private int find(int p){
        while(p != id[p])
            p = id[p];
        return p;
    }

    public void union(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot == qRoot)
            return;
        id[pRoot] = qRoot;
        count--;
    }
}
