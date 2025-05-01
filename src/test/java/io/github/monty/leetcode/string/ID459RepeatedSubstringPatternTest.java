package io.github.monty.leetcode.string;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ID459RepeatedSubstringPatternTest {

    private final ID459RepeatedSubstringPattern solution = new ID459RepeatedSubstringPattern();

    @Nested
    class RepeatedSubstringPatternByConcatenation {

        @Test
        @DisplayName("符合重複模式：abab → true")
        void shouldReturnTrueForRepeatedPattern_abab() {
            assertTrue(solution.repeatedSubstringPattern("abab"));
        }

        @Test
        @DisplayName("單一字元重複：aaaa → true")
        void shouldReturnTrueForRepeatedPattern_aaaa() {
            assertTrue(solution.repeatedSubstringPattern("aaaa"));
        }

        @Test
        @DisplayName("不符重複規律：aba → false")
        void shouldReturnFalseForNonRepeatedPattern_aba() {
            assertFalse(solution.repeatedSubstringPattern("aba"));
        }

        @Test
        @DisplayName("結尾不同：aaab → false")
        void shouldReturnFalseForNonRepeatedPattern_aaab() {
            assertFalse(solution.repeatedSubstringPattern("aaab"));
        }

        @Test
        @DisplayName("長字串規律重複：abcabcabcabc → true")
        void shouldReturnTrueForLongRepeatedPattern() {
            assertTrue(solution.repeatedSubstringPattern("abcabcabcabc"));
        }

        @Test
        @DisplayName("單一字元：a → false")
        void shouldReturnFalseForSingleCharacter() {
            assertFalse(solution.repeatedSubstringPattern("a"));
        }

        @Test
        @DisplayName("空字串：→ false")
        void shouldReturnFalseForEmptyString() {
            assertFalse(solution.repeatedSubstringPattern(""));
        }

        @Test
        @DisplayName("重複長度為 3：xyzxyzxyz → true")
        void shouldReturnTrueForPattern_xyzxyzxyz() {
            assertTrue(solution.repeatedSubstringPattern("xyzxyzxyz"));
        }
    }

}