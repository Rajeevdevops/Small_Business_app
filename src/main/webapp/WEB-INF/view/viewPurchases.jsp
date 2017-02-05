<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<script src="js/viewPurchase.js"></script>
<script src="js/modifyPurchase.js"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
#whiteContent {
	display: none;
	position: absolute;
	top: 25%;
	left: 25%;
	width: 76%;
	height: 85%;
	background-color: white;
	z-index: 1002;
	overflow: auto;
	-moz-border-radius: 10px;
	border-radius: 10px;
	-webkit-border-radius: 10px;
	padding: 16px;
}
</style>
<h2>View Purchase Orders</h2>


<input type="hidden" id="offset" value="8" />
<input type="hidden" id="index" value="0" />
<input type="hidden" id="pageNumber" value="1" />
<input type="hidden" id="pages" />
<input type="hidden" id="selectValue" value="0" />
<input type="hidden" id="selectId" />
<input type="hidden" id="mainId" />

<input type="hidden" id="optionDetails" />
<input type="hidden" id="operation" />
<div class="panel panel-default" id="whiteContent">
	<div class="panel-heading">Purchase Order Credentials</div>

	<div class="panel-body">

		<ul class="nav nav-pills">
			<li class="active"><a href="#productList" data-toggle="tab">Products
					List</a></li>
					<li><a href="#modifyPurchaseCred" data-toggle="tab">Modify Purchase Credentials</a></li>
		</ul>


		<div class="tab-content">


<div class="tab-pane fade" id="modifyPurchaseCred">
	<br/>
	<h4>Modify Purchase Order Credentials</h4>
	<table class="table table-responsive">
		
		<tr>
		<td></td>
			<td>Select Purchase: 
				<select id= "consigneeName" class = "selectpicker form-control">
				
				</select>
			</td>
			<td>
				Date:
				<input type = "date" id = "date" class = "form-control"/>
			</td>
			<td>
				Remarks1:
				<input type = "text" id = "remarks" class = "form-control"/>
			</td>
			<td>
				Remarks 2 : 
				<input type = "text" id = "remarks2" class = "form-control"/>
			</td>
		</tr>
		
	</table>
	<input type = "button" id = "modifyMain"  class = "btn btn-primary" style = "border : 0px;" value = "Modify"/>
</div>

			<div class="tab-pane fade in active" id="productList">
				<br />
				<h4>Products List</h4>


				<input type="hidden" id="poffset" value="4" /> <input type="hidden"
					id="pindex" value="0" /> <input type="hidden" id="ppageNumber"
					value="1" /> <input type="hidden" id="ppages" /> <input
					type="hidden" id="purchaseId" />

				<table class="table table-stripped table-responsive"
					id="purchaseListTable">
					<thead>
						<tr>
							<th></th>
							<th>#</th>
							<th>Product Name</th>
							<th>Units</th>
							<th>Quantity</th>
							<th>Free</th>
							<th>Unit Price</th>
							<th>Discount</th>
							<th>Tax</th>
							<th>Tax %</th>
							<th>Total Amount</th>
						</tr>
					</thead>
					<tbody id="purchaseListData">

					</tbody>
				</table>

				<img src="load.gif" height="150px" width="150px" id="loadingImage" />
			

			<div style="width: 100%">
				<div style="margin-right: 2px;" id="ppageButtons"></div>
			</div>

			<br />

			<div style="float: right;">
				<button type="button" id="addProduct" style="border: 0px;"
					class="btn  btn-outline btn-primary">Add Product</button>

				<button type="button" id="deleteProduct" style="border: 0px;"
					class="btn  btn-outline btn-primary">Delete Product</button>
			</div>
			<br /> <br />
			<div id="newContent" style="display: none;">
				<table class="table table-stripped">

					<tr>
						<td></td>
						<td>Product: <select id="purchaseProduct"
							class="selectpicker form-control"></select>
						</td>
						<td>Quantity: <input type="number" id="purchaseQuantity"
							class="form-control" onkeyup="getAmount()" value="0" />
						</td>

						<td>Free: <input type="number" id="purchaseFree"
							class="form-control" value="0" />
						</td>

						<td>Ut Price: <input type="number" id="purchasePrice"
							class="form-control" onkeyup="getAmount()" value="0" />
						</td>

						<td>Discount: <input type="number" id="purchaseDiscount"
							class="form-control" onkeyup="getAmount()" value="0" />
						</td>

						<td>Tax: <select id="purchaseTax" style="width: 80px;"
							class="form-control">

								<option value="CST">CST</option>
								<option value="VAT">VAT</option>
						</select>
						</td>

						<td>Tax %: <input type="number" step="0.1" id="purchaseVat"
							class="form-control" onkeyup="getAmount()" value="0" />
						</td>

						<td>Amt: <input type="number" step="0.1" id="purchaseAmt"
							readonly="readonly" class="form-control" value="0" />
						</td>

					</tr>

				</table>
				<input type="button" style="border: 0px;" class="btn btn-success"
					id="button" value="Add" /> <br />
				<div id="notification"></div>
			</div>
		</div>

		</div>

	</div>
</div>


<table class="table table-stripped" id="dataTables-example">
	<thead>
		<tr>
			<th>#</th>
			<th>Consignee Name</th>
			<th>Date</th>
			<th>Total Amount</th>
			<th>Remarks 1</th>
			<th>Remarks 2</th>
			<th>View Details</th>
			<th>Delete</th>
			<th>Files</th>
		</tr>
	</thead>
	<tbody id="data"></tbody>
</table>



<div style="width: 100%">
	<div style="margin-right: 2px;" id="pageButtons"></div>
</div>

<img src="load.gif" id="load" style="display: none" />
