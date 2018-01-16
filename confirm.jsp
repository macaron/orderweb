<%@ page import="order.nishi.models.CustomerEntity" %>
<%@ page import="order.nishi.models.SalesEntity" %>
<%@ page import="order.nishi.models.CartEntity" %>
<!-- 文字コードwindows-31jによるHTML文書 -->
<%@page contentType="text/html" pageEncoding="windows-31j" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-31j"/>
    <title>受注登録 - 受注確認</title>
</head>
<body>
<h2>受注登録</h2>
<form action="./confirm" method="POST">
    <label>受注年月日</label>
    <select name="year" id="year">
        <option value="<% out.print(request.getAttribute("year")); %>">
            <% out.print(request.getAttribute("year")); %>
        </option>
    </select>
    <label for="year">年</label>
    <select name="month" id="month">
        <%
            for (int month = 1; month <= 12; month++) {
                // 今月をデフォルト選択する
                if (month == (int) request.getAttribute("month")) {
                    out.print("<option value=\"" + month + "\" selected>" + month + "</option>");
                } else {
                    out.print("<option value=\"" + month + "\">" + month + "</option>");
                }
            }
        %>
    </select>
    <label for="month">月</label>
    <select name="day" id="day">
        <%
            for (int day = 1; day <= (int) request.getAttribute("maximumDay"); day++) {
                // 今日をデフォルト選択する
                if (day == (int)request.getAttribute("day")) {
                    out.print("<option value=\"" + day + "\" selected>" + day + "</option>");
                } else {
                    out.print("<option value=\"" + day + "\">" + day + "</option>");
                }
            }
        %>
    </select>
    <label for="day">日</label>

    <label for="customer">顧客名</label>
    <select name="customer_c" id="customer">
        <%
            CustomerEntity customer = (CustomerEntity) request.getAttribute("customers");
            for (int i=0; i<customer.getArrayTotal(); i++) {
                out.print("<option value=\"" + customer.getCustomC(i) + "\">" + customer.getCustomNam(i) + " (" + customer.getCustomC(i) + ")</option>");
            }
        %>
    </select>

    <label for="sales">担当者</label>
    <select name="sales_c" id="sales">
        <%
            SalesEntity sales = (SalesEntity) request.getAttribute("sales");
            for (int i=0; i<sales.getArrayTotal(); i++) {
                out.print("<option value=\"" + sales.getSalesC(i) + "\">" + sales.getSalesNam(i) + " (" + sales.getSalesC(i) + ")</option>");
            }
        %>
    </select>

    <button type="submit" class="btn btn-outline-primary">登録</button>
</form>

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

<br>
<form>
   <INPUT type="button" onClick='history.back();' value="戻る">
   </button>
</form>

</body>
</html>
