<%@page contentType="text/html" pageEncoding="windows-31j"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="org.apache.commons.lang3.*"%>
<?xml version="1.0" encoding="windows-31j" ?>
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 <html xmlns="http://www.w3.org/1999/xhtml">

 <body>
	<h1>受注詳細情報</h1>
  <form>
   <INPUT type="button" onClick='history.back();' value="戻る">
   </button>
  </form><br>
  <table border=1>
   <tr>
    <td>受注No.</td>
    <td>受注日付</td>
    <td>顧客コード</td>
    <td>顧客名</td>
    <td>担当者コード</td>
    <td>担当者名</td>
    <td>合計金額</td>
    <td>消費税額</td>
    <td>請求金額</td>
   </tr>
   <%
   	order.DeleteHelperModel Model = (order.DeleteHelperModel) request.getAttribute("Model");
  	if (Model != null) {
		List<String[]> titleResults = Model.getTitleResults();
		for (String[] result : titleResults) {
			String order_no = result[0];
			String order_date = result[1];
			String custom_c = result[2];
			String custom_nam = result[3];
			String sales_c = result[4];
			String sales_nam = result[5];
			out.println(String.format(
				"<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td>",
				order_no, order_date, custom_c, custom_nam, sales_c, sales_nam));
			String total_amount1 = result[6];
			int total_amount2 = Integer.parseInt(total_amount1);
			double sales_tax1 = (double)total_amount2 * 0.08;
			int sales_tax2 = (int)sales_tax1;
			int bill = total_amount2 + sales_tax2;
			out.println(String.format(
				"<td>%s</td><td>%s</td><td>%s</td></tr>",
				total_amount2, sales_tax2, bill));
		}
	}
   %>
  </table>
  
  <br>
  
  <table border=1>
   <tr>
    <td>商品コード</td>
    <td>商品名</td>
    <td>単価</td>
    <td>数量</td>
   </tr>
   <%
  	if (Model != null) {
		List<String[]> detailResults = Model.getDetailResults();
		for (String[] result : detailResults) {
			String item_c = result[0];
			String item_nam = result[1];
			String price = result[2];
			String quantity = result[3];
			out.println(String.format(
				"<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>",
				item_c, item_nam, price, quantity));
		}
	}
	%>
  </table>
  
  <br>
  <form action="DeleteController" method="get">
    <button type="submit" name = "selectNumber" value="<%= request.getAttribute("selectNumber") %>" />受注削除</button>
  </form>
 </body>
 </html>
