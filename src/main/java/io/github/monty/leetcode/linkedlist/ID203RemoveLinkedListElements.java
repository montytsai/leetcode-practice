package io.github.monty.leetcode.linkedlist;

/**
 * LeetCode #203:
 * <a href="https://leetcode.com/problems/remove-linked-list-elements">Remove Linked List Elements</a>
 * 給 linked list 的頭部 head 和整數 val，
 * 移除所有與 val 相同值的 node，回傳新的 head
 * Constraints:
 * The number of nodes in the list is in the range [0, 104].
 * 1 <= Node.val <= 50
 * 0 <= val <= 50
 *
 * @author Monty.Tsai
 * @since 2025-04-21 20:41:20
 */
public class ID203RemoveLinkedListElements {

    /**
     * 設置虛擬頭節點
     * 時間複雜度: O(n)
     * 空間複雜度: O(1)
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(0, head);

        ListNode node = dummyHead;
        while (node.next != null) {
            // 檢查下個元素是否需要移除
            if (node.next.val == val) {
                node.next = node.next.next;
            } else { // 遍歷下個節點
                node = node.next;
            }
        }

        return dummyHead.next;
    }

}

