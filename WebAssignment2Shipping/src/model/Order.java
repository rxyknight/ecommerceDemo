package model;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Order {
	private int orderId;
	private String status="processing";
	private String customer;
	private String address;
	private double totalPrice;
	private double shippingCost;
	private double finalCost;
	private Cart cart;
	
	public Order(){
		
	}

	public Order(int orderId, String status, String customer, String address,
			double totalPrice, double shippingCost, double finalCost) {
		super();
		this.orderId = orderId;
		this.status = status;
		this.customer = customer;
		this.address = address;
		this.totalPrice = totalPrice;
		this.shippingCost = shippingCost;
		this.finalCost = finalCost;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public Collection<CartItem> getItems() {
		return cart.getItems();
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getFinalCost() {
		return finalCost;
	}

	public void setFinalCost(double finalCost) {
		this.finalCost = finalCost;
	}

	public double getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
	}
	
	public String toString(){
		return "OrderID: "+orderId+" Customer: "+customer+" ... ";
		
	}
	
	
}
