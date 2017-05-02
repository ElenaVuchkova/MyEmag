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
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<jsp:include page="insertHeader.jsp" />

<form action="order" method="post" >
				
<div id="wrap">
	<div id="accordian">
		<div class="step" id="step1">
			<div class="number">
				<span>1</span>
			</div>
			<div class="title">
				<h1>Delivery data</h1>
			</div>			
		</div>
	
		<div class="content" id="email">		
		<div>
        <input type="text" name="address" id="email-address" placeholder="Address" required/><label for="email">Address</label>
        </div>		
		</div>
		
		<div class="step" id="step2">
			<div class="number">
				<span>2</span>
			</div>
			<div class="title">
				<h1>User Information</h1>
			</div>
		</div>
		
		
		<div class="content" id="address">			
			<div>
			<input type="text" name="first_name" value="${sessionScope.username}" id="first_name" readonly />
       		</div>	
       		<div>
			<input type="text" name="first_name" value="${sessionScope.user.email}" id="first_name" readonly />
       		</div>					
		</div>
	
		<div class="step" id="step3">
			<div class="number">
				<span>3</span>
			</div>
			<div class="title">
				<h1>Payment Information</h1>
			</div>
		</div>
		<div class="content" id="payment">
			<div class="left credit_card">				
				<div>
				  <div class="expiry">	
				      <div class="month_select">
				       <select name ="wayToPay" required >
							<c:forEach var="way" items="${waysToPay}">
					       	 <option  value="${way}"><c:out value="${way}"></c:out></option>
							</c:forEach>
			    		</select> <br />	
                      </div>              
            	   </div>
               </div>                    
          </div>
          
          <div class="right">
			<div class="accepted">
				<span><img src="http://i.imgur.com/Pu4e7AT.png" height="60" width="60"></span>
				<span><img src="http://i.imgur.com/ewMjaHv.png" height="60" width="60"></span>
				<span><img src="http://i.imgur.com/3LmmFFV.png" height="60" width="60"></span>
			</div>			
		</div>
		
	   </div>
 		
 		<div class="step" id="step5">
			<div class="number">
				<span>4</span>
			</div>
			<div class="title">
				<h1>Finalize Order</h1>
			</div>
		</div>
		
		<div class="content" id="final_products">
			<div class="left" id="ordered">				
				<div class="final">
					<span class="title">Total <span id="calculated_total">$${sessionScope.price}</span></span>
				</div>
			</div>	
			
			<div class="right" id="reviewed">
				<div id="complete">
				<button type="submit" class="big_button" id="complete">Order</button>
				</div>
			</div>
	   </div>
	   
	   </div>
</div>
		
		
		
</form>
<jsp:include page="insertFooter.jsp" />

</body>
</html>