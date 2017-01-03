package ru.innopolis.uni.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *Created by Igor Ryabtsev on 28.12.2016.
 * Класс для получения соединения с базой данных
 */
@Repository
public class DBConnection {
    private static Logger log = LoggerFactory.getLogger(DBConnection.class);
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/online_shop";
    private static Connection conn;

    private DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    DATABASE_URL, "root",
                    "root");
        } catch (ClassNotFoundException e) {
            log.warn(e.getMessage());
        } catch (SQLException e) {
            log.warn(e.getMessage());
        }
    }

    public static Connection getConnecton() {
        if (conn != null) {
            return conn;
        } else {
            return new DBConnection().conn;
        }
    }

    public void close() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
            conn = null;
        }
    }
}
