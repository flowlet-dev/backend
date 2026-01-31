# Flowlet プロジェクト開発ガイドライン

## 1. 基本方針
* **保守性優先**: 短期的な実装スピードよりも、読みやすさと修正のしやすさを重視する。
* **APIファースト**: API仕様（OpenAPI/Swagger）を正とし、自動生成されたインターフェースを元に実装を行う。
    * **自動生成の活用**: `openapi-generator` 等のツールで生成される `Api` インターフェースを Controller で実装（`implements`）し、メソッド定義の乖離を防ぐ。
* **ドメイン駆動設計 (DDD) の意識**: 
    * ビジネスロジックはドメインモデル（EntityやValue Object）に持たせ、Service層が単なる「手続き」の羅列にならないよう設計する。
    * オブジェクトは常に正しい状態を保つよう、ドメイン層で整合性を担保する。
* **エラーハンドリングの集約**: `GlobalExceptionHandler` (@RestControllerAdvice) を使用し、例外処理を一括管理する. Controller内での個別の try-catch は原則禁止とする。

## 2. 命名規則
* **データベース・Entity**: 
    * マスターテーブル: `M_{TableName}` (例: `MCategory`)
    * トランザクションテーブル: `T_{TableName}` (例: `TTransaction`)
* **Javaクラス**:
    * 基本的にアッパーキャメルケース（PascalCase）。
    * Entity名は対応するテーブル名に準拠させる。

## 3. ドキュメント（JavaDoc）
* **必須適用**: すべての公開（Public/Protected）クラス、インターフェース、メソッドには必ずJavaDocを記述すること。
* **言語**: **日本語**で記述する。
* **記述内容**: 
    * クラス: そのクラスが担当するドメイン上の役割と責務。
    * メソッド: 引数（@param）、戻り値（@return）、例外（@throws）の具体的な意味と、ビジネスルール上の振る舞い。

## 4. アーキテクチャ（階層構造）
DDDの考え方を取り入れた以下の4層構造を基本とする：
1. **Controller (Interface Layer)**: APIの受付、入力バリデーション、レスポンスの返却。
2. **Service (Application Layer)**: ユースケースの実現。ドメインオブジェクトを組み合わせて一連の処理（トランザクション）を制御する。
3. **Domain (Domain Layer)**: Entity、Value Object、Repositoryインターフェース。ビジネスルールとデータ構造の定義。
4. **Infrastructure (Infrastructure Layer)**: Repositoryの実装（JPA）、外部システム連携、共通ユーティリティの実装。

## 5. メッセージ管理
* **一元管理**: エラーメッセージやユーザー通知は、コード内に直接記述（ハードコード）せず、必ず `src/main/resources/messages.properties` で管理する。
* **メッセージキーの命名規則**: `error.{カテゴリー}.{詳細内容}` の形式とする（例: `error.category.not.found`）。
* **例外との連携**: カスタム例外をスローする際は、メッセージキーを渡し、`MessageSource` を通じて多言語化や変更に対応しやすい設計にする。

## 6. 実装のこだわり（Coding Standards）
* **Lombokの活用**: `@Getter`, `@Setter`, `@RequiredArgsConstructor` などを積極的に使い、コードの冗長性を排除する。
* **依存性の注入**: フィールド注入（@Autowired）は避け、Lombokの `@RequiredArgsConstructor` を用いた **コンストラクタ注入** を採用する。
* **型安全**: マジックナンバーや文字列による状態管理を避け、EnumやValue Objectを活用する。

## 7. Junie (AI) への指示ルール
* **コード生成時**: 
    * 新規機能の実装時は、Service、ドメインモデル、および対応する **単体テスト** をセットで作成すること。
    * **必ず全てのクラスとメソッドに日本語のJavaDocを付与すること。**
    * メッセージが必要な場合は、`messages.properties` への追加案も提示すること。
* **レビュー・改善提案**: 
    * パフォーマンス懸念（N+1問題など）や、DDDの観点からドメインモデルに移動すべきロジックがあれば積極的に指摘すること。
    * 既存Entityの変更時は、影響範囲（Repository、DTO、テスト）を網羅的に確認し、修正案を提示すること。

## 8. API定義とOpenAPIアノテーション
OpenAPI Generator との整合性を保ち、かつドキュメントとしての正確性を維持するため、以下のルールを遵守する。

* **Controller の実装**:
    * 自動生成された `...Api` インターフェースを実装し、`@Override` を付与してメソッドを定義する。
    * APIのメタデータ（`@Tag`, `@Operation` など）は、可能な限り生成されたインターフェース側に持たせ、Controller 実装側はロジックの呼び出しに集中する。
* **DTO (Data Transfer Object)**:
    * **アノテーションの付与**: `@Schema` を使用して、物理名だけでなく論理名（description）やバリデーション制約（example, minLength, maxLength 等）を明記する。
    * **バリデーション**: `jakarta.validation.constraints`（`@NotBlank`, `@Size`, `@NotNull` 等）を適切に使用し、OpenAPI 定義と Java 側のバリデーションを同期させる。
    * **JSON制御**: 必要に応じて `@JsonInclude(JsonInclude.Include.NON_NULL)` 等を使用し、APIレスポンスの制御を明示する。
