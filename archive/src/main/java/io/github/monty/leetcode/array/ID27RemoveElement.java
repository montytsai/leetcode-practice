package io.github.monty.leetcode.array;

/**
 * LeetCode #27:
 * <a href="https://leetcode.com/problems/remove-element">Remove Element</a>
 * <p>
 * 給定陣列 nums，移除陣列中所有與 target 相同的數值
 * 1. 取得所有與 target 不同的數值的個數 k
 * 2. 陣列中前 k 個 index 放與 target 不同的數值 (可亂序)
 *
 * @author Monty.Tsai
 * @since 2025-04-18 16:34:15
 */
public class ID27RemoveElement {

    /**
     * 快慢雙指針解: 若是 fast 指針值不等於 val，可以複製到前面元素(慢指針)
     * <p>
     * 耗時: 24:00
     * 時間複雜度: O(n)
     * 空間複雜度: O(1)
     */
    public int removeElement(int[] nums, int val) {
        int slow = 0; // 目的地指針: 指向下個「有效元素」要填入的位置 (放置非 val 的位置)

        for (int fast = 0; fast < nums.length; fast++) { // 掃描指針
            if (nums[fast] != val) { // 表示這個值要保留 → 複製到 nums[slow]
                nums[slow++] = nums[fast];
            }
        }

        return slow;
    }

    /**
     * 相向雙指針解 (用尾端值覆蓋相同值)
     * <p>
     * 耗時: 08:50
     * 時間複雜度: O(n)，其中 n 是 nums.length
     * 空間複雜度: O(1)，即 常數額外空間
     */
    public int removeElement2(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            if (nums[left] == val) {
                nums[left] = nums[right--];
            } else {
                left++;
            }
        }

        return left;
    }

    /**
     * 暴力解
     * <p>
     * 耗時: 25 mins，含閱讀題幹
     * 時間複雜度: O(n)
     * 空間複雜度: O(n)（不符合題目要求的 O(1)）
     */
    public int removeElementZ(int[] nums, int val) {
        int[] temp = new int[nums.length];
        int tempIndex = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                temp[tempIndex++] = nums[i];
            }
        }

        for (int i = 0; i < tempIndex; i++) {
            nums[i] = temp[i];
        }

        return tempIndex;
    }

}