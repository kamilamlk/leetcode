package com.leetcode.ram;

public class IArray<T>  {
    BArray<BArray<T>> _arr;
    int _delta;

    IArray(int size) {
        _delta = size;
        _arr = new BArray<>(1, _delta);
        _arr.add(0, new BArray<>(_delta, 0));
    }

    T get(int index) {
        int i = index / _delta;
        int j = index % _delta;
        return _arr.get(i).get(j);
    }

    void relocate() {
        _arr.add(_arr.size, new BArray<>(_delta, 0));
    }

    void add(int index, T elemnt) {
        if (_arr.size() * _delta <= index) {
            relocate();
        }
        int i = index / _delta;
        int j = index % _delta;
        System.out.println("i: " + i + " index: " + index);
        _arr.get(i).add(j, elemnt);
    }

    void insert(int index, T element) {

    }
}
