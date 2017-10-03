<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin login</title>
</head>
<body>
<h3>Please Login for admin entry</h3>

<s:form action="adminLogin" namespace="/">
 	  <s:textfield name="userName"  label="user Name" />
 	  <s:password name="password"  label="password" />
   	  <s:submit/>   	  
</s:form>	
</body>
</html>