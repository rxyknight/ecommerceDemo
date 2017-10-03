package model;

import java.util.*;

public class Cart {
	private Map<String, CartItem> carts;
	

	public Cart() {
		carts = new HashMap<String, CartItem>();
		
	}

	public void addItem(Product p) {
		
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
