package com.genweb2.jasper.db;

import java.sql.Connection;

public abstract class DbConnection {
    protected Connection connection = null;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
