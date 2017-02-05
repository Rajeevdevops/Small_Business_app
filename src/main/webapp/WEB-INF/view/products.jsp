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
<spring:form method="post" modelAttribute="product"
	action="product.html">

	<h2>Products Registration</h2>
	<table style="width: 100%;">
		<tr>
			<td>Name <spring:input path="name" cssClass="form-control"
					placeholder="Product Name" required="true" />
			</td>

			<td>Units <spring:input path="units" cssClass="form-control"
					placeholder="units"  required="true" /></td>
			<td>Description 1 <spring:input path="desc1"
					cssClass="form-control" placeholder="Description 1"  required="true" /></td>
		</tr>

		<tr>
			<td>Description 2<spring:input path="desc2"
					cssClass="form-control" placeholder="Description 2"  required="true" /></td>
			<td>Description 3<spring:input path="desc3"
					cssClass="form-control" placeholder="Description 3"  required="true" /></td>
			<td>Description 4<spring:input path="desc4"
					cssClass="form-control" placeholder="Description 4"  required="true" /></td>
		</tr>

		<tr>
			<td>Discount <spring:input path="discount"
					cssClass="form-control" placeholder="Discount" /></td>
					<td>Quantity <spring:input path = "quantity" cssClass="form-control" placeholder = "Quantity" required="true"/></td>

			<td>Quantity Level For Alert<spring:input path="quantityRate"
					cssClass="form-control" placeholder="Quantity Rate"  required="true" /></td>
		</tr>

	</table>

	<button type="submit" class="btn btn-primary" style = "border : 0px;">Register Product</button>
	<br />
	<br />

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