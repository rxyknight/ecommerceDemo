<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Function</title>
</head>
<body>
	<s:form action="showAllOrders" method="post" >
		<s:submit value="View All Orders" />
	</s:form>
<a href="<s:url action='logout' namespace="/" />" >logout</a>
</body>
</html>
