package com.xupt.binarysorttree;

/**
 * @author Wnlife
 * @create 2019-12-10 20:08
 * <p>
 * 二叉排序树
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        for (int a : arr) {
            binaryTree.add(new Node(a));
        }
        binaryTree.infixOrder();

//        System.out.println("测试查找目标节点和目标节点的父节点~~");
//        Node targetNode = binaryTree.searchTargetNode(2);
//        Node parentNode = binaryTree.searchParentNode(targetNode);
//        System.out.println("targetNode=" + targetNode);
//        System.out.println("parentNode=" + parentNode);

        System.out.println("删除节点测试~~");
        binaryTree.deleteNode(3);
        binaryTree.deleteNode(5);
        binaryTree.deleteNode(7);
        binaryTree.deleteNode(12);
        binaryTree.deleteNode(10);
        binaryTree.deleteNode(1);
        binaryTree.deleteNode(2);
        binaryTree.deleteNode(9);
        binaryTree.infixOrder();

    }

}

//二叉排序树
class BinaryTree {
    private Node root;

    //增加节点的方法
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //前序遍历的方法
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("树为空~~");
        }
    }

    /**
     * 找到要删除的目标节点
     *
     * @param value 目标节点的值
     * @return 要删除的targetNode节点，没有找到返回null
     */
    public Node searchTargetNode(int value) {
        if (root != null) {
            return root.searchTargetNode(value);
        } else {
            System.out.println("~~排序二叉树为空~~");
            return null;
        }
    }

    /**
     * 找到要删除节点的父节点
     *
     * @param node 要删除的节点
     * @return 要删除节点的父节点, 没有找到返回null
     */
    public Node searchParentNode(Node node) {
        if (root != null) {
            return root.searchParentNode(node);
        } else {
            System.out.println("~~排序二叉树为空~~");
            return null;
        }
    }


    /**
     * 找到当前子树的最小节点的值
     *
     * @param node 当前子树的根节点
     * @return 最小节点的值
     */
    public int findSmallNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        int temp = node.value;
        deleteNode(temp);
        return temp;
    }

    /**
     * 删除一个节点
     *
     * @param value 要删除节点的值
     */
    public void deleteNode(int value) {
        //1.找到要删除的节点
        Node targetNode = searchTargetNode(value);
        if (targetNode == null) {
            System.out.println("要删除的节点不存在!!!");
            return;
        }
        //2.找到要删除节点的父节点
        Node parentNode = searchParentNode(targetNode);
        //第一种情况: 删除叶子节点
        if (targetNode.left == null && targetNode.right == null) {
            if (parentNode != null) {//如果父节点不为空
                if (parentNode.left == targetNode) {
                    parentNode.left = null;
                } else {
                    parentNode.right = null;
                }
            } else {//如果父节点为空
                root = null;
            }
        } else if (targetNode.left != null && targetNode.right != null) {//第三种情况：删除有两颗子树的节点
            //方法1：找到targetNode节点的右子节点的最小节点，使用临时变量temp将最小节点的值保存起来，
            //删除这个最小节点，并且将保存的temp赋值给targetNode，即：targetNode.val=temp;
            int temp = findSmallNode(targetNode.right);
//            System.out.println("smallNodeValue=" + smallNodeValue);
            targetNode.value = temp;
        } else {//第二种情况：删除只有一颗子树的节点
            if (targetNode.left != null) {//如果targetNode有左子节点
                if (parentNode != null) {//如果父节点不为空
                    if (parentNode.left == targetNode) {//如果targetNode是parentNode的左子节点
                        parentNode.left = targetNode.left;
                    } else {//如果targetNode是parentNode的右子节点
                        parentNode.right = targetNode.left;
                    }
                } else {//如果父节点为空
                    root = targetNode.left;
                }
            } else {//如果targetNode有右子节点
                if (parentNode != null) {//如果父节点不为空
                    if (parentNode.left == targetNode) {//如果targetNode是parentNode的左子节点
                        parentNode.left = targetNode.right;
                    } else {//如果targetNode是parentNode的右子节点
                        parentNode.left = targetNode.right;
                    }
                } else {//如果父节点为空
                    root = targetNode.left;
                }
            }
        }
    }

}

//树节点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //增加节点的方法
    public void add(Node node) {
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    //前序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 找到要删除的目标节点
     *
     * @param value 目标节点的值
     * @return 要删除的targetNode节点，没有找到返回null
     */
    public Node searchTargetNode(int value) {
        if (this.value == value) {//找到，直接返回
            return this;
        }
        if (value < this.value) {
            if (this.left != null) {
                return this.left.searchTargetNode(value);
            }
        } else {
            if (this.right != null) {
                return this.right.searchTargetNode(value);
            }
        }
        return null;
    }

    /**
     * 找到要删除节点的父节点
     *
     * @param node 要删除的节点
     * @return 要删除节点的父节点, 没有找到返回null
     */
    public Node searchParentNode(Node node) {
        if ((this.left != null && this.left == node) || (this.right != null && this.right == node)) {
            return this;
        }
        if (node.value < this.value) {
            if (this.left != null) {
                return this.left.searchParentNode(node);
            }
        } else {
            if (this.right != null) {
                return this.right.searchParentNode(node);
            }
        }
        return null;
    }
}