package edu.yangao.linked.list;

import java.util.Objects;

/**
 * @author YangAo
 */
public interface LinkedList {

    /**
     * 遍历显示链表
     */
    void list();

    /**
     * 向列表尾部添加节点
     *
     * @param node 节点信息
     */
    void add(Node node);

    /**
     * 按顺序向链表中添加节点
     *
     * @param node 节点信息
     */
    void addByOrderNum(Node node);

    /**
     * 更新节点信息
     *
     * @param node 节点信息
     */
    void update(Node node);

    /**
     * 删除节点
     *
     * @param node 节点信息
     */
    void del(Node node);

    /**
     * 清空链表
     */
    void clear();

    /**
     * 获取单链表中有效节点的个数
     *
     * @return 有效节点的个数
     */
    Integer getLength();

    /**
     * 反转链表
     */
    void reverse();

    /**
     * 反向打印
     */
    void reversePrint();

    /**
     * 是否为空
     */
    default Boolean isEmpty() {
        return getLength() == 0;
    }

    /**
     * 节点信息
     * 两个节点的orderNum相同则视为相同
     */
    class Node implements Comparable<Node> {

        /**
         * 排序号
         */
        public Integer orderNum;

        /**
         * 信息
         */
        public String info;

        /**
         * 下一个节点
         */
        public Node next;

        /**
         * 上一个节点
         */
        public Node previous;

        public Node() {
        }

        public Node(Integer orderNum, String info) {
            this.orderNum = orderNum;
            this.info = info;
        }

        @Override
        public int compareTo(Node o) {
            return o != null ? orderNum - o.orderNum : 1;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node other) {
                return Objects.equals(other.orderNum, orderNum);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return orderNum.hashCode();
        }

        @Override
        public String toString() {
            return "Node{" +
                    "orderNum=" + orderNum +
                    ", info='" + info + '\'' +
                    '}';
        }

        @Override
        protected Object clone() {
            Node clone = new Node();
            clone.previous = previous;
            clone.next = next;
            clone.orderNum = orderNum;
            clone.info = info;
            return clone;
        }
    }

    default void testLinkedList() {
        // 测试
        System.out.println(getClass().getSimpleName() + "链表测试");
        // 创建节点
        Node node1 = new Node(1, "宋江");
        Node node2 = new Node(2, "卢俊义");
        Node node3 = new Node(3, "吴用");
        Node node4 = new Node(4, "林冲");
        // 直接添加到尾部
        System.out.println("长度: " + getLength());
        System.out.println("直接添加到尾部");
        add(node1);
        add(node3);
        add(node4);
        add(node2);

        System.out.println("长度: " + getLength());
        list();

        // 按顺序添加
        clear();
        System.out.println("按顺序添加");
        addByOrderNum(node1);
        addByOrderNum(node3);
        addByOrderNum(node4);
        addByOrderNum(node2);

        list();

        // 修改
        Node newNode = new Node(4, "公孙胜");
        update(newNode);
        System.out.println("修改后的链表" + newNode);
        list();
        newNode = new Node(1, "测试");
        update(newNode);
        System.out.println("修改后的链表" + newNode);
        list();
        try {
            Node newNode1 = new Node(5, "公孙胜");
            update(newNode1);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println();
        }

        // 删除
        Node delNode = new Node(4, "公孙胜");
        del(delNode);
        System.out.println("删除后的链表" + delNode);
        list();
        Node delNode1 = new Node(1, "测试");
        del(delNode1);
        System.out.println("删除后的链表" + delNode1);
        list();
        try {
            Node newNode1 = new Node(5, "公孙胜");
            del(newNode1);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            System.out.println();
        }

        // 反转后
        reverse();
        System.out.println("反转后的链表");
        list();

        // 反向打印
        System.out.println("反向打印");
        reversePrint();
        System.out.println("原列表不变");
        list();
    }
}
