<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	function myFunction() {
	    alert("Subscribed!"),
		$.post("/MyEmag/subscribe");
	};
</script>
</head>
<body>
<!--//footer-->
	<div class="footer">
	<!-- if user -->
	
	  <div class="footer-middle">
				<div class="container">
					<c:if test="${sessionScope.logged && sessionScope.user.role == 1}">
						<div class="col-md-3 footer-middle-in">
							<h6>Here you can subscribe for sales</h6>
							<button onclick="myFunction()" style="color: #F67777; background: transparent; font-size: 20px;">Subscribe</button>
						</div>	
						</c:if>
					<c:if test="${!sessionScope.logged}">
						<div class="col-md-3 footer-middle-in">
							<h6>Newsletter! You can subscribe for sales!</h6>
							<a href="login" style="color: #F67777; background: transparent; font-size: 20px;">Login here</a>
						</div>		
					</c:if>					
					
					<div class="clearfix"> </div>
				</div>
		</div>
		
	<!-- //if user -->
	</div>
		<!--//footer-->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/MyEmag/js/simpleCart.min.js"> </script>
<!-- slide -->
<script src="/MyEmag/js/bootstrap.min.js"></script>
<!--light-box-files --> 	

</body>
</html>