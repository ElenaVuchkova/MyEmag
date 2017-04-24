<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="/MyEmag/css/test.css" rel="stylesheet" type="text/css" media="all" />
</head>
<body>
	<h1>Amazing Reviews</h1>
<h2>Online Retail Testimonial Style</h2>
<div class=amazing-reviews>
  <blockquote>
    <header>
      <span data-rating=5>
        <i class=ion-star></i>
        <i class=ion-star></i>
        <i class=ion-star></i>
        <i class=ion-star></i>
        <i class=ion-star></i>
      </span>
      <strong>Ullamco laboris!</strong>
      <span>, June 2</span>
      <span>By <em>Wolfman</em></span>
      <span>Verified Purchase</span>
    </header>
    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris.</p>
  </blockquote>
</div>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.3/jquery.min.js"></script>
	<script type="text/javascript">
   		$(function() {    		
			$('input[type=submit]').click(function() {
				$('p').html('<span class="stars">'+parseFloat($('input[name=amount]').val())+'</span>');
				$('span.stars').stars();
			});    		
			$('input[type=submit]').click();
		});

		$.fn.stars = function() {
			return $(this).each(function() {
				$(this).html($('<span />').width(Math.max(0, (Math.min(5, parseFloat($(this).html())))) * 16));
			});
		}
	</script>
	<style type="text/css">
		span.stars, span.stars span {
			display: block;
			background: url(http://www.ulmanen.fi/stuff/stars.png) 0 -16px repeat-x;
			width: 80px;
			height: 16px;
		}
	
		span.stars span {
			background-position: 0 0;
		}
	</style>
</head>
<body>

	<input type="text" name="amount" value="2.53" />
	<input type="submit" value="update">
<p>
 
</body>
</html>