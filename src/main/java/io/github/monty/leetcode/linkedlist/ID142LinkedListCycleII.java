package io.github.monty.leetcode.linkedlist;

/**
 * LeetCode #142:
 * <a href="https://leetcode.com/problems/linked-list-cycle-ii">Linked List Cycle II</a>
 * 142. 環形鍊錶II
 * 給定一個鍊錶的頭節點 head，返回鍊錶開始入環的第一個節點。 如果鍊錶無環，則傳回 null。
 * 如果鍊錶中有某個節點，可以透過連續追蹤 next 指標再次到達，則鍊錶中存在環。
 * 為了表示給定鍊錶中的環，評測系統內部使用整數 pos 來表示鍊錶尾連接到鍊錶中的位置（索引從 0 開始）。
 * 如果 pos 是 -1，則在該鍊錶中沒有環。
 * 注意：pos 不作為參數進行傳遞，只是為了標識鍊錶的實際情況。
 * 不允許修改鍊錶。
 * 空間複雜度要求 O(1)。
 *
 * @author Monty.Tsai
 * @since 2025-04-25 12:08:45
 */
public class ID142LinkedListCycleII {

    /**
     * Floyd 判圈演算法 (龜兔賽跑演算法)：
     * 第一階段：判斷鏈表是否有環（fast、slow 指針相遇）
     * 第二階段：找出環的入口點
     *
     * 時間複雜度：O(n)，在最壞情況下，fast 和 slow 指針都需要遍歷鏈表的所有節點。
     * 空間複雜度：O(1)，只使用了常數空間來存儲指針。
     */
    public ListNode detectCycle(ListNode head) {
        // 0. 基礎判斷: 鏈表為空或只有一個節點時無環
        if (head == null || head.next == null) return null;

        // 1. 找相遇點（有環則一定會相遇）
        // 設置快慢指針，fast 每次走兩步，slow 每次走一步
        ListNode fast = head;
        ListNode slow = head;

        // 速率不同的快慢指針會在有環的情況下相遇
        while (fast != null && fast.next != null) { // fast = null 表示鏈表無環
            fast = fast.next.next; // fast 走兩步
            slow = slow.next; // slow 走一步

            // 當快慢指針相遇時，表示有環
            if (fast == slow) { // 找到相交點了
                // 2. 重新將 fast 指針放回頭節點，slow 保持在相交點
                //    一邊從頭節點出發，一邊從相交點出發，兩者會在入環點相遇
                fast = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow; // 找到入環處，直接返回(跳出迴圈)
            }
        }

        return null; // 無環的情況
    }

}