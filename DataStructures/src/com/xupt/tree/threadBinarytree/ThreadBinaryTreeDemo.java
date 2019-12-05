package com.xupt.tree.threadBinarytree;

/**
 * @author Wnlife
 * @create 2019-11-29 15:52
 * <p>
 * 线索化二叉树
 */
public class ThreadBinaryTreeDemo {

    public static void main(String[] args) {
        //测试一把中序线索二叉树的功能
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");
//        HeroNode node7 = new HeroNode(17, "ddpp");

        //二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node2.setParent(root);
        node3.setLeft(node6);
        node3.setParent(root);
        node4.setParent(node2);
        node5.setParent(node2);
        node6.setParent(node3);
//        node3.setRight(node7);
        //测试中序线索化
        ThreadBinaryTree tree = new ThreadBinaryTree(root);
//        tree.infexThreadedNodes();
//        HeroNode leftNode = node5.getLeft();
//        HeroNode rightNode = node5.getRight();
//        System.out.println("10号结点的前驱结点是 =" + leftNode); //3
//        System.out.println("10号结点的后继结点是=" + rightNode); //1
//        System.out.println("按照中序遍历，使用线索化的方式遍历 线索化二叉树");
//        tree.infixThreadedList();// 8, 3, 10, 1, 14, 6

        //测试前序线索化
//        tree.preThreadedNodes();
//        HeroNode leftNode = node4.getLeft();
//        HeroNode rightNode = node4.getRight();
//        System.out.println("8号结点的前驱结点是 =" + leftNode);//3
//        System.out.println("8号结点的后继结点是=" + rightNode);//10
//        System.out.println("按照前序遍历，使用线索化的方式遍历 线索化二叉树");
//        tree.preThreadedList();//1 3 8 10 6 14

        //测试后序线索化
        tree.postThreadedNodes();
        HeroNode leftNode = node6.getLeft();
        HeroNode rightNode = node6.getRight();
        System.out.println("14号结点的前驱结点是 =" + leftNode);//3
        System.out.println("14号结点的后继结点是=" + rightNode);//6
        System.out.println("按照后序遍历，使用线索化的方式遍历 线索化二叉树");
        tree.postThreadList();//8 10 3 14 6 1
    }

}


/**
 * 线索化二叉树
 */
class ThreadBinaryTree {

    //根节点
    private HeroNode root;

    //当前节点的前驱节点指针，在递归进行线索化时，pre总是保留前一个节点
    private HeroNode pre;

    public ThreadBinaryTree(HeroNode root) {
        this.root = root;
    }

    public void infexThreadedNodes() {
        infexThreadedNodes(root);
    }

    /**
     * 按照【中序遍历】的顺序对二叉树进行线索化的方法
     *
     * @param node 当前需要线索化的节点
     */
    public void infexThreadedNodes(HeroNode node) {
        //一、当前节点为空，则不要进行线索化
        if (node == null) {
            return;
        }
        //二、先线索化左子节点
        infexThreadedNodes(node.getLeft());

        //线索化当前节点
        //1.先处理当前节点的前驱节点
        if (node.getLeft() == null) {
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针类型，指向前驱节点
            node.setLeftType(1);
        }
        //2.处理后继节点
        if (pre != null && pre.getRight() == null) {
            //让前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改当前前驱节点的右指针类型
            pre.setRightType(1);
        }
        pre = node;//当前节点都是下一个前驱节点

        //三、线索化右子节点
        infexThreadedNodes(node.getRight());
    }

    /**
     * 按照【中序遍历】的方式进行线索化遍历二叉树
     */
    public void infixThreadedList() {
        HeroNode curNode = root;//定义一个变量，存储当前遍历的结点，从root开始
        while (curNode != null) {
            /**
             * 找到中序遍历线索化二叉树时的第一个节点，
             * 循环的找到leftType==1的节点，第一个找到的就是8节点，
             * 后面随着遍历而变化，因为当leftType==1，说明该节点是按照线索化处理后的有效节点
             */
            while (curNode.getLeftType() == 0) {
                curNode = curNode.getLeft();
            }
            //打印出当前这个节点
            System.out.println(curNode);
            //如果当前节点的都是指向的都是后继节点，就一直输出
            while (curNode.getRightType() == 1) {
                //获取当前节点的后继节点
                curNode = curNode.getRight();
                System.out.println(curNode);
            }
            //替换这个遍历的节点
            curNode = curNode.getRight();
        }
    }

