package com.liuting.libdatastructure.xiaohuihui;

/**
 * 作者:admin on 2021/9/8 21:46
 * 邮箱:474211389@qq.com
 * 项目名：AndroidSpecial
 * 包名：com.liuting.libdatastructure.xiaohuihui
 * TODO:
 * 假如给出一个正整数，请找出这个正整数所有数字全排列的下一个数
 * 比如如果输入12345，返回12354
 * 思路
 * 1，从后向前查看逆序区域，找到逆序区域的前一位，也就是数字置换的边界
 * 2，让逆序区域的前一位和逆序区域中大于它的最小数字交换位置
 * 3，把原来的逆序区域转为顺序状态
 */
class FindNearestNumber {



    public static void main(String[] arg) {
        FindNearestNumber findNearestNumber=new FindNearestNumber();
        int[] arr={1,2,3,4,5};
        for(int i=0;i<10;i++){
            int[] temp=findNearestNumber.findNearestNumber(arr);
            outputNumbers(temp);
            arr=temp;
        }

    }

    public static void outputNumbers(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    FindNearestNumber() {

    }

    public int[] findNearestNumber(int[] arr){
        //从后面向前查看逆序区域，找到逆序区域的前一位，也就是数字置换的边界
        //如果数字置换的边界是0，说明整个数字已经逆序，无法得到更大的相同的数字组成的整数，返回null
        int index=findTransferPoint(arr);
        if(index==0){
            return null;
        }
        int[] result=arr.clone();
        exchangeHead(index,result);
        //把原来的逆序区域转为顺序
        reverse(index,result);
        return result;

    }

    private int findTransferPoint(int[] arr){
        for(int i=arr.length-1;i>0;i--){
            if(arr[i]>arr[i-1]){
                return i;
            }
        }
        return 0;
    }

    private void exchangeHead(int index,int[] arr){
       int head=arr[index-1];
       for(int i=arr.length-1;i>0;i--){
           if(head<arr[i]){
               arr[index-1]=arr[i];
               arr[i]=head;
               break;
           }
       }
    }

    private void reverse(int index,int[] arr){
        int i=index;
        int j=arr.length-1;
        while(i<j){
            int temp=arr[i];
             arr[i]=arr[j];
             arr[j]=temp;
             i++;
             j--;
        }
    }


}
