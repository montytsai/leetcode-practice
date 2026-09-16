package io.github.monty.leetcode.string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ID541ReverseStringIITest {

    private final ID541ReverseStringII solution = new ID541ReverseStringII();

    @Test
    void testReverseStr_case1() {
        String input = "abcdefg";
        int k = 2;
        String expected = "bacdfeg";
        assertEquals(expected, solution.reverseStr(input, k));
    }

    @Test
    void testReverseStr_case2() {
        String input = "abcd";
        int k = 2;
        String expected = "bacd";
        assertEquals(expected, solution.reverseStr(input, k));
    }

    @Test
    void testReverseStr_case3() {
        String input = "abc";
        int k = 4;
        String expected = "cba";
        assertEquals(expected, solution.reverseStr(input, k));
    }

    @Test
    void testReverseStr_empty() {
        String input = "";
        int k = 2;
        String expected = "";
        assertEquals(expected, solution.reverseStr(input, k));
    }

    @Test
    void testReverseStr_singleCharacter() {
        String input = "a";
        int k = 2;
        String expected = "a";
        assertEquals(expected, solution.reverseStr(input, k));
    }

    @Test
    void testReverseStr_exactMultipleOf2k() {
        String input = "abcdef";
        int k = 3;
        String expected = "cbadef";
        assertEquals(expected, solution.reverseStr(input, k));
    }

}
