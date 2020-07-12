package com.genweb2.jasper.db;

import java.sql.DriverManager;

public class AccountDBConnection extends DbConnection {

    private static AccountDBConnection dbConnection;

    private AccountDBConnection() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            this.connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.15:1521:orcl1","user_db","user_db");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AccountDBConnection getInstance() {
        if (dbConnection == null)
            dbConnection = new AccountDBConnection();

        return dbConnection;
    }
}
