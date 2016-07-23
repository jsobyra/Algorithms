package DataStructures;

import java.util.Iterator;

/**
 * Created by KUBA on 2016-06-20.
 */
public class ResizingArrayDeque<Item> implements Iterable<Item>{
    public Item[] a;
    private int N;
    private int first;
    private int last;

    public ResizingArrayDeque(){
        a = (Item[]) new Object[2];
        N = 0;
        first = 0;
        last = 0;
    }

    public int getA(){
        return a.length;
    }

    public Item[] getArray(){
        return a;
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

        for(int i = (max - N)/2, j = 0; i < (max + N)/2; i++, j++)
            temp[i] = a[(first+j) % a.length];
        a = temp;
        first = (max - N)/2;
        last = (max + N)/2;

    }

    public void pushRight(Item item){
        if(N == a.length)
            resize(2*a.length);
        a[last] = item;
        last++;
        if(last == a.length)
            last = 0;
        N++;
    }

    public void pushLeft(Item item){
        if(N == a.length)
            resize(2*a.length);
        if(first == 0)
            first = a.length;
        a[first-1] = item;
        first--;
        N++;
    }

    public Item popLeft(){
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

    public Item popRight(){
        Item item = a[last];
        a[last] = null;
        N--;
        last--;
        if(last == -1)
            last = a.length - 1;
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
