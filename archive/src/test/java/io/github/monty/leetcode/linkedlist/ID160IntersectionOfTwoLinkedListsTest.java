package io.github.monty.leetcode.linkedlist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ID160IntersectionOfTwoLinkedListsTest {

    private final ID160IntersectionOfTwoLinkedLists solution = new ID160IntersectionOfTwoLinkedLists();

    @Test
    void testIntersectionAtNode() {
        // 公共交點節點
        ListNode intersection = new ListNode(8, new ListNode(10));

        // List A: 4 -> 1 -> 8 -> 10
        ListNode headA = new ListNode(4, new ListNode(1, intersection));

        // List B: 5 -> 0 -> 1 -> 8 -> 10
        ListNode headB = new ListNode(5, new ListNode(0, new ListNode(1, intersection)));

        ListNode result = solution.getIntersectionNode(headA, headB);
        assertSame(intersection, result);
    }

    @Test
    void testNoIntersection() {
        // List A: 2 -> 6 -> 4
        ListNode headA = new ListNode(2, new ListNode(6, new ListNode(4)));
        // List B: 1 -> 5
        ListNode headB = new ListNode(1, new ListNode(5));

        ListNode result = solution.getIntersectionNode(headA, headB);
        assertNull(result);
    }

    @Test
    void testSameHead() {
        ListNode shared = new ListNode(1, new ListNode(2));
        ListNode result = solution.getIntersectionNode(shared, shared);
        assertSame(shared, result);
    }

    @Test
    void testBothNull() {
        assertNull(solution.getIntersectionNode(null, null));
    }

    @Test
    void testOneNull() {
        ListNode headA = new ListNode(1);
        assertNull(solution.getIntersectionNode(headA, null));
        assertNull(solution.getIntersectionNode(null, headA));
    }

}
