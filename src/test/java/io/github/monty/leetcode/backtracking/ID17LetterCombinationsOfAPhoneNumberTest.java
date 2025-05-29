package io.github.monty.leetcode.backtracking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ID17LetterCombinationsOfPhoneNumberTest {

    private ID17LetterCombinationsOfAPhoneNumber solution;

    @BeforeEach
    void setUp() {
        solution = new ID17LetterCombinationsOfAPhoneNumber();
    }

    @Test
    void testExample1() {
        String input = "23";
        List<String> expected = Arrays.asList(
                "ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"
        );
        List<String> actual = solution.letterCombinations(input);
        assertEquals(expected.size(), actual.size());
        assertTrue(actual.containsAll(expected));
    }

    @Test
    void testEmptyInput() {
        String input = "";
        List<String> expected = Collections.emptyList();
        List<String> actual = solution.letterCombinations(input);
        assertEquals(expected, actual);
    }

    @Test
    void testSingleDigit() {
        String input = "7";
        List<String> expected = Arrays.asList("p", "q", "r", "s");
        List<String> actual = solution.letterCombinations(input);
        assertEquals(expected.size(), actual.size());
        assertTrue(actual.containsAll(expected));
    }

    @Test
    void testLongInput() {
        String input = "234";
        List<String> actual = solution.letterCombinations(input);
        // 應該是 3×3×3 = 27 種組合
        assertEquals(27, actual.size());
    }

}