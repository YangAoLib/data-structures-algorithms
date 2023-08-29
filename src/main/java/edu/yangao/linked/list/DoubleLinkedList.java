package edu.yangao.linked.list;

import java.util.Deque;

/**
 * 双向链表
 *
 * @author YangAo
 */
public class DoubleLinkedList implements LinkedList {

    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.testLinkedList();
    }

    public DoubleLinkedList() {
    }

    public DoubleLinkedList(Node head) {
        this.head = head;
    }

    /**
     * 头节点
     */
    private Node head = new Node();


    @Override
    public void list() {
        for (Node temp = head.next; temp != null; temp = temp.next)
            System.out.println(temp);
        System.out.println();
    }

    @Override
    public void add(Node node) {
        Node end = head;
        while (end.next != null)
            end = end.next;
        node.previous = end;
        end.next = node;
    }

    @Override
    public void addByOrderNum(Node node) {
        Node pre = head;
        while (pre.next != null && node.compareTo(pre.next) >= 0) {
            pre = pre.next;
        }
        node.next = pre.next;
        node.previous = pre;
        if (pre.next != null) pre.next.previous = node;
        pre.next = node;
    }

    @Override
    public void update(Node node) {
        Node pre = head.next;
        while (pre.next != null && !node.equals(pre)) {
            pre = pre.next;
        }
        node.previous = pre.previous;
        pre.previous.next = node;
        if (pre.next != null) pre.next.previous = node;
        node.next = pre.next;
    }

    @Override
    public void del(Node node) {
        Node cur = head.next;
        while (cur != null && !node.equals(cur))
            cur = cur.next;
        if (cur != null) {
            cur.previous.next = cur.next;
            if (cur.next != null) cur.next.previous = cur.previous;
        } else throw new RuntimeException("不存在对应节点");
    }

    @Override
    public void clear() {
        head.next = null;
    }

    @Override
    public Integer getLength() {
        int count = 0;
        for (Node temp = head.next; temp != null; temp = temp.next)
            ++count;
        return count;
    }

    @Override
    public void reverse() {
        Node cur = head.next;
        cur.previous = null;
        while (true) {
            Node tempNext = cur.next;
            cur.next = cur.previous;
            cur.previous = tempNext;
            if (tempNext != null) cur = tempNext;
            else break;
        }
        head.next = cur;
        cur.previous = head;
    }

    @Override
    public void reversePrint() {
        Deque<Node> stack = new java.util.LinkedList<>();
        for(Node cur = head.next; cur != null; cur = cur.next) {
            Node tempCur = (Node) cur.clone();
            tempCur.next = stack.peek();
            stack.push(tempCur);
        }
        Node head = new Node();
        Node pre = head;
        Node first = stack.poll();
        for (Node cur = first; cur != null; cur = stack.poll()) {
            cur.previous = pre;
            pre = cur;
        }
        head.next = first;
        new DoubleLinkedList(head).list();
    }
}
