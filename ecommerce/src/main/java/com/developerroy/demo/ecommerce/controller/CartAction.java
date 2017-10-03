package com.developerroy.demo.ecommerce.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;



import org.apache.struts2.interceptor.SessionAware;

import com.developerroy.demo.ecommerce.dao.FlickrDao;
import com.developerroy.demo.ecommerce.model.*;
import com.opensymphony.xwork2.ActionSupport;



public class CartAction extends ActionSupport implements SessionAware {
	
	private String productId;
	private List<Product> products;
	private List<CartData> cartData;
	private double totalPrice;
	
	private Map session;
	
	/*************************************************************
	 * get session
	 *************************************************************/
	public void setSession(Map value){
		session=value;
	}
	

	/*********************Getters and Setters****************************************/
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	public List<CartData> getCartData() {
		return cartData;
	}

	public void setCartData(List<CartData> cartData) {
		this.cartData = cartData;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/************************ action methods ********************************************/
	public String addToCart(){
		/*try {
			Product product1 = new Product("1","test1","asd","/asd");
			Product product2 = new Product("2","test2","qwe","/qwe");
			Cart cart = new Cart();
			cart.addItem(product1);
			cart.addItem(product1);
			cart.addItem(product2);
			
			Order order = new Order();
			order.setOrderId(1);
			order.setCustomer("ge");
			order.setAddress("Wollongong");
			order.setTotalPrice(63.2);
			order.setFinalCost(100);
			order.setCart(cart);
			OrderDao oDao = DaoFactory.getInstance().getOrderDao();
			Order order = oDao.getOrderById(1);
			System.out.println(order.getOrderId());
			System.out.println(order.getCustomer());
			System.out.println(order.getAddress());
			System.out.println(order.getTotalPrice());
			System.out.println(order.getFinalCost());
			
			Cart cart = order.getCart();
			Collection<CartItem> ci = cart.getItems();
			for(CartItem c:ci){
				System.out.println(c.getProduct());
				System.out.println(c.getQuantity());
			}
			
			order.setStatus("shipped");
			oDao.updateOrder(order);
			oDao.deleteOrder(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		Object obj = session.get(Constants.CART);
		Cart cart = null;
		Product p = FlickrDao.getInstance().getProductById(productId);
		if (obj == null){
			cart = new Cart();
			cart.addItem(p);
			session.put(Constants.CART, cart);
		}else{
			cart = (Cart)obj;
			cart.addItem(p);
		}
		return SUCCESS;
	}
	
	public String removeFromCart(){
		Object obj = session.get(Constants.CART);
		Cart cart = null;
		Product p = FlickrDao.getInstance().getProductById(productId);
		if(obj ==null){
			return ERROR;
		}else{
			cart = (Cart)obj;
			for(CartItem it:cart.getItems()){
				if(it.getProduct().getProductId().equals(productId)){
					cart.removeItem(p);
					return SUCCESS;
				}
			}
			return ERROR;
			
		}
		
		
	}
	
	public String clearCart(){
		session.remove(Constants.CART);
		return SUCCESS;
	}
	
	// method to load cart
		public String loadCart(){
			
			Object obj = session.get(Constants.CART);
			Cart ca = (Cart)obj;
			if(obj!= null && ca.getItems().isEmpty()){
				session.remove(Constants.CART);
			}
			obj = session.get(Constants.CART);
			//System.out.println("CartAction: "+obj);
			Cart cart = null;
			if (obj == null){ // no cart info
				return Constants.NOCART;
			}else{
				cart = (Cart)obj;
			
				// The data will be access by cart_partial.jsp
				totalPrice = 0.0;
				cartData = new ArrayList<CartData>();
				for (CartItem ci: cart.getItems()){
					cartData.add(new CartData(ci.getProduct().getTitle(), 
											ci.getQuantity(),
											ci.getProduct().getPrice()));
					totalPrice += ci.getQuantity() * ci.getProduct().getPrice();
				}
				return SUCCESS;
			}
		}

}
