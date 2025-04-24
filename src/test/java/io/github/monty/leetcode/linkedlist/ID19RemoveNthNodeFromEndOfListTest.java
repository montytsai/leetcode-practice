package io.github.monty.leetcode.linkedlist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * LeetCode #19: Remove Nth Node From End of List
 * 測試類別
 */
class ID19RemoveNthNodeFromEndOfListTest {

    private final ID19RemoveNthNodeFromEndOfList solution = new ID19RemoveNthNodeFromEndOfList();

    private ListNode buildList(int... values) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int v : values) {
            curr.next = new ListNode(v);
            curr = curr.next;
        }
        return dummy.next;
    }

    private int[] toArray(ListNode head) {
        java.util.List<Integer> list = new java.util.ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        return list.stream().mapToInt(i -> i).toArray();
    }


    @Test
    void testRemoveNthFromEnd_middle() {
        ListNode head = buildList(1, 2, 3, 4, 5);
        ListNode result = solution.removeNthFromEnd(head, 2);
        assertArrayEquals(new int[]{1, 2, 3, 5}, toArray(result));
    }

    @Test
    void testRemoveNthFromEnd_singleNode() {
        ListNode head = buildList(1);
        ListNode result = solution.removeNthFromEnd(head, 1);
        assertNull(result);
    }

    @Test
    void testRemoveNthFromEnd_firstNode() {
        ListNode head = buildList(1, 2);
        ListNode result = solution.removeNthFromEnd(head, 2);
        assertArrayEquals(new int[]{2}, toArray(result));
    }

    @Test
    void testRemoveNthFromEnd_lastNode() {
        ListNode head = buildList(1, 2, 3);
        ListNode result = solution.removeNthFromEnd(head, 1);
        assertArrayEquals(new int[]{1, 2}, toArray(result));
    }

}
