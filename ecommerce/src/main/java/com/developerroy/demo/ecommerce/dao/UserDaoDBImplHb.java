package com.developerroy.demo.ecommerce.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import com.developerroy.demo.ecommerce.model.User;





public class UserDaoDBImplHb implements UserDao {

	private SessionFactory factory;
	
	
	
	public UserDaoDBImplHb(SessionFactory factory) {
		super();
		this.factory = factory;
	}



	@Override
	public User getUserByUserName(String userName) {
		Session session = factory.openSession();
		Transaction tx = null;
		User user = null;
		try{
			tx = session.beginTransaction();
			user = (User) session.get(User.class, userName);
			tx.commit();
		}catch(HibernateException e){
			if(tx!=null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return user;
	}
	
	
}
