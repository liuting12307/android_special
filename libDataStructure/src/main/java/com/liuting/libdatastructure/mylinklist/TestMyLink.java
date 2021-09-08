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
        MyLink<Integer> myLink2=new MyLink<>();
        buildMyLink2(myLink2);
        mergeLink(myLink,myLink2).printLink();
        System.out.println("---------构建循环链表-----------");
        Node<Integer> loop1=new Node<>(5);
        Node<Integer> loop2=new Node<>(3);
        Node<Integer> loop3=new Node<>(7);
        Node<Integer> loop4=new Node<>(2);
        Node<Integer> loop5=new Node<>(6);
        Node<Integer> loop6=new Node<>(8);
        Node<Integer> loop7=new Node<>(1);
        loop1.next=loop2;
        loop2.next=loop3;
        loop3.next=loop4;
        loop4.next=loop5;
        loop5.next=loop6;
        loop6.next=loop7;
        loop7.next=loop4;
        System.out.println("环的长度为:"+loopLength(loop1));
        System.out.println("环的入口值为:"+loopMeetValue(loop1));


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
        myLink.addNode(15);
    }
    public static void buildMyLink2(MyLink<Integer> myLink){
        myLink.addNode(8);
        myLink.addNode(9);
        myLink.addNode(10);
        myLink.addNode(3);
        myLink.addNode(16);
    }

    public static void reverseLink(MyLink<Integer> myLink){//链表反转
        if(myLink.getHead()==null)
            return;
        Node<Integer> preNode=null;//前驱节点
        Node<Integer> sucNode;//后继节点
        Node<Integer> current=myLink.getHead();
        while(current!=null){
            sucNode=current.next;
            current.next=preNode;
            preNode=current;
            current=sucNode;
        }
        myLink.setHead(preNode);
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
        return false;
    }

    /**
     * 如果链表有环，求环的长度
     *
     * @param head
     * @return
     */
    public static int loopLength(Node<Integer> head) {
        Node<Integer> fast=head;
        Node<Integer> slow=head;
        if (fast == null) {
            return 0;
        }
        int meetTimes=0;
        int count=0;
        while(fast!=null&&fast.next!=null){
            count++;
            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow){
                System.out.println("该链表有环");
                meetTimes++;
                if(meetTimes==1){
                    count=0;
                }
                if(meetTimes==2){
                    return count;
                }
            }
        }
        return 0;
    }


    /**
     * 如果链表有环，求入环节点值
     *
     * @param head
     * @return
     */
    public static int loopMeetValue(Node<Integer> head) {

        Node<Integer> fast=head;
        Node<Integer> slow=head;
        if (fast == null) {
            return 0;
        }
        boolean hasMeet=false;
        while(fast!=null&&fast.next!=null){
            if(hasMeet){
                fast=fast.next;
            }else{
                fast=fast.next.next;
            }
            slow=slow.next;
            if(hasMeet){
                if(fast==slow){
                    return fast.data;
                }
            }else{
                if(fast==slow){
                    System.out.println("该链表有环");
                    //首次相遇时将快指针移到起点值
                    fast=head;
                    hasMeet=true;
                }
            }

        }
        return -1;
    }

    /**
     * 合并有序链表
     *
     */
    public static MyLink<Integer> mergeLink(MyLink<Integer> myLink1,MyLink<Integer> myLink2){
        if(myLink1==null||myLink2==null){
            System.out.println("非法传入");
            return null;
        }
        orderList(myLink2);
        myLink2.printLink();
        Node<Integer> currentLink1Node=myLink1.getHead();
        Node<Integer> currentLink2Node=myLink2.getHead();
        if(currentLink2Node==null){
            System.out.println("非法传入");
            return null;
        }
        boolean hasMerge=false;
        while(currentLink1Node!=null){
            Node<Integer> temp=currentLink1Node.next;
            Node<Integer> preNode=null;
            while(currentLink2Node!=null){
                if(currentLink1Node.data<currentLink2Node.data){
                    if(preNode==null){
                        myLink2.setHead(currentLink1Node);
                    }else{
                        preNode.next=currentLink1Node;
                    }
                    currentLink1Node.next=currentLink2Node;
                    hasMerge=true;
                    preNode=null;
                    currentLink2Node=myLink2.getHead();
                    myLink2.printLink();
                    break;
                }
                preNode=currentLink2Node;
                currentLink2Node=currentLink2Node.next;
            }
            if(!hasMerge){
                preNode.next=currentLink1Node;
            }
            currentLink1Node=temp;
        }
        return myLink2;
    }
}
