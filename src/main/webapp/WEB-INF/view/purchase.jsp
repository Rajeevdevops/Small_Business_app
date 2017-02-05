<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="js/purchase.js" type="text/javascript"></script>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>

table {
	border-spacing: 4px;
	border-collapse: separate;
}

td {
	padding: 4px;
}
</style>


<f:form modelAttribute="purchase" action="quotation.html" method="post" id="formPurchase">
	<h2>Purchase Orders</h2>
	<input type = "hidden" id = "optionDetails"/>
	<table style="width:100%">
		<tr>
			<td colspan="4" style="width:100%">Consignee Name
				<f:select cssClass="selectpicker form-control" id="consignee" placeholder="Select Consignee"   path="client.name"/> 
				
			</td>
			</tr><tr>
			<td>Date
				<f:input cssClass="form-control" path="dateFormat" id="dateFormat" required="true" type="date" placeholder="select date"/>
			</td>
			<td>
				Total Amount
				<f:input cssClass="form-control" path="totalAmount"  id="totalAmount" required="true" placeholder = "Enter Total Amount" readonly="true"/>
			</td>
			<td>
				Remarks 1
				<f:input cssClass="form-control" path="remarks1" id = "remarks1" required="true" placeholder= "Enter Remarks "/>
			</td>
			
			<td>Remarks 2
				<f:input cssClass="form-control" path = "remarks2"  id = "remarks2" required="true" placeholder="Enter Remarks"/> 
			</td>
			</tr>
					
	</table>
	<br/>
	<br/>
	<br/>
	<h4>Products List for Quotation</h4>
	<table class="table table-stripped" id="tableData">
		<thead>
			<tr>
				<th>Product</th>
				<th>Quantity</th>
				<th>Free</th>
				<th>Unit Price</th>
				<th>Discount</th>
				<th>Tax</th>
				<th>Vat</th>
				<th>Total Amount</th>
			</tr>
		</thead>
		<tbody id = "data">
			
		</tbody>	
	</table>
	<button style="border:0px;" class="btn btn-success"  type="button" id = "addRow">Add Row</button>
 	<button style = "border:0px;" class = "btn btn-primary" type = "button" id ="generatePurchase">Submit Values</button>
</f:form>