package com.liuting.libdatastructure.queue;

public class ArrayQueue {
    private String[] items;
    private int n = 0;
    private int head = 0;//从队头出数据
    private int tail = 0;//从尾部加入新数据

    // 申请一个大小为capacity的数组
    public ArrayQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }

    // 入队操作，将item放入队尾
    public boolean enqueue(String item) {
        if (tail == n) {
            if(head==0){
                System.out.println("队列已满，入队失败");
                return false;
            }
            for(int i=head;i<tail;i++){
                items[i-head]=items[i];
            }
            tail=tail-head;
            head=0;
        }
        items[tail] = item;
        tail++;
        return true;
    }

    //队列为空的条件 head=tail
    public String dequeue() {
        if (head == tail) {
            System.out.println("队列为空，出队失败");
            return null;
        }
        String temp = items[head];
        head++;
        System.out.printf("元素%s出队列\n",temp);
        return temp;
    }

    public void printQueue() {
        if (head == tail) {
            System.out.println("队列为空");
            return;
        }
        StringBuilder stringBuilder = new StringBuilder("队列为=[");
        for (int i = head; i < tail; i++) {
            if (i == tail - 1) {
                stringBuilder.append(items[i]).append("]");
            } else {
                stringBuilder.append(items[i]).append(",");
            }
        }
        System.out.println(stringBuilder.toString());
    }
}
