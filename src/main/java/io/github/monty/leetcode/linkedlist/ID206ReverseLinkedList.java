package io.github.monty.leetcode.linkedlist;

/**
 * LeetCode #206:
 * <a href="https://leetcode.com/problems/reverse-linked-list">Reverse Linked List</a>
 *給一個單鏈表的頭節點，翻轉這個鏈表。
 *
 * @author Monty.Tsai
 * @since 2025-04-24 16:46:48
 */
public class ID206ReverseLinkedList {

    /**
     * #雙指針法(reverseListByTwoPointers)
     * 時間複雜度: O(n)
     * 空間複雜度: O(1)
     */
    public ListNode reverseListByTwoPointers(ListNode head) {
        ListNode prePrevToNext = null;
        ListNode curr = head;
        ListNode preNextToPrev = null;
        while (curr != null) {
            preNextToPrev = curr.next;
            curr.next = prePrevToNext;
            prePrevToNext = curr;

            curr = preNextToPrev; // 下一個節點
        }

        return prePrevToNext;
    }

    /**
     * # 遞迴法 (reverseListByRecursive)
     * 時間複雜度: O(n), 要遞迴處理鏈表的每個節點
     * 空間複雜度: O(n), 遞迴呼叫了 n 層棧空間
     */
    public ListNode reverseList(ListNode head) {
        return reverse(null, head);
    }

    private ListNode reverse(ListNode prePrevToNext, ListNode curr) {
        if (curr == null) {
            return prePrevToNext;
        }
        ListNode preNextToPrev = curr.next;
        curr.next = prePrevToNext;

        return reverse(curr, preNextToPrev);
    }

    /**
     * Leetcode: Definition for singly-linked list.
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}