package order.nishi.models;

import java.sql.*;

import order.nishi.utils.ConnectUtilMy;

public class Sales {
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet resultSet = null;

    public SalesEntity getSalesEntity() {
        return this.setSales();
    }

    private SalesEntity setSales() {
        SalesEntity sales = new SalesEntity();
        try {
            conn = (new ConnectUtilMy()).connectDatabase();
            String query = "SELECT sales_c, sales_nam FROM sales";
            stmt = conn.prepareStatement(query);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                sales.setSalesC(resultSet.getString("sales_c"));
                sales.setSalesNam(resultSet.getString("sales_nam"));
            }
        } catch (SQLException ex) {
            System.out.println("エラーコード" + ex.getErrorCode());
            System.out.println("SQLエラーコード" + ex.getSQLState());
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return sales;
    }
}
