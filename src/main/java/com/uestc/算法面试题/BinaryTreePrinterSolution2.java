package com.uestc.算法面试题;


/**
 * 如何较为直观地打印二叉树
 *
 * 【题目】
 * 二叉树可以用常规的三种遍历结果来描述其结构，但是不够直观，尤其是二叉树中有重复值的时候，仅通过三种遍历的结果来构造二叉树的
 * 真实结构更是难上加难，有时候根本不可能。给定一棵二叉树的头节点head，已知二叉树节点值的类型为32位整数，请实现一个打印二叉
 * 树的函数，可以直观地展示树的形状，也便于画出真实的结构。
 *
 * 【难度】
 * 中等
 *
 * 【解答】
 * 请参看如下代码中的print方法。
 *
 * @author Created by LiveEveryDay
 */

public class BinaryTreePrinterSolution2 {

    public static class Node {
        public int data;
        public Node left;
        public Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void print(Node root) {
        System.out.println("The binary tree is:");
        if (root == null) {
            System.out.println("Empty binary tree!");
        }
        int height = getHeight(root);
        int arrHeight = height * 2 - 1;
        int arrWidth = (2 << (height - 2)) * 3 + 1;
        String[][] r = new String[arrHeight][arrWidth];
        for (int i = 0; i < arrHeight; i++) {
            for (int j = 0; j < arrWidth; j++) {
                r[i][j] = " ";
            }
        }
        writeToArray(height, root, r, 0, arrWidth / 2);
        for (String[] line : r) {
            StringBuilder strBuilder = new StringBuilder();
            for (int i = 0; i < line.length; i++) {
                strBuilder.append(line[i]);
                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2 : line[i].length() - 1;
                }
            }
            System.out.println(strBuilder);
        }
    }

    private static int getHeight(Node root) {
        return root == null ? 0 : (1 + Math.max(getHeight(root.left), getHeight(root.right)));
    }

    private static void writeToArray(int height, Node node, String[][] r, int i, int j) {
        if (node == null) {
            return;
        }
        r[i][j] = String.valueOf(node.data);
        int curLevel = (i + 1) >> 1;
        if (curLevel == height) {
            return;
        }
        int gap = height - curLevel - 1;
        if (node.left != null) {
            r[i + 1][j - gap] = "/";
            writeToArray(height, node.left, r, i + 2, j - gap * 2);
        }
        if (node.right != null) {
            r[i + 1][j + gap] = "\\";
            writeToArray(height, node.right, r, i + 2, j + gap * 2);
        }
    }

    public static void main2(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node4.left = node8;
        print(node1);
    }


    public static void main(String[] args) throws Exception {
        int x = 1;

        if (x == 1) {
            throw new Exception("sS");
        }
        System.out.println(1);
    }

}

// ------ Output ------
/*
The binary tree is:
      2
    /   \
  3       5
 / \     / \
7   8   1   3
*/