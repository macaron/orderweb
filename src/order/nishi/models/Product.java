package order.nishi.models;

import java.sql.*;
import order.nishi.utils.ConnectUtilMy;

public class Product {
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet resultSet = null;

    public ProductEntity getProductEntity() {
        return this.setProductEntity();
    }

    private ProductEntity setProductEntity() {
        ProductEntity entity = new ProductEntity();
        try {
            conn = (new ConnectUtilMy()).connectDatabase();
            stmt = conn.prepareStatement("SELECT item_c, item_nam, price FROM item ORDER BY item_nam");
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                entity.addCode(resultSet.getInt("item_c"));
                entity.addName(resultSet.getString("item_nam"));
                entity.addPrice(resultSet.getDouble("price"));
            }
        } catch (SQLException ex) {
            System.out.println("error code" + ex.getErrorCode());
            System.out.println("SQL error" + ex.getSQLState());
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
        return entity;
    }

    public ProductEntity makeProductEntityByProductCode(int code) {
        ProductEntity entity = new ProductEntity();
        try {
            conn = (new ConnectUtilMy()).connectDatabase();
            stmt = conn.prepareStatement("SELECT item_c, item_nam, price FROM item WHERE item_c = ?");
            stmt.setInt(1, code);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                entity.addCode(resultSet.getInt("item_c"));
                entity.addName(resultSet.getString("item_nam"));
                entity.addPrice(resultSet.getDouble("price"));
            }
        } catch (SQLException ex) {
            System.out.println("error code" + ex.getErrorCode());
            System.out.println("SQL error" + ex.getSQLState());
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
        return entity;
    }
}
