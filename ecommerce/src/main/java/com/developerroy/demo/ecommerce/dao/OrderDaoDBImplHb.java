package com.developerroy.demo.ecommerce.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.developerroy.demo.ecommerce.model.Cart;
import com.developerroy.demo.ecommerce.model.Order;
import com.google.gson.Gson;

public class OrderDaoDBImplHb implements OrderDao{

	
	private SessionFactory factory; 
	
	public OrderDaoDBImplHb(SessionFactory factory) {
		this.factory = factory;
	}

	
	public List<Order> getAllOrders() {

		Session session = factory.openSession();
		Transaction tx = null;
		List<Order> orders = null;
		try{
			tx = session.beginTransaction();
			orders = session.createQuery("FROM Order").list();
			Gson gson = new Gson();
			for(Order o:orders){
				o.setCart(gson.fromJson(o.getCartStr(), Cart.class));
			}
			tx.commit();
		}catch(HibernateException e){
			if(tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return orders;
	}

	@Override
	public void addOrder(Order o) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(o);
			tx.commit();
		}catch(HibernateException e){
			if(tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	@Override
	public Order getOrderById(int orId) {
		Session session = factory.openSession();
		Transaction tx = null;
		Order order = null;
		try {
			tx = session.beginTransaction();
			order = (Order) session.get(Order.class, orId);
			order.setCart(new Gson().fromJson(order.getCartStr(), Cart.class));
			tx.commit();
		}catch(HibernateException e){
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return order;
	}

	@Override
	public void updateOrder(Order o) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(o);
			tx.commit();
		}catch(HibernateException e){
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		
	}

	@Override
	public void deleteOrder(int orId) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Order order = (Order) session.get(Order.class, orId);
			session.delete(order);
			tx.commit();
		}catch(HibernateException e){
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		
	}

	@Override
	public List<Order> getOrdersByCustomer(String customer) {
		Session session = factory.openSession();
		Transaction tx = null;
		List<Order> orders = null;
		try {
			tx = session.beginTransaction();
			orders = session.createQuery("FROM Order AS O WHERE O.customer = :customer")
				.setParameter("customer", customer)
				.list();
			tx.commit();
		}catch(HibernateException e){
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return orders;
	}


	

}
