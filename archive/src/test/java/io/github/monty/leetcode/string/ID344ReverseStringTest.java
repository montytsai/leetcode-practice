package io.github.monty.leetcode.string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ID344ReverseStringTest {

    private final ID344ReverseString solution = new ID344ReverseString();

    @Test
    void testReverseString_case1() {
        char[] input = {'h', 'e', 'l', 'l', 'o'};
        char[] expected = {'o', 'l', 'l', 'e', 'h'};
        solution.reverseString(input);
        assertArrayEquals(expected, input);
    }

    @Test
    void testReverseString_case2() {
        char[] input = {'H', 'a', 'n', 'n', 'a', 'h'};
        char[] expected = {'h', 'a', 'n', 'n', 'a', 'H'};
        solution.reverseString(input);
        assertArrayEquals(expected, input);
    }

    @Test
    void testReverseString_empty() {
        char[] input = {};
        char[] expected = {};
        solution.reverseString(input);
        assertArrayEquals(expected, input);
    }

    @Test
    void testReverseString_singleCharacter() {
        char[] input = {'a'};
        char[] expected = {'a'};
        solution.reverseString(input);
        assertArrayEquals(expected, input);
    }

}
