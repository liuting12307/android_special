package com.liuting.libdatastructure.sort;

//快速排序
public class QuickSort {
    public static void main(String[] args) {
        int[] a = {2, 4, 1, 9, 3, 6, 14,18,26,45,12, 3, 8};
        printArray(a);
        quickSort(a,0,a.length-1);
        printArray(a);
    }

    public static void quickSort(int[] arr,int left,int right){
        if(left<right){
            //获取枢纽值，并将其放在当前待处理序列末尾
            dealPivot(arr,left,right);
            //枢纽值被放在序列末尾
            int pivot = right - 1;
            //左指针
            int i = left;
            //右指针
            int j = right - 1;
            while(true){
                while(arr[++i]<arr[pivot]){

                }
                while(j>left&&arr[--j]>arr[pivot]){

                }
                if(i<j){
                    swap(arr,i,j);
                }else{
                    break;
                }

            }
            if(i<right-1){
                swap(arr,i,right-1);
            }
            quickSort(arr,left,i-1);
            quickSort(arr,i+1,right);

        }
    }

    /**
     * 处理枢纽值
     *
     * @param arr
     * @param left
     * @param right
     */
    public static void dealPivot(int[] arr, int left, int right) {
        int mid = (left + right) / 2;
        if (arr[left] > arr[mid]) {
            swap(arr, left, mid);
        }
        if (arr[left] > arr[right]) {
            swap(arr, left, right);
        }
        if (arr[right] < arr[mid]) {
            swap(arr, right, mid);
        }
        swap(arr, right - 1, mid);
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
