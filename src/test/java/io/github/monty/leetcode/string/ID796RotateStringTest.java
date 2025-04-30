package io.github.monty.leetcode.string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ID796RotateStringTest {

    ID796RotateString solution = new ID796RotateString();

    @Test
    void testExample1() {
        // 範例測資：s 可以透過左旋轉變成 goal
        String s = "abcde";
        String goal = "cdeab";
        assertTrue(solution.rotateString(s, goal));
    }

    @Test
    void testExample2() {
        // 範例測資：s 無法透過旋轉變成 goal
        String s = "abcde";
        String goal = "abced";
        assertFalse(solution.rotateString(s, goal));
    }

    @Test
    void testEqualStrings() {
        // s 與 goal 完全相同，視為合法旋轉（0 次旋轉）
        String s = "a";
        String goal = "a";
        assertTrue(solution.rotateString(s, goal));
    }

    @Test
    void testEmptyStrings() {
        // 空字串的旋轉仍是空字串
        String s = "";
        String goal = "";
        assertTrue(solution.rotateString(s, goal));
    }

    @Test
    void testDifferentLengths() {
        // 長度不同，無論如何旋轉都不可能相等
        String s = "abc";
        String goal = "abcd";
        assertFalse(solution.rotateString(s, goal));
    }

    @Test
    void testRotationAtVariousPositions() {
        // 測試多個可能旋轉點
        assertTrue(solution.rotateString("abcde", "bcdea"));
        assertTrue(solution.rotateString("abcde", "deabc"));
        assertFalse(solution.rotateString("abcde", "aebcd"));
    }

    @Test
    void testWithRepeatingCharacters() {
        // 重複字元的旋轉需正確辨識
        String s = "aaab";
        String goal = "abaa";
        assertTrue(solution.rotateString(s, goal));
    }

    @Test
    void testAllSameCharacters() {
        // 所有字元相同的情況
        String s = "aaaa";
        String goal = "aaaa";
        assertTrue(solution.rotateString(s, goal));
    }

}