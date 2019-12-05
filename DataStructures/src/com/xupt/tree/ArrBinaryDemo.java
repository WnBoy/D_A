package com.xupt.tree;

/**
 * @author Wnlife
 * @create 2019-11-28 19:03
 * <p>
 * 编写一个ArrayBinaryTree, 实现顺序存储二叉树遍历
 */
public class ArrBinaryDemo {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree abt = new ArrBinaryTree(arr);
        System.out.println("前序遍历~~");
        abt.preOrder();//1 2 4 5 3 6 7
        System.out.println("中续遍历~~");
        abt.infixOrder();//4 2 5 1 6 3 7
        System.out.println("后续遍历~~");
        abt.postOrder();//4 5 2 6 7 3 1
    }
}

/**
 * 顺序存储二叉树
 */
class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        preOrder(0);
    }

    /**
     * 二叉树顺序存储的前序遍历
     *
     * @param index 数组的角标
     */
    private void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空~~");
            return;
        }
        //输出当前值
        System.out.println(arr[index] + " ");
        //递归的输出左子树的值
        if (2 * index + 1 < arr.length) {
            preOrder(2 * index + 1);
        }
        //递归的输出右子树的值
        if (2 * index + 2 < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    public void infixOrder() {
        infixOrder(0);
    }

    /**
     * 二叉树顺序存储的中序遍历
     *
     * @param index 数组的角标
     */
    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空~~");
            return;
        }
        //递归输出左子节点
        if (index * 2 + 1 < arr.length) {
            infixOrder(index * 2 + 1);
        }
        //输出自己本身的值
        System.out.println(arr[index]+" ");
        //递归输出右子节点
        if (index * 2 + 2 < arr.length) {
            infixOrder(index * 2 + 2);
        }
    }

    public void postOrder() {
        postOrder(0);
    }

    /**
     * 二叉树顺序存储的后续遍历
     *
     * @param index
     */
    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空~~");
            return;
        }
        //先输出左子节点
        if (index * 2 + 1 < arr.length) {
            postOrder(index * 2 + 1);
        }
        //再输出右子节点
        if (index * 2 + 2 < arr.length) {
            postOrder(index * 2 + 2);
        }
        //输出自己本身的值
        System.out.println(arr[index]+" ");
    }
}