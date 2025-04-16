# 🧠 LeetCode Practice with Java

紀錄我使用 Java 刷 LeetCode 題目的過程，專案使用 Maven 管理，並包含單元測試。  
目標是熟悉演算法、資料結構。

---
  
## 📦 專案資訊

- **Group ID**: `io.github.monty`
- **Artifact ID**: `leetcode-practice`
- **Version**: `1.0.0`
- **語言**：Java 8
- **建構工具**：Maven

---

## 專案結構
依照題型分類，也會記錄我的解法與想法。
```
leetcode-practice/
├── README.md                   # 專案說明文件
├── pom.xml                     # Maven 設定檔
├── src/
│   ├── main/
│   │   └── java/
│   │       └── io/
│   │           └── github/
│   │               └── monty/
│   │                   └── leetcode/
│   │                       ├── Main.java             # 主程式
│   │                       └── array/                # 依照 topic 進行分類
│   │                           └── TwoSum.java       # 題目實作
│   └── test/
│       └── java/
│           └── io/
│               └── github/
│                   └── monty/
│                       └── leetcode/
│                           └── array/
│                               └── TwoSumTest.java   # 題目對應單元測試
```

---

## ✅ 題目紀錄進度
| 題號 | 題目名稱    | 分類    | 難度   | 狀態    | 解法連結                                                           |
|----|---------|-------|------|-------|----------------------------------------------------------------|
| 1  | Two Sum | Array | Easy | ✅ 已完成 | [解法](src/main/java/io/github/monty/leetcode/array/TwoSum.java) |
| 2  | ......  |

---

## 🚀 執行方式
確保已安裝 Java 8（或以上）與 Maven。

### 🔨 編譯專案
```bash
mvn compile
mvn exec:java -Dexec.mainClass="io.github.monty.leetcode.Main"
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
- 每個題目會放在對應資料夾中
- 題目檔案命名統一用駝峰式，例如：TwoSum.java
- 註解包含題目簡述與解法思路
- 附上對應的 JUnit 單元測試