package com.restaurant.service;

import java.util.List;

import com.restaurant.bean.MenuItem;
import com.restaurant.bean.Order;
import com.restaurant.bean.User;
import com.restaurant.dao.MenuItemDAO;
import com.restaurant.dao.OrderDAO;
import com.restaurant.dao.UserDAO;

public class RestaurantService {
	private MenuItemDAO menuItemDAO;
    private OrderDAO orderDAO;
    private UserDAO userDAO;

    public RestaurantService() {
        menuItemDAO = new MenuItemDAO();
        orderDAO = new OrderDAO();
        userDAO = new UserDAO();
    }

    public List<MenuItem> getMenuItems() {
        return menuItemDAO.getAllMenuItems();
    }

    public void placeOrder(Order order) {
        orderDAO.addOrder(order);
    }

    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }
}
