package edu.yangao.stack;

import java.util.Scanner;

/**
 * 用数组来模拟栈
 */
public class ArrayStack<T> implements Stack<T> {

    public static void main(String[] args) {
        new ArrayStack<Integer>(3).test();
    }

    public ArrayStack(Integer maxSize) {
        this.maxSize = maxSize;
        this.top = -1;
        this.array = new Object[maxSize];
    }

    @Override
    public Boolean isFull() {
        return this.top == this.maxSize - 1;
    }

    @Override
    public Boolean isEmpty() {
        return this.top == -1;
    }

    @Override
    public void push(T value) {
        if (isFull()) throw new RuntimeException("栈已满");
        this.array[++this.top] = value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) throw new RuntimeException("栈已空");
        return (T)this.array[this.top--];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) throw new RuntimeException("栈已空");
        return (T)this.array[this.top];
    }

    @Override
    public void list() {
        System.out.print("栈中元素: ");
        for (int i = top; i > -1; --i) {
            System.out.print(this.array[i]);
            if (i != 0) System.out.print(", ");
        }
        System.out.println();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getValueFromScanner(Scanner scanner) {
        return (T)Integer.getInteger(scanner.next());
    }

    /**
     * 栈顶指向
     */
    private Integer top;

    /**
     * 数组大小(最多可以存储数据的个数)
     */
    private final Integer maxSize;

    /**
     * 存储数据的数组
     */
    private final Object[] array;
}
