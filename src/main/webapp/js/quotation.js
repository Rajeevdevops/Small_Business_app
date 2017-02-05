$(document).ready(function(){
	getOptions();
	loadData();
	$(".selectpicker").select2();
	$("#qua").keyup(function() {
		var value = parseInt($(this).val());
		var avValue = parseInt($("#avQua").val());
		if(value > avValue) {
			alert("Quantity cannot be greater than available quantity");
		
		} else {
			generate();
		}
	});
	

	$("#discount").keyup(function() {generate();});
	$("#taxPerc").keyup(function() {generate();});
	
	
	$("#unit").blur(function() { 
		var value = parseFloat($(this).val());
		var avUnit = parseFloat($("#avUnit").val());
		if(value < avUnit) {
			alert("You are selling your product for loss..");
		}
		generate();
	});
	
	$("#claim").keyup(function(){
		var claim = parseInt($(this).val());
		var amount = parseFloat(claim * parseFloat($("#totAmt").val()));
		$("#claimAmt").val(amount/100);
	});
	
});

function getOptions() {
	$.get("getProductNamesAndId.html",{}, function(data){
		htmlData = "<option value = 'no'>--Select--</option>";
		$.each(data, function(key, value){
			
			var option = value[0], name = value[1], units = value[2];
			
			htmlData += "<option value = \""+option+"@$"+name+"@$"+units+"\">"+name +"- units:"+units+"</option>";
			
		});
		
		$("#product").empty().append(htmlData);
	
	});
	
}



function generate() {
	var quantity = parseInt($("#qua").val());
	var unitPrice = parseFloat($("#unit").val());
	var discount = parseInt($("#discount").val());
	var vat= parseFloat($("#taxPerc").val());
 
	
	var total = parseFloat(quantity * unitPrice);
	var discAmount = parseFloat(parseFloat(discount/100) * total);
	var amt = parseFloat(total-discAmount);
	var vatAmount = parseFloat(amt * parseFloat(vat/100));
	var totAmt = parseFloat(amt + vatAmount);
	$("#totalAmt").val(totAmt.toFixed(2));
}

function getAmount() {
	var quantity = parseInt($("#qua").val());
	var unitPrice = parseFloat($("#avUnit").val());
	var discount = parseInt($("#discount").val());
	var vat= parseFloat($("#taxPerc").val());
 
	
	var total = parseFloat(quantity * unitPrice);
	var discAmount = parseFloat(parseFloat(discount/100) * total);
	var amt = parseFloat(total-discAmount);
	var vatAmount = parseFloat(amt * parseFloat(vat/100));
	var totAmt = parseFloat(amt + vatAmount);
	return totAmt;
}

function getDiscount() {
	$("#discount").val(0);
	
	var grand  = parseFloat($("#totalAmt").val());
	var vat = parseFloat($("#taxPerc").val());
	var total = parseFloat(parseInt($("#qua").val() * parseFloat($("#unit").val())));
	var denom = parseFloat(total * (1 + parseFloat(vat/100)));
	var divident = parseFloat(grand /  denom);
	var discPerc =  100 *  (1 - divident);
	if(discPerc > 0) { 
	$("#discount").val(Math.round(discPerc));
	} else {
		$("#discount").val(0);	
	}
	
}


function getProductDetails() {
	var productVal = $("#product").val().split("@$");
	var id = productVal[0];
	var name = productVal[1];
	var units = productVal[2];
	$.get("getProductCred.html", {productId : id} , function(data) {
		var values = data.split(":");
		$("#avUnit").val(values[0]);
		$("#avQua").val(values[1]);
		if($("#data tr").length > 0) {
			var qua = getColumnData(name);
			$("#avQua").val(parseInt(values[1]) - qua);
			
		}
		
	});
}

function addRow() {
	if($("#product").val() == "no") { alert("Please Select a product");} else {
	if(validateFields()) {
		var totalAmount = getAmount();
		var total = parseFloat($("#totalAmt").val());
		if(total < totalAmount) {
			if(window.confirm("You are selling your product for loss.. Do you wish to continue")) {
				getRowDetails();
			}  else {
				$("#product").focus();
			}
		} else {
			getRowDetails();
		}
	} }
}

function deleteRow(index, quantity, amount) {
	$("#row"+index).remove();
	var total = parseFloat($("#totAmt").val());
	$("#totAmt").val(total-amount);
	var qua = parseInt($("#totalQuantity").val());
	$("#totalQuantity").val(qua - quantity);
}


function getRowDetails() {
	var index = parseInt($("#index").val());
	var productVal = $("#product").val().split("@$");
	var id = productVal[0];
	var name = productVal[1];
	var units = productVal[2];
	$("#totAmt").val(parseFloat($("#totAmt").val()) + parseFloat($("#totalAmt").val()) );
	$("#totalQuantity").val(parseInt($("#totalQuantity").val()) + parseInt($("#qua").val()));
	var htmlData = "<tr id = \"row"+index+"\">" +
			"<td style = 'display : none'>"+id+"</td>" +
			"<td>"+name+"</td>" +
					"<td>"+units+"</td>" +
							"<td>"+$("#qua").val()+"</td>" +
									"<td>"+$("#unit").val() +"</td>" +
											"<td>"+$("#discount").val()+"</td>" +
													"<td>"+$("#tax").val()+"</td>" +
															"<td>"+$("#taxPerc").val()+"</td>" +
																	"<td>"+$("#totalAmt").val()+"</td>" +
																			"<td style = 'display : none'> "+$("#avUnit").val()+"</td>" +
																		"<td>" +
																		"<a href= '#' onclick = \"deleteRow("+index+", "+parseInt($("#qua").val())+", "+parseInt($("#totalAmt").val())+")\">Delete Row</a></td></tr>";
	$("#qua").val(0);
	$("#avQua").val(0);
	$("#unit").val(0);
	$("#avUnit").val(0);
	$("#discount").val(0);
	$("#totalAmt").val(0);
	index++;
	$("#index").val(index);
	$("#data").append(htmlData);
	$("#product").val("no");
	$("#product").focus();
	$("#select2-product-container").html("--Select--");
}



function getColumnData(product) {
	var qua = 0;
	 var rows = $('#data tr');
	    var columns;
	    for (var i = 0; i < rows.length; i++) {
	        columns = $(rows[i]).find('td');
	        var name = $(columns[0]).html();
	        var quantity = parseInt($(columns[2]).html());
	        if(product == name) {
	        	qua += quantity;
	        }
	    }
	    return qua;
}


function validateFields() {
	var quantity = parseInt($("#qua").val());
	if(quantity > 0) {
		if(parseInt($("#avQua").val()) < quantity) {
			alert("Quantity should be less than Available Quantity");
			return false;
		}
	} else {
		alert("Quantity should be greater than 0");
		return false;
	}
	
	
	
	return true;
}


function loadData() {
	$.get("getNamesList.html", {}, function(data){
			var htmlData = "<option value = \"no\">--Select--</option>";
			$.each(data, function(key, value) {
				htmlData += "<option>"+value+"</option>";
			}); 
			$('#consignee').append(htmlData);
			$("#select2-consignee-container").html("--Select--");
			$("#consignee").val("no");
	});
}
