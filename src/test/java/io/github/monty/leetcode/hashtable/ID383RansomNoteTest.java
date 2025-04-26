package io.github.monty.leetcode.hashtable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ID383RansomNoteTest {

    private final ID383RansomNote solution = new ID383RansomNote();

    @Test
    void testCanConstruct_trueCase() {
        assertTrue(solution.canConstruct("aa", "aab"));
    }

    @Test
    void testCanConstruct_falseCase() {
        assertFalse(solution.canConstruct("aa", "ab"));
    }

    @Test
    void testCanConstruct_emptyRansomNote() {
        assertTrue(solution.canConstruct("", "anything"));
    }

    @Test
    void testCanConstruct_emptyMagazine() {
        assertFalse(solution.canConstruct("a", ""));
    }

    @Test
    void testCanConstruct_sameContent() {
        assertTrue(solution.canConstruct("abc", "abc"));
    }

    @Test
    void testCanConstruct_notEnoughLetters() {
        assertFalse(solution.canConstruct("aabbcc", "abc"));
    }

}