package com.genweb2.jasper.db;

import java.sql.DriverManager;

public class OrderDBConnection extends DbConnection {

    private static OrderDBConnection dbConnection;

    private OrderDBConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection("jdbc:postgresql://192.168.1.19:5432/order_db", "postgres", "123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static OrderDBConnection getInstance() {
        if (dbConnection == null)
            dbConnection = new OrderDBConnection();

        return dbConnection;
    }
}
