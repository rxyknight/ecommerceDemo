<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>shopping cart table</title>
</head>
<body>
	<h2>Items in cart :</h2>
	<table>
		<s:iterator value="cartData">
			<tr>
				<td><s:property value="title" /></td>
				<td><s:property value="quantity" /></td>
			</tr>
		</s:iterator>
		<tfoot>
			<tr>
				<th>Total:</th>
				<th>$<s:property value="totalPrice" /></th>
			</tr>
		</tfoot>
	</table>
	<s:url var="clearCartUrl" action="clearCart"></s:url>
	<sx:a afterNotifyTopics="loadcart" href="%{clearCartUrl}">Clear Cart</sx:a>
	
	<s:url var="checkOutUrl" action="checkOut"></s:url>
	<a href="${checkOutUrl}">Check Out</a>
	
</body>
</html>