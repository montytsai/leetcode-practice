package io.github.monty.leetcode.stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode #20:
 * <a href="https://leetcode.com/problems/valid-parentheses">Valid Parentheses</a>
 *
 * @author Monty.Tsai
 * @since 2025-05-03 21:52:06
 */
public class ID20ValidParentheses {

    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            switch (c) {
                case '(':
                    stack.push(')');
                    break;
                case '[':
                    stack.push(']');
                    break;
                case '{':
                    stack.push('}');
                    break;
                default:
                    // 如果遇到右括號但 stack 為空，或 pop 出來的括號與目前的不符，代表不合法
                    // (stack empty but pop 會 stack exception)
                    if (stack.isEmpty() || stack.pop() != c) return false;
            }
        }

        // 最後 stack 應該為空，代表所有括號都成功配對
        return stack.isEmpty();
    }

}
