package rest;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;



import model.CartItem;
import model.Order;

@Path("/shipping")
public class ShippingComponent {
	//private OrderDao oDao = DaoFactory.getInstance().getOrderDao();
	@Context
	UriInfo uriInfo;

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response caculateFees(JAXBElement<Order> orderXML) {
		//System.out.println("starting");
		Response res = null;
		Order order = orderXML.getValue();

		System.out.println("receiving order");
		try {
			//InputStream is = new FileInputStream(
			//		"/usr/local/apache-tomcat-7.0.52/webapps/WebAssignment2Shiping/WEB-INF/classes/costSheet.xml");
			InputStream is = this.getClass().getResourceAsStream("/costSheet.xml");
			DocumentBuilder db = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = db.parse(is);
			NodeList nl = doc.getElementsByTagName(order.getAddress()
					.toLowerCase());
			if (nl.item(0) == null) {
				System.out.println("Address Invalid!");
				order.setAddress("invalid");;
				res = Response.ok(order).build();
				return res;
			} else {
				String deliveryFeeStr = nl.item(0).getTextContent();
				Double deliveryFee = Double.parseDouble(deliveryFeeStr);
				nl = doc.getElementsByTagName("per-item");
				String perItemFeeStr = nl.item(0).getTextContent();
				Double perItemFee = Double.parseDouble(perItemFeeStr);
				int totalNumber = 0;
				for (CartItem ci : order.getItems()) {
					totalNumber += ci.getQuantity();
				}
				double shippingCost = totalNumber * perItemFee + deliveryFee;
				order.setShippingCost(shippingCost);
				order.setFinalCost(shippingCost + order.getTotalPrice());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("finishing cost computation");
		System.out.println("-----------Order detail----------");
		System.out.println("Custom: " + order.getCustomer());
		System.out.println("----Item info--------");
		for (CartItem ci : order.getItems()) {
			System.out.println("ItemNo: "+ci.getProduct().getProductId());
			System.out.println("ItemTitle: "+ci.getProduct().getTitle());
			System.out.println("Item quantity: "+ci.getQuantity());
			System.out.println("Item description: "+ci.getProduct().getDescription());
			System.out.println("Item price: "+ci.getProduct().getPrice());
			System.out.println("Photo url: "+ci.getProduct().getImgUrl());
		}
		System.out.println("----Item info end----");
		System.out.println("Delivery Address: " + order.getAddress());
		System.out.println("Total Price: " + order.getTotalPrice());
		System.out.println("Shipping Cost: " + order.getShippingCost());
		System.out.println("Final Cost: " + order.getFinalCost());
		System.out.println("-----------Detail end----------");
		res = Response.ok(order).build();
		return res;

	}

	
}
