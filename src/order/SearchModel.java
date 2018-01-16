package order;

import java.util.*;
import java.sql.*;

public class SearchModel {
	private String searchWord;
	private List<String[]> results;
	
	public SearchModel(String searchWord) {
		this.searchWord = "%"+searchWord+"%";
	}

	public void execute() {
		try {
			// データベースに接続
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://127.0.0.1/orderdb?" +
				"useUnicode=true&characterEncoding=WINDOWS-31J&useSSL=false";
			Connection conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			PreparedStatement pstmt = conn.prepareStatement("select order_no, order_date, order_title.custom_c, custom_nam, order_title.sales_c, sales_nam, total_amount from order_title, custom, sales where order_title.custom_c = custom.custom_c and order_title.sales_c = sales.sales_c and custom_nam like ? order by order_no");
			pstmt.setString(1, searchWord);
			ResultSet rs = pstmt.executeQuery();

			// 検索結果の取得
			results = new LinkedList<String[]>();
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
				results.add(result);
			}

			// 切断
			rs.close();
			pstmt.close();
			conn.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public List<String[]> getResults() {
		return results;
	}
}
