package io.github.monty.leetcode.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ID701InsertIntoABinarySearchTreeTest {

    private ID701InsertIntoABinarySearchTree solution;

    @BeforeEach
    public void setUp() {
        solution = new ID701InsertIntoABinarySearchTree();
    }

    @Test
    public void testInsertIntoBST_iterative() {
        // 測資一：插入值為 5，插入點為右子樹
        // 原始樹：
        //     4
        //    / \
        //   2   7
        //  / \
        // 1   3
        TreeNode root = TreeNode.of(4, 2, 7, 1, 3);
        TreeNode result = solution.insertIntoBST(root, 5);

        // 預期樹：
        //     4
        //    / \
        //   2   7
        //  / \  /
        // 1   3 5
        TreeNode expected = TreeNode.of(4, 2, 7, 1, 3, 5);
        assertTrue(TreeNode.isSameTree(expected, result));
    }

    @Test
    public void testInsertIntoBST_recursive() {
        // 測資二：插入空樹
        TreeNode root = null;
        TreeNode result = solution.insertIntoBSTRecursive(root, 10);

        TreeNode expected = new TreeNode(10);
        assertTrue(TreeNode.isSameTree(expected, result));
    }

    @Test
    public void testInsertIntoBST_leafRight() {
        // 測資三：右子節點插入
        // 原始樹：
        //   1
        TreeNode root = new TreeNode(1);
        TreeNode result = solution.insertIntoBSTRecursive(root, 2);

        // 預期樹：
        //   1
        //    \
        //     2
        TreeNode expected = TreeNode.of(1, null, 2);
        assertTrue(TreeNode.isSameTree(expected, result));
    }

}