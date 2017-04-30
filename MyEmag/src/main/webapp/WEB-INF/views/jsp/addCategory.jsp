<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
				Category:<br> 
				<select name = "category">
					<option selected="selected" class="holder">Please select</option>
					<c:forEach var="cat" items="${catAndSubcat}">
		                <option  value="${cat.key}"><c:out value="${cat.key}"></c:out></option>
		       		 </c:forEach>
		        </select> <br>		              
		      	Add new subcetegory for excisting category:</br>
				<input type="text" name="subcategory" class="validate" required=""><br>		 	
				<input type="submit" value = "Save changes"></br>
		</form>
		
		<form action="addCategory" method="post" enctype="multipart/form-data">
				Add New Category:<br>
				<input id="newSubcategory" type="text" name="category" class="validate" required=""><br>	    
           
		      	Add subcetegories for new category:</br>
		      		Subcategory 1:
					<input id="newSubcategory" type="text" name="subcategory1" class="validate" required=""><br>
         			Subcategory 2:	
         		  	<input id="newSubcategory" type="text" name="subcategory2" class="validate" > <br>
      		   	Subcategory 3:	
         		   	<input id="newSubcategory" type="text" name="subcategory3" class="validate"><br>	 		 		 	
				<input type="submit" value = "Submit"></br>
		</form>
		

<jsp:include page="insertFooter.jsp" />
</body>
</html>