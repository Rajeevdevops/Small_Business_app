$(document).ready(function() {
	getOptions();
	getOptionsForConsignee();
	$("#addProduct").click(function(){
		
	displayContent("save");
		
	
		
	})
	
	$("#modifyMain").click(function() {
		alert("Clicked");
		var consignee = $("#consigneeName").val();
		var date = $("#date").val();
		var remarks1 = $("#remarks").val();
		var remarks2 = $("#remarks2").val();
		$.get("modifyPurchaseDetails.html", {id :  $("#mainId").val(), consignee : consignee, date : date, remarks1 : remarks1 , remarks2 : remarks2}, function (data) {
			
				if(data == "Success") {
					alert("Modified Successfully..!");
					$("#consignee").val("");
					$("#date").val("");
					$("#remarks").val("");
					$("#remarks2").val("");
					getData();
				} else {
					alert("Not Modified.. Please Try again..!");
					
				}
		});;
		
	});
	

	
	

	$("#deleteProduct").click(function() {
		var selectValue = parseInt($("#selectValue").val());
		var selectId = parseInt($("#selectId").val());
		if(selectValue > 0) {
			
			$.get("deletePurchaseItem.html", {
				purchaseId : $("#mainId").val(),
				purchaseItemId : selectValue
			} , function(data){
				if(data == "Success") {
					
					alert("Deleted Successfully..!");
					$("#row"+selectId).remove();
					getData();
				} else {
					alert("Not Deleted.. Please try again..!!")
					
				}
			});
			
		} else {
			alert("Please select a row to delete");
		}
	}) ;
	
	$("#button").click(function() {
		var operation = $("#operation").val();
		
		if(operation === "save") {
			
			$.get("addPurchaseItemProduct.html", {
				id : $("#mainId").val(),
				productId : $("#purchaseProduct").val(),
				quantity : $("#purchaseQuantity").val(),
				free : $("#purchaseFree").val(),
				price : $("#purchasePrice").val(),
				discount : $("#purchaseDiscount").val(),
				tax : $("#purchaseTax").val(),
				vat : $("#purchaseVat").val(),
				amt : $("#purchaseAmt").val()
				
			}, function(data){
				
				if(data == "Success") {
					alert("Product Added Successfully..!");
					
					$("#newContent").css('display', 'none');
					getPurchaseListData($("#mainId").val());
					$("#purchaseQuantity").val("0");
					$("#purchaseFree").val("0");
					$("#purchaseDiscount").val("0");
					$("#purchaseVat").val("0");
					$("#purchaseAmt").val("0");
					getData();

				} else {
					alert("Product not added .. Try again..!");
				}
			});
			
		} else {
			
		}
	});
	
});



function getOptionsForConsignee() {
	
	$.get("getNamesList.html", {}, function(data){
			var htmlData = "";
			$.each(data, function(key, value) {
				htmlData += "<option>"+value+"</option>";
			}); 
			$("#consigneeName").append(htmlData);

	});



}



function displayContent(operation) {
	var selectValue = parseInt($("#selectValue").val()); 
	$("#newContent").css('display','block');
	$("#button").val(operation);
	$("#operation").val(operation);
}

function getAmount() {
		var quantity = parseInt($("#purchaseQuantity").val());
		var unitPrice = parseFloat($("#purchasePrice").val());
		var discount = parseInt($("#purchaseDiscount").val());
		var vat = parseFloat($("#purchaseVat").val());
		
		var total = parseFloat(quantity * unitPrice);
		var disc = parseFloat(total * parseFloat((discount/100)) );
		var mainTotal = parseFloat(total - disc);
		var vatAmount = parseFloat(mainTotal * parseFloat(vat/100));
		$("#purchaseAmt").val(parseFloat(mainTotal+vatAmount));
		
	

}





