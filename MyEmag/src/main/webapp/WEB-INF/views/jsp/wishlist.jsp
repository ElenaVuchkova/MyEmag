<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<div class="check-out">
<div class="container">
	<div class="bs-example4" data-example-id="simple-responsive-table">
	    <div class="table-responsive">
	    <c:if test="${fn:length(favouriteProducts) == 0}">
		   <h2 align="center" >Your wishlist is empty!</h2>
		</c:if>
		 <c:if test="${fn:length(favouriteProducts) gt 0}">
	    	    <table class="table-heading simpleCart_shelfItem">
			  <tr>
				<th class="table-grid">Item</th>	
				<th class="table-grid">Title</th>		
				<th>Price</th>
			  </tr>
			  <c:forEach items="${favouriteProducts}" var="product">
			  <tr class="cart-header">
			    <c:set var="index" value="${0}"/>
				<td class="ring-in"><a href="/MyEmag/product/${product.productId}" class="at-in"><img src="/MyEmag/image/${product.productId}/${index}" class="img-responsive"  alt=""></a>
				<div class="clearfix"> </div>
				<td>  ${product.title} </td>		
				</td>
				<td> <c:if test="${product.salePrice != 0}">
	   					 $${product.salePrice}
					  </c:if>
					  <c:if test="${product.salePrice == 0}">
	   					 $${product.price}
					  </c:if>
				</td>
				
				<td class="add-check">
				<form action="/MyEmag/wishlist/addToCart/${product.productId}" method="post">
				<input class="item_add hvr-skew-backward" type="submit" value="Add to cart">
				</form>
				</td>	
				
				<td class="add-check">				
				<form action="/MyEmag/wishlist/delete/${product.productId}" method="post">
				<input class="item_add hvr-skew-backward" type="submit" value="Delete from wishlist">
				</form>
				</td> 
			  </tr>
			  </c:forEach>
		</table>
		</c:if>
		</div>
	</div>
</div>
</div>

<jsp:include page="insertFooter.jsp" />

</body>
</html>