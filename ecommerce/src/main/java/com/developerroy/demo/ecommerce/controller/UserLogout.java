package com.developerroy.demo.ecommerce.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.*;
import com.opensymphony.xwork2.ActionSupport;

public class UserLogout extends ActionSupport implements SessionAware {

	private Map session;
	
	public void setSession(Map<String, Object> value) {
		session = value;
	}
	
	public String execute(){
		//session.remove("admin");
		HttpSession hSession = ServletActionContext.getRequest().getSession();
		hSession.removeAttribute("regular");
		hSession.removeAttribute("admin");
		session.clear();
		hSession.invalidate();
		return SUCCESS;
	}

}
