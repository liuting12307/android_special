package com.liuting.libdatastructure.queue;

public class CircleQueue {

    private String[] items;
    private int n = 0;
    // head表示队头下标，tail表示队尾下标
    private int head = 0;
    private int tail = 0;

    // 申请一个大小为capacity的数组
    public CircleQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }

    // 入队操作，将item放入队尾
    public boolean enqueue(String item) {
        if ((tail + 1) % n == head) {
            System.out.println("队列已满，入队失败");
            return false;
        }
        items[tail] = item;
        tail = (tail + 1) % n;
        System.out.printf("元素%s入队\n", item);
        return true;
    }


    public String dequeue() {
        if (head == tail) {
            System.out.println("队列为空，出队失败");
            return null;
        }
        String temp = items[head];
        head = (head + 1) % n;
        System.out.printf("元素%s出队列\n", temp);
        return temp;
    }

    public void printQueue() {
        if (head == tail) {
            System.out.println("队列为空");
            return;
        }
        System.out.printf("tail=%d\n", tail);
        System.out.printf("head=%d\n", head);
        StringBuilder stringBuilder = new StringBuilder("队列为=[");
        if (tail > head) {
            for (int i = head; i < tail; i++) {
                if (i == tail - 1) {
                    stringBuilder.append(items[i]);
                } else {
                    stringBuilder.append(items[i]).append(",");
                }
            }
        } else {
            for (int i = head; i < n; i++) {
                stringBuilder.append(items[i]).append(",");
            }
            if(tail==0){
                stringBuilder.deleteCharAt(stringBuilder.length()-1);
            }
            for (int i = 0; i < tail; i++) {
                if (i == tail - 1) {
                    stringBuilder.append(items[i]);
                } else {
                    stringBuilder.append(items[i]).append(",");
                }
            }
        }
        System.out.println(stringBuilder.append("]").toString());
    }

}
