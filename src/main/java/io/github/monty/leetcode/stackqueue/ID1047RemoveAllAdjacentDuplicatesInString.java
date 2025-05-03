package io.github.monty.leetcode.stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode #1047:
 * <a href="https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string">Remove All Adjacent Duplicates In String</a>
 *
 * @author Monty.Tsai
 * @since 2025-05-03 22:39:39
 */
public class ID1047RemoveAllAdjacentDuplicatesInString {

    public String removeDuplicates(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (stack.isEmpty() || stack.peek() != c) {
                stack.push(c);
            } else {
                stack.pop();
            }
        }

        // Stack 底為字串開頭，因此需反轉
        StringBuilder sb = new StringBuilder(stack.size());
        while (!stack.isEmpty()) {
            sb.append(stack.removeLast()); // Deque 為雙向佇列, removeLast() 運算為 O(1)
        }
        return sb.toString();
    }

}