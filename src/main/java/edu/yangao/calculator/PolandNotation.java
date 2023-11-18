package edu.yangao.calculator;

import java.util.*;

/**
 * 逆波兰表达式
 */
public class PolandNotation {

    public static void main(String[] args) {
        String infixExpressionStr = "1.01+((2^3)*4)-5";
        // 中缀表达式
        List<String> infixExpressionList = parseInfixExpression(infixExpressionStr);
        // 后缀表达式
        List<String> suffixExpression = parseSuffixExpression(infixExpressionList);
        // 结果
        System.out.println(infixExpressionStr + " = " + cal(suffixExpression));
    }

    /**
     * 计算后缀表达式
     * @param suffixExpression 后缀表达式
     * @return 结果
     */
    public static double cal(List<String> suffixExpression) {
        // 数栈
        Deque<Double> stack = new LinkedList<>();
        // 遍历表达式
        for (String item : suffixExpression) {
            // 如果是数字 则 入栈
            if (isNumber(item)) {
                stack.push(Double.parseDouble(item));
            } else {
                // 如果是操作符 弹出两个数字进行计算
                stack.push(cal(stack.pop(), stack.pop(), item));
            }
        }
        // 最后栈中的就是结果
        return stack.pop();
    }


    /**
     * 将表达式转成中缀表达式
     *
     * @param expression 表达式
     * @return 中缀表达式
     */
    public static List<String> parseInfixExpression(String expression) {
        List<String> result = new ArrayList<>();
        // 遍历字符串
        StringBuilder number = new StringBuilder();
        for (char character : expression.toCharArray()) {
            if (Calculator.isOperate(character)) {
                if (!number.isEmpty()) {
                    // 将收集的数字存入结果中
                    result.add(number.toString());
                    number.setLength(0);
                }
                // 将符号存入
                result.add(String.valueOf(character));
            } else if (Calculator.isNumber(character) || !number.isEmpty() && character == '.') {
                // 如果是数字则进行收集整合
                number.append(character);
            }
        }
        // 存入最后的数字
        if (!number.isEmpty()) {
            // 将收集的数字存入结果中
            result.add(number.toString());
        }
        return result;
    }


    /**
     * 将中缀表达式转成后缀表达式
     *
     * @param infixExpression 中缀表达式
     * @return 后缀表达式
     */
    public static List<String> parseSuffixExpression(List<String> infixExpression) {
        // 结果
        List<String> result = new ArrayList<>();
        // 存储中间的操作符
        Deque<String> operateStack = new LinkedList<>();
        for (String item : infixExpression) {
            if(isNumber(item)) {
                // 如果是数字则直接存入结果
                result.add(item);
            } else if ("(".equals(item)) {
                // 如果是 ( 则直接入符号栈
                operateStack.push(item);
            } else if (")".equals(item)) {
                // 弹出符号栈中的操作符 直到 (
                String operate;
                while (!"(".equals(operate = operateStack.pop())) {
                    result.add(operate);
                }
            } else if (operateStack.isEmpty() || "(".equals(operateStack.peek())) {
                // 栈为空则操作符直接入栈
                operateStack.push(item);
            } else {
                // 如果栈顶操作符的优先级大于等于当前操作符优先级则将操作符存入结果
                while (!operateStack.isEmpty() && priority(operateStack.peek()) >= priority(item)) {
                    result.add(operateStack.pop());
                }
                // 将当前操作符入栈
                operateStack.push(item);
            }
        }
        // 将符号栈中剩余的操作符存入
        while (!operateStack.isEmpty()) {
            result.add(operateStack.pop());
        }
        return result;
    }

    /**
     * 操作符列表
     */
    private static final List<String> operateList = Arrays.asList("+", "-", "*", "/", "(", ")");
    /**
     * 优先级映射关系Map
     */
    private static final Map<String, Integer> operatePriorityMap = new HashMap<>(6);

    static {
        operatePriorityMap.put("+", 0);
        operatePriorityMap.put("-", 0);
        operatePriorityMap.put("*", 1);
        operatePriorityMap.put("/", 1);
        operatePriorityMap.put("^", 2);
    }

    /**
     * 获取操作符优先级
     *
     * @param operate 操作符
     * @return 优先级
     */
    private static int priority(String operate) {
        return operatePriorityMap.get(operate);
    }

    /**
     * 字符串是否为数字
     *
     * @param str 字符串
     * @return 是否为数字
     */
    public static boolean isNumber(String str) {
        return str.matches("\\d+(\\.\\d+)?");
    }

    /**
     * 字符串是否为操作符
     *
     * @param str 字符串
     * @return 是否为操作符
     */
    public static boolean isOperate(String str) {
        return operateList.contains(str);
    }

    /**
     * 计算
     *
     * @param end     算式第二个值
     * @param first   算式第一个值
     * @param operate 操作符
     * @return 结果
     */
    public static double cal(double end, double first, String operate) {
        return switch (operate) {
            case "+" -> first + end;
            case "-" -> first - end;
            case "*" -> first * end;
            case "/" -> first / end;
            case "^" -> Math.pow(first, end);
            default -> throw new RuntimeException("操作符异常" + operate);
        };
    }
}
