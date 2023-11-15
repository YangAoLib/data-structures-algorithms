package edu.yangao.stack;

import java.util.Scanner;

/**
 * 栈
 */
public interface Stack<T> {

    /**
     * 当前栈是否满
     * @return 当前栈是否满
     */
    Boolean isFull();

    /**
     * 当前栈是否为空
     * @return 当前栈是否为空
     */
    Boolean isEmpty();

    /**
     * 入栈
     * @param value 存入值
     */
    void push(T value);

    /**
     * 出栈
     * @return 出栈元素
     */
    T pop();

    /**
     * 查看栈顶元素
     * @return 栈顶元素
     */
    T peek();

    /**
     * 遍历输出内容
     */
    void list();

    /**
     * 从输入获取值
     * @param scanner 输入
     * @return 获取的值
     */
    T getValueFromScanner(Scanner scanner);

    /**
     * 测试方法
     */
    default void test() {
        Scanner scanner = new Scanner(System.in);
        out:
        while (true) {
            System.out.println("show: 遍历显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 入栈");
            System.out.println("pop: 出栈");
            System.out.print("请输入你的选择: ");
            String key = scanner.next();
            switch (key) {
                case "show":
                    this.list();
                    break;
                case "push":
                    System.out.print("请输入入栈值: ");
                    T value = getValueFromScanner(scanner);
                    try {
                        this.push(value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "pop":
                    try {
                        System.out.println("出栈的数据是: " + this.pop());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    break out;
                default:
                    System.out.println("输入错误请重新输入~~~");
            }
        }
    }
}
