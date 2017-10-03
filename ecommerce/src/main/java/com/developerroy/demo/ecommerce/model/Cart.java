package com.developerroy.demo.ecommerce.model;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class Cart {
	private Map<String, CartItem> carts;
	static Log log = LogFactory.getLog(Cart.class);

	public Cart() {
		carts = new HashMap<String, CartItem>();
		log.info("new cart created.");
	}

	public void addItem(Product p) {
		log.info("Current cart has " + carts.size() + " items.");
		String productId = p.getProductId();
		CartItem ci = carts.get(productId);
		if (ci == null) {
			carts.put(productId, new CartItem(p, 1)); // add the new product
		} else { // increase the quantity
			ci.increaseQuantity();
		}
	}

	public Collection<CartItem> getItems() {

		return carts.values();
	}

	public void removeItem(Product p) {
		log.info("Current cart has " + carts.size() + " items.");

		String productId = p.getProductId();
		CartItem ci = carts.get(productId);

		ci.decreaseQuantity();

		if (ci.getQuantity() == 0) {
			carts.remove(productId);
		}

		// System.out.println("Cart: "+carts);
	}

	public Map<String, CartItem> getCarts() {
		return carts;
	}

	public void setCarts(Map<String, CartItem> carts) {
		this.carts = carts;
	}
	

}
