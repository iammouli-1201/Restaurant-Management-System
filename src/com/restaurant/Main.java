package com.restaurant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.restaurant.bean.MenuItem;
import com.restaurant.bean.Order;
import com.restaurant.bean.User;
import com.restaurant.service.RestaurantService;

public class Main {
	private static Scanner scanner = new Scanner(System.in);
    private static RestaurantService service = new RestaurantService();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Welcome to the Restaurant Management System");
            System.out.println("1. View Menu Items");
            System.out.println("2. Place an Order");
            System.out.println("3. View User Information");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewMenuItems();
                    break;
                case 2:
                    placeOrder();
                    break;
                case 3:
                    viewUserInformation();
                    break;
                case 4:
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewMenuItems() {
        List<MenuItem> menuItems = service.getMenuItems();
        System.out.println("Menu Items:");
        for (MenuItem item : menuItems) {
            System.out.println(item.getId() + ". " + item.getName() + " - $" + item.getPrice());
        }
    }

    private static void placeOrder() {
        System.out.print("Enter your user ID: ");
        int userId = scanner.nextInt();

        List<MenuItem> menuItems = service.getMenuItems();
        List<MenuItem> orderItems = new ArrayList<>();

        System.out.println("Menu Items:");
        for (MenuItem item : menuItems) {
            System.out.println(item.getId() + ". " + item.getName() + " - $" + item.getPrice());
        }

        System.out.println("Enter the menu item IDs to add to your order (0 to finish):");
        while (true) {
            int itemId = scanner.nextInt();
            if (itemId == 0) break;
            MenuItem menuItem = menuItems.stream().filter(item -> item.getId() == itemId).findFirst().orElse(null);
            if (menuItem != null) {
                orderItems.add(menuItem);
                System.out.println(menuItem.getName() + " added to your order.");
            } else {
                System.out.println("Invalid item ID. Please try again.");
            }
        }

        if (!orderItems.isEmpty()) {
            Order order = new Order();
            order.setUserId(userId);
            order.setItems(orderItems);
            order.setOrderDate(new Date());
            service.placeOrder(order);
            System.out.println("Your order has been placed successfully.");
        } else {
            System.out.println("No items added to your order.");
        }
    }

    private static void viewUserInformation() {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        User user = service.getUserById(userId);
        if (user != null) {
            System.out.println("User Information:");
            System.out.println("ID: " + user.getId());
            System.out.println("Name: " + user.getName());
            System.out.println("Email: " + user.getEmail());
        } else {
            System.out.println("User not found.");
        }
    }
}
