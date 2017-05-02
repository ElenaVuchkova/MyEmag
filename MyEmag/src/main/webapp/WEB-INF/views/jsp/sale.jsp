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
<div class="content">
	<div class="container">		
					
			<c:forEach items="${allProductsWithSale}" var="entry">	
			<div class="content-mid" style="display:table-row;">
				<h3 >${entry.key}</h3>
				<label class="line"></label>
		
				<c:forEach items="${entry.value}" var="product">			
					<div class="col-md-12 item-grid simpleCart_shelfItem" >
						<div class="mid-pop" style="display:table-cell;">						
							<div class="pro-img">
								<c:set var="index" value="${0}"/>
								<img src="/MyEmag/image/${product.productId}/${index}" height=200 width=200 class="img-responsive" alt=""> 
								<div class="zoom-icon ">
								 <a class="picture" href="/MyEmag/image/${product.productId}/${index}" rel="title" class="b-link-stripe b-animate-go  thickbox"><i class="glyphicon glyphicon-search icon "></i></a>
								<a href="product/${product.productId}"><i class="glyphicon glyphicon-menu-right icon"></i></a> 
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
   										<span class="mid-2 sale-bg-color">Sale: $${product.salePrice}</span>
							    	</c:if>	
									  <div class="block">
										<div class="starbox small ghosting"> </div>
									</div>								
									<div class="clearfix"></div>
								</div>							
							</div>
						</div>
					</div>
			  </c:forEach>
			</div>
		</c:forEach>		
	</div>					
</div>						

<jsp:include page="insertFooter.jsp" />
</body>
</html>