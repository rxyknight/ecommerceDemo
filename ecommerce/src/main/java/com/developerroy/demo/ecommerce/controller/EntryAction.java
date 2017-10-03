package com.developerroy.demo.ecommerce.controller;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class EntryAction extends ActionSupport {
	public String regularEntry(){
		return SUCCESS;
	}
	public String adminEntry(){
		return SUCCESS;
	}

}
