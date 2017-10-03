package com.developerroy.demo.ecommerce.model;

public class CartItem {
		private Product product;
		private int quantity;
		public CartItem(){			
		}
		
		public CartItem(Product p, int q){
			this.product = p;
			this.quantity = q;
		}
		
		public Product getProduct() {
			return product;
		}
		public void setProduct(Product product) {
			this.product = product;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		/**
		 * Increase the quantity by 1
		 */
		public void increaseQuantity(){
			quantity++;
		}
		
		public void decreaseQuantity(){
			if(quantity>0)
				quantity--;
		}
}
