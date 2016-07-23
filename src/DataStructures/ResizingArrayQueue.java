package DataStructures;

import java.util.Iterator;

/**
 * Created by KUBA on 2016-06-20.
 */
public class ResizingArrayQueue<Item> implements Iterable<Item>{
    private Item[] a;
    private int N;
    private int first;
    private int last;

    public ResizingArrayQueue(){
        a = (Item[]) new Object[2];
        N = 0;
        first = 0;
        last = 0;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    private void resize(int max){
        assert max >= N;
        Item[] temp = (Item[]) new Object[max];
        for(int i = 0; i < N; i++){
            temp[i] = a[(first+i) % a.length];
        }
        a = temp;
        first = 0;
        last = N;
    }

    public void enqueue(Item item){
        if(N == a.length)
            resize(2*a.length);
        a[last] = item;
        last++;
        if(last == a.length)
            last = 0;
        N++;
    }

    public Item dequeue(){
        Item item = a[first];
        a[first] = null;
        N--;
        first++;
        if(first == a.length)
            first = 0;
        if(N > 0 && N == a.length/4)
            resize(a.length/2);

        return item;
    }

    public Iterator<Item> iterator(){
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item>{
        private int i = 0;
        public boolean hasNext(){
            return i < N;
        }
        public void remove(){}
        public Item next(){
            Item item = a[(first+i) % a.length];
            i++;
            return item;
        }
    }


















}
