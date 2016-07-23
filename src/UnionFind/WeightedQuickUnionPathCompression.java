package UnionFind;

/**
 * Created by KUBA on 2016-06-20.
 */
public class WeightedQuickUnionPathCompression {
    public int[] id;
    private int[] sz;
    private int count;

    public WeightedQuickUnionPathCompression(int N){
        count = N;
        id = new int[N];
        for(int i = 0; i < N; i++)
            id[i] = i;
        sz = new int[N];
        for(int i = 0; i < N; i++)
            sz[i] = 1;
    }

    public int count(){
        return count;
    }

    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    private int find(int p){
        int root = p;
        while(root != id[root])
            root = id[root];
        while(p != root) {
            int newp = id[p];
            id[p] = root;
            p = newp;
        }

        return root;
    }

    public void union(int p, int q){
        int i = find(p);
        int j = find(q);
        if(i == j)
            return;

        if(sz[i] < sz[j]){
            id[i] = j;
            sz[j] += sz[i];
        }
        else{
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }
}