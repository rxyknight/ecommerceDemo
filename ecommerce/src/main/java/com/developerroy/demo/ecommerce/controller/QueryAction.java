package com.developerroy.demo.ecommerce.controller;

import java.util.List;

import com.developerroy.demo.ecommerce.dao.FlickrDao;
import com.developerroy.demo.ecommerce.model.Product;
import com.opensymphony.xwork2.ActionSupport;



public class QueryAction extends ActionSupport {
	private static final long serialVersionUID = 8345957447067709178L;
	private String keyword;
	private List<Product> products;

	public String queryProducts() {
		products = FlickrDao.getInstance().searchPhotos(keyword);
		System.out.println(products.toString());
		return SUCCESS;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	

	
}
