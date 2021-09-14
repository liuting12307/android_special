package com.liuting.libdatastructure.xiaohuihui;

import java.util.Arrays;
import java.util.Stack;

/**
 * 作者:admin on 2021/9/8 21:46
 * 邮箱:474211389@qq.com
 * 项目名：AndroidSpecial
 * 包名：com.liuting.libdatastructure.xiaohuihui
 * TODO:
 * 给一个有序数组nums，请你原地删除重复出现的元素，使每个元素最多出现两次，返回删除输出后数组的新长度
 * o（1）
 * 思路
 */
class RemoveDuplicates {



    public static void main(String[] arg) {
        RemoveDuplicates rkd=new RemoveDuplicates();
        int[] arr={1,1,1,2,2,3};
        System.out.println("结果为---"+rkd.removeDuplicates(arr));

    }

    RemoveDuplicates() {

    }

    public int removeDuplicates(int [] arr){

        int left1=1;
        int left2=0;
        int last=arr[0];
        for(int i=0;i<arr.length-1;i++){

            if(arr[i]==last){
                left1++;
            }else{
                left1++;
                left2++;
            }
            if(left1-left2>1){
                int temp=left1;
                while(temp<arr.length-1){
                    arr[temp]=arr[temp+1];
                    temp++;
                }
                if(arr[left2]!=arr[left1])
                    left2=left1;
                else{
                    left1--;
                }
            }
            last=arr[i];
        }
        System.out.print("[");
        for(int k=0;k<=left2;k++){
            if(k==0)
                System.out.print(arr[k]);
            else{
                System.out.print(","+arr[k]);
            }
        }
        System.out.print("]");
        System.out.println();
        return left2+1;
    }


}
