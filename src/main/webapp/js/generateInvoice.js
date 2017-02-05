
function calAmount(id){

	var a= parseFloat($("#quantity"+id).val()) * parseFloat($("#unit"+id).val());
	var discount = a * (parseFloat($("#discount"+id).val()))/100; 
	 a = a-discount;
	 var vat = a * (parseFloat($("#taxp"+id).val()))/100;
	 a = a + vat;
	 $("#total"+id).html(a);
	 calTot();	
}

function getRowsLength() {
	return $("#data tr").length;
}

function calTot(){
   
   var i=0;
	var quan=0
	var tot=0;
	for(i=0;i<getRowsLength();i++){
		quan = quan + parseInt($("#quantity"+i).val());
		tot = tot + parseFloat($("#total"+i).html());
	}
	$("#totAmt").val(tot);
	$("#totalQuantity").val(quan);
}

//********************---------------------------Adding invoice to database-----------------------**********************************************//


function getAddedRows1(){

	if (validateInputs()) {
		var quotId = $("#quotId").val();
		var client = $("#consignee").val();
		var totalAmount = $("#totAmt").val();
		var totalQuantity = $("#totalQuantity").val();
		var reference = $("#reference").val();
		var phone = $("#phone").val();
		var claim = $("#claim").val();
		var claimAmt = $("#claimAmt").val();
		var htmlData = client + "@&" + totalAmount +"@&" + totalQuantity +"@&"+ reference +"@&"+ phone +"@&"+ claim +"@&"+ claimAmt+"@&"+ quotId ;
		console.log(htmlData);
		var productsData = "";
		var k=0;
		$('#data tr').each(
				function(row) {

					var $this = $(this);
					var column = $this.children("td");
					var productId = column.eq("0").html();
					
					var quantity = column.eq("3").html();
					
					if(quantity[0] == "<"){
						var quantity = $("#quantity"+k).val();
						var unitRate = $("#unit"+k).val();
						var discount = $("#discount"+k).val();
						var tax = $("#tax"+k).val();
						var taxPerc = $("#taxp"+k).val();
						var totalAmount = column.eq("9").html();
						var avUnit = column.eq("4").html();
						
					   //alert(productId+":"+quantity+":"+unitRate+":"+discount+":"+tax+":"+taxPerc+":"+totalAmount+":"+avUnit);
					}else{
					
					var unitRate = column.eq("5").html();
					var discount = column.eq("6").html();
					var tax = column.eq("7").html();
					var taxPerc = column.eq("8").html();
					var totalAmount = column.eq("9").html();
					var avUnit = column.eq("4").html();
					
					//alert(productId+":"+quantity+":"+unitRate+":"+discount+":"+tax+":"+taxPerc+":"+totalAmount+":"+avUnit);
					}
					productsData += productId + "@&" + quantity + "@&"
					+ unitRate + "@&" + discount + "@&" + tax + "@&"
					+ taxPerc + "@&" + totalAmount + "@&" + avUnit
					+ "##";
					
					k++;
				});
		alert(productsData);
		$.get("registerInvoice.html", {htmlData : htmlData, productData : productsData}, function(data) {
			if(data == "Success") {
				alert("Invoice Generated Successfully...!");
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
