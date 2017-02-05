<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <style>
table {
	border-spacing: 4px;
	border-collapse: separate;
}

td {
	padding: 4px;
}
</style>
    
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Consignee Credentials</h2>
<spring:form action="client.html" method="post" modelAttribute="client">
	<table style="width:100%">
		<tr>
			<td>Name<spring:input path="name" placeholder="Enter name" required="true" cssClass="form-control"/> </td>
			<td>Address <spring:input path="address" placeholder="Enter address" required= "true" cssClass="form-control"/></td>
			<td>Tin No <spring:input path="tinNo" placeholder="Enter Tin NO" required="true" cssClass="form-control"/></td> </tr><tr>
			<td>CST No <spring:input path="cstNo" placeholder="Enter CST NO" required="true" cssClass="form-control"/></td>
			<td>Phone Number 1<spring:input path="phoneNumber1" placeholder="Enter Phone Number" required="true" cssClass="form-control"/></td>
			<td>Phone Number 2<spring:input path ="phoneNumber2" placeholder="Enter Phone Number" required="true" cssClass="form-control"/></td></tr><tr>
			<td>Email Id<spring:input path="email" placeholder="Enter Email" required="true" cssClass="form-control"/></td>
			<td>Fax No<spring:input path="fax" placeholder="Enter Fax No" required="true" cssClass="form-control"/></td>
			<td>Remarks <spring:input path="remarks" placeholder="Enter Remarks" required="true" cssClass="form-control"/></td></tr><tr>
			<td>Description 1 <spring:input path="desc1" placeholder="Enter Description" required="true" cssClass="form-control"/></td>
			<td>Description 2 <spring:input path="desc2" placeholder="Enter Description" required="true" cssClass="form-control"/></td>
		</tr>
		
		
	</table>
	<input type="submit" value = "Register  Client Details" class="btn btn-success" style = "border:0px;"/>
	<br/>
	<br/>
	<br/>
	<c:if test="${message != null && type != null}">

		<c:choose>
			<c:when test="${type  eq 'Success' }">

				<div class="alert alert-success">
					<c:out value="${message}"></c:out>
				</div>
			</c:when>
			<c:otherwise>
				<div class="alert alert-danger">
					<c:out value="${message}"></c:out>
				</div>
			</c:otherwise>
		</c:choose>

		<c:if test="${type eq 'Success'}">

		</c:if>




	</c:if>
</spring:form>
