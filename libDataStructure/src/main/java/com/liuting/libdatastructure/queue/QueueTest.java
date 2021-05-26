package com.liuting.libdatastructure.queue;

public class QueueTest {

    public static void main(String[] args){
        ArrayQueue arrayQueue=new ArrayQueue(5);
        arrayQueue.printQueue();
        arrayQueue.enqueue("1");
        arrayQueue.enqueue("2");
        arrayQueue.enqueue("3");
        arrayQueue.enqueue("4");
        arrayQueue.enqueue("5");
        arrayQueue.printQueue();
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.printQueue();
        arrayQueue.enqueue("6");
        arrayQueue.printQueue();
        arrayQueue.enqueue("7");
        arrayQueue.printQueue();
        System.out.println("-----------下面为循环队列-------------------");
        CircleQueue circleQueue=new CircleQueue(5);
        circleQueue.printQueue();
        circleQueue.enqueue("1");
        circleQueue.enqueue("2");
        circleQueue.enqueue("3");
        circleQueue.enqueue("4");
        circleQueue.enqueue("5");
        circleQueue.printQueue();
        circleQueue.dequeue();
        circleQueue.dequeue();
        circleQueue.dequeue();
        circleQueue.printQueue();
        circleQueue.enqueue("4");
        circleQueue.printQueue();
        circleQueue.enqueue("4");
        circleQueue.printQueue();
    }
}
