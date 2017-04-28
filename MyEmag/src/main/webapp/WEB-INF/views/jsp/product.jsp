<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>MyEmag</title>
<link href="/MyEmag/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="/MyEmag/css/test.css" rel="stylesheet" type="text/css" media="all" />

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
<style>

 #wrapper {
  display: inline-block;
    padding-bottom: 2em;
    width: 100%;
  }
  
  #leftCarousel {
	float: left;
	width: 350px;
  }
  #rightInfo {
  float:right;
  }

</style>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="insertHeader.jsp" />
<div class="single">

<div class="container">
<div class="col-md-12">

<!-- slide images -->

	<div id="wrapper"> 
		<div class="container" id="leftCarousel">		
		  <div id="myCarousel" class="carousel slide" data-ride="carousel">		    	
		    <!-- Wrapper for slides -->
		    <div class="carousel-inner" >
		    
		    <div class="item active" >
		    	<c:set var="index" value="${0}"/>
		        <img src="/MyEmag/image/${product.productId}/${index}"  style="width:100%;">
		      </div> 
		    
		    <c:forEach var="i" begin="1" end="${fn:length(product.imagePaths)-1}">
		      <div class="item">
		        <img src="/MyEmag/image/${product.productId}/${i}"  style="width:100%;">
		      </div> 
			</c:forEach>
		     
		    </div>
		
		    <!-- Left and right controls -->
		    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
		      <span class="glyphicon glyphicon-chevron-left"></span>
		      <span class="sr-only">Previous</span>
		    </a>
		    <a class="right carousel-control" href="#myCarousel" data-slide="next">
		      <span class="glyphicon glyphicon-chevron-right"></span>
		      <span class="sr-only">Next</span>
		    </a>
		  </div>
		</div>


<!-- //slide images -->
	
<div class="col-md-7 single-top-in" id="rightInfo">
		<div class="span_2_of_a1 simpleCart_shelfItem">
				<h3>${product.title}</h3>
			    <div class="price_single">
				  <span class="reducedfrom item_price">$${product.price}</span>
				  <c:if test="${product.salePrice != 0}">
   					<span class="reducedfrom item_price sale-bg-color">Sale: $${product.salePrice}</span>
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
			 		<li class="wish">
			 		<c:if test="${message!=null}">
						<c:out value="${message}"/>
					</c:if>
				 		<form class="wish-button" action="/MyEmag/product/${product.productId}/addToWishList" method="post">		 
			  				<input class="add-to item_add hvr-skew-backward" type="submit" value="Add to wishlist">
						</form> 
						<form class="wish-button" action="/MyEmag/product/${product.productId}/addToCart" method="post">		 
		  					<input class="add-to item_add hvr-skew-backward" type="submit" value="Add to cart">
						</form>
						<form class="wish-button" action="/MyEmag/product/${product.productId}/review" method="get">		 
					  				<input class="add-to item_add hvr-skew-backward" type="submit" value="Make review">
						</form>  
			 		</li>
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
			 
			 	
				<div class="clearfix"> </div>
	</c:if>
	</div>
</div>
			<div class="clearfix"> </div> 
			<!---->
			<div class="tab-head"> 
			
<!-- //if user -->

<!-- if admin -->
	
	<div class="admin-panel">
		<c:if test="${sessionScope.logged && sessionScope.user.role == 0}">
			<!-- form CHANGE -->
			<form action="${product.productId}/changeQuantity" method="post">
				Current quantity:
			  	<input  type="text" name="quantity" value="${product.quantity}" />	 
			  	<input type="submit" value="Update" />
			</form> 
			<!-- END form CHANGE -->
			
			<!-- form DISCOUNT -->
			<c:if test="${sessionScope.messageDiscount!=null}">
				<c:out value="${sessionScope.messageDiscount}"/>
			</c:if>
			<form action="/MyEmag/product/${product.productId}/setDiscount" method="post">
			Enter 1-100:
				<input type="text" name="discount" placeholder="Percent number" />
			  	<input type="submit" value="Set discount" />
			</form> 
			<!-- END form DISCOUNT -->
	
			<!-- form DELETE -->
			<form action="/MyEmag/product/${product.productId}/delete" method="post">		 
			  <input type="submit" value="Delete">
			</form> 
			<!-- END form DELETE -->
			
		</c:if>
	</div>
<!-- //if admin -->
	<div class="amazing-reviews">
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
	
	
</div>	
	<!--//content-->
<jsp:include page="insertFooter.jsp" />
</body>
</html>
