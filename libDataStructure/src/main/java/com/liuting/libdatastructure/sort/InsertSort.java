package com.liuting.libdatastructure.sort;

public class InsertSort {
    public static void main(String[] args){
        int[] a = {2, 4, 1, 9, 3, 6, 12, 3, 8};
        printArray(a);
        insert(a,a.length);
        printArray(a);
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
