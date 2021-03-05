package com.liuting.libdatastructure.mylinklist;

public class TestMyLink {
    public static void main(String[] args){
        MyLink<Integer> myLink=new MyLink<>();
        buildMyLink(myLink);
        System.out.println("length="+myLink.length());
        myLink.printLink();
        reverseLink(myLink);
        myLink.printLink();
        searchMid(myLink.getHead());
        System.out.println("倒数后元素为:"+findReverseElem(myLink,6).data);
        orderList(myLink);
        myLink.printLink();
        deleteDuplecate(myLink);
        myLink.printLink();
        printListReversely(myLink.getHead());
        isLoop(myLink.getHead());

    }

    public static void buildMyLink(MyLink<Integer> myLink){
        myLink.addNode(2);
        myLink.addNode(7);
        myLink.addNode(1);
        myLink.addNode(4);
        myLink.addNode(5);
        myLink.addNode(1);
        myLink.addNode(1);
        myLink.addNode(6);
    }

    public static void reverseLink(MyLink<Integer> myLink){//链表反转
        if(myLink.getHead()==null)
            return;
        Node<Integer> preNode=null;//前驱节点
        Node<Integer> sucNode;//后继节点
        Node<Integer> current=myLink.getHead();
        while(current!=null){
            myLink.setHead(current);
            sucNode=current.next;
            current.next=preNode;
            preNode=current;
            current=sucNode;
        }
    }

    /**
     * 查找单链表的中间节点
     *
     * @param head
     * @return
     */
    public static Node<Integer> searchMid(Node<Integer> head) {
        Node<Integer> fast = head, slow = head;
        while (fast != null && fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        System.out.println("Mid:" + slow.data);
        return slow;
    }

    /**
     * 查找倒数第k个元素
     *  采用两个指针P1,P2，P1先前移K步，然后P1、P2同时移动，当p1移动到尾部时，P2所指位置的元素即倒数第k个元素
     * @param k
     * @return
     */
    public static Node<Integer> findReverseElem(MyLink<Integer> myLink, int k) {

        if(k<1||k>myLink.length()){
            System.out.println("k取值非法");
            return null;
        }
        Node<Integer> p1=myLink.getHead();
        Node<Integer> p2=myLink.getHead();
        for(int i=0;i<k;i++){
            p1=p1.next;
        }
        while(p1!=null){
            p1=p1.next;
            p2=p2.next;
        }


        return p2;
    }

    /**
     * 排序  从小到大排序
     *
     * @return
     */
    public static void orderList(MyLink<Integer> myLink) {
        Node<Integer> nextNode;
        int temp;
        Node<Integer> curNode=myLink.getHead();
        while(curNode!=null){
            nextNode=curNode.next;
            while(nextNode!=null){
                if(curNode.data>nextNode.data){
                    temp=curNode.data;
                    curNode.data=nextNode.data;
                    nextNode.data=temp;
                }
                nextNode=nextNode.next;
            }
            curNode=curNode.next;
        }
    }

    /**
     * 删除重复节点
     *
     * @return
     */
    public static void deleteDuplecate(MyLink<Integer> myLink) {
        Node<Integer> temp;
        Node<Integer> curNode=myLink.getHead();
        while(curNode!=null){
            temp=curNode;
            while(temp.next!=null){
                if(curNode.data.equals(temp.next.data)){
                    temp.next=temp.next.next;
                }else{
                    temp=temp.next;
                }
            }
            curNode=curNode.next;
        }
    }

    /**
     * 从尾到头输出单链表，采用递归方式实现
     *
     * @param pListHead
     */
    public static void printListReversely(Node<Integer> pListHead) {
       if(pListHead!=null){
           printListReversely(pListHead.next);
           System.out.println("printListReversely:" + pListHead.data);
       }
    }

    /**
     * 判断链表是否有环，单向链表有环时，尾节点相同
     *
     * @param head
     * @return
     */
    public static boolean isLoop(Node<Integer> head) {
        Node<Integer> fast=head;
        Node<Integer> slow=head;
        if (fast == null) {
            return false;
        }
        while(fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow){
                System.out.println("该链表有环");
                return true;
            }
        }
        return !(fast == null || fast.next == null);
    }
}
