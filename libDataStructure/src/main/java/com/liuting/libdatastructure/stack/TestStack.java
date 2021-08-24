package com.liuting.libdatastructure.stack;

public class TestStack {
    public static void main(String[] args){
        ArrayStack arrayStack=new ArrayStack(5);
        arrayStack.push("2");
        arrayStack.push("1");
        arrayStack.push("5");
        arrayStack.push("6");
        arrayStack.push("7");
        arrayStack.push("7");
        arrayStack.printStack();
        System.out.println("出栈:"+5/2);
        System.out.println("出栈:"+arrayStack.pop());
        System.out.println("出栈:"+arrayStack.pop());
        System.out.println("出栈:"+arrayStack.pop());
        arrayStack.push("9");
        arrayStack.printStack();

    }




}
