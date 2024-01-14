package org.example.basetestsclass;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseJdbcTests {
    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    private Connection getNewConnection() throws SQLException {
        String url = "jdbc:h2:tcp://localhost:9092/mem:testdb";
        String user = "user";
        String passwd = "pass";
        return DriverManager.getConnection(url, user, passwd);
    }

    public static Statement getStatement() throws SQLException {
        return connection.createStatement();
    }

    @BeforeEach
    public void init() throws SQLException {
        connection = getNewConnection();
    }
    @AfterEach
    public void close() throws SQLException {
        connection.close();
    }
}
