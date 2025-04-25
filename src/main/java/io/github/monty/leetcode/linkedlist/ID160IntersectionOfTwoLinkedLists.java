package io.github.monty.leetcode.linkedlist;

/**
 * LeetCode #160:
 * <a href="https://leetcode.com/problems/intersection-of-two-linked-lists">Intersection of Two Linked Lists</a>
 * 給兩個單鏈表的頭節點 headA 和 headB ，請找出並返回兩個單鏈表相交的起始節點。如果兩個鏈表不存在相交節點，返回 null 。
 * 整個鏈式結構中不存在環。
 * 注意，函數返回結果後，鏈表必須 保持其原始結構 。
 * runs in O(m + n) time and use only O(1) memory
 *
 * @author Monty.Tsai
 * @since 2025-04-24 22:30:32
 */
public class ID160IntersectionOfTwoLinkedLists {

    /**
     * 雙指針異步移動法：<br>
     * 先計算兩條鏈表長度差，讓較長鏈表先走差值步數，再齊頭同步前進。<br>
     * 重點是比較節點參考（reference）而非節點值（value），
     * 因為要找的是**相交的節點本身**，而非內容相同的節點。
     * <br><br>
     * 時間複雜度: O(n + m)<br>
     * 空間複雜度: O(1)
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        if (headA == headB) return headA;

        // 1. 求 A 鏈表大小 lenA
        int lenA = 0;
        ListNode currA = headA;
        while (currA != null) {
            lenA++;
            currA = currA.next;
        }
//        System.out.println("lenA = " + lenA);

        // 2. 求 B 鏈表大小 lenB
        int lenB = 0;
        ListNode currB = headB;
        while (currB != null) {
            lenB++;
            currB = currB.next;
        }
//        System.out.println("lenB = " + lenB);

        // 3. 對齊兩鏈表起點: 根據 A, B 鏈表大小求差，大的先走 |lenA-lenB| 步，讓兩邊起頭平等
        int gap = lenA - lenB;
        currA = headA;
        currB = headB;
        if (gap > 0) {
            for (int i = 0; i < gap; i++) {
                currA = currA.next;
            }
        } else {
            for (int i = gap; i < 0; i++) {
                currB = currB.next;
            }
        }
//        System.out.println("gap = " + gap);
//        System.out.printf("A in [%s]\n", currA.val);
//        System.out.printf("B in [%s]\n", currB.val);

        // 4. 齊頭後的兩鏈表一同步前進，直到找到相交節點或結束
        while (currA != currB && currA != null && currB != null) {
            currA = currA.next;
            currB = currB.next;
        }
//        System.out.printf("curr: %s, curr.val: \n", currA);

        return currA;
    }

    /**
     * #雙指針同步走法。<br>
     * 利用雙指針交錯行走，每個指針走過兩條鏈表後，若有交點會同時抵達，若沒有則會同時為 null。
     * 因為走過的步數都是 m + n，所以不需事先知道長度也能對齊。<br><br>
     *
     * 範例：<br>
     * 原鏈表 A: {A1, A2, C1, C2, C3}<br>
     * 原鏈表 B: {B1, B2, B3, C1, C2, C3}<br>
     * =><br>
     * 指針 A 走: {A1, A2, C1, C2, C3, B1, B2, B3, C1}<br>
     * 指針 B 走: {B1, B2, B3, C1, C2, C3, A1, A2, C1}<br>
     * 最終相遇於 C1。<br><br>
     *
     * 時間複雜度: O(n + m)<br>
     * 空間複雜度: O(1)
     */
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