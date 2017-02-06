package com.win.java.se;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class IntArrayListTest {
    @Test
    public void mergeSortAscending() throws Exception {

        int size = ThreadLocalRandom.current().nextInt(1,1000);
        int[] random = new int[size];
        for (int i = 0;i<random.length;i++)
        {
            random[i] = ThreadLocalRandom.current().nextInt();
        }
        int[] test = new int[size];
        System.arraycopy(random,0,test,0,random.length);
        java.util.Arrays.sort(test);

        int[] free = new int[random.length];

        IntArrayList.mergeSortAscending(random,0,random.length,free);

        assertTrue(Arrays.equals(test,random));
    }

    @Test
    public void binarySearch() throws Exception {

        int[] ints = {10, 20, 30, 40, 50, 60};

        final IntArrayList list = new IntArrayList(ints);

        assertEquals(4,list.binarySearch(50));

        int[] ints2 = {10,20,25, 40, 50, 60, 70,80,120,500};

        final IntArrayList list2 = new IntArrayList(ints2);

        assertEquals(-4,list2.binarySearch(30));
        assertEquals(-1,list2.binarySearch(-5));
        assertEquals(-11,list2.binarySearch(Integer.MAX_VALUE));
        assertEquals(-1,list2.binarySearch(Integer.MIN_VALUE));
        assertEquals(-10,list2.binarySearch(135));

    }

}