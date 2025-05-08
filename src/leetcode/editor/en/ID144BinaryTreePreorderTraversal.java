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

    /** 使用遞歸實現前序遍歷 */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    /**
     * 遞迴三大要素：
     * 1. 確定遞迴函數的參數和返回值
     * 2. 確定終止條件
     * 3. 確定單層遞迴的邏輯
     */
    // 1. 確定遞迴函數的參數和返回值:
    //    要印出前序遍歷 node 的 val，所以參數傳入 List<Integer> 來放印出結果
    //    除了這一點就不需要再處理什麼資料了也不需要有返回值，所以遞迴函數返回類型就是 void
    private void preorder(TreeNode cur, List<Integer> result) {
        // 2. 確定終止條件: 當前節點為空，直接 return 結束遞迴
        if (cur == null) {
            return;
        }
        // 3. 確定單層遞迴的邏輯: 前序遍歷是「中 → 左 → 右」的順序，所以在單層遞迴的邏輯，是要先取中節點的數值
        result.add(cur.val);         // 中
        preorder(cur.left, result);  // 左
        preorder(cur.right, result); // 右
    }

}
//leetcode submit region end(Prohibit modification and deletion)

