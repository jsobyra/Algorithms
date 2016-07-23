import DataStructures.ResizingArrayDeque;
import Sorting.*;
import UnionFind.WeightedQuickUnionPathCompression;

public class Main {

    public static void main(String[] args) {

        Integer array[] = new Integer[20];

        for(int i = 0; i < 20; i++)
            array[i] = 20-i;

        for(int i = 0; i < 20; i++)
            System.out.print(array[i] + " ");

        System.out.println();
        HeapSort.sort(array);

        for(int i = 0; i < 20; i++)
            System.out.print(array[i] + " ");

    }
}
