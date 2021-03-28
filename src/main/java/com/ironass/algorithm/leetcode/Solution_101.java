package com.ironass.algorithm.leetcode;
//Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
//
// For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
//
//
//    1
//   / \
//  2   2
// / \ / \
//3  4 4  3
//
//
//
//
// But the following [1,2,2,null,3,null,3] is not:
//
//
//    1
//   / \
//  2   2
//   \   \
//   3    3
//
//
//
//
// Note:
//Bonus points if you could solve it both recursively and iteratively.
//

/**
 * 判断一棵树是否为镜像树
 *
 * @author lixin
 * @date 2019-05-29 17:46
 **/
public class Solution_101 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 思路：解法有多种，题目要求使用递归+迭代的方法更能加分。
     * 采用递归+迭代的方法 需要新定义一个用于递归迭代的方法，特别需要注意递归调用时的参数传递,参数也是以镜像的方式传递。
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left != null && right != null) {
            if (left.val != right.val) {
                return false;
            }
            return (isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left));
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode tree1 = new TreeNode(1);
        TreeNode tree2 = new TreeNode(2);
        TreeNode tree3 = new TreeNode(2);
        TreeNode tree4 = new TreeNode(3);
        TreeNode tree5 = new TreeNode(3);
        TreeNode tree6 = new TreeNode(4);
        TreeNode tree7 = new TreeNode(4);

        tree1.left = tree2;
        tree1.right = tree3;
        tree2.left = tree4;
        tree2.right = tree6;
        tree3.left = tree7;
        tree3.right = tree5;
        Solution_101 solution_101 = new Solution_101();
        System.out.println(solution_101.isSymmetric(tree1));
    }
}
