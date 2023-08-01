package edu.yangao.queue;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * 用于测试队列的接口
 * @author YangAo
 */
public interface Queue {

    default void testQueue() {
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
                    this.show();
                    break;
                case 'a':
                    // 提示并获取用户的输入
                    System.out.print("请输入要添加的数字: ");
                    int value = scanner.nextInt();
                    this.add(value);
                    break;
                case 'g':
                    try {
                        System.out.printf("取出的队列头元素是%d\n", this.get());
                    } catch (NoSuchElementException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'p':
                    try {
                        System.out.printf("查看队列头元素是%d\n", this.peek());
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
     * 向队列中添加数据
     *
     * @param item 单个数据
     */
    void add(int item);

    /**
     * 判断队列是否满
     *
     * @return 队列是否满
     */
    boolean isFull();

    /**
     * 从队列头取出元素
     *
     * @return 队列头元素
     */
    int get();

    /**
     * 查看队列的头元素（不取出）
     *
     * @return 队列的头元素
     */
    int peek();

    /**
     * 判断队列是否为空
     *
     * @return 队列是否为空
     */
    boolean isEmpty();

    /**
     * 打印队列中的信息
     */
    void show();
}
