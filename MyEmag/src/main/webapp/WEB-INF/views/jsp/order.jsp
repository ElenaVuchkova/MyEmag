<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>MyEmag</title>
<link href="/MyEmag/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<!-- Custom Theme files -->
<!--theme-style-->
<link href="/MyEmag/css/style.css" rel="stylesheet" type="text/css" media="all" />	
<!--//theme-style-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Shopin Responsive web template, Bootstrap Web Templates, Flat Web Templates, AndroId Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--theme-style-->
<link href="/MyEmag/css/style4.css" rel="stylesheet" type="text/css" media="all" />	
<!--//theme-style-->
<script src="/MyEmag/js/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<jsp:include page="insertHeader.jsp" />

<form action="order" method="post" >
<h2>Order data<br /> </h2>
<div class="order-box">
	1. Delivery data<br />
		Delivery by courier<br />
		<div class="login-mail">
			<input type="text" name="address" placeholder="Enter your address" required="required" />
			<i class="glyphicon glyphicon-user"></i>
		</div>	
</div>
	
2. Payment method: <br />

	<select name ="wayToPay" >
		<c:forEach var="way" items="${waysToPay}">
	        <option  value="${way}"><c:out value="${way}"></c:out></option>
		</c:forEach>
    </select> <br />	

    
3. User information<br />

Ime : <c:out value="${sessionScope.username}"/>


4. Order price<br />

<c:out value="${sessionScope.price}"/>
<button type="submit" class="btn btn-primary btn-block btn-large">Order</button>
</form>
<jsp:include page="insertFooter.jsp" />

</body>
</html>