$(document).ready(function(){
    getOptions(); 
    loadData();
	$(".selectpicker").select2();
	$("#addRow").click(function() {
		addRow();
		$(".selectpicker").select2();
	});
	$("#generatePurchase").click(function() {
		getValues();
	});
	
});

function getOptions() {
	$.get("getProductNamesAndId.html",{}, function(data){
		htmlData = "";
		$.each(data, function(key, value){
			
			var option = value[0], name = value[1], units = value[2];
			
			htmlData += "<option value = \""+option+"\">"+name +"- units:"+units+"</option>";
			
		});
		
		$("#optionDetails").val(htmlData);
	
	});
	
}


function addRow() {
	var rowCount = getRowCount();
	var row = "";
	var options = $("#optionDetails").val();
	
	row += "<tr>" +
			"<td><select class = \"selectpicker form-control\" style ='width:250px;' id = \"product"+rowCount+"\"> "+options+"</select></td>" +
			"<td><input type = 'number' class='form-control' required='true' id = \"quantity"+rowCount+"\" onkeyup = \"calcAmount("+rowCount+")\" value = '0'/></td>" +
			"<td><input type = 'number' class='form-control' required='true'  id = \"free"+rowCount+"\" onkeyup = \"calcAmount("+rowCount+")\" value = '0'/></td>" +
			"<td><input type = 'number' class='form-control' required='true' step = \"0.1\"  id = \"unit"+rowCount+"\"  value = '0' onkeyup = \"calcAmount("+rowCount+")\" /></td>"+
			"<td><input type = 'number' class='form-control' required='true'   id = \"discount"+rowCount+"\"  value = '0' onkeyup = \"calcAmount("+rowCount+")\"/></td>" +
			"<td><select class='form-control' style = \"width:90px;\" id=\"tax"+rowCount+"\"><option value = 'VAT'>VAT</option><option value = 'CST'>CST</option></select></td>" +
			"<td><input type = 'number' class='form-control' required='true'  step = \"0.1\"  id = \"vat"+rowCount+"\"  value = '0' onkeyup = \"calcAmount("+rowCount+")\"/></td>" +
			"<td><input type = 'number' class='form-control' required='true'  id = \"total"+rowCount+"\" readonly ='true'  value = '0'/></td>" +
			"</tr>";
	
	$("#data").append(row);
		
}

function getRowCount() {
	return $("#data tr").length;
}


function calcAmount(rowCount) {
	var quantity = parseInt($("#quantity"+rowCount).val());
	var unitPrice = parseFloat($("#unit"+rowCount).val());
	var discount = parseInt($("#discount"+rowCount).val());
	var vat = parseFloat($("#vat"+rowCount).val());
	
	var total = parseFloat(quantity * unitPrice);
	var disc = parseFloat(total * parseFloat((discount/100)) );
	var mainTotal = parseFloat(total - disc);
	var vatAmount = parseFloat(mainTotal * parseFloat(vat/100));
	$("#total"+rowCount).val(parseFloat(mainTotal+vatAmount));
	getTotalAmount();
}



function getTotalAmount() {
	var amount = 0;
	for (var a  = 0; a < parseInt(getRowCount()) ; a++) {
		amount += parseFloat($("#total"+a).val());
	}
	$("#totalAmount").val(Math.round(amount));
}


function loadData() {
	$.get("getNamesList.html", {}, function(data){
			var htmlData = "";
			$.each(data, function(key, value) {
				htmlData += "<option>"+value+"</option>";
			}); 
			$('.selectpicker').append(htmlData);

	});
}


function getValues() {
	var consignee = $("#consignee").val();
	var dateFormat = $("#dateFormat").val();
	var totalAmount = $("#totalAmount").val();
	var remarks1 = $("#remarks1").val();
	var remarks2 = $("#remarks2").val();
	
	var tableData= "";
	if(parseInt(getRowCount()) > 0) {
	for(var i = 0 ; i < parseInt(getRowCount()); i++) {
		var product = $("#product"+i).val();
		var quantity = $("#quantity"+i).val();
		var free = $("#free"+i).val();
		var unit = $("#unit"+i).val();
		var discount = $("#discount"+i).val();
		var tax = $("#tax"+i).val();
		var vat = $("#vat"+i).val();
		var total = $("#total"+i).val();
		if(parseInt(quantity) > 0) {
		tableData += product +"@&"+quantity+"@&"+free+"@&"+unit+"@&"+discount+"@&"+tax+"@&"+vat+"@&"+total+"#"; }
	}

	
	
	var mainData = consignee + "@&"+ dateFormat +"@&"+ totalAmount +"@&"+ remarks1 +"@&"+ remarks2;
	
	$.get("registerPurchase.html", {mainDetails : mainData, tableContent : tableData}, function(data) {
		if(data === "Success") {
			alert("Purchase Order Registered Successfully..!")
			$("#formPurchase").trigger('reset');
			$("#data").empty();
		} else {
			alert("Purchase Order Not Registered.. Please fill in all the fields and  Try again..!")
		}
	});
	} else {
		alert("No products are added to generate purchase order");
	}
}
