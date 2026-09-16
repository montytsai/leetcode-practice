# LeetCode 題解格式正本

本頁是 `solutions/<NN-主題>/lc-*.md` 的格式正本。新題解直接套用；既有題解的 frontmatter 已統一，正文結構在下次實質更新時再遷移，不批次改寫。

- 一題一檔，但同題的不同 AC 解法全部並列保留。
- 每個解法都是完整單位：Intuition、Approach、Complexity、Code、Code Review。
- 只有同一解法的修正／改良，或我明確要求移除時，才替換既有版本。
- 只有一個解法時仍使用「解法一」；有多個解法時，檔尾必須加入比較表。
- 題目說明只摘要條件與目標，不複製完整題目敘述。

> 可直接複製的骨架在 [`solutions/_template.md`](solutions/_template.md)。
> 新增題解時複製那一份，本頁只管規格與寫作口徑。
> 骨架裡的相對路徑（`../../_moc.md` 這類）是以題解的位置 `solutions/<NN-主題>/` 為準，
> 在範本檔自己身上不成立，複製到目標資料夾後才會是對的。

---

## Frontmatter

| 欄位 | 型別 | 意思 | 值 |
| --- | --- | --- | --- |
| `title` | Text | 題名 | |
| `difficulty` | Text | LeetCode 官方難度 | `Easy` ｜ `Medium` ｜ `Hard` |
| `topics` | List | LeetCode 官方 tag，第一個是主要主題 | |
| `category` | Text | 這題住哪個資料夾，決定檔案位置 | `01-arrays-hashing` … `21-math-geometry` |
| `order` | Number | 該分類內的排序。新題取末尾的下一號，既有的不重新編號 | 純數字 |
| `source` | List | 題單來源，可多選、不重複 | `Carl` ｜ `Grind75` ｜ `LeetCode75` ｜ `Contest` ｜ `Extra` |
| `platform` | Text | 解題平台。非 `LeetCode` 的題不進官方 AC 紀錄，計數由 `lc-stats.sh` 依此欄判斷 | `LeetCode` ｜ `NeetCode` ｜ `KamaCoder` |
| `url` | Text | 題目連結 | |
| `status` | Text | 掌握程度 | `todo` ｜ `ac-unknown` ｜ `ac-assisted` ｜ `ac-solo` ｜ `mastered` ｜ `review` |
| `note` | Text | 備註 | |
| `date_created` | Date | 初次 AC 日期 | `YYYY-MM-DD` |
| `date_updated` | Date | 最近一次 AC 日期 | `YYYY-MM-DD` |

型別欄是 Obsidian Properties 認到的型別。骨架檔的值留空，型別才推得對。

### 跨檔的同步約束

- `topics[0]` 是主要主題，依 `_moc.md` 最接近的既有分類決定；其後照 LeetCode 官方 topicTags 排列。
- 日期以 [`_leetcode-submission-history.md`](_leetcode-submission-history.md) 的 Accepted 紀錄為準：`date_created` 取最早一次，`date_updated` 取最近一次。首次 AC 建檔時兩者同日。不進 LeetCode history 的平台題，以有實質內容的題解日期為證據。格式整理、schema migration、補註解不改日期。
- 未刷的題是只有 frontmatter 的空殼；`_moc.md` 不因多解重複計數。
- 檔尾的「相關」區塊連回 `topics[]` 對應的每一份主題筆記（例 `[two-pointers](../../topics/T02-21-two-pointers.md)`）與 [`_moc`](_moc.md)；主題筆記的「已刷題目」清單反向連回題解。新建或本次有實質更新的題解要補上，未觸碰的既有題解下次實質更新再補。

---

## 寫作口徑

### 最高一條：不要用中文複述程式碼

`prefix[0] = 1（0 號左邊沒有東西，乘法單位元素是 1）` 這種句子，讀者看 code 就知道了，寫了等於沒寫。

要寫的是 code 看不出來的東西：這個手法叫什麼、為什麼這樣做會對、什麼時候該想到用它。

### 各節分工

| 節 | 寫什麼 | 形式 |
| --- | --- | --- |
| 心得 | 我的一句話心得 | 用她的原話，不改寫 |
| Intuition | 問題的本質是什麼、用什麼手法、為什麼有效 | 書面筆記，濃縮精確 |
| Approach | 程式碼的步驟一二三四 | 編號逐步，本來就該講程式碼 |
| Complexity | 複雜度與推導 | 條列 |
| Code Review | 複習時掃一眼找「上次錯在哪」 | 條列 |
| Optimality | 為什麼這是（或不是）最好的、指標是什麼 | 書面筆記，濃縮精確 |

### Intuition 與 Optimality 的形式：是筆記，不是逐字稿

用詞要平白（這就是「講人話」），但形式是**書面筆記**：一句話講一件事，段落之間有邏輯順序，由淺入深。

禁止：

- 反問句開場（「先想一個問題」「差在哪裡？」「visited 平常到底在幹嘛？」）
- 對話口吻（「你可能會問」「順帶一提」「我知道你想要一個乾脆的答案」）
- 用比喻講故事。技術題解直接講機制，比喻留給 `topics/*-basics.md`
- 一個概念拆成好幾段慢慢鋪陳。想到就寫完，不要營造節奏

長度參考：Intuition 通常 3 到 6 段，每段 2 到 4 句。有好幾個要點就分小標，不要一路鋪陳。

### 去 AI 味（寫完自查）

- 不虛構我沒有的前提再推翻它。她沒打算用除法，就不要花一段講「除法走不通」。
- 不自問自答下判決：「這條路本來就走不通」「這樣一拆，問題就消失了」。
- 不用價值上升詞與說教腔：標誌著、本質上、說到底、真正的關鍵在於。
- 不用「不是 A 而是 B」堆疊、排比三段式、勸誡反問收尾。
- 粗體只標真正的關鍵字，一段最多一處。能用段落講完就不開表格。
