package com.xupt.linkedlist;

/**
 * @author Wnlife
 * @create 2019-10-20 16:16
 * <p>
 * 双向链表示例
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        // 测试
        System.out.println("双向链表的测试");
        // 先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");

        // 创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.show();

        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况");
        doubleLinkedList.show();

        // 删除
        doubleLinkedList.delete(4);
        System.out.println("删除后的链表情况~~");
        doubleLinkedList.show();
    }
}


/**
 * 创建双向链表
 */
class DoubleLinkedList {

    // 先初始化一个头节点, 头节点不要动, 不存放具体的数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    // 返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    //输出链表的方法
    public void show() {

        if (head.next == null) {
            System.out.println("链表为空~~");
            return;
        }
        HeroNode2 temp = head.next;//从第二个节点开始输出
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    // 添加一个节点到双向链表的最后.
    public void add(HeroNode2 heroNode) {

        HeroNode2 temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = heroNode;
        heroNode.pre=temp;
    }

    /**
     * 修改链表：
     * 修改节点的信息, 根据no编号来修改，即no编号不能改.
     * 说明：根据 newHeroNode 的 no 来修改即可
     */
    public void update(HeroNode2 newHeroNode) {

        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空~~");
            return;
        }
        //找到要修改的节点，根据no编号
        HeroNode2 temp = head.next;
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

        // 判断当前链表是否为空
        if (head.next == null) {// 空链表
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode2 temp = head.next;
        while (temp != null) {
            if (temp.no == no) {
                HeroNode2 tt = temp;
                temp.pre.next = temp.next;
                if (temp.next != null)
                    temp.next.pre = temp.pre;
                tt = null;
                return;
            }
            temp = temp.next;
        }
        System.out.printf("要删除的 %d 节点不存在\n", no);
    }

    /**
     * 第二种方式在添加英雄时，根据排名将英雄插入到指定位置
     * (如果有这个排名，则添加失败，并给出提示)
     */
    public void addByOrder(HeroNode2 heroNode) {

        //因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
        HeroNode2 temp = head.next;
        while (temp != null) {

            if (temp.no > heroNode.no)//找到位置插入
                break;
            else if (temp.no == heroNode.no) {//要插入的编号存在
                System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.no);
                return;
            }
            temp = temp.next;
        }
        temp.pre.next=heroNode;
        heroNode.pre=temp.pre;
        heroNode.next=temp;
        temp.pre=heroNode;

    }
}


/**
 * 定义HeroNode2，每个HeroNode对象就是一个节点
 */
class HeroNode2 {

    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}