<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<title>login</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<!-- Custom Theme files -->
<!--theme-style-->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />	
<!--//theme-style-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Shopin Responsive web template, Bootstrap Web Templates, Flat Web Templates, AndroId Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />

<link href="css/style4.css" rel="stylesheet" type="text/css" media="all" />	
<!--//theme-style-->
<script src="js/jquery.min.js"></script>

</head>
<body>


<!--banner-->
<div class="banner-top">
	<div class="container">
		<h1>Login</h1>
		<em></em>
		<h2><a href="index">Home</a><label>/</label>Login</h2>
	</div>
</div>
	<!-- Messege for login failed -->
	<c:if test="${sessionScope.login!=null}">
		<c:out value="${sessionScope.login}"/>
	</c:if>
	
<!--login-->
	<div class="container">
		<div class="login">
			<f:form action="login" method="post" commandName="user">			
			<div class="col-md-6 login-do">
			<div class="login-mail">
					<f:input path="username" type="text" placeholder="Username" required=""></f:input>
					<i  class="glyphicon glyphicon-user"></i>
			</div>				
				<div class="login-mail">
					<f:input  path="password" type="password" placeholder="Password" required=""></f:input>				
					<i class="glyphicon glyphicon-lock"></i>
				</div>							
				<label class="hvr-skew-backward">
					<input type="submit" value="Submit">
				</label>
			</div>
			</f:form>
		</div>
	</div>

 <jsp:include page="insertFooter.jsp" />

</body>
</html>