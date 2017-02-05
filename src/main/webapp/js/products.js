$(document).ready(function() {
	getData();
	 $.get("getCount.html", {}, function(data) {
	        var count = parseInt(data);
	        var rowsCount = parseInt($("#offset").val());

	        if (count > 0) {
	            var pages = Math.ceil(parseFloat(count / rowsCount));
	            $("#pages").val(pages);
	            printPageNumbers();
	        } else {
	            $("#pageNumbers").empty().html("No rows found");
	        }
	    });

});



function getDetails(pageNo) {
    $("#index").val(parseInt(parseInt(pageNo - 1) * parseInt($("#offset").val())));
    $("#pageNumber").val(pageNo);
    getData();
    printPageNumbers();

}


function closeLightBox() {
    document.getElementById('light').style.display = 'none';
    document.getElementById('fade').style.display = 'none';

}

function openLightBox(id, name, rowId) {
    
	var popUp =  $('#light').bPopup({
		    easing: 'easeOutBack', //uses jQuery easing plugin
	            speed: 450,
	            transition: 'slideDown'
	        });
	            
    $("#id").val(id);
    $("#name").val(name);
    $("#rowId").val(rowId);
    $("#desc1").val("--");
    $("#desc2").val("--");
    $("#desc3").val("--");
    $("#desc4").val("--");
    $("#quantity").val("0");
    $("#modifyProduct").click(function() {


		jname = $("#name").val();
		junits = $("#units").val();
		jdesc1 = $("#desc1").val();
		jdesc2 = $("#desc2").val();
		jdesc3 = $("#desc3").val();
		jdesc4 = $("#desc4").val();
		jquantityRate = $("#quantityRate").val();
		jdiscount = $("#discount").val();
		jquantity = $("#quantity").val();
		
    	$.get("modifyProduct.html", {
    		id : $("#id").val(), name : $("#name").val(), units : $("#units").val() ,
    		desc1 : $("#desc1").val(),
    		desc2 : $("#desc2").val(),
    		desc3 : $("#desc3").val(),
    		desc4 : $("#desc4").val(),
    		quanityRate  : $("#quantityRate").val(),
    		discount : $("#discount").val(),
    		quantity : $("#quantity").val()
    		
    	}, function(data) {
    		
    		if(data === "success") {
    			
    			var htmlData = "";
    			var index = parseInt($("#index").val());
    			htmlData += "<td>"+ (index+parseInt(rowId)) +"</td>";
    			htmlData += "<td>"+jname+"</td>";
    			htmlData += "<td>"+junits+"</td>";
    			htmlData += "<td>"+jdesc1+"</td>";
    			htmlData += "<td>"+jdesc2+"</td>";
    			htmlData += "<td>"+jdesc3+"</td>";
    			htmlData += "<td>"+jdesc4+"</td>";
    			htmlData += "<td>"+jdiscount+"</td>";
    			htmlData += "<td>"+jquantity+"</td>";
    			htmlData += "<td>"+jquantityRate+"</td>";
    			htmlData += "<td><a href = '#' onclick = \"openLightBox('"+id+"','"+jname+"','"+rowId+"')\">Edit</a></td>";
    			htmlData += "<td><a href = '#' onclick = \"deleteRow("+id+", "+rowId+")\">Delete</a></td>";
    			
    			$("#row"+rowId).empty().html(htmlData);
    			alert("Modified Successfully...!");
    			popUp.close();
    			clear();
    			
    		} else {
    			$("#error_message").html("Modification Failed.. May be of same Product Name");
    		}
    		
    	});
    });
}

function deleteRow(id, rowId) {
	$("#loading").css("visibility", "visible");
	$.get("deleteProduct.html", {id : id}, function(data) {
		if(data == "Success") {
			alert("Deleted Successfully..!");
			$("#row"+rowId).remove();
		} else {
			alert("Not Deleted.. Please Try again..!");
		}
	});
	$("#loading").css("visibility", "hidden");
	
	
}


function clear() {
	$("#id").val("");
	$("#name").val("");
	$("#units").val("");
	$("#quantityRate").val("");
	$("#discount").val("");
	$("#desc1").val("");
	$("#desc2").val("");
	$("#desc3").val("");
	$("#desc4").val("");
	$("#quantity").val("0");
}

