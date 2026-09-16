package io.github.monty.leetcode.linkedlist;

/**
 * LeetCode #19:
 * <a href="https://leetcode.com/problems/remove-nth-node-from-end-of-list">Remove Nth Node From End of List</a>
 * 19. 刪除鏈表的倒數第 N 個結點
 * 給一個鏈表，刪除鏈表的倒數第 n 個結點，並且返回鏈表的頭結點。
 *
 * @author Monty.Tsai
 * @since 2025-04-24 16:48:20
 */
public class ID19RemoveNthNodeFromEndOfList{

    /**
     * 虛擬頭節點 + 雙指針
     * 時間複雜度： O(n)，遍歷一次整條鏈表。
     * 空間複雜度： O(1)，只使用常數級指標變數。
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        // 設置虛擬頭節點
        ListNode dummyHead = new ListNode(-1, head);

        // 讓 slow 與 fast 指針相差 n 個節點
        int size = 0;
//        int step = 0; // for log
        ListNode fast = dummyHead;
        ListNode slow = dummyHead;
        while (fast.next != null) {
            fast = fast.next;
            if (++size > n) {
                slow = slow.next;
//                step++;
            }
        }
//        System.out.printf("fast In [%s], size(fast move): %s\n", fast.val, size);
//        System.out.printf("step In [%s], step(slow move): %s\n", slow.val, step);

        // slow 停在欲刪節點的前一個位置，刪除 slow 的下個節點
        if (slow.next != null) {
            slow.next = slow.next.next;
        }

        return dummyHead.next;
    }

    /**
     * 去除 size 變數
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode fast = dummy, slow = dummy;

        // fast 先走 n 步
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // fast 再與 slow 一起走，直到 fast 到末尾
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 刪除目標節點
        slow.next = slow.next.next;

        return dummy.next;
    }

}