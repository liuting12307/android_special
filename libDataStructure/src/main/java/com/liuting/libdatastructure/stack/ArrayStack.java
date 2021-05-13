package com.liuting.libdatastructure.stack;

public class ArrayStack {
    private String[] items;
    private int count;//栈中元素个数
    private int length;//栈大小

   public ArrayStack(int length){
       items=new String[length];
       this.length=length;
       count=0;
   }

   public boolean push(String item){
       if(count==length){
           System.out.println("栈中元素已满，入栈失败");
           return false;
       }
       System.out.println("入栈："+item);
       items[count]=item;
       count++;
       return true;
   }

   public String pop(){
       if(count==0){
           System.out.println("栈中已经没有元素，弹栈失败");
           return null;
       }
       String temp=items[count-1];
       count--;

       return temp;
   }

   public void printStack(){
       StringBuilder stringBuilder=new StringBuilder();
       stringBuilder.append("[");
       for(int i=0;i<count;i++){
           if(i==count-1){
               stringBuilder.append(items[i]);
           }else{
               stringBuilder.append(items[i]).append(",");
           }
       }
       stringBuilder.append("]");
       System.out.println(stringBuilder.toString());
   }
}
