package io.github.monty.leetcode.linkedlist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ID024SwapNodesInPairsTest {

    private final ID24SwapNodesInPairs solution = new ID24SwapNodesInPairs();

    private ListNode buildList(int... values) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int v : values) {
            curr.next = new ListNode(v);
            curr = curr.next;
        }
        return dummy.next;
    }

    private int[] listToArray(ListNode head) {
        int[] result = new int[100];
        int idx = 0;
        while (head != null) {
            result[idx++] = head.val;
            head = head.next;
        }
        int[] trimmed = new int[idx];
        System.arraycopy(result, 0, trimmed, 0, idx);
        return trimmed;
    }

    @Test
    void testSwapPairs_evenNumberOfNodes() {
        ListNode input = buildList(1, 2, 3, 4);
        ListNode result = solution.swapPairs(input);
        assertArrayEquals(new int[]{2, 1, 4, 3}, listToArray(result));
    }

    @Test
    void testSwapPairs_oddNumberOfNodes() {
        ListNode input = buildList(1, 2, 3);
        ListNode result = solution.swapPairs(input);
        assertArrayEquals(new int[]{2, 1, 3}, listToArray(result));
    }

    @Test
    void testSwapPairs_singleNode() {
        ListNode input = buildList(1);
        ListNode result = solution.swapPairs(input);
        assertArrayEquals(new int[]{1}, listToArray(result));
    }

    @Test
    void testSwapPairs_emptyList() {
        ListNode result = solution.swapPairs(null);
        assertArrayEquals(new int[]{}, listToArray(result));
    }

}
