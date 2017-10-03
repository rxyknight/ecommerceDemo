<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Address Invalid</title>
</head>
<body>
<h1>Address Invalid</h1>
	<s:form action="submitOrder" method="post">
		<s:textfield name="address" label="Your Address"></s:textfield>
		<s:submit value="submit the order" />
	</s:form>
	<s:form action="discardOrder" method="post">
		<s:submit value="Discard the order" />
	</s:form>
</body>
</html>