package edu.yangao.queue;

import java.util.NoSuchElementException;

/**
 * 数组模拟队列
 *
 * @author YangAo
 */
public class ArrayQueue implements Queue {

    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        arrayQueue.testQueue();
    }

    /**
     * 初始化数组队列
     *
     * @param maxSize 数组大小
     */
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        // 创建数组
        arr = new int[maxSize];
    }

    /**
     * 向队列中添加数据
     *
     * @param item 单个数据
     */
    public void add(int item) {
        // 判断是否数组（队列）满
        if (isFull()) {
            // 如果队列满, 则打印说明, 并结束, 不进行元素的添加
            System.out.println("队列已满, 无法添加元素");
            return;
        }
        // 添加元素
        arr[++rear] = item;
    }

    /**
     * 判断队列是否满
     *
     * @return 队列是否满
     */
    public boolean isFull() {
        // 当队尾指向数组最后一个位置时, 队列就满了
        return rear == maxSize - 1;
    }

    /**
     * 从队列头取出元素
     *
     * @return 队列头元素
     */
    public int get() {
        // 判断是否数组（队列）已空
        if (isEmpty()) {
            // 如果数组为空, 则打印说明并抛出异常
            System.out.println("队列已空, 无法取出元素");
            throw new NoSuchElementException("队列中无元素");
        }
        // 取出数据
        return arr[++front];
    }

    /**
     * 查看队列的头元素（不取出）
     *
     * @return 队列的头元素
     */
    public int peek() {
        // 判断是否数组（队列）已空
        if (isEmpty()) {
            // 如果数组为空, 则打印说明并抛出异常
            System.out.println("队列已空, 无法查看元素");
            throw new NoSuchElementException("队列中无元素");
        }
        return arr[front + 1];
    }

    /**
     * 判断队列是否为空
     *
     * @return 队列是否为空
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 打印队列中的信息
     */
    public void show() {
        // 判断队列是否为空
        if (isEmpty()) {
            // 队列为空, 输出错误信息, 不进行打印操作
            System.out.println("队列已空, 无法打印");
        }
        for (int i = front + 1; i <= rear; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    // 队列头部, 指向当前队列头元素的前一个位置, 需后移只有才能取得元素
    private int front = -1;

    // 队列尾部, 指向当前队列尾元素, 需后移再进行元素的添加
    private int rear = -1;

    // 队列数据大小
    private final int maxSize;

    // 实际存储数据的数组引用
    private final int[] arr;


}

