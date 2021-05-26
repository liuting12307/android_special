package com.liuting.libdatastructure.sort;


//归并排序
public class MergeSort {
    public static void main(String[] args) {
        int[] a = {2, 4, 1, 9, 3, 6, 12, 3, 8};
        printArray(a);
        sort(a);
        printArray(a);
    }


    public static void sort(int[] a) {
        //在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        int[] temp =new int[a.length];
        sort(a,0,a.length-1,temp);
    }

    private static void sort(int[] a, int left, int right,int [] temp) {
       if(left<right){
           int mid=(left+right)/2;//左边归并排序，使得左子序列有序
           sort(a,left,mid,temp);//右边归并排序，使得右子序列有序
           sort(a,mid+1,right,temp);
           merge(a,left,mid,right,temp);//将两个有序子数组合并操作
       }
    }
    private static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i=left;//左序列指针
        int j=mid+1;//右序列指针
        int t=0;//临时数组指针
        while(i<=mid&&j<=right){
            if(arr[i]<=arr[j]){
                temp[t++]=arr[i++];
            }else{
                temp[t++]=arr[j++];
            }
        }
        //将左边剩余元素填充进temp中
        while(i<=mid){
            temp[t++]=arr[i++];
        }
        //将右序列剩余元素填充进temp中
        while(j<=right){
            temp[t++]=arr[j++];
        }
        t=0;
        while(left<=right){
            arr[left++]=temp[t++];
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
