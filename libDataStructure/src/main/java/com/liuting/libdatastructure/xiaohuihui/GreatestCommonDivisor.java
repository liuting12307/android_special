package com.liuting.libdatastructure.xiaohuihui;

/**
 * 作者:admin on 2021/9/8 21:46
 * 邮箱:474211389@qq.com
 * 项目名：AndroidSpecial
 * 包名：com.liuting.libdatastructure.xiaohuihui
 * TODO:
 * 最大公约数
 * 更相减损术
 *   两个正整数a和b，a>b,他们的最大公约数等于a-b的差值c和较小数b的最大公约数
 *
 */
class GreatestCommonDivisor {

    public static void main(String[] arg){
        System.out.println("最大公约数为"+getGCD(100,24));
    }
    public static int getGCD(int a,int b){
        if(a==b){
            return a;
        }else if((a&1)==0 &&(b&1)==0){
            return getGCD(a>>1,b>>1)<<1;
        }else if((a&1)==0 &&(b&1)!=0){
            return getGCD(a>>1,b);
        }else if((a&1)!=0 &&(b&1)==0){
            return getGCD(a,b>>1);
        }else{
            int big=Math.max(a,b);
            int small=Math.min(a,b);
            return getGCD(big-small,small);
        }
    }
}
