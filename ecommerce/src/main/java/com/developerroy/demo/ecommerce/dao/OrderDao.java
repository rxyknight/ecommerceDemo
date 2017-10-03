package com.developerroy.demo.ecommerce.dao;

import java.util.List;

import com.developerroy.demo.ecommerce.model.Order;



public interface OrderDao {
	public List<Order> getAllOrders();
	public void addOrder(Order o);
	public Order getOrderById(int orId);
	public void updateOrder(Order o);
	public void deleteOrder(int orId);	
	public List<Order> getOrdersByCustomer(String customer);
}
