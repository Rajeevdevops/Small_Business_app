<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/generateInvoice.js" type="text/javascript"></script>
<script src="js/invoice.js" type="text/javascript"></script>
<style>

table {
	border-spacing: 4px;
	border-collapse: separate;
}

td {
	padding: 4px;
}
</style>

<f:form action="quotation.html" method="post" id="form" modelAttribute="invoice">
	<f:hidden path="quotId" id="quotId"/>
	<h2>Generate Invoice</h2>
	<table style="width:100%">
		<tr>
			<td colspan="3" style="width:100%">Consignee Name
				<f:select cssClass="selectpicker form-control" placeholder="Select Consignee"  id = "consignee"  path="client.name"/> 
				
			</td>
			</tr><tr>
			<td>
				Reference/New Customer
				<f:input cssClass="form-control" path="reference" id = "reference" required="true" placeholder = "Enter Reference Name"/>
			</td>
			<td>
				Ref PhNo
				<f:input cssClass="form-control" path="phone" id = "phone" required="true" placeholder= "Enter Ref Phone Number"/>
			</td>
			
			<td>Total Amount
				<f:input type = "number" step = "0.1" id = "totAmt"  path = "totalAmount" readonly="true" class = "form-control"  required="true"/> 
			</td></tr><tr>
			<td>Claim Percentage
				<f:input cssClass="form-control" id = "claim" path ="claimPercentage" required="true" placeholder= "Enter Claim Percentage"/>
			</td>
			<td>
				Claim Amount
				<f:input class = "form-control"  path="claimAmount" id="claimAmt" readonly="true"  required="true" placeholder= "Your Claim Amount" value = "0"/>
			</td>
			
			<td>Total Quantity <f:input class="form-control" readonly="true" id="totalQuantity"  required="true" path="totalQuantity"/></td>
		</tr>
		
	</table>
	
	<h4>Add Products To Quotation</h4>
	<div class="wrapper" >
	<input type = "hidden" id = "index" value = "0"/>
		<table>
			<tr>
				<td>Select Product</td><td>
					<select id = "product" class="selectpicker form-control"  onchange="getProductDetails()">
					</select>
				</td>
			</tr>
			<tr>
				<td>Available Quantity</td>
				<td><input type = "text" id="avQua" class = "form-control" readonly="readonly"/></td>
			</tr>
			<tr>
				<td>Quantity</td>
				<td><input type= "number" id="qua" class = "form-control" value = "0" /></td>
			</tr>
			
			<tr>
				<td>Available Unit Price</td>
				<td><input type= "text" id="avUnit" class = "form-control" readonly="readonly"/></td>
			</tr>
			<tr>
				<td>Unit Price</td>
				<td><input type = "number" id="unit" step= "0.1" class = "form-control" value="0"/></td>
			</tr>
			
			<tr>
				<td>Discount</td>
				<td><input type = "number" id="discount" class="form-control" value="0"/></td>
			</tr>
			<tr>
				<td>Select Tax/Tax %</td>
				<td><select id="tax" class = "form-control" >
					<option value = "VAT">VAT</option>
					<option value = "CST">CST</option>
				</select>&nbsp; <input type = "number" id="taxPerc"  value="14.5" step = "0.1" class="form-control" /></td>
			</tr>
			<tr>
				<td>Total Amount</td>
				<td>
					<input type = "number" id="totalAmt" class = "form-control" value="0" onkeyup="getDiscount()"/>
				</td>
			</tr>
			<tr>
				<td>
					<input type = "button" id="addProduct" onclick="addRow1()" class = "btn btn-primary" value="Add Product"/>
				</td>
			</tr>
			<tr>
				<td id="details"></td>
			</tr>
		</table>
	</div>
	
	<h4>Products List for Quotation</h4>
	<table class="table table-stripped">
		<thead>
			<tr>
				<th>Product</th>
				<th>Units</th>
				<th>Quantity</th>
				<th>Actual Price</th>
				<th>Unit Price</th>
				<th>Discount</th>
				<th>Tax</th>
				<th>Tax %</th>
				<th>Total Amount</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody id = "data">
			<c:forEach items="${invoice.quotationItemSet}" var="items" varStatus="i">
			   <tr id="<c:out value="row${i.index}"/>">
			   <td style="display : none;"><c:out value="${items.product.id}"/></td>
			   <td><c:out value="${items.product.name}"/></td>
			   <td><c:out value="${items.product.units}"/></td>
			   <td><input type="text" id="<c:out value="quantity${i.index}"/>" value="${items.quantity}" onkeyup = "calAmount('<c:out value="${i.index}"/>')"/></td>
			   <td><c:out value="${items.availableUnitPrice}"/></td>
			   <td><input type="text" id="<c:out value="unit${i.index}"/>" value="${items.unitRate}" onkeyup="calAmount('<c:out value="${i.index}"/>')"/></td>
			   <td><input type="text" id="<c:out value="discount${i.index}"/>" value="${items.discount }" onkeyup="calAmount('<c:out value="${i.index}"/>')"/></td>
			   <td><input type="text" id="<c:out value="tax${i.index}"/>" value="${items.tax}" onkeyup="calAmount('<c:out value="${i.index}"/>')"/></td>
			   <td><input type="text" id="<c:out value="taxp${i.index}"/>" value="${items.vat}" onkeyup="calAmount('<c:out value="${i.index}"/>')"/></td>
			   <td id="<c:out value="total${i.index}"/>"><c:out value="${items.totalAmount}"/></td>
			   <td><a href="#" onclick="deleteRow(<c:out value="${i.index}"/>,<c:out value="${items.quantity}"/>,<c:out value="${items.totalAmount}"/>)">delete</a></td>
			   </tr>
			</c:forEach>
		</tbody>	
	</table>

<br/>

<input type = "button" class="btn btn-danger" onclick="getAddedRows1()" value = "Generate Invoice"/>
</f:form>