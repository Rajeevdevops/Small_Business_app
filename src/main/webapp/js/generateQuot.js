function validateInputs() {
	if (parseInt($("#totAmt").val()) == 0) {
		alert("Quotation Cannot be raised having 0 total amount");
		return false;
	}
	if ($("#reference").val().trim().legth == 0) {
		alert("Reference/Customer Name cannot be empty");
		return false;
	}

	if ($("#phone").val().trim().length == 0) {
		alert("Reference Phone Number cannot be empty");
		return false;
	}

	if (parseInt($("#totalQuantity").val()) == 0) {
		alert("Quotation Cannot be raised having 0 Total Quantity");
		return false;
	}

	return true;

}

function getAddedRows() {

	if (validateInputs()) {
		var client = $("#consignee").val();
		var totalAmount = $("#totAmt").val();
		var totalQuantity = $("#totalQuantity").val();
		var reference = $("#reference").val();
		var phone = $("#phone").val();
		var claim = $("#claim").val();
		var claimAmt = $("#claimAmt").val();
		var htmlData = client + "@&" + totalAmount +"@&" + totalQuantity +"@&"+ reference +"@&"+ phone +"@&"+ claim +"@&"+ claimAmt ; 
		var productsData = "";
		$('#data tr').each(
				function(row) {

					var $this = $(this);
					var column = $this.children("td");
					var productId = column.eq("0").html();
					var quantity = column.eq("3").html();
					var unitRate = column.eq("4").html();
					var discount = column.eq("5").html();
					var tax = column.eq("6").html();
					var taxPerc = column.eq("7").html();
					var totalAmount = column.eq("8").html();
					var avUnit = column.eq("9").html();
					productsData += productId + "@&" + quantity + "@&"
							+ unitRate + "@&" + discount + "@&" + tax + "@&"
							+ taxPerc + "@&" + totalAmount + "@&" + avUnit
							+ "##";
				});
		
		$.get("generateQuotation.html", {htmlData : htmlData, productData : productsData}, function(data) {
			if(data == "Success") {
				alert("Quotation Generated Successfully...!");
				$("#form").trigger('reset');
				$("#data").empty();
				$("#consignee").val("no");
				$("#select2-consignee-container").html("--Select--");
			} else {
				alert("Not Generated.. Please try again..!");
				
			}
		});
		
	}

}
