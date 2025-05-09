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
│   ├── main/
│   │   └── java/
│   │       └── io/
│   │           └── github/
│   │               └── monty/
│   │                   └── leetcode/
│   │                       ├── Main.java                        # 主程式入口（選用，可用於整體測試）
│   │                       └── array/                           # 題型分類
│   │                           └── ID704BinarySearch.java       # 題目解法實作
│   └── test/
│       └── java/
│           └── io/
│               └── github/
│                   └── monty/
│                       └── leetcode/
│                           └── array/
│                               └── ID704BinarySearchTest.java  # 題目對應單元測試（JUnit 撰寫）
```

### 命名與結構原則

- 題目檔名皆採 `IDxxx題名.java`，方便對照 LeetCode 題號與尋找

- 每題包含：
  - 自行整理的解法（依主題分類於 `src/main/.../topic*/`）
  - 對應單元測試（位於 `src/test/.../topic*/`）

- `topic*` 分類包含：
  - `array/`
  - `binarytree/`
  - `hashtable/`
  - `linkedlist/`
  - `stackqueue/`
  - `string/`

- 使用 IntelliJ [Leetcode Editor外掛](https://github.com/shuzijun/leetcode-editor) 產生題敘與範例程式碼

---

## ✅ 題目紀錄進度
紀錄於 [LeetCode 刷題紀錄](doc/leetcode-daily-challenge.md)

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