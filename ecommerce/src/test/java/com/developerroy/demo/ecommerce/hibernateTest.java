package com.developerroy.demo.ecommerce;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.developerroy.demo.ecommerce.model.Cart;
import com.developerroy.demo.ecommerce.model.Order;
import com.developerroy.demo.ecommerce.model.User;
import com.google.gson.Gson;

public class hibernateTest {
	
	private static SessionFactory factory; 
	
	@BeforeClass
	public static void init(){
		factory = new Configuration().configure().buildSessionFactory();
	}
	
	
	public void testUserReadAll(){
		Session session = factory.openSession();
		List<User> users = session.createQuery("FROM User").list();
		for(User u:users){
			System.out.println("User name:"+u.getUserName()+" pwd:"+u.getPassword()+" role:"+u.getRole());
		}
	    
	}
	
	
	public void testUserReadByName(){
		Session session = factory.openSession();
		User u = (User) session.get(User.class, "junting");
		System.out.println("User name:"+u.getUserName()+" pwd:"+u.getPassword()+" role:"+u.getRole());
	}
	
	
	public void testOrderReadAll(){
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			List<Order> orders = session.createQuery("FROM Order").list();
			Gson gson = new Gson();
			for(Order o:orders){
				o.setCart(gson.fromJson(o.getCartStr(), Cart.class));
				System.out.println(o);
			}
			tx.commit();
		}catch(HibernateException e){
			if(tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		
	}
	
	
	public void testAddOrder(){
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Order order = new Order(3, "test", "testc", "testa", 0.13, 0.12 , 0.12);
			order.setCartStr("");
			session.save(order);
			tx.commit();
		}catch(HibernateException e){
			if(tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		
	}
	
	
	public void testAddUser(){
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			User user = new User("testUsername", "testpwd", "testRole");
			session.save(user);
			tx.commit();
		}catch(HibernateException e){
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	
	public void testGetOrderById(){
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Order order = (Order) session.get(Order.class, 2);
			System.out.println(order);
			tx.commit();
		}catch(HibernateException e){
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	
	public void testUpdateOrder(){
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Order order = new Order(7, "test", "testccccc", "testa", 0.13, 0.12 , 0.12);
			order.setCartStr("");
			session.update(order);
			tx.commit();
		}catch(HibernateException e){
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	@Test
	@Ignore
	public void testGetOrdersByCustomer(){
		Session session = factory.openSession();
		Transaction tx = null;
		List<Order> orders = null;
		try {
			tx = session.beginTransaction();
			orders = session.createQuery("FROM Order AS O WHERE O.customer = :customer")
				.setParameter("customer", "ge")
				.list();
			tx.commit();
		}catch(HibernateException e){
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		for(Order o:orders){
			System.out.println(o);
		}
	}
	
}
