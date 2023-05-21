package com.example.transportenligne;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databasemaneger {

    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            String url = "http://51.210.241.72/phpmyadmin/index.php?db=admin_transport&target=db_structure.php&token=dc33f69314e663dee31dfa3a2ffb092a";
            String user = "admin_transport";
            String password = "123456transport";
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }
}