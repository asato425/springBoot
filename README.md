# springboot-simple

Spring Boot 3.4.2 と Thymeleaf で作った、シンプルな足し算アプリです。フォームから2つの数字を入力すると、計算結果を別画面に表示します。

## 機能

- 2つの数値を入力して足し算できる
- フォーム入力を `CalculationForm` で受け取る
- 計算ロジックを `CalculationService` に分離している
- `jakarta.validation` による入力チェックを行う
- エラー内容を画面に赤字で表示する
- PRG パターンで結果画面へリダイレクトする

## 技術スタック

- Java 17
- Spring Boot 3.4.2
- Spring MVC
- Thymeleaf
- Spring Validation
- Lombok
- Maven

## 画面構成

- `/` - 入力フォーム画面
- `/calculate` - 計算処理
- `/result` - 計算結果画面

## プロジェクト構成

```text
src/main/java/com/example/springboot/
├── SpringbootApplication.java
├── CalculationController.java
├── CalculationService.java
└── CalculationForm.java

src/main/resources/templates/
├── index.html
└── result.html
```

## 実行方法

### 1. アプリを起動する

```bash
mvn spring-boot:run
```

### 2. ブラウザで開く

```text
http://localhost:8080/
```

## 入力チェック

- `firstNumber` が未入力の場合はエラーになる
- `secondNumber` が未入力の場合はエラーになる
- エラーメッセージはフォーム画面で赤字表示される

## 補足

- フォーム送信後は POST から GET にリダイレクトする PRG パターンを使っています。
- 結果画面の「もう一度計算する」リンクを押すと、入力画面に戻れます。
