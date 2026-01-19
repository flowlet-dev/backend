# DB Schema Draft（MVP）

## 目的
- 家計管理アプリ（個人利用）のMVP用DB設計
- 拡張は前提とするが、現時点では作り込みすぎない
- 「DB → API → 画面」の土台となる設計を定義する

---

## テーブル一覧

### マスターテーブル（m_）

- m_category  
- m_account  
- m_virtual_account  
- m_setting  
- m_setting_salary  

### トランザクションテーブル（t_）

- t_transaction  

---

## 命名・設計ルール

### テーブル
- マスターテーブル：`m_`
- トランザクションテーブル：`t_`

### 主キー
- **1テーブル1主キー**
- カラム名はすべて `id`
- cd / no は使用しない（今後も使用しない方針）

### 外部キー
- `xxx_id` 形式で明示的に命名する
- 論理削除は MVP では採用しない

---

## 共通カラム（全テーブル共通）

| カラム名 | 型 | 説明 |
|--------|----|----|
| id | PK | 主キー |
| created_by | string / uuid | 作成者 |
| created_at | datetime | 作成日時 |
| updated_by | string / uuid | 更新者 |
| updated_at | datetime | 更新日時 |

---

## テーブル詳細

### m_category（カテゴリー）

| カラム名 | 説明 |
|--------|----|
| category_id | カテゴリーID |
| parent_category_id | 親カテゴリーID（NULL=親） |
| category_name | カテゴリー名 |
| type | INCOME / EXPENSE |
| icon_key | 親カテゴリー用アイコン |
| sort_order | 表示順 |
| is_active | 有効フラグ |

---

### m_account（口座）

| カラム名 | 説明 |
|--------|----|
| account_id | 口座ID |
| account_name | 口座名 |
| account_type | CASH / BANK / CREDIT |
| initial_balance | 初期残高 |
| is_active | 有効フラグ |

---

### m_virtual_account（仮想口座）

| カラム名 | 説明 |
|--------|----|
| virtual_account_id | 仮想口座ID |
| account_id | 紐づく口座ID |
| virtual_account_name | 仮想口座名 |
| purpose | 目的（任意） |

---

### m_setting（設定）

| カラム名 | 説明 |
|--------|----|
| setting_id | 設定ID |
| setting_key | 設定キー |
| setting_value | 設定値 |

---

### m_setting_salary（給料日設定）

| カラム名 | 説明 |
|--------|----|
| setting_salary_id | 給料日設定ID |
| payday | 給料日（日） |
| adjust_type | PREVIOUS_DAY / NEXT_DAY / ON_THAT_DAY |
| adjust_if_holiday | 土日祝対応フラグ |

---

### t_transaction（取引）

| カラム名 | 説明 |
|--------|----|
| transaction_id | 取引ID |
| transaction_date | 取引日 |
| amount | 金額 |
| category_id | カテゴリーID |
| from_account_id | 出金元口座ID |
| to_account_id | 入金先口座ID |
| virtual_account_id | 仮想口座ID（任意） |
| memo | メモ |

---

## 補足方針

- 金額は常に **正の数** で保持
- 収入 / 支出 / 振替の区別は
  - category.type
  - from / to account の有無
 で判断する
- 集計ロジックは DB に持たせず、API / frontend 側で行う

---

## 次の作業

- [ ] m_category テーブル詳細設計（型・制約）
- [ ] 他マスターテーブルの詳細化
- [ ] ER 図作成（必要なら）
- [ ] マイグレーション作成
