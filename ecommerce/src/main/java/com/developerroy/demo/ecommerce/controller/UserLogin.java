package com.developerroy.demo.ecommerce.controller;

import java.util.Map;



import org.apache.struts2.interceptor.SessionAware;


import com.developerroy.demo.ecommerce.dao.UserDao;
import com.developerroy.demo.ecommerce.model.User;
import com.opensymphony.xwork2.ActionSupport;



import org.apache.struts2.ServletActionContext;

public class UserLogin extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private String userName, password;
	private String redirectUrl; // use for redirection
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/******************* Session aware code ******************/
	private Map session;

	public void setSession(Map session) {
		this.session = session;
	}

	/********************** action method *******************/
	public String regularLogin() {
		/*
		 * if(ServletActionContext.getRequest().getMethod().equals("GET")){
		 * session.remove("redirectUrl"); }
		 */
		User regularUser = userDao.getUserByUserName(userName);

		if (regularUser != null && !regularUser.getPassword().equals(password)) {
			regularUser = null;
		}
		if (regularUser != null && regularUser.getPassword().equals(password)
				&& !(regularUser.getRole().equals("regular"))) {
			regularUser = null;
		}
		if (regularUser == null) {
			Object obj = session.get("redirectUrl");
			if (obj == null) {
				redirectUrl = ServletActionContext.getRequest().getHeader(
						"referer");
				if (redirectUrl == null)
					redirectUrl = "/index.jsp";
				session.put("redirectUrl", redirectUrl);
			}

			return INPUT;
		} else {
			session.put("regular", regularUser);

			// Prepare to get redirection url
			Object obj = session.get("redirectUrl");
			if (obj == null) {
				redirectUrl = ServletActionContext.getRequest().getHeader(
						"referer");
				if (redirectUrl == null)
					redirectUrl = "/index.jsp";
			} else {
				redirectUrl = (String) session.get("redirectUrl");
				session.remove("redirectUrl");
			}

			return SUCCESS;
		}

	}

	public String adminLogin() {
		User adminUser = userDao.getUserByUserName(userName);

		if (adminUser != null && !adminUser.getPassword().equals(password)) {
			adminUser = null;
		}
		if (adminUser != null && adminUser.getPassword().equals(password)
				&& !(adminUser.getRole().equals("admin"))) {
			adminUser = null;
		}
		if (adminUser == null) {
			Object obj = session.get("redirectUrl");
			if (obj == null) {
				redirectUrl = ServletActionContext.getRequest().getHeader(
						"referer");
				if (redirectUrl == null)
					redirectUrl = "/index.jsp";
				session.put("redirectUrl", redirectUrl);
			}

			return INPUT;
		} else {
			session.put("admin", adminUser);

			// Prepare to get redirection url
			Object obj = session.get("redirectUrl");
			if (obj == null) {
				redirectUrl = ServletActionContext.getRequest().getHeader(
						"referer");
				if (redirectUrl == null)
					redirectUrl = "/index.jsp";
			} else {
				redirectUrl = (String) session.get("redirectUrl");
				session.remove("redirectUrl");
			}

			return SUCCESS;
		}
	}

	/*********** getters/setters ****************************/
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

}
