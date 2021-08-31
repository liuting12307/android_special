package com.liuting.libdatastructure.tree;

public class BinarySearchTree {
    private TreeNode<Integer> root;
    public TreeNode<Integer> find(int data){
        TreeNode<Integer> p=root;
        while(p!=null){
            if(p.data==data){
                return p;
            }else if(p.data>data){
                p=p.left;
            }else{
                p=p.right;
            }
        }
        return null;
    }

    public void insert(int data){
        TreeNode<Integer> p=root;
        while(p!=null){
            if(p.data>data){
                if(p.left==null){
                    p.left= new TreeNode<>(data);
                    return;
                }else{
                    p=p.left;
                }
            }else if(p.data<data){
                if(p.right==null){
                    p.right= new TreeNode<>(data);
                    return;
                }else{
                    p=p.right;
                }
            }
        }
    }

    public void delete(int data){

    }
}
