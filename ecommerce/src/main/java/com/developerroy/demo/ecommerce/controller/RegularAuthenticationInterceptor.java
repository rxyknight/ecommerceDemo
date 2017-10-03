package com.developerroy.demo.ecommerce.controller;

import java.util.Map;

import com.developerroy.demo.ecommerce.model.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class RegularAuthenticationInterceptor implements Interceptor {

    public void init() {}
    public void destroy () {}
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		 	Map session = actionInvocation.getInvocationContext().getSession();

	        User user = (User) session.get("regular");

	        if (user == null) {
	            return "regularlogin";            
	        }
	        else {
	            return actionInvocation.invoke();
	        }
	}
}
