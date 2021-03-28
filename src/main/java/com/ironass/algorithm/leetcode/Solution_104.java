package com.ironass.algorithm.leetcode;

//Given a binary tree, find its maximum depth.
//
// The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
//
// Note: A leaf is a node with no children.
//
// Example:
//
// Given binary tree [3,9,20,null,null,15,7],
//
//
//    3
//   / \
//  9  20
//    /  \
//   15   7
//
// return its depth = 3.
//

/**
 * 求一颗二叉树的深度 easy,采用递归是目前最高效最优解。
 *
 * @author lixin
 * @date 2019-05-30 18:47
 **/
public class Solution_104 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 解法思路： 二叉树问题一般都可以采用递归求解，此题亦然。
     * 分别递归遍历一个子节点的左子树和右子树，每遍历一级深度+1
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftDepth = maxDepth(root.left);
            int rightDepth = maxDepth(root.right);
            return (leftDepth > rightDepth ? leftDepth : rightDepth) + 1;
        }
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
        Solution_104 solution_104 = new Solution_104();
        System.out.println(solution_104.maxDepth(tree1));
    }
}
