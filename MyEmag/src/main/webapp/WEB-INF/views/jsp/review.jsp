<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PRODUCT</title>
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
<!--- start-rate---->
<script src="/MyEmag/js/jstarbox.js"></script>
	<link rel="stylesheet" href="/MyEmag/css/jstarbox.css" type="text/css" media="screen" charset="utf-8" />
		<script type="text/javascript">
			jQuery(function() {
			jQuery('.starbox').each(function() {
				var starbox = jQuery(this);
					starbox.starbox({
					average: starbox.attr('data-start-value'),
					changeable: starbox.hasClass('unchangeable') ? false : starbox.hasClass('clickonce') ? 'once' : true,
					ghosting: starbox.hasClass('ghosting'),
					autoUpdateAverage: starbox.hasClass('autoupdate'),
					buttons: starbox.hasClass('smooth') ? false : starbox.attr('data-button-count') || 5,
					stars: starbox.attr('data-star-count') || 5
					}).bind('starbox-value-changed', function(event, value) {
					if(starbox.hasClass('random')) {
					var val = Math.random();
					starbox.next().text(' '+val);
					return val;
					} 
				})
			});
		});
		</script>
<!---//End-rate---->
<link href="/MyEmag/css/form.css" rel="stylesheet" type="text/css" media="all" />
<title>Insert title here</title>
</head>

<body>
<!--header-->
<div class="header">
<div class="container">
		<div class="head">
			<div class=" logo">
				<a href="index.html"><img src="/MyEmag/images/logo.png" alt=""></a>	
			</div>
		</div>
	</div>
	<div class="header-top">
		<div class="container">
		<div class="col-sm-5 col-md-offset-2  header-login">
					<ul >
						<li><a href="login.html">Login</a></li>
						<li><a href="register.html">Register</a></li>
						<li><a href="checkout.html">Checkout</a></li>
					</ul>
				</div>
				
			<div class="col-sm-5 header-social">		
					<ul >
						<li><a href="#"><i></i></a></li>
						<li><a href="#"><i class="ic1"></i></a></li>
						<li><a href="#"><i class="ic2"></i></a></li>
						<li><a href="#"><i class="ic3"></i></a></li>
						<li><a href="#"><i class="ic4"></i></a></li>
					</ul>
					
			</div>
				<div class="clearfix"> </div>
		</div>
		</div>
		
		<div class="container">
		
			<div class="head-top">
			
		 <div class="col-sm-8 col-md-offset-2 h_menu4">
<nav class="navbar nav_bottom" role="navigation">
 
 <!-- Brand and toggle get grouped for better mobile display -->
  <div class="navbar-header nav_2">
      <button type="button" class="navbar-toggle collapsed navbar-toggle1" data-toggle="collapse" data-target="#bs-megadropdown-tabs">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
     
   </div> 
   <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-megadropdown-tabs">
        <ul class="nav navbar-nav nav_1">
            <li><a class="color" href="index.html">Home</a></li>
            
    		<li class="dropdown mega-dropdown active">
			    <a class="color1" href="#" class="dropdown-toggle" data-toggle="dropdown">Categories<span class="caret"></span></a>				
				<div class="dropdown-menu ">
                    <div class="menu-top">
						<div class="col1">
							<div class="h_nav">									
								<c:forEach items="${sessionScope.catAndSubcat}" var="catAndSubcat">								
									<h4>${catAndSubcat.key}</h4>
									<c:forEach items="${catAndSubcat.value}" var="subcategory">
									<ul>
										<li><a href="product.html"><c:out value="${subcategory}"></c:out></a></li>
									</ul>
									</c:forEach>
								</c:forEach>	
							</div>							
						</div>						
					</div>                  
				</div>				
			</li>
			<li><a class="color3" href="product.html">Sale</a></li>
			<li><a class="color4" href="404.html">About</a></li>
            <li ><a class="color6" href="contact.html">Contact</a></li>
        </ul>
     </div><!-- /.navbar-collapse -->

