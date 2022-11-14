package ru.grabovsky.lists;


import java.util.*;

public class MyArrayList<T> implements MyList<T> {
    private int size = 0;
    private int capacity = 10;
    private Object[] array;

    public MyArrayList(final int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
            array = new Object[capacity];
        } else {
            throw new IllegalArgumentException("Invalid capacity");
        }
    }

    public MyArrayList() {
        array = new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iter();
    }

    @Override
    public boolean add(final Object o) {
        if (size == capacity) {
            capacity = capacity * 2;
            Object[] tempArray = array;
            array = Arrays.copyOf(tempArray, capacity);
        }
        array[size++] = o;
        return true;
    }

    @Override
    public void clear() {
        array = new Object[capacity];
        size = 0;
    }

    @Override
    public T get(final int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    @Override
    public T set(final int index, final T element) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = element;
        return element;
    }

    @Override
    public T remove(final int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        T element = (T) array[index];
        size--;
        System.arraycopy(array,index + 1, array, index, size - index);
        return element;
    }

    @Override
    public void add(final int index, final Object element) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        size++;
        if (size == capacity) {
            capacity = capacity * 2;
            Object[] tempArray = array;
            array = Arrays.copyOf(tempArray, capacity);
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
    }

    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder();
        for(int i = 0; i < size; i++){
            temp.append(array[i]);
            if(i != size - 1){
                temp.append(", ");
            }
        }
        return "MyArrayList{" +
               temp.toString() +
                '}';
    }

    private class Iter implements Iterator<T>{
        int cursor = 0;
        int lastReturned = -1;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public T next() {
            lastReturned++;
            return (T) array[cursor++];
        }
    }
}
