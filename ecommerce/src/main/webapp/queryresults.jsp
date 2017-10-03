<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/style_new.css"
	media="screen" />
<title>Catalogue page version II</title>
<sx:head />
</head>
<body>
	<div id="wrap">

		<div id="header">
			<h1>COMP5347 Assignment 2</h1>
			<h2>Query results page</h2>
		</div>

		<div id="catalogue">

			<h1>Query Result:</h1>

			<table border="1">
				<thead>
					<tr>
						<th>Item Title</th>
						<th>Item Description</th>
						<th>Picture</th>
						<th>Item Price</th>
						<th>Quantity</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="products">
						<tr>
							<td><s:property value="title" /></td>
							<td><s:property value="description" /></td>
							<td><img src="<s:property value='imgUrl'/>"
								alt="image not available"></td>
							<td>$<s:property value="price" /></td>
							<td><s:url var="addCartUrl" action="addToCart">
									<s:param name="productId">
										<s:property value="productId" />
									</s:param>
								</s:url> <sx:a afterNotifyTopics="loadcart" href="%{addCartUrl}">Add to Cart</sx:a>
								<s:url var="removeCarUrl" action="removeFromCart">
									<s:param name="productId">
										<s:property value="productId" />
									</s:param>
								</s:url> <sx:a afterNotifyTopics="loadcart" href="%{removeCarUrl}">Remove from Cart</sx:a>
							</td>

						</tr>
					</s:iterator>
			</table>
			<a href="<s:url action='regularEntry' namespace="regular" />">Back to Query Page</a>
			<s:url var="clearCartUrl" action="clearCart"></s:url>
			<sx:a afterNotifyTopics="loadcart" href="%{clearCartUrl}">Clear Cart</sx:a>
		</div>



		<s:url var="loadCartUrl" action="loadCart" />
		<sx:div id="cart" href="%{loadCartUrl}" listenTopics="loadcart">

		</sx:div>
	</div>
</body>
</html>