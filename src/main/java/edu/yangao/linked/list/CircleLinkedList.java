package edu.yangao.linked.list;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 环型链表
 *
 * @author YangAo
 */
public class CircleLinkedList implements LinkedList {

    public static void main(String[] args) {
        CircleLinkedList circleLinkedList = new CircleLinkedList();
        circleLinkedList.testLinkedList();

        circleLinkedList.clear();
        Joseph joseph = circleLinkedList.new Joseph();
        joseph.addBoy(25);
        System.out.println(joseph.countBoy(1, 2));
        joseph.addBoy(25);
        System.out.println(joseph.countBoyNative(1, 2));
    }

    public CircleLinkedList() {
    }

    public CircleLinkedList(Node first) {
        this.first = first;
    }

    /**
     * 约瑟夫问题
     */
    public class Joseph {

        /**
         * 加入节点的数量
         * @param count 节点数量
         */
        public void addBoy(Integer count) {
            for(int i = 0; i < count; ++i) {
                Integer no = i + 1;
                add(new Node(no, no.toString()));
            }
        }

        /**
         * 计算出队顺序
         * @param startNo 从第几个开始数
         * @param countNum 每回数多少下
         * @return 出队编号的列表
         */
        public List<Integer> countBoy(Integer startNo, Integer countNum) {
            ArrayList<Integer> countNoList = new ArrayList<>();
            // 对输入进行校验
            // 队列为空 || 起点位置小于1 || 起点位置大于队列长度
            if (isEmpty()) {
                throw new RuntimeException("当前队列为空");
            } else if (startNo < 1){
                throw new RuntimeException("起点不能小于1");
            } else if (getLength() < startNo) {
                throw new RuntimeException("起点不能超过队列长度");
            }
            // 找到开始节点
            Node start = first;
            for(int i = 1; i < startNo; ++i) {
                start = start.next;
            }
            // 进行出队操作 (全部出队为止)
            while (!isEmpty()) {
                // 数数 countNum - 1 次
                for (int i = 1; i < countNum; ++i) {
                    start = start.next;
                }
                // 出队(删除节点)
                Node tempNext = start.next;
                Node del = del(start);
                countNoList.add(del.orderNum);
                start = tempNext;
            }
            // 出队顺序
            return countNoList;
        }

        public List<Integer> countBoyNative(Integer startNo, Integer countNum) {
            ArrayList<Integer> countNoList = new ArrayList<>();
            // 对输入进行校验
            // 队列为空 || 起点位置小于1 || 起点位置大于队列长度
            if (isEmpty()) {
                throw new RuntimeException("当前队列为空");
            } else if (startNo < 1){
                throw new RuntimeException("起点不能小于1");
            } else if (getLength() < startNo) {
                throw new RuntimeException("起点不能超过队列长度");
            }
            // 找到开始节点
            Node start = first;
            for(int i = 1; i < startNo; ++i) {
                start = start.next;
            }
            // 找到开始节点的前一个节点
            Node startPre = getPre(start);
            // 进行出队操作 (有一个节点以上就执行)
            while (startPre != start) {
                // 数数 countNum - 1 次
                for (int i = 1; i < countNum; ++i) {
                    startPre = start;
                    start = start.next;
                }
                // 出队(删除节点)
                startPre.next = start.next;
                countNoList.add(start.orderNum);
                start = start.next;
            }
            // 取出最后一个节点
            countNoList.add(start.orderNum);
            first = null;
            return countNoList;
        }
    }

    /**
     * 第一个节点
     */
    private Node first;

    @Override
    public void list() {
        // 遍历链表
        Node temp = first;
        while (temp != null) {
            System.out.println(temp.orderNum + ": " + temp.info);
            // 在节点访问完成后再判断是否为尾部节点
            if (temp.next == first) {
                break;
            }
            // 如果不是尾部节点 那么继续遍历
            temp = temp.next;
        }
        System.out.println();
    }

    @Override
    public void add(Node node) {
        // 找到链表尾部 (第一个节点的前一个节点)
        Node end = getPre(first);
        // 根据链表情况 设置添加节点的存储位置
        if (end == null) {
            // 链表为空 添加到第一个节点的位置
            first = node;
        } else {
            // 链表非空
            end.next = node;
        }
        // 因为是向尾部添加节点并保持环型结构 所以添加进来的节点的下一个节点指向第一个节点
        node.next = first;
    }

