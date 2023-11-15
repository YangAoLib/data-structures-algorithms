package edu.yangao.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calculator {

    public static void main(String[] args) {
        // 表达式
        String expression = "1+((2+3)*4)-5";
        // 数栈
        ArrayIntegerStack numberStack = new ArrayIntegerStack(10);
        // 操作符栈
        ArrayStack<Character> operateStack = new ArrayStack<>(10);
        // 遍历表达式
        StringBuilder number = new StringBuilder();
        for (int index = 0; index < expression.length(); ++index) {
            char c = expression.charAt(index);
            if (isOperate(c)) {
                if (!number.isEmpty()) {
                    numberStack.push(Integer.parseInt(number.toString()));
                    number.setLength(0);
                }
                if (!operateStack.isEmpty() && operateStack.peek() == '(') {
                    operateStack.push(c);
                    continue;
                }
                // 如果操作符栈为空 直接入栈 否则判断操作符的优先级判断操作
                if (!operateStack.isEmpty() && priority(c) <= priority(operateStack.peek()) || c == ')') {
                    // 计算值
                    numberStack.push(cal(numberStack.pop(), numberStack.pop(), operateStack.pop()));
                }
                operateStack.push(c);
                if (c == ')') {
                    operateStack.pop();
                    operateStack.pop();
                }
            } else if (isNumber(c)) {
                number.append(c);
            }
        }
        if (!number.isEmpty()) {
            numberStack.push(Integer.parseInt(number.toString()));
        }
        // 遍历剩余的操作符对数栈进行计算
        while (!operateStack.isEmpty()) {
            // 计算值
            numberStack.push(cal(numberStack.pop(), numberStack.pop(), operateStack.pop()));
        }
        System.out.println(expression + '=' + numberStack.pop());
    }

    /**
     * 操作符列表
     */
    private static final List<Character> operateList = Arrays.asList('+', '-', '*', '/', '(', ')');
    /**
     * 优先级映射关系Map
     */
    private static final Map<Character, Integer> operatePriorityMap = new HashMap<>(6);

    static {
        operatePriorityMap.put('+', 0);
        operatePriorityMap.put('-', 0);
        operatePriorityMap.put('*', 1);
        operatePriorityMap.put('/', 1);
        operatePriorityMap.put('(', 2);
        operatePriorityMap.put(')', 2);
    }

    /**
     * 获取操作符优先级
     *
     * @param operate 操作符
     * @return 优先级
     */
    private static int priority(char operate) {
        return operatePriorityMap.get(operate);
    }

    /**
     * 字符是否为数字
     *
     * @param character 字符
     * @return 是否为数字
     */
    private static boolean isNumber(char character) {
        return character >= '0' && character <= '9';
    }

    /**
     * 字符是否为操作符
     *
     * @param character 字符
     * @return 是否为操作符
     */
    private static boolean isOperate(char character) {
        return operateList.contains(character);
    }

    /**
     * 计算
     *
     * @param end     算式第二个值
     * @param first   算式第一个值
     * @param operate 操作符
     * @return 结果
     */
    private static int cal(int end, int first, char operate) {
        switch (operate) {
            case '+':
                return first + end;
            case '-':
                return first - end;
            case '*':
                return first * end;
            case '/':
                return first / end;
            default:
                throw new RuntimeException("操作符异常" + operate);
        }
    }
}
