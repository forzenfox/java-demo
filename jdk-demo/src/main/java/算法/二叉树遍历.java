package 算法;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class 二叉树遍历 {

    public static Tree initTree() {
        Tree node1 = new Tree(1);
        Tree node2 = new Tree(2);
        Tree node3 = new Tree(3);
        Tree node4 = new Tree(4);
        Tree node5 = new Tree(5);
        Tree node6 = new Tree(6);
        Tree node7 = new Tree(7);
        Tree node8 = new Tree(8);
        Tree node9 = new Tree(9);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node5.right = node8;
        node7.left = node9;
        return node1;
    }

    public static void main(String[] args) {
        先序深度(initTree());
    }

    public static void 递归先序(Tree tree) {
        if (tree == null) {
            return;
        }

        System.out.println(tree.data);
        递归先序(tree.left);
        递归先序(tree.right);
    }

    // 先序
    public static void 先序深度(Tree tree) {
        Stack<Tree> stack = new Stack<>();
        stack.push(tree);

        while (!stack.isEmpty()) {
            Tree node = stack.pop();
            System.out.println(node.data);

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    public static void 层次遍历(Tree tree) {
        Queue<Tree> queue = new LinkedList<>();
        queue.add(tree);

        while (!queue.isEmpty()) {
            Tree node = queue.poll();
            System.out.println(node.data);

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }


}


class Tree {
    int data;
    Tree left;
    Tree right;

    public Tree(int data) {
        this.data = data;
    }
}