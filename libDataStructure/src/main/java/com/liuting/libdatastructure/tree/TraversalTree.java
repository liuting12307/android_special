package com.liuting.libdatastructure.tree;

import java.util.LinkedList;
import java.util.Stack;

public class TraversalTree {
    public static void main(String[] args) {
        TreeNode<String> rootNode=new TreeNode<>("A");
        makeTree(rootNode);
        System.out.println("前序遍历");
        preOrder(rootNode);
        System.out.println("通过栈前序遍历");
        preOrderWithStack(rootNode);
        System.out.println("中序遍历");
        inOrder(rootNode);
        System.out.println("通过栈中序遍历");
        inOrderWithStack(rootNode);
        System.out.println("后序遍历");
        postOrder(rootNode);
        System.out.println("通过栈后序遍历");
        postOrderWithStack(rootNode);

        System.out.println("广度优先，按层遍历");
        bfs(rootNode);
    }
    public static void makeTree(TreeNode<String> root){
        /*
        *                   A
        *                  / \
        *                 B   C
        *                / \ / \
        *               D  E F  G
        * */
        TreeNode<String> bNode=new TreeNode<>("B");
        TreeNode<String> cNode=new TreeNode<>("C");
        TreeNode<String> dNode=new TreeNode<>("D");
        TreeNode<String> eNode=new TreeNode<>("E");
        TreeNode<String> fNode=new TreeNode<>("F");
        TreeNode<String> gNode=new TreeNode<>("G");
        root.left=bNode;
        root.right=cNode;
        bNode.left=dNode;
        bNode.right=eNode;
        cNode.left=fNode;
        cNode.right=gNode;

    }
    //前序遍历 递归
    public static void preOrder(TreeNode<String> root){
        if(root==null)
            return;
        System.out.println(root);
        preOrder(root.left);
        preOrder(root.right);
    }

    //前序遍历 栈
    public static void preOrderWithStack(TreeNode<String> root){
        Stack<TreeNode<String>> stack=new Stack<>();
        TreeNode<String> temp=root;
        stack.push(temp);
        if(temp!=null){
            while(!stack.isEmpty()){
                temp=stack.pop();
                System.out.println(temp);
                if(temp.right!=null){
                    stack.push(temp.right);
                }
                if(temp.left!=null){
                    stack.push(temp.left);
                }
            }
        }
    }

    //中序遍历
    public static void inOrder(TreeNode<String> root){
        if(root==null) {
            return;
        }
        inOrder(root.left);
        System.out.println(root);
        inOrder(root.right);
    }

    //中序遍历 栈
    public static void inOrderWithStack(TreeNode<String> root){
        Stack<TreeNode<String>> stack=new Stack<>();
        TreeNode<String> temp=root;
        while(!stack.isEmpty()||temp!=null){
            if(temp!=null){
                stack.push(temp);
                temp=temp.left;
            }else{
                temp=stack.pop();
                System.out.println(temp);
                temp=temp.right;
            }
        }
    }

    //后序遍历
    public static void postOrder(TreeNode<String> root){
        if(root==null)
            return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root);
    }
    //后序遍历
    public static void postOrderWithStack(TreeNode<String> root){
        Stack<TreeNode<String>> stack = new Stack<TreeNode<String>>();
        TreeNode<String> p = root,    prev = root;
        while(p!=null || !stack.isEmpty()){
            while(p!=null){
                stack.push(p);
                p = p.left;
            }
            if(!stack.isEmpty()){
                TreeNode<String> temp = stack.peek().right;
                //只是拿出来栈顶这个值，并没有进行删除
                if(temp == null||temp == prev){
                    //节点没有右子节点或者到达根节点【考虑到最后一种情况】
                    p = stack.pop();
                    System.out.println(p);
                    prev = p;
                    p = null;
                } else{
                    p = temp;
                }
            }
        }
    }

    /**
     * 广度优先遍历（迭代模板）. 从根到叶子节点，层次遍历.
     *
     * @param root
     */
    public static void bfs(TreeNode<String> root){
        // 创建一个队列，用于存储待遍历的节点
        LinkedList<TreeNode<String>> nodeQueue=new LinkedList<>();
        nodeQueue.add(root);
        while(!nodeQueue.isEmpty()){
            //队头出队
            TreeNode<String> treeNode = nodeQueue.poll();
            System.out.println(treeNode);

            //左孩子入队
            if(treeNode.left!=null){
                nodeQueue.add(treeNode.left);
            }

            //右孩子入队
            if(treeNode.right!=null){
                nodeQueue.add(treeNode.right);
            }
        }

    }

}
