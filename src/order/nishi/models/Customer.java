package order.nishi.models;

import java.sql.*;

import order.nishi.utils.ConnectUtilMy;

public class Customer {
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet resultSet = null;

    public CustomerEntity getCustomerEntity() {
        return this.setCustomerEntity();
    }

    private CustomerEntity setCustomerEntity() {
        CustomerEntity customer = new CustomerEntity();
        try {
            conn = (new ConnectUtilMy()).connectDatabase();
            String query = "SELECT custom_c, custom_nam FROM custom";
            stmt = conn.prepareStatement(query);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                customer.addCustomC(resultSet.getInt("custom_c"));
                customer.addCustomNam(resultSet.getString("custom_nam"));
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
        return customer;
    }
}
