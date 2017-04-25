<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!--//footer-->
	<div class="footer">
	<div class="footer-middle">
				<div class="container">
										
					<div class="col-md-3 footer-middle-in">
						<h6>Newsletter</h6>
						<span>Sign up for News Letter</span>
							<form>
								<input type="text" value="Enter your E-mail" onfocus="this.value='';" onblur="if (this.value == '') {this.value ='Enter your E-mail';}">
								<input type="submit" value="Subscribe">	
							</form>
					</div>
					<div class="clearfix"> </div>
				</div>
			</div>
		</div>
		<!--//footer-->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/MyEmag/js/simpleCart.min.js"> </script>
<!-- slide -->
<script src="/MyEmag/js/bootstrap.min.js"></script>
<!--light-box-files -->
		<script src="/MyEmag/js/jquery.chocolat.js"></script>
		<link rel="stylesheet" href="/MyEmag/css/chocolat.css" type="text/css" media="screen" charset="utf-8">
		<!--light-box-files -->
		<script type="text/javascript" charset="utf-8">
		$(function() {
			$('a.picture').Chocolat();
		});
		</script>


</body>
</html>