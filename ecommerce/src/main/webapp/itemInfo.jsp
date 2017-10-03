<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Items Info</title>

</head>
<body>


	<h1>Items Info</h1>
	


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
	</table>
	<a href="<s:url action='myOrders' namespace="regular" />">Back</a>


</body>
</html>