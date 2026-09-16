package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ID257BinaryTreePathsTest {

    private ID257BinaryTreePaths solution;

    @BeforeEach
    void setUp() {
        solution = new ID257BinaryTreePaths();
    }

    /**
     * 測試空樹情況，應返回空列表
     */
    @Test
    void testEmptyTree() {
        TreeNode root = null;
        List<String> result = solution.binaryTreePaths(root);
        assertEquals(Collections.emptyList(), result);
    }

    /**
     * 測試單一節點的樹（僅根節點）
     * 樹結構：
     * 1
     * 輸出應為：["1"]
     */
    @Test
    void testSingleNode() {
        TreeNode root = TreeNode.of(1);
        List<String> result = solution.binaryTreePaths(root);
        assertEquals(Collections.singletonList("1"), result);
    }

    /**
     * 測試一般二元樹
     * 樹結構：
     *       1
     *      / \
     *     2   3
     *      \
     *       5
     * 預期輸出：["1->2->5", "1->3"]
     */
    @Test
    void testNormalTree() {
        TreeNode root = TreeNode.of(1, 2, 3, null, 5);
        List<String> result = solution.binaryTreePaths(root);
        List<String> expected = Arrays.asList("1->2->5", "1->3");
        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }

    /**
     * 測試只有左子樹
     * 樹結構：
     *       1
     *      /
     *     2
     *    /
     *   3
     * 輸出應為：["1->2->3"]
     */
    @Test
    void testOnlyLeftSubtree() {
        TreeNode root = TreeNode.of(1, 2, null, 3);
        List<String> result = solution.binaryTreePaths(root);
        assertEquals(Collections.singletonList("1->2->3"), result);
    }

    /**
     * 測試只有右子樹
     * 樹結構：
     *   1
     *    \
     *     2
     *      \
     *       3
     * 輸出應為：["1->2->3"]
     */
    @Test
    void testOnlyRightSubtree() {
        TreeNode root = TreeNode.of(1, null, 2, null, 3);
        List<String> result = solution.binaryTreePaths(root);
        assertEquals(Collections.singletonList("1->2->3"), result);
    }

}