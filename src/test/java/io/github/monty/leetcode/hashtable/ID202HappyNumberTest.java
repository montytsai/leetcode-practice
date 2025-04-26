package io.github.monty.leetcode.hashtable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * LeetCode #202 測試：快樂數 (Happy Number)
 * <p>
 * 測試說明：
 * - 驗證數字是否正確判斷為快樂數或非快樂數
 * - 包含正向案例與負向案例
 */
class ID202HappyNumberTest {

    // 建立待測物件
    private final ID202HappyNumber solution = new ID202HappyNumber();

    /**
     * 測試快樂數的正確案例
     * 1、19、7 都是快樂數
     */
    @Test
    void testIsHappy_trueCases() {
        assertTrue(solution.isHappy(1));   // 1 已經是快樂數，直接返回 true
        assertTrue(solution.isHappy(19));  // 19 -> 82 -> 68 -> 100 -> 1，最終會到 1
        assertTrue(solution.isHappy(7));   // 7 -> 49 -> 97 -> 130 -> 10 -> 1，最終也會到 1
    }

    /**
     * 測試非快樂數的案例
     * 2、4、20 都會進入循環，無法到達 1
     */
    @Test
    void testIsHappy_falseCases() {
        assertFalse(solution.isHappy(2));  // 2 -> 4 -> 16 -> 37 -> 58 -> 89 -> 145 -> 42 -> 20 -> 4 (循環)
        assertFalse(solution.isHappy(4));  // 4 -> 16 -> 37 -> 58 -> ... (同樣循環)
        assertFalse(solution.isHappy(20)); // 20 -> 4 -> 16 -> 37 -> 58 -> ... (也是循環)
    }

}