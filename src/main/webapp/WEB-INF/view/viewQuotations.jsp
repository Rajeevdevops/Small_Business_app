<script src= "js/viewQuotations.js" type="text/javascript"></script>
<style>
.white_content {
	display: none;
	position: absolute;
	top: 25%;
	left: 25%;
	width: 55%;
	height: 55%;
	background-color: white;
	z-index: 1002;
	overflow: auto;
	-moz-border-radius: 10px;
	border-radius: 10px;
	-webkit-border-radius: 10px;
	padding: 16px;
}
</style>
<h2>View Quotations</h2>


<div class="white_content col-lg-4" id="light">
             
                           <table class="table table-responsive">
                           		<thead>
                           			<tr>
                           				<th>Id</th>
                           				<th>Name</th>
                           				<th>Units</th>
                           				<th>Quantity</th>
                           				<th>Unit Price</th>
                           				<th>Discount</th>
                           				<th>Tax</th>
                           				<th>Tax %</th>
                           				<th>Total Amount</th>
                           			</tr>
                           		</thead>
                           		<tbody id="productData">
                           		
                           		</tbody>
                           </table>
                           <img src = "load.gif" id = "load"/>
                        </div>

<input type="hidden" id="offset" value="8" />
<input type="hidden" id="index" value="0" />
<input type="hidden" id="pageNumber" value="1" />
<input type="hidden" id="pages" />

<table class="table table-responsive">

	<thead>
		<tr>
			<th>S.No</th>
			<th>Date</th>
			<th>Client Name</th>
			<th>Reference</th>
			<th>Phone</th>
			<th>Amount</th>
			<th>Quantity</th>
			<th>Product Details</th>
			<th>To Invoice</th>
			<th>Delete</th>
		</tr>
	</thead>
	<tbody id="data">

	</tbody>
</table>

<div style="width: 100%">
	<div style="margin-right: 2px;" id="pageButtons"></div>
</div>


