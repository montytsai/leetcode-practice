//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int val = 1; // 矩陣中的值
        // 設定起始點到終點
        int left = 0, right = n - 1; // 0-4
        int top = 0, bottom = n - 1; // 0-4

        while (left <= right && top <= bottom) {
            // 從左至右
            for (int i = left; i < right; i++) { //0-3 1-2
                matrix[top][i] = val++;
            }
            // 從上至下
            for (int i = top; i < bottom; i++) { //0-3
                matrix[i][right] = val++;
            }
            // 從右至左
            for (int i = right; i > left; i--) { //4-1
                matrix[bottom][i] = val++;
            }
            // 從下至上
            for (int i = bottom; i > top; i--) { //4-1
                matrix[i][left] = val++;
            }

            top++;
            right--;
            bottom--;
            left++;
        }

        if (n % 2 == 1) {
            matrix[n/2][n/2] = val;
        }

        return matrix;
    }
}
//leetcode submit region end(Prohibit modification and deletion)