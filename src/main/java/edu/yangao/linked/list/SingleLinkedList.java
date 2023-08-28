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
        for (Node temp = head.next; temp != null; temp = temp.next) {
            System.out.println(temp.orderNum + ": " + temp.info);
        }
        System.out.println();
    }

    @Override
    public void add(Node node) {
        Node end = head;
        while (end.next != null) {
            end = end.next;
        }
        end.next = node;
    }

    @Override
    public void addByOrderNum(Node node) {
        Node pre = head;
        Node cur = head.next;
        while (cur != null && cur.compareTo(node) < 0) {
            pre = cur;
            cur = cur.next;
        }
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
    public void del(Node node) {
        Node pre = head;
        Node cur = head.next;
        while (cur != null && !cur.equals(node)) {
            pre = cur;
            cur = cur.next;
        }
        if (cur != null) pre.next = cur.next;
        else throw new RuntimeException("不存在对应节点");
    }

    @Override
    public void clear() {
        head.next = null;
    }


    @Override
    public Integer getLength() {
        int count = 0;
        for (Node temp = head.next; temp != null; ++count) {
            temp = temp.next;
        }
        return count;
    }

    @Override
    public void reverse() {
        Node pre = null;
        Node cur = head.next;
        while (cur != null) {
            Node temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        head.next = pre;
    }

    @Override
    public void reversePrint() {
        Deque<Node> stack = new java.util.LinkedList<>();
        for (Node cur = head.next; cur != null; cur = cur.next) {
            stack.push(cur);
        }
        for (Node cur = stack.poll(); cur != null; cur = stack.poll()) {
            System.out.println(cur.orderNum + ": " + cur.info);
        }
    }
}
