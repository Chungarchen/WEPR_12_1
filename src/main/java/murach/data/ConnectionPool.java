package murach.data;

import java.sql.*;

public class ConnectionPool {
    private static ConnectionPool pool = null;

    // JDBC URL cho PostgreSQL (Render)
    private static final String URL = "jdbc:postgresql://dpg-d3gabe9r0fns73bepsa0-a:5432/sql2_db";
    private static final String USER = "sql2_db_user";       // user Render cấp
    private static final String PASSWORD = "tKphsIdvdegP8XNxXelJjzKOgAWVgaag";  // thay bằng password Render cấp

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
