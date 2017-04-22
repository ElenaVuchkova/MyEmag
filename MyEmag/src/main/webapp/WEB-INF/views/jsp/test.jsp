<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	gffjgfjgf
	<c:forEach items="${allproducts}" var="product">			
			Title:<c:out value="${product.value.title}"></c:out><br>
			Price:<c:out value="${product.value.price}"></c:out><br>
			Path::<c:out value="${product.value.imagePaths[0]}"></c:out><br>
			<img src="${product.value.imagePaths[0]}"> <br>		
	</c:forEach>

</body>
</html>