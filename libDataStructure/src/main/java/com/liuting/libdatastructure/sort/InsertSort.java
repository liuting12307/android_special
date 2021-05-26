package com.liuting.libdatastructure.sort;

import java.util.Random;

public class InsertSort {
    public static void main(String[] args){
        //模拟高考,100万考生，高考总分750
        int[] a=new int[1000*1000];
        Random random=new Random();
        for(int i=0;i<a.length;i++){
            int value=random.nextInt(750);
            a[i]=value;
        }
        System.out.println("-------排序前---------");
        long start=System.currentTimeMillis();
        printArray(a);
        insert(a,a.length);
        System.out.println("-------排序后---------");
        printArray(a);
        long end=System.currentTimeMillis();
        System.out.println("排序时间="+(end-start));
    }
    public static void insert(int[] a,int n){

        for(int i=1;i<n;i++){
            int value=a[i];
            int j=i-1;
           for( ;j>=0;--j){
               if(value<a[j]){
                   a[j+1]=a[j];//数据移动
               }else{
                   break;
               }
           }
           a[j+1]=value;
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
