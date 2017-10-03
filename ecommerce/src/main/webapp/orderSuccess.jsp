<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Order Success</title>

</head>
<body>


	<h1>Order Success</h1>



	<table border="1">
		<thead>
			<tr>
				<th>Custom</th>
				<th><s:property value="order.customer" /></th>
			</tr>
			<tr>
				<th>Status</th>
				<th><s:property value="order.status" /></th>
			</tr>
			<tr>
				<th>Delivery Address</th>
				<th><s:property value="order.address" /></th>
			</tr>
			<tr>
				<th>Item Title</th>
				<th>Item Quantity</th>
				<th>Item Description</th>
				<th>Picture</th>
				<th>Item Price</th>

			</tr>
		</thead>
		<tbody>
			<s:iterator value="order.items">
				<tr>
					<td><s:property value="product.title" /></td>
					<td><s:property value="quantity" /></td>
					<td><s:property value="product.description" /></td>
					<td><img src="<s:property value='product.imgUrl'/>"
						alt="image not available"></td>
					<td>$<s:property value="product.price" /></td>

				</tr>
			</s:iterator>
		</tbody>
		<tfoot>
			<tr>
				<th>Total Price</th>
				<th>$<s:property value="order.totalPrice" /></th>
			</tr>
			<tr>
				<th>Shipping Cost</th>
				<th>$<s:property value="order.shippingCost" /></th>
			</tr>
			<tr>
				<th>Final Cost</th>
				<th>$<s:property value="order.finalCost" /></th>
			</tr>
		</tfoot>
	</table>
	<a href="<s:url action='regularEntry' namespace="regular" />">Back
		to Query Page</a>
	<a href="<s:url action='logout' namespace="/" />">logout</a>


</body>
</html>