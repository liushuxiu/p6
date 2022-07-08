package com.uestc.算法面试题.树的视图;


import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * 二叉树的左视图和右视图
 * 所谓二叉树的左视图，是指打印从左方向看到的二叉树。
 * 根据前序遍历算法思想，在左视图代码中先遍历左子树在遍历左子树，这样在判断level == resultList.size()时，先遍历左子树则保证层数与数组长度相等时遍历的是每一层的第一个节点
 * 同理，在右视图中，在判断level == resultList.size()时，先遍历右子树保证层数与数组长度相等时遍历的是每一层的最后一个节点
 * @date 2021/2/26 16:04
 */
public class TreeView {

    public void view(TreeNode root) {
        if (null == root) {
            return;
        }
        int level = 0;
        List<Integer> resultList = new ArrayList<>();
//        rightview(root, resultList, level);
        leftview(root, resultList, level);
        resultList.stream().forEach(a -> {
            System.out.print(a + " ");
        });
    }

    /**
     * 二叉树左视图
     * 打印每一层的第一个节点
     * @param root
     * @param resultList
     * @param level
     */
    private void leftview(TreeNode root, List<Integer> resultList, int level) {
        if (null == root)
            return;
        if (level == resultList.size())//判断是不是每一层的最后一个节点
            resultList.add(root.val);
        leftview(root.left, resultList,level + 1);
        leftview(root.right, resultList,level + 1);
    }

    /**
     * 二叉树右视图
     * 打印每一层的最后一个节点
     * @param root
     * @param resultList
     * @param level
     */
    private void rightview(TreeNode root, List<Integer> resultList, int level) {
        if (null == root)
            return;
        if (level == resultList.size())//判断是不是每一层的第一个节点
            resultList.add(root.val);
        rightview(root.right, resultList,level + 1);
        rightview(root.left, resultList,level + 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode r2 = new TreeNode(2);
        TreeNode r3 = new TreeNode(3);
        TreeNode r4 = new TreeNode(4);
        TreeNode r5 = new TreeNode(5);
        TreeNode r6 = new TreeNode(6);
        TreeNode r7 = new TreeNode(7);
        TreeNode r8 = new TreeNode(8);
        TreeNode r9 = new TreeNode(9);
        TreeNode r10 = new TreeNode(10);
        TreeNode r11 = new TreeNode(11);
        TreeNode r12 = new TreeNode(12);
        TreeNode r13 = new TreeNode(13);
        TreeNode r14 = new TreeNode(14);
        TreeNode r15 = new TreeNode(15);
        root.left = r2;
        root.right = r3;
        r2.left = r4;
        r2.right = r5;
        r3.left = r6;
        r3.right = r7;
        r4.left = r8;
        r4.right = r9;
        r9.left = r10;
        r6.left = r11;
        r7.right=r12;
        r12.right=r13;
        r13.right=r14;
        r14.right=r15;
        new TreeView().view(root);
    }
}

class TreeNode {
    int val;
    TreeNode left = null;
    TreeNode right = null;
    TreeNode(int val) {
        this.val = val;
    }
}