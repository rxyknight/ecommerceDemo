<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/style_new.css"
	media="screen" />
<script language="JavaScript" type="text/JavaScript"
	src="../javascript/status.js"></script>


<title>All Orders</title>
</head>
<body>



	<h1>All Orders</h1>


	<table border="1">
		<thead>
			<tr>
				<th>Order ID</th>
				<th>Customer</th>
				<th>Status</th>
				<th>Operation</th>
			</tr>
		</thead>
		<tbody>

			<s:iterator value="orders">

				<tr>
					<td><s:property value="orderId" /></td>
					<td><s:property value="customer" /></td>
					<td id='${orderId}'><s:property value="status" /></td>
					<td><s:url var="statusUrl" action="updateStatus"
							namespace="/admin">
							<s:param name="orderId">
								<s:property value="orderId" />
							</s:param>
						</s:url> <a href="javascript:status('${statusUrl}')"> Update</a> <s:url
							var="orderDeatilUrl" action="orderDeatil" namespace="/admin">
							<s:param name="orderId">
								<s:property value="orderId" />
							</s:param>
						</s:url> <a href="javascript:orderDetail('${orderDeatilUrl}')"> View
							Detail</a></td>
				</tr>

			</s:iterator>
	</table>


	<p id="confirmation"></p>
	<h2>Order detail:</h2>
	<table border="1" id="detailcontent">

	</table>
	<a href="<s:url action='adminEntry' namespace="/admin" />">Back</a>
	<a href="<s:url action='logout' namespace="/" />">logout</a>
</body>
</html>