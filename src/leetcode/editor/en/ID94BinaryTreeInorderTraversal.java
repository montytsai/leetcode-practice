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

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        // 使用 stack 存放被訪問過的節點
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();

            if (node != null) {
                stack.pop(); // 把 node 先彈出，再照順序放回來

                // 加入右 node (空節點不入棧)
                if (node.right != null) stack.push(node.right);
                // 加入中 node
                stack.push(node);
                stack.push(null); // 使用空指針標記: 此 node 被訪問過，但是還沒有處理
                // 加入左 node (空節點不入棧)
                if (node.left != null)  stack.push(node.left);
            } else { // 遇到空指針，此指針被訪問過，可以放進結果集
                stack.pop(); // 彈出 null
                node = stack.pop(); // 彈出被標記的指針

                result.add(node.val); // 加入結果集
            }
        }

        return result;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

