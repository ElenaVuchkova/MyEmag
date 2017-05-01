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
<link href="/MyEmag/css/order.css" rel="stylesheet" type="text/css" media="all" />	
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
</head>
<body>
<jsp:include page="insertHeader.jsp" />
	<c:if test="${words1!=null}">
		<c:out value="${words1}"/>
	</c:if>
	<c:if test="${words2!=null}">
		<c:out value="${words2}"/>
	</c:if>
	
		<form action="addSubcategory" method="post" enctype="multipart/form-data">
		
		 
  <div id="wrap">
	<div id="accordian">
		<div class="step" id="step1">
			<div class="number">
				<span>1</span>
			</div>
			<div class="title">
				<h1>Category</h1>
			</div>			
		 </div>
		 <div class="content" id="email">		
			 <div>
	       		 <select name = "category" id="categorycombo" onchange="fillSubcategories()">
	       		 	<option selected="selected" class="holder">Please select</option>
					<c:forEach var="cat" items="${catAndSubcat}">
		                <option  value="${cat.key}"><c:out value="${cat.key}"></c:out></option>
		       		 </c:forEach>
		        </select>
	         </div>		
		 </div>
		<div class="step" id="step1">
			<div class="number">
				<span>2</span>
			</div>
			<div class="title">
				<h1>Add new subcategory for existing category</h1>
			</div>			
		 </div>
		 <div class="content" id="email">		
			 <div>
	       		 <input type="text" name="subcategory" class="validate" required=""><br>		 	
	         </div>		
		 </div>
		 
		 <div class="right" id="reviewed">
			<div id="complete">
				<button type="submit" class="big_button" id="complete" value = "Save changes">Save</button>     
			</div>
		</div> 
				
			</div>
			</div>        	
		</form>
		
<form action="addCategory" method="post" enctype="multipart/form-data">
	<div id="wrap">
		<div id="accordian">
						
			<div class="step" id="step1">
				<div class="number">
					<span>1</span>
				</div>
				<div class="title">
					<h1>Add new category</h1>
				</div>			
			</div>
			<div class="content" id="email">		
			 	<div>
	       			<input id="newSubcategory" type="text" name="category" class="validate" required=""><br>	    	 	
	         	</div>		
			</div>
				
			<div class="step" id="step1">
				<div class="number">
					<span>2</span>
				</div>
				<div class="title">
					<h1>Add subcategories for new category</h1>
				</div>			
			</div>
			<div class="content" id="email">		
				 <div>
		       		<input id="newSubcategory" type="text" name="subcategory1" class="validate" required="">
		       		<input id="newSubcategory" type="text" name="subcategory2" class="validate" >
		       		<input id="newSubcategory" type="text" name="subcategory3" class="validate">
		         </div>		
			 </div> 	
			 
			<div class="right" id="reviewed">
				<div id="complete">
					<button type="submit" class="big_button" id="complete" value = "Save">Save</button>     
				</div>
			</div> 	 		 				
		</div>
	</div>
</form>
		

<jsp:include page="insertFooter.jsp" />
</body>
</html>