package com.genweb2.jasper.service;

import com.genweb2.jasper.db.AccountDBConnection;
import com.genweb2.jasper.db.OrderDBConnection;

public class BaseService {

    protected AccountDBConnection accountDB;
    protected OrderDBConnection orderDB;

    public BaseService() {
        accountDB = AccountDBConnection.getInstance();
        orderDB = OrderDBConnection.getInstance();
    }
}
