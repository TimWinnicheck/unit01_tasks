package com.win.java.se;


import java.util.Arrays;

public class IntArrayList {
    private int[] data;
    private int size;

    public IntArrayList(int[] data)
    {
        this.data = Arrays.copyOf(data, data.length);
        size = data.length;
    }

    public IntArrayList()
    {
        data = new int[10];
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public void sort(){
        mergeSort(data, 0, getSize(), new int[getSize()]);
    }

    /**
     * Task 2 - Binary Search
     *
     * Expects collection to be sorted.
     *
     * @param value value to find in collection
     * @return index of the value or -indexToInsert - 1
     */

    public int binarySearch(int value) {
        return binarySearchRec(data,0,getSize() - 1,value);
    }

    private int binarySearchRec(int[] data, int startInclusive, int endExclusive, int value) {

        final int length = endExclusive - startInclusive;
        final int mid = startInclusive + length/2;

        if (startInclusive > endExclusive)
        {
            return -startInclusive - 1;
        }

        if ((length == 0) && (data[mid] > value))
        {
            return -startInclusive - 1;
        }
        else if ((length == 0) && (data[mid] < value))
        {
            return -(startInclusive + 1) -1;
        }

        if (data[mid] == value)
        {
            return mid;
        }
        else if (data[mid] < value)
        {
            return binarySearchRec(data,mid+1,endExclusive,value);
        }
        else
        {
            return binarySearchRec(data,startInclusive,mid-1,value);
        }
    }

    private static void mergeSort(int[] data, int startInclusive, int endExclusive, int[] free) {
        final int length = endExclusive - startInclusive;
        if (length <= 1){
            return;
        }

        final int mid = startInclusive + length/2;

        mergeSort(data, startInclusive,mid, free);
        mergeSort(data, mid, endExclusive, free);

        merger(data, startInclusive, mid, endExclusive, free);

    }

    private static void merger(int[] data, int startInclusive, int mid, int endExclusive, int[] free) {
        System.arraycopy(data,startInclusive,free,startInclusive,endExclusive - startInclusive);

        int i = startInclusive;
        int j = mid;
        for (int k = startInclusive; k < endExclusive; k++) {
            if (i >= mid) data[k] = free[j++];
            else if (j >= endExclusive) data[k] = free[i++];
            else if (free[i] < free[j]) data[k] = free[i++];
            else data[k] = free[j++];
        }
    }

    /**
     * Task 3 - Ascending Merge Sort
     * @param data array of data to sort
     * @param startInclusive starting index
     * @param endExclusive ending index
     * @param free array of free space needed for merger()
     */

    public static void mergeSortAscending(int[] data, int startInclusive, int endExclusive, int[] free)
    {
        final int length = endExclusive - startInclusive;
        if (length <= 1){
            return;
        }

        int size = 1;
        int pos;

        while (size < endExclusive)
        {
            pos = startInclusive;
            while (pos < endExclusive)
            {
                if ((pos + size)>endExclusive)
                {
                    merger(data,pos,pos+(endExclusive-pos)/2,endExclusive,free);
                    pos = pos + size * 2;
                }
                else {
                    if ((pos + size * 2) > endExclusive) {
                        merger(data, pos, pos + size, endExclusive, free);
                        pos = pos + size * 2;
                    }
                    else {
                        merger(data, pos, pos + size, pos + size * 2, free);
                        pos = pos + size * 2;
                    }
                }
            }
            size = size*2;
        }
    }

}
