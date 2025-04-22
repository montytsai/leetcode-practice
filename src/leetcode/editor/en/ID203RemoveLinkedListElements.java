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

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(0, head);

        ListNode node = dummyHead;
        while (node.next != null ) {
            // 檢查下個元素是否需要移除
            ListNode next = node.next;
            if (next.val == val) {
                node.next = next.next;
            } else { // 遍歷下個節點
                node = node.next;
            }
        }

        return dummyHead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)