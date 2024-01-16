package org.example.steps;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import org.example.managers.DriverManager;
import org.example.managers.InitManager;
import org.example.managers.TestPropManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.example.utils.PropConst.BASE_URL;

public class Hooks {
    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    public static Statement getStatement() throws SQLException {
        return connection.createStatement();
    }

    /**
     * Менеджер WebDriver
     *
     * @see DriverManager#getDriverManager()
     */
    private final DriverManager driverManager = DriverManager.getDriverManager();

    @BeforeAll
    public static void beforeAll() {
        InitManager.initFramework();
    }

    @AfterAll
    public static void afterAll() {
        InitManager.quitFramework();
    }

    @Before (value = "@addproduct")
    public void beforeEach() {
        driverManager.getDriver().get(TestPropManager.getTestPropManager().getProperty(BASE_URL));
    }

    private Connection getNewConnection() throws SQLException {
        String url = "jdbc:h2:tcp://localhost:9092/mem:testdb";
        String user = "user";
        String passwd = "pass";
        return java.sql.DriverManager.getConnection(url, user, passwd);
    }

    @Before (value = "@addproddb")
    public void init() throws SQLException {
        connection = getNewConnection();
    }

    @After (value = "@addproddb")
    public void close() throws SQLException {
        connection.close();
    }

}
