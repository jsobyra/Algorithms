package DataStructures;

import java.util.Iterator;

/**
 * Created by KUBA on 2016-06-19.
 */
public class LinkedListSteque<Item> implements Iterable<Item>{

    private Node first;
    private Node last;
    private int N;

    private class Node{
        Item item;
        Node next;
    }

    public LinkedListSteque(){
        N = 0;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void enqueue(Item item){
        Node oldlast = last;
        last = new Node();
        last.next = null;
        last.item = item;
        if(isEmpty())
            first = last;
        else
            oldlast.next = last;
        N++;
    }


    public void push(Item item){
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        if(first.next == null)
            last = first;
        N++;
    }

    public Item pop(){
        Item item = first.item;
        first = first.next;
        N--;
        return item;
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
