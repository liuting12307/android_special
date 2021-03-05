package com.liuting.libdatastructure.mylinklist;

public class MyLink<T> {
    private Node<T> head=null;//头节点
    public void addNode(T data){
        Node<T> newNode=new Node<>(data);
        if(head==null){
            head=newNode;
            return;
        }
        Node<T> temp=head;
        while(temp.next!=null){
            temp=temp.next;
        }
        temp.next=newNode;
    }

    public int length(){
        if(head==null){
            return 0;
        }
        int count=1;
        Node<T> temp=head;
        while(temp.next!=null){
            count++;
            temp=temp.next;
        }
        return count;
    }

    public void printLink(){
        if(head==null){
            System.out.println("当前链表为空");
            return;
        }
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("[").append(head.data.toString());
        Node<T> temp=head;
        while(temp.next!=null){
            stringBuilder.append(",").append(temp.next.data.toString());
            temp=temp.next;
        }
        stringBuilder.append("]");
        System.out.println(stringBuilder.toString());
    }


    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }
}
