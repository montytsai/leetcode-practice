#!/bin/bash
# 告訴系統這個腳本要用 bash 來執行

# 使用方式：./new.sh 1 "Two Sum" array

# 將參數傳給變數
number=$1
title=$2
category=$3

# 格式化類別名：移除空格 & 變成駝峰(首字大寫)
className=$(echo "$title" | sed -E 's/[^a-zA-Z0-9]//g' | sed -E 's/(^| )(.)/\U\2/g')
# 定義主程式與測試的檔案路徑
dir="src/main/java/io/github/monty/leetcode/$category"
testdir="src/test/java/io/github/monty/leetcode/$category"
# 題目網址用小寫與 dash 格式
url=$(echo "$title" | tr '[:upper:]' '[:lower:]' | tr ' ' '-')
# 作者
author=Monty.Tsai
# 日期變數（格式為 yyyy-mm-dd）
today=$(date +%F)

# 建立指定的資料夾（如果不存在的話）。-p 表示會遞迴建立不存在的父資料夾。
mkdir -p "$dir"
mkdir -p "$testdir"

# 用 here document 的方式建立 Java 題目主檔案，路徑是 src/main/...，內容如下：
cat << EOF > "$dir/${className}.java"
package io.github.monty.leetcode.$category;

/**
 * LeetCode #$number: <a href="https://leetcode.com/problems/$url">$title</a>
 *
 * @author $author
 * @since $today
 */
public class ${className} {
    public int[] solution(int[] nums, int target) {
        // TODO: implement
        return new int[0];
    }
}
EOF

# 建立測試檔案，會放一個基本的 JUnit 測試模板。這裡使用 JUnit 5 (org.junit.jupiter.api)
cat << EOF > "$testdir/${className}Test.java"
package io.github.monty.leetcode.$category;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ${className}Test {

    @Test
    void testExample() {
        ${className} sol = new ${className}();
        assertArrayEquals(new int[]{0, 1}, sol.solution(new int[]{2, 7, 11, 15}, 9));
    }

}
EOF

echo "✅ 已建立 $category/$className 題目與測試檔案！"

# 建議的 commit message
echo ""
echo "👉 建議 commit message："
echo "feat: [#$number] $title add"