function getOptions() {
	$.get("getProductNamesAndId.html",{}, function(data){
		htmlData = "";
		$.each(data, function(key, value){
			
			var option = value[0], name = value[1], units = value[2];
			
			htmlData += "<option value = \""+option+"\">"+name +"- units:"+units+"</option>";
			
		});
		
		$("#purchaseProduct").empty().append(htmlData);
	
	});
	
}




	function openLightBox(id, name, rowId) {
		$("#mainId").val(id);
		$("#formId").val(id);
		getOptions();
		
		$("#newContent").css('display','none');
		var popUp =  $('#whiteContent').bPopup({
			    easing: 'easeOutBack', //uses jQuery easing plugin
		            speed: 450,
		            transition: 'slideDown'
		        });
		$("#purchaseId").val(id);
		$.get("getPurchaseItemsCount.html", { purchaseId : id}, function(data) {
	        var count = parseInt(data);
	        var rowsCount = parseInt($("#poffset").val());

	        if (count > 0) {
	            var pages = Math.ceil(parseFloat(count / rowsCount));
	            $("#ppages").val(pages);
	            printPurchasePageNumbers();
	        } else {
	            $("#ppageNumbers").empty().html("No rows found");
	        }
	    });
		getPurchaseListData(id);
		getData();
	}
	

	function getNewDetails(pageNo) {
	    $("#pindex").val(parseInt(parseInt(pageNo - 1) * parseInt($("#poffset").val())));
	    $("#ppageNumber").val(pageNo);
	    getPurchaseListData($("#purchaseId").val());
	    printPurchasePageNumbers();

	}
	
	
	function printPurchasePageNumbers() {
	    var pages = parseInt($("#ppages").val());
	    var pageNumber = parseInt($("#ppageNumber").val());
	    var button = "&nbsp;<input type = 'button' class = 'btn btn-primary'   ";
	    var htmlData = "";
	    if (pageNumber <= 3) {

	        for (var i = 1; i <= 5; i++) {
	            if (i < pages) {
	                var style = "style = 'width:20px; padding : 5px; border : 0px;'";
	                if (i === pageNumber) {
	                	htmlData += "&nbsp;<input type = 'button' class = 'btn btn-danger' style = 'border:0px; width:30px; padding:5px;'  "+ "value = '" + i + "' " + style + " onclick = \"getNewDetails('" + i + "')\"/>&nbsp;";
	                } else {
	                htmlData += button + "value = '" + i + "'  " + style + " onclick = \"getNewDetails('" + i + "')\"/>";
	                }
	            } else {
	                break;
	            }
	        }

	    } else {
	        htmlData += button + "value = 'First' onclick = \"getNewDetails('" + 1 + "')\" style = 'border:0px;' />";
	        for (var i = pageNumber - 2; i <= pageNumber + 2; i++) {
	            if (i < pages) {


	                var style = "style = 'width:20px; padding:5px; border:0px; '";
	                if (i > 10) {
	                    style = " style = 'width:30px; padding : 5px;'";
	                }
	                if (i === pageNumber) {

	                    htmlData += "&nbsp;<input type = 'button' class = 'btn btn-danger' style = 'border:0px; width:30px; padding:5px;'  "+ "value = '" + i + "' " + style + " onclick = \"getNewDetails('" + i + "')\"/>&nbsp;";
	                } else {
	                htmlData += button + "value = '" + i + "' " + style + " onclick = \"getNewDetails('" + i + "')\"/>"; 
	                }

	            }

	            else {
	                break;
	            }
	        }
	    }

	    htmlData += "&nbsp;<input type = 'button' class = 'btn btn-primary' style = 'border:0px;'value = 'Last'  onclick = \"getNewDetails('" + pages + "')\"/>";
	    $("#ppageButtons").html(htmlData);
	}



	
	
	function getPurchaseListData(id) {
		$.get("getPurchaseItems.html",{purchaseId : id, offset : $("#poffset").val(), index :  $("#pindex").val() }, function(data) {
			var htmlData = "";
			var keys = "", vals = "";
			var val = parseInt($("#pindex").val());
			
			var i = 0;
			$.each(data, function (k1, v1){
				var id, name, units, quantity, free, totalQuantity, unitRate, discount, vat, amt, tax;
				keys += k1;
				vals += v1;
				id = v1[0];
				$.each(v1[1], function(key, value){
					if(key == "name") {
						name = value;
					} else if(key == "units") {
						units = value;
					}
				});
				
				quantity = v1[2];
				free = v1[3];
				unitRate = v1[4];
				discount = v1[5];
				vat = v1[6];
				amt = v1[7];
				tax = v1[8];
				
				
				htmlData += "<tr id = \"row"+(++i)+"\">" +
						"<td><input type= 'radio' name = 'select' id = \""+(i)+"\" onclick='setSelectValue(\""+id+"\", \""+i+"\")' /></td>" +
						"<td>"+(parseInt(val+i))+"</td>"+
						"<td>"+name+"</td>" +
						"<td>"+units+"</td>" +
						"<td>"+quantity+"</td>" +
						"<td>"+free+"</td>" +
						"<td>"+unitRate+"</td>" +
						"<td>"+discount+"</td>" +
						"<td>"+tax+"</td>" +
						"<td>"+vat+"</td>" +
						"<td>"+amt+"</td>" +
						"</tr>";
			});
			$("#loadingImage").css('display', 'none');
			$("#purchaseListData").empty().html(htmlData);
			
		}); 
	}
	
	
	
	function setSelectValue(value, rowId) {
		
		$("#selectValue").val(value);
		$("#selectId").val(rowId);
	}