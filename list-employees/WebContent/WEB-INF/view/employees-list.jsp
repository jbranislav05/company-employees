<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>

<head>
	<title>Employees in company</title>
	
	<style>
		body{
			background-image: url("${pageContext.request.contextPath}/resources/images/company.jpeg");
			background-repeat: no-repeat;
			width:100%;
			height:auto;
		}
	</style>
	

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>

<body>
	
	<div id="container">
	
		<div id="content">
		
		
			<input type="button" value="Employee Add"
				   onclick="window.location.href='showFormForAdd'; return false;"
				   class="add-button"/>
				   
			<form:form action="search" method="GET">
		  		Search employee: <input type="text" name="theSearchName"/>
		  		
		  		<input type="submit" value="Search" class="add-button"/>
		    </form:form>
		
		
			<table border="1">
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Address</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var="tempEmployee" items="${employees}">
				
					<c:url var="updateLink" value="/employee/showFormForUpdate">
						<c:param name="employeeId" value="${tempEmployee.id}" />
					</c:url>	
					
					<c:url var="deleteLink" value="/employee/delete">
						<c:param name="employeeId" value="${tempEmployee.id}" />
					</c:url>					
					
					<tr>
						<td> ${tempEmployee.firstName} </td>
						<td> ${tempEmployee.lastName} </td>
						<td> ${tempEmployee.address} </td>
						<td> ${tempEmployee.email} </td>
						
						<td>
							<a href="${updateLink}">Update</a>
							/
							<a href="${deleteLink}">Delete</a>
						</td>
						
					</tr>
				
				</c:forEach>
						
			</table>
				
		</div>
	
	</div>
	
	
	<div style="clear; both;">
		
		<p>
			<a href="${pageContext.request.contextPath}/employee/list">Back to complete list</a>
		</p>
	
	</div>
	

</body>

</html>









