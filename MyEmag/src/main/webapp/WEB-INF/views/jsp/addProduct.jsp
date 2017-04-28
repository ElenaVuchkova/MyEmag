<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
				 <input id="title" type="text" name="title" class="validate" required>
         		   <label for="title">Title</label><br>
         		 <input id="quantity" type="text" name="quantity" class="validate" required>
         		   <label for="quantity">Quantity</label><br>
         		<input id="price" type="text" name="price" class="validate" required>
         		   <label for="price">Price</label><br>	
         		   
         		    <input id="descrKey1" type="text" name="descrKey1" class="validate" required>
         		   <label for="descrKey1">descrKey1</label><br>
         		   
         		     <input id="descrValue1" type="text" name="descrValue1" class="validate" required>
         		   <label for="descrValue1">"descrValue1"</label><br>
         		   
         		    <input id="descrKey2" type="text" name="descrKey2" class="validate" required>
         		   <label for="descrKey2">descrKey2</label><br>
         		   
         		     <input id="descrValue2" type="text" name="descrValue2" class="validate" required>
         		   <label for="descrValue2">"descrValue2"</label><br>
         		   
         		    <input id="descrKey3" type="text" name="descrKey3" class="validate" required>
         		   <label for="descrKey3">descrKey3</label><br>
         		   
         		     <input id="descrValue3" type="text" name="descrValue3" class="validate" required>
         		   <label for="descrValue3">"descrValue3"</label><br>
         		   
         		       		   
         		   
				Categories: 
				<select name = "category" id="categorycombo" onchange="fillSubcategories()">
					<c:forEach var="cat" items="${catAndSubcat}">
		                <option  value="${cat.key}"><c:out value="${cat.key}"></c:out></option>
		       		 </c:forEach>
		        </select> <br>		        
		       Subcategories: 
				<select name = "subcategory" id="subcats">
		        </select> <br>      
		        
				<label for="photo"> Select picture:  </label> <br>			
				<input type="file" name="picture" size="50" placeholder="Upload Image" ><br><br>
					
				<label for="photo"> Select picture:  </label> <br>			
				<input type="file" name="picture1" size="50" placeholder="Upload Image" ><br><br>	
				
				<label for="photo"> Select picture:  </label> <br>			
				<input type="file" name="picture2" size="50" placeholder="Upload Image" ><br><br>	
						 	
				<input type="submit" value = "Add product"></br>
		</form>
 <jsp:include page="insertFooter.jsp" />
</body>
</html>