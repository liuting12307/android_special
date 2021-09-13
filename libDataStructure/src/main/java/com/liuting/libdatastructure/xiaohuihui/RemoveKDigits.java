package com.liuting.libdatastructure.xiaohuihui;

import java.util.Arrays;
import java.util.Stack;

/**
 * 作者:admin on 2021/9/8 21:46
 * 邮箱:474211389@qq.com
 * 项目名：AndroidSpecial
 * 包名：com.liuting.libdatastructure.xiaohuihui
 * TODO:
 * 假如给出一个正整数，从该整数中去掉k个数字，要求剩下的数字形成的新整数尽可能小
 * 比如如果输入1593212，删去3个数字，最小情况为1212
 * 思路
 * 将问题转换为，如果只删除1个数字，如何让新整数的值最小
 * 应该优先把高位的数字降低，
 * 原整数的所有数字从左到右进行比较，如果发现某一位数字大于它右面的数字，那么在删除该数字后，必然
 * 会使该数位的值降低
 * 这样进行k次，就是删除k个数字后的最小值
 * ，这样每次求得局部最优解，得到全局最优解的思路，即贪心算法
 */
class RemoveKDigits {



    public static void main(String[] arg) {
        RemoveKDigits rkd=new RemoveKDigits();
        System.out.println("结果为---"+rkd.removeKDigits(1593212,1));

    }

    RemoveKDigits() {

    }

    public int removeKDigits(int num,int k){

        char [] nums=(num+"").toCharArray();
        System.out.println("原数打为数组为---"+ Arrays.toString(nums));
        int newLength=nums.length-k;
        if(newLength<=0)
            return 0;
        Stack<Character> stack=new Stack<>();
        for(int i=0;i<nums.length;i++){
            char current=nums[i];
            System.out.println("当前遍历元素---"+ current);
            while(stack.size()>0&&stack.peek()>current&&k>0){
                stack.pop();
                k--;
            }
            if('0'==current&&stack.size()==0){
                newLength--;
                if(newLength<=0){
                    return 0;
                }
                continue;
            }
            stack.push(current);
        }
        char[] newNum=new char[newLength];
        for(int i=0;i<newLength;i++){
            newNum[i]=stack.elementAt(i);
        }
        return Integer.parseInt(new String(newNum));

    }



}
