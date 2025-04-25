//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        if (headA == headB) return headA;

        // 每個指針都會走完自己的鏈表，再走對方的鏈表。若有交點，兩指針會在同一節點相遇；若無交點，兩者皆為 null
        ListNode pointerA = headA; // pointerA 走 lenA + skipB 步
        ListNode pointerB = headB; // pointerB 走 lenB + skipA 步
        while (pointerA != pointerB) {
            // A 走一步，如果走到尾，換走 B 鏈表
            pointerA = (pointerA != null) ? pointerA.next : headB;
            // B 走一步，如果走到尾，換走 A 鏈表
            pointerB = (pointerB != null) ? pointerB.next : headA;
        }

        return pointerA;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
