package order;

import java.util.*;
import java.sql.*;

public class DeleteModel {
	private int selectNumber;
	private int results;
	String[] sql = { "DELETE FROM order_title WHERE order_no = ?",
					 "DELETE FROM order_detail WHERE order_no = ?"};
	
	public DeleteModel(String selectNumber) {
		this.selectNumber = Integer.parseInt(selectNumber);
	}
	
	public void execute() {
		try {
			// データベースに接続
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://127.0.0.1/orderdb?" +
				"useUnicode=true&characterEncoding=WINDOWS-31J&useSSL=false";
			Connection conn = DriverManager.getConnection(url, "webdb", "webdb");
			PreparedStatement pstmt;
			for (int i = 0; i < sql.length; i++) {
				pstmt = conn.prepareStatement(sql[i]);
				pstmt.setInt(1, selectNumber);
				results = pstmt.executeUpdate();
			}

			//rs.close();
			//pstmt.close();
			conn.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public int getResults() {
		return results;
	}
}
