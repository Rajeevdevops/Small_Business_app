<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<style>

table.register {
	border-spacing: 4px;
	border-collapse: separate;
}

table.register td {
	padding: 4px;
}
.white_content {
	display: none;
	position: absolute;
	top: 25%;
	left: 25%;
	width: 45%;
	height: 65%;
	background-color: white;
	z-index: 1002;
	overflow: auto;
	-moz-border-radius: 10px;
	border-radius: 10px;
	-webkit-border-radius: 10px;
	padding:16px;
}
</style>
<script src="js/products.js" type="text/javascript"></script>
<h2>Products</h2>

<div id="light" class="white_content">
	<div>
		<span style="font-size: 15px;">Modify Product Details</span>
		
	</div>
	

	<hr />
	<center>
	<input type="hidden" id="id" />
	<input type = "hidden" id = "rowId"/>
	<table class = "register">
		<tr>
			<td>Name <input id="name" class="form-control"
				placeholder="Product Name" />
			</td>

			<td>Units <input id="units" class="form-control"
				placeholder="units" /></td>
				
				</tr><tr>
			<td>Description 1 <input id="desc1" class="form-control"
				placeholder="Description 1" /></td>
			<td>Description 2<input id="desc2" class="form-control"
				placeholder="Description 2" /></td> </tr><tr>
			<td>Description 3<input id="desc3" Class="form-control"
				placeholder="Description 3" /></td>
			<td>Description 4<input id="desc4" class="form-control"
				placeholder="Description 4" /></td>
		</tr>

		<tr>
			<td>Discount <input id="discount" class="form-control"
				placeholder="Discount" /></td>
			<td>Quantity <input id = "quantity" class = "form-control"/></td>
			<td>Quantity Level For Alert<input id="quantityRate"
				class="form-control" placeholder="Quantity Rate" /></td>
		</tr>

	</table>
</center>
	<span id="error_message" style = "color:red"></span>
	<button type="button" class="btn btn-primary" style="float:right;border:0px;" id="modifyProduct">Modify Product</button>

</div>

<div class="table-responsive">



	<input type="hidden" id="offset" value="8" /> <input type="hidden"
		id="index" value="0" /> <input type="hidden" id="pageNumber"
		value="1" /> <input type="hidden" id="pages" />
	<table class="table table-stripped table-hover table-responsive">
		<thead>
			<tr>
				<th>#</th>
				<th>Name</th>
				<th>Units</th>
				<th>Desc 1</th>
				<th>Desc 2</th>
				<th>Desc 3</th>
				<th>Desc 4</th>
				<th>Discount</th>
				<th>Quantity</th>
				<th>Alert Level</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody id="data">

		</tbody>
	</table>
<img alt="" src="grid-loading.gif" style="float:right; visibility: hidden;" id="loading" />
	<div style="width: 100%">
		<div style="margin-right: 2px;" id="pageButtons"></div>

	</div>
	
</div>