package Sorting;

import java.util.Random;

/**
 * Created by KUBA on 2016-06-21.
 */
public class QuickSortUpgraded {
    private static final int CUTOFF = 5; // cutoff to insertion sort

    private static void ShuffleArray(Comparable[] array)
    {
        int index;
        Comparable temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    public static void sort(Comparable[] a){
        ShuffleArray(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi){
        if(hi <= lo + CUTOFF){                // CUTOFF TO INSERTION SORT
            insertionSort(a, lo, hi);
            return;
        }
        int lt = lo;
        int i = lo + 1;
        int gt = hi;
        Comparable v = a[lo];
        while(i <= gt){
            int cmp = a[i].compareTo(v);
            if(cmp < 0)
                exch(a, lt++, i++);
            else if(cmp > 0)
                exch(a, i, gt--);
            else
                i++;
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi){
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while(true){
            while(less(a[++i], v))
                if(i == hi)
                    break;
            while(less(v, a[--j]))
                if(j == lo)
                    break;
            if(i >= j)
                break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void insertionSort(Comparable[] a, int lo, int hi){
        for(int i = lo; i <= hi; i++)
            for(int j = i; j > lo && less(a[j], a[j - 1]); j--)
                exch(a, j, j-1);
    }
}
