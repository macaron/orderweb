package order.nishi.models;

import java.sql.*;
import java.util.Calendar;

import order.nishi.utils.ConnectUtilMy;


public class OrderWrite {
    private int customer_c;
    private String staff_c;
    private long cal;
    private int order_no;
    private double total_amount;

    public OrderWrite(int year, int month, int day, int customer_c, String staff_c) {
        this.customer_c = customer_c;
        this.staff_c = staff_c;

        // 受注日時でgetTimeする
        this.cal = this.makeOrderDate(year, month - 1, day);
    }

    // 受注見出しを登録する
    public void insertOrderTitle() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        try {
            conn = (new ConnectUtilMy()).connectDatabase();

            // 受注日付、顧客コード、担当者コード、合計金額、消費税、税込み価格
            String query1 = "INSERT INTO order_title (order_no, order_date, custom_c, sales_c, total_amount, sales_tax, bill) VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query1, stmt.RETURN_GENERATED_KEYS);
            stmt.setInt(1, 0);
            stmt.setDate(2, new java.sql.Date(this.cal));
            stmt.setInt(3, this.customer_c);
            stmt.setString(4, this.staff_c);
            stmt.setDouble(5, 0);
            stmt.setDouble(6, 0);
            stmt.setDouble(7, 0);
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            this.order_no = keys.getInt(1);

        } catch (SQLException ex) {
            System.out.println("エラーコード：" + ex.getErrorCode());
            System.out.println("SQL状態：" + ex.getSQLState());
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
    }

    // 受注詳細を登録する
    public void insertOrderDetail(int item_cd, int amount) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        // アイテム価格を取得する
        double price = this.getPriceByItemCode(item_cd);
        // 合計金額
        this.total_amount += price * amount;

        try {
            conn = (new ConnectUtilMy()).connectDatabase();

            // order_detail
            String query = "INSERT INTO order_detail (order_no, item_c, quantity) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, this.order_no);
            stmt.setInt(2, item_cd);
            stmt.setInt(3, amount);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("エラーコード：" + ex.getErrorCode());
            System.out.println("SQL状態：" + ex.getSQLState());
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
    }

    // 注文見出しを更新する
    public void updateOrderTitle() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        // 消費税
        double sales_tax = this.total_amount * 0.08;
        double bill = this.total_amount + sales_tax;

        try {
            conn = (new ConnectUtilMy()).connectDatabase();

            // order_detail
            String query = "UPDATE order_title SET total_amount=?, sales_tax=?, bill=? WHERE order_no = ?";
            stmt = conn.prepareStatement(query);
            stmt.setDouble(1, this.total_amount);
            stmt.setDouble(2, sales_tax);
            stmt.setDouble(3, bill);
            stmt.setInt(4, this.order_no);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("エラーコード：" + ex.getErrorCode());
            System.out.println("SQL状態：" + ex.getSQLState());
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
    }

    // 日付データに加工する
    private long makeOrderDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DATE, day);
        return cal.getTime().getTime();
    }

    // アイテムコードから価格を取得する
    private double getPriceByItemCode(int item_id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        try {
            conn = (new ConnectUtilMy()).connectDatabase();
            // クエリ
            String query = "SELECT price FROM item WHERE item_c = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, item_id);
            resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                // 価格を返却する
                return resultSet.getDouble("price");
            }
        } catch (SQLException ex) {
            System.out.println("エラーコード：" + ex.getErrorCode());
            System.out.println("SQL状態：" + ex.getSQLState());
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
        return 0;
    }
}
