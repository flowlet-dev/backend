# 家計管理アプリ 設計まとめ（概念・DB・設定）

## 1. アプリの目的

- 自分自身の家計管理を行う
- 収支をあとからまとめて入力・振り返る
- 給料日ベースで家計の区切りを把握する
- 将来の拡張（口座管理・仮想口座・分析）に耐える設計にする

---

## 2. 基本設計思想

### 2.1 設計の原則

- DBは「事実」と「関係」だけを持つ
- UIや表示ルールはアプリ側に寄せる
- 将来必要になる可能性が高いものは、最小構成で先に入れる
- 後から壊さなくて済む設計を優先する

---

## 3. テーブル種別と命名規則

### 3.1 テーブル種別

| 種別 | 接頭辞 | 役割 |
|---|---|---|
| マスター | m_ | 参照される基本データ |
| トランザクション | t_ | お金が動いた事実 |

---

## 4. 識別子ルール（確定）

### 4.1 識別子は id のみ

- すべてのテーブルで **UUID の id のみを使用**
- cd / no は使用しない
- 自動採番は id（UUID）で完結させる

理由：
- 採番ルール変更に強い
- 変更・拡張時に DB を壊さない
- JOIN・参照が一貫する

---

## 5. 共通カラム（全テーブル）

| カラム名 | 説明 |
|---|---|
| created_by | 作成者 |
| created_at | 作成日時 |
| updated_by | 更新者 |
| updated_at | 更新日時 |

---

## 6. 概念モデル（エンティティ）

### 6.1 Transaction（取引）
- お金が動いた「事実」
- 収支・移動・将来のクレカ利用も同一概念で扱う

### 6.2 Category（カテゴリー）
- 収支の分類
- 集計・分析の最小単位
- アイコンを持つ

### 6.3 Account（実口座）
- 銀行口座・クレジットカード・現金
- 実際のお金・負債の置き場所

### 6.4 VirtualAccount（仮想口座）
- お金の目的・意味
- 実口座とは独立

### 6.5 Setting（設定）
- アプリの挙動を決めるルール
- 種類別に拡張可能な構成

---

## 7. DB設計（最終）

### 7.1 m_category（カテゴリー）

```sql
CREATE TABLE m_category (
    category_id UUID PRIMARY KEY,

    category_name TEXT NOT NULL,
    category_type TEXT NOT NULL CHECK (category_type IN ('income', 'expense')),
    icon_key TEXT NOT NULL,

    created_by TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_by TEXT NOT NULL,
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);
```

### 7.2 m_account（口座）

```sql
CREATE TABLE m_account (
    account_id UUID PRIMARY KEY,

    account_name TEXT NOT NULL,
    account_type TEXT NOT NULL CHECK (account_type IN ('bank', 'credit', 'cash')),

    created_by TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_by TEXT NOT NULL,
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);
```

### 7.3 m_virtual_account（仮想口座）

```sql
CREATE TABLE m_virtual_account (
    virtual_account_id UUID PRIMARY KEY,

    virtual_account_name TEXT NOT NULL,

    created_by TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_by TEXT NOT NULL,
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);
```

## 8. 設定設計（拡張可能）

### 8.1 m_setting（設定ヘッダ）

```sql
CREATE TABLE m_setting (
    setting_id UUID PRIMARY KEY,
    setting_type TEXT NOT NULL,

    created_by TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_by TEXT NOT NULL,
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);
```

### 8.2 m_setting_salary（給料日ルール）

```sql
CREATE TABLE m_setting_salary (
    setting_id UUID PRIMARY KEY REFERENCES m_setting(setting_id),

    base_day INTEGER NOT NULL CHECK (base_day BETWEEN 1 AND 31),

    weekend_rule TEXT NOT NULL CHECK (
        weekend_rule IN ('none', 'before', 'after')
    ),

    holiday_rule TEXT NOT NULL CHECK (
        holiday_rule IN ('none', 'before', 'after')
    ),

    holiday_calendar TEXT NOT NULL DEFAULT 'JP'
);
```

#### 給料日ルールの考え方

* 基本日（例：25日）

* 土日・祝日の場合：

  * 前営業日

  * 後営業日

  * 何もしない

## 9. t_transaction（取引）

```sql
CREATE TABLE t_transaction (
    transaction_id UUID PRIMARY KEY,

    transaction_date DATE NOT NULL,
    transaction_amount INTEGER NOT NULL CHECK (transaction_amount > 0),
    transaction_type TEXT NOT NULL CHECK (transaction_type IN ('income', 'expense')),

    category_id UUID NOT NULL REFERENCES m_category(category_id),

    from_account_id UUID REFERENCES m_account(account_id),
    to_account_id UUID REFERENCES m_account(account_id),

    virtual_account_id UUID REFERENCES m_virtual_account(virtual_account_id),

    transaction_memo TEXT,

    created_by TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_by TEXT NOT NULL,
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);
```

## 10. カテゴリーアイコン設計

* カテゴリーは icon_key を持つ

* 画像やSVGは持たない

* フロント側で icon_key → 実アイコンに変換

### icon_key ルール

* 英小文字

* 意味ベース（UI依存しない）

例：

* food

* transport

* rent

* salary

* medical

## 11. この設計の特徴

* MVPに必要な最小構成

* 将来拡張しても壊れない

* DBとUIの責務が明確

* 家計簿 → 本格的資金管理へ自然に進化可能

## 12. 次のステップ候補

* API設計（CRUD / 集計 / 設定）

* 給料日ベースの集計ロジック定義

* 初期カテゴリ・口座の洗い出し

* backend 実装開始
