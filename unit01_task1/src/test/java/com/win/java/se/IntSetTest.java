package com.win.java.se;

import org.junit.Test;

import static org.junit.Assert.*;

public class IntSetTest {
    @Test
    public void subset() throws Exception {

        IntSet one = new IntSet();
        IntSet two = new IntSet();

        assertTrue(one.subset(two));

        one.add(0);
        one.add(20);
        one.add(12345);
        one.add(80);

        two.add(20);
        two.add(12345);

        assertTrue(one.subset(two));

        two.add(666);

        assertFalse(one.subset(two));

        one.add(666);
        one.add(Integer.MAX_VALUE);
        two.add(Integer.MAX_VALUE);

        assertTrue(one.subset(two));

        one.remove(Integer.MAX_VALUE);

        assertFalse(one.subset(two));

        two = new IntSet();

        assertTrue(one.subset(two));


    }

    @Test
    public void difference() throws Exception {

        IntSet one = new IntSet();
        IntSet two = new IntSet();
        IntSet result = new IntSet();

        one.add(0);
        one.add(12345);
        one.add(Integer.MAX_VALUE);

        two.add(0);
        two.add(Integer.MAX_VALUE);
        two.add(666);

        result = one.difference(two);

        for (int i = -1; i < Integer.MAX_VALUE; i++)
        {
            if (i == 666 || i == 12345)
            {
                assertTrue(result.contains(i));
            }
            else
            {
                assertFalse("at "+ i,result.contains(i));
            }
        }

    }

    @Test
    public void intersect() throws Exception {

        IntSet one = new IntSet();
        IntSet two = new IntSet();
        IntSet result = new IntSet();

        one.add(0);
        one.add(12345);
        one.add(Integer.MAX_VALUE);

        two.add(0);
        two.add(Integer.MAX_VALUE);
        two.add(666);

        result = one.intersect(two);

        for (int i = -1; i < Integer.MAX_VALUE; i++)
        {
            if (i == 0 || i == Integer.MAX_VALUE)
            {
                assertTrue(result.contains(i));
            }
            else
            {
                assertFalse(result.contains(i));
            }
        }

    }

    @Test
    public void union() throws Exception {
IntSet one = new IntSet();
IntSet two = new IntSet();
one.add(0);
one.add(2);
one.add(4);
two.add(1);
two.add(3);
one = one.union(two);
assertTrue(one.contains(0));
        assertTrue(one.contains(1));
        assertTrue(one.contains(2));
        assertTrue(one.contains(3));
        assertTrue(one.contains(4));

        one = new IntSet();
        two = new IntSet();

        one.add(0);
        one.add(9);
        one.add(Integer.MAX_VALUE);
        two.add(1234567);
        IntSet result = one.union(two);

        for (int i = -1; i < Integer.MAX_VALUE; i++)
        {
            if (i == 0 || i == 9 || i == 1234567 || i == Integer.MAX_VALUE)
            {
                assertTrue(result.contains(i));
            }
            else
            {
                assertFalse(result.contains(i));
            }
        }


        result = new IntSet();
        result = two.union(one);

        for (int i = -1; i < Integer.MAX_VALUE; i++)
        {
            if (i == 0 || i == 9 || i == 1234567 || i == Integer.MAX_VALUE)
            {
                assertTrue(result.contains(i));
            }
            else
            {
                assertFalse(result.contains(i));
            }
        }
    }

    @Test
    public void remove() throws Exception {
        IntSet set = new IntSet();
        set.add(0);
        set.add(500);
        set.add(1234567);
        set.add(Integer.MAX_VALUE);

        for (int i = -1; i < Integer.MAX_VALUE; i++)
        {
            if (i == 0 || i == 500 || i == 1234567 || i == Integer.MAX_VALUE)
            {
                assertTrue(set.contains(i));
            }
            else
            {
                assertFalse(set.contains(i));
            }
        }

        set.remove(0);
        set.remove(500);
        set.remove(500);
        set.add(500);
        set.remove(500);
        set.remove(0);
        set.remove(1234567);
        set.remove(Integer.MAX_VALUE);

        for (int i = -1; i<Integer.MAX_VALUE; i++)
        {
            assertFalse(set.contains(i));
        }

    }

    @Test
    public void contains() throws Exception {
        IntSet set = new IntSet();
        for (int i = -1; i < Integer.MAX_VALUE; i++)
        {
            assertFalse(set.contains(i));
        }


        set.add(13);
        set.add(10000);
        set.add(666);
        set.add(666);
        set.add(1445531246);

        for (int i = -1; i < Integer.MAX_VALUE; i++)
        {
            if (i == 13 || i == 10000 || i == 666 || i == 1445531246)
            {
                assertTrue(set.contains(i));
            }
            else
            {
                assertFalse(set.contains(i));
            }
        }

    }

    @Test
    public void add() throws Exception {
        IntSet set = new IntSet();


    set.add(20);
        assertTrue(set.contains(20));

        set.add(-1);
        assertFalse(set.contains(-1));

        set.add(Integer.MIN_VALUE);
        assertFalse(set.contains(Integer.MIN_VALUE));

    set.add(64);
    assertTrue(set.contains(64));

    set.remove(64);
    set.add(64);
    set.add(1000);
    assertTrue(set.contains(1000));

    set.add(10000);
    assertTrue(set.contains(10000));

    assertTrue(set.contains(1000));

    set.add(Integer.MAX_VALUE);
    assertTrue(set.contains(Integer.MAX_VALUE));

    assertTrue(set.contains(1000));
    assertTrue(set.contains(64));


    }

}