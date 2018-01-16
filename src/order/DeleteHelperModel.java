package order;

import java.util.*;
import java.sql.*;

public class DeleteHelperModel {
	private int selectNumber;
	private List<String[]> titleResults;
	private List<String[]> detailResults;
	
	public DeleteHelperModel(String selectNumber) {
		this.selectNumber = Integer.parseInt(selectNumber);
	}

	public void executeTitle() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://127.0.0.1/orderdb?" +
				"useUnicode=true&characterEncoding=WINDOWS-31J&useSSL=false";
			Connection conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			PreparedStatement pstmt = conn.prepareStatement("select order_no, order_date, order_title.custom_c, custom_nam, order_title.sales_c, sales_nam, total_amount from order_title, custom, sales where order_title.custom_c = custom.custom_c and order_title.sales_c = sales.sales_c and order_no = ? order by order_no");
			pstmt.setInt(1, selectNumber);
			ResultSet rs = pstmt.executeQuery();

			titleResults = new LinkedList<String[]>();
			while (rs.next()) {
				String[] result = {
					rs.getString("order_no"),
					rs.getString("order_date"),
					rs.getString("order_title.custom_c"),
					rs.getString("custom_nam"),
					rs.getString("order_title.sales_c"),
					rs.getString("sales_nam"),
					rs.getString("total_amount")
					};
				titleResults.add(result);
			}

			rs.close();
			pstmt.close();
			conn.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void executeDetail() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://127.0.0.1/orderdb?" +
				"useUnicode=true&characterEncoding=WINDOWS-31J&useSSL=false";
			Connection conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			PreparedStatement pstmt = conn.prepareStatement("select order_detail.item_c, item_nam, price, quantity from order_detail, item where order_detail.item_c = item.item_c and order_no = ?");
			pstmt.setInt(1, selectNumber);
			ResultSet rs = pstmt.executeQuery();

			detailResults = new LinkedList<String[]>();
			while (rs.next()) {
				String[] result= {rs.getString("order_detail.item_c"),
								  rs.getString("item_nam"),
								  rs.getString("price"),
								  rs.getString("quantity")};
				detailResults.add(result);
			}

			rs.close();
			pstmt.close();
			conn.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public List<String[]> getTitleResults() {
		return titleResults;
	}
	public List<String[]> getDetailResults() {
		return detailResults;
	}
}