function printPageNumbers() {
    var pages = parseInt($("#pages").val());
    var pageNumber = parseInt($("#pageNumber").val());
    var button = "&nbsp;<input type = 'button' class = 'btn btn-primary'   ";
    var htmlData = "";
    if (pageNumber <= 3) {

        for (var i = 1; i <= 5; i++) {
            if (i < pages) {
                var style = "style = 'width:20px; padding : 5px; border : 0px;'";
                if (i === pageNumber) {
                	htmlData += "&nbsp;<input type = 'button' class = 'btn btn-danger' style = 'border:0px; width:30px; padding:5px;'  "+ "value = '" + i + "' " + style + " onclick = \"getDetails('" + i + "')\"/>&nbsp;";
                } else {
                htmlData += button + "value = '" + i + "'  " + style + " onclick = \"getDetails('" + i + "')\"/>";
                }
            } else {
                break;
            }
        }

    } else {
        htmlData += button + "value = 'First' onclick = \"getDetails('" + 1 + "')\" style = 'border:0px;' />";
        for (var i = pageNumber - 2; i <= pageNumber + 2; i++) {
            if (i < pages) {


                var style = "style = 'width:20px; padding:5px; border:0px; '";
                if (i > 10) {
                    style = " style = 'width:30px; padding : 5px;'";
                }
                if (i === pageNumber) {

                    htmlData += "&nbsp;<input type = 'button' class = 'btn btn-danger' style = 'border:0px; width:30px; padding:5px;'  "+ "value = '" + i + "' " + style + " onclick = \"getDetails('" + i + "')\"/>&nbsp;";
                } else {
                htmlData += button + "value = '" + i + "' " + style + " onclick = \"getDetails('" + i + "')\"/>"; 
                }

            }

            else {
                break;
            }
        }
    }

    htmlData += "&nbsp;<input type = 'button' class = 'btn btn-primary' style = 'border:0px;'value = 'Last'  onclick = \"getDetails('" + pages + "')\"/>";
    $("#pageButtons").html(htmlData);
}





function getData() {

	$.get(

	"viewAllProducts.html", {
		offset : $("#offset").val(),
		index : $("#index").val()
	},

	function(data) {
		var htmlData = "";
		var i = 0;
		var indexValue = parseInt($("#index").val());
		$.each(data, function(key, value) {
			var a = (++i);
			htmlData += "<tr id = \"row" + a + "\" >";

			var name, units, discount, d1, d2, d3, d4, quantityRate, id, quantity;

			$.each(value, function(k, v) {
				if (k == "id") {
					id = v;
				} else if (k == "name") {
					name = v;
				} else if (k == "discount") {
					discount = v;
				} else if (k == "quantityRate") {
					quantityRate = v;
				} else if (k == "units") {
					units = v;
				} else if (k == "desc1") {
					d1 = v;
				} else if (k == "desc2") {
					d2 = v;
				} else if (k == "desc3") {
					d3 = v;
				} else if (k == "desc4") {
					d4 = v;
				} else if(k == "quantity") {
					quantity = v;
				}
				
				
			});
			
			htmlData += "<td>"+ (indexValue+a) +"</td>";
			htmlData += "<td>"+name+"</td>";
			htmlData += "<td>"+units+"</td>";
			htmlData += "<td>"+d1+"</td>";
			htmlData += "<td>"+d2+"</td>";
			htmlData += "<td>"+d3+"</td>";
			htmlData += "<td>"+d4+"</td>";
			htmlData += "<td>"+discount+"</td>";
			htmlData += "<td>"+quantity+"</td>";
			htmlData += "<td>"+quantityRate+"</td>";
			htmlData += "<td><a href = '#' onclick = \"openLightBox('"+id+"','"+name+"','"+a+"')\">Edit</a></td>";
			htmlData += "<td><a href = '#' onclick = \"deleteRow("+id+", "+a+")\">Delete</a></td>";
			htmlData += "</tr>";
		});
		$("#data").empty().html(htmlData);
	}

	);
	
	

}
