package com.developerroy.demo.ecommerce.model;

public class StatusData {
	private int orderId;
	private String status;
	public StatusData(){
		
	}
	public StatusData(int orderId, String status) {
		super();
		this.orderId = orderId;
		this.status = status;
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
	
}
