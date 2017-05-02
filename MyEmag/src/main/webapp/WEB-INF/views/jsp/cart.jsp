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

 <script type="text/javascript">
 
	 function changeQuantity(productId, value) {
		  	$.ajax({
				  url: "/MyEmag/setNewQuantity/" + productId + "/" + value,
				  type: "POST", //send it through get method
				  contentType : 'application/json; charset=utf-8',
				  dataType : 'json',
				  success: function(response) {
					  var changes = response.changes;
					  for(i = 0; i < changes.length; i++){
						  var price = (changes[i].messege).toFixed(2);
	 				 	 document.getElementById(changes[i].place).innerHTML = price + " $";
					  }	  
				  },
				  error: function(xhr) {
					  document.getElementById("status").innerHTML="error";
				  }
			}); 
 
	}
 
 </script>
		
</head>
<body>

<jsp:include page="insertHeader.jsp" />
<div class="check-out">
<div class="container">
	<div class="bs-example4" data-example-id="simple-responsive-table">
	    <div class="table-responsive">
	    <c:if test="${fn:length(sessionScope.cart) == 0}">
		   <h2 align="center" >Your cart is empty!</h2>
		</c:if>
		 <c:if test="${fn:length(sessionScope.cart) gt 0}">
	    		<table class="table-heading simpleCart_shelfItem">
					  <tr>
						<th class="table-grid">Item</th>	
						<th class="table-grid">Title</th>	
						<th>Quantity</th>	
						<th>Price</th>
						
					  </tr>
					  <c:forEach items="${sessionScope.cart}" var="entry">
					  <tr class="cart-header">
					  
					  	<c:set var="index" value="${0}"/>
						<td class="ring-in"><a href="/MyEmag/product/${product.productId}" class="at-in"><img src="/MyEmag/image/${entry.key.productId}/${index}" class="img-responsive"  alt=""></a>
						<div class="clearfix"> </div>
						</td>	
						
						
						<td>  ${entry.key.title} </td>				
								
						<td>						
						<select id="quantity" name = "cart[items][09154100][quantity]" 
						onchange="changeQuantity(${entry.key.productId}, this.value)">
							<c:forEach  var="i" begin="1" end="${entry.key.quantity}">
				                <option  value="${i}"><c:out value="${i}"></c:out></option>
				       		 </c:forEach>
				        </select> <br>	
						</td>
						<td id="${entry.key.productId}"> 
							<c:if test="${entry.key.salePrice != 0}">
			   					 $${entry.key.salePrice}
							  </c:if>
							  <c:if test="${entry.key.salePrice == 0}">
			   					 $${entry.key.price}
							  </c:if>
						</td>
						
						<td class="add-check">
						<form action="/MyEmag/cart/delete/${entry.key.productId}" method="post">
						<input class="item_add hvr-skew-backward" type="submit" value="Delete">
						</form>
						</td> 
					  </tr>
					  </c:forEach>
		</table>
		</c:if>
		</div>
	</div>
	<div class="produced">
	    <c:if test="${fn:length(sessionScope.cart) gt 0}">
		  <a href="/MyEmag/order" class="hvr-skew-backward">Continue</a>
		</c:if>
	</div>
</div>
</div>

<jsp:include page="insertFooter.jsp" />

</body>
</html>