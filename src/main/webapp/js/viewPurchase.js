$(document).ready(function(){

	$("#load").css('display', 'block');
	getData();
	$("#load").css('display', 'none');

	 $.get("getPurchaseCount.html", {}, function(data) {
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

	"getPurchaseList.html", {
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

			var id, name, totalAmount, date, remarks1, remarks2;

			id = value[0];
			name = value[1];
			totalAmount = value[2];
			date = value[3];
			remarks1 = value[4];
			remarks2 = value[5];
			
			htmlData += "<td>"+ (indexValue+a) +"</td>";
			htmlData += "<td>"+name+"</td>";
			htmlData += "<td>"+getDateDetails(date)+"</td>";
			htmlData += "<td>"+totalAmount+"</td>";
			htmlData += "<td>"+remarks1+"</td>";
			htmlData += "<td>"+remarks2+"</td>";
	
			htmlData += "<td><a href = '#' onclick = \"openLightBox('"+id+"','"+name+"','"+a+"')\">Edit</a></td>";
			htmlData += "<td><a href = '#' onclick = \"deletePurchaseRow("+id+", "+a+")\">Delete</a></td>" +
					"<td><a href= \"purchaseOrderFiles.html?id="+id+"\">Click Here</a></td>";
			htmlData += "</tr>";
		});
		$("#data").empty().html(htmlData);
	}

	);
	
	

}



function deletePurchaseRow(id, rowId) {
	$.get("deletePurchaseModel.html", {id : id}, function(data) {
		if(data == "Success") {
			alert("Purchase Order Successfully Deleted...!");
			$("#row"+rowId).remove();
		}	 else {
			alert("Purchase Order not deleted.. Please try again..!");
		}
	});
}


function getDateDetails(dates) {
	var details = dates.split("-");
	var value = details[2]+"-"+details[1]+"-"+details[0];
	return value;
}




function getDetails(pageNo) {
    $("#index").val(parseInt(parseInt(pageNo - 1) * parseInt($("#offset").val())));
    $("#pageNumber").val(pageNo);
    getData();
    printPageNumbers();

}