    @Override
    public void addByOrderNum(Node node) {
        // 寻找要添加节点的位置
        Node temp = first;
        while (temp != null && temp.compareTo(node) <= 0 && temp.next != first) {
            temp = temp.next;
        }
        // 根据链表的情况 将节点添加到不同的位置
        if (temp == null) {
            // 链表为空 添加到第一个节点的位置
            first = node;
            node.next = first;
        } else {
            // 寻找该节点的前一个节点(如果节点存在的话)
            Node pre = getPre(temp);
            // 如果要添加到链表的头部 那么就需要更新第一个节点指向的节点的位置
            if (temp == first && temp.compareTo(node) > 0) {
                // 更新第一个节点
                first = node;
            }
            // 添加节点
            node.next = pre.next;
            pre.next = node;
        }
    }

    @Override
    public void update(Node node) {
        // 寻找要添加节点的位置
        Node temp = first;
        while (temp != null && !temp.equals(node) && temp.next != first) {
            temp = temp.next;
        }
        // 寻找该节点的前一个节点(如果节点存在的话)
        if (temp == null || !temp.equals(node)) {
            throw new RuntimeException("对应更新节点不存在");
        }
        // 找到更新节点的前一个节点
        Node pre = getPre(temp);
        // 如果要更新链表的第一个节点 那么就需要更新第一个节点指向的节点的位置
        if (temp == first) {
            // 更新第一个节点
            first = node;
        }
        // 更新节点
        node.next = temp.next;
        pre.next = node;
    }

    @Override
    public Node del(Node node) {
        // 寻找要添加节点的位置
        Node temp = first;
        while (temp != null && !temp.equals(node) && temp.next != first) {
            temp = temp.next;
        }
        // 寻找该节点的前一个节点(如果节点存在的话)
        if (temp == null || !temp.equals(node)) {
            throw new RuntimeException("对应删除节点不存在");
        }
        // 找到要删除节点的前一个节点
        Node pre = getPre(temp);
        // 前一个节点要连接指向的下一个节点 (正常情况指向要被删除的节点的下一个节点)
        Node next = temp.next;
        // 要删除的是第一个节点
        if (temp == first) {
            if (first.next == first) {
                // 如果只有一个节点则直接清空
                first = null;
            } else {
                // 有多个节点则直接顺延
                first = first.next;
            }
            // 要删除第一个节点 删除节点的前一个节点直接连接到第一个节点
            next = first;
        }
        // 删除节点
        pre.next = next;
        // 清空节点连接并返回
        return temp.clearLink();
    }

    @Override
    public void clear() {
        first = null;
    }

    @Override
    public Integer getLength() {
        // 记数值
        Integer count = 0;
        // 遍历链表
        Node temp = first;
        while (temp != null) {
            count++;
            if (temp.next == first) {
                break;
            }
            temp = temp.next;
        }
        return count;
    }

    @Override
    public void reverse() {
        // 找到第一个节点的前一个节点
        Node pre = getPre(first);
        // 进行反转操作
        Node cur = first;
        while (cur != null) {
            Node temp = cur.next;
            cur.next = pre;
            pre = cur;
            if (temp == first) {
                break;
            }
            cur = temp;
        }
        first = pre;
    }

    @Override
    public void reversePrint() {
        Deque<Node> stack = new java.util.LinkedList<>();

        // 将链表克隆存入栈
        Node cur = first;
        while (cur != null) {
            stack.push(cur.clone());
            if (cur.next == first) {
                break;
            }
            cur = cur.next;
        }
        // 从栈中取出节点 并 设置对应的下一个节点
        // 新链表的第一个节点
        Node first = stack.poll();
        // 用于反转的前一个节点
        Node preReverse = first;
        // 用于反转的当前节点
        Node curReverse = stack.poll();
        // 栈中有两个及以上的节点才进行下一个节点的设置值
        while (curReverse != null) {
            // 设置前一个节点的下一个节点 为 取出的节点
            preReverse.next = curReverse;
            // 指向后移
            preReverse = curReverse;
            // 从栈中获取节点
            curReverse = stack.poll();
        }
        if (first != null) {
            preReverse.next = first;
        }
        new CircleLinkedList(first).list();
    }

    /**
     * 获取 cur 节点的前一个节点
     * @param cur 需要寻找前一个节点的节点
     * @return cur的前一个节点
     */
    private Node getPre(Node cur) {
        Node pre = cur;
        // 遍历到cur的前一个节点
        while (pre != null && pre.next != cur) {
            pre = pre.next;
        }
        return pre;
    }
}
