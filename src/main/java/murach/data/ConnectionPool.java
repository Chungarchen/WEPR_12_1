package murach.data;

import java.sql.*;

public class ConnectionPool {
    private static ConnectionPool pool = null;

    // JDBC URL cho PostgreSQL (Render)
    private static final String URL = "jdbc:postgresql://dpg-d3h6s42li9vc73dsuck0-a:5432/werp";
    private static final String USER = "werp_user";       // user Render cấp
    private static final String PASSWORD = "fCkKHCr6Sk9ZhXS3FKKjlzcQ0NBemfVD";  // thay bằng password Render cấp

    private ConnectionPool() {
        try {
            // load driver PostgreSQL
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static synchronized ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void freeConnection(Connection c) {
        try {
            if (c != null) c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
