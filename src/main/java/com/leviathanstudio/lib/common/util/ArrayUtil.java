package com.leviathanstudio.lib.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ArrayUtil
{
    /**
     * @param <T>
     *            type of the array
     * @param array
     *            the array
     * @param element
     *            the element to find
     * @return true if the array contain the element
     */
    public static <T> boolean contain(T[] array, T element)
    {
        boolean find = false;
        for (int i = 0; i < array.length && !find; i++)
        {
            find = array[i].equals(element);
        }
        return find;
    }

    /**
     * Convert an array into a list
     * 
     * @param <T>
     *            type of the array
     * @param array
     *            the array to convert
     * @return the list representing the array
     */
    public static <T> List<T> toList(T[] array)
    {
        List<T> l = new ArrayList<>();
        if (array.length > 0)
        {
            for (int i = 0; i < array.length; i++)
                l.add(array[i]);
            return l;
        }
        return null;
    }

    /**
     * Convert a list into an array
     * 
     * @param <T>
     *            type of the array
     * @param list
     *            the list to convert
     * @return the array representing the list
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] toArray(List<T> list)
    {
        return (T[]) list.toArray(new Object[list.size()]);
    }

    /**
     * Convert a set into an array
     * 
     * @param <T>
     *            type of the array
     * @param set
     *            the set to convert
     * @return the array representing the list
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] toArray(Set<T> set)
    {
        return (T[]) set.toArray(new Object[set.size()]);
    }
}
