package edu.yangao.linked.list;

import java.util.Deque;

/**
 * 单链表
 *
 * @author YangAo
 */
public class SingleLinkedList implements LinkedList {

    public static void main(String[] args) {
        new SingleLinkedList().testLinkedList();
    }

    /**
     * 头节点
     */
    private final Node head = new Node();

    @Override
    public void list() {
        // 从第一个节点开始遍历打印
        for (Node temp = head.next; temp != null; temp = temp.next) {
            System.out.println(temp.orderNum + ": " + temp.info);
        }
        System.out.println();
    }

    @Override
    public void add(Node node) {
        // 遍历到节点尾部
        Node end = head;
        while (end.next != null) {
            end = end.next;
        }
        // 在尾部连接插入节点
        end.next = node;
    }

    @Override
    public void addByOrderNum(Node node) {
        // 遍历节点 查找要插入节点的位置
        Node pre = head;
        Node cur = head.next;
            // 没有到结尾并且当前节点小于传入的节点
        while (cur != null && cur.compareTo(node) < 0) {
            pre = cur;
            cur = cur.next;
        }
        // 插入节点操作
        node.next = cur;
        pre.next = node;
    }

    @Override
    public void update(Node node) {
        // 遍历链表查询需要更新的节点
        Node pre = head;
        Node cur = head.next;
        while (cur != null && !cur.equals(node)) {
            pre = cur;
            cur = cur.next;
        }
        if (cur != null) {
            // 找到对应节点进行替换
            node.next = cur.next;
            pre.next = node;
        } else {
            // 未找到节点则抛出异常
            throw new RuntimeException("对应节点不存在");
        }
    }

    @Override
    public Node del(Node node) {
        // 查找需要删除的节点与前一个节点
        Node pre = head;
        Node cur = head.next;
        while (cur != null && !cur.equals(node)) {
            pre = cur;
            cur = cur.next;
        }
        // 找到节点则将其删除(将前一个节点的下一个节点指向当前节点的下一个节点)
        if (cur != null) pre.next = cur.next;
        else throw new RuntimeException("不存在对应节点");
        // 清空节点链接并返回
        return cur.clearLink();
    }

    @Override
    public void clear() {
        head.next = null;
    }


    @Override
    public Integer size() {
        // 遍历统计链表长度
        int count = 0;
        for (Node temp = head.next; temp != null; temp = temp.next) {
            ++count;
        }
        return count;
    }

    @Override
    public void reverse() {
        // 前一个节点初始为空
        Node pre = null;
        // 当前直接初始为第一个节点
        Node cur = head.next;
        while (cur != null) {
            // 缓存下一个节点的地址
            Node temp = cur.next;
            // 将当前的节点的下一个节点的地址指向前一个节点
            cur.next = pre;
            // 将两个节点的指向后移
            pre = cur;
            cur = temp;
        }
        head.next = pre;
    }

    @Override
    public void reversePrint() {
        // 使用一个栈将节点存储后再取出那么其就被反转了(因为栈的特性是 先进后出)
        Deque<Node> stack = new java.util.LinkedList<>();
        for (Node cur = head.next; cur != null; cur = cur.next) {
            stack.push(cur);
        }
        for (Node cur = stack.poll(); cur != null; cur = stack.poll()) {
            System.out.println(cur.orderNum + ": " + cur.info);
        }
    }
}
