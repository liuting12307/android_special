package com.liuting.libdatastructure.tree;

import java.util.LinkedList;

public class TraversalTree {
    public static void main(String[] args) {
        TreeNode<String> rootNode=new TreeNode<>("A");
        makeTree(rootNode);
        System.out.println("前序遍历");
        preOrder(rootNode);
        System.out.println("中序遍历");
        inOrder(rootNode);
        System.out.println("后序遍历");
        postOrder(rootNode);

        System.out.println("广度优先，按层遍历");
        bfs(rootNode);
    }
    public static void makeTree(TreeNode<String> root){
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
    //前序遍历
    public static void preOrder(TreeNode<String> root){
        if(root==null)
            return;
        System.out.println(root);
        preOrder(root.left);
        preOrder(root.right);
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

    //后序遍历
    public static void postOrder(TreeNode<String> root){
        if(root==null)
            return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root);
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
