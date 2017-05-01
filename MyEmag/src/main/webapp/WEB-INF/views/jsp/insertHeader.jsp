<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
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

<style>	
	#searchInput {
	height: 2em;
	margin-left: 1em;
	border: 1px solid black;
	text-align: center;
	}
</style>
</head>
<body>
<!--header-->
<div class="header">
<div class="container">
		<div class="head">
			<div class=" logo">
				<a href="/MyEmag/index"><img src="/MyEmag/images/ebaglogo.jpg" alt=""></a>	
			</div>
		</div>
	</div>
	<div class="header-top">
		<div class="container">
		<div class="col-sm-5 col-md-offset-2  header-login">
					<ul >
						<c:if test="${sessionScope.logged}">
							<li><a href="/MyEmag/logout">Logout</a></li>
						</c:if>
						<c:if test="${!sessionScope.logged}">
							<li><a href="/MyEmag/login">Login</a></li>
							<li><a href="/MyEmag/register">Register</a></li>
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
										<li><a href="/MyEmag/${subcategory}"><c:out value="${subcategory}"></c:out></a></li>
									</ul>
									</c:forEach>
								</c:forEach>										
							</div>							
						</div>						
					</div>                  
				</div>				
			</li>
			<li><a class="color3" href="/MyEmag/sale">Sale</a></li>
            <li ><a class="color6" href="contact.html">Contact</a></li>      
            <!-- if admin -->      
            <c:if test="${sessionScope.logged && sessionScope.user.role == 0}">
             <li ><a class="color6" href="/MyEmag/addProduct">Add product</a></li>
             <li ><a class="color6" href="/MyEmag/addCategory">Add Category</a></li>
            </c:if>
        </ul>
         <!--Search product-->
        <div class="menu-top">
			<div class="col1">
				<div class="h_nav">	
     				<form action="search" method="GET">
			          	 <input type="text"  class="glyphicon glyphicon-search" name="keyword" placeholder="Search" value="" required id="searchInput"> 		
					</form>
				</div>
			</div>
		</div>
		<!--/Search product-->
     </div><!-- /.navbar-collapse -->

</nav>
</div>
			<!-- if user -->
			<c:if test="${sessionScope.user.role == 1}">
			<div class="col-sm-2 search-right">
				<ul class="heart">
				<li>
				<a href="/MyEmag/wishlist" >
				<span class="glyphicon glyphicon-heart" aria-hidden="true"></span>
				</a>
				</li>	
				<div class="cart box_1">
					<a href="/MyEmag/cart">
					<h3> <div class="total"></div>						
						<img src="/MyEmag/images/cart.png" alt=""/>
					</h3>
					</a>
					</div>
				</ul>
			</div>
			</c:if>
			
		</div>	
	</div>	
</div>

<div class="banner-top">
	<div class="container">
		<h1>eBAG</h1>
		<em></em>
		<h2><a href="/MyEmag/index">Home</a><label>
	</div>
</div>

</body>
</html>