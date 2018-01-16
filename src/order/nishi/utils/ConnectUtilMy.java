package order.nishi.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectUtilMy {
    public Connection connectDatabase() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1/orderdb?"
                            + "useUnicode=true&characterEncoding=WINDOWS-31J&useSSL=false",
                    "webdb", "webdb");
        }
        catch (SQLException ex) {
            System.out.println("error code" + ex.getErrorCode());
            System.out.println("SQL error" + ex.getSQLState());
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return conn;
    }
}
