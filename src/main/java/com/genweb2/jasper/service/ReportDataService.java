package com.genweb2.jasper.service;

import com.genweb2.jasper.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportDataService extends BaseService {

    public ReportDataService() {
        super();
    }

    public List<User> getUserList() throws SQLException {
        Map<Integer, Integer> orderCount = this.getOrderCount();

        List<User> userList = new ArrayList<>();
        String sql = "select * from \"user\"";
        Statement stmt = accountDB.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            User user = new User();
            user.setUserId(rs.getInt("user_id"));
            user.setUsername(rs.getString("username"));
            user.setName(rs.getString("full_name"));
            user.setEmail(rs.getString("email"));

            user.setTotalOrder(orderCount.get(user.getUserId()));

            userList.add(user);
        }


        return userList;
    }

    private Map<Integer, Integer> getOrderCount() throws SQLException {

        Map<Integer, Integer> userOrderCount = new HashMap<>();

        String sql = "select user_id, count(order_id) as total_order from \"order\" group by user_id";
        Statement stmt = orderDB.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            userOrderCount.put(rs.getInt("user_id"), rs.getInt("total_order"));
        }

        return userOrderCount;
    }
}
