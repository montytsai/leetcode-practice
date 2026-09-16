# 🧠 LeetCode Practice with Java

> **已 sunset。** 這是 2025 年 4 月到 6 月的版本——Java 實作、Maven 專案、每日刷題筆記，
> 於 2025-06-17 停止新增。現行內容在 [repo 根目錄](../README.md)。
> 以下是當時的說明原文，未改寫；文內的相對路徑在本資料夾內仍然有效。

---

紀錄我使用 Java 刷 LeetCode 題目的過程。  
專案使用 Maven 管理，包含單元測試，目標是熟悉演算法與資料結構實作，並建立完整的題庫筆記與程式碼歸檔習慣。

---
  
## 📦 專案資訊

- **Group ID**: `io.github.monty`
- **Artifact ID**: `leetcode-practice`
- **Version**: `1.0.0`
- **語言**：Java 8
- **建構工具**：Maven

---

## 📁 專案結構

```
leetcode-practice/
 ├── README.md                                # 專案總覽與使用說明
 ├── pom.xml                                  # Maven 設定檔（依賴管理、編譯與測試組態）
 ├── doc/
 │   ├── leetcode-daily-challenge.md          # 刷題進度總覽
 │   └── daily/                               # 每日筆記區（命名格式：dayXX-YYYY-MM-DD.md）
 ├── src/
 │   ├── main/
 │   │   └── java/io/github/monty/leetcode/
 │   │       ├── Main.java                    # 主程式入口（選用）
 │   │       └── topic*/                      # 題目解法分類資料夾
 │   └── test/
 │       └── java/io/github/monty/leetcode/
 │           └── topic*/                      # 對應主題的 JUnit 單元測試
```

### 題目命名與分類原則

- 每道題目使用檔案命名格式：`IDxxx題名.java`（如 `ID704BinarySearch.java`），方便尋找與版本控管。
- 每題包含： 
  - 題目解法檔案（放於 `src/main/.../topic*/`）
  - 對應單元測試（放於 `src/test/.../topic*/`）

#### 題型分類（topic*）

| 資料夾             | 說明                        |
|-----------------|---------------------------|
| `array/`        | 陣列                        |
| `linkedlist/`   | 鏈結串列                      |
| `hashtable/`    | 雜湊表                       |
| `string/`       | 字串                        |
| `stackqueue/`   | 堆疊與佇列                     |
| `binarytree/`   | 二元樹                       |
| `backtracking/` | 回溯演算法                     |
| `greedy/`       | 貪婪演算法                     |
| `dp/`           | 動態規劃（Dynamic Programming） |
| `monotonic/`    | 單調資料結構（如單調棧／單調佇列）         |

---

## ✅ 題目紀錄進度

- 紀錄於 [LeetCode 刷題紀錄](doc/leetcode-daily-challenge.md)

---

## 🚀 執行方式
確保已安裝 Java 8（或以上）與 Maven

### 編譯專案
```bash
mvn compile
```

### 執行主程式（可選）
```bash
mvn exec:java -Dexec.mainClass="io.github.monty.leetcode.Main"
```

### 執行所有測試
```bash
mvn test
```

---

## 📚 資源

- 本專案刷題順序主要參考[《代碼隨想錄》](https://programmercarl.com/)，依其規劃題目順序與每日挑戰，並依個人理解加上分類、註解與筆記整理。

- 開發過程使用 IntelliJ Plugin [LeetCode Editor](https://github.com/shuzijun/leetcode-editor)，自動產生題目描述與樣板程式碼。

---

## 📝 備註
Hang in there and keep holding on!