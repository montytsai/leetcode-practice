//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reverseWords(String s) {
        char[] arr = s.toCharArray();
        int sLen = arr.length;

        // 1. 去掉所有空格
        int readIdx = 0; // 讀取 index
        int writeIdx = 0; // 寫入(要移動到)的 index

        while (readIdx < sLen) {
            // 略過前置空格
            while (readIdx < sLen && arr[readIdx] == ' ') {
                readIdx++;
            }

            // 將單字逐字拷貝到新位置
            while (readIdx < sLen && arr[readIdx] != ' ') {
                arr[writeIdx++] = arr[readIdx++];
            }

            // 略過單字後空格，並補一個空格（若後面還有單字）
            while (readIdx < sLen && arr[readIdx] == ' ') {
                readIdx++;
            }
            if (readIdx < sLen) {
                arr[writeIdx++] = ' ';
            }
        }

        // 2. 反轉整個有效字串
        this.reverse(arr, 0, writeIdx - 1);

        // 3. 反轉每個單字
        int start = 0;
        while (start < writeIdx) {
            int end = start + 1;
            while (end < writeIdx && arr[end] != ' ') {
                end++;
            }
            this.reverse(arr, start, end - 1);
            start = end + 1;
        }

        return new String(arr, 0, writeIdx);
    }

    private void reverse(char[] arr, int start, int end) {
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