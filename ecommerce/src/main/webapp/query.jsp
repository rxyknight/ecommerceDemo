<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Query Pages</title>
</head>
<body>
	<s:form action="queryProducts" method="post" >
		<s:textfield name="keyword" label="Please Query"></s:textfield>
		<s:submit value="Query" />
	</s:form>
<a href="<s:url action='myOrders' namespace="/regular" />" >view my orders</a>	
<a href="<s:url action='logout' namespace="/" />" >logout</a>
</body>
</html>
