$(document).ready(function() {
	generatePageNumbers();
});

// light box content//

function getLightBoxContent(id) {
	$.get("getQuotationItems.html", {
		quotId : id
	}, function(data) {
		var htmlData = "";
		var a = 0;
		$.each(data, function(key, value) {
			var name, units, quantity, discount, tax, vat, totalAmt, unitPrice;
			name = value[0];
			units = value[1];
			quantity = value[2];
			unitPrice = value[3];
			discount = value[4];
			tax = value[5];
			vat = value[6];
			totalAmt = value[7];
			htmlData += "" + "<tr>" + "<td>" + (++a) + "</td>" + "<td>" + name
					+ "</td>" + "<td>" + units + "</td>" + "<td>" + quantity
					+ "</td>" + "<td>" + unitPrice + "</td>" + "<td>"
					+ discount + "</td>" + "<td>" + tax + "</td>" + "<td>"
					+ vat + "</td>" + "<td>" + totalAmt + "</td>" + "</tr>";

		});
		$("#load").css('display', 'none');
		$("#productData").empty().append(htmlData);
	});

}

// Open Light Box

function openLightBox(id) {
	var popUp = $('#light').bPopup({
		easing : 'easeOutBack', // uses jQuery easing plugin
		speed : 450,
		transition : 'slideDown'
	});

	getLightBoxContent(id);
}

// ------------------------------->> Pagination Content
// <<-----------------------------------------//

function generatePageNumbers() {
	getData();
	$.get("getQuotCount.html", {}, function(data) {
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

}

function getDetails(pageNo) {
	$("#index").val(
			parseInt(parseInt(pageNo - 1) * parseInt($("#offset").val())));
	$("#pageNumber").val(pageNo);
	getData();
	printPageNumbers();

}

function getData() {

	$
			.get(

					"quotCredentials.html",
					{
						offset : $("#offset").val(),
						index : $("#index").val()
					},

					function(data) {
						var htmlData = "";
						var i = 0;
						var indexValue = parseInt($("#index").val());
						$
								.each(
										data,
										function(key, value) {
											var a = (++i);
											htmlData += "<tr id = \"row" + a
													+ "\" >";

											var id, date, name, totalQuantity, totalAmount, reference, phone, date;
											id = value[0];
											name = value[1];
											totalAmount = value[2];
											totalQuantity = value[3];
											reference = value[4];
											phone = value[5];
											date = getDate(value[6]);

											htmlData += "<td>" + a + "</td>";
											htmlData += "<td>" + date + "</td>";
											htmlData += "<td>" + name + "</td>";
											htmlData += "<td>" + reference
													+ "</td>";
											htmlData += "<td>" + phone
													+ "</td>";
											htmlData += "<td>" + totalAmount
													+ "</td>";
											htmlData += "<td>" + totalQuantity
													+ "</td>";
											htmlData += "<td><a href = \"#\" onclick = \"openLightBox('"
													+ id
													+ "')\" >Click Here</a></td>"
											htmlData += "<td><a href= \"generateInvoice.html?quotId="+id+"\">Forward</a></td>";
											htmlData += "<td><a href= \"deleteQuot.html?id="
													+ id + "\">Delete</a></td>";
											htmlData += "</tr>";
										});
						$("#data").empty().html(htmlData);
					}

			);

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
					htmlData += "&nbsp;<input type = 'button' class = 'btn btn-danger' style = 'border:0px; width:30px; padding:5px;'  "
							+ "value = '"
							+ i
							+ "' "
							+ style
							+ " onclick = \"getDetails('" + i + "')\"/>&nbsp;";
				} else {
					htmlData += button + "value = '" + i + "'  " + style
							+ " onclick = \"getDetails('" + i + "')\"/>";
				}
			} else {
				break;
			}
		}

	} else {
		htmlData += button + "value = 'First' onclick = \"getDetails('" + 1
				+ "')\" style = 'border:0px;' />";
		for (var i = pageNumber - 2; i <= pageNumber + 2; i++) {
			if (i < pages) {

				var style = "style = 'width:20px; padding:5px; border:0px; '";
				if (i > 10) {
					style = " style = 'width:30px; padding : 5px;'";
				}
				if (i === pageNumber) {

					htmlData += "&nbsp;<input type = 'button' class = 'btn btn-danger' style = 'border:0px; width:30px; padding:5px;'  "
							+ "value = '"
							+ i
							+ "' "
							+ style
							+ " onclick = \"getDetails('" + i + "')\"/>&nbsp;";
				} else {
					htmlData += button + "value = '" + i + "' " + style
							+ " onclick = \"getDetails('" + i + "')\"/>";
				}

			}

			else {
				break;
			}
		}
	}

	htmlData += "&nbsp;<input type = 'button' class = 'btn btn-primary' style = 'border:0px;'value = 'Last'  onclick = \"getDetails('"
			+ pages + "')\"/>";
	$("#pageButtons").html(htmlData);
}

function getDate(date) {
	var dateValue = date.split("-");
	return dateValue[2] + " - " + dateValue[1] + " - " + dateValue[0];
}

// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
