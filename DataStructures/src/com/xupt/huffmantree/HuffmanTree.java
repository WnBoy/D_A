package com.xupt.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Wnlife
 * @create 2019-12-06 16:14
 * <p>
 * 实现赫夫曼树
 */
public class HuffmanTree {

    public static void main(String[] args) {
        int arr[] = { 13, 7, 8, 3, 29, 6, 1 };
        Node root = createHuffmanTree(arr);
        preOrder(root);

    }

    /**
     * 前序遍历
     * @param root 要遍历的树的根节点
     */
    public static void preOrder(Node root){
        if(root!=null){
            root.preOrder();
        }else {
            System.out.println("此树是空树，不能遍历~~");
        }
    }

    /**
     * 创建赫夫曼树
     * @param arr 需要创建成哈夫曼树的数组
     * @return 创建好的赫夫曼树的根节点
     */
    public static Node createHuffmanTree(int[]arr){
        /*
         *第一步：遍历数组，将arr数组中每一个元素构成一个Node,将每一个Node放入到ArrayList中
         */
        List<Node> nodes = new ArrayList<>();
        for (int val : arr) {
            nodes.add(new Node(val));
        }
        //循环处理
        while (nodes.size()>1){
            Collections.sort(nodes);//排序，从小到大

            //取出根节点权值最小的两颗儿茶素
            Node leftNode=nodes.get(0);//取出权值最小的节点
            Node rightNode=nodes.get(1);//取出权值第二小的节点

            //将取出的两个节点构造为一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left=leftNode;
            parent.right=rightNode;

            //从集合中删除取出的节点
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //将新节点添加到集合中
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}

/**
 * 树节点
 */
class Node implements Comparable<Node>{
    int value;//节点权值
    Node left;//指向左子节点
    Node right;//指向右子节点

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public int compareTo(Node o) {
        // 表示从小到大排序
        return this.value-o.value;
    }
}