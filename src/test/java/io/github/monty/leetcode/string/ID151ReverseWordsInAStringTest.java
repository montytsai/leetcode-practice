package io.github.monty.leetcode.string;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ID151ReverseWordsInAStringTest {

    ID151ReverseWordsInAString solution = new ID151ReverseWordsInAString();

    @Test
    void testExample1() {
        String s = "the sky is blue";
        String expected = "blue is sky the";
        assertEquals(expected, solution.reverseWords(s));
    }

    @Test
    void testExample2() {
        String s = "  hello world  ";
        String expected = "world hello";
        assertEquals(expected, solution.reverseWords(s));
    }

    @Test
    void testExample3() {
        String s = " a  good   example ";
        String expected = "example good a";
        assertEquals(expected, solution.reverseWords(s));
    }

    @Test
    void testEmptyString() {
        String s = " ";
        String expected = "";
        assertEquals(expected, solution.reverseWords(s));
    }

    @Test
    void testSingleWord() {
        String s = "single";
        String expected = "single";
        assertEquals(expected, solution.reverseWords(s));
    }

}