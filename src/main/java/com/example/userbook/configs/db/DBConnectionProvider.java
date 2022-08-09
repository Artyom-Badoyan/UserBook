package com.example.userbook.configs.db;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnectionProvider {

    private static final DBConnectionProvider instance = new DBConnectionProvider();

    private String driverName;
    private String dbUrl;
    private String username;
    private String password;

    private Connection connection;

    @SneakyThrows
    private DBConnectionProvider() {
        loadProperties();
        Class.forName(driverName);
    }

    @SneakyThrows
    private void loadProperties() {
        Properties properties = new Properties();
        properties.load(new FileInputStream(
                "C:\\Users\\User\\IdeaProjects\\UserBook\\src\\main\\resources\\db.properties"));
        driverName = properties.getProperty("db.driver.name");
        dbUrl = properties.getProperty("db.url");
        username = properties.getProperty("db.username");
        password = properties.getProperty("db.password");

    }

    @SneakyThrows
    public static DBConnectionProvider getInstance() {
        return instance;
    }

    @SneakyThrows
    public Connection getConnection() {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(dbUrl, username, password);
        }
        return connection;
    }

    @SneakyThrows
    public void closeConnection() {
        if (!connection.isClosed()) {
            connection.close();
        }
    }
}
