package com.xupt.tree;

/**
 * @author Wnlife
 * @create 2019-11-25 21:01
 * 二叉树
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {
        /*创建二叉树*/
        BinaryTree binaryTree = new BinaryTree();
        /*创建需要的结点*/
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        /*构建二叉树之间的关系*/
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);
        /*测试前序遍历: 1,2,3,5,4*/
        System.out.println("前序遍历~~");
        binaryTree.preOrder();
        /*测试中续遍历: 2,1,5,3,4*/
        System.out.println("中序遍历~~");
        binaryTree.infixOrder();
        /*测试后序遍历: 2,5,4,3,1*/
        System.out.println("后序遍历~~");
        binaryTree.postOrder();

        /*************************************************************/

        /**
         * 测试前序遍历查找
         * 前序遍历的次数 ：4
         */
//        System.out.println("前序遍历查找~~");
//        HeroNode heroNode1 = binaryTree.preOrderSearch(5);
//        System.out.println(heroNode1);

        /**
         * 测试中序遍历查找
         * 中序遍历的次数 ：3
         */
//        System.out.println("中序遍历查找~~");
//        HeroNode heroNode2 = binaryTree.infixOrderSearch(5);
//        System.out.println(heroNode2);

        /**
         * 测试后序遍历查找
         * 后序遍历的次数 ：2
         */
//        System.out.println("后序遍历查找~~");
//        HeroNode heroNode3 = binaryTree.postOrderSearch(5);
//        System.out.println(heroNode3);



        /*删除节点测试*/
        /*System.out.println("删除之前的前序遍历：");
        binaryTree.preOrder(); //1,2,3,5,4
        binaryTree.delNdoe(5);
        System.out.println("删除之后的前序遍历：");
        binaryTree.preOrder(); //1,2,3,4*/

    }
}

/**
 * 创建二叉树
 */
class BinaryTree {
    /*根节点*/
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 前序遍历查找
     *
     * @param no 要查找的编号
     * @return 查找到的结果，没有找到返回null
     */
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        }
        return null;
    }

    /**
     * 中序遍历查找
     *
     * @param no 要查找的编号
     * @return 查找到的结果，没有找到返回null
     */
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        }
        return null;
    }

    /**
     * 后续遍历查找
     *
     * @param no 要查找的编号
     * @return 查找到的结果，没有找到返回null
     */
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        }
        return null;
    }

    /**
     * 删除指定节点：
     * 1.如果删除的节点是叶子节点，则删除该节点
     * 2.如果删除的节点是非叶子节点，则删除该子树
     *
     * @param no 要删除节点的编号
     */
    public void delNdoe(int no){
        if(root!=null){ //判断根节点是否为空
            if(root.getNo()==no){ //如果根节点为要删除的节点，则删除根节点
                root=null;
            }else {
                root.delNode(no); //如果根节点不是要删除的节点，则递归的删除子树
            }
        }else { //如果根节点为空
            System.out.println("要删除的树是空树，无法删除~~");
        }
    }

}

/**
 * 创建HeroNode节点
 */
class HeroNode {

    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }


    /**
     * 删除节点：
     * 1.如果删除的节点是叶子节点，则删除该节点
     * 2.如果删除的节点是非叶子节点，则删除该子树
     *
     *  思路:
     *  1.如果左子节点不为空，则判断左子节点是不是要删除的节点，
     *    如果左子节点是要删除的节点，则删除左子节点，
     *    如果左子节点不是要删除的节点，则递归的删除左子树
     *  2.如果左子节点没有找到要删除的节点，则去右子节点找，
     *    如果右子节点是要删除的节点，则删除右子节点，
     *    如果右子节点不是要删除的节点，则递归的删除右子树
     *
     * @param no 要删除节点的编号
     */
    public void delNode(int no){

        if(this.left!=null){
            if(this.left.no==no){
                this.left=null;
                return;
            }
            this.left.delNode(no);
        }
        if(this.right!=null){
            if(this.right.no==no){
                this.right=null;
                return;
            }
            this.right.delNode(no);
        }
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        /*先输出当前节点*/
        System.out.println(this);
        /*左子节点不为空，递归遍历左子树*/
        if (this.left != null) {
            this.left.preOrder();
        }
        /*右子节点不为空，递归遍历右子树*/
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        /*左子节点不为空，递归遍历左子树*/
        if (this.left != null) {
            this.left.infixOrder();
        }
        /*输出当前节点*/
        System.out.println(this);
        /*右子节点不为空，递归遍历右子树*/
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 后续遍历
     */
    public void postOrder() {
        /*左子节点不为空，递归遍历左子树*/
        if (this.left != null) {
            this.left.postOrder();
        }
        /*右子节点不为空，递归遍历右子树*/
        if (this.right != null) {
            this.right.postOrder();
        }
        /*最后遍历当前节点*/
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     *
     * @param no 要查找的编号
     * @return 查找到的结果，没有找到返回null
     */
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序查找");
        /*首选判断当前节点的值和要查找的值是否相等*/
        if (this.no == no) {
            return this;
        }
        /**
         * 若当前节点的值不等于要查找的值，则判断左子节点是否为空，
         * 若不为空，则递归的遍历左子树，若找到则返回
         */
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
            if (resNode != null) {
                return resNode;
            }
        }
        /**
         * 若左子树没有找到，则判断右子节点是否为空，
         * 若不为空，则递归遍历右子树查找
         */
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 中序遍历查找
     *
     * @param no 要查找的编号
     * @return 查找到的结果，没有找到返回null
     */
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
        /*首先判断左子节点是否为空，若不为空，递归的遍历左子树,若找到则返回*/
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
            if (resNode != null) {
                return resNode;
            }
        }
        System.out.println("进入中序查找");
        /*若左子树没有找到，则判断当前节点是否满足，若满足则返回*/
        if (this.no == no) {
            return this;
        }
        /*若当前节点不满足，则递归的遍历右子树*/
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 后续遍历查找
     *
     * @param no 要查找的编号
     * @return 查找到的结果，没有找到返回null
     */
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        /*首先判断左子节点是否为空，若不为空，递归的遍历左子树,若找到则返回*/
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
            if (resNode != null) {
                return resNode;
            }
        }
        /*若左子树没有找到，则判断右子节点是否为空，不为空时递归遍历右子树*/
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
            if (resNode != null) {
                return resNode;
            }
        }
        System.out.println("进入后序查找");
        /*若右子树也没找到，则判断当前节点是否满足*/
        if (this.no == no) {
            return this;
        } else {
            return null;
        }
    }

}
