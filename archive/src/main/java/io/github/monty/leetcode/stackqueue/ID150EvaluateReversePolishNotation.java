package io.github.monty.leetcode.stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode #150:
 * <a href="https://leetcode.com/problems/evaluate-reverse-polish-notation">Evaluate Reverse Polish Notation</a>
 *
 * @author Monty.Tsai
 * @since 2025-05-03 23:09:37
 */
public class ID150EvaluateReversePolishNotation {

    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (String token : tokens) {
            // 如果是數字，轉為整數後儲存至 stack
            if (isNumeric(token)) {
                stack.push(Integer.parseInt(token));
                continue;
            }

            // stack 先進後出，要照運算符操作順序
            int two = stack.pop();
            int one = stack.pop();
            int count = 0;

            switch (token) {
                case "+":
                    count = one + two;
                    break;
                case "-":
                    count = one - two;
                    break;
                case "*":
                    count = one * two;
                    break;
                case "/":
                    count = one / two;
                    break;
                default:
                    // 題目輸入必為 +-*/或數字，若不正則判斷可在此加入數字 or 拋出異常
                    break;
            }
            stack.push(count);
        }

        // stack 最後的數字便是運算結果
        return stack.pop();
    }

    // 判斷字串是否為整數（支援負號），不支援小數
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+");
    }

}