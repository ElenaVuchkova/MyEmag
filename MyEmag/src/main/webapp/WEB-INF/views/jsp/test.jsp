<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- images -->
<title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
   <link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700,600' rel='stylesheet' type='text/css'>

	
	<link href="/MyEmag/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="/MyEmag/css/test.css" rel="stylesheet" type="text/css" media="all" />



<title>MyEmag</title>

</head>
<body>
<jsp:include page="insertHeader.jsp" />
 
<div class="single">
	<div id="wrap">
	<div id="accordian">
		<div class="step" id="step1">
			<div class="number">
				<span>1</span>
			</div>
			<div class="title">
				<h1>Email Address</h1>
			</div>			
		</div>
		
		
		<div class="content" id="email">
		<form class="go-right">
		<div>
        <input type="email" name="email" value="" id="email-address" placeholder="Address" data-trigger="change" data-validation-minlength="1" data-type="email" data-required="true" data-error-message="Enter a valid email address."/><label for="email">Email Address</label>
        </div>
		</form>
		</div>
		
		<div class="step" id="step2">
			<div class="number">
				<span>2</span>
			</div>
			<div class="title">
				<h1>user Information</h1>
			</div>
		</div>
		
		
		<div class="content" id="address">
			<form class="go-right">
				<div>
				<input type="name" name="first_name" value="" id="first_name" placeholder="John" data-trigger="change" data-validation-minlength="1" data-type="name" data-required="true" data-error-message="Enter Your First Name"/><label for="first_name">First Name</label>
        		</div>				
			</form>
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
				       <select name ="wayToPay" >
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
				<span>5</span>
			</div>
			<div class="title">
				<h1>Finalize Order</h1>
			</div>
		</div>
		
		<div class="content" id="final_products">
			<div class="left" id="ordered">				
				<div class="final">
					<span class="title">Total <span id="calculated_total">$51.00</span></span>
				</div>
			</div>	
			
			<div class="right" id="reviewed">
				<div id="complete">
				<a class="big_button" id="complete" href="#">Complete Order</a>
				</div>
			</div>
	   </div>
	   
	   </div>
	</div>
</div>


 <jsp:include page="insertFooter.jsp" />

</body>
</html>