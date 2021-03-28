package com.ironass.algorithm.leetcode;

//Given two binary trees, write a function to check if they are the same or not.
//
// Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
//
// Example 1:
//
//
//Input:     1         1
//          / \       / \
//         2   3     2   3
//
//        [1,2,3],   [1,2,3]
//
//Output: true
//
//
// Example 2:
//
//
//Input:     1         1
//          /           \
//         2             2
//
//        [1,2],     [1,null,2]
//
//Output: false
//
//
// Example 3:
//
//
//Input:     1         1
//          / \       / \
//         2   1     1   2
//
//        [1,2,1],   [1,1,2]
//
//Output: false
//
//

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 *
 * @author lixin
 * @date 2019-05-29 14:45
 **/
public class Solution_100 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 思路：常见思路是 选取某种遍历方式分别遍历两颗二叉树，遍历结果存放在容器当中并对容器内值进行比较(应该摒弃掉，遍历过程中比较即可)。
     * 另外一种思路则是通过递归方式，层次遍历两棵树并比较同样节点，有不同的则返回false,这种方式目前来看是该题的最优解。
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null && q == null) {
            return true;
        }
        if (p != null && q != null) {
            if (p.val != q.val) {
                return false;
            } else {
                return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        TreeNode treeA1 = new TreeNode(1);
        TreeNode treeA2 = new TreeNode(2);
        TreeNode treeA3 = new TreeNode(3);
        treeA1.left = treeA2;
        treeA1.right = treeA3;

        TreeNode treeB1 = new TreeNode(1);
        TreeNode treeB2 = new TreeNode(2);
        TreeNode treeB3 = new TreeNode(3);
        treeB1.left = treeB2;
        treeB1.right = treeB3;

        Solution_100 solution_100 = new Solution_100();
        System.out.println(solution_100.isSameTree(treeA1, treeB1));
    }
}
