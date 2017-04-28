<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>MyEmag</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<!-- Custom Theme files -->
<!--theme-style-->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />	
<!--//theme-style-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Shopin Responsive web template, Bootstrap Web Templates, Flat Web Templates, AndroId Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--theme-style-->
<link href="css/style4.css" rel="stylesheet" type="text/css" media="all" />	
<!--//theme-style-->
<script src="js/jquery.min.js"></script>
<!--- start-rate---->
<script src="js/jstarbox.js"></script>
	<link rel="stylesheet" href="css/jstarbox.css" type="text/css" media="screen" charset="utf-8" />
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

</head>
<body>
<!--header-->
<div class="header">
<div class="container">
		<div class="head">
			<div class=" logo">
				<a href="index"><img src="images/ebaglogo.jpg" alt=""></a>	
			</div>
		</div>
	</div>
	<div class="header-top">
		<div class="container">
		<div class="col-sm-5 col-md-offset-2  header-login">
					<ul >
						<c:if test="${sessionScope.logged}">
							<li><a href="logout">Logout</a></li>
						</c:if>
						<c:if test="${!sessionScope.logged}">
							<li><a href="login">Login</a></li>
							<li><a href="register">Register</a></li>
						</c:if>
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
    		<li class="dropdown mega-dropdown active">
			    <a class="color1" href="#" class="dropdown-toggle" data-toggle="dropdown">Categories<span class="caret"></span></a>				
				<div class="dropdown-menu ">
                    <div class="menu-top">
						<div class="col1">
							<div class="h_nav">								
								<c:forEach items="${applicationScope['catAndSubcat']}" var="catAndSubcat">								
									<h4>${catAndSubcat.key}</h4>
									<c:forEach items="${catAndSubcat.value}" var="subcategory">
									<ul>
										<li><a href="${subcategory}"><c:out value="${subcategory}"></c:out></a></li>
									</ul>
									</c:forEach>
								</c:forEach>											
							</div>							
						</div>						
					</div>                  
				</div>				
			</li>
			<li><a class="color3" href="/MyEmag/sale">Sale</a></li>
			<li><a class="color4" href="404.html">About</a></li>
            <li ><a class="color6" href="contact.html">Contact</a></li>      
            <!-- if admin -->      
            <c:if test="${sessionScope.logged && sessionScope.user.role == 0}">
             <li ><a class="color6" href="addProduct">Add product</a></li>
            </c:if>
             <!-- //if admin -->   
        </ul>
        <!--Search product-->
        <div class="menu-top">
			<div class="col1">
				<div class="h_nav">	
     				<form action="search" method="GET">
			          	 <input type="text"  class="glyphicon glyphicon-search" name="keyword" placeholder="Search for product..." value="" required> 		
					</form>
				</div>
			</div>
		</div>
		<!--/Search product-->
     </div><!-- /.navbar-collapse -->

</nav>
</div>
			
			<div class="col-sm-2 search-right">
				<ul class="heart">
				<li>
				<a href="/MyEmag/wishlist" >
				<span class="glyphicon glyphicon-heart" aria-hidden="true"></span>
				</a></li>
					
					<div class="cart box_1">
						<a href="/MyEmag/cart">
						<h3> <div class="total">
						<!-- 	<span class="simpleCart_total"></span></div> -->
							<img src="/MyEmag/images/cart.png" alt=""/></h3>
						</a>
					</div>
					</ul>
					<div class="clearfix"> </div>
					
						<!---pop-up-box---->					  
			<link href="css/popuo-box.css" rel="stylesheet" type="text/css" media="all"/>
			<script src="js/jquery.magnific-popup.js" type="text/javascript"></script>
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
<div class="banner">
<div class="container">
<section class="rw-wrapper">
				<h1 class="rw-sentence">
					<span><!-- eBag</span>
					<div class="rw-words rw-words-1">
						<span>TV</span>
						<span>ELEKTRONIKA</span>
						
					</div>
					<div class="rw-words rw-words-2">
						<span>KAKVOTO POJELAETE</span>
						<span>MOJE DA SI KUPITE</span>
						
					</div>-->
				</h1>
			</section>
			</div>
</div>
	<!--content-->
		<div class="content">
			<div class="container">				
				<!--products-->
			<div class="content-mid">
				<h3>Top rated</h3>
				<label class="line"></label>
			<c:forEach items="${topRatedProducts}" var="product">	
				<div class="mid-popular">
					<div class="col-md-3 item-grid simpleCart_shelfItem">
					<div class=" mid-pop">
					<div class="pro-img">
						
						<img src="${product.imagePaths[0]}" height=200 width=200 class="img-responsive" alt=""> 
						<div class="zoom-icon ">
						 <a class="picture" href="${product.imagePaths[0]}" rel="title" class="b-link-stripe b-animate-go  thickbox"><i class="glyphicon glyphicon-search icon "></i></a>
						<a href="product/${product.productId}"><i class="glyphicon glyphicon-menu-right icon"></i></a> 
						</div>
						</div>
						<div class="mid-1">
						<div class="women">
						<div class="women-top">
							<span><a href="single">${product.title}</a></span>
							</div>
							<div class="img item_add">
								<a href="#"><img src="images/ca.png" alt=""></a>
							</div>
							<div class="clearfix"></div>
							</div>
							<div class="mid-2">
								<span>$${product.price}</span>
								  <div class="block">
									<div class="starbox small ghosting"> </div>
								</div>								
								<div class="clearfix"></div>
							</div>
							
						</div>
					</div>
					</div>					
				</div>						
			</c:forEach>
			</div>				
			</div> 	
		</div>
	<!--//content-->
	<!--//footer-->
 <jsp:include page="insertFooter.jsp" />

</body>
</html>