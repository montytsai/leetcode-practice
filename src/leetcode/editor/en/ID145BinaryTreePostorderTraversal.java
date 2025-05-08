//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    /**
     * 使用「迭代 + 空指針標記法」實現後序遍歷
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        // 利用棧來彈出要處理的元素
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (node != null) {
                stack.pop();

                stack.push(node); // 中
                stack.push(null);
                if (node.right != null) stack.push(node.right); // 右
                if (node.left != null) stack.push(node.left); // 左
            } else {
                stack.pop(); // pop null
                node = stack.pop();
                result.add(node.val);
            }

        }
        return result;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

