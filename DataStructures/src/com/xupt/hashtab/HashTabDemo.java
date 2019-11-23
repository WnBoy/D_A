package com.xupt.hashtab;

import java.util.Scanner;

/**
 * @author Wnlife
 * @create 2019-11-21 19:25
 * <p>
 * 哈希表的实现
 */
public class HashTabDemo {

    public static void main(String[] args) {

        HashTab hashTab = new HashTab(7);
        Scanner in = new Scanner(System.in);
        String key = null;
        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("delete: 删除雇员");
            System.out.println("exit: 退出系统");
            key = in.next();
            switch (key) {
                case "add": {
                    System.out.println("请输入雇员的ID:");
                    int id = in.nextInt();
                    System.out.println("请输入雇员的姓名:");
                    String name = in.next();
                    //创建雇员
                    Employee employee = new Employee(id, name);
                    hashTab.add(employee);
                    break;
                }
                case "list": {
                    hashTab.list();
                    break;
                }
                case "find": {
                    System.out.println("请输入雇员的ID：");
                    int id = in.nextInt();
                    hashTab.findById(id);
                    break;
                }
                case "delete": {
                    System.out.println("请输入雇员的ID：");
                    int id = in.nextInt();
                    hashTab.deleteById(id);
                    break;
                }
                case "exit":
                    in.close();
                    System.exit(0);
            }

        }

    }

}

/**
 * 表示每个员工，也是链表中的一个节点
 */
class Employee {

    public int id;
    public String name;
    public Employee next;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
}


/**
 * 表示哈希表中的每个链表
 */
class EmployeeLinkedList {

    /*链表头指针，直接指向链表的第一个节点*/
    private Employee head;

    /**
     * 添加节点到链表
     * 说明：
     * 1.假定，当添加雇员时，id 是自增长，即id的分配总是从小到大
     * 因此我们将该雇员直接加入到本链表的最后即可
     *
     * @param employee 要添加的节点
     */
    public void add(Employee employee) {
        /*如果添加的是第一个雇员*/
        if (head == null) {
            head = employee;
        } else {
            /*如果添加的不是第一个雇员，将新雇员添加到链表的最后*/
            Employee temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = employee;
        }
    }

    /**
     * 遍历链表的雇员信息
     *
     * @param no 第几个链表，链表编号
     */
    public void list(int no) {

        if (head == null) {
            System.out.println("第 " + (no + 1) + " 链表为空");
            return;
        }
        Employee temp = head;
        System.out.printf("第%d条链表的信息为：", no + 1);
        while (temp != null) {
            System.out.printf(" id=%d name=%s\t", temp.id, temp.name);
            temp = temp.next;
        }
        System.out.println();
    }

    /**
     * 根据ID查找雇员
     *
     * @param id 要查找的雇员ID
     * @return 范围雇员的对象
     */
    public Employee findByID(int id) {
        if (head == null) {
            System.out.println("链表为空~~");
            return null;
        }
        Employee temp = head;
        while (temp != null) {
            if (temp.id == id) {
                return temp;
            }
            temp = temp.next;
        }
        /*没有找到*/
        return null;
    }

    /**
     * 根据员工的ID在哈希表里面删除员工
     *
     * @param id 员工的ID
     */
    public void deleteById(int id) {

        if (head == null) {
            System.out.println("这个员工不存在~~");
            return;
        }
        /*如果删除的是头节点*/
        if (head.id == id) {
            head = head.next;
            return;
        }
        /*如果删除的不是头结点*/
        Employee temp = head;
        while (temp.next != null) {
            if (temp.next.id == id) {
                temp.next = temp.next.next;
                System.out.println("Id为" + id + "的员工已经被删除~~");
                return;
            }
        }
        System.out.println("没有找到这个员工~~");
    }

}

/**
 * 创建哈希表管理多条链表
 */
class HashTab {

    /*链表数组*/
    private EmployeeLinkedList[] employeeLinkedLists;

    /*链表数组的大小*/
    int size;

    /*构造函数*/
    public HashTab(int size) {
        this.size = size;
        employeeLinkedLists = new EmployeeLinkedList[size];
        for (int i = 0; i < employeeLinkedLists.length; i++) {
            employeeLinkedLists[i] = new EmployeeLinkedList();
        }
    }

    /**
     * 根据员工的ID计算链表数组的索引值的方法
     *
     * @param id 员工的ID
     * @return 链表数组的索引值
     */
    public int getIndex(int id) {
        return id % size;
    }

    /*添加节点的方法*/
    public void add(Employee employee) {
        int index = getIndex(employee.id);
        employeeLinkedLists[index].add(employee);

    }

    /*遍历链表节点的方法*/
    public void list() {
        for (int i = 0; i < size; i++) {
            employeeLinkedLists[i].list(i);
        }
    }

    /**
     * 根据雇员的ID在哈希表中查找雇员
     *
     * @param id 雇员的Id
     */
    public void findById(int id) {
        int index = getIndex(id);
        Employee employee = employeeLinkedLists[index].findByID(id);
        if (employee != null) {
            System.out.printf("在链表%d中，雇员id = %d  name=%s \n", index + 1, employee.id,employee.name);
        } else {
            System.out.println("在哈希表中，没有找到该雇员~~");
        }

    }

    /**
     * 根据雇员的Id从哈希表中删除雇员
     *
     * @param id 要删除员工的ID
     */
    public void deleteById(int id) {
        int index = getIndex(id);
        employeeLinkedLists[index].deleteById(id);
    }

}