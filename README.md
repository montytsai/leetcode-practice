# 🧠 LeetCode Practice with Java

紀錄我使用 Java 刷 LeetCode 題目的過程
專案使用 Maven 管理，包含單元測試，目標是熟悉演算法與資料結構實作

---
  
## 📦 專案資訊

- **Group ID**: `io.github.monty`
- **Artifact ID**: `leetcode-practice`
- **Version**: `1.0.0`
- **語言**：Java 8
- **建構工具**：Maven

---

## 專案結構

```
leetcode-practice/
├── README.md                                                    # 專案總覽與使用說明
├── pom.xml                                                      # Maven 設定檔（依賴管理、編譯與測試配置）
├── doc/
│   ├── leetcode-daily-challenge.md                              # LeetCode 每日挑戰刷題紀錄總覽
│   └── daily/                                                   # 存放每日解題日記（60 天挑戰）
│       └── day01-2025-04-18.md                                  # 每日刷題筆記，檔名含日期
├── src/
│   ├── leetcode/
│   │   └── editor/
│   │       └── en/                                              # 使用 IntelliJ [leetcode.editor] 外掛自動生成
│   │           ├── ID704_BinarySearch.java                      # 題目原始 Java 檔 (含題敘與預設範例)
│   │           └── doc/
│   │               └── content/
│   │                   └── ID704_BinarySearch.md                # 題目敘述 Markdown 檔，便於閱讀與註解
│   ├── main/
│   │   └── java/
│   │       └── io/
│   │           └── github/
│   │               └── monty/
│   │                   └── leetcode/
│   │                       ├── Main.java                        # 主程式入口（選用，可用於整體測試）
│   │                       └── array/                           # 題型分類
│   │                           └── ID704_BinarySearch.java      # 題目解法實作
│   └── test/
│       └── java/
│           └── io/
│               └── github/
│                   └── monty/
│                       └── leetcode/
│                           └── array/
│                               └── ID704_BinarySearchTest.java  # 題目對應單元測試（JUnit 撰寫）
```

### 命名與結構原則
- 題目皆以 `IDxxx_題名.java` 命名，方便對照 LeetCode 題號與查找
- 使用 IntelliJ [Leetcode Editor外掛](https://github.com/shuzijun/leetcode-editor) 產生題敘與範例程式碼
- 每題有： 
  - 外掛生成 LeetCode 執行檔（src/leetcode/editor/en/） 
  - 外掛生成 題目 Markdown 紀錄（src/leetcode/editor/en/doc/content/） 
  - 自行整理的解法（依主題歸類在 src/main/.../主題/） 
  - 對應的單元測試（src/test/.../主題/）

---

## ✅ 題目紀錄進度
紀錄於 [LeetCode 刷題紀錄](doc/leetcode-daily-challenge)

---

## 🚀 執行方式
確保已安裝 Java 8（或以上）與 Maven

### 🔨 編譯專案
```bash
mvn compile
```

### ▶️ 執行主程式
```bash
mvn exec:java -Dexec.mainClass="io.github.monty.leetcode.Main"
```

### 🧪 執行測試
```bash
mvn test
```

---

## 📝 備註
Hang in there and keep holding on!