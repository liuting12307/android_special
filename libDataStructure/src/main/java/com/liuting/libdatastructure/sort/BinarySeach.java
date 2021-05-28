package com.liuting.libdatastructure.sort;

public class BinarySeach {
    public static void main(String[] args) {

        int[] a = {1, 3, 5, 9, 12, 15, 15, 15, 22, 36, 36, 42};

        int result = bsearch3(a, a.length, 6);
        if (result == -1) {
            System.out.println("未找到数据");
        } else {
            System.out.println("找到数据，下标为index=" + result);
        }
    }


    //假定有序集合不存在重复的数
    public static int bsearch(int[] a, int n, int value) {
        int low = 0;
        int high = n-1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] == value) {
                return mid;
            } else if (a[mid] > value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    //查找第一个值为value的数
    public static int bsearch1(int[] a, int n, int value) {
        int low = 0;
        int high = n-1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] < value) {
                low = mid + 1;
            } else if (a[mid] > value) {
                high = mid - 1;
            } else {
                if (mid == 0 || a[mid - 1] != value) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    //查找最后一个值为value的数
    public static int bsearch2(int[] a, int n, int value) {
        int low = 0;
        int high = n-1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] < value) {
                low = mid + 1;
            } else if (a[mid] > value) {
                high = mid - 1;
            } else {
                if (mid == n-1 || a[mid + 1] != value) {
                    return mid;
                } else {
                    low = mid +1;
                }
            }
        }
        return -1;
    }

    //查找第一个大于或等于value的数
    public static int bsearch3(int[] a, int n, int value) {
        int low = 0;
        int high = n-1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] <value) {
                low = mid + 1;
            }else {
                if (mid == 0 || a[mid - 1] <= value) {
                    return mid;
                } else {
                    high = mid -1;
                }
            }
        }
        return -1;
    }
}
