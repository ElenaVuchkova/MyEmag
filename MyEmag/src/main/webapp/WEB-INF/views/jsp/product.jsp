<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form commandName="product" >
		Category<form:input path="category" cssErrorClass="error"/><br>
		Sub<form:input path="subcategory" cssErrorClass="error"/><br>
		Title<form:input path="title" cssErrorClass="error"/><br>
		Quantity<form:input path="quantity" cssErrorClass="error"/><br>
		Price<form:input path="price" cssErrorClass="error"/><br>
		Specifications<br>
		1.<form:input path="descrKey1" cssErrorClasss="error"/>
		<form:input path="descrValue1" cssErrorClass="error"/><br>
		2.<form:input path="descrKey2" cssErrorClass="error"/>
		<form:input path="descrValue2" cssErrorClass="error"/><br>
		3.<form:input path="descrKey3" cssErrorClass="error"/>
		<form:input path="descrValue3" cssErrorClass="error"/><br>
		<input type="submit" value="Create product"/>
	</form:form>
	
	

</body>
</html>