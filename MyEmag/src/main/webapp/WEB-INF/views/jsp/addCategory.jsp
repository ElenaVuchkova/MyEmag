<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	 function addSubcategory(category, value) {
		  	$.ajax({
				  url: "/MyEmag/addCategory/" + category + "/" + value,
				  type: "POST", //send it through get method
				  contentType : 'application/json; charset=utf-8',
				  dataType : 'json',
				  success: function(response) {
					  var changes = response.changes;
					  for(i = 0; i < changes.length; i++){
						  var price = (changes[i].messege).toFixed(2);
	 				 	 document.getElementById(changes[i].place).innerHTML = price + " $";
					  }	  
				  },
				  error: function(xhr) {
					  document.getElementById("status").innerHTML="error";
				  }
			}); 
 
	}
 
</script>
</head>
<body>
<jsp:include page="insertHeader.jsp" />
		<form action="addSubcategory" method="post" enctype="multipart/form-data">
				Category:<br> 
				<select name = "category" id="categorycombo" onchange="fillSubcategories()">
					<option selected="selected" class="holder">Please select</option>
					<c:forEach var="cat" items="${catAndSubcat}">
		                <option  value="${cat.key}"><c:out value="${cat.key}"></c:out></option>
		       		 </c:forEach>
		        </select> <br>		              
		      	Add new subcetegory for excisting category:</br>
				<input id="newSubcategory" type="text" name="newSubcategory" class="validate" required>
         		   <label for="newSubcategory"></label><br>		 	
				<input type="submit" value = "Save changes"></br>
		</form>
		
		<form name="frm1" method="post" onsubmit="return greeting()">
        <input type="text" name="fname">
        <input type="submit" value="Submit">
		</form>
		
		<form action="addCategory" method="post" enctype="multipart/form-data">
				Add New Category:<br>
				<input id="newSubcategory" type="text" name="newSubcategory" class="validate" required="">
         		<label for="newSubcategory" ></label><br>	           
		      	Add subcetegories for new category:</br>
		      		Subcategory 1:
					<input id="newSubcategory" type="text" name="newSubcategory" class="validate" required="">
         			<label for="newSubcategory" ></label><br>
         			Subcategory 2:	
         		  	<input id="newSubcategory" type="text" name="newSubcategory" class="validate" >
         		   	<label for="newSubcategory" ></label><br>
         		   	Subcategory 3:	
         		   	<input id="newSubcategory" type="text" name="newSubcategory" class="validate">
         		   	<label for="newSubcategory" ></label><br>		 		 		 	
				<input type="submit" value = "Save changes"></br>
		</form>
		

<jsp:include page="insertFooter.jsp" />
</body>
</html>