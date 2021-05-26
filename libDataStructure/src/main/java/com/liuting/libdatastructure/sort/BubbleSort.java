package com.liuting.libdatastructure.sort;

public class BubbleSort {
    public static void main(String[] args) {
        int[] a = {2, 4, 1, 9, 3, 6, 12, 3, 8};
        printArray(a);
        bubble(a, a.length);
        printArray(a);
    }

    public static void bubble(int[] a, int n) {
        if (n <= 1) {
            return;
        }
        for (int i = 0; i < n; i++) {
            boolean flag = false;//是否有数据交换的flag
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
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
