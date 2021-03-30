package com.ironass.algorithm.shopee;

import java.util.ArrayList;
import java.util.List;

public class ShopeeNC45_ThreeOrders {

    public static void main(String[] args) {

    }


    //建三个ArrayList分别存储三种遍历的结果
    ArrayList<Integer> pre = new ArrayList<>();
    ArrayList<Integer> middle = new ArrayList<>();
    ArrayList<Integer> post = new ArrayList<>();

    /**
     *
     * @param root TreeNode类 the root of binary tree
     * @return int整型二维数组
     */
    public int[][] threeOrders (TreeNode root) {
        // write code here
        preOrder(root);
        middleOrder(root);
        postOrder(root);

        int length = pre.size();
        int[][] res = new int[3][length];
        for(int i = 0; i< length; i++){
            res[0][i] = pre.get(i);
            res[1][i] = middle.get(i);
            res[2][i] = post.get(i);
        }

        return res;
    }



    private void preOrder(TreeNode root) {
        if(root == null){
            return;
        }
        pre.add(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    private void middleOrder(TreeNode root) {
        if(root == null){
            return;
        }
        middleOrder(root.left);
        middle.add(root.val);
        middleOrder(root.right);
    }

    private void postOrder(TreeNode root) {
        if(root == null){
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        post.add(root.val);
    }

}

 class TreeNode {
  int val = 0;
  TreeNode left = null;
  TreeNode right = null;
}