    public void preThreadedNodes() {
        preThreadedNodes(root);
    }

    /**
     * 按照【前序遍历】的方式线索化二叉树
     *
     * @param node 当前需要线索化的节点
     */
    public void preThreadedNodes(HeroNode node) {
        //一、如果当前节点为空，则直接返回
        if (node == null) {
            return;
        }
        //二、先线索化当前节点
        //1.设置当前节点的前驱节点
        if (node.getLeft() == null) {
            //设置当前节点的前驱节点
            node.setLeft(pre);
            //设置当前节点的类型
            node.setLeftType(1);
        }
        //2.设置后继节点
        if (pre != null && pre.getRight() == null) {//1  3  8  10  6  14
            //设置上一个节点的后继节点
            pre.setRight(node);
            //设置后继节点的类型
            pre.setRightType(1);
        }
        //当前节点成为下一个节点的前驱节点
        pre = node;
        //三、线索化当前节点的左子树
        if (node.getLeftType() != 1) {
            preThreadedNodes(node.getLeft());
        }
        //四、线索化当前节点的右子树
        if (node.getRightType() != 1) {
            preThreadedNodes(node.getRight());
        }
    }

    /**
     * 按照【前序遍历】的方式遍历线索化二叉树
     */
    public void preThreadedList() {
        HeroNode curNode = root;//定义一个变量，存储当前遍历的结点，从root开始
        while (curNode != null) {
            //遇到先对其进行进行访问，再对其左子树进行遍历访问，直到找到最左的那个节点；
            while (curNode.getLeftType() == 0) {
                System.out.println(curNode);
                curNode = curNode.getLeft();
            }
            //再根据线索化的指向对其右子树进行遍历访问。
            System.out.println(curNode);
            curNode = curNode.getRight();
        }
    }

    public void postThreadedNodes() {
        postThreadedNodes(root);
    }

    /**
     * 按照【后序遍历】的方式线索化二叉树
     *
     * @param node 当前需要线索化的节点
     */
    public void postThreadedNodes(HeroNode node) {
        //一、如果当前节点为空，则直接返回
        if (node == null) {
            return;
        }
        //二、线索化当前节点的左子树
        postThreadedNodes(node.getLeft());
        //三、线索化当前节点的右子树
        postThreadedNodes(node.getRight());
        //四、先线索化当前节点
        //1.设置当前节点的前驱节点
        if (node.getLeft() == null) {
            //设置当前节点的前驱节点
            node.setLeft(pre);
            //设置当前节点的类型
            node.setLeftType(1);
        }
        //2.设置后继节点
        if (pre != null && pre.getRight() == null) {
            //设置上一个节点的后继节点
            pre.setRight(node);
            //设置后继节点的类型
            pre.setRightType(1);
        }
        //当前节点成为下一个节点的前驱节点
        pre = node;
    }

    /**
     * 按照【后序遍历】的方式遍历线索化二叉树
     */
    public void postThreadList() {
        HeroNode curNode = root;//设置一个临时变量，作为当前节点，从根节点开始
        while (curNode.getLeftType() == 0) {//找到最左边的开始节点
            curNode = curNode.getLeft();
        }
        HeroNode preNode=null;
        while (curNode != null) {
            //右边是线索
            if(curNode.getRightType()==1){
                System.out.println(curNode);
                preNode=curNode;
                curNode=curNode.getRight();
            }else {
                if(curNode.getRight()==preNode){//如果上个处理的节点是当前节点的右子节点
                    System.out.println(curNode);
                    if(curNode==root)
                        return;
                    preNode=curNode;
                    curNode=curNode.getParent();
                }else {//如果从左节点的进入则找到有右子树的最左节点
                    curNode=curNode.getRight();
                    while (curNode!=null&&curNode.getLeftType()==0){
                        curNode=curNode.getLeft();
                    }
                }
            }
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

    //这只父节点，在后序线索化遍历时使用
    private HeroNode parent;

    /**
     * 说明：
     * 1.如果leftType==0，表示是左子树，如果是1表示是前驱节点
     * 2.如果rightType==0，表示是右子树，如果是1表示是后继节点
     */
    private int leftType;
    private int rightType;

    public void setParent(HeroNode parent) {
        this.parent = parent;
    }

    public HeroNode getParent() {

        return parent;
    }
    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getLeftType() {

        return leftType;
    }

    public int getRightType() {
        return rightType;
    }

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
}