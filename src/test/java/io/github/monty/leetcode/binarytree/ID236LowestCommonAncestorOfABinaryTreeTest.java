package io.github.monty.leetcode.binarytree;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ID236LowestCommonAncestorOfABinaryTreeTest {

    private ID236LowestCommonAncestorOfABinaryTree solution;

    @BeforeEach
    public void setUp() {
        solution = new ID236LowestCommonAncestorOfABinaryTree();
    }

    @Test
    public void testExample1() {
        // 建立測資二元樹：
        //         3
        //       /   \
        //      5     1
        //     / \   / \
        //    6  2  0   8
        //      / \
        //     7   4
        TreeNode root = TreeNode.of(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4);
        TreeNode p = root.left;          // 節點 5
        TreeNode q = root.right;         // 節點 1

        TreeNode lca = solution.lowestCommonAncestor(root, p, q);
        assertEquals(3, lca.val);
    }

    @Test
    public void testExample2() {
        // 同樣的樹
        //         3
        //       /   \
        //      5     1
        //     / \   / \
        //    6  2  0   8
        //      / \
        //     7   4
        TreeNode root = TreeNode.of(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4);
        TreeNode p = root.left;              // 節點 5
        TreeNode q = root.left.right.right;  // 節點 4

        TreeNode lca = solution.lowestCommonAncestor(root, p, q);
        assertEquals(5, lca.val);
    }

    @Test
    public void testExample3() {
        // 同樣的樹
        //         3
        //       /   \
        //      5     1
        //     / \   / \
        //    6  2  0   8
        //      / \
        //     7   4
        TreeNode root = TreeNode.of(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4);
        TreeNode p = root.left.right.left;   // 節點 7
        TreeNode q = root.left.right.right;  // 節點 4

        TreeNode lca = solution.lowestCommonAncestor(root, p, q);
        assertEquals(2, lca.val);
    }

    @Test
    public void testSingleNode() {
        TreeNode root = TreeNode.of(1);  // 單節點樹
        TreeNode p = root;
        TreeNode q = root;

        TreeNode lca = solution.lowestCommonAncestor(root, p, q);
        assertEquals(1, lca.val);
    }

    @Test
    public void testUnbalancedTree() {
        // 建立不平衡二元樹：
        //       1
        //        \
        //         2
        //          \
        //           3
        //            \
        //             4
        TreeNode root = TreeNode.of(1, null, 2, null, 3, null, 4);
        TreeNode p = root.right.right;   // 節點 3
        TreeNode q = root.right.right.right; // 節點 4

        TreeNode lca = solution.lowestCommonAncestor(root, p, q);
        assertEquals(3, lca.val);
    }

}