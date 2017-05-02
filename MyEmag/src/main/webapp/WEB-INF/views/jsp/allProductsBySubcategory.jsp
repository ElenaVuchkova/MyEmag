<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>MyEmag</title>
<link href="/MyEmag/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="/MyEmag/css/order.css" rel="stylesheet" type="text/css" media="all" />
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

<div class="content">
			<div class="container">				
				<!--products-->
			<div class="content-mid">
				<h3>${subcategory}</h3>
				<label class="line"></label>
				<!-- if admin -->      
	            <c:if test="${sessionScope.logged && sessionScope.user.role == 0}">	             
	             <form action="${subcategory}/setDiscount" method="post">      	             				
					<div id="wrap">
						<div id="accordian">
							<div class="step" id="step1">
								<div class="title">
									<h1>Set discount for subcategory</h1>
								</div>			
							 </div>
							 <div class="content" id="email">		
								 <div>
						       		<input placeholder="Percent number" type="number" min="1" max="100" name="discount"  >
						         </div>		
							 </div>
							  <div class="right" id="reviewed">
							<div id="complete">
								<button type="submit" class="big_button" id="complete" value = "Set discount">Save</button>     
							</div>
						</div> 
			 		</div>
			 	</div>
			</form> 
				
				
	             
	            </c:if>
	             <!-- //if admin -->  
				
				<c:if test="${select!=null}">
					<c:out value="${select}"/>
				</c:if>
				
				 <form action="${subcategory}" method="post">
					<div id="wrap">
						<div id="accordian">
							<div class="step" id="step1">
								<div class="title">
									<h1>Order by</h1>
								</div>			
							 </div>
						 	<div class="content" id="email">		
								<div>	 
									<select name="param">
										<option selected="selected" class="holder" >Please select</option>
									    <option value="date">date</option>
									    <option value="price desc.">price desc.</option>
									    <option value="price asc.">price asc.</option>
									    <option value="most reviews">most reviews</option>
									    <option value="sale desc.">sale desc.</option>
									</select>
								</div>
							</div>
								
							<div class="right" id="reviewed">
								<div id="complete">
									<button type="submit" class="big_button" id="complete">Order</button>     
								</div>
							</div> 
						</div>
					</div>
				</form>
				
		<c:if test="${sortedProducts == null}">
			<c:forEach items="${products}" var="product">	
					<div class=" mid-pop" style="display:table-cell;">
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
							
							<div class="clearfix"></div>
							</div>
							<div class="mid-2">
								<span>$${product.price}</span>
								<c:if test="${product.salePrice != 0}">
   									<span class="mid-2 sale-bg-color" style="width:50px; height:50px; ">Sale: $${product.salePrice}</span>
							    </c:if>	
								  <div class="block">
									<div class="starbox small ghosting"> </div>
								</div>								
								<div class="clearfix"></div>
							</div>
							
						</div>
					</div>						
			</c:forEach>
			</c:if>
			
			<c:if test="${sortedProducts != null}">
			<c:forEach items="${sortedProducts}" var="sortedProduct">				
					<div class=" mid-pop" style="display:table-cell;">
					<div class="pro-img">	
						<c:set var="index" value="${0}"/>
						<img src="/MyEmag/image/${sortedProduct.productId}/${index}" height=200 width=200 class="img-responsive" alt=""> 
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
							<div class="clearfix"></div>
							</div>
							<div class="mid-2">
								<span>$${sortedProduct.price}</span>
								<c:if test="${sortedProduct.salePrice != 0}">
   									<span class="mid-2 sale-bg-color">Sale: $${sortedProduct.salePrice}</span>
							    </c:if>	
								  <div class="block">
									<div class="starbox small ghosting"> </div>
								</div>								
								<div class="clearfix"></div>
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