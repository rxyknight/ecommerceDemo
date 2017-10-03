<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Place the order</title>

</head>
<body>


	<h1>Place the order</h1>
	<h2>Your cart info:</h2>


	<table border="1">
		<thead>
			<tr>
				<th>Item Title</th>
				<th>Item Quantity</th>
				<th>Item Description</th>
				<th>Picture</th>
				<th>Item Price</th>

			</tr>
		</thead>
		<tbody>
			<s:iterator value="items">
				<tr>
					<td><s:property value="product.title" /></td>
					<td><s:property value="quantity" /></td>
					<td><s:property value="product.description" /></td>
					<td><img src="<s:property value='product.imgUrl'/>"
						alt="image not available"></td>
					<td>$<s:property value="product.price" /></td>

				</tr>
			</s:iterator>
		<tfoot>
			<tr>
				<th>Total Price</th>
				<th>$<s:property value="totalPrice" /></th>
			</tr>
		</tfoot>
	</table>
	<s:form action="submitOrder" method="post" namespcae = "regular">
		<s:textfield name="address" label="Your Address"></s:textfield>
		<s:submit value="submit the order" />
	</s:form>



</body>
</html>