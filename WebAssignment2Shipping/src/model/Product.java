package model;

public class Product {
	public static final double BASEPRICE = 3.0;

	private String productId;
	private String title;
	private String description;
	private String imgUrl;
	private double price;

	public Product() {

	}
	
	public Product(String productId, String title, String description, String imgUrl) {
		
		this.productId = productId;
		this.title = title;
		this.description = description;
		this.imgUrl = imgUrl;
		if(description.length()==0){
			this.price = BASEPRICE;
		}else{
			String[] tgs = description.split(" ");
			this.price = BASEPRICE + tgs.length * 1.0;
		}
		
	}

	

	public String getProductId() {
		return productId;
	}

	public void setProductId(String iD) {
		this.productId = iD;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public static double getBaseprice() {
		return BASEPRICE;
	}

	public String toString() {
		/*
		 * System.out.println("Title: "+title);
		 * System.out.println("Tags: "+tags);
		 * System.out.println("farm: "+farmID);
		 * System.out.println("server: "+serverID);
		 * System.out.println("ID: "+id); System.out.println("secret: "+secret);
		 * System.out.println(imgUrl);
		 */
		return "productId: " + productId + " title: " + title
				+ " description: " + description + " imgUrl: " + imgUrl
				+ " price: " + price;
	}

}
