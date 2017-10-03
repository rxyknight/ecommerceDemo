<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>My Orders Detail</title>

</head>
<body>


	<h1>My Orders Detail</h1>
	


	<table border="1">
		<thead>
			<tr>
				<th>My Order No.</th>
				<th>Status</th>
				<th>Delivery Address</th>
				<th>Total Price</th>
				<th>Shipping Cost</th>
				<th>Final Cost</th>
				<th>View Items Info</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator status="stat" value="orders">
				<tr>
					<td><s:property value="#stat.count" /></td>
					<td><s:property value="status" /></td>
					<td><s:property value="address" /></td>
					<td><s:property value="totalPrice" /></td>
					<td><s:property value="shippingCost" /></td>
					<td><s:property value="finalCost" /></td>
					<td>
					<s:url var="itemInfoUrl" action="itemInfo">
									<s:param name="orderId">
										<s:property value="orderId" />
									</s:param>
								</s:url>
					<a href="${itemInfoUrl}">view</a>
					</td>

				</tr>
			</s:iterator>
	</table>
	<a href="<s:url action='regularEntry' namespace="regular" />">Back to Query Page</a>
	<a href="<s:url action='logout' namespace="/" />" >logout</a>


</body>
</html>