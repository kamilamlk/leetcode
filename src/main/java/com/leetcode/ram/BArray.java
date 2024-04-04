package com.leetcode.ram;

public class BArray<T> {
    int allocationCapacity = 10;
    Object[] _arr;
    int size;

    BArray() {
        _arr = new Object[allocationCapacity];
    }

    BArray(int size, int capacity){
        _arr = new Object[size];
        this.allocationCapacity = capacity;
    }

    @SuppressWarnings("unchecked")
    T get(int index) {
        return (T)_arr[index];
    }

    private void relocate(int newsize, int index) {
        Object[] tmp = new Object[newsize];

        if (_arr != null)
            for(int i=0; i < _arr.length; i++)
                if (i<index)
                    tmp[i] = _arr[i];
                else
                    tmp[i+1] = _arr[i];
        _arr = tmp;
    }

    void add(int index, T element) {
        if (_arr == null || _arr.length <= index){
            relocate(index+allocationCapacity, index);
        }
        _arr[index] = (Object)element;
        size++;
    }

    void set(int index, T element) {
        _arr[index] = (Object)element;
    }

    int size( ) {
        return _arr.length;
    }

    void remove(int index) {
        if (index >= 0  && index < size) {
            for (int i = index + 1; i < size; i++) {
                _arr[i -1] = _arr[i];
            }
            _arr[size - 1] = null;
            size--;
        }
    }
}
