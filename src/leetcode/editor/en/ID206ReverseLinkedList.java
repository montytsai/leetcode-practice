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
    public ListNode reverseList(ListNode head) {
        /**
         * 給一個單鏈表的頭節點，翻轉這個鏈表
         */
        ListNode prePrevToNext = null;
        ListNode curr = head;
        ListNode preNextToPrev = null;
        while (curr != null) {
            preNextToPrev = curr.next;
            curr.next = prePrevToNext;
            prePrevToNext = curr;

            curr = preNextToPrev; // 下一個節點
        }

        return prePrevToNext;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
