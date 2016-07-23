package DataStructures;

import java.util.Iterator;

/**
 * Created by KUBA on 2016-06-20.
 */
public class LinkedListDeque<Item> implements Iterable<Item>{
    private Node first;
    private Node last;
    private int N;

    public LinkedListDeque(){
        N = 0;
        first = null;
        last = null;
    }

    private class Node{
        Item item;
        Node next;
        Node prev;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void pushLeft(Item item){
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        if(oldfirst != null){
            first.next = oldfirst;
            oldfirst.prev = first;
        }
        if(last == null)
            last = first;
        N++;
    }

    public void pushRight(Item item){
        Node oldlast = last;
        last = new Node();
        last.item = item;
        if(oldlast != null){
            last.prev = oldlast;
            oldlast.next = last;
        }
        if(first == null)
            first = last;
        N++;
    }

    public Item popLeft(){
        Node oldfirst = first;
        first = first.next;
        if(first == null)
            last = null;
        else
            first.prev = null;

        N--;
        return oldfirst.item;
    }

    public Item popRight(){
        Node oldlast = last;
        last = last.prev;
        if(last == null)
            first = null;
        else
            last.next = null;

        N--;
        return oldlast.item;
    }

    public Iterator<Item> iterator(){
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<Item>{
        private Node current = first;

        public boolean hasNext(){
            return current != null;
        }
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove(){}

    }
}
