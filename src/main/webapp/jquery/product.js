/**
 * 
 */

$(document).ready(function() {
	$("#link").click(function() {
		
		$.get("ajaxTest.html", {}, function(data) {
			
			alert(data);
		});
	});
	
});