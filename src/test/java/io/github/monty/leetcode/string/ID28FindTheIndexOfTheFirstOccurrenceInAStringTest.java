package io.github.monty.leetcode.string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ID28FindTheIndexOfTheFirstOccurrenceInAStringTest {

    ID28FindTheIndexOfTheFirstOccurrenceInAString solution = new ID28FindTheIndexOfTheFirstOccurrenceInAString();

    @Test
    void testNormalCase() {
        // 測試 needle 存在於 haystack 中間
        assertEquals(2, solution.strStr("hello", "ll"));
    }

    @Test
    void testNeedleAtBeginning() {
        // needle 出現在開頭
        assertEquals(0, solution.strStr("abcdef", "abc"));
    }

    @Test
    void testNeedleAtEnd() {
        // needle 出現在結尾
        assertEquals(3, solution.strStr("abcdef", "def"));
    }

    @Test
    void testNeedleIsWholeHaystack() {
        // haystack 與 needle 完全一致
        assertEquals(0, solution.strStr("abc", "abc"));
    }

    @Test
    void testNeedleNotFound() {
        // needle 不存在於 haystack 中
        assertEquals(-1, solution.strStr("aaaaa", "bba"));
    }

    @Test
    void testEmptyNeedle() {
        // needle 為空字串時，依題意應回傳 0
        assertEquals(0, solution.strStr("abc", ""));
    }

    @Test
    void testEmptyHaystack() {
        // haystack 為空，needle 非空，必定找不到
        assertEquals(-1, solution.strStr("", "a"));
    }

    @Test
    void testBothEmpty() {
        // haystack 和 needle 都為空字串，依題意回傳 0
        assertEquals(0, solution.strStr("", ""));
    }

    @Test
    void testRepeatedPatterns() {
        // 多個匹配可能性，應返回最先出現的 index
        assertEquals(1, solution.strStr("aaabcaab", "aab"));
    }

    @Test
    void testLongNeedle() {
        // needle 長度大於 haystack，必定找不到
        assertEquals(-1, solution.strStr("short", "longerneedle"));
    }

}