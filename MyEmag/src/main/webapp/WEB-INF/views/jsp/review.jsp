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
<link href="/MyEmag/css/order.css" rel="stylesheet" type="text/css" media="all" />	
<!--//theme-style-->
<script src="/MyEmag/js/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>

<jsp:include page="insertHeader.jsp" />
<div id="accordian">
		<div class="step" id="step1">			
			<div class="title">
				<h1 align="middle">Review</h1>
			</div>			
		</div>
	</div>	
<div class="container">
<div class="col-md-9" align="middle">	
		<div class="flexslider">
			 <c:set var="index" value="${0}"/>
			 <div class="thumb-image"> <img src="/MyEmag/image/${product.productId}/${index}" align="middle" data-imagezoom="true" class="img-responsive" height="350" width="350"> </div>
		</div>	
	<div class="col-md-9 single-top-in" align="middle">
		<div class="span_2_of_a1 simpleCart_shelfItem" style="width:800px; margin:0 auto;">
			<h3>${product.title}</h3>
			   <div class="price_single" >
				  <span class="reducedfrom item_price" >$${product.price}</span>
				  <c:if test="${product.salePrice != 0}">
   					 	<span class="reducedfrom item_price sale-bg-color" >Sale: $${product.salePrice}</span>
				  </c:if>
				<div class="clearfix"> </div>
				</div>
			</div>
		</div>
	
</div>
</div>

	<form method="post" action="review" >
		<div style="width: 400px;">
		</div>
		<div style="padding-bottom: 18px;">Rate this product<br/>
			<select id="rating" name="rating" style="width : 150px;" align="middle" class="form-control" required><option>5</option>
			<option>4</option>
			<option>3</option>
			<option>2</option>
			<option>1</option>
			</select>
		</div>
		<div style="padding-bottom: 18px;">Add comment<span style="color: red;"> *</span><br/>
			<textarea id="comment" ${readonly} name="comment"  rows="10" align="middle" class="form-control" required></textarea>
		</div>
		<div style="padding-bottom: 18px;"><input value="Submit" type="submit"></div>
	</form>

<script type="text/javascript">
	function validateForm() {
	if (isEmpty(document.getElementById('data_11').value.trim())) {
		alert('Reviewer is required!');
		return false;
	}
	if (isEmpty(document.getElementById('data_4').value.trim())) {
		alert('Title is required!');
		return false;
	}
	if (isEmpty(document.getElementById('data_8').value.trim())) {
		alert('Review is required!');
		return false;
	}
	if (!document.getElementById('data_9_0').checked && !document.getElementById('data_9_1').checked && !document.getElementById('data_9_2').checked ) {
		alert('Would you recommend this product? is required!');
		return false;}
		return true;
	}
	function isEmpty(str) { return (str.length === 0 || !str.trim()); }	
	function validateEmail(email) {
		var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,15}(?:\.[a-z]{2})?)$/i;
		return isEmpty(email) || re.test(email);
	}
</script>		
<jsp:include page="insertFooter.jsp" />
</body>
</html>
