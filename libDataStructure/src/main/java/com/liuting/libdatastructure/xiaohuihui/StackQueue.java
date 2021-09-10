package com.liuting.libdatastructure.xiaohuihui;

import java.util.Stack;

/**
 * 作者:admin on 2021/9/8 21:46
 * 邮箱:474211389@qq.com
 * 项目名：AndroidSpecial
 * 包名：com.liuting.libdatastructure.xiaohuihui
 * TODO:
 * 用栈模拟队列的出队，入队操作
 * 思路：用两个栈A和B
 * 有数据入队，放入A栈，有数据出队的时候，把a栈的数据依次弹出，压进b，如果
 */
class StackQueue {

    private final Stack<Integer> mStackA;
    private final Stack<Integer> mStackB;

    public static void main(String[] arg) {
        StackQueue stackQueue = new StackQueue();
        stackQueue.enQueue(1);
        stackQueue.enQueue(2);
        stackQueue.enQueue(3);
        System.out.println("出队元素：" + stackQueue.deQueue());
        System.out.println("出队元素：" + stackQueue.deQueue());
        stackQueue.enQueue(4);
        System.out.println("出队元素：" + stackQueue.deQueue());
        System.out.println("出队元素：" + stackQueue.deQueue());
        System.out.println("出队元素：" + stackQueue.deQueue());
    }

    StackQueue() {
        mStackA = new Stack<>();
        mStackB = new Stack<>();
    }

    public void enQueue(int a) {
        mStackA.push(a);
    }

    public Integer deQueue() {
        if (mStackB.size() == 0) {
            System.out.println("栈b已经为空");
            if (mStackA.size() == 0) {
                System.out.println("队列为空");
                return null;
            } else {
                while (mStackA.size() > 0) {
                    mStackB.push(mStackA.pop());
                }
                return mStackB.pop();
            }
        } else {
            return mStackB.pop();
        }
    }
}