</nav>
			</div>
			<div class="col-sm-2 search-right">
				<ul class="heart">
				<li>
				<a href="wishlist.html" >
				<span class="glyphicon glyphicon-heart" aria-hidden="true"></span>
				</a></li>
				<li><a class="play-icon popup-with-zoom-anim" href="#small-dialog"><i class="glyphicon glyphicon-search"> </i></a></li>
					</ul>
					<div class="cart box_1">
						<a href="checkout.html">
						<h3> 
							<div class="total">
							<span class="simpleCart_total"></span></div>
							<img src="/MyEmag/images/cart.png" alt=""/>
						</h3>
						</a>
						<p><a href="javascript:;" class="simpleCart_empty">Empty Cart</a></p>

					</div>
					<div class="clearfix"> </div>
					
						<!----->

						<!---pop-up-box---->					  
			<link href="/MyEmag/css/popuo-box.css" rel="stylesheet" type="text/css" media="all"/>
			<script src="/MyEmag/js/jquery.magnific-popup.js" type="text/javascript"></script>
			<!---//pop-up-box---->
			<div id="small-dialog" class="mfp-hide">
				<div class="search-top">
					<div class="login-search">
						<input type="submit" value="">
						<input type="text" value="Search.." onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search..';}">		
					</div>
					<p>Shopin</p>
				</div>				
			</div>
		 <script>
			$(document).ready(function() {
			$('.popup-with-zoom-anim').magnificPopup({
			type: 'inline',
			fixedContentPos: false,
			fixedBgPos: true,
			overflowY: 'auto',
			closeBtnInside: true,
			preloader: false,
			midClick: true,
			removalDelay: 300,
			mainClass: 'my-mfp-zoom-in'
			});
																						
			});
		</script>		
						<!----->
			</div>
			<div class="clearfix"></div>
		</div>	
	</div>	
</div>
<!--banner-->
<div class="banner-top">
	<div class="container">
		<h1>Single</h1>
		<em></em>
		<h2><a href="index.html">Home</a><label>/</label>Single</h2>
	</div>
</div>
<h2>Review</h2>
<div class="container">
<div class="col-md-9">
	<div class="col-md-5 grid">		
		<div class="flexslider">
			 <div class="thumb-image"> <img src="${product.imagePaths[0]}" data-imagezoom="true" class="img-responsive"> </div>
		</div>	
	<div class="col-md-7 single-top-in">
		<div class="span_2_of_a1 simpleCart_shelfItem">
			<h3>${product.title}</h3>
			   <div class="price_single">
				  <span class="reducedfrom item_price">$${product.price}</span>
				  <c:if test="${product.salePrice != 0}">
   					 <span class="reducedfrom item_price">$${product.salePrice}</span>
				  </c:if>
				<div class="clearfix"> </div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>

	<form method="post" action="//submit.form" onSubmit="return validateForm();">
		<div style="width: 400px;">
		</div>
		<div style="padding-bottom: 18px;">Rate this product<br/>
			<select id="rating" name="rating" style="width : 150px;" class="form-control"><option>5</option>
			<option>4</option>
			<option>3</option>
			<option>2</option>
			<option>1</option>
			</select>
		</div>
		<div style="padding-bottom: 18px;">Add comment<span style="color: red;"> *</span><br/>
			<textarea id="comment" ${readonly} name="comment" style="width : 450px;" rows="10" class="form-control"></textarea>
		</div>
		<div style="padding-bottom: 18px;"><input name="skip_Submit" value="Submit" type="submit"/></div>
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





<!--  
<body>
			Title:<c:out value="${sessionScope.product.title}"></c:out><br>
			Price:<c:out value="${sessionScope.product.price}"></c:out><br>
			Path::<c:out value="${product.imagePaths[0]}"></c:out><br>
			<img src="${product.imagePaths[0]}"> <br>	
</body>
</html>
-->