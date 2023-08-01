package edu.yangao.queue;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * 数组模拟队列
 *
 * @author YangAo
 */
public class ArrayQueue {

    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);

        // 简易交互界面
        // 控制程序结束
        boolean loop = true;
        // 用于获取用户的输入
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 取出队列头部元素");
            System.out.println("p(peek): 查看队列头部元素");
            // 获取输入的首个字
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrayQueue.show();
                    break;
                case 'a':
                    // 提示并获取用户的输入
                    System.out.print("请输入要添加的数字: ");
                    int value = scanner.nextInt();
                    arrayQueue.add(value);
                    break;
                case 'g':
                    try {
                        System.out.printf("取出的队列头元素是%d\n", arrayQueue.get());
                    } catch (NoSuchElementException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'p':
                    try {
                        System.out.printf("查看队列头元素是%d\n", arrayQueue.peek());
                    } catch (NoSuchElementException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    // 关闭scanner
                    scanner.close();
                    // 结束循环
                    loop = false;
                    break;
                default:
                    System.out.println("错误的输入");
            }
        }
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
        arr[rear++] = item;
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
        // 取出数据
        return arr[front++];
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
        for (int i = front; i <= front + (rear + maxSize - front) % maxSize; i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    // 队列头部, 指向当前队列第一个元素, 在取得元素后, 进行后移操作
    private int front = -1;

    // 队列尾部, 指向当前队列最后一个元素的后一个位置, 在进行元素的添加后, 再进行后移
    private int rear = -1;

    // 队列数据大小
    private final int maxSize;

    // 实际存储数据的数组引用
    private final int[] arr;


}

