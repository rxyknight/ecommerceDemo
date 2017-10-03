package com.developerroy.demo.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.developerroy.demo.ecommerce.model.Order;
import com.developerroy.demo.ecommerce.model.Cart;
import com.google.gson.Gson;




public class OrderDaoDBImpl implements OrderDao {
	private DataSource ds;
	private String getOrderByIdSQL = "SELECT * from orders where id = ?",
			getOrdersByCustomer = "SELECT * from orders where customer = ?",
			getAllOrdersSQL = "SELECT * from orders",
			deleteOrderSQL = "DELETE from orders where id = ?" ,
			insertOrderSQL = "INSERT into orders(status,customer,address,totalprice,shippingcost,finalcost,cart) values (?,?,?,?,?,?,?)" ,
			updateOrderSQL = "UPDATE orders set status=?, customer=?, address=?, totalprice=?, shippingcost=?, finalcost=?, cart=? where id = ?";
	Log log = LogFactory.getLog(OrderDaoDBImpl.class);
	public OrderDaoDBImpl() throws Exception{
		try{
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			// Look up our data source
			ds = (DataSource) envCtx.lookup("jdbc/Ecommence");			
		}catch (NamingException e){
			throw new Exception("cannot find database");
		}catch (Exception e){
			throw new Exception(e.getMessage());
		}
	}
	public List<Order> getAllOrders() {
		List<Order> orders = new ArrayList<Order>();
		try{
			Connection conn =ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(getAllOrdersSQL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
			Order order = new Order(rs.getInt("id"),
									rs.getString("status"),
									rs.getString("customer"),
									rs.getString("address"),
									rs.getDouble("totalprice"),
									rs.getDouble("shippingcost"),
									rs.getDouble("finalcost"));
				Gson gson = new Gson();
				String cartStr = rs.getString("cart");
				Cart cart = gson.fromJson(cartStr, Cart.class);
				order.setCart(cart);
				orders.add(order);
				
			}
			rs.close();
			ps.close();
			conn.close();
			return orders;
		}catch (Exception e){
			log.error("does not get orders!");
			return null;
		}
	}

	public void addOrder(Order o) {
		try{
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(insertOrderSQL);
			ps.setString(1, o.getStatus());
			ps.setString(2, o.getCustomer());
			ps.setString(3, o.getAddress());
			ps.setDouble(4, o.getTotalPrice());
			ps.setDouble(5, o.getShippingCost());
			ps.setDouble(6, o.getFinalCost());
			Gson gson = new Gson();
			String cartStr = gson.toJson(o.getCart());
			ps.setString(7, cartStr);
			ps.executeUpdate();
			ps.close();
			conn.close();
		}
		catch(SQLException e){
			log.error("can not insert order " + o);
		}
	}

	public Order getOrderById(int orId) {
		try{
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(getOrderByIdSQL);
			ps.setInt(1, orId);
			ResultSet rs = ps.executeQuery();
			Order o = null;
			if (rs.next()){
				o = new Order(rs.getInt("id"),
						rs.getString("status"),
						rs.getString("customer"),
						rs.getString("address"),
						rs.getDouble("totalprice"),
						rs.getDouble("shippingcost"),
						rs.getDouble("finalcost"));
					Gson gson = new Gson();
					String cartStr = rs.getString("cart");
					Cart cart = gson.fromJson(cartStr, Cart.class);
					o.setCart(cart);
			}
			rs.close();
			ps.close();
			conn.close();
			return o;
		}catch (SQLException e){
			return null;
		}
	}

	public void updateOrder(Order o) {
		try{
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(updateOrderSQL);
			ps.setString(1, o.getStatus());
			ps.setString(2, o.getCustomer());
			ps.setString(3, o.getAddress());
			ps.setDouble(4, o.getTotalPrice());
			ps.setDouble(5, o.getShippingCost());
			ps.setDouble(6, o.getFinalCost());
			Gson gson = new Gson();
			String cartStr = gson.toJson(o.getCart());
			ps.setString(7, cartStr);
			ps.setInt(8, o.getOrderId());
			ps.executeUpdate();
			ps.close();
			conn.close();
		}
		catch(SQLException e){
			log.error("can not update order " + o);
		}

	}

	public void deleteOrder(int orId) {
		try{
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(deleteOrderSQL);
			ps.setInt(1, orId);
			ps.executeUpdate();
			ps.close();
			conn.close();
		}
		catch(SQLException e){
			log.error("can not delete order " +orId);
		}
	}

	
	
	public List<Order> getOrdersByCustomer(String customer) {
		List<Order> orders = new ArrayList<Order>();
		try{
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(getOrdersByCustomer);
			ps.setString(1, customer);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
			Order order = new Order(rs.getInt("id"),
									rs.getString("status"),
									rs.getString("customer"),
									rs.getString("address"),
									rs.getDouble("totalprice"),
									rs.getDouble("shippingcost"),
									rs.getDouble("finalcost"));
				Gson gson = new Gson();
				String cartStr = rs.getString("cart");
				Cart cart = gson.fromJson(cartStr, Cart.class);
				order.setCart(cart);
				orders.add(order);
				
			}
			rs.close();
			ps.close();
			conn.close();
			return orders;
		}catch (Exception e){
			log.error("does not get orders!");
			return null;
		}
	}

}
