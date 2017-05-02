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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

<script type="text/javascript">
function fillSubcategories(){
	var cat = document.getElementById("categorycombo").value;
	$.get("subcategories/get/" + cat, null,
		    function(data, status){
		        var select = document.getElementById('subcats');
		        var length = select.options.length;
		        select.innerHTML=null;		     
		        data.forEach(function(entry) {
		        	var opt = document.createElement('option');
		            opt.value = entry;
		            opt.innerHTML = entry;
		            select.appendChild(opt);
		        });
		    });
}
</script>

<body>

 <jsp:include page="insertHeader.jsp" />
 
  <form action="addProduct" method="post" enctype="multipart/form-data">
  <div id="wrap">
	<div id="accordian">
	
	   <!-- 1 -->
		<div class="step" id="step1">
			<div class="number">
				<span>1</span>
			</div>
			<div class="title">
				<h1>Title</h1>
			</div>			
		 </div>
		 <div class="content" id="email">		
			 <div>
	       		 <input id="title" type="text" name="title" class="validate" required>
	         </div>		
		 </div>
		<!-- //1 --> 
		
		 <!-- 2 -->
		<div class="step" id="step1">
			<div class="number">
				<span>2</span>
			</div>
			<div class="title">
				<h1>Quantity</h1>
			</div>			
		 </div>
		 <div class="content" id="email">		
			 <div>
	       		 <input id="quantity" type="number" min="1" name="quantity" class="validate" required>
	         </div>		
		 </div>
		<!-- //2 --> 
		
		<!-- 3 -->
		<div class="step" id="step1">
			<div class="number">
				<span>3</span>
			</div>
			<div class="title">
				<h1>Price</h1>
			</div>			
		 </div>
		 <div class="content" id="email">		
			 <div>
	       		 <input id="price" type="number" step="0.01" name="price" class="validate" required>
	         </div>		
		 </div>
		<!-- //3 --> 
		
		<!-- 4 -->
		<div class="step" id="step1">
			<div class="number">
				<span>4</span>
			</div>
			<div class="title">
				<h1>Specification</h1>
			</div>			
		 </div>
		 <div class="content" id="email">		
			 <div>
	       		 <input id="descrKey1" placeholder="Characteristic" type="text" name="descrKey1" class="validate" required>
	       		 <input id="descrValue1" placeholder="Description" type="text" name="descrValue1" class="validate" required>
	         </div>		
		 </div>
		<!-- //4 --> 
		
		<!-- 5 -->
		<div class="step" id="step1">
			<div class="number">
				<span>5</span>
			</div>
			<div class="title">
				<h1>Specification</h1>
			</div>			
		 </div>
		 <div class="content" id="email">		
			 <div>
	       		  <input id="descrKey2" placeholder="Characteristics" type="text" name="descrKey2" class="validate" required>
	       		  <input id="descrValue2" placeholder="Description" type="text" name="descrValue2" class="validate" required>
	         </div>		
		 </div>
		<!-- //5 --> 
		
		<!-- 6 -->
		<div class="step" id="step1">
			<div class="number">
				<span>6</span>
			</div>
			<div class="title">
				<h1>Specification</h1>
			</div>			
		 </div>
		 <div class="content" id="email">		
			 <div>
	       		  <input id="descrKey3" type="text" placeholder="Characteristics" name="descrKey3" class="validate" required>
	       		  <input id="descrValue3" type="text" placeholder="Description" name="descrValue3" class="validate" required>
	         </div>		
		 </div>
		<!-- //6 -->
		
		
		<!-- 7 -->
		<div class="step" id="step1">
			<div class="number">
				<span>7</span>
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
		<!-- //7 --> 
		
		<!-- 8 -->
		<div class="step" id="step1">
			<div class="number">
				<span>8</span>
			</div>
			<div class="title">
				<h1>Subcategory</h1>
			</div>			
		 </div>
		 <div class="content" id="email">		
			 <div>
	       		<select name="subcategory" id="subcats" >
		        </select> 
	         </div>		
		 </div>
		<!-- //8 -->
		
		<!-- 9 -->
		<div class="step" id="step1">
			<div class="number">
				<span>9</span>
			</div>
			<div class="title">
				<h1>Pictures</h1>
			</div>			
		 </div>
		 <div class="content" id="email">		
			 <div>
	       				
				<input type="file" name="picture" size="50" placeholder="Upload Image" ><br><br>
					
				<input type="file" name="picture1" size="50" placeholder="Upload Image" ><br><br>	
					
				<input type="file" name="picture2" size="50" placeholder="Upload Image" ><br><br>	 
	         </div>		
		 </div>
		<!-- //9 -->
		        
		<div class="right" id="reviewed">
			<div id="complete">
				<button type="submit" class="big_button" id="complete" value = "Add product">Add</button>
			</div>
		</div>
		
		</div>
		</div>
	</form>	
 <jsp:include page="insertFooter.jsp" />
</body>
</html>