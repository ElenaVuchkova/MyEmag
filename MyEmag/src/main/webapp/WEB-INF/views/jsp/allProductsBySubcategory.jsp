<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>MyEmag</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<!-- Custom Theme files -->
<!--theme-style-->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />	
<!--//theme-style-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Shopin Responsive web template, Bootstrap Web Templates, Flat Web Templates, AndroId Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--theme-style-->
<link href="css/style4.css" rel="stylesheet" type="text/css" media="all" />	
<!--//theme-style-->
<script src="js/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
		
</head>
<body>

<jsp:include page="insertHeader.jsp" />

<div class="content">
			<div class="container">				
				<!--products-->
			<div class="content-mid">
				<h3>${subcategory}</h3>
				<label class="line"></label>
				<!-- if admin -->      
	            <c:if test="${sessionScope.logged && sessionScope.user.role == 0}">	             
	             <form action="${subcategory}/setDiscount" method="post">
				  Enter 1-100:<input placeholder="Percent number" type="text" name="discount" >
				  <br>		 
				  <input class="quick_desc" type="submit" value="Set discount">
				</form> 
	             
	            </c:if>
	             <!-- //if admin -->  
				
				<c:if test="${select!=null}">
					<c:out value="${select}"/>
				</c:if>
				<h2>Order by:</h2>
				<form action="${subcategory}" method="post">		 
					<select name="param">
						<option selected="selected" class="holder">Please select</option>
					    <option value="date">date</option>
					    <option value="price desc.">price desc.</option>
					    <option value="price asc.">price asc.</option>
					    <option value="most reviews">most reviews</option>
					    <option value="sale desc.">sale desc.</option>
					</select>
					<input type="submit" value="Submit" >
					</form>
		<c:if test="${sortedProducts == null}">
			<c:forEach items="${products}" var="product">	
				<div class="mid-popular">
					<div class="col-md-3 item-grid simpleCart_shelfItem">
					<div class=" mid-pop">
					<div class="pro-img">
						<c:set var="index" value="${0}"/>
						<img src="/MyEmag/image/${product.productId}/${index}" height=200 width=200 class="img-responsive" alt=""> 
						<div class="zoom-icon ">
						 <a class="picture" href="/MyEmag/image/${product.productId}/${index}" rel="title" class="b-link-stripe b-animate-go  thickbox"><i class="glyphicon glyphicon-search icon "></i></a>
						<a href="/MyEmag/product/${product.productId}"><i class="glyphicon glyphicon-menu-right icon"></i></a> 
						</div>
						</div>
						<div class="mid-1">
						<div class="women">
						<div class="women-top">
							<span><a href="single">${product.title}</a></span>
							</div>
							<div class="img item_add">
								<a href="#"><img src="images/ca.png" alt=""></a>
							</div>
							<div class="clearfix"></div>
							</div>
							<div class="mid-2">
								<span>$${product.price}</span>
								  <div class="block">
									<div class="starbox small ghosting"> </div>
								</div>								
								<div class="clearfix"></div>
							</div>
							
						</div>
					</div>
					</div>					
				</div>						
			</c:forEach>
			</c:if>
			
			<c:if test="${sortedProducts != null}">
			<c:forEach items="${sortedProducts}" var="sortedProduct">	
				<div class="mid-popular">
					<div class="col-md-3 item-grid simpleCart_shelfItem">
					<div class=" mid-pop">
					<div class="pro-img">
						<img src="${sortedProduct.productId}" height=200 width=200 class="img-responsive" alt=""> 
						<div class="zoom-icon ">
						 <c:set var="index" value="${0}"/>
						 <a class="picture" href="/MyEmag/image/${sortedProduct.productId}/${index}" rel="title" class="b-link-stripe b-animate-go  thickbox"><i class="glyphicon glyphicon-search icon "></i></a>
						<a href="product/${sortedProduct.productId}"><i class="glyphicon glyphicon-menu-right icon"></i></a> 
						</div>
						</div>
						<div class="mid-1">
						<div class="women">
						<div class="women-top">
							<span><a href="single">${sortedProduct.title}</a></span>
							</div>
							<div class="img item_add">
								<a href="#"><img src="images/ca.png" alt=""></a>
							</div>
							<div class="clearfix"></div>
							</div>
							<div class="mid-2">
								<span>$${sortedProduct.price}</span>
								  <div class="block">
									<div class="starbox small ghosting"> </div>
								</div>								
								<div class="clearfix"></div>
							</div>
							
						</div>
					</div>
					</div>					
				</div>						
			</c:forEach>
			</c:if>
			
			</div>				
			</div> 	
		</div>	
<jsp:include page="insertFooter.jsp" />
 
</body>
</html>