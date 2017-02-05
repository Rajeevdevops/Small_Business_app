$(document).ready(function() {
	getData();
	 $.get("getClientCount.html", {}, function(data) {
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
    $("#ph1").val("--");
    $("#ph2").val("--");
    $("#email").val("--");
    $("#fax").val("--");
    $("#remarks").val("--");
    $("#tinNo").val("--");
    $("#cstNo").val("--");
    $("#address").val("--");
    $("#modifyProduct").click(function() {


		jname = $("#name").val();
		jph1 = $("#ph1").val();
		jph2 = $("#ph2").val();
		jdesc1 = $("#desc1").val();
		jdesc2 = $("#desc2").val();
		jaddress = $("#address").val();
		jcstNo = $("#cstNo").val();
		jtinNo = $("#tinNo").val();
		jemail = $("#email").val();
		jfax = $("#fax").val();
		jremarks = $("#remarks").val();
		
    	$.get("modifyClient.html", {
    		id : $("#id").val(), name : $("#name").val(), address : $("#address").val() ,
    		tinNo : $("#tinNo").val(),
    		cstNo : $("#cstNo").val(),
    		phoneNumber1 : $("#ph1").val(),
    		phoneNumber2 : $("#ph2").val(),
    		email : $("#email").val(),
    		fax : $("#fax").val(),
    		desc1 : $("#desc1").val(),
    		desc2  : $("#desc2").val(),
    		remarks : $("#remarks").val()
    		
    	}, function(data) {
    		
    		if(data === "Success") {
    			
    			var htmlData = "";
    			var index = parseInt($("#index").val());
    			htmlData += "<td>"+ (index+parseInt(rowId)) +"</td>";
    			htmlData += "<td>"+jname+"</td>";
    			htmlData += "<td>"+jaddress+"</td>";
    			htmlData += "<td>"+jtinNo+"</td>";
    			htmlData += "<td>"+jcstNo+"</td>";
    			htmlData += "<td>"+jph1+"</td>";
    			htmlData += "<td>"+jph2+"</td>";
    			htmlData += "<td>"+jemail+"</td>";
    			htmlData += "<td>"+jfax+"</td>";
    			htmlData += "<td>"+jdesc1+"</td>";
    			htmlData += "<td>"+jdesc2+"</td>";
    			htmlData += "<td>"+jremarks+"</td>";
    			htmlData += "<td><a href = '#' onclick = \"openLightBox('"+id+"','"+jname+"','"+rowId+"')\">Edit</a></td>";
    			htmlData += "<td><a href = '#' onclick = \"deleteRow("+id+", "+rowId+")\">Delete</a></td>";
    			htmlData += "</tr>";
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
	$.get("deleteClient.html", {id : id}, function(data) {
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

	"getClientList.html", {
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

			var id, name, discount, quantityRate, units, d1, d2, remarks, phoneNumber1, phoneNumber2, email, fax, tinNo, cstNo;

			$.each(value, function(k, v) {
				if (k == "id") {
					id = v;
				} else if (k == "name") {
					name = v;
				} else if (k == "address") {
					address = v;
				} else if (k == "tinNo") {
					tinNo = v;
				} else if (k == "cstNo") {
					cstNo = v;
				} else if (k == "phoneNumber1") {
					phoneNumber1 = v;
				} else if (k == "phoneNumber2") {
					phoneNumber2 = v;
				} else if (k == "email") {
					email = v;
				} else if (k == "fax") {
					fax = v;
				} else if(k == "desc1") {
					d1 = v;
				} else if( k == "desc2") {
					d2 = v;
				} else if(k == "remarks") {
					remarks = v;
				}
				
				
				
			});
			
			htmlData += "<td>"+ (indexValue+a) +"</td>";
			htmlData += "<td>"+name+"</td>";
			htmlData += "<td>"+address+"</td>";
			htmlData += "<td>"+tinNo+"</td>";
			htmlData += "<td>"+cstNo+"</td>";
			htmlData += "<td>"+phoneNumber1+"</td>";
			htmlData += "<td>"+phoneNumber2+"</td>";
			htmlData += "<td>"+email+"</td>";
			htmlData += "<td>"+fax+"</td>";
			htmlData += "<td>"+d1+"</td>";
			htmlData += "<td>"+d2+"</td>";
			htmlData += "<td>"+remarks+"</td>";
			htmlData += "<td><a href = '#' onclick = \"openLightBox('"+id+"','"+name+"','"+a+"')\">Edit</a></td>";
			htmlData += "<td><a href = '#' onclick = \"deleteRow("+id+", "+a+")\">Delete</a></td>";
			htmlData += "</tr>";
		});
		$("#data").empty().html(htmlData);
	}

	);
	
	

}
