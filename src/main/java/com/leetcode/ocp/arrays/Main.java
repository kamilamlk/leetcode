package com.leetcode.ocp.arrays;

public class Main {
    public static void main(String[] args) {
        SafeStack[] safeStacks = new SafeStack[10];
        Stack[] stacks = safeStacks;
        ISafeStack[] iSafeStacks = safeStacks;
        IStack[] iStacks = iSafeStacks;
        Object[] objects = iStacks;
        Object object = iStacks[0];
        System.out.println(iStacks.getClass());
        System.out.println(new int[5].getClass());
        iStacks[0] = new Stack();

        for (IStack iStack : iStacks) {
            System.out.println(iStack.getClass());
        }

        int x = 10;
        Integer y = x;
        Object obj = x;
        Integer boxed = 10;
        Number num = boxed;
        obj = num;
    }

    public void doSomething(Long l) {
        System.out.println("Long");
    }

    public void doSomething(Double d) {
        System.out.println("Double");
    }
}
