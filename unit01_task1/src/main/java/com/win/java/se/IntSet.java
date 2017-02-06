package com.win.java.se;

/**
 * Class IntSet - Set of integers that is represented by an array of longs
 */
public class IntSet {

    /**
     * Data - data that is contained in a set
     */
    private long[] data = new long[1];

    public IntSet() {

    }

    private IntSet(long[] data)
    {
        this.data = data;
    }

    /**
     * Evaluates whether or not set has to be extended, and extends it if necessary, adding additional array space
     *
     * @param value Int value for evaluation. If the current IntSet is incapable of storing this value set extends
     * @return New, extended array, or the untouched array
     */
    private long[] extend (int value)
    {
        if (value + 1 > data.length)
        {
            long[] datanew = new long[value + 1];
            System.arraycopy(data,0,datanew,0,data.length);
            return datanew;
        }
        return data;
    }


    /**
     * Adds a new value to the IntSet
     *
     * @param value Value to add
     */
    public void add(int value)
    {
        if (value < 0)
        {
            return;
        }
        data = extend(value / 64);
        data[value/64] |= 1L << value%64;
    }

    /**
     * Checks whether or not a value is contained in IntSet
     * @param value Value to check
     * @return true if value is contained in an IntSet, false otherwise
     */
    public boolean contains(int value)
    {
        if (value < 0 || (value > (data.length*64 - 1)))
        {
            return false;
        }
        long res = data[value/64] & (1L << value%64);
        return res != 0;
    }

    /**
     * Removes a value from an IntSet
     * @param value Value to remove
     */
    public void remove(int value)
    {
        if (value < 0 || (value > (data.length*64 - 1)))
        {
            return;
        }
        data[value/64] &= ~(1L << value%64);
    }

    /**
     * Creates a union of two IntSets
     * @param other IntSet to create union with
     * @return Unified IntSet
     */
    public IntSet union(IntSet other)
    {
        if (this.data.length < other.data.length)
        {
            this.data = this.extend(other.data.length - 1);
        }
        if (other.data.length < this.data.length)
        {
            other.data = other.extend(this.data.length - 1);
        }
        long[] datanew = new long[this.data.length];
        for (int i=0;(i< this.data.length);i++)
        {
            datanew[i] = this.data[i] | other.data[i];
        }
        return new IntSet(datanew);
    }


    /**
     * Creates an intersection of two IntSets
     *
     * @param other IntSet to  intersect with
     * @return Intersected IntSet
     */
    public IntSet intersect(IntSet other)
    {
        if (this.data.length < other.data.length)
        {
            this.data = this.extend(other.data.length - 1);
        }
        if (other.data.length < this.data.length)
        {
            other.data = other.extend(this.data.length - 1);
        }
        long[] datanew = new long[this.data.length];
        for (int i=0;(i< this.data.length);i++)
        {
            datanew[i] = this.data[i] & other.data[i];
        }
        return new IntSet(datanew);
    }

    /**
     * Creates a difference between two IntSets
     *
     * @param other IntSet to find difference with
     * @return IntSet that contains the difference of the two IntSets
     */
    public IntSet difference(IntSet other)
    {
        if (this.data.length < other.data.length)
        {
            this.data = this.extend(other.data.length - 1);
        }
        if (other.data.length < this.data.length)
        {
            other.data = other.extend(this.data.length - 1);
        }
        long[] datanew = new long[this.data.length];
        for (int i=0;(i< this.data.length);i++)
        {
            datanew[i] = this.data[i] ^ other.data[i];
        }
        return new IntSet(datanew);
    }


    /**
     * Checks whether or not an IntSet is a subset of the IntSet
     *
     * @param other IntSet to check for being a subset
     * @return True if the other IntSet is a subset of a IntSet, false otherwise
     */
    public boolean subset(IntSet other)
    {
        if (this.data.length < other.data.length)
        {
            this.data = this.extend(other.data.length - 1);
        }
        if (other.data.length < this.data.length)
        {
            other.data = other.extend(this.data.length - 1);
        }
        long[] datanew = new long[this.data.length];
        for (int i=0;(i< this.data.length);i++)
        {
            datanew[i] = ~(this.data[i]) & other.data[i];
            if (datanew[i] != 0b0)
            {
                return false;
            }
        }
        return true;
    }
}
