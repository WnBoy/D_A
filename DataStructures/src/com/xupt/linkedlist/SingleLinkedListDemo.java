package com.xupt.linkedlist;

/**
 * @author Wnlife
 * @create 2019-10-19 10:48
 * <p>
 * 单链表实例演示：使用带head头的单向链表实现-水浒英雄排行榜管理对人物的增删改查操作
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {

        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();

        //无序添加
        /*singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);*/

        //按照编号的顺序添加
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.show();

        System.out.println("修改后的链表~~");
        singleLinkedList.update(new HeroNode(3, "小卢", "玉麒麟@@"));
        singleLinkedList.show();

        System.out.println("删除后的节点~~");
        singleLinkedList.delete(4);
        singleLinkedList.delete(3);
        singleLinkedList.show();

        System.out.println("链表的长度为:" + singleLinkedList.getLength(singleLinkedList.getHead()));

        singleLinkedList.printLinkListByEnd(singleLinkedList.getHead());

    }
}

/**
 * 定义SingleLinkedList 管理英雄
 */
class SingleLinkedList {

    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加节点到单向链表
     * 思路，当不考虑编号顺序时
     * 1. 找到当前链表的最后节点
     * 2. 将最后这个节点的next 指向 新的节点
     */
    public void add(HeroNode heroNode) {

        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    /**
     * 第二种方式在添加英雄时，根据排名将英雄插入到指定位置
     * (如果有这个排名，则添加失败，并给出提示)
     */
    public void addByOrder(HeroNode heroNode) {

        //因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
        //因为单链表，因为我们找的temp 是位于 添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        while (temp.next != null) {

            if (temp.next.no > heroNode.no)//找到位置插入
                break;
            else if (temp.next.no == heroNode.no) {//要插入的编号存在
                System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.no);
                return;
            }
            temp = temp.next;
        }
        heroNode.next = temp.next;
        temp.next = heroNode;
    }

    /**
     * 修改链表：
     * 修改节点的信息, 根据no编号来修改，即no编号不能改.
     * 说明：根据 newHeroNode 的 no 来修改即可
     */
    public void update(HeroNode newHeroNode) {

        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空~~");
            return;
        }
        //找到要修改的节点，根据no编号
        HeroNode temp = head.next;
        while (temp != null) {
            if (temp.no == newHeroNode.no) {//找到要修改的节点
                temp.name = newHeroNode.name;
                temp.nickname = newHeroNode.nickname;
                return;
            }
            temp = temp.next;
        }
        //没有找导要修改的节点
        System.out.printf("没有找到 编号 %d 的节点，不能修改\n", newHeroNode.no);
    }

    /**
     * 删除节点：
     * 思路：
     * 1. head 不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
     * 2. 说明我们在比较时，是temp.next.no 和  需要删除的节点的no比较
     */
    public void delete(int no) {

        HeroNode temp = head;
        while (temp.next != null) {
            if (temp.next.no == no) {
                HeroNode tt = temp.next;
                temp.next = tt.next;
                tt = null;
                return;
            }
            temp = temp.next;
        }
        System.out.printf("要删除的 %d 节点不存在\n", no);
    }

    //输出链表的方法
    public void show() {

        if (head.next == null) {
            System.out.println("链表为空~~");
            return;
        }
        HeroNode temp = head.next;//从第二个节点开始输出
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //单链表的常见面试题有如下:

    //1.求单链表中有效节点的个数(如果是带头结点的链表，需求不统计头结点)
    public int getLength(HeroNode head) {

        if (head.next == null) {
            System.out.println("链表为空~~");
            return 0;
        }
        HeroNode cur = head.next;
        int length = 0;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;

    }

    //2.查找单链表中的倒数第k个结点
    public HeroNode findKthToTail(HeroNode head, int k) {

        HeroNode pAhead=head;
        HeroNode pBehind=null;
        for (int i = 0; i < k-1; i++) {
            pAhead=pBehind.next;
        }
        pBehind=head;
        while(pAhead!=null){
            pAhead=pAhead.next;
            pBehind=pBehind.next;
        }
        return pBehind;
    }

    //3.单链表的反转
    public HeroNode reverse(HeroNode head){

        HeroNode newHead=null;
        HeroNode pre=null;
        HeroNode cur=head;
        while(cur!=null){
            HeroNode next = cur.next;
            if(next==null)
                newHead=cur;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        return newHead;
    }

    //4.从尾到头打印单链表
    public void printLinkListByEnd(HeroNode head){

        if(head!=null){
            if(head.next!=null)
                printLinkListByEnd(head.next);

            System.out.println(head);
        }
    }

    //5.合并两个有序的单链表，合并之后的链表依然有序
    public HeroNode combinLinkedList(HeroNode head1,HeroNode head2){

        //空指针判断
        if (head1 == null) {
            return head2;
        }else if (head2 == null) {
            return head1;
        }
        //递归合并
        HeroNode combinHead=null;
        if(head1.no>head2.no){
            combinHead=head2;
            combinHead.next=combinLinkedList(head1,head2.next);
        }else{
            combinHead.next=head1;
            combinHead.next=combinLinkedList(head1.next,head2);
        }
        return combinHead;
    }
}


/**
 * 定义HeroNode，每个HeroNode对象就是一个节点
 */
class HeroNode {

    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
