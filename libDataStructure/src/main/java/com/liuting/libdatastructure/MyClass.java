package com.liuting.libdatastructure;

import com.liuting.libdatastructure.mylinklist.MyLink;

public class MyClass {
    public static void main(String[] args){
        testMyLink();

    }

    public static void testMyLink(){
        MyLink<Integer> myLink=new MyLink<>();
        myLink.printLink();
        myLink.addNode(1);
        myLink.addNode(2);
        myLink.addNode(3);
        myLink.addNode(4);
        myLink.addNode(5);
        myLink.addNode(5);
        myLink.addNode(6);
        myLink.printLink();
    }
}