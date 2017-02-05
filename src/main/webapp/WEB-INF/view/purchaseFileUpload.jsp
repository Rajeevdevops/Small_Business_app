<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<h2>Upload Purchase Order Files</h2>

<f:form method="post" action="purchaseOrderFiles.html"
	modelAttribute="purchase" enctype="multipart/form-data">
	<input type= "hidden" name = "name" value = "<c:out value = "${purchase.fileName}" />"/>
	<input type="file" name="file" required="required" class="form-control" style="width:250px;" />
	<br/>
	<input type="submit" value="Upload Files" style="border: 0px;" class = "btn btn-danger" />
	<c:if test="${message != null }">
		<div  class="alert alert-success">
			<c:choose>
				<c:when test="${message eq 'Success' }">
					Files Uploaded Successfully..!
				</c:when>
				<c:otherwise>
					Files not uploaded.. Please try again..!
				</c:otherwise>
			</c:choose>
		</div>
	</c:if>
	
	<br/>
	
	
	<h4>Files Uploaded</h4>
	<ul>
		<c:forEach items="${files }" var="s">
		<li>
		
		<a href= "downloadPurchaseOrder.html?path=<c:out value = "${purchase.fileName}"/>&name=<c:out value = "${s}"/>"><c:out value = "${s}"/></a>
		</li>
				
		</c:forEach>
	</ul>
</f:form>