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
<div class="single">

<div class="container">
<div class="col-md-9">
	<div class="col-md-5 grid">		
		<div class="flexslider">
			<c:forEach items="${product.imagePaths}" var="imagePath">
			<ul class="slides">
				 <li data-thumb="${imagePath}">
			        <div class="thumb-image"> <img src="${imagePath}" data-imagezoom="true" class="img-responsive"> </div>
			    </li>
			</ul>
			</c:forEach>			  
		</div>
	</div>	
<div class="col-md-7 single-top-in">
		<div class="span_2_of_a1 simpleCart_shelfItem">
				<h3>${product.title}</h3>
			    <div class="price_single">
				  <span class="reducedfrom item_price">$${product.price}</span>
				  <c:if test="${product.salePrice != 0}">
   					 <br><span class="reducedfrom item_price">Sale: $${product.salePrice}</span>
				  </c:if>
				 
			
				 <div class="clearfix"></div>
				</div>
				
				<h4 class="quick">Quick Overview:</h4>
				
				<p class="quick_desc">${product.descrKey1}: ${product.descrValue1}</p>
				<p class="quick_desc">${product.descrKey2}: ${product.descrValue2}</p>
				<p class="quick_desc">${product.descrKey3}: ${product.descrValue3}</p>
				
				
	<script>
    $('.value-plus').on('click', function(){
    	var divUpd = $(this).parent().find('.value'), newVal = parseInt(divUpd.text(), 10)+1;
    	divUpd.text(newVal);
    });

    $('.value-minus').on('click', function(){
    	var divUpd = $(this).parent().find('.value'), newVal = parseInt(divUpd.text(), 10)-1;
    	if(newVal>=1) divUpd.text(newVal);
    });
	</script>
	<!--quantity-->
				
				
<!-- if user -->
	<c:if test="${sessionScope.logged && sessionScope.user.role == 1}">
		    <div class="wish-list">
			 	<ul>
			 		<li class="wish"><a href="#"><span class="glyphicon glyphicon-check" aria-hidden="true"></span>Add to Wishlist</a></li>
			 	</ul>
			 </div>
			 <!-- 
			 <div class="quantity"> 
							<div class="quantity-select">                           
								<div class="entry value-minus">&nbsp;</div>
								<div class="entry value"><span>1</span></div>
								<div class="entry value-plus active">&nbsp;</div>
							</div>
			 </div>
			-->
				<a href="#" class="add-to item_add hvr-skew-backward">Add to cart</a><br>
				<a href="/MyEmag/product/${product.productId}/review" class="add-to item_add hvr-skew-backward">Make review</a>
				<div class="clearfix"> </div>
	</c:if>
	</div>
</div>
			<div class="clearfix"> </div> 
			<!---->
			<div class="tab-head"> 
			
<!-- //if user -->

<!-- if admin -->
	
	<c:if test="${sessionScope.logged && sessionScope.user.role == 0}">
		<p class="quick_desc">Current quantity: ${product.quantity}</p>
		<!-- form CHANGE -->
		<form action="${product.productId}/changeQuantity" method="post">
		  Current quantity:<input  type="text" name="quantity" value="${product.quantity}">
		  <br>		 
		  <input class="quick_desc" type="submit" value="Update">
		</form> 
		<!-- END form CHANGE -->
		
		<!-- form DELETE -->
		<!-- <a href="${product.productId}/delete" class="add-to item_add hvr-skew-backward">Delete product</a>-->
		<form action="${product.productId}/delete" method="post">		 
		  <input class="add-to item_add hvr-skew-backward" type="submit" value="Delete">
		</form> 
		<!-- END form DELETE -->
		
		<!-- form DISCOUNT -->
		<c:if test="${sessionScope.messageDiscount!=null}">
			<c:out value="${sessionScope.messageDiscount}"/>
		</c:if>
		<a href="#" class="add-to item_add hvr-skew-backward">Set discount</a>	
		<form action="${product.productId}/setDiscount" method="post">
		Enter 1-100:<input placeholder="Percent number" type="text" name="discount" >
		  <br>		 
		  <input class="quick_desc" type="submit" value="Set discount">
		</form> 
		<!-- END form DISCOUNT -->
	</c:if>

<!-- //if admin -->
	
	<!-- test reviews -->
	<c:if test="${fn:length(product.reviews) gt 0}">
	<h1>Reviews</h1>	
		<c:forEach items="${product.reviews}" var="review">
		 <blockquote>  			
		    <header>
		      <span data-rating=5>
		        <i class=ion-star></i>
		        <i class=ion-star></i>
		        <i class=ion-star></i>
		        <i class=ion-star></i>
		        <i class=ion-star></i>
		      </span>
		      <i>${review.comment}</i><br>
		      <span>${review.date}</span><br>
		      <span>By <em>${review.user.username}</em></span>      
		    </header>
		 </blockquote>  
		</c:forEach>
	</c:if>
	<!-- //test reviews -->
		
	<div class="tab-content one">
  </div>
  <div class="clearfix"></div>
  </div>
			<!---->	
</div>
<!----->

<div class="col-md-3 product-bottom product-at">
			
<!--//menu-->
		</div>
		<div class="clearfix"> </div>
	</div>
</div>	
		
	<!--//content-->
<jsp:include page="insertFooter.jsp" />
</body>
</html>
