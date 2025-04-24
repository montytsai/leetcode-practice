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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        // 設置虛擬頭節點
        ListNode dummyHead = new ListNode(-1, head);

        // 讓 slow 與 fast 指針相差 n 個節點
        int size = 0;
        ListNode fast = dummyHead;
        ListNode slow = dummyHead;
        while (fast.next != null) {
            fast = fast.next;
            if (++size > n) {
                slow = slow.next;
            }
        }

        // 刪除 slow 的下個節點
        if (slow.next != null) {
            slow.next = slow.next.next;
        }

        return dummyHead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)