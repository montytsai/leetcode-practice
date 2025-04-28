//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public String reverseStr(String s, int k) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        // 每 2k 個字串處理一次邏輯
        char[] arr = s.toCharArray();
        for (int left = 0; left < arr.length; left += 2 * k) { // 處理字串的左界線(左閉)
            int right = Math.min(left + k - 1, arr.length - 1); // 處理字串的右界線(右閉)
            reverse(arr, left, right);
        }
        return new String(arr);
    }

    private void reverse(char[] arr, int start, int end) {
        if (arr.length <= 1 || start >= end) {
            return;
        }

        while (start < end) {
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            start++;
            end--;
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

