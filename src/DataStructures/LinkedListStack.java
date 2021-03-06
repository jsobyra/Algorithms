package DataStructures;

import java.util.Iterator;

/**
 * Created by KUBA on 2016-06-19.
 */
public class LinkedListStack<Item> implements Iterable<Item>{

    private Node first;
    private int N;

    public LinkedListStack(){
        N = 0;
    }

    private class Node{
        Item item;
        Node next;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return N;
    }

    public void push(Item item){
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
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
