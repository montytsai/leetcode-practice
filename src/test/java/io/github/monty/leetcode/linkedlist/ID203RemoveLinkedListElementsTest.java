package io.github.monty.leetcode.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ID203RemoveLinkedListElementsTest {

    private final ID203RemoveLinkedListElements solution = new ID203RemoveLinkedListElements();

    private ID203RemoveLinkedListElements.ListNode buildList(int... vals) {
        ID203RemoveLinkedListElements.ListNode dummy = new ID203RemoveLinkedListElements.ListNode(0);
        ID203RemoveLinkedListElements.ListNode current = dummy;
        for (int val : vals) {
            current.next = new ID203RemoveLinkedListElements.ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }

    private String listToString(ID203RemoveLinkedListElements.ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val).append("->");
            head = head.next;
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 2) : "";
    }

    @Test
    public void testRemoveElements_case1() {
        ID203RemoveLinkedListElements.ListNode input = buildList(1, 2, 6, 3, 4, 5, 6);
        int valToRemove = 6;
        ID203RemoveLinkedListElements.ListNode result = solution.removeElements(input, valToRemove);
        assertEquals("1->2->3->4->5", listToString(result));
    }

    @Test
    public void testRemoveElements_allRemoved() {
        ID203RemoveLinkedListElements.ListNode input = buildList(7, 7, 7, 7);
        int valToRemove = 7;
        ID203RemoveLinkedListElements.ListNode result = solution.removeElements(input, valToRemove);
        assertEquals("", listToString(result));
    }

    @Test
    public void testRemoveElements_noneRemoved() {
        ID203RemoveLinkedListElements.ListNode input = buildList(1, 2, 3);
        int valToRemove = 4;
        ID203RemoveLinkedListElements.ListNode result = solution.removeElements(input, valToRemove);
        assertEquals("1->2->3", listToString(result));
    }

    @Test
    public void testRemoveElements_headRemoved() {
        ID203RemoveLinkedListElements.ListNode input = buildList(6, 1, 2, 3);
        int valToRemove = 6;
        ID203RemoveLinkedListElements.ListNode result = solution.removeElements(input, valToRemove);
        assertEquals("1->2->3", listToString(result));
    }

    @Test
    public void testRemoveElements_tailRemoved() {
        ID203RemoveLinkedListElements.ListNode input = buildList(1, 2, 6);
        int valToRemove = 6;
        ID203RemoveLinkedListElements.ListNode result = solution.removeElements(input, valToRemove);
        assertEquals("1->2", listToString(result));
    }

    @Test
    public void testRemoveElements_emptyInput() {
        ID203RemoveLinkedListElements.ListNode result = solution.removeElements(null, 1);
        assertEquals("", listToString(result));
    }

}
