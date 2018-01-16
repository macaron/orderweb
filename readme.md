# MVC版受注登録システム「orderweb」

## トップページURL
[http://localhost:8080/orderweb/SearchView1.html] (http://localhost:8080/orderweb/SearchView1.html)

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
