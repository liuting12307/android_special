package com.liuting.libdatastructure.sort;

import java.util.Random;

/**
 * 计数排序其实是桶排序的一种特殊情况。当要排序的 n 个数据，所处的范围并不大的时候，比如最大值是k，我们就可以把数据划分成 k个桶。每个桶内的数据值都是相同的，省掉了桶内排序的时间。
 */
public class CountingSort {
    public static void main(String[] args) {

        //模拟高考,100万考生，高考总分750
        int[] a=new int[1000*10000];
        Random random=new Random();
        for(int i=0;i<a.length;i++){
            int value=random.nextInt(750);
            a[i]=value;
        }
        System.out.println("-------排序前---------");
        long start=System.currentTimeMillis();
        printArray(a);
        countingSort(a,a.length);
        System.out.println("-------排序后---------");
        printArray(a);
        long end=System.currentTimeMillis();
        System.out.println("排序时间="+(end-start));
    }

    // 计数排序，a是数组，n是数组大小。假设数组中存储的都是非负整数。
    public static void countingSort(int[] a, int n) {
        if (n <= 1)
            return;
        //计算数组最大值
        int max = a[0];
        for (int i = 1; i < n; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        // 申请一个计数数组bucket，下标大小[0,max],存储每个数据出现的次数
        int[] bucket = new int[max + 1];
        for (int i = 0; i <= max; i++) {
            bucket[i] = 0;
        }

        //将源数组中的数据放到相对应的桶的下标中
        for (int i = 0; i < n; i++) {
            bucket[a[i]]++;
        }

        //将桶中数组依次累加
        for (int i = 1; i <= max; i++) {
            bucket[i] = bucket[i - 1] + bucket[i];
        }

        //临时数组，存储排序后的数据
        int[] temp = new int[n];

        //遍历源数组
        for (int i = 0; i < n; i++) {
            int index = bucket[a[i]]-1;
            temp[index] = a[i];
            bucket[a[i]]--;
        }

        //将结果拷贝给源数组
        for(int i=0;i<n;i++){
            a[i]=temp[i];
        }

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
