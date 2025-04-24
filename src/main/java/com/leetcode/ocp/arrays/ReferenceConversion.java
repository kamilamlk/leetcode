package com.leetcode.ocp.arrays;

public class ReferenceConversion {
    public static void main(String[] args) {
        Object objRef;
        Stack stackRef;
        SafeStack safeStackRef;
        IStack iStackRef;
        ISafeStack iSafeStackRef;

        safeStackRef = new SafeStack();
        objRef = safeStackRef; // SafeStack -> Object
        stackRef = safeStackRef; // SafeStack -> Stack
        iStackRef = safeStackRef; // SafeStack -> IStack
        iSafeStackRef = safeStackRef; // SafeStack -> ISafeStack

        objRef = iStackRef; // IStack -> Object
        iStackRef = iSafeStackRef; // ISafeStack -> IStack

        Object[] objArray = new Object[10];
        Stack[] stackArray = new Stack[10];
        SafeStack[] safeStackArray = new SafeStack[10];
        ISafeStack[] iSafeStackArray = new ISafeStack[10];
        SafeStack[][] safeStackArray2D = new SafeStack[10][10];
        int[] intArray = new int[10];

        System.out.println(safeStackArray2D.getClass());
        objRef = objArray; // Object[] -> Object
        objRef = stackArray; // Stack[] -> Object
        objRef = safeStackArray; // SafeStack[] -> Object
        objRef = iSafeStackArray; // ISafeStack[] -> Object
        //objArray = intArray;

        sendParams(stackRef, safeStackRef, iStackRef, safeStackArray, iSafeStackArray);
        System.out.println("-----------------");
        sendParams(iSafeStackArray, stackRef, iStackRef, stackArray, safeStackArray);
    }

    private static void sendParams(Object objRefParam,
                                   Stack stackRef,
                                   IStack iStackRef,
                                   Stack[] stackArray,
                                   IStack[] iStackArray) {
        System.out.println(objRefParam.getClass());
        System.out.println(stackRef.getClass());
        System.out.println(iStackRef.getClass());
        System.out.println(stackArray.getClass());
        System.out.println(iStackArray.getClass());
    }
}
