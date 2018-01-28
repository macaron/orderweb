# MVC版受注登録システム「orderweb」

## トップページURL
[http://localhost:8080/orderweb/SearchView1.html](http://localhost:8080/orderweb/SearchView1.html)

## 修正箇所
- 【受注登録】商品コードや数量を空欄にしておくと、異常終了する
- 【受注登録】数量に整数でない値を入力すると、異常終了する
- 【受注登録】誤った（存在しない）商品コードを入力すると、異常終了する
- 【受注登録】誤った日付（2月31日など）を入力すると、不正な日付で登録されてしまう

## コンパイル&実行方法
- srcディレクトリに移動し下記のコマンドを実行
- src/orderディレクトリをWEB-INF/classes以下にコピー

```
javac order/*.java
javac -encoding utf8 order/nishi/controllers/*.java
javac -encoding utf8 order/nishi/models/*.java
javac -encoding utf8 order/nishi/utils/*.java
```

## 動作確認環境
- Windows10 64bit
- apache-tomcat-8.5.23
- mysql-connector-java-5.1.44-bin.jar
