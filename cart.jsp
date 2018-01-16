<!-- 文字コードwindows-31jによるHTML文書 -->
<%@page contentType="text/html" pageEncoding="windows-31j" %>
<%@page import="order.nishi.models.ProductEntity" %>
<%@page import="order.nishi.models.CartEntity" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-31j"/>
    <title>受注登録 - 商品選択</title>
</head>
<body>
<h2>受注商品選択</h2>
<form action="./register" method="POST">
    <label for="pcode" class="sr-only"></label>
    <input type="text" name="code" class="form-control" id="pcode" placeholder="商品コード">
    <label for="quant" class="sr-only"></label>
    <input type="text" name="quantity" class="form-control" id="quant" placeholder="数量">
    <button type="submit" class="btn btn-outline-primary">商品選択</button><br>
</form>

<button type="button" value="link" onClick="location.href='./confirm'">
商品選択確定
</button><br>

<button type="button" value="link" onClick="location.href='./reset'">
リセット
</button><br><br>
<button type="button" value="link" onClick="location.href='SearchView1.html'">
 戻る
</button><br>

<table border="1">
    <caption>受注商品一覧</caption>
    <thead>
    <tr>
        <th>商品コード</th>
        <th>商品名</th>
        <th>単価</th>
        <th>数量</th>
        <th>税込金額</th>
    </tr>
    </thead>
        <%
        CartEntity cart = (CartEntity) request.getAttribute("cart");
        if (cart != null) {
            for (int i = 0; i < cart.getArrayTotal(); i++) {
                out.println("<tr>");
                out.println("<td>" + cart.getCode(i) + "</td>");
                out.println("<td>" + cart.getName(i) + "</td>");
                out.println("<td>" + cart.getPrice(i) + "</td>");
                out.println("<td>" + cart.getCount(i) + "</td>");
                out.println("<td>" + cart.getSubTotal(i) + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");

            cart.calcPrice();
            out.println("<p>合計金額: "+cart.price+"円");
            out.println("消費税額: "+cart.tax+"円");
            out.println("請求金額: "+cart.bill+ "円</p>");
        }
    %>
</table>


    <hr/>

    <table border="1">
        <caption>商品一覧</caption>
        <thead>
        <tr>
            <th>商品コード</th>
            <th>商品名</th>
            <th>単価</th>
        </tr>
        </thead>
        <%
            ProductEntity products = (ProductEntity) request.getAttribute("products");
            for (int i = 0; i < products.getArrayTotal(); i++) {
                out.println("<tr>");
                out.println("<td>" + products.getCode(i) + "</td>");
                out.println("<td>" + products.getName(i) + "</td>");
                out.println("<td>" + products.getPrice(i) + "</td>");
                out.println("</tr>");
            }
        %>
    </table>
</body>

</html>