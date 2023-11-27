package com.leetcode.frequent.elemts;

public class BucketSort {
    /**
     * bucketSort(a[], n)
     * 1. Create 'n' empty buckets
     * 2. Do for each array element a[i]
     *        2.1. Put array elements into buckets, i.e. insert a[i] into bucket[n*a[i]]
     * 3. Sort the elements of individual buckets by using the insertion sort.
     * 4. At last, gather or concatenate the sorted buckets.
     * End bucketSort
     * <p>
     * Algorithm
     * <p>
     * Bucket Sort(A[])
     * 1.  Let B[0....n-1] be a new array
     * 2.  n=length[A]
     * 3.  for i=0 to n-1
     * 4.  make B[i] an empty list
     * 5.  for i=1 to n
     * 6.  do insert A[i] into list B[n a[i]]
     * 7.  for i=0 to n-1
     * 8.  do sort list B[i] with insertion-sort
     * 9.  Concatenate lists B[0], B[1],........, B[n-1] together in order
     * End
     */
    public void sort(int[] array) {
        int n = array.length;
        int max = getMax(array);
        int[] buckets = new int[max + 1];

        for (int i : array) {
            buckets[i]++;
        }

        for (int i = 0, j = 0; i < max; i++) {
            while (buckets[i] > 0) {
                array[j++] = i;
                buckets[i]--;
            }
        }

    }

    int getMax(int[] a) {
        int n = a.length;
        int max = a[0];
        for (int i = 1; i < n; i++){
            if (a[i] > max) {
                max = a[i];
            }
        }
        return max;
    }

    void bucket(int[] a) // function to implement bucket sort
    {
        int n = a.length;
        int max = getMax(a); //max is the maximum element of array
        int[] bucket = new int[max+1];

        for (int i = 0; i < n; i++) {
            bucket[a[i]]++;
        }

        for (int i = 0, j = 0; i <= max; i++) {
            while (bucket[i] > 0) {
                a[j++] = i;
                bucket[i]--;
            }
        }
    }

    void printArr(int a[]) {
        int i;
        int n = a.length;
        for (i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
    }

    public static void main(String[] args) {
        int a[] = { 90, 40, 5, 15, 30, 9 };
        BucketSort b1 = new BucketSort();
        System.out.print("Before sorting array elements are - \n");
        b1.printArr(a);
        b1.sort(a);
        System.out.print("\nAfter sorting array elements are - \n");
        b1.printArr(a);
    }
}
