package edu.yangao.queue;

import java.util.NoSuchElementException;

/**
 * 数组模拟循环队列
 *
 * @author YangAo
 */
public class CircleArrayQueue implements Queue {

    public static void main(String[] args) {
        CircleArrayQueue circleArrayQueue = new CircleArrayQueue(3);
        circleArrayQueue.testQueue();
    }

    /**
     * 初始化数组队列
     *
     * @param maxSize 数组大小
     */
    public CircleArrayQueue(int maxSize) {
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
        arr[rear] = item;
        // 后移队列尾部的下标
        rear = ++rear % maxSize;
    }

    /**
     * 判断队列是否满
     *
     * @return 队列是否满
     */
    public boolean isFull() {
        // 当队列尾部指向队列头部前一个位置时, 队列就满了
        return (rear + 1) % maxSize == front;
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
        // 取出数据并缓存
        int value = arr[front];
        // 修改队列头部位置
        front = ++front % maxSize;
        // 返回数据
        return value;
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
        return arr[front];
    }

    /**
     * 判断队列是否为空
     *
     * @return 队列是否为空
     */
    public boolean isEmpty() {
        // 头部指向当前元素, 尾部指向最后一个元素的后一个位置, 如果两个重合, 则证明当前队列为空
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
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    /**
     * 队列尾部, 指向当前队列最后一个元素的后一个位置, 在进行元素的添加后, 再进行后移
     * <p>因为没有元素, 所以队列的最后一个元素的位置为-1, 所以其后一个位置是0</p>
     */
    private int rear;

    /**
     * 队列头部, 指向当前队列第一个元素, 在取得元素后, 进行后移操作
     * <p>
     *     当前没有元素, 与队列尾部对齐
     * </p>
     */
    private int front = rear;


    /**
     * 用于存储队列的数组的大小
     * <p>循环队列需要一个空位做约定, 用于判断当前队列是满的还是空的</p>
     * <p>所以实际可存储的数据量会比数组小 1</p>
     */
    private final int maxSize;

    // 实际存储数据的数组引用
    private final int[] arr;


}

