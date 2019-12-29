package com.xupt.avltree;

/**
 * @author Wnlife
 * @create 2019-12-29 11:08
 *
 * AVL树：平衡二叉搜索树
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        AVLTree avlTree=new AVLTree();
//        int []arr={4,3,6,5,7,8};
//        int []arr={10,12, 8, 9, 7, 6};
        int []arr={10, 11, 7, 6, 8, 9};

        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        System.out.println("中序遍历");
        avlTree.infixOrder();
        //树高4，左子树高1，右子树高3
        System.out.println("树的高度="+avlTree.getRoot().height());
        System.out.println("树的左子树的高度="+avlTree.getRoot().leftHeight());
        System.out.println("树的右子树的高度="+avlTree.getRoot().rightHeight());

    }
}


/**
 * AVLTree
 */
class AVLTree{
    private Node root;

    public Node getRoot() {
        return root;
    }

    /**
     * 增加节点的方法
     * @param node
     */
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * 前序遍历的方法
     */
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

/**
 * 树节点
 */
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

    /**
     * 返回当前节点的左子树的高度
     * @return
     */
    public int leftHeight() {
        if(this.left==null){
            return 0;
        }else {
            return this.left.height();
        }
    }

    /**
     * 返回当前节点的右子树的高度
     * @return
     */
    public int rightHeight(){
        if(this.right==null){
            return 0;
        }else {
            return this.right.height();
        }
    }

    /**
     * 返回以该节点为根节点的树的高度
     * @return 树的高度
     */
    public int height(){
        return Math.max(this.left == null ? 0 : this.left.height(),
                this.right == null ? 0 : this.right.height()) + 1;
    }

    /**
     * 左旋转：降低右子树的高度
     */
    public void leftRotate(){
        //1.创建一个新的节点newNode(以4这个值创建)，值等于当前根节点的值
        Node newNode = new Node(this.value);
        //2.把新节点的左子树设置为当前节点的左子树
        newNode.left = this.left;
        //3.把新节点的右子树设置为当前节点的右子树的左子树
        newNode.right = this.right.left;
        //4.把当前节点的值换为右子节点的值
        this.value = this.right.value;
        //5.把当前节点的右子树设置为右子树的右子树
        this.right = this.right.right;
        //6.把当前节点的左子树设置为新节点
        this.left = newNode;
    }

    /**
     * 右旋转：降低左子树的高度
     */
    public void rightRotate(){
        //1.创建一个新的节点，值等于当前根节点的值
        Node newNode = new Node(this.value);
        //2.把新节点的右子树设置为当前节点的右子树
        newNode.right = this.right;
        //3.把新节点的左子树设置为当前节点的左子树的右子树
        newNode.left = this.left.right;
        //4.把当前节点的值替换为左子节点的值
        this.value = this.left.value;
        //5.把当前节点的左子树设置为左子树的左子树
        this.left = this.left.left;
        //6.把当前节点的右子树设置为新节点
        this.right = newNode;
    }

    /**
     * 增加节点的方法
     * @param node
     */
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
        //添加一个节点后，判断是否需要进行左旋转
        if(rightHeight()-leftHeight()>1){
            //如果右子树的左子树的高度大于右子树的右子树的高度【考虑到双旋问题】
            if(this.right!=null&&this.right.leftHeight()>this.right.rightHeight()){
                //先对右子树进行右旋转
                this.right.rightRotate();
            }
            //然后再对当前节点进行左旋转
            leftRotate();
            return;
        }
        //添加一个节点后，判断是否需要进行右旋转
        if(leftHeight()-rightHeight()>1){
            //如果左子树的右子树高度大于左子树的左子树的高度时【考虑到双旋问题】
            if(this.left!=null&&this.left.rightHeight()>this.left.leftHeight()){
                //先要对左子树进行左旋转
                this.left.leftRotate();
            }
            //然后再对当前节点进行右旋转
            rightRotate();
        }
    }

    /**
     * 前序遍历
     */
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
        //找到，直接返回
        if (this.value == value) {
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