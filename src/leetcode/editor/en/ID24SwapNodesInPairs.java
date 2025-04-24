//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    /**
     * 遞迴法
     */
    public ListNode swapPairs(ListNode head) {
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
}
//leetcode submit region end(Prohibit modification and deletion)