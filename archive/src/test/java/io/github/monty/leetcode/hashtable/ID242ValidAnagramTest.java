package io.github.monty.leetcode.hashtable;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ID242ValidAnagramTest {

    private final ID242ValidAnagram solution = new ID242ValidAnagram();

    @Test
    void givenSameLetters_whenIsAnagram_thenTrue() {
        assertTrue(solution.isAnagram("anagram", "nagaram"));
    }

    @Test
    void givenDifferentLetters_whenIsAnagram_thenFalse() {
        assertFalse(solution.isAnagram("rat", "car"));
    }

    @Test
    void givenDifferentLengths_whenIsAnagram_thenFalse() {
        assertFalse(solution.isAnagram("a", "ab"));
    }

    @Test
    void givenEmptyStrings_whenIsAnagram_thenTrue() {
        assertTrue(solution.isAnagram("", ""));
    }

    @Test
    void givenSameLettersDifferentCounts_whenIsAnagram_thenFalse() {
        assertFalse(solution.isAnagram("aabb", "ab"));
    }

    @Test
    void givenSingleLetterSame_whenIsAnagram_thenTrue() {
        assertTrue(solution.isAnagram("z", "z"));
    }

}