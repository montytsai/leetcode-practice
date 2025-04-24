package io.github.monty.leetcode.linkedlist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ID206ReverseLinkedListTest {

    private ID206ReverseLinkedList.ListNode buildList(int[] values) {
        ID206ReverseLinkedList.ListNode dummy = new ID206ReverseLinkedList.ListNode(0);
        ID206ReverseLinkedList.ListNode curr = dummy;
        for (int v : values) {
            curr.next = new ID206ReverseLinkedList.ListNode(v);
            curr = curr.next;
        }
        return dummy.next;
    }

    private int[] toArray(ID206ReverseLinkedList.ListNode head) {
        int size = 0;
        ID206ReverseLinkedList.ListNode node = head;
        while (node != null) {
            size++;
            node = node.next;
        }

        int[] result = new int[size];
        node = head;
        int i = 0;
        while (node != null) {
            result[i++] = node.val;
            node = node.next;
        }
        return result;
    }

    @Test
    void testReverseList_multipleNodes() {
        ID206ReverseLinkedList solution = new ID206ReverseLinkedList();
        ID206ReverseLinkedList.ListNode head = buildList(new int[]{1, 2, 3, 4, 5});
        ID206ReverseLinkedList.ListNode reversed = solution.reverseList(head);
        assertArrayEquals(new int[]{5, 4, 3, 2, 1}, toArray(reversed));
    }

    @Test
    void testReverseList_singleNode() {
        ID206ReverseLinkedList solution = new ID206ReverseLinkedList();
        ID206ReverseLinkedList.ListNode head = buildList(new int[]{42});
        ID206ReverseLinkedList.ListNode reversed = solution.reverseList(head);
        assertArrayEquals(new int[]{42}, toArray(reversed));
    }

    @Test
    void testReverseList_emptyList() {
        ID206ReverseLinkedList solution = new ID206ReverseLinkedList();
        ID206ReverseLinkedList.ListNode reversed = solution.reverseList(null);
        assertNull(reversed);
    }

}
