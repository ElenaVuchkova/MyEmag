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
	    <c:if test="${fn:length(sessionScope.cart) == 0}">
		   <p>Your wishlist is empty!<p>
		</c:if>
		 <c:if test="${fn:length(fovuriteProducts) gt 0}">
	    	    <table class="table-heading simpleCart_shelfItem">
			  <tr>
				<th class="table-grid">Item</th>		
				<th>Prices</th>
			  </tr>
			  <c:forEach items="${fovuriteProducts}" var="product">
			  <tr class="cart-header">
				<td class="ring-in"><a href="product" class="at-in"><img src="${product.imagePaths[0]}" class="img-responsive"  alt=""></a>
				
				<div class="clearfix"> </div>
				<div class="close1"> </div></td>
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