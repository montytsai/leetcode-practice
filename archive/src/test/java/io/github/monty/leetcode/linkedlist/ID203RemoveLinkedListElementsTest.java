package io.github.monty.leetcode.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ID203RemoveLinkedListElementsTest {

    private final ID203RemoveLinkedListElements solution = new ID203RemoveLinkedListElements();

    private ListNode buildList(int... vals) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int val : vals) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }

    private String listToString(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val).append("->");
            head = head.next;
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 2) : "";
    }

    @Test
    public void testRemoveElements_case1() {
        ListNode input = buildList(1, 2, 6, 3, 4, 5, 6);
        int valToRemove = 6;
        ListNode result = solution.removeElements(input, valToRemove);
        assertEquals("1->2->3->4->5", listToString(result));
    }

    @Test
    public void testRemoveElements_allRemoved() {
        ListNode input = buildList(7, 7, 7, 7);
        int valToRemove = 7;
        ListNode result = solution.removeElements(input, valToRemove);
        assertEquals("", listToString(result));
    }

    @Test
    public void testRemoveElements_noneRemoved() {
        ListNode input = buildList(1, 2, 3);
        int valToRemove = 4;
        ListNode result = solution.removeElements(input, valToRemove);
        assertEquals("1->2->3", listToString(result));
    }

    @Test
    public void testRemoveElements_headRemoved() {
        ListNode input = buildList(6, 1, 2, 3);
        int valToRemove = 6;
        ListNode result = solution.removeElements(input, valToRemove);
        assertEquals("1->2->3", listToString(result));
    }

    @Test
    public void testRemoveElements_tailRemoved() {
        ListNode input = buildList(1, 2, 6);
        int valToRemove = 6;
        ListNode result = solution.removeElements(input, valToRemove);
        assertEquals("1->2", listToString(result));
    }

    @Test
    public void testRemoveElements_emptyInput() {
        ListNode result = solution.removeElements(null, 1);
        assertEquals("", listToString(result));
    }

}
