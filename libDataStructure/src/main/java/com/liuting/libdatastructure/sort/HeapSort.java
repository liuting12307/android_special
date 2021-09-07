package com.liuting.libdatastructure.sort;

import java.util.Random;

//堆排序  演示 从小到大排序，构建最大堆
public class HeapSort {
    public static void main(String[] args) {
        //模拟高考,100万考生，高考总分750
        int[] a = new int[20];
        Random random = new Random();
        for (int i = 0; i < a.length; i++) {
            int value = random.nextInt(100);
            a[i] = value;
        }
        System.out.println("-------排序前---------");
        long start = System.currentTimeMillis();
        printArray(a);
        heapSort(a);
        System.out.println("-------排序后---------");
        printArray(a);
        long end = System.currentTimeMillis();
        System.out.println("排序时间=" + (end - start));
    }

    public static void heapSort(int[] arr){
        //把无序数组构建最大堆
        for(int i= (arr.length-2)/2;i>=0;i--){
            downAdjust(i,arr.length,arr);
        }
        for(int i=arr.length-1;i>=1;i--){
            swap(arr,0,i);
            //下沉调整最大堆
            downAdjust(0,i,arr);
        }
    }

    public static void downAdjust(int parentIndex,int length,int[]arr){
        int temp=arr[parentIndex];
        int childIndex=2*parentIndex+1;
        while(childIndex<length){
            //如果有右孩子，且右孩子的值大于左孩子的值，则定位到右孩子
            if(childIndex+1<length&&arr[childIndex+1]>arr[childIndex]){
                childIndex++;
            }
            if(temp>=arr[childIndex]){
                break;
            }
            //不需要真正交换，单向赋值就可以
            arr[parentIndex]=arr[childIndex];
            parentIndex=childIndex;
            childIndex=2*childIndex+1;
        }
        arr[parentIndex]=temp;
    }

    /**
     * 交换元素通用处理
     *
     * @param arr
     * @param a
     * @param b
     */
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void printArray(int[] a) {
        if (a == null) {
            return;
        }
        if (a.length == 0) {
            System.out.println("数组为空！");
        }
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < a.length; i++) {
            if (i == a.length - 1) {
                stringBuilder.append(a[i]).append("]");
            } else {
                stringBuilder.append(a[i]).append(",");
            }
        }
        System.out.println(stringBuilder.toString());
    }
}
