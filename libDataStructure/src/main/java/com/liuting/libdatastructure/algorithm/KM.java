package com.liuting.libdatastructure.algorithm;

/**
 * 作者:admin on 2021/7/29 20:49
 * 邮箱:474211389@qq.com
 * 项目名：AndroidSpecial
 * 包名：com.liuting.libdatastructure.algorithm
 * TODO:KM算法  查找功能
 *
 * keyStr 要查找的关键字符串
 * mainStr 主串
 */
public class KM {

    public static final int SIZE=256;//待查询的都是能表示为ASCII的字符


    //生成keyStr的散列表，提高查找字符的效率
    public static void generateBC(char[] keyStr,int m,int[] bc){
        for(int i=0;i<SIZE;i++){
            bc[i]=-1;//初始化bc
        }
        for(int i=0;i<m;i++){
            int ascii=keyStr[i];//计算当前字符的ascii值
            bc[ascii]=i;
        }
    }

    public static int bm(char[] mainStr,int n,char[] keyStr,int m){
        int[] bc=new int[SIZE];
        generateBC(keyStr,m,bc);
        int i=0;//i表示主串与关键串对齐的第一字符的下标
        while(i<=n-m){
            int j;//遍历关键串
            for( j=m-1;j>=0;j--){
                if(mainStr[i+j]!=keyStr[j]){
                    break;//换字符在keyStr中的下标为j
                }
            }
            if(j<0){
                return i;//匹配成功，返回主串与关键串第一对齐的下标
            }
            i=i+j-bc[mainStr[i+j]];//找出换字符在关键串中最靠后的一个下标，没有就是-1
        }
        return -1;
    }

}
