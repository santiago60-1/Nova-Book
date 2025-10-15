// src/main/java/com/codeup/booksnova/util/ConnectionFactory.java
package com.codeup.booksnova.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final Properties props = new Properties();
    private static Connection testConnection;

    static {
        try (InputStream in = ConnectionFactory.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties")) {
            if (in == null) {
                throw new RuntimeException("config.properties not found in classpath");
            }
            props.load(in);

            // optionally load driver class if specified
            String driver = props.getProperty("db.driver");
            if (driver != null && !driver.isBlank()) {
                Class.forName(driver);
            }
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * Returns a live Connection. If tests have set a testConnection, returns that instead.
     */
    public static Connection getConnection() throws SQLException {
        if (testConnection != null) {
            return testConnection;
        }
        return DriverManager.getConnection(
            props.getProperty("db.url"),
            props.getProperty("db.user"),
            props.getProperty("db.password")
        );
    }

    /**
     * Fetches arbitrary property values (e.g. loanPeriodDays, finePerDay).
     */
    public static String get(String key) {
        return props.getProperty(key);
    }

    /**
     * Allows test code to inject its own Connection (e.g. H2 in-memory).
     */
    public static void setTestConnection(Connection conn) {
        testConnection = conn;
    }
}
