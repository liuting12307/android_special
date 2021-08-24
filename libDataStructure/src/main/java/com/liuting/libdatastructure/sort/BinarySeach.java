package com.liuting.libdatastructure.sort;

import java.util.HashMap;

public class BinarySeach {
    public static void main(String[] args) {

        int[] a = {1, 3, 5, 9, 12, 15, 15, 15, 22, 36, 36, 42};

        int result = bsearch3(a, a.length, 6);
        if (result == -1) {
            System.out.println("未找到数据");
        } else {
            System.out.println("找到数据，下标为index=" + result);
        }

        ListNode listNode1=new ListNode(2);
        ListNode listNode2=new ListNode(4);
        ListNode listNode3=new ListNode(3);
        ListNode listNode5=new ListNode(5);
        ListNode listNode6=new ListNode(6);
        ListNode listNode4=new ListNode(4);
        listNode1.next=listNode2;
        listNode2.next=listNode3;
        listNode5.next=listNode6;
        listNode6.next=listNode4;
        addTwoNumbers(listNode1,listNode5);
    }

    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0,j=nums.length-1;i<j;i++,j--){
            map.put(nums[i],i);
            map.put(nums[j],j);
            if(map.containsKey(target-nums[i])){
                 int[] a=new int[2];
                 a[0]=i;
                 a[1]=map.get(target-nums[i]);
                 return a;
            }
            if(map.containsKey(target-nums[j])){
                int[] b=new int[2];
                b[0]=j;
                b[1]=map.get(target-nums[j]);
                return b;
            }
        }
        return null;
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

    public static  ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int num1=0;
        int num2=0;
        StringBuilder s1=new StringBuilder();
        StringBuilder s2=new StringBuilder();
        StringBuilder s=new StringBuilder();
        ListNode node1=l1;
        while(node1!=null){
            s1.insert(0,node1.val);
            node1=node1.next;
        }
        num1=Integer.parseInt(s1.toString());
        System.out.println("num1="+num1);
        ListNode node2=l2;
        while(node2!=null){
            s2.insert(0,node2.val);
            node2=node2.next;
        }
        num2=Integer.parseInt(s2.toString());
        System.out.println("num2="+num2);
        int res=num1+num2;
        System.out.println("res="+res);
        s.append(res);
        ListNode result=new ListNode(-1);
        for(int i=s.length()-1;i>=0;i--){
            ListNode pre=result.next;
            ListNode next=new ListNode();
            result.val=Integer.parseInt(s.charAt(i)+"");
            next.next=pre;
            result.next=next;
        }
        return result;
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1=nums1.length;
        int length2=nums2.length;
        int totalLength=length1+length2;
        if(totalLength%2==0){
            int midIndex = totalLength / 2;
            return getKeyEle(nums1,nums2,midIndex+1);
        }else{
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            return (getKeyEle(nums1,nums2,midIndex1+1)+getKeyEle(nums1,nums2,midIndex2+1))/2;
        }
    }

    public double getKeyEle(int[] nums1, int[] nums2,int k){
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */

        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;
        int kthElement = 0;

        int half=k/2;
        int newIndex1=Math.min(index1+half,length1)-1;
        int newIndex2=Math.min(index2+half,length2)-1;
        int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
        if(pivot1>=pivot2){

        }
        return kthElement;
    }

}



   class ListNode {
     int val;
      ListNode next;
      ListNode() {}
     ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
