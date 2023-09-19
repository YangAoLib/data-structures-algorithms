package edu.yangao.linked.list;

import java.util.Deque;
import java.util.Optional;

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
        head = new Node();
    }

    public DoubleLinkedList(Node head) {
        this.head = Optional.ofNullable(head).orElse(new Node());
    }

    /**
     * 头节点
     */
    private final Node head;


    @Override
    public void list() {
        // 遍历打印
        for (Node temp = head.next; temp != null; temp = temp.next)
            System.out.println(temp);
        System.out.println();
    }

    @Override
    public void add(Node node) {
        // 寻找尾部节点
        Node end = head;
        while (end.next != null)
            end = end.next;
        // 将传入的前一个节点设置为尾节点
        node.previous = end;
        // 尾节点的后一个节点为传入的节点
        end.next = node;
    }

    @Override
    public void addByOrderNum(Node node) {
        // 寻找要添加的节点的前一个节点 (不直接使用当前节点进行比较是因为最后遍历的节点为null无法找到前一个节点, 导致无法挂载)
        Node pre = head;
        while (pre.next != null && node.compareTo(pre.next) >= 0) {
            pre = pre.next;
        }
        // pre → pre.next —— node → pre.next
        // 设置添加节点的下一个节点为前置节点的后一个节点
        node.next = pre.next;
        // null ← node —— pre ← node
        // 设置添加节点的前一个节点为前置节点
        node.previous = pre;
        // pre ← pre.next —— node ← pre.next
        // 如果有后置节点, 则将后置节点的前一个节点设置为添加节点
        if (pre.next != null) pre.next.previous = node;
        // pre → pre.next —— pre → node
        // 将前置节点的后一节点设置为添加节点
        pre.next = node;
    }

    @Override
    public void update(Node node) {
        // 寻找到要更新的节点的前一个节点
        Node pre = head;
        while (pre.next != null && !node.equals(pre.next)) {
            pre = pre.next;
        }
        if (pre.next == null) throw new RuntimeException("不存在对应节点");
        // 设置更新节点的前一个节点
        node.previous = pre;
        // 设置更新节点的后一个节点
        node.next = pre.next.next;
        // 如果有后置节点则设置后置节点的前一个节点为更新节点
        if (pre.next.next != null) pre.next.next.previous = node;
        // 设置前置节点的下一个节点为更新节点
        pre.next = node;
    }

    @Override
    public void del(Node node) {
        // 寻找要删除的节点
        Node cur = head.next;
        while (cur != null && !node.equals(cur))
            cur = cur.next;
        // 如果删除节点存在
        if (cur != null) {
            // 前置节点的下一个节点设置为删除节点的下一个节点
            cur.previous.next = cur.next;
            // 如果存在后置节点, 则将后置节点的前一个节点设置为前置节点
            if (cur.next != null) cur.next.previous = cur.previous;
        // 不存在删除节点则抛出异常
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
        // 如果链表为空则直接返回
        if (isEmpty()) return;
        // 获取第一个节点
        Node cur = head.next;
        // 第一个节点的前一个节点设空(因为反转后它是最后一个节点)
        cur.previous = null;
        while (true) {
            // 将每个节点的前一个节点和后一个节点交换
            Node tempNext = cur.next;
            cur.next = cur.previous;
            cur.previous = tempNext;
            // 如果下一个节点不为空则后移之后继续操作
            if (tempNext != null) cur = tempNext;
            // 否则退出保留(反转后链表的第一个节点的引用)
            else break;
        }
        // 将新链表连接到当前头节点上
        head.next = cur;
        // 将第一个节点的前一个节点指向头节点
        cur.previous = head;
    }

    @Override
    public void reversePrint() {
        // 将链表存储到栈中
        Deque<Node> stack = new java.util.LinkedList<>();
        for(Node cur = head.next; cur != null; cur = cur.next) {
            // 存储克隆后的节点 (防止影响之前的链表)
            Node tempCur = (Node) cur.clone();
            // 设置节点的下一个节点为栈顶节点
            tempCur.next = stack.peek();
            // 将节点存入栈
            stack.push(tempCur);
        }
        // 定义头节点
        Node head = new Node();
        Node pre = head;
        // 取出栈顶节点(它就是链表的第一个节点)
        Node first = stack.poll();
        for (Node cur = first; cur != null; cur = stack.poll()) {
            // 将取出的节点的前一个节点设置为上次取出的节点
            cur.previous = pre;
            pre = cur;
        }
        // 将第一个节点连接到头节点上
        head.next = first;
        new DoubleLinkedList(head).list();
    }
}
