<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="js/clients.js"></script>
<style>
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
	padding: 16px;
}
</style>
<h2>Consignee Credentials</h2>

<div id="light" class="white_content">
	<div>
		<span style="font-size: 15px;">Modify Client Details</span>
	</div>
	<hr/>
	
	
	<input type="hidden" id="id" />
	<input type = "hidden" id = "rowId"/>
	<table style="width:100%" cellpadding="5" cellspacing="5">
		<tr>
			<td>Name <input id="name" class="form-control"
				placeholder="Product Name" />
			</td>

			<td>Address <input id="address" class="form-control"
				placeholder="Address" /></td>
				
				</tr><tr>
			<td>Tin No<input id="tinNo" Class="form-control"
				placeholder="Tin No" /></td>
			<td>Cst No<input id="cstNo" class="form-control"
				placeholder="Cst No" /></td>
		</tr>

		<tr>
			<td>Phone Number 1<input id="ph1" class="form-control"
				placeholder="Phone Number 1" /></td>

			<td>Phone Number 2<input id="ph2"
				class="form-control" placeholder="Phone Number2" /></td>
		</tr>
		
				<tr>
			<td>Email<input id="email" class="form-control"
				placeholder="Email-Id" /></td>

			<td>Fax<input id="fax"
				class="form-control" placeholder="Fax" /></td>
				
			<td>Remarks<input id="remarks"
				class="form-control" placeholder="Remarks" /></td>
		</tr>
		<tr>
			<td>Description 1 <input id="desc1" class="form-control"
				placeholder="Description 1" /></td>
			<td>Description 2<input id="desc2" class="form-control"
				placeholder="Description 2" /></td> </tr>

	</table>
	
	<span id="error_message" style = "color:red"></span>
	<button type="button" class="btn btn-primary" style="float:right;border:0px;" id="modifyProduct">Modify Product</button>
	
	
</div>

<input type="hidden" id="offset" value="8" />
<input type="hidden" id="index" value="0" />
<input type="hidden" id="pageNumber" value="1" />
<input type="hidden" id="pages" />




<div class="table-responsive">
	<table class="table table-stripped table-hover">
		<thead>
			<tr>
				<th>#</th>
				<th>Name</th>
				<th>Address</th>
				<th>Tin No</th>
				<th>Cst No</th>
				<th>Ph No:1</th>
				<th>Ph No:2</th>
				<th>Email</th>
				<th>Fax</th>
				<th>Desc 1</th>
				<th>Desc 2</th>
				<th>Remarks</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody id="data">
		</tbody>
	</table>

	<img alt="" src="grid-loading.gif"
		style="float: right; visibility: hidden;" id="loading" />
	<div style="width: 100%">
		<div style="margin-right: 2px;" id="pageButtons"></div>
	</div>
	
</div>