package edu.yangao.stack;

import java.util.Scanner;

/**
 * 使用链表来实现模拟栈
 */
public class LinkedStack implements Stack<Integer> {

    public static void main(String[] args) {
        new LinkedStack().test();
    }

    public LinkedStack() {
        this.head = new Node(null, null);
    }

    @Override
    public Boolean isFull() {
        return false;
    }

    @Override
    public Boolean isEmpty() {
        return head.next == null;
    }

    @Override
    public void push(Integer value) {
        if (isFull()) throw new RuntimeException("栈已满");
        head.next = new Node(value, head.next);
    }

    @Override
    public Integer pop() {
        if (isEmpty()) throw new RuntimeException("栈已空");
        Integer data = head.next.data;
        head.next = head.next.next;
        return data;
    }

    @Override
    public Integer peek() {
        if (isEmpty()) throw new RuntimeException("栈已空");
        return head.next.data;
    }

    @Override
    public void list() {
        System.out.print("栈中元素: ");
        for (Node cur = head.next; cur != null; cur = cur.next) {
            System.out.print(cur.data);
            if (cur.next != null) System.out.print(", ");
        }
        System.out.println();
    }

    @Override
    public Integer getValueFromScanner(Scanner scanner) {
        return scanner.nextInt();
    }

    /**
     * 头节点
     */
    private final Node head;

    /**
     * 节点类
     */
    private static class Node {

        public Node(Integer data, Node next) {
            this.data = data;
            this.next = next;
        }

        /**
         * 节点数据
         */
        public Integer data;

        /**
         * 下一个节点
         */
        public Node next;
    }
}
