<%@page contentType="text/html" pageEncoding="windows-31j"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="org.apache.commons.lang3.*"%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 <html xmlns="http://www.w3.org/1999/xhtml">

 <body>
	<h1>ŒŸõŒ‹‰Ê</h1>
  <form>
   <button type="button" value="link" onClick="location.href='SearchView1.html'">
	–ß‚é
   </button>
  </form><br>
  <form action="DeleteHelperController" methot="get">
   ó’No.
   <select name="selectNumber">
   <% 
   	order.SearchModel model = (order.SearchModel) request.getAttribute("model");
		if (model != null) {
			List<String[]> results = model.getResults();
			for (String[] result : results) { 
   				String order_no1 = result[0];
   				int order_no2 = Integer.parseInt(order_no1); %>
   				<option value = "<%= order_no2 %>"><%= order_no2 %></option>
   <% } } %>
   </select>
   <button type="submit" value="search" />Ú×•\¦</button>
  </form>
  <table border=1>
   <tr>
    <td>ó’No.</td>
    <td>ó’“ú•t</td>
    <td>ŒÚ‹qƒR[ƒh</td>
    <td>ŒÚ‹q–¼</td>
    <td>’S“–ÒƒR[ƒh</td>
    <td>’S“–Ò–¼</td>
    <td>‡Œv‹àŠz</td>
    <td>Á”ïÅŠz</td>
    <td>¿‹‹àŠz</td>
   </tr>
   <%
   	//out.print(request.getAttribute("searchWord"));
    //order.SearchModel model = (order.SearchModel) request.getAttribute("model");
	if (model != null) {
		List<String[]> results = model.getResults();
		for (String[] result : results) {
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
 </body>
 </html>
