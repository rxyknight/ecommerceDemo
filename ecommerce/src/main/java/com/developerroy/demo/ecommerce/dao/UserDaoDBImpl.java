package com.developerroy.demo.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.developerroy.demo.ecommerce.model.User;




public class UserDaoDBImpl implements UserDao {
	private DataSource ds;
	private String getUserByUserName = "SELECT * from users where username = ?";
	Log log = LogFactory.getLog(UserDaoDBImpl.class);
	
	public UserDaoDBImpl() throws Exception{
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
	public User getUserByUserName(String userName) {
		try{
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(getUserByUserName);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			User u = null;
			if (rs.next()){
				u = new User(rs.getString("username"),
						rs.getString("password"),
						rs.getString("role"));
			}
			rs.close();
			ps.close();
			conn.close();
			return u;
		}catch (SQLException e){
			log.error(e);
			return null;
		}
		
	}
	
	public static void main(String args[]){
		try {
			UserDao ud = new UserDaoDBImpl();
			User user = ud.getUserByUserName("xiangyu");
			System.out.println(user.getPassword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
