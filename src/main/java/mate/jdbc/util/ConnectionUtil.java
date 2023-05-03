package mate.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static String url = "jdbc:mysql://localhost:3306/dbcarservice";
    private static String name = "root";
    private static String password = "password2023";

    private ConnectionUtil() {
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, name, password);
        } catch (SQLException e) {
            throw new DataProcessingException("error in connection method", e);
        }
        return connection;
    }
}
