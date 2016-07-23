package DataStructures;

import java.util.NoSuchElementException;

/**
 * Created by KUBA on 2016-06-27.
 */
public class HeapIndexMinPriorityQueue<Key extends Comparable<Key>>{

    private int n;
    private int maxN;
    private int[] pq;
    private int[] qp;
    private Key[] keys;

    public HeapIndexMinPriorityQueue(int maxN){
        if(maxN < 0)
            throw new IllegalArgumentException();
        this.maxN = maxN;
        n = 0;
        keys = (Key[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        for(int i = 0; i <= maxN; i++)
            qp[i] = -1;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public boolean contains(int k){
        if(k < 0 || k >= maxN)
            throw new IndexOutOfBoundsException();
        return qp[k] != -1;
    }

    public int size(){
        return n;
    }

    public void insert(int k, Key key){
        if(k < 0 || k >= maxN)
            throw new IndexOutOfBoundsException();
        if(contains(k))
            throw new IllegalArgumentException("Index is already in the priority queue");
        n++;
        qp[k] = n;
        pq[n] = k;
        keys[k] = key;
        swim(n);
    }

    public int minIndex(){
        if(n == 0)
            throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    public Key minKey(){
        if(n == 0)
            throw new NoSuchElementException("Priority queue underflow");
        return keys[pq[1]];
    }

    public int delMin(){
        if(n == 0)
            throw new NoSuchElementException("Priority queue underflow");
        int indexOfMin = pq[1];
        exch(1, n--);
        sink(1);
        assert indexOfMin == pq[n+1];
        qp[indexOfMin] = -1;
        keys[indexOfMin] = null;
        pq[n+1] = -1;
        return indexOfMin;
    }

    public void changeKey(int k, Key key){
        if(k < 0 || k >= maxN)
            throw new IndexOutOfBoundsException();
        if(!contains(k))
            throw new NoSuchElementException("Index is not in the priority queue");
        keys[k] = key;
        swim(qp[k]);
        sink(qp[k]);
    }

    public void delete(int k){
        if(k < 0 || k >= maxN)
            throw new IndexOutOfBoundsException();
        if(!contains(k))
            throw new NoSuchElementException("Index is not in the priority queue");
        int index = qp[k];
        exch(index, n--);
        swim(index);
        sink(index);
        keys[k] = null;
        qp[k] = -1;
    }

    private boolean greater(int i, int j){
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void exch(int i, int j){
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private void swim(int i){
        while(i > 1 && greater(i/2, i)){
            exch(i, i/2);
            i = i/2;
        }
    }

    private void sink(int i){
        while(2*i <= n){
            int j = 2*i;
            if(j < n && greater(j, j+1))
                j++;
            if(!greater(i, j))
                break;
            exch(i, j);
            i = j;
        }
    }
}
