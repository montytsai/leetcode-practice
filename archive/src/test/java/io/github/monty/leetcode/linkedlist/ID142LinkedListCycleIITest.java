package io.github.monty.leetcode.linkedlist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ID142LinkedListCycleIITest {

    private final ID142LinkedListCycleII solution = new ID142LinkedListCycleII();

    /**
     * Helper function to build a linked list with a cycle.
     * @param vals 節點值陣列
     * @param pos  cycle 起點在第幾個 index（從 0 開始），若為 -1 表示無環
     */
    private ListNode buildCyclicList(int[] vals, int pos) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        ListNode cycleStart = null;

        for (int i = 0; i < vals.length; i++) {
            curr.next = new ListNode(vals[i]);
            curr = curr.next;
            if (i == pos) {
                cycleStart = curr;
            }
        }

        if (pos != -1) {
            curr.next = cycleStart;
        }

        return dummy.next;
    }

    @Test
    void testDetectCycle_NoCycle() {
        ListNode head = buildCyclicList(new int[]{3, 2, 0, -4}, -1);
        assertNull(solution.detectCycle(head));
    }

    @Test
    void testDetectCycle_CycleAtHead() {
        ListNode head = buildCyclicList(new int[]{1, 2}, 0);
        assertEquals(head, solution.detectCycle(head));
    }

    @Test
    void testDetectCycle_CycleInMiddle() {
        ListNode head = buildCyclicList(new int[]{3, 2, 0, -4}, 1);
        ListNode expected = head.next; // node with value 2
        assertEquals(expected, solution.detectCycle(head));
    }

    @Test
    void testDetectCycle_SingleNodeWithCycle() {
        ListNode head = buildCyclicList(new int[]{1}, 0);
        assertEquals(head, solution.detectCycle(head));
    }

    @Test
    void testDetectCycle_SingleNodeWithoutCycle() {
        ListNode head = buildCyclicList(new int[]{1}, -1);
        assertNull(solution.detectCycle(head));
    }

}
