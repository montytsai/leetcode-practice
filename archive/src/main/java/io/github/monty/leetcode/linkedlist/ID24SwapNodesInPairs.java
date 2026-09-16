package io.github.monty.leetcode.linkedlist;

/**
 * LeetCode #24:
 * <a href="https://leetcode.com/problems/swap-nodes-in-pairs">Swap Nodes in Pairs</a>
 * 兩兩交換鏈表中的節點
 * 給一個鏈表，兩兩交換其中相鄰的節點，並返回交換後鏈表的頭節點。
 * 必須在不修改節點內部的值的情況下完成本題（即，只能進行節點交換）。
 *
 * @author Monty.Tsai
 * @since 2025-04-24 20:37:07
 */
public class ID24SwapNodesInPairs{

    /**
     * #迴圈法
     * 時間複雜度：O(n)，每個節點最多被訪問一次。
     * 空間複雜度：O(1)，使用有限變數，不依賴額外空間。
     */
    public ListNode swapPairs1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;

        ListNode one = head;
        ListNode two = head.next;
        while (one != null && two != null) {
            ListNode nextOne = two.next;
            two.next = one;
            one.next = nextOne != null && nextOne.next != null ? nextOne.next : nextOne;

            one = nextOne;
            two = nextOne != null? nextOne.next : null;
        }

        return newHead;
    }

    /**
     * #遞迴法 (非典型、使用額外函式且是 void 回傳)
     * 時間複雜度：O(n)，每個節點仍最多被訪問一次。
     * 空間複雜度：O(n)（遞迴棧空間），最多會有 n/2 次遞迴呼叫，故為 O(n)。
     */
    public ListNode swapPair2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;

        swap(head, head.next);

        return newHead;
    }

    private void swap(ListNode one, ListNode two) {
        if (one == null || two == null) {
            return;
        }

        ListNode nextOne = two.next;
        two.next = one;
        one.next = nextOne != null && nextOne.next != null ? nextOne.next : nextOne;

        one = nextOne;
        two = nextOne != null? nextOne.next : null;

        swap(one, two);
    }

    /**
     * #遞迴法 (典型)
     * 時間複雜度：O(n)
     * 空間複雜度：O(n)
     */
    public ListNode swapPairs(ListNode head) {
        // Base case：空節點或只剩一個節點，不需要交換
        if (head == null || head.next == null) return head;

        // 新的頭節點
        ListNode newHead = head.next;

        // 下一組要處理的頭節點
        ListNode nextPairHead = head.next.next;
        // 把 head 的 next 指向下一組交換後的結果
        head.next = swapPairs(nextPairHead);
        // 把新頭的 next 指向 head（完成交換）
        newHead.next = head;

        return newHead;
    }

}