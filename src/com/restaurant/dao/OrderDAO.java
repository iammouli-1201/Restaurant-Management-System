package com.restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.restaurant.bean.Order;
import com.restaurant.util.DBConnection;

public class OrderDAO {
	public void addOrder(Order order) {
        Connection conn = DBConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO orders (user_id, order_date) VALUES (?, ?)");
            ps.setInt(1, order.getUserId());
            ps.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